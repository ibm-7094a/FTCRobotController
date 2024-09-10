import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "Main OpMode", group = "TeleOp")
public class MainOpMode extends LinearOpMode {

    private DcMotor motor;
    private StickInputHandler stickInputHandler;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize hardware and secondary script
        motor = hardwareMap.get(DcMotor.class, "motor");
        stickInputHandler = new StickInputHandler(gamepad1);

        // Wait for the game to start
        waitForStart();

        while (opModeIsActive()) {
            // Use the secondary script to handle stick input
            stickInputHandler.processStickInput(motor);
            
            // Other robot control logic can be added here
            
            telemetry.update();
        }
    }
}
