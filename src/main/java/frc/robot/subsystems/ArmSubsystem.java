package frc.robot.subsystems;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.*;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;


public class ArmSubsystem extends SubsystemBase {
    //Motors
    private final TalonFX armKraken;
    private final CANcoder armEncoder;
    //Constructor
    public ArmSubsystem() {
        armKraken = new TalonFX(ArmSubsystemConfig.ARM_KRAKEN_ID, Constants.CANIVORE_NAME);
        armEncoder = new CANcoder(ArmSubsystemConfig.ARM_CANCODER_ID, Constants.CANIVORE_NAME);

        var armConfigurator = armKraken.getConfigurator();
        var talonFXConfiguration = new TalonFXConfiguration();
    }

    public Angle getEnc() {
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
        return moveDownAndStop(speed).until(() -> getEnc() <= ArmSubsystemConfig.ARM_DEPLOY_UPPER_BOUND && getEnc() >= ArmSubsystemConfig.ARM_DEPLOY_LOWER_BOUND);
    }

    private void stop() {
        armKraken.stopMotor();
    }
}
