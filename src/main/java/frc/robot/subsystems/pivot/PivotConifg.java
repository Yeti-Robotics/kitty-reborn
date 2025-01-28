package frc.robot.subsystems.pivot;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.signals.GravityTypeValue;

class PivotConifg {
    static final int PIVOT_MOTOR_ID = 29;
    static final int PIVOT_CANCoder_ID = 16;

    private static final Slot0Configs slot0Configs = new Slot0Configs()
            .withGravityType(GravityTypeValue.Arm_Cosine)
            .withKG(0)
            .withKS(0)
            .withKV(0)
            .withKA(0)
            .withKP(0)
            .withKI(0.)
            .withKD(0);

    private static final MotionMagicConfigs motionMagicConfigs = new MotionMagicConfigs()
            .withMotionMagicExpo_kV(0)
            .withMotionMagicExpo_kA(0)
            .withMotionMagicAcceleration(0)
            .withMotionMagicCruiseVelocity(0)
            .withMotionMagicJerk(0);

    private static final CurrentLimitsConfigs currentLimitsConfigs = new CurrentLimitsConfigs()
            .withStatorCurrentLimitEnable(true)
            .withStatorCurrentLimit(0)
            .withSupplyCurrentLimitEnable(true)
            .withSupplyCurrentLimit(0);

    static final TalonFXConfiguration pivotConfigs = new TalonFXConfiguration()
            .withSlot0(slot0Configs)
            .withMotionMagic(motionMagicConfigs)
            .withCurrentLimits(currentLimitsConfigs);

    static final MotionMagicVoltage pivotRequest = new MotionMagicVoltage(0);

}
