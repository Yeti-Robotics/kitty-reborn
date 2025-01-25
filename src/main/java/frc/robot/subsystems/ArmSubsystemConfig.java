package frc.robot.subsystems;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.*;

public class ArmSubsystemConfig {
    static final int ARM_KRAKEN_ID = 21;
    static final int ARM_CANCODER_ID = 5;
    static final double ARM_HANDOFF_POSITION = 0.5;
    static final double ARM_DEPLOY_UPPER_BOUND = 0.15;
    static final double ARM_DEPLOY_LOWER_BOUND = 0;
    static final double P_VALUE = 0;
    static final double I_value = 0;
    static final double D_value = 0;

    static final Slot0Configs slot0Configs = new Slot0Configs()
            .withKA(0)
            .withKD(D_value)
            .withKG(0)
            .withKI(I_value)
            .withKP(P_VALUE)
            .withKS(0)
            .withKV(0);

    static final MotionMagicConfigs motionMagicConfigs = new MotionMagicConfigs()
            .withMotionMagicJerk(0)
            .withMotionMagicAcceleration(0)
            .withMotionMagicExpo_kA(0);

    static final CANcoderConfiguration canconderconfigs = new CANcoderConfiguration();
    static final TalonFXConfiguration talonFXConfigs = new TalonFXConfiguration().withSlot0(slot0Configs).withMotionMagic(motionMagicConfigs);
}
