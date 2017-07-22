package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by studenttest on 1/25/2017.
 */
@Autonomous(name="HolonomicAuto1", group="H_autonomus" )

public class  HolonomicAuto1 extends OpMode{

    DcMotor motorFrontRight;
    DcMotor motorFrontLeft;
    DcMotor motorBackRight;
    DcMotor motorBackLeft;



    static final double Runtime = 5.0;
    private ElapsedTime     runtime = new ElapsedTime();

    float Y = 0;
    float X = 0;
    float Z = 0;


    /**
     * Constructor
     */
    public HolonomicAuto1() {

    }
    @Override
    public void init() {


		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */

//Do NOT put spaces in the names
        motorFrontRight = hardwareMap.dcMotor.get("motorFrontRight");
        motorFrontLeft = hardwareMap.dcMotor.get("motorFrontLeft");
        motorBackLeft = hardwareMap.dcMotor.get("motorBackLeft");
        motorBackRight = hardwareMap.dcMotor.get("motorBackRight");

       // servoCatapult = hardwareMap.servo.get("servo catapult")


        //reverse motors
        motorFrontLeft.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void start(){
        runtime.reset();
    }



//deadzone
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


//vectors for holonomic
        float FrontRight = Y - X - Z;
        float FrontLeft = -Y - X - Z;
        float BackRight = Y + X - Z;
        float BackLeft = -Y + X - Z;


        // clip the right/left values so that the values never exceed +/- 1
        FrontRight = Range.clip(FrontRight, -1, 1);
        FrontLeft = Range.clip(FrontLeft, -1, 1);
        BackLeft = Range.clip(BackLeft, -1, 1);
        BackRight = Range.clip(BackRight, -1, 1);

      //  need to do something here??
      //  catapult = Range.clip(catapult, 0, 1);


        // write the values to the motors
        //drive terrain
        motorFrontRight.setPower(FrontRight);
        motorFrontLeft.setPower(FrontLeft);
        motorBackLeft.setPower(BackLeft);
        motorBackRight.setPower(BackRight);







        //Back wheels go out; Front wheels go in

      //  servoCatapult.setPosition(0.5);



        //runtime.reset();   put in start functuion (we need to create this)
        if( runtime.seconds() < 8.00 ) {
            motorFrontRight.setPower(1.00);
            motorFrontLeft.setPower(1.00);
            motorBackRight.setPower(1.00);
            motorBackLeft.setPower(1.00);
        } else {

            motorFrontRight.setPower(0.00);
            motorFrontLeft.setPower(0.00);
            motorBackRight.setPower(0.00);
            motorBackLeft.setPower(0.00);
        }

//need to add buttons for driver 2


            //telemetry.addData("Path", "Leg 1: %2.5f S Elapsed", runtime.seconds());
            //telemetry.update();
            //idle();




        //runtime.reset();

		/*
		 * Telemetry for debugging
		 */
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("Joy XL YL XR",  String.format("%.2f", X) + " " +
                String.format("%.2f", Y) + " " +  String.format("%.2f", Z));
        //telemetry.addData("f left pwr",  "front left  pwr: " + String.format("%.2f", FrontLeft));
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





