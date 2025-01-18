// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.constants.Constants;
import frc.robot.subsystems.limelite.vision;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import java.util.function.BooleanSupplier;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    private final Joystick driverJoystick = new Joystick(0);

    XboxController xboxController;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);
        configureBindings();
    }


    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link CommandPS4Controller
     * PS4} controllers or {@link CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        new JoystickButton(driverJoystick, 1).onTrue(new InstantCommand(this::updateLimeLightStatus));

        new JoystickButton(driverJoystick, 2).onTrue(new InstantCommand(this::adjustPositionOfCrosshair));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }

    private void updateLimeLightStatus(){
        boolean isTargetDetected = vision.isTarget();
        vision.updateLedMode(isTargetDetected);
    }

    private void adjustPositionOfCrosshair(){
        double tx = vision.getTx();
        double ty = vision.getTy();

        if (Math.abs(tx) > 1.00){
            System.out.println("Crosshair IS NOT aligned horizontally by [ " + tx + " ]");
        }
        else{
            System.out.println("Crosshair IS aligned horizontally by [ " + tx + " ]");
        }

        if (Math.abs(ty) > 1.00){
            System.out.println("Crosshair IS NOT aligned vertically by [ " + ty + " ]");
        }
        else{
            System.out.println("Crosshair IS aligned horizontally by [ " + ty + " ]");
        }

    }
}

