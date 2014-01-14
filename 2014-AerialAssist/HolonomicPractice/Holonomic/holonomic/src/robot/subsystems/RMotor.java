/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.subsystems;
import edu.wpi.first.wpilibj.Jaguar;
import robot.HW;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Dylan
 */
public class RMotor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Jaguar rmotor;
    double power;
    int time;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public RMotor(){
        rmotor=new Jaguar(1,5);
        power=.8;
        time=220;
    }
    public void set(double power1){
        rmotor.set(power);
    }
    public void pIncrement(double power2){
        if((power+power2<=1)&&(power+power2>0))
            power+=power2;
        else if(power+power2>1)
            power=1;
        else if(power+power2<=0)
            power=0;        
    }
    public void tIncrement(int time1){
        time+=time1;
    }
    public int gett(){
        return time;
    }
     public double getp(){
        return power;
    }
}
