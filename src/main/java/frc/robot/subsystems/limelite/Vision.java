package frc.robot.subsystems.limelite;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.limelite.LimelightHelpers;
import frc.robot.subsystems.limelite.LimelightHelpers.LimelightResults;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class Vision {
    public static void main(String[] args) {
        // Fetch the latest results from the Limelight
        LimelightResults results = LimelightHelpers.getLatestResults("limelight");

        // Check the number of detected April Tags
        int numAprilTags = results.targets_Fiducials.length;
        System.out.println("Number of April Tags detected: " + numAprilTags);

        if (numAprilTags > 0) {
            // Assuming we are interested in the first detected April Tag
            LimelightHelpers.LimelightTarget_Fiducial target = results.targets_Fiducials[0];

            // Extract and print the pose and other relevant data
            System.out.println("April Tag ID: " + target.fiducialID);
            System.out.println("Pose (Field Space): " + target.getRobotPose_FieldSpace2D());
            System.out.println("Target Area: " + target.ta);
            System.out.println("Horizontal Offset: " + target.tx);
            System.out.println("Vertical Offset: " + target.ty);
        } else {
            System.out.println("No April Tags detected.");
        }
    }
}
//