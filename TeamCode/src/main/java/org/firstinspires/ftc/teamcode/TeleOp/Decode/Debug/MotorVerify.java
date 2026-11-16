package org.firstinspires.ftc.teamcode.TeleOp.Decode.Debug;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MotorVerify", group = "OpMode")
public class MotorVerify extends OpMode {

    private DcMotor MDT, MEF, MDF, MET;
    private Servo ServoTeste0, ServoTeste1;


    @Override
    public void init() {

        ServoTeste0 = hardwareMap.get(Servo.class, "ServoTeste0");
        ServoTeste1 = hardwareMap.get(Servo.class, "ServoTeste1");

        ServoTeste0.setDirection(Servo.Direction.FORWARD);
        ServoTeste1.setDirection(Servo.Direction.REVERSE);

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

        ServoTeste0.setPosition(0.5);
        ServoTeste1.setPosition(0.5);

    }

    @Override
    public void loop() {
        Verificar();
        Telemetria();

    }
    public void Verificar(){
        if (gamepad1.y){
            MEF.setPower(0.5);

        }
        if (gamepad1.x) {
            MET.setPower(0.5);

        }
        if (gamepad1.a) {
            MDF.setPower(0.5);

        }
        if (gamepad2.b) {
            MDT.setPower(0.5);

        }
        if (gamepad2.dpad_right) {
            ServoTeste0.setPosition(Math.min(ServoTeste0.getPosition() + 0.5, 1));

        }
        if (gamepad2.dpad_left) {
            ServoTeste1.setPosition(Math.min(ServoTeste0.getPosition() + 0.5, 1));

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
