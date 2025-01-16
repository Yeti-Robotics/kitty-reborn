package frc.robot.subsystems;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;


public class ArmSubsystem extends SubsystemBase {

    private final TalonFX armKraken;
    private final CANcoder armEncoder;

    public ArmSubsystem() {
        armKraken = new TalonFX(ArmConstants.ARM_KRAKEN_ID, Constants.TalonFXConstants.CANIVORE_NAME);
        armEncoder = new CANcoder(ArmConstants.ARM_CANCODER_ID, Constants.TalonFXConstants.CANIVORE_NAME);
    }

    public static class ArmConstants {
        public static final int ARM_KRAKEN_ID = 21;
        public static final int ARM_CANCODER_ID = 5;

    }

    public double getEnc() {
        return armEncoder.getAbsolutePosition().getValue();
    }

    private void moveUp(double speed) {
        armKraken.set(Math.abs(speed));
    }

    private void moveDown(double speed) {
        armKraken.set(-Math.abs(speed));
    }

    public Command moveUpAndStop(double speed) {
        return startEnd(() -> moveUp(speed), this::stop);
    }

    public Command moveDownAndStop(double speed){
        return startEnd(() -> moveDown(speed), this::stop);
    }

    public Command deployArm(double speed){
        return moveDownAndStop(speed).until(()
                -> getEnc() <= ArmConstants.ARM_DEPLOY_UPPER_BOUND && getEnc() >= ArmConstants.ARM_DEPLOY_LOWER_BOUND);
    }

    private void stop() {
        armKraken.stopMotor();
    }
}
