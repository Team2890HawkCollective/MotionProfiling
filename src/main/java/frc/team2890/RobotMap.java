/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team2890;

import frc.team319.models.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  final public static int FRONT_LEFT_TALON_ID = 0;
  final public static int FRONT_RIGHT_TALON_ID = 0;
  final public static int REAR_LEFT_TALON_ID = 0;
  final public static int REAR_RIGHT_TALON_ID = 0;

  public static LeaderBobTalonSRX frontLeftTalon;
  public static LeaderBobTalonSRX frontRightTalon;

  public static void init()
  {
    // Create new "Leader talons", where when a speed is set to the lead talons, the follower talons also are set that speed
    // Parameters: leaderDeviceID, follower BobTalonSRX/Any MotorController)
    frontLeftTalon = new LeaderBobTalonSRX(FRONT_LEFT_TALON_ID, new BobTalonSRX(REAR_LEFT_TALON_ID));
    frontRightTalon = new LeaderBobTalonSRX(FRONT_RIGHT_TALON_ID, new BobTalonSRX(REAR_RIGHT_TALON_ID));
  }
}
