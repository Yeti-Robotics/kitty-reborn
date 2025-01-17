package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;

public class vision {
    private static NetworkTableInstance table = null;


     //Light modes for Limelight.
    public static enum LightMode {
        eOn, eOff, eBlink
    }

    //Camera modes for Limelight.
    public static enum CameraMode {
        eVision, eDriver
    }

    //is there a target in the area
    public static boolean isTarget() {
        return getValue("tv").getDouble(0) == 1;
    }

    //Horizontal offset from crosshair to target
    public static double getTx() {
        return getValue("tx").getDouble(0.00);
    }

    //Vertical offset from crosshair to target
    public static double getTy() {
        return getValue("ty").getDouble(0.00);
    }

    //Area that the detected target takes up in total camera FOV
    public static double getTa() {
        return getValue("ta").getDouble(0.00);
    }

    //Gets target skew or rotation
    public static double getTs() {
        return getValue("ts").getDouble(0.00);
    }

    //Gets target latency (ms)
    public static double getTl() {
        return getValue("tl").getDouble(0.00);
    }

    //Sets LED mode of Limelight.
    public static void setLedMode(LightMode mode) {
        getValue("ledMode").setNumber(mode.ordinal());
    }

    //Sets camera mode for Limelight.
    public static void setCameraMode(CameraMode mode) {
        getValue("camMode").setNumber(mode.ordinal());
    }

    //Sets pipeline number
    public static void setPipeline(int number) {
        getValue("pipeline").setNumber(number);
    }

    public static void main(String[] args) {
        // Set the LED mode to blink
        setLedMode(LightMode.eBlink);
        System.out.println("Limelight LEDs should now be blinking.");
    }

    //Helper method to get an entry from the Limelight NetworkTable.
    private static NetworkTableEntry getValue(String key) {
        if (table == null) {
            table = NetworkTableInstance.getDefault();
        }

        return table.getTable("limelight").getEntry(key);
    }

    public static void updateLedMode(boolean isTargetDetected, boolean isInAutoMode) {
        if (isInAutoMode) {
            setLedMode(LightMode.eOn);
        } else if (isTargetDetected) {
            setLedMode(LightMode.eBlink);
        } else {
            setLedMode(LightMode.eOff);
        }
    }
}