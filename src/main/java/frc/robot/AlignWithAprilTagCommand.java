package frc.robot;

import com.ctre.phoenix6.swerve.SwerveRequest;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CommandSwerveDrivetrain;
import frc.robot.subsystems.limelite.Vision;

public class AlignWithAprilTagCommand extends Command {
    private final CommandSwerveDrivetrain drivetrain;
    private final Vision vision;
    private final PIDController pidController;

    public AlignWithAprilTagCommand(CommandSwerveDrivetrain drivetrain, Vision vision) {
        this.drivetrain = drivetrain;
        this.vision = vision;
        this.pidController = new PIDController(0.02, 0.0, 0.002); // Tuned PID values
        pidController.setTolerance(1.0);

        addRequirements(drivetrain, vision);
    }

    @Override
    public void initialize() {
        pidController.setSetpoint(0.0); // The goal is to align with the target (offset of 0 degrees)
    }

    @Override
    public void execute() {
        if (!vision.hasValidTarget()) {
            System.out.println("No valid target detected.");
            return;
        }

        double horizontalOffset = vision.getHorizontalOffset(); // TX value from Limelight
        double rotationSpeed = pidController.calculate(horizontalOffset);

        SwerveRequest.RobotCentric request = new SwerveRequest.RobotCentric();
        request.withRotationalRate(rotationSpeed); // Adjust robot rotation

        drivetrain.applyRequest(() -> request);
    }

    @Override
    public boolean isFinished() {
        return pidController.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.applyRequest(() -> new SwerveRequest.RobotCentric().withRotationalRate(0));
    }
}
