package frc.robot.subsystems;

import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ArmSubsystemConfig {
    static final int ARM_KRAKEN_ID = 21;
    static final int ARM_CANCODER_ID = 5;

    static final InvertedValue ARM_INVERSION = InvertedValue.CounterClockwise_Positive;
    static final NeutralModeValue ARM_NEUTRAL_MODE = NeutralModeValue.Brake;

    static final double ARM_POSITION_STATUS_FRAME = 0.04;
    static final double ARM_VELOCITY_STATUS_FRAME = 0.01;
    static final double ARM_HANDOFF_POSITION = 0.5;
    static final double ARM_DEPLOY_UPPER_BOUND = 0.15;
}
