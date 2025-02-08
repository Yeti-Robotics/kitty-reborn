package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import frc.robot.subsystems.limelite.Vision;

public class AlignWithAprilTagCommand extends SubsystemBase {
    private final MotorController motor;
    private final PIDController pidController;
    private final Vision vision;

    public AlignWithAprilTagCommand(MotorController motor, Vision vision) {
        this.motor = motor;
        this.vision = vision;
        pidController = new PIDController(0.1, 0.0, 0.0); // Example PID values, you may need to tune them
        pidController.setTolerance(1.0); // Tolerance in degrees
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if (vision.hasValidTarget()) {
            double targetOffset = vision.getHorizontalOffset();
            double output = pidController.calculate(targetOffset, 0);
            motor.set(output);
        } else {
            motor.set(0); // Stop the motor if no valid target is found
        }
    }

    public boolean isAtTargetAngle() {
        return pidController.atSetpoint();
    }
}