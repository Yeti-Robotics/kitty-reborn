// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.mechanisms.swerve.LegacySwerveModule;
import com.ctre.phoenix6.swerve.SwerveDrivetrain;
import com.ctre.phoenix6.swerve.SwerveModule;
import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.CommandSwerveDrivetrain;

import static frc.robot.Constants.MaxAngularRate;
import static frc.robot.Constants.MaxSpeed;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    private final CommandXboxController joystick = new CommandXboxController(0);

    CommandXboxController xboxController;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        xboxController = new CommandXboxController(frc.robot.Constants.XBOX_CONTROLLER_PORT);
        configureBindings();
    }


    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
         final SwerveRequest.FieldCentric m_driveRequest = new SwerveRequest.FieldCentric()
                .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1)
                .withDriveRequestType(SwerveModule.DriveRequestType.OpenLoopVoltage)
                .withSteerRequestType(SwerveModule.SteerRequestType.MotionMagicExpo);

         final CommandSwerveDrivetrain m_drivetrain = TunerConstants.createDrivetrain();
         m_drivetrain.setDefaultCommand(
                 m_drivetrain.applyRequest(() ->
                         m_driveRequest.withVelocityX(-joystick.getLeftY() * TunerConstants.kSpeedAt12Volts.magnitude())
                                 .withVelocityY(-joystick.getLeftX() * TunerConstants.kSpeedAt12Volts.magnitude())
                                 .withRotationalRate(-joystick.getRightX() * TunerConstants.kSpeedAt12Volts.magnitude())
                 )
         );
    }




    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
}
