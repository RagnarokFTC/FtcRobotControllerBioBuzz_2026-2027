package org.firstinspires.ftc.teamcode.TeleOp.Decode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
@Disabled // "Invisibilizando" a programação no driver hub
@TeleOp(name = "Outtake", group = "OpMode")
public class Outtake extends OpMode {

    // Declarando as variáveis como os motores e o gamepad
    private DcMotor OutkA;
    private DcMotor OutkB;

    private HardwareMap hardwareMap; // Importando o hardwareMap para usarmos os motores

    private Gamepad gamepad2; // Iporando o gamepad para usarmos o controle

    // Definindo uma função padrão para Outtake
    public Outtake(HardwareMap hardwareMap, Gamepad gamepad2) {
        this.hardwareMap = hardwareMap;
        this.gamepad2 = gamepad2;
        init();
    }

    // Definindo os comandos na função init
    @Override
    public void init() {

        // Definindo as variáveis dos motores como os motores do hardwareMap
        OutkA = hardwareMap.get(DcMotor.class, "OutA");
        OutkB = hardwareMap.get(DcMotor.class, "OutB");
        // Definindo a direção dos motores
        OutkA.setDirection(DcMotor.Direction.FORWARD);
        OutkB.setDirection(DcMotor.Direction.REVERSE);

        // Resetando e fazendo com que os motores não usem seu Encoder
        OutkA.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        OutkB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        OutkA.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        OutkB.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Definindo para que toda a vez que o motor pare, ele dê Brake
        OutkA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        OutkB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {

        controlOut(0.6);
    }

    // Definindo dentro de um loop os comandos para a função controlOut
    public void controlOut(double PowUp) {
        if (gamepad2.right_trigger > 0.3) { // Se o RT estiver pressionado
            OutkA.setPower(PowUp); // O outtake começa a girar
            OutkB.setPower(PowUp);
        }
        else { // Se o RT não estiver pressionado
            OutkA.setPower(0);// Os motores param e Brekam
            OutkB.setPower(0);
            OutkA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            OutkB.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
}