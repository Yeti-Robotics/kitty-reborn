package frc.robot.subsystems.flywheel;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class FlywheelConfig {
    public static final int RIGHT_FLY_WHEEL_ID = 15;
    public static final int LEFT_FLY_WHEEL_ID = 5;

    private static final Slot0Configs slot0Configs = new Slot0Configs()
            .withKV(90)
            .withKA(0.9)
            .withKS(10)
            .withKD(0)
            .withKP(5);

    private static final MotionMagicConfigs motionMagicConfigs = new MotionMagicConfigs()
            .withMotionMagicExpo_kV(0.4)
            .withMotionMagicExpo_kA(0.2)
            .withMotionMagicAcceleration(50)
            .withMotionMagicJerk(50);

    private static final MotorOutputConfigs motorOutputConfigs = new MotorOutputConfigs()
            .withNeutralMode(NeutralModeValue.Brake)
            .withInverted(InvertedValue.CounterClockwise_Positive);

    private static final CurrentLimitsConfigs currentLimitsConfigs = new CurrentLimitsConfigs()
            .withStatorCurrentLimitEnable(true)
            .withStatorCurrentLimit(50)
            .withSupplyCurrentLimitEnable(true)
            .withSupplyCurrentLimit(50);

    static final TalonFXConfiguration flyWheelConfigs = new TalonFXConfiguration()
            .withSlot0(slot0Configs)
            .withMotionMagic(motionMagicConfigs)
            .withMotorOutput(motorOutputConfigs)
            .withCurrentLimits(currentLimitsConfigs);

    static final MotionMagicVelocityVoltage flyWheelRequest = new MotionMagicVelocityVoltage(0);

}
