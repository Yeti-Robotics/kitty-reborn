// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.swerve.SwerveModule;
import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.constants.Constants;
import frc.robot.constants.Intake.IntakeSubsystem;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ArmSubsystemConfig;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import static frc.robot.Constants.MaxAngularRate;
import static frc.robot.Constants.MaxSpeed;

public class RobotContainer {
    private final CommandXboxController joystick = new CommandXboxController(0);
    CommandXboxController xboxController;
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final ArmSubsystem armSubsystem = new ArmSubsystem();

    public RobotContainer() {
        xboxController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
        configureBindings();
    }

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
        xboxController.leftBumper().onTrue(armSubsystem.deployArm(ArmSubsystemConfig.ArmPositions.DEPLOY));
        xboxController.rightBumper().onTrue(armSubsystem.deployArm(ArmSubsystemConfig.ArmPositions.HANDOFF));
        // if true, intakes note, if false spits the note out


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
