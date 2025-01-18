package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.constants.Constants;
import frc.robot.FeederSubsystem;
import edu.wpi.first.wpilibj2.command.RunCommand;

public class RobotContainer {

    CommandXboxController xboxController;
    private final FeederSubsystem feederSubsystem;

    public RobotContainer() {
        xboxController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
        feederSubsystem = FeederSubsystem.getInstance();
        configureBindings();
    }

    private void configureBindings() {
        // Sets the button of the feeder to be A
        xboxController.a().whileTrue(feederSubsystem.runFeederCommand(1));
    }

    public Command getAutonomousCommand() {
        return null;
    }
}