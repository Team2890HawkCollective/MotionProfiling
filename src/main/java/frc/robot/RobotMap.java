/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

import frc.robot.subsystems.DrivetrainSubsystem;

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

  final public static int FRONT_LEFT_TALON_ID = 7;
  final public static int FRONT_RIGHT_TALON_ID = 6;
  //switched from 3 to 2
  final public static int REAR_LEFT_TALON_ID = 2;
  final public static int REAR_RIGHT_TALON_ID = 1;

  final public static int NAV_X_CAN_ID = 12;

  final public static int LINEAR_PIDF_SLOT = 0;
  final public static int ROTATIONAL_PIDF_SLOT = 1;

  final public static double LINEAR_P = 0.7;
  final public static double LINEAR_I = 0;
  final public static double LINEAR_D = 0;
  final public static double LINEAR_F = 0.7;
  final public static int LINEAR_I_ZONE = 0;

  final public static double ROTATIONAL_P = 0.7;
  final public static double ROTATIONAL_I = 0;
  final public static double ROTATIONAL_D = 0;
  final public static double ROTATIONAL_F = 0;
  final public static int ROTATIONAL_I_ZONE = 0;

  final public static SPI.Port NAV_X_PORT = SPI.Port.kMXP;

  public static AHRS navX;

  public static DrivetrainSubsystem drivetrain;
  
  public static WPI_TalonSRX frontLeftTalon;
  public static WPI_TalonSRX frontRightTalon;
  public static WPI_TalonSRX rearLeftTalon;
  public static WPI_TalonSRX rearRightTalon;

  public static CANifier navXCAN;

  public static void init()
  {

    frontLeftTalon = new WPI_TalonSRX(FRONT_LEFT_TALON_ID);
    frontRightTalon = new WPI_TalonSRX(FRONT_RIGHT_TALON_ID);
    rearLeftTalon = new WPI_TalonSRX(REAR_LEFT_TALON_ID);
    rearRightTalon = new WPI_TalonSRX(REAR_RIGHT_TALON_ID);

    navX = new AHRS(NAV_X_PORT);

    //navXCAN = new CANifier(NAV_X_CAN_ID);
    
    drivetrain = new DrivetrainSubsystem();

    configureTalons();
  }

  private static void configureTalons()
  {
    //Config Front Left Talon
    frontLeftTalon.setSensorPhase(false);
    frontLeftTalon.setInverted(true);

    frontLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 5);
    frontLeftTalon.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, 0);

    //Config Front Right Talon
    frontRightTalon.setSensorPhase(false);
    frontRightTalon.setInverted(true);

    frontRightTalon.configRemoteFeedbackFilter(frontRightTalon.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0, 0);
    //frontRightTalon.configRemoteFeedbackFilter(navXCAN.getDeviceID(), RemoteSensorSource.CANifier_PWMInput3, 1, 0);

    frontRightTalon.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, 0);
    frontRightTalon.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.QuadEncoder, 0);

    frontRightTalon.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, 0);
    frontRightTalon.configSelectedFeedbackCoefficient(0.5, 0, 0);

    // Configure NavX via CANifier or PigeonIMU
    //frontRightTalon.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor1, 1, 0);
    //frontRightTalon.configSelectedFeedbackCoefficient((3600.0 / 8192.0), 1, 0);

    //Config Rear talons
    rearLeftTalon.setInverted(false);
    rearRightTalon.setInverted(true);
    
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
