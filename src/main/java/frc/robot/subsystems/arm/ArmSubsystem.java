package frc.robot.subsystems.arm;

//Imports
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.MotionMagicTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

import static frc.robot.subsystems.arm.ArmSubsystemConfig.canconderconfigs;
import static frc.robot.subsystems.arm.ArmSubsystemConfig.talonFXConfigs;

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

    public double getCurrentArmPos(){
        return armKraken.getPosition().getValueAsDouble();
    }

    public boolean atSetPoint(double desiredPosition, double positionTolerance) {
        return Math.abs(armKraken.getPosition().getValueAsDouble() - desiredPosition) < positionTolerance;
    }

    public Command armToPosition(ArmPositions newPosition){
        return run(() -> armKraken.setControl(motionMagic.withPosition(newPosition.getPosition()))).until(() -> atSetPoint(newPosition.getPosition(), 0));
    }
}
