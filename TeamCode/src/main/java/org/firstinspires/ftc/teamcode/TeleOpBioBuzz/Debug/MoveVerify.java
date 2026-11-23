package org.firstinspires.ftc.teamcode.TeleOpBioBuzz.Debug;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@TeleOp(name = "MoveVerify", group = "OpMode")
public class MoveVerify extends OpMode {

    private DcMotor MDT, MEF, MDF, MET;

    private List Motores;



    @Override
    public void init() {
      Motores = Arrays.asList(new DcMotor[]{MDT, MEF, MDF, MET});


        MEF = hardwareMap.get(DcMotor.class, "LeftDriveUp");
        MET = hardwareMap.get(DcMotor.class, "LeftDriveDown");
        MDF = hardwareMap.get(DcMotor.class, "RightDriveUp");
        MDT = hardwareMap.get(DcMotor.class, "RightDriveDown");

        MDT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MEF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MDF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MEF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MDT.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MET.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        MDF.setDirection(DcMotor.Direction.FORWARD);
        MDT.setDirection(DcMotor.Direction.FORWARD);
        MEF.setDirection(DcMotor.Direction.REVERSE);
        MET.setDirection(DcMotor.Direction.REVERSE);


    }

    @Override
    public void loop() {
        Verificar();
        Telemetria();

    }
    public void Verificar(){
        if (gamepad1.y){
            MEF.setPower(0.5);

        }if (gamepad1.x) {
            MET.setPower(0.5);

        }
        if (gamepad1.a) {
            MDF.setPower(0.5);

        }
        if (gamepad2.b) {
            MDT.setPower(0.5);

        }
        else {
            MEF.setPower(0);
        }
        
    }


    public void Telemetria() {

        telemetry.addData("Posição dos Motores", "MDT(frente) e MEF(lateral) devem ser colocado as odometrias");
        telemetry.addData("Motor Esquerda Frente", MEF.getCurrentPosition());
        telemetry.addData("Motor Esquerda Trás", MET.getCurrentPosition());
        telemetry.addData("Motor Direita Frente", MDF.getCurrentPosition());
        telemetry.addData("Motor Direita Trás", MDT.getCurrentPosition());

        telemetry.addLine();

        telemetry.addData("Y para o Motor Esquerda Frente", MEF.getPower());
        telemetry.addData("X para o Motor Esquerda Trás", MET.getPower());
        telemetry.addData("A para o Motor Direita Frente", MDF.getPower());
        telemetry.addData("B para o Motor Direita Trás", MDT.getPower());

    }
}
