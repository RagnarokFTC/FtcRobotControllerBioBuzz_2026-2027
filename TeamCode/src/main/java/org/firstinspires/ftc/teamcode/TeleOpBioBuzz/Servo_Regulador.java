package org.firstinspires.ftc.teamcode.TeleOpBioBuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.sun.tools.javac.code.Type;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "Servo_Regulador", group = "OpMode")
public class Servo_Regulador extends OpMode {

    private Servo ServoDireita, ServoEsquerda;
    private Gamepad gamepad2;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    boolean A_pressionado;

    double posicaoinicial;

    public Servo_Regulador(HardwareMap hardwareMap, Gamepad gamepad2, Telemetry telemetry){
        this.hardwareMap = hardwareMap;
        this.gamepad2 = gamepad2;
        this.telemetry = telemetry;
        init();
    }

    @Override
    public void init() {

        ServoDireita = hardwareMap.get(Servo.class, "ServoDireita");
        ServoEsquerda = hardwareMap.get(Servo.class, "ServoEsquerda");

        ServoDireita.setDirection(Servo.Direction.REVERSE);
        ServoEsquerda.setDirection(Servo.Direction.FORWARD);

        posicaoinicial = 0.0;
        A_pressionado = true;
    }

    @Override
    public void loop() {

        ServoAction(1);
    }

    public void ServoAction(double PowerPos) {

        if (gamepad2.a) {

            if (A_pressionado) {
                A_pressionado = false;
                ServoDireita.setPosition(PowerPos);
                ServoEsquerda.setPosition(PowerPos);

            }
            else if (!A_pressionado) {
                A_pressionado = true;

                ServoDireita.setPosition(posicaoinicial);
                ServoEsquerda.setPosition(posicaoinicial);
            }
        }
    }

    public void TelemetryServo(){
        telemetry.addData("Botão dos Servos", ServoDireita.getController());

        telemetry.addLine();

        telemetry.addData("Posição do Servo da direita", ServoDireita.getPosition());
        telemetry.addData("Posição do Servo da esquerda", ServoEsquerda.getPosition());

    }
}
