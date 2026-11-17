package org.firstinspires.ftc.teamcode.TeleOpBioBuzz;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;


import java.util.Arrays;
import java.util.List;


public class ArrayTest extends OpMode {

    private ElapsedTime timereset;
    private List motores;
    private DcMotor Motor1, Motor2, Motor3, Motor4;


    @Override
    public void init() {
        motores = Arrays.asList(new DcMotor[]{Motor1, Motor2, Motor3, Motor4});
    }

    @Override
    public void loop() {

        ValueOfMotor(0);

    }
    public void ValueOfMotor(int posicao) {
        posicao = (int) (posicao + timereset.seconds())/ 10;// a cada 10 segundos muda o motor


        if (timereset.seconds() >= 50) {
            timereset.reset();
        }

        telemetry.addData("Motores?", motores);
        telemetry.addData("Motores.get?", motores.get(posicao));
        telemetry.addData("Tempo passado", timereset.time());
    }
}
