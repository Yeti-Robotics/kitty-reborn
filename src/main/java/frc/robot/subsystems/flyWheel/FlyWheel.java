package frc.robot.subsystems.flyWheel;

import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

import static frc.robot.subsystems.flyWheel.FlyWheelConfig.flyWheelConfigs;

public class FlyWheel extends SubsystemBase {

    private final TalonFX rightFlyWheelMotor;
    private final TalonFX leftFlyWheelMotor;

    public FlyWheel() {
        rightFlyWheelMotor = new TalonFX(FlyWheelConfig.RIGHT_FLY_WHEEL_ID, Constants.CANIVORE_BUS);
        leftFlyWheelMotor = new TalonFX(FlyWheelConfig.LEFT_FLY_WHEEL_ID, Constants.CANIVORE_BUS);

        rightFlyWheelMotor.setControl(new Follower(FlyWheelConfig.LEFT_FLY_WHEEL_ID, false));

        rightFlyWheelMotor.getConfigurator().apply(flyWheelConfigs);
        leftFlyWheelMotor.getConfigurator().apply(flyWheelConfigs);
    }

    private void setShooterSpeed(double speed) {
        leftFlyWheelMotor.setControl(new VoltageOut(speed));
    }

    private void stop() {
        leftFlyWheelMotor.stopMotor();
    }

    public Command spinShooter() {
        return startEnd(() -> setShooterSpeed(7), this::stop);
    }

}
