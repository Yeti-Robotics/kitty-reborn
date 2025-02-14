package frc.robot.constants.Intake;

import com.ctre.phoenix6.controls.MotionMagicVelocityTorqueCurrentFOC;
import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.constants.Constants.CANIVORE_BUS;
import static frc.robot.constants.Intake.IntakeConfigs.*;


public class IntakeSubsystem extends SubsystemBase {
    private final TalonFX intakeMotor = new TalonFX(INTAKE_MOTOR_ID, CANIVORE_BUS);
    public VoltageOut voltageOut = new VoltageOut(0);



    public IntakeSubsystem(){
        intakeMotor.getConfigurator().apply(intakeMotorConfig);
    }

    public void stopIntake(){
        intakeMotor.stopMotor();
    }

    public void runIntakeOut(){
        intakeMotor.setControl(voltageOut.withOutput(VOLTAGE_OUTPUT));
    }
    public void runIntakeIn(){
        intakeMotor.setControl(voltageOut.withOutput(-VOLTAGE_OUTPUT));
    }

}

