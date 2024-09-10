import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

public class StickInputHandler {

    private Gamepad gamepad;

    public StickInputHandler(Gamepad gamepad) {
        this.gamepad = gamepad;
    }

    public void processStickInput(DcMotor motor) {
        // Read the left stick Y value to control the motor power
        double power = -gamepad.left_stick_y; // Invert the value to match joystick behavior

        // Set motor power based on joystick input
        motor.setPower(power);
    }
}
