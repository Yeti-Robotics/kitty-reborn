package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;

public class FeederSubsystem extends SubsystemBase {

    // Creates a singleton
    private final static FeederSubsystem INSTANCE = new FeederSubsystem();

    // Motor Config
    private static final int FEEDER_MOTOR_CAN_ID = 16;
    private final TalonFX feederMotor;

    //Beam Break Sensor Config
    private static final int BEAM_BREAK_SENSOR_PORT = 2;
//    private final DigitalInput beamBreakSensor;

    public static FeederSubsystem getInstance() {
        return INSTANCE;

    }

    private FeederSubsystem() {
        //References the motor and sensor as objects - "TalonFX & DigitalInput"
        feederMotor = new TalonFX(FEEDER_MOTOR_CAN_ID);
//        beamBreakSensor = new DigitalInput(BEAM_BREAK_SENSOR_PORT);
    }

    private void setFeederSpeed(double speed) {
        feederMotor.set(speed);
    }

    private void stopFeeder() {
        setFeederSpeed(0);
    }

    private void feedBallToShooter() {
        setFeederSpeed(1.0);
    }

    // Run the feeder for 3 seconds
    public Command runFeederCommand(double speed) {
        return run(() -> setFeederSpeed(speed));
    }
}