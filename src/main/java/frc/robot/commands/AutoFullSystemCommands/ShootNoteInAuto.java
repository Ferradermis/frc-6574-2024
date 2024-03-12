package frc.robot.commands.AutoFullSystemCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.RobotConstants;
import frc.robot.commands.SetShooterWristPosition;
import frc.robot.commands.ShootNote;

public class ShootNoteInAuto extends SequentialCommandGroup{
    /** Creates a new ShootInAuto */
    public ShootNoteInAuto() {
        addCommands(
            new SetShooterWristPosition(RobotConstants.shooterWristHome),
            new ShootNote().withTimeout(1)
            );
    }
}
