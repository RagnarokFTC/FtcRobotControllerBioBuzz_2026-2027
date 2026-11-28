package org.firstinspires.ftc.teamcode.TeleOpBioBuzz.Teste.LancadorTuning;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LancadorTune {
    private DcMotorEx Lancador;

    double kV, kS, kP;
    final double TicksPerRotation = 28;
    final double GearRatio = 1;//Significa a redução do robô, como não temos é 1:1
    //É possível verificar o quão rapido esta nosso motor usando Lgiht-based sensor, Hall effect encoder

    public void init(HardwareMap hardwareMap){
        Lancador = hardwareMap.get(DcMotorEx.class, "Lancador");
        Lancador.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Lancador.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setMotorPower(double power){
        Lancador.setPower(power);

    }

    public double getTicksPerSecond(){
        return Lancador.getVelocity();
    }

    public double getRPM(){
        return ((getTicksPerSecond()/TicksPerRotation) * 60)/GearRatio ;//De segundo para minuto, por isso 60
    }


}
