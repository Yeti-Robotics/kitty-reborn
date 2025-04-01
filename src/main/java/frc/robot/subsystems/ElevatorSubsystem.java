package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.constants.ElevatorConstants;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import static frc.robot.constants.Constants.CANIVORE_BUS;


//import static frc.robot.ElevatorConstants.CANIVORE_BUS;
//import static frc.robot.subsystems.ElevatorConfig.CANIVORE_BUS;

public class ElevatorSubsystem extends SubsystemBase {
    private final TalonFX elevatorMotor = new TalonFX(ElevatorConfig.ELEVATOR_MOTOR_ID, CANIVORE_BUS);
    private final CANcoder elevatorEncoder = new CANcoder(ElevatorConfig.ELEVATOR_ENCODER_ID, CANIVORE_BUS);

    private final ProfiledPIDController m_controller = new ProfiledPIDController(
            ElevatorConfig.kElevatorKp,
            ElevatorConfig.kElevatorKi,
            ElevatorConfig.kElevatorKd,
            new TrapezoidProfile.Constraints(
                    ElevatorConfig.kElevatorMaxVelocity,
                    ElevatorConfig.kElevatorMaxAcceleration
            )
    );

    public ElevatorSubsystem() {
        elevatorMotor.getConfigurator().apply(new TalonFXConfiguration());

        elevatorMotor.getConfigurator().setPosition(0); // Reset encoder position
    }

    public void setSpeed(double speed) {
        elevatorMotor.set(speed);
    }

    public void stop() {
        elevatorMotor.stopMotor();
    }

    public double getEncoderPosition() {
        double counts = elevatorMotor.getPosition().getValue();
        return countsToMeters(counts);
    }

    public double getEncoderVelocity() {
        double countsPerSecond = elevatorMotor.getVelocity().getValue();
        return countsToMeters(countsPerSecond);
    }

    public Command elevatorToPosition(double targetPositionMeters) {
        return runOnce(() -> {
            m_controller.setGoal(targetPositionMeters);
        }).andThen(run(() -> {
            double output = m_controller.calculate(getEncoderPosition());
            elevatorMotor.set(output);
        })).finallyDo((interrupted) -> stop());
    }
}