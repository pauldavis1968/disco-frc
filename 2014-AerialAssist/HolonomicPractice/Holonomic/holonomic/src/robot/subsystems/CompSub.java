/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 * @author Dylan
 */
public class CompSub extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    Compressor comp;
    public CompSub(){
        super("Compressor");
        comp = new Compressor(1,5,1,1);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void on(){
        comp.start();
    }
    public void off(){
        comp.stop();
    }
    public boolean check(){
        return comp.enabled();
    }
}
