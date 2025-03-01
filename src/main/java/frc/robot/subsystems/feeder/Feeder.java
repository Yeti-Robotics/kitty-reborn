package frc.robot.subsystems.feeder;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

import static frc.robot.subsystems.feeder.FeederConfig.*;

public class Feeder extends SubsystemBase {

    private final TalonFX feederMotor;
    private final DigitalInput feederBeamBreak;

    public Feeder() {
        feederMotor = new TalonFX(FEEDER_MOTOR_ID, Constants.CANBus);
        feederBeamBreak = new DigitalInput(BEAM_BREAK_PORT_ID);
        feederMotor.getConfigurator().apply(motorOutputConfigs);
    }

    private void setFeederSpeed(double speed) {
        feederMotor.setControl(new VoltageOut(speed));
    }

    private void stop() {
        feederMotor.stopMotor();
    }


    public boolean getBeamBreak() {
        return !feederBeamBreak.get();
    }

    public Command feedNote() {
        return startEnd(() -> setFeederSpeed(3), this::stop).until(this::getBeamBreak);
    }

    public Command spinFeeder(){
        return startEnd(() -> setFeederSpeed(3), this::stop).withTimeout(0.2);
    }



}
