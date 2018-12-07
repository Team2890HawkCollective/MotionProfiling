/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team319.follower.FollowsArc;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DrivetrainSubsystem extends Subsystem implements FollowsArc{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void forwards()
  {
    RobotMap.frontLeftTalon.set(ControlMode.PercentOutput, -1.0);
    RobotMap.frontRightTalon.set(ControlMode.PercentOutput, -1.0);
  }

  public void backwards()
  {
    RobotMap.frontLeftTalon.set(ControlMode.PercentOutput, 1.0);
    RobotMap.frontRightTalon.set(ControlMode.PercentOutput, 1.0);
  }

  public void rotateLeft()
  {
    RobotMap.frontLeftTalon.set(ControlMode.PercentOutput, 1.0);
    RobotMap.frontRightTalon.set(ControlMode.PercentOutput, -1.0);
  }

  public void rotateRight()
  {
    RobotMap.frontLeftTalon.set(ControlMode.PercentOutput, -1.0);
    RobotMap.frontRightTalon.set(ControlMode.PercentOutput, 1.0);
  }

  public void stop()
  {
    RobotMap.frontLeftTalon.set(ControlMode.PercentOutput, 0);
    RobotMap.frontRightTalon.set(ControlMode.PercentOutput, 0);
  }

  // This should return your left talon object
  @Override
  public WPI_TalonSRX getLeft() {
    return RobotMap.frontLeftTalon; 
  }

  // This should return your right talon object
  @Override
  public WPI_TalonSRX getRight() {
    return RobotMap.frontRightTalon; 
  }

  // This should return the current value of your sum sensor that will be configured in a future step
  @Override
  public double getDistance() {
    return RobotMap.frontRightTalon.getSelectedSensorPosition(0);
  }
  
  // This should return the instance of your drive train
  @Override
  public Subsystem getRequiredSubsystem() {
    return this;
  }
}
