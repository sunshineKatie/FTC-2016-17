package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="Autonomous1", group="TestOp")
//this program makes the robot go forward
public class TestOp extends LinearOpMode {

    DcMotor right;
    DcMotor left;




    static final double GOFAST = 0.1;
    static final double Runtime = 5.0;

    private ElapsedTime     runtime = new ElapsedTime();




   //1 tile is 23 inches
    //robot is 17 inches
    // we need 2 tiles (29 in)then turn 45? degrees to the left

    @Override
    public void runOpMode() throws InterruptedException {
        right = hardwareMap.dcMotor.get("motor_right");
        left = hardwareMap.dcMotor.get ("motor_left");
        right.setDirection(DcMotor.Direction.REVERSE);

        right.setPower(0);
        left.setPower(0);

        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        telemetry.addData("Status", "Running");
        telemetry.update();

        waitForStart();

        right.setPower(0.75);
        left.setPower(0.75);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2.5 )) {
            telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
            idle();

        }






        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
}








