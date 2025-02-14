package frc.robot.subsystems.pivot;

public enum PivotPositions{
    HANDOFF(-1),
    AIM(-0.82);

    private final double position;

    PivotPositions(final double position) {
        this.position = position;
    }
    public double getPosition(){
        return position;
    }
}
