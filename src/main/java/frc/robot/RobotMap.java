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
import frc.robot.subsystems.SensorSubsystem;

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

  //Component Ports/IDs
    //Talon IDs
      final public static int FRONT_LEFT_TALON_ID = 7;
      final public static int FRONT_RIGHT_TALON_ID = 6;
      final public static int REAR_LEFT_TALON_ID = 2;
      final public static int REAR_RIGHT_TALON_ID = 1;

    //Other IDs
      //NavX Port/ID
        final public static int NAV_X_CAN_ID = 12;
        final public static SPI.Port NAV_X_PORT = SPI.Port.kMXP;

      final public static int LINEAR_PIDF_SLOT = 0;
      final public static int ROTATIONAL_PIDF_SLOT = 1;

  //PIDF Values
    //Linear(straight) PIDF Values
      final public static double LINEAR_P = 0.7;
      final public static double LINEAR_I = 0;
      final public static double LINEAR_D = 0;
      final public static double LINEAR_F = 0.7;

    //Rotational(turning) PIDF Values
      final public static double ROTATIONAL_P = 0.7;
      final public static double ROTATIONAL_I = 0;
      final public static double ROTATIONAL_D = 0;
      final public static double ROTATIONAL_F = 0;

  //Objects
    //Talons
    public static WPI_TalonSRX frontLeftTalon;
    public static WPI_TalonSRX frontRightTalon;
    public static WPI_TalonSRX rearLeftTalon;
    public static WPI_TalonSRX rearRightTalon;

    //Sensors
      public static AHRS navX;
      public static CANifier navXCAN;

    //Commands/Subsystems
      //Commands
      //Subsystems
        public static DrivetrainSubsystem drivetrainSubsystem;
        public static SensorSubsystem sensorSubsystem;

  public static void init()
  {

    frontLeftTalon = new WPI_TalonSRX(FRONT_LEFT_TALON_ID);
    frontRightTalon = new WPI_TalonSRX(FRONT_RIGHT_TALON_ID);
    rearLeftTalon = new WPI_TalonSRX(REAR_LEFT_TALON_ID);
    rearRightTalon = new WPI_TalonSRX(REAR_RIGHT_TALON_ID);

    navX = new AHRS(NAV_X_PORT);

    //navXCAN = new CANifier(NAV_X_CAN_ID);
    
    drivetrainSubsystem = new DrivetrainSubsystem();

    configureTalons();
  }

  //Sets the Subsystems and Names for ShuffleBoard
  private static void setSubsystems()
  {
    //Components
      //Talons
        frontLeftTalon.setName("DrivetrainSubsystem", "FrontLeftDriveTalon");
        frontRightTalon.setName("DrivetrainSubsystem", "FrontRightDriveTalon");
        rearLeftTalon.setName("DrivetrainSubsystem", "RearLeftDriveTalon");
        rearRightTalon.setName("DrivetrainSubsystem", "RearRightDriveTalon");
      //Sensors
      navX.setName("SensorSubsystem", "NavX");
    
    //Subsystems
      drivetrainSubsystem.setName("DrivetrainSubsystem", "DrivetrainSubsystem");
      sensorSubsystem.setName("SensorSubsystem", "SensorSubsystem");
  }

  /**
   * Configures the talons inversion, sensor phase, sensors, PIDF, and follow mode
   */
  private static void configureTalons()
  {
    configTalonSensors();

    configPIDF(LINEAR_PIDF_SLOT, LINEAR_P, LINEAR_I, LINEAR_D, LINEAR_F);

    //Config Front Left Talon
      frontLeftTalon.setSensorPhase(false);
      frontLeftTalon.setInverted(true);

    //Config Front Right Talon
      frontRightTalon.setSensorPhase(false);
      frontRightTalon.setInverted(true);

    //Config Rear talons
      rearLeftTalon.setInverted(false);
      rearRightTalon.setInverted(true);
    
      rearLeftTalon.follow(frontLeftTalon);
      rearRightTalon.follow(frontRightTalon);
  }

  /**
   * Configures the front left and right talons for use during motionProfiling
   */
  private static void configTalonSensors()
  {
    //Left Talon
      frontLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 5); //set encoder
      frontLeftTalon.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, 0); //Set the update rate

    //Right Talon
      frontRightTalon.configRemoteFeedbackFilter(frontLeftTalon.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0, 0); //Config the right talon to look at the left talon as a sensor
      //frontRightTalon.configRemoteFeedbackFilter(navXCAN.getDeviceID(), RemoteSensorSource.CANifier_PWMInput3, 1, 0); //Config the right talon to use the navX (over CANifier) as a gyro sensor

      //Config the primary sensor of the right talon to be a sum sensor (to average the left/right sides, telling us the overall position/speed of the bot)
        frontRightTalon.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, 0); 
        frontRightTalon.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, 0);

      frontRightTalon.configSelectedFeedbackSensor(FeedbackDevice.SensorSum, 0, 0);
      frontRightTalon.configSelectedFeedbackCoefficient(0.5, 0, 0);

    // Configure NavX via CANifier or PigeonIMU
      //frontRightTalon.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor1, 1, 0); //Set the secondary sensor of the right talon to be the navX (via CANifier) 
      //frontRightTalon.configSelectedFeedbackCoefficient((3600.0 / 8192.0), 1, 0);
  }

  /**
   * Configures the PIDF's of the front left and right talons. <br>
   * (see: Talon SRX Software Reference/Team319 BobTrajectory Wiki)
   * @param pidSlot The slot to place the values in (typically 0)
   * @param p The P-Gain to use
   * @param i The I-Gain to use
   * @param d The D_Gain to use
   * @param f The F-Gain to use
   */
  private static void configPIDF(int pidSlot, double p, double i, double d, double f)
  {
    frontLeftTalon.config_kP(pidSlot, p, 0);
    frontLeftTalon.config_kI(pidSlot, i, 0);
    frontLeftTalon.config_kD(pidSlot, d, 0);
    frontLeftTalon.config_kF(pidSlot, f, 0);

    frontRightTalon.config_kP(pidSlot, p, 0);
    frontRightTalon.config_kI(pidSlot, i, 0);
    frontRightTalon.config_kD(pidSlot, d, 0);
    frontRightTalon.config_kF(pidSlot, f, 0);
  }
}
