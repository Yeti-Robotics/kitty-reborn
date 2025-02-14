package frc.robot.subsystems.intake;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

    private TalonFX intakeMotor;
    public IntakeSubsystem(){
        intakeMotor = new TalonFX(IntakeConfigs.INTAKE_MOTOR_ID);
    }

    private void stopMotor(){
        intakeMotor.stopMotor();
    }

    private void setSpeed(double speed){
        intakeMotor.set(speed);
    }

    public Command intakeCommand(){
        return startEnd(() -> setSpeed(0.5), this::stopMotor);
    }

    public Command ejectCommand(){
        return startEnd(() -> setSpeed(-0.5), this::stopMotor);
    }



}


// create a object for the motor, really just moving the motors, beam break