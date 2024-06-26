package frc.robot.commands.FullSystemCommandsTeleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotContainer;

public class PrintPose extends Command {
    public PrintPose() {
    }
    
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("Auto Robot Pose(X)", RobotContainer.m_robotDrive.getPose().getX());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
