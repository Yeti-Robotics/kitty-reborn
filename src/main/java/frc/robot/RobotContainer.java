// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.constants.Constants;
import frc.robot.subsystems.ArmSubsystem;

public class RobotContainer {
    final ArmSubsystem arm = new ArmSubsystem();

    CommandXboxController xboxController;

    public RobotContainer() {
        xboxController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
        configureBindings();
    }

    private void configureBindings() {
        xboxController.rightBumper().onTrue(arm.moveUpAndStop(0.5));
        xboxController.leftBumper().onTrue(arm.moveDownAndStop(0.5));
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
