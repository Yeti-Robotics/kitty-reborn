package frc.robot.constants.Intake;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.InvertedValue;

import java.lang.module.Configuration;


public class IntakeConfigs {

    public static final int INTAKE_MOTOR_ID = 8;
    public static final int VOLTAGE_OUTPUT = 12;

    public static Slot0Configs slot0Configs = new Slot0Configs()
            .withKP(0)
            .withKI(0)
            .withKD(0)
            .withKS(0);

    public static MotorOutputConfigs motorOutputConfigs = new MotorOutputConfigs()
            .withInverted(InvertedValue.Clockwise_Positive);


    public static TalonFXConfiguration intakeMotorConfig = new TalonFXConfiguration()
            .withSlot0(slot0Configs)
            .withMotorOutput(motorOutputConfigs);

}
