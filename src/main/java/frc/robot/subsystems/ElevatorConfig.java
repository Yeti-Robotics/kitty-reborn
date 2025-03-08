package frc.robot.subsystems;

public class ElevatorConfig {
    //    fr elevevator
    public static final double kElevatorKp = 0.1;
    public static final double kElevatorKi = 0.0;
    public static final double kElevatorKd = 0.0;
    public static final double kElevatorMaxVelocity = 0.0;
    public static final double kElevatorMaxAcceleration = 0.0;

    public static final double kElevatorGearRatio = 10.0;
    public static final double kElevatorSpoolRadius = 0.05;
    public static final double kElevatorEncoderResolution = 10;
    public static final double kElevatorMaxHeight = 2.0;
    public static final double kElevatorMinHeight = 2.0;

    public static final int ELEVATOR_MOTOR_ID = 10;
    public static final int ELEVATOR_ENCODER_ID = 49;
}
