package frc.robot.subsystems.limelite;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
    private final String limelightName = "";
    private LimelightHelpers.LimelightTarget_Fiducial[] fiducials;

    public Vision() {
        LimelightHelpers.SetRobotOrientation(limelightName, 12, 12, 0, 0, 0, 0);
        LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(limelightName);
        updateVisionData();
    }

    private LimelightHelpers.LimelightResults updateVisionData() {
        LimelightHelpers.LimelightResults results = (LimelightHelpers.LimelightResults) LimelightHelpers.getLatestResults(limelightName);
        LimelightHelpers.getTargetPose_RobotSpace(limelightName);
        fiducials = results.targets_Fiducials;
        SmartDashboard.putNumberArray("Target pos", LimelightHelpers.getTargetPose_RobotSpace(limelightName));
        SmartDashboard.putData("Latest Results: ", LimelightHelpers.getLatestResults(limelightName));

        return updateVisionData();
    }

    public LimelightHelpers.LimelightTarget_Fiducial getLatestFiducial() {
        if (fiducials != null && fiducials.length > 0) {
            return fiducials[0]; // Return the first fiducial target
        }
        return null;
    }

    public boolean hasValidTarget() {
        return LimelightHelpers.getTV(limelightName);
    }

    public double getHorizontalOffset() {
        return LimelightHelpers.getTX(limelightName);
    }

    @Override
    public void periodic() {
        updateVisionData();
        if (fiducials != null && fiducials.length > 0) {
            for (LimelightHelpers.LimelightTarget_Fiducial fiducial : fiducials) {
                System.out.println("Fiducial ID: " + fiducial.fiducialID);
                System.out.println("Fiducial Area: " + fiducial.ta);
                System.out.println("Camera Pose (Target Space): " + fiducial.getCameraPose_TargetSpace2D());
                System.out.println("Robot Pose (Field Space): " + fiducial.getRobotPose_FieldSpace2D());
                System.out.println("Robot Pose (Target Space): " + fiducial.getRobotPose_TargetSpace2D());
                System.out.println("Target Pose (Camera Space): " + fiducial.getTargetPose_CameraSpace2D());
                System.out.println("Target Pose (Robot Space): " + fiducial.getTargetPose_RobotSpace2D());
                System.out.println("--------------------------");
            }
        } else {
            System.out.println("No fiducial targets detected.");
        }
    }
}