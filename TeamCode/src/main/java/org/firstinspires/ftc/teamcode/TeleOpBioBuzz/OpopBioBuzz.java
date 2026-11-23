package org.firstinspires.ftc.teamcode.TeleOpBioBuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(group = "OpMode", name = "OpopBio")
public class OpopBioBuzz extends OpMode {

    private Movimenthacao movimenthacao;

    private IntakeBioBuzz intakeBioBuzz;
    private Servo_Regulador servoRegulador;
    private Lancador lancador;
    private ElapsedTime runtime = new ElapsedTime();


    double deadzone;

    @Override
    public void init() {


        movimenthacao = new Movimenthacao(hardwareMap,gamepad1,telemetry);
        intakeBioBuzz = new IntakeBioBuzz(hardwareMap,gamepad2);
        servoRegulador = new Servo_Regulador(hardwareMap,gamepad2,telemetry);
        lancador = new Lancador(hardwareMap, gamepad2);
        runtime.reset();
    }

    @Override
    public void loop() {
        deadzone = 0.1;
        movimenthacao.Movimentar(deadzone, 0.6);
        intakeBioBuzz.controlIn(0.5);
        servoRegulador.ServoAction(1);
        lancador.controlOut(0.6);


        Telemetria();
    }
    public void Telemetria(){
        telemetry.addData("Gamepad1 = start + A", " Controla a movimentação do Robô");
        telemetry.addData("Gamepad2 = start + B", " Controla os mecanismos do robô");

        telemetry.addLine();

        movimenthacao.Telemetria(runtime);

        telemetry.addLine();

        servoRegulador.TelemetryServo();

    }
}
