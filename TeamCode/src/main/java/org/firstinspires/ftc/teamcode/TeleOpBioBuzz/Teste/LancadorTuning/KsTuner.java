package org.firstinspires.ftc.teamcode.TeleOpBioBuzz.Teste.LancadorTuning;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TeleOpBioBuzz.Lancador;

@TeleOp
public class KsTuner extends OpMode {

    public static double kS = 0;
    double[] increments = {0.000001, 0.00001, 0.0001, 0.001, 0.01, 0,1 };
    int incrementisIdx = 4;// esse valor será selecionado o númer que estiver na 4° posição, nesse caso o 0.001

    LancadorTune lancador = new LancadorTune();

    @Override
    public void init() {

        lancador.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad2.dpad_left){
            incrementisIdx = Math.min(incrementisIdx + 1, 6);
        } else if (gamepad2.dpad_right){
            incrementisIdx = Math.max(incrementisIdx - 1, 1);
        }

        double CurrentStep = increments[incrementisIdx];

        lancador.setMotorPower(kS);

        telemetry.addData("kS", "%.6f",kS);//vai mostra 6 casas decimais
        telemetry.addData("RPM", lancador.getRPM());
        telemetry.addData("TicksperSecond", lancador.getTicksPerSecond());
        telemetry.update();
    }
}
