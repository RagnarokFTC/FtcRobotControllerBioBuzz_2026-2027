package org.firstinspires.ftc.teamcode.TeleOp.Decode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Disabled // "Invisibilizando" a programação no driver hub
@TeleOp(name = "Intake", group = "OpMode")
public class Intake extends OpMode {

    // Declarando as variáveis como o motor e o gamepad
    private DcMotor Intake;

    private HardwareMap hardwareMap; // Importando o hardwareMap para usarmos o motore

    private Gamepad gamepad2; // Iporando o gamepad para usarmos o controle

    // Definindo uma função padrão para Intake
    public Intake(HardwareMap hardwareMap, Gamepad gamepad2) {
        this.hardwareMap = hardwareMap;
        this.gamepad2 = gamepad2;
        init();
    }

    // Definindo os comandos na função init
    @Override
    public void init() {

        // Definindo a variável do motor como o motor do hardwareMap
        Intake = hardwareMap.get(DcMotor.class, "Intake");
        // Definindo a direção do motor
        Intake.setDirection(DcMotor.Direction.REVERSE);

        // Resetando e fazendo com que o motore não use seu Encoder
        Intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Definindo para que toda a vez que o motor pare, ele dê Brake
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {

        controlIn(0.5);
    }

    // Definindo dentro de um loop os comandos para a função controlIn
    public void controlIn(double PowUp) {
        if (gamepad2.left_trigger > 0.3) { // Se o LT estiver pressionado
            Intake.setPower(-PowUp); // O intake começa a girar
        }
        else { // Se o LT não estiver pressionado
            Intake.setPower(0);// O motor para e Breka
            Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
}