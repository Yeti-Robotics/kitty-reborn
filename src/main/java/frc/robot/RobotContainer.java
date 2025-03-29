package frc.robot;

import com.ctre.phoenix6.swerve.SwerveModule;
import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.constants.Constants;
import frc.robot.subsystems.arm.ArmPositions;
import frc.robot.subsystems.feeder.FeederSubsystem;
import frc.robot.subsystems.flywheel.FlywheelSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.drivetrain.TunerConstants;
import frc.robot.subsystems.arm.ArmSubsystem;
import frc.robot.subsystems.drivetrain.CommandSwerveDrivetrain;
import frc.robot.subsystems.pivot.PivotSubsystem;
import frc.robot.subsystems.pivot.PivotPositions;

import static frc.robot.Constants.MaxAngularRate;
import static frc.robot.Constants.MaxSpeed;

public class RobotContainer {
    CommandXboxController xboxController;
    private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
    private final ArmSubsystem armSubsystem = new ArmSubsystem();
    private final PivotSubsystem pivotSubsystem = new PivotSubsystem();
    private final FeederSubsystem feederSubsystem = new FeederSubsystem();
    private final FlywheelSubsystem flywheelSubsystem = new FlywheelSubsystem();


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
                         m_driveRequest.withVelocityX(MathUtil.applyDeadband(-xboxController.getLeftY(), 0.05) * TunerConstants.kSpeedAt12Volts.magnitude())
                                 .withVelocityY(MathUtil.applyDeadband(-xboxController.getLeftX(), 0.05) * TunerConstants.kSpeedAt12Volts.magnitude())
                                 .withRotationalRate(-xboxController.getRightX() * TunerConstants.kSpeedAt12Volts.magnitude())
                 )
         );
         xboxController.start().onTrue(m_drivetrain.runOnce(m_drivetrain::seedFieldCentric));

        xboxController.leftTrigger().onTrue(armSubsystem.armToPosition(ArmPositions.DEPLOY));
//        xboxController.rightTrigger().onTrue(armSubsystem.armToPosition(ArmPositions.HANDOFF));
        xboxController.rightBumper().whileTrue(intakeSubsystem.in());
        xboxController.leftBumper().whileTrue(intakeSubsystem.out());

        xboxController.x().onTrue(pivotSubsystem.pivotToPosition(PivotPositions.HOME));
//        xboxController.a().onTrue(pivotSubsystem.pivotToPosition((PivotPositions.AIM)));

        xboxController.rightTrigger()
                .onTrue(
                        armSubsystem.armToPosition(ArmPositions.HANDOFF)
                        .andThen(pivotSubsystem.pivotToPosition(PivotPositions.HANDOFF))
                        .andThen(intakeSubsystem.out())
                        .alongWith(feederSubsystem.feedNote()));

//        xboxController.rightTrigger()
//                .whileTrue(flywheelSubsystem.spinShooter()
//                        .andThen(feederSubsystem.spinFeeder()
//                                .alongWith(flywheelSubsystem.spinShooter())));
    }


    public Command getAutonomousCommand() {
        return null;
    }
}
