package frc.robot.commands.AutoFullSystemCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.Constants.OIConstants;
import frc.robot.LimelightHelpers;
import frc.robot.RobotContainer;

//I am very scared to run this. - Ace
public class LimelightDriveToTarget extends Command{
    private double rotation;
    private double forward;
    private boolean fieldRelative;

    /** Creates a new LimelightDriveToTarget */
    public LimelightDriveToTarget() {
        addRequirements(RobotContainer.m_robotDrive);
    }
    public void initialize() {
        rotation = RobotContainer.limelight.limelight_aim_proportional();
        forward = RobotContainer.limelight.limelight_range_proportional();
        fieldRelative = false;
    }

    public void execute() {
        RobotContainer.m_robotDrive.drive(
        -MathUtil.applyDeadband(RobotContainer.m_driverController.getLeftY(), OIConstants.kDriveDeadband), 
        -MathUtil.applyDeadband(RobotContainer.m_driverController.getLeftX(), OIConstants.kDriveDeadband), 
        rotation, 
        fieldRelative, 
        true);
    }

    public void end(boolean interrupted) {
        RobotContainer.m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(
            () -> RobotContainer.m_robotDrive.drive(
                -MathUtil.applyDeadband(RobotContainer.m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(RobotContainer.m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(RobotContainer.m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true),
            RobotContainer.m_robotDrive));
            
    }

    /*
    @Override
    public boolean isFinished() {
        if ((Math.abs(LimelightHelpers.getTX("limelight")) <= 0.5) //&& (Math.abs(LimelightHelpers.getTY("limelight")) <= 0.25)
        ) {
        return true;
        } else {
        return false;
        }
    }
    */
}
