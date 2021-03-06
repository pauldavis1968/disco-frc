/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package robt.commands.arm;

import robt.commands.CommandBase;

/**
 *
 * @author Developer
 */
public class ToggleIntake extends CommandBase {
    
    public ToggleIntake() {
        requires(intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
         if(intake.getEnabled()){
            intake.intakeOff();
        }
        else{
            intake.intakeOn();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
