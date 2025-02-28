package frc.robot.subsystems.pivot;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.controls.MotionMagicTorqueCurrentFOC;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.signals.*;

class PivotConifg {
    static final int PIVOT_MOTOR_ID = 29;
    static final int PIVOT_CANCoder_ID = 16;

    private static final Slot0Configs slot0Configs = new Slot0Configs()
            .withGravityType(GravityTypeValue.Arm_Cosine)
            .withKG(3.25)
            .withKS(0)
            .withKV(0)
            .withKA(3)
            .withKP(2400)
            .withKI(0)
            .withKD(48);

    private static final MotionMagicConfigs motionMagicConfigs = new MotionMagicConfigs()
            .withMotionMagicAcceleration(0.4)
            .withMotionMagicCruiseVelocity(0.2);

    private static final MotorOutputConfigs motorOutputConfigs = new MotorOutputConfigs()
            .withNeutralMode(NeutralModeValue.Brake);

    private static final CurrentLimitsConfigs currentLimitsConfigs = new CurrentLimitsConfigs()
            .withStatorCurrentLimitEnable(true)
            .withStatorCurrentLimit(50)
            .withSupplyCurrentLimitEnable(true)
            .withSupplyCurrentLimit(50);

    static final MagnetSensorConfigs CANCoderFeedbackConfigs = new MagnetSensorConfigs()
            .withMagnetOffset(0.111572)
            .withSensorDirection(SensorDirectionValue.Clockwise_Positive)
            .withAbsoluteSensorDiscontinuityPoint(0);

    static final FeedbackConfigs motorFeedbackConfigs = new FeedbackConfigs()
            .withFeedbackRemoteSensorID(PIVOT_CANCoder_ID)
            .withFeedbackSensorSource(FeedbackSensorSourceValue.RemoteCANcoder)
            .withRotorToSensorRatio(67.1)
            .withSensorToMechanismRatio(1);

    static final TalonFXConfiguration motorPivotConfigs = new TalonFXConfiguration()
            .withSlot0(slot0Configs)
            .withMotionMagic(motionMagicConfigs)
            .withMotorOutput(motorOutputConfigs)
            .withCurrentLimits(currentLimitsConfigs)
            .withFeedback(motorFeedbackConfigs);

    static final CANcoderConfiguration CANCoderPivotConfigs = new CANcoderConfiguration()
            .withMagnetSensor(CANCoderFeedbackConfigs);

    static final MotionMagicTorqueCurrentFOC pivotRequest = new MotionMagicTorqueCurrentFOC(0);

}
