package frc.robot.subsystems.pivot;

public enum PivotPositions{
    HANDOFF(0.045),
    AIM(0.11),
    HOME(0.003906);

    private final double position;

    PivotPositions(final double position) {

        this.position = position;
    }

    public double getPosition(){
        return position;
    }
}
