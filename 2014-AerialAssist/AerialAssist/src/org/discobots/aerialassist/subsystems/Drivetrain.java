/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.discobots.aerialassist.subsystems;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.discobots.aerialassist.HW;
import org.discobots.aerialassist.commands.drive.MecanumDrive;
import org.discobots.aerialassist.utils.BetterRobotDrive;
import org.discobots.aerialassist.utils.DiscoGyro;
import org.discobots.aerialassist.utils.velocity.Velocity;

/**
 *
 * @author Sam
 */
public class Drivetrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    private Talon leftFront;
    private Talon leftRear;
    private Talon rightFront;
    private Talon rightRear;
    private BetterRobotDrive drive;
    private DoubleSolenoid shifter;
    private DiscoGyro gyro;
    private ADXL345_I2C accelerometer;
    private Velocity velocityReporter;
    public static final boolean MECANUM = false;
    public static final boolean TRACTION = true;
    private boolean currentState = MECANUM;    //Why does it start out as mecanum if TRACTION is used as the default?

    public Drivetrain() {
        super("Drivetrain");
        leftFront = new Talon(HW.motorModule, HW.leftFrontMotor);
        leftRear = new Talon(HW.motorModule, HW.leftRearMotor);
        rightFront = new Talon(HW.motorModule, HW.rightFrontMotor);
        rightRear = new Talon(HW.motorModule, HW.rightRearMotor);
        drive = new BetterRobotDrive(leftFront, leftRear, rightFront, rightRear);
        shifter = new DoubleSolenoid(HW.solonoidModule,HW.driveShiftSolenoidForward,HW.driveShiftSolenoidReverse);

        drive.setSafetyEnabled(false);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);//should be false
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);//should be false
        
        gyro = new DiscoGyro(HW.gyroChannel);
        accelerometer = new ADXL345_I2C(1, ADXL345_I2C.DataFormat_Range.k4G);

        if (Velocity.ENABLE_VELOCITY) {
            try {
                velocityReporter = new Velocity(accelerometer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
//        currentState=getShiftPosition();
        
        }
    }

    public void initDefaultCommand() {
        setDefaultCommand(new MecanumDrive());
        this.currentState = MECANUM;
    }

    public void holonomicPolar(double mag, double dir, double rot) {
        drive.mecanumDrive_Polar(mag, dir, rot);
    }

    public void tankDrive(double leftVal, double rightVal) {
        drive.tankDrive(leftVal, rightVal);
    }

    public void shiftTraction() {
        shifter.set(DoubleSolenoid.Value.kForward);
        System.out.println("Traction Mode Engaged");
        currentState = TRACTION;
    }

    public void shiftMecanum() {
        shifter.set(DoubleSolenoid.Value.kReverse);
        System.out.println("Mecanum Mode Engaged");
        currentState = MECANUM;
    }

    public boolean getShiftPosition() {
        return shifter.get() == DoubleSolenoid.Value.kForward;
    }

    public double getGyroAngle() {
        return gyro.getAngle();
    }

    public DiscoGyro getGyro() {
        return gyro;
    }

    public boolean getDriveState() {
        return currentState;
    }
    
    public ADXL345_I2C getAccelerometer() {
        return accelerometer;
    }

    public double getXVelocity() {
        return velocityReporter.getXVelocity();
    }

    public double getYVelocity() {
        return velocityReporter.getYVelocity();
    }
}