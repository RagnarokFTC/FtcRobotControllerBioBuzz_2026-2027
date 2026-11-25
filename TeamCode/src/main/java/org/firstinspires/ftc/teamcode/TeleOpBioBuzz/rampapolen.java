package org.firstinspires.ftc.teamcode.TeleOpBioBuzz;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "ramapolen", group = "OpMode")
public class rampapolen extends OpMode {
    private Servo rampapolen0, rampapolen1;

    boolean RB_pressionado;

    @Override
    public void init() {
        rampapolen0 = hardwareMap.get(Servo.class, "rampapolen0");
        rampapolen1 = hardwareMap.get(Servo.class, "rampapolen1");
        rampapolen0.setDirection(Servo.Direction.FORWARD);
        rampapolen1.setDirection(Servo.Direction.REVERSE);
        RB_pressionado = false;
    }

    @Override
    public void loop() {
      rb();
    }

    public void rb(){
        if (gamepad1.right_bumper){
            if (!RB_pressionado){
                rampapolen0.setPosition(1);
                rampapolen1.setPosition(1);
                RB_pressionado = true;
            } else if (RB_pressionado){
                rampapolen0.setPosition(0);
                rampapolen1.setPosition(0);
                RB_pressionado = false;
            }
        }
    }
}
