package frc.robot.subsystems;

import com.ctre.phoenix6.controls.MotionMagicTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import static frc.robot.constants.Constants.CANIVORE_BUS;

public class ElevatorSubsystem extends SubsystemBase {
    private final TalonFX elevatorMotor = new TalonFX(ElevatorConfig.ELEVATOR_MOTOR_ID, CANIVORE_BUS);
    private final CANcoder elevatorEncoder = new CANcoder(ElevatorConfig.ELEVATOR_ENCODER_ID, CANIVORE_BUS);
    final MotionMagicTorqueCurrentFOC motionMagic;

    public ElevatorSubsystem() {
        elevatorMotor.getConfigurator().apply(new TalonFXConfiguration());

        elevatorMotor.getConfigurator().setPosition(0); // Reset encoder position
        motionMagic = new MotionMagicTorqueCurrentFOC(0);

    }

    public void setSpeed(double speed) {
        elevatorMotor.set(speed);
    }

    public void stop() {
        elevatorMotor.stopMotor();
    }

    public Angle getEncoderPosition() {
       return elevatorMotor.getPosition().getValue();
    }

    public AngularVelocity getEncoderVelocity() {
        return elevatorMotor.getVelocity().getValue();
    }

    public Command elevatorToPosition(double targetPositionMeters) {
        return run(() -> elevatorMotor.setControl(motionMagic.withPosition(targetPositionMeters)));
    }

}