/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous_commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.*;

/**
 * Add your docs here.
 */
public class getGyroValuesCommand extends TimedCommand 
{
  private static double maxAccel = 0.0;
  private static double maxVel = 0.0;

  /**
   * Add your docs here.
   */
  public getGyroValuesCommand(double timeout) {
    super(timeout);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(RobotMap.drivetrain);
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
    RobotMap.drivetrain.forwards();

    if (RobotMap.navX.getRawAccelX() > maxAccel)
      maxAccel = RobotMap.navX.getRawAccelX();

    if (RobotMap.navX.getVelocityX() > maxVel)
      maxVel = RobotMap.navX.getVelocityX();
  }

  // Called once after timeout
  @Override
  protected void end() {
    System.out.println("Acceleration: " + maxAccel);
    System.out.println("Velocity: " + maxVel);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
