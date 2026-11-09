package org.firstinspires.ftc.teamcode.TeleOp.Decode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="opop", group="OpMode")
public class opop extends OpMode {

    // Definindo as variáveis
    private Movimentacao movimentacao;
    private Midtake midtake;
    private Outtake Outtake;
    private Intake Intake;
    private ElapsedTime runtime = new ElapsedTime();

    // definindo valores das variáveis como o hardwareMap do robô, o gamepad e a Telemetria
    @Override
    public void init() {
        movimentacao = new Movimentacao(hardwareMap, gamepad1, telemetry);
        midtake = new Midtake(hardwareMap, gamepad2, telemetry);
        Outtake = new Outtake(hardwareMap, gamepad2);
        Intake = new Intake(hardwareMap,gamepad2);
        runtime.reset();
    }

    // Usando as variáveis públicas das outras programações
    @Override
    public void loop() {
        movimentacao.Mov(0.3, 0.7, 0.5);
        movimentacao.movTelemetry();
        midtake.Telemetry();
        midtake.Mtake();
        Intake.controlIn(0.45);
        Outtake.controlOut(0.6);

    }
}
