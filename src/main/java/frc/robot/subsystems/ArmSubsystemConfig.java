package frc.robot.subsystems;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;


public class ArmSubsystemConfig {
    public static final int ARM_KRAKEN_ID = 21;
    public static final int ARM_CANCODER_ID = 5;

    public static final InvertedValue ARM_INVERSION = InvertedValue.CounterClockwise_Positive;
    public static final NeutralModeValue ARM_NEUTRAL_MODE = NeutralModeValue.Brake;

    public static final double ARM_POSITION_STATUS_FRAME = 0.04;
    public static final double ARM_VELOCITY_STATUS_FRAME = 0.01;
    public static final double ARM_HANDOFF_POSITION = 0.5;
    public static final double ARM_DEPLOY_UPPER_BOUND = 0.15;
    public static final double ARM_DEPLOY_LOWER_BOUND = 0;
}
