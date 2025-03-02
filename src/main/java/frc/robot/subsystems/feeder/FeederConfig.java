package frc.robot.subsystems.feeder;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.signals.InvertedValue;

public class FeederConfig {

    static final int FEEDER_MOTOR_ID = 16;

    // 0 set as placeholder
    static final int BEAM_BREAK_PORT_ID = 5;

    static MotorOutputConfigs motorOutputConfigs = new MotorOutputConfigs()
        .withInverted(InvertedValue.Clockwise_Positive);


}
