package org.firstinspires.ftc.teamcode.TeleOpBioBuzz.Debug;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "ServorVerify", group = "OpMode")
public class ServoVerify extends OpMode {



    private Servo ServoTeste0, ServoTeste1;

    double inicialPosition;


    @Override
    public void init() {

        ServoTeste0 = hardwareMap.get(Servo.class, "ServoDebug1");
        ServoTeste1 = hardwareMap.get(Servo.class, "ServoDebug2");

        ServoTeste0.setDirection(Servo.Direction.FORWARD);
        ServoTeste1.setDirection(Servo.Direction.REVERSE);

        inicialPosition = 0.5;
    }

    @Override
    public void loop() {
        Verificar(inicialPosition);
        Telemetria();

    }
    public void Verificar(double posicaoinicial){

        if (gamepad2.dpad_right) {
            ServoTeste0.setPosition(Math.min(ServoTeste0.getPosition() + 0.5, 1));


        }else{
            ServoTeste0.setPosition(posicaoinicial);
        }


        if (gamepad2.dpad_left) {
            ServoTeste1.setPosition(Math.min(ServoTeste1.getPosition() + 0.5, 1));

        } else{
            ServoTeste1.setPosition(posicaoinicial);
        }

    }


    public void Telemetria() {

        telemetry.addData("Setinha para Direita para o Servo da porta 0", ServoTeste0.getPosition());
        telemetry.addData("Direção do Servo da porta 0", ServoTeste0.getDirection());
        telemetry.addData("Setinha para Esquerda para o ServoTeste da porta 1", ServoTeste1.getPosition());
        telemetry.addData("Direção do Servo da porta 1", ServoTeste1.getDirection());

    }
}
