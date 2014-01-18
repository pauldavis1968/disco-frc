/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.discobots.aerialassist.subsystems;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.discobots.aerialassist.HW;
import org.discobots.aerialassist.commands.CommandBase;
import org.discobots.aerialassist.commands.MecanumDrive;
import org.discobots.aerialassist.utils.BetterRobotDrive;
import org.discobots.aerialassist.utils.DiscoGyro;
import org.discobots.aerialassist.utils.Velocity;
//import robot.commands.CommandBase;
//import robot.commands.HolonomicPolar;

/**
 *
 * @author Sam
 */
public class Drivetrain extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Jaguar leftFront;
    Jaguar leftRear;
    Jaguar rightFront;
    Jaguar rightRear;
    BetterRobotDrive drive;
    private DoubleSolenoid sol;
    private DiscoGyro gyro = new DiscoGyro(HW.gyroChannel);
<<<<<<< HEAD
    public ADXL345_I2C accelerometer = new ADXL345_I2C(HW.accelModule, ADXL345_I2C.DataFormat_Range.k4G);
    public static final boolean MECANUM=true;
    public static final boolean TRACTION=false;
=======
    private ADXL345_I2C accelerometer = new ADXL345_I2C(HW.accelModule, ADXL345_I2C.DataFormat_Range.k4G);
    private Velocity velocityReporter;
>>>>>>> 6f82ae474d2574a321c7c4a76eb1963e65ae5150

    
    public Drivetrain(){
        super("Drivetrain");
        leftFront=new Jaguar(1,HW.leftFrontMotor);
        leftRear=new Jaguar(1,HW.leftRearMotor);
        rightFront=new Jaguar(1,HW.rightFrontMotor);
        rightRear=new Jaguar(1,HW.rightRearMotor);
        drive=new BetterRobotDrive(leftFront,leftRear,rightFront,rightRear);
        
        drive.setSafetyEnabled(false);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        //sol = new DoubleSolenoid(1,1);
        velocityReporter=new Velocity();
        
    }

    public void initDefaultCommand() {
        setDefaultCommand(new MecanumDrive(CommandBase.oi.getGP()));
    }
    
    public void holonomicPolar(double mag,double dir,double rot) {
        drive.mecanumDrive_Polar(mag, dir, rot);
    }
    public void PneuOut(){
        sol.set(DoubleSolenoid.Value.kForward);
    }
    public void PneuIn(){
        sol.set(DoubleSolenoid.Value.kReverse);
    }
    public DoubleSolenoid.Value checkPneu(){
        return sol.get();
    }
    public double getGyroAngle() {
        return gyro.getAngle();
    }
    public ADXL345_I2C getAccelerometer(){
        return accelerometer;
    }
    
    public double getXVelocity(){
        return velocityReporter.getXVelocity();
    }
    public double getYVelocity(){
        return velocityReporter.getYVelocity();
    }
}
