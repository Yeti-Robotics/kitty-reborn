package frc.robot.subsystems;

import com.ctre.phoenix6.configs.*;

public class ArmSubsystemConfig {
    static final int ARM_KRAKEN_ID = 21;
    static final int ARM_CANCODER_ID = 5;
    static final double ARM_HANDOFF_POSITION = 0.5;
    static final double ARM_DEPLOY_UPPER_BOUND = 0.15;
    static final double ARM_DEPLOY_LOWER_BOUND = 0;
    static final double P_VALUE = 0;
    static final double I_VALUE = 0;
    static final double D_VALUE = 0;
    static final double A_VALUE = 0;
    static final double G_VALUE = 0;
    static final double S_VALUE = 0;
    static final double V_VALUE = 0;

    static final Slot0Configs slot0Configs = new Slot0Configs()
            .withKA(A_VALUE)
            .withKD(D_VALUE)
            .withKG(G_VALUE)
            .withKI(I_VALUE)
            .withKP(P_VALUE)
            .withKS(S_VALUE)
            .withKV(V_VALUE);

    static final MotionMagicConfigs motionMagicConfigs = new MotionMagicConfigs()
            .withMotionMagicJerk(0)
            .withMotionMagicAcceleration(0);

    static final CANcoderConfiguration canconderconfigs = new CANcoderConfiguration();
    static final TalonFXConfiguration talonFXConfigs = new TalonFXConfiguration().withSlot0(slot0Configs).withMotionMagic(motionMagicConfigs);
}
