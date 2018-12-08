/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous_commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.RobotMap;

public class getVelocityViaEncoders extends Command 
{
  private int maxVel = 0;
  private int maxErr = 0;
  private boolean step = false;

  public getVelocityViaEncoders(boolean s) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotMap.drivetrainSubsystem);
    step = s;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    int vel = RobotMap.frontRightTalon.getSelectedSensorVelocity(0);
    int err = RobotMap.frontRightTalon.getClosedLoopError(0);

    System.out.println("Velocity: " + vel);

    if (vel > maxVel)
      maxVel = vel;

    if (step)
    {
      RobotMap.frontLeftTalon.set(ControlMode.Velocity, 4096 * 500 / 600);
      RobotMap.frontRightTalon.set(ControlMode.Velocity, 4096 * 500 / 600);
      System.out.println("Err: " + err);
      System.out.println("Trgt: " + 4096 * 500 / 600);

      if (err > maxErr)
        maxErr = err;
    }
    else
    {
      RobotMap.frontLeftTalon.set(1);
      RobotMap.frontRightTalon.set(1);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    if (step)
      System.out.println("P-Gain: " + (.1 * 1023) / maxErr);
    else
      System.out.println("F-Gain: " + (1 * 1023) / maxVel);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
  }
}
