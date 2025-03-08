package frc.robot.subsystems.Intake;


import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.constants.Constants.CANIVORE_BUS;
import static frc.robot.subsystems.Intake.IntakeConfigs.*;


public class IntakeSubsystem extends SubsystemBase {
    private final TalonFX intakeMotor = new TalonFX(INTAKE_MOTOR_ID, CANIVORE_BUS);
    private final DigitalInput beamBreak = new DigitalInput(BEAMBREAK_ID);

    public IntakeSubsystem(){
        intakeMotor.getConfigurator().apply(intakeMotorConfig);
    }

    public void stopIntake(){
        intakeMotor.stopMotor();
    }

    public boolean getBeamBreak(){
        return !beamBreak.get();
    }

    public void runIntake(double speed){
        intakeMotor.setControl(new VoltageOut(speed));
    }

    public Command in(){
        return startEnd(()-> runIntake(ROLLER_SPEED), this::stopIntake).until(this::getBeamBreak);
    }

    public Command out(){
        return startEnd(()-> runIntake(-ROLLER_SPEED), this::stopIntake).withTimeout(3);
    }

    public Command spinIntake(boolean reversed){
        if (reversed == true){
            return in();
        }
        else{
            return out();
        }
    }
}

