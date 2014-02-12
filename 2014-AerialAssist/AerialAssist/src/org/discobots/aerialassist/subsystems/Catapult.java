package org.discobots.aerialassist.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.discobots.aerialassist.HW;

/**
 *
 * @author Patrick
 */
public class Catapult extends Subsystem {

    private Victor catapultLauncher;
    private boolean manualFire;
    DigitalInput limitSwitch;
    Counter counter;


    public void initDefaultCommand() {
    }

    public Catapult() {
        catapultLauncher = new Victor(1, HW.catapultMotor);
        limitSwitch = new DigitalInput(1, HW.chooChooTouchSensor);
        counter = new Counter(limitSwitch);
        System.out.println(catapultLauncher.toString() + " --------------------------");
        System.out.println(counter.toString());
        System.out.println(limitSwitch.toString());
        
    }

    public void run() {
        catapultLauncher.set(1);
    }
    
    public boolean isSwitchSet() {
        return counter.get() > 0;
    }

    public void stop() {
        catapultLauncher.set(0);
    }
    
    public void initializeCounter() {
        counter.reset();
    }

    public void toggleManualFire() {
        manualFire = !manualFire;
    }

    public boolean getManual() {
        return manualFire;
    }
    
    public boolean getLimitValue()
    {
        return limitSwitch.get();
    }
    
}