package org.firstinspires.ftc.teamcode.TeleOp.Decode.Debug;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp (name = "ControllerTester", group = "Debug")
public class ControllerTester extends OpMode {


    @Override
    public void init() {

        telemetry.addData(gamepad1.type.name(), "Não sei que nome é esse mas testa");
        telemetry.addData(String.valueOf(gamepad1), "esperando iniciar");
        telemetry.addData(String.valueOf(gamepad2), "esperando iniciar");


    }

    @Override
    public void loop() {


            telemetry.addData("Gamepad1 A button", gamepad1.a);
            telemetry.addData("Gamepad1 X button", gamepad1.x);
            telemetry.addData("Gamepad1 Y button", gamepad1.y);
            telemetry.addData("Gamepad1 B button", gamepad1.b);
            telemetry.addData("Gamepad1 LT", gamepad1.left_trigger);
            telemetry.addData("Gamepad1 RT", gamepad1.right_trigger);

            telemetry.addLine();

        telemetry.addData("Gamepad1 Analogico esquerdo X", gamepad1.left_stick_x);
        telemetry.addData("Gamepad1 Analogico esquerdo Y", gamepad1.left_stick_y);
        telemetry.addData("Gamepad1 Analogico direito X", gamepad1.right_stick_x);
        telemetry.addData("Gamepad1 Analogico direito Y", gamepad1.right_stick_y);

        telemetry.addLine();

        telemetry.addData("Gamepad2 A button", gamepad2.a);
        telemetry.addData("Gamepad2 X button", gamepad2.x);
        telemetry.addData("Gamepad2 Y button", gamepad2.y);
        telemetry.addData("Gamepad2 B button", gamepad2.b);
        telemetry.addData("Gamepad2 LT", gamepad2.left_trigger);
        telemetry.addData("Gamepad2 RT", gamepad2.right_trigger);

        telemetry.addLine();

        telemetry.addData("Gamepad2 Analogico esquerdo X", gamepad2.left_stick_x);
        telemetry.addData("Gamepad2 Analogico esquerdo Y", gamepad2.left_stick_y);
        telemetry.addData("Gamepad2 Analogico direito X", gamepad2.right_stick_x);
        telemetry.addData("Gamepad2 Analogico direito Y", gamepad2.right_stick_y);
    }
}
/*
x =* 10 é a mesma coisa que x = x * 10
&& é a mesma coisa que and
|| é a mesma coisa que ou
 */
