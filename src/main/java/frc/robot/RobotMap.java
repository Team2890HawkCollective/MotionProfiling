/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/*import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import frc.robot.commands.autonomous_commands.MotionProfilingTestingCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.team319.models.BobTalonSRX;
import frc.team319.models.LeaderBobTalonSRX;
import frc.team319.models.SRXGains;*/

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

  final public static int FRONT_LEFT_TALON_ID = 3;
  final public static int FRONT_RIGHT_TALON_ID = 1;
  final public static int REAR_LEFT_TALON_ID = 2;
  final public static int REAR_RIGHT_TALON_ID = 4;
  /*
  final public static int LINEAR_PIDF_SLOT = 0;
  final public static int ROTATIONAL_PIDF_SLOT = 1;

  final public static int linearP = 0;
  final public static int linearI = 0;
  final public static int linearD = 0;
  final public static int linearF = 0;
  final public static int linearIZone = 0;

  final public static int rotationalP = 0;
  final public static int rotationalI = 0;
  final public static int rotationalD = 0;
  final public static int rotationalF = 0;
  final public static int rotationalIZone = 0;

  public static LeaderBobTalonSRX frontLeftTalon;
  public static LeaderBobTalonSRX frontRightTalon;

  public static SRXGains linearGains;
  public static SRXGains rotationalGains;

  public static MotionProfilingTestingCommandGroup testingCommandGroup;

  public static DrivetrainSubsystem drivetrain;*/
  
  public static WPI_TalonSRX frontLeftTalon;
  public static WPI_TalonSRX frontRightTalon;
  public static WPI_TalonSRX rearLeftTalon;
  public static WPI_TalonSRX rearRightTalon;

  public static void init()
  {
    // Create new "Leader talons", where when a speed is set to the lead talons, the follower talons also are set that speed
    // Parameters: leaderDeviceID, follower BobTalonSRX/Any MotorController)
    /*frontLeftTalon = new LeaderBobTalonSRX(FRONT_LEFT_TALON_ID, new BobTalonSRX(REAR_LEFT_TALON_ID));
    frontRightTalon = new LeaderBobTalonSRX(FRONT_RIGHT_TALON_ID, new BobTalonSRX(REAR_RIGHT_TALON_ID));

    linearGains = new SRXGains(LINEAR_PIDF_SLOT, linearP, linearI, linearD, linearF, linearIZone);
    rotationalGains  = new SRXGains(ROTATIONAL_PIDF_SLOT, rotationalP, rotationalI, rotationalD, rotationalF, rotationalIZone);

    testingCommandGroup = new MotionProfilingTestingCommandGroup();
    
    drivetrain = new DrivetrainSubsystem();

    frontLeftTalon.configPrimaryFeedbackDevice(FeedbackDevice.CTRE_MagEncoder_Relative);
    frontRightTalon.configPrimaryFeedbackDevice(FeedbackDevice.CTRE_MagEncoder_Relative);

    SRXGains[] gains = {linearGains, rotationalGains};
    setGains(gains);*/
    
    frontLeftTalon = new WPI_TalonSRX(FRONT_LEFT_TALON_ID);
    frontRightTalon = new WPI_TalonSRX(FRONT_RIGHT_TALON_ID);
    rearLeftTalon = new WPI_TalonSRX(REAR_LEFT_TALON_ID);
    rearRightTalon = new WPI_TalonSRX(REAR_RIGHT_TALON_ID);
    
    rearLeftTalon.follow(frontLeftTalon);
    rearRightTalon.follow(frontRightTalon);
  }

  /*private static void setGains(SRXGains[] gains)
  {
    for (SRXGains a : gains)
    {
      frontLeftTalon.setGains(a);
      frontRightTalon.setGains(a);
    }
  }*/
}
