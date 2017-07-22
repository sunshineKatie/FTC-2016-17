package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by studenttest on 1/24/2017.
 */

        import com.qualcomm.robotcore.eventloop.opmode.OpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.util.Range;

/**
 * Created by studenttest on 1/17/2017.
 */


@TeleOp(name= "HolonomicDriveTerrain2", group= "Holonomic")

public class HolonomicDrive2 extends OpMode {

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;
//    Servo ServoArmR;
//    Servo ServoArmL;
//    DcMotor motorLift;

    float Y = 0;
    float X = 0;
    float Z = 0;
//    double ArmR = 0;
//    double ArmL = 0;
//    float Lift = 0;
//
    /**
     * Constructor
     */
    public HolonomicDrive2() {

    }
    @Override
    public void init() {


		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */


        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");
//        ServoArmR = hardwareMap.servo.get("ArmRight");
//        ServoArmL = hardwareMap.servo.get("ArmLeft");
//        motorLift = hardwareMap.dcMotor.get("Lift");



        //motorArm= hardwareMap.dcMotor.get("motorArm");
       // servoArm = hardwareMap.servo.get("servo arm");

        //These work without reversing (Tetrix motors).
        //AndyMark motors may be opposite, in which case uncomment these lines:
        //motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorBackLeft.setDirection(DcMotor.Direction.REVERSE);
        //motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        //motorBackRight.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {

        double Threshold= 0.15;

        // left stick controls direction
        // right stick X controls rotation

//        float Y = -gamepad1.left_stick_y;
//        float X = gamepad1.left_stick_x;
//        float Z = gamepad1.right_stick_x;
        if(java.lang.Math.abs(-gamepad1.left_stick_x)> Threshold)
        {
            X= gamepad1.left_stick_x;
        }
        else
        {
            X=0;
        }

        if(java.lang.Math.abs (-gamepad1.left_stick_y)> Threshold)
        {
            Y= -gamepad1.left_stick_y;
        }
        else
        {
            Y=0;
        }

        if(java.lang.Math.abs (gamepad1.right_stick_x)> Threshold)
        {
            Z=gamepad1.right_stick_x;
        }
        else
        {
            Z=0;
        }



        float FrontRight = Y - X - Z;
        float FrontLeft = -Y - X - Z;
        float BackRight = Y + X - Z;
        float BackLeft = -Y + X - Z;
       // double arm = 0;
// if code doesnt work, fix this to float

      // motorArm.setPower(0);

       //motorArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);




        // clip the right/left values so that the values never exceed +/- 1
        //clip if  using joystick
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);
//        Lift = Range.clip(Lift, -1,1);
      //  arm = Range.clip(arm, 0, 1);


        // write the values to the motors
        motorFrontRight.setPower(FrontRight);
        motorFrontLeft.setPower(FrontLeft);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);
    //    ServoArmR.setPosition(ArmR);
    //    ServoArmL.setPosition(ArmL);
    //    motorLift.setPower(Lift);


     //   boolean servoArmRpow = gamepad2.y;
     //   ServoArmR.setPosition(servoArmRpow);

     //   boolean servoArmLpow = gamepad2.y;
     //   ServoArmL.setPosition(servoArmLpow);

     //   boolean servoArmLpow = gamepad2.a;
     //   ServoArmR.setPosition();


     //   float motorLiftpow = gamepad2.right_stick_y;
     //   motorLift.setPower(motorLiftpow);



        // float motorLiftpow = gamepad2.right_stick_y;
       //motorArm.setPower(motorLiftpow);

       // double servoArmpow = gamepad2.left_stick_y;
       // servoArm.setPosition(servoArmpow);



		/*
		 * Telemetry for debugging
		 */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Joy XL YL XR",  String.format("%.2f", X) + " " +
                String.format("%.2f", Y) + " " +  String.format("%.2f", Z));
        telemetry.addData("f left pwr",  "front left  pwr: " + String.format("%.2f", FrontLeft));
        telemetry.addData("f right pwr", "front right pwr: " + String.format("%.2f", FrontRight));
        telemetry.addData("b right pwr", "back right pwr: " + String.format("%.2f", BackRight));
        telemetry.addData("b left pwr", "back left pwr: " + String.format("%.2f", BackLeft));

    }

    @Override
    public void stop() {

    }

    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

}
 





//   @Override
    //public void loop() {

        
        /*
        if(abs (Jval X)> Threshold )
        {
        X= Jval X
        }
        else
        {
        X=0 
        }
        
        */
        // holonomic formulas
//}
