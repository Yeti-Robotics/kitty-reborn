package frc.robot.subsystems.flyWheel;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class FlyWheelConfig {
    public static final int Right_Fly_Wheel_Id = 15;
    public static final int Left_Fly_Wheel_Id = 5;

    private static final Slot0Configs slot0Configs = new Slot0Configs()
            .withKV(0.2)
            .withKA(0.2)
            .withKS(0.5)
            .withKD(0);

    private static final MotionMagicConfigs motionMagicConfigs = new MotionMagicConfigs()
            .withMotionMagicExpo_kV(0.2)
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
