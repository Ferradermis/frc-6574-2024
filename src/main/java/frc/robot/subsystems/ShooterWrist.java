// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterWrist extends SubsystemBase {

  public CANSparkMax shooterWristMotor;
  public AbsoluteEncoder m_AbsoluteEncoder;
  //private RelativeEncoder wristEncoder;

  private SparkPIDController shooterWristPIDController;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  //private double maxSpeed = 0.25;
  //private double deadBand = 0.1;

  /** Creates a new ShooterWrist. */
  public ShooterWrist() {
    shooterWristMotor = new CANSparkMax(Constants.RobotConstants.shooterWristCANID, MotorType.kBrushless);
    shooterWristMotor.restoreFactoryDefaults();

    shooterWristPIDController = shooterWristMotor.getPIDController();
    m_AbsoluteEncoder = shooterWristMotor.getAbsoluteEncoder(Type.kDutyCycle);
    shooterWristPIDController.setFeedbackDevice(m_AbsoluteEncoder);


    //m_AbsoluteEncoder.setPositionConversionFactor(360);
    //m_AbsoluteEncoder.setVelocityConversionFactor(1);
    shooterWristMotor.setInverted(true);
    shooterWristMotor.setIdleMode(IdleMode.kBrake);
    m_AbsoluteEncoder.setZeroOffset(0.6526145);

    shooterWristMotor.setSmartCurrentLimit(45);

    kP = 2.8; //2.5 last working value
    kI = 0;
    kD = 0;
    kIz = 0;
    kFF = 0;
    kMaxOutput = .5;
    kMinOutput = -.5;

    shooterWristPIDController.setP(kP);
    shooterWristPIDController.setI(kI);
    shooterWristPIDController.setD(kD);
    shooterWristPIDController.setIZone(kIz);
    shooterWristPIDController.setFF(kFF);
    shooterWristPIDController.setOutputRange(kMinOutput, kMaxOutput);

    shooterWristPIDController.setPositionPIDWrappingEnabled(true);
    shooterWristPIDController.setPositionPIDWrappingMinInput(0);
    shooterWristPIDController.setPositionPIDWrappingMaxInput(1);
  }

  @Override

  public void periodic() {
    SmartDashboard.putNumber("Shooter Wrist Position", getAbsoluteEncoderPosition());
    //SmartDashboard.putNumber("Wrist Joystick", RobotContainer.operator.getRawAxis(5));
    //SmartDashboard.putNumber("Wrist encoder", wristMotor.getEncoder().getPosition());

    /* if (RobotContainer.operator.getRawAxis(5) > deadBand) {
      wristMotor.set(-RobotContainer.operator.getRawAxis(5) * maxSpeed);
    } else if (RobotContainer.operator.getRawAxis(5) < -deadBand) {
      wristMotor.set(-RobotContainer.operator.getRawAxis(5) * maxSpeed);
    } */



    /* if (RobotContainer.operator.getRawButtonPressed(1)) {
      intakeMotor.set(1);
    }
      else if (RobotContainer.operator.getRawButtonReleased(1)) {
        intakeMotor.set(0);
      }

      if (RobotContainer.operator.getRawButtonPressed(2)) {
        intakeMotor.set(-1);
      }
      else if (RobotContainer.operator.getRawButtonReleased(2)) {
        intakeMotor.set(0);
      } */


    }




  public void setSpeed(double speed)
  {
    shooterWristMotor.set(speed);
  }

  public void stop()
  {
    shooterWristMotor.stopMotor();
  }


  public double getAbsoluteEncoderPosition() {
    return m_AbsoluteEncoder.getPosition();
  }
  public void setPosition(double position) {
    shooterWristPIDController.setReference(position, CANSparkMax.ControlType.kPosition);
  }

}