package frc.robot.subsystems.arm;

public enum ArmPositions {
    HANDOFF(0.429),
    DEPLOY(0);

    private final double position;

    ArmPositions(final double position) {
        this.position = position;
    }
    public double getPosition(){
        return position;
    }
}
