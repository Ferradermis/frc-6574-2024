package frc.robot.commands.FullSystemCommandsTeleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.RobotConstants;
import frc.robot.RobotContainer;

public class Shoot extends Command{
    //private double speed;
  /** Creates a new setWristIntakeSpeed. */
  public Shoot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.intake);
    addRequirements(RobotContainer.shooter);
    //this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.intake.disableIntakeLimitSwitch();
    RobotContainer.shooter.setShooterVelocityUsingMotionMagic(80);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.shooter.getVelocity() >= RobotConstants.shooterVelocityRPS)
    {
        RobotContainer.intake.setIntakeSpeed(0, -1, 1);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter.setShooterSpeed(0);
    RobotContainer.intake.setIntakeSpeed(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
