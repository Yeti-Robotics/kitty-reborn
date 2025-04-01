package frc.robot.subsystems;

import com.ctre.phoenix6.controls.MotionMagicTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.constants.ElevatorConstants;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import frc.robot.subsystems.arm.ArmPositions;

import static frc.robot.constants.Constants.CANIVORE_BUS;


//import static frc.robot.ElevatorConstants.CANIVORE_BUS;
//import static frc.robot.subsystems.ElevatorConfig.CANIVORE_BUS;

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
        Angle counts = elevatorMotor.getPosition().getValue();
        return counts;
    }

    public AngularVelocity getEncoderVelocity() {
        AngularVelocity countsPerSecond = elevatorMotor.getVelocity().getValue();
        return countsPerSecond;
    }

    public Command elevatorToPosition(double targetPositionMeters) {
        return run(() -> elevatorMotor.setControl(motionMagic.withPosition(targetPositionMeters)));
    }

}