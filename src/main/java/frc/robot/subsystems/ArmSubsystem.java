package frc.robot.subsystems;

//Imports
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

import static frc.robot.subsystems.ArmSubsystemConfig.canconderconfigs;
import static frc.robot.subsystems.ArmSubsystemConfig.talonfxconfigs;


public class ArmSubsystem extends SubsystemBase {

    private final TalonFX armKraken;
    private final CANcoder armEncoder;
    final MotionMagicVoltage motionMagic;
    public ArmSubsystem() {
        armKraken = new TalonFX(ArmSubsystemConfig.ARM_KRAKEN_ID, Constants.CANIVORE_NAME);
        armEncoder = new CANcoder(ArmSubsystemConfig.ARM_CANCODER_ID, Constants.CANIVORE_NAME);

        armKraken.getConfigurator().apply(talonfxconfigs);
        armEncoder.getConfigurator().apply(canconderconfigs);

        motionMagic = new MotionMagicVoltage(0);

    }




    public double getEnc() {
        return armEncoder.getAbsolutePosition().getValue().magnitude();
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

    public Command deployArm(double newPosition){
        return runOnce(() -> armKraken.setControl(motionMagic.withPosition(newPosition)));
    }

    private void stop() {
        armKraken.stopMotor();
    }
}
