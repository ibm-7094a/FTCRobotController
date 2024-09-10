import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;

@Autonomous(name = "Drive Forward", group = "Autonomous")
public class DriveForwardOpMode extends LinearOpMode {

    // Define your motor variables
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor rearLeftMotor;
    private DcMotor rearRightMotor;

    // Variables for wheel circumference and distance
    private final double whlcirc = 4.0; // Example wheel circumference in inches
    private final double dist = 5.0; // Example distance in feet

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize motors
        frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left_motor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "front_right_motor");
        rearLeftMotor = hardwareMap.get(DcMotor.class, "rear_left_motor");
        rearRightMotor = hardwareMap.get(DcMotor.class, "rear_right_motor");

        // Set motor directions
        frontLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        frontRightMotor.setDirection(DcMotor.Direction.REVERSE);
        rearLeftMotor.setDirection(DcMotor.Direction.FORWARD);
        rearRightMotor.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to run using encoders
        frontLeftMotor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        frontRightMotor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        rearLeftMotor.setMode(RunMode.STOP_AND_RESET_ENCODER);
        rearRightMotor.setMode(RunMode.STOP_AND_RESET_ENCODER);

        frontLeftMotor.setMode(RunMode.RUN_TO_POSITION);
        frontRightMotor.setMode(RunMode.RUN_TO_POSITION);
        rearLeftMotor.setMode(RunMode.RUN_TO_POSITION);
        rearRightMotor.setMode(RunMode.RUN_TO_POSITION);

        // Calculate the number of ticks to travel the specified distance
        double distanceInInches = dist * 12; // Convert feet to inches
        double ticksPerRevolution = 1440; // Motor ticks per revolution
        double ticksRequired = (distanceInInches / whlcirc) * ticksPerRevolution;

        // Set target positions for all motors to ensure equal distance
        frontLeftMotor.setTargetPosition((int) ticksRequired);
        frontRightMotor.setTargetPosition((int) ticksRequired);
        rearLeftMotor.setTargetPosition((int) ticksRequired);
        rearRightMotor.setTargetPosition((int) ticksRequired);

        // Set motor power to start moving
        frontLeftMotor.setPower(1.0);
        frontRightMotor.setPower(1.0);
        rearLeftMotor.setPower(1.0);
        rearRightMotor.setPower(1.0);

        // Wait until the motors reach their target position
        while (opModeIsActive() && (frontLeftMotor.isBusy() || frontRightMotor.isBusy() || rearLeftMotor.isBusy() || rearRightMotor.isBusy())) {
            telemetry.addData("Status", "Moving forward");
            telemetry.addData("Front Left Motor Position", frontLeftMotor.getCurrentPosition());
            telemetry.addData("Front Right Motor Position", frontRightMotor.getCurrentPosition());
            telemetry.addData("Rear Left Motor Position", rearLeftMotor.getCurrentPosition());
            telemetry.addData("Rear Right Motor Position", rearRightMotor.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motors once the target position is reached
        frontLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        rearLeftMotor.setPower(0);
        rearRightMotor.setPower(0);

        // Optionally, add a short pause to ensure the robot has fully stopped
        sleep(500);
    }
}
