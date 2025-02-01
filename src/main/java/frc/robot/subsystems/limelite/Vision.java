package frc.robot.subsystems.limelite;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Vision extends SubsystemBase {
    private final String limelightName = "";
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public Vision() {
        LimelightHelpers.SetRobotOrientation(limelightName, 12, 12, 0, 0, 0, 0);
        LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(limelightName);
        LimelightHelpers.LimelightResults results =  LimelightHelpers.getLatestResults(limelightName);
        LimelightHelpers.LimelightTarget_Fiducial[] fiducials = results.targets_Fiducials;
        scheduler.scheduleAtFixedRate(this::periodic, 0, 10, TimeUnit.SECONDS);

    }

    @Override
    public void periodic() {
        LimelightHelpers.LimelightResults results =  LimelightHelpers.getLatestResults(limelightName);
        LimelightHelpers.LimelightTarget_Fiducial[] fiducials = results.targets_Fiducials;
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