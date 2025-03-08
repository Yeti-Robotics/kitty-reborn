package frc.robot.subsystems;

//Imports
import com.ctre.phoenix6.controls.MotionMagicTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

import static frc.robot.subsystems.ArmSubsystemConfig.canconderconfigs;
import static frc.robot.subsystems.ArmSubsystemConfig.talonFXConfigs;

public class ArmSubsystem extends SubsystemBase {

    private final TalonFX armKraken;
    private final CANcoder armEncoder;
    final MotionMagicTorqueCurrentFOC motionMagic;
    public ArmSubsystem() {
        armKraken = new TalonFX(ArmSubsystemConfig.ARM_KRAKEN_ID, Constants.CANIVORE_BUS);
        armEncoder = new CANcoder(ArmSubsystemConfig.ARM_CANCODER_ID, Constants.CANIVORE_BUS);

        armKraken.getConfigurator().apply(talonFXConfigs);
        armEncoder.getConfigurator().apply(canconderconfigs);

        motionMagic = new MotionMagicTorqueCurrentFOC(0);

    }

    public double getEnc() {
        return armEncoder.getAbsolutePosition().getValue().magnitude();
    }

    public Command deployArm(ArmSubsystemConfig.ArmPositions newPosition){
        return runOnce(() -> armKraken.setControl(motionMagic.withPosition(newPosition.getPosition())));
    }

    private void stop() {
        armKraken.stopMotor();
    }
}
