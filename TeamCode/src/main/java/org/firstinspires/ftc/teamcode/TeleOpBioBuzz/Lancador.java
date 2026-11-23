package org.firstinspires.ftc.teamcode.TeleOpBioBuzz;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp(name = "Lancador", group = "OpMode")
public class Lancador extends OpMode {

    // Declarando as variáveis como os motores e o gamepad
    private DcMotor Lancador;


    private HardwareMap hardwareMap; // Importando o hardwareMap para usarmos os motores

    private Gamepad gamepad2; // Iporando o gamepad para usarmos o controle

    // Definindo uma função padrão para o Lançador
    public Lancador(HardwareMap hardwareMap, Gamepad gamepad2) {
        this.hardwareMap = hardwareMap;
        this.gamepad2 = gamepad2;
        init();
    }

    // Definindo os comandos na função init
    @Override
    public void init() {

        // Definindo as variáveis dos motores como os motores do hardwareMap
        Lancador = hardwareMap.get(DcMotor.class, "Lancador");

        // Definindo a direção dos motores
        Lancador.setDirection(DcMotor.Direction.FORWARD);


        // Resetando e fazendo com que os motores não usem seu Encoder
        Lancador.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Lancador.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Definindo para que toda a vez que o motor pare, ele dê Brake
        Lancador.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {

        controlOut(0.6);
    }

    // Definindo dentro de um loop os comandos para a função controlOut
    public void controlOut(double PowUp) {
        if (gamepad2.right_trigger > 0.3) { // Se o RT estiver pressionado
            Lancador.setPower(PowUp); // O outtake começa a girar

        }
        else { // Se o RT não estiver pressionado
            Lancador.setPower(0);// Os motores param e Brekam
            Lancador.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
}