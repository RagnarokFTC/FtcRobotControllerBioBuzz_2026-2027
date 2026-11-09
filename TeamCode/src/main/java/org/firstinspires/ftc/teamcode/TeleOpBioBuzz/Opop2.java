package org.firstinspires.ftc.teamcode.TeleOpBioBuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.easyopencv.OpenCvCameraBase;

@TeleOp(group = "OpMode", name = "Opop2")
public class Opop2 extends OpMode {

    private Movimenthacao movimenthacao;

    double deadzone;

    @Override
    public void init() {
        movimenthacao = new Movimenthacao(hardwareMap,gamepad1,telemetry);

    }

    @Override
    public void loop() {
        deadzone = 0.1;
        movimenthacao.Movimentar(deadzone);
        movimenthacao.Telemetria();
    }
}
