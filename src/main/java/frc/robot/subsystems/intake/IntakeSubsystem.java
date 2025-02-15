package frc.robot.subsystems.intake;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.CANIVORE_BUS;

public class IntakeSubsystem extends SubsystemBase {

    private final TalonFX intakeMotor;
    private final DigitalInput beamBreak;

    public IntakeSubsystem(){
        intakeMotor = new TalonFX(IntakeConfigs.INTAKE_MOTOR_ID, CANIVORE_BUS);
        beamBreak = new DigitalInput(IntakeConfigs.BEAM_BREAK_PORT);
    }

    private boolean getBeamBreak(){
        return beamBreak.get();
    }

    private void stopMotor(){
        intakeMotor.stopMotor();
    }

    private void setSpeed(double speed){
        intakeMotor.set(speed);
    }

    public Command intakeCommand() {
        return startEnd(() -> setSpeed(0.5), this::stopMotor);
    }

    public Command ejectCommand(){
        return startEnd(() -> setSpeed(-0.5), this::stopMotor);
    }

   public Command intakeUntilBeamBreak(){
        return intakeCommand().until(this::getBeamBreak);

    }

    //make a get function, get the value of beam break and return it in a boolean form, commands

    //for example running the function until the beam brake function is met

}


    // create a object for the motor, really just moving the motors, beam break

    // after beam break make a command that sucks in the note until it detects that the note is in
