package org.firstinspires.ftc.teamcode.TeleOpBioBuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name= "Barreira", group = "OpMode")
public class Barreira extends OpMode {
    private Servo barreira;
    boolean B_pressionado;

    @Override
    public void init() {
        barreira = hardwareMap.get(Servo.class, "barreira" );

        barreira.setDirection(Servo.Direction.FORWARD);

        B_pressionado = false;
    }

    @Override
    public void loop() {

        B_barreira();
    }
    public void B_barreira(){
        if (gamepad2.b){
            if (!B_pressionado) {
                barreira.setPosition(1);
                B_pressionado = true;

            }else if (B_pressionado) {
                barreira.setPosition(0);
                B_pressionado = false;

            }

        }
    }

}
