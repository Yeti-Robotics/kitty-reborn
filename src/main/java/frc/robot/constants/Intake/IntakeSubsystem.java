package frc.robot.constants.Intake;


import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.constants.Constants.CANIVORE_BUS;
import static frc.robot.constants.Intake.IntakeConfigs.*;


public class IntakeSubsystem extends SubsystemBase {
    private final TalonFX intakeMotor = new TalonFX(INTAKE_MOTOR_ID, CANIVORE_BUS);




    public IntakeSubsystem(){
        intakeMotor.getConfigurator().apply(intakeMotorConfig);

    }

    public void stopIntake(){
        intakeMotor.stopMotor();
    }

    public void runIntakeOut(double speed){
        intakeMotor.setControl(new VoltageOut(speed));
    }
    public Command spinIntake(){
        return startEnd(()-> runIntakeOut(5), this::stopIntake).withTimeout(.5);
    }
    public Command spinIntakeOut(){
        return  startEnd(()-> runIntakeOut(-5), this::stopIntake).withTimeout(1);
    }

}

