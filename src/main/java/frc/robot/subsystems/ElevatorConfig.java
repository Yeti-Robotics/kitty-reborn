package frc.robot.subsystems;

import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.signals.*;

public class ElevatorConfig {
    //    fr elevevator
    /*public static final double kElevatorKp = 0.1;
    public static final double kElevatorKi = 0.0;
    public static final double kElevatorKd = 0.0;
    public static final double kElevatorMaxVelocity = 0.0;
    public static final double kElevatorMaxAcceleration = 0.0;

    public static final double kElevatorGearRatio = 0;
    public static final double kElevatorSpoolRadius = 0.;
    public static final double kElevatorEncoderResolution = 0;
    public static final double kElevatorMaxHeight = 2.0;
    public static final double kElevatorMinHeight = 2.0;
*/
    static final double P_VALUE = 0;
    static final double I_VALUE = 0;
    static final double D_VALUE = 0;
    static final double A_VALUE = 0;
    static final double G_VALUE = 0;
    static final double S_VALUE = 0;
    static final double V_VALUE = 0;
    public static final int ELEVATOR_MOTOR_ID = 10;
    public static final int ELEVATOR_ENCODER_ID = 49;

    static final Slot0Configs slot0Configs = new Slot0Configs()
            .withGravityType(GravityTypeValue.Arm_Cosine)
            .withKA(A_VALUE)
            .withKD(D_VALUE)
            .withKG(G_VALUE)
            .withKI(I_VALUE)
            .withKP(P_VALUE)
            .withKS(S_VALUE)
            .withKV(V_VALUE);

    static final MotorOutputConfigs motorOutputConfigs = new MotorOutputConfigs()
            .withNeutralMode(NeutralModeValue.Brake)
            .withInverted(InvertedValue.CounterClockwise_Positive);

    static final MotionMagicConfigs motionMagicConfigs = new MotionMagicConfigs()
            .withMotionMagicCruiseVelocity(2.75)
            .withMotionMagicJerk(0)
            .withMotionMagicAcceleration(2.75);


    static final CurrentLimitsConfigs currentLimitsConfigs = new CurrentLimitsConfigs()
            .withStatorCurrentLimitEnable(true)
            .withStatorCurrentLimit(50)
            .withSupplyCurrentLimitEnable(true)
            .withSupplyCurrentLimit(50);


    static final CANcoderConfiguration canconderconfigs = new CANcoderConfiguration()
            .withMagnetSensor(
                    new MagnetSensorConfigs()
                            .withMagnetOffset(0.442138671875)
                            .withSensorDirection(SensorDirectionValue.CounterClockwise_Positive)
                            .withAbsoluteSensorDiscontinuityPoint(.5)
            );

    static final TalonFXConfiguration talonFXConfigs = new TalonFXConfiguration()
            .withSlot0(slot0Configs)
            .withMotionMagic(motionMagicConfigs)
            .withMotorOutput(motorOutputConfigs)
            .withCurrentLimits(currentLimitsConfigs)
            .withFeedback(
                    new FeedbackConfigs()
                            .withFeedbackRemoteSensorID(ELEVATOR_ENCODER_ID)
                            .withFeedbackSensorSource(FeedbackSensorSourceValue.FusedCANcoder)
                            .withRotorToSensorRatio(60)
                            .withSensorToMechanismRatio(1)
            );
}
