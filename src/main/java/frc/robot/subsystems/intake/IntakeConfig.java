package frc.robot.subsystems.intake;

import com.ctre.phoenix6.configs.MotorOutputConfigs;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;




public class IntakeConfig {

    public static final int INTAKE_MOTOR_ID = 8;
    public static final int BEAM_BREAK_ID = 2;
    public static final int ROLLER_SPEED = 5;

    public static MotorOutputConfigs motorOutputConfigs = new MotorOutputConfigs()
            .withInverted(InvertedValue.Clockwise_Positive);


    public static TalonFXConfiguration intakeMotorConfig = new TalonFXConfiguration()
            .withMotorOutput(motorOutputConfigs);

}
