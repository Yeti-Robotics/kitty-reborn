package frc.robot.subsystems.pivot;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.Constants;

import static frc.robot.subsystems.pivot.PivotConifg.pivotConfigs;
import static frc.robot.subsystems.pivot.PivotConifg.pivotRequest;

public class Pivot extends SubsystemBase {

    private final TalonFX pivotMotor;
    private final CANcoder pivotEncoder;

    public Pivot() {
        pivotMotor = new TalonFX(PivotConifg.PIVOT_MOTOR_ID, Constants.CANBus);
        pivotEncoder = new CANcoder(PivotConifg.PIVOT_CANCoder_ID, Constants.CANBus);

        pivotMotor.getConfigurator().apply(pivotConfigs);
    }

    private void set(double speed){
        pivotMotor.set(speed);
    }

    private double getPivotPosition() {
        return pivotEncoder.getPosition().getValue().magnitude();
    }

    private void stop() {
        pivotMotor.stopMotor();
    }

    public Command homePosition(double homePosition){
        pivotMotor.setControl(pivotRequest.withPosition(homePosition));
        return homePosition(homePosition);
    }

    public Command pivotToPosition() {
        return startEnd(() -> set(0.1), this::stop);
    }
}
