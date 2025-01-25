package frc.robot.subsystems.flyWheel;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import frc.robot.constants.Constants;

import static frc.robot.subsystems.flyWheel.FlyWheelConfig.flyWheelConfigs;
import static frc.robot.subsystems.flyWheel.FlyWheelConfig.flyWheelRequest;

public class Flywheel extends SubsystemBase {

    private final TalonFX rightFlyWheelMotor;
    private final TalonFX leftFlyWheelMotor;

    public Flywheel() {
        rightFlyWheelMotor = new TalonFX(FlyWheelConfig.Right_Fly_Wheel_Id, Constants.CANBus);
        leftFlyWheelMotor = new TalonFX(FlyWheelConfig.Left_Fly_Wheel_Id, Constants.CANBus);

        // set left motor to follow right motor
        rightFlyWheelMotor.setControl(new Follower(FlyWheelConfig.Left_Fly_Wheel_Id, true));

        rightFlyWheelMotor.getConfigurator().apply(flyWheelConfigs);
        leftFlyWheelMotor.getConfigurator().apply(flyWheelConfigs);
    }

    private void setShooterSpeed(double speed) {
        rightFlyWheelMotor.set(speed);
        leftFlyWheelMotor.set(speed);
    }

    private void stop() {
        rightFlyWheelMotor.stopMotor();
        leftFlyWheelMotor.stopMotor();
    }


    public Command spinShooter() {
        return startEnd(() -> setShooterSpeed(0.4), this::stop);
    }

}
