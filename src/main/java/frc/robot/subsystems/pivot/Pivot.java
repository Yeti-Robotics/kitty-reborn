package frc.robot.subsystems.pivot;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.Constants;


import static frc.robot.subsystems.pivot.PivotConfig.*;

public class Pivot extends SubsystemBase {

    private final TalonFX pivotMotor;
    private final CANcoder pivotEncoder;

    public Pivot() {
        pivotMotor = new TalonFX(PivotConfig.PIVOT_MOTOR_ID, Constants.CANBus);
        pivotEncoder = new CANcoder(PivotConfig.PIVOT_CANCoder_ID, Constants.CANBus);

        pivotMotor.getConfigurator().apply(motorPivotConfigs);
        pivotEncoder.getConfigurator().apply(CANCoderPivotConfigs);
    }

    private void set(double speed){
        pivotMotor.set(speed);
    }

    private void stop() {
        pivotMotor.stopMotor();
    }

    public Command pivotToPosition(PivotPositions position){
        return runOnce(() -> pivotMotor.setControl(pivotRequest.withPosition(position.getPosition())));
    }
}
