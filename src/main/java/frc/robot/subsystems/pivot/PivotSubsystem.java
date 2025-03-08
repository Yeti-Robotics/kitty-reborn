package frc.robot.subsystems.pivot;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.Constants;


import static frc.robot.subsystems.pivot.PivotConfig.*;

public class PivotSubsystem extends SubsystemBase {

    private final TalonFX pivotMotor;
    private final CANcoder pivotEncoder;

    public PivotSubsystem() {
        pivotMotor = new TalonFX(PivotConfig.PIVOT_MOTOR_ID, Constants.CANIVORE_BUS);
        pivotEncoder = new CANcoder(PivotConfig.PIVOT_CANCoder_ID, Constants.CANIVORE_BUS);

        pivotMotor.getConfigurator().apply(motorPivotConfigs);
        pivotEncoder.getConfigurator().apply(CANCoderPivotConfigs);
    }

    public Command pivotToPosition(PivotPositions position){
        return runOnce(() -> pivotMotor.setControl(pivotRequest.withPosition(position.getPosition())));
    }

    private void stop() {
        pivotMotor.stopMotor();
    }
}
