package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name="TeleOp1", group="TeleOp")
//this program makes the robot go forward
public class TestOp2 extends OpMode{

    DcMotor right;
    DcMotor left;
    DcMotor intake;
    DcMotor shooter;

    static final double GOFAST = 0.5;

    @Override
    public void init() {
        right = hardwareMap.dcMotor.get("motor_right");
        left = hardwareMap.dcMotor.get ("motor_left");
        intake = hardwareMap.dcMotor.get("intake");
        shooter = hardwareMap.dcMotor.get("shooter");


        right.setDirection(DcMotor.Direction.REVERSE);


        right.setPower(0);
        left.setPower(0);
        intake.setPower(0);
        shooter.setPower(0);


        right.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        left.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        shooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }

    @Override
    public void loop() {
        // try adding negatives in front of right and left... see if it makes backwards go backwards and forwards forwards
        float rightpow = -gamepad1.right_stick_y;
        float leftpow= -gamepad1.left_stick_y;

        right.setPower(rightpow);
        left.setPower(leftpow);

        float intakepow = gamepad2.right_stick_y;


        intake.setPower(intakepow);


        if(gamepad2.y)
            shooter.setPower(0.25);


    }
}


