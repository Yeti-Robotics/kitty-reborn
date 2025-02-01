package frc.robot.util.controllerUtils;


import edu.wpi.first.wpilibj2.command.Command;

import java.util.HashMap;
import java.util.function.BooleanSupplier;

public class ButtonHelper {
    private static final int maxAxis = 32;
    private static final int maxPOV = 16;
    private final Controller[] controllers;

    private final HashMap<Controller, HashMap<Byte, MultiButton>> buttonMaps = new HashMap<>();
    private Controller controller;

    public ButtonHelper(Controller[] controllers) {
        this.controllers = controllers;
        for (Controller c : controllers) {
            buttonMaps.put(c, new HashMap<>());
        }

        controller = controllers[0];
    }

    public static String buttonIDToString(byte buttonID) {
        String type;
        int port = (buttonID & 0xFF);
        byte binaryType = (byte) ((buttonID & 0xFF) >> 6);

        switch (binaryType) {
            case 0b00000000:
                type = "Button";
                port = buttonID;
                break;
            case 0b00000001:
                type = "Axis";
                port = port ^ 0b01000000;
                if (port >= maxAxis) {
                    port -= maxAxis;
                }
                break;
            case 0b00000010:
                type = "POV";
                port = port ^ 0b10000000;
                if (port >= maxPOV) {
                    port %= maxPOV;
                }
                break;
            default:
                type = "UNKNOWN";
                break;
        }

        return String.format("Button Type: %s || Port: %d\n", type, port);
    }

    private MultiButton createButton(
            BooleanSupplier supplier,
            byte buttonID,
            int layer,
            Command command,
            MultiButton.RunCondition runCondition) {

        if (buttonID == (byte) 0b11111111) {
            System.out.println("FAILED to create button!!!");
            return null;
        }

        if (buttonMaps.get(controller).containsKey(buttonID)) {
            buttonMaps.get(controller).get(buttonID).addLayer(layer, command, runCondition);
            return buttonMaps.get(controller).get(buttonID);
        }

        MultiButton multiButton = new MultiButton(
                supplier,
                buttonID,
                layer,
                command,
                runCondition);

        buttonMaps.get(controller).put(buttonID, multiButton);
        return multiButton;
    }

    private MultiButton createButton(
            BooleanSupplier supplier,
            byte buttonID) {

        if (buttonID == (byte) 0b11111111) {
            System.out.println("FAILED to create button!!!");
            return null;
        }

        if (buttonMaps.get(controller).containsKey(buttonID)) {
            return buttonMaps.get(controller).get(buttonID);
        }

        MultiButton multiButton = new MultiButton(
                supplier,
                buttonID);

        buttonMaps.get(controller).put(buttonID, multiButton);
        return multiButton;
    }


    public MultiButton createButton(
            int buttonPort,
            int layer,
            Command command,
            MultiButton.RunCondition runCondition) {


        return createButton(
                () -> controller.getRawButton(buttonPort),
                getButtonID(buttonPort),
                layer,
                command,
                runCondition);
    }


    public MultiButton createButton(int buttonPort) {

        return createButton(
                () -> controller.getRawButton(buttonPort),
                getButtonID(buttonPort));
    }


    public MultiButton createAxisButton(
            int axisPort,
            int layer,
            Command command,
            MultiButton.RunCondition runCondition,
            double threshold) {
        BooleanSupplier axisSupplier = () -> {
            if (threshold < 0.0) {
                return controller.getRawAxis(axisPort) < threshold;
            }
            return controller.getRawAxis(axisPort) > threshold;
        };

        return createButton(
                axisSupplier,
                getButtonID(axisPort, threshold < 0.0),
                layer,
                command,
                runCondition);
    }


    public MultiButton createAxisButton(
            int axisPort,
            double threshold) {
        BooleanSupplier axisSupplier = () -> {
            if (threshold < 0.0) {
                return controller.getRawAxis(axisPort) < threshold;
            }
            return controller.getRawAxis(axisPort) > threshold;
        };

        return createButton(
                axisSupplier,
                getButtonID(axisPort, threshold < 0.0));
    }


    public MultiButton createPOVButton(
            int povPort,
            POVDirections direction,
            int layer,
            Command command,
            MultiButton.RunCondition runCondition) {
        BooleanSupplier povSupplier = () -> controller.getPOV(povPort) == direction.value;

        return createButton(
                povSupplier,
                getButtonID(povPort, direction),
                layer,
                command,
                runCondition);
    }

    public MultiButton createPOVButton(
            int povPort,
            POVDirections direction) {
        BooleanSupplier povSupplier = () -> controller.getPOV(povPort) == direction.value;

        return createButton(
                povSupplier,
                getButtonID(povPort, direction));
    }

    public void setController(int controllerNumber) {
        controller = controllers[controllerNumber];
    }

    public void setAllLayers(int layer) {
        MultiButton.syncLayers(layer);
    }

    public int getAllLayers() {
        return MultiButton.getSyncLayer();
    }

    public void setButtonLayer(int controllerNumber, byte buttonID, int layer) {
        buttonMaps.get(controllers[controllerNumber]).get(buttonID).setButtonLayer(layer);
    }

    public int getButtonLayer(int controllerNumber, byte buttonID) {
        return buttonMaps.get(controllers[controllerNumber]).get(buttonID).getButtonLayer();
    }

    public HashMap<Byte, MultiButton> getButtonMap(int controllerNumber) {
        return buttonMaps.get(controllers[controllerNumber]);
    }

    public MultiButton getButton(int controllerNumber, byte buttonID) {
        return buttonMaps.get(controllers[controllerNumber]).get(buttonID);
    }


    private byte getButtonID(ButtonType type, Integer port) {
        if (port > 63) {
            return (byte) 0b11111111;
        }
        switch (type) {
            case BUTTON:
                return port.byteValue();
            case AXIS:
                return (byte) (0b01000000 | port.byteValue());
            case POV:
                return (byte) (0b10000000 | port.byteValue());
        }

        return (byte) 0b11111111;
    }


    public byte getButtonID(Integer port) {
        return getButtonID(ButtonType.BUTTON, port);
    }


    public byte getButtonID(Integer port, boolean isNegative) {
        int imaginaryAxisPort = isNegative ? port + maxAxis : port;

        return getButtonID(ButtonType.AXIS, imaginaryAxisPort);
    }


    public byte getButtonID(Integer port, POVDirections direction) {
        /*
         * Getting a unique port number based on the direction
         */
        int imaginaryPOVPort = (Math.max((direction.value / 90 * maxPOV - 1), 0)) + port;

        return getButtonID(ButtonType.POV, imaginaryPOVPort);
    }

    public enum ButtonType {
        BUTTON,
        AXIS,
        POV
    }
}