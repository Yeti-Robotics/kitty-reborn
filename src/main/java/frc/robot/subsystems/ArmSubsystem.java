package frc.robot.subsystems;

//Imports
import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

import static frc.robot.subsystems.ArmSubsystemConfig.canconderconfigs;
import static frc.robot.subsystems.ArmSubsystemConfig.talonfxconfigs;


public class ArmSubsystem extends SubsystemBase {

    private final TalonFX armKraken;
    private final CANcoder armEncoder;

    //Constructor
    public ArmSubsystem() {
        armKraken = new TalonFX(ArmSubsystemConfig.ARM_KRAKEN_ID, Constants.CANIVORE_NAME);
        armEncoder = new CANcoder(ArmSubsystemConfig.ARM_CANCODER_ID, Constants.CANIVORE_NAME);

        armKraken.getConfigurator().apply(talonfxconfigs);
        armEncoder.getConfigurator().apply(canconderconfigs);
    }

    public double getEnc() {
        return armEncoder.getAbsolutePosition().getValue().magnitude();
    }

    //Move Arm Up
    private void moveUp(double speed) {
        armKraken.set(Math.abs(speed));
    }

    //Move Arm Down
    private void moveDown(double speed) {
        armKraken.set(-Math.abs(speed));
    }

    //Move Arm Up and Stop
    public Command moveUpAndStop(double speed) {
        return startEnd(() -> moveUp(speed), this::stop);
    }

    //Move Arm Down and Stop
    public Command moveDownAndStop(double speed){
        return startEnd(() -> moveDown(speed), this::stop);
    }

    //Deploy Position
    public Command deployArm(double speed){
        return moveDownAndStop(speed).until(() -> getEnc() <= ArmSubsystemConfig.ARM_DEPLOY_UPPER_BOUND && getEnc() >= ArmSubsystemConfig.ARM_DEPLOY_LOWER_BOUND);
    }

    private void stop() {
        armKraken.stopMotor();
    }
}
