package org.firstinspires.ftc.teamcode.TeleOp.Decode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Disabled // "Invisibilizando" a programação no driver hub
@TeleOp(name = "Midtake", group = "OpMode")
public class Midtake extends OpMode {

    // Definindo variáveis
    private HardwareMap hardwareMap;
    private Gamepad gamepad2;
    private Telemetry telemetry;
    private Servo Midtake;

    // Definindo uma função padrão para Midtake
    public Midtake(HardwareMap hardwareMap, Gamepad gamepad2, Telemetry telemetry) {

        this.hardwareMap = hardwareMap;
        this.gamepad2 = gamepad2;
        this.telemetry = telemetry;
        init();
    }

    @Override
    public void init() {

        // Definindo o valor da variável midtake como o servo contínuo do hardwareMap
        Midtake = hardwareMap.get(Servo.class, "MidTake");
        Midtake.setPosition(0.5);
    }

    // Definindo a função Mtake
    @Override
    public void loop() {

        Mtake();
    }

    // Definindo os comandos da função Mtake
    public void Mtake(){
        if (gamepad2.a) {
            Midtake.setPosition(0); // Vai para a posição 0
        } else {
            Midtake.setPosition(0.5); // Vai para a posição 1
        }
    }

    public void Telemetry() {

        // Adicionando a atual posição do servo a telemetria
        telemetry.addData("PosiSrv: ", Midtake.getPosition());
        telemetry.addData("A:", gamepad2.a);

        telemetry.update();
    }
}