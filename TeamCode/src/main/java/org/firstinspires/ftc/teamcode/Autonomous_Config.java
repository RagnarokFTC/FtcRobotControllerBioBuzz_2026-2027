package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Autonomous_Config extends LinearOpMode {

    private DcMotor MDT, MDF, MET, MEF;
    double PMDT, PMDF, PMET, PMEF;

    private ElapsedTime tempopassado;
    private IMU imu;


    @Override
    public void runOpMode() {

        MEF = hardwareMap.get(DcMotor.class, "LeftDriveUp");
        MET = hardwareMap.get(DcMotor.class, "LeftDriveDown");
        MDF = hardwareMap.get(DcMotor.class, "RightDriveUp");
        MDT = hardwareMap.get(DcMotor.class, "RightDriveDown");

        // Definindo a direção dos motores
        MDF.setDirection(DcMotor.Direction.FORWARD);
        MDT.setDirection(DcMotor.Direction.FORWARD);
        MEF.setDirection(DcMotor.Direction.REVERSE);
        MET.setDirection(DcMotor.Direction.REVERSE);

        // Resetando e definindo que os motores não usarão seu encoder
        MDT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MEF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MDF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MEF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MDT.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MET.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");

        //Direção do ControlHub, ficar atento a isso
        RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection = RevHubOrientationOnRobot.UsbFacingDirection.LEFT; //Pode mudar mais tarde

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoFacingDirection, usbFacingDirection);

        imu.initialize(new IMU.Parameters(orientationOnRobot));
        imu.resetYaw();

        waitForStart();

        Forward(0.6, 0.6, 0.6, 0.6, 4);

    }

    public void Forward(double PMDF, double PMEF, double PMDT, double PMET, double preset ) {

        tempopassado.reset();

        while (tempopassado.time() < preset) {
            MDF.setPower(PMDF);
            MDT.setPower(PMDT);
            MEF.setPower(PMEF);
            MET.setPower(PMET);
        }
    }
}
