package org.firstinspires.ftc.teamcode.TeleOpBioBuzz;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

import java.time.Year;

@TeleOp(group = "Opmode", name = "Movimenthacao")
public class Movimenthacao extends OpMode {

    private DcMotor MDT, MDF, MET, MEF;
    double PMDT, PMDF, PMET, PMEF;

    private ElapsedTime tempopassado;
    private IMU imu;
    public Movimenthacao(HardwareMap hardwareMap, Gamepad gamepad, Telemetry telemetry){
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad;
        this.telemetry = telemetry;
    }
    @Override
    public void init() {

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

        telemetry.addData("Aperta pra começar aí do  lado", "so clicar");
        telemetry.update();
    }

    @Override
    public void loop() {

        Movimentar(0.1);
        Telemetria();
    }
    public void Movimentar(double deadzone){

        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        double max; // valor que impede que as potências passem do máximo(1)

        if (gamepad1.dpad_left) {
            imu.resetYaw();

            telemetry.clearAll();

            telemetry.addData("imu reiniciado", imu.getRobotYawPitchRollAngles());
            telemetry.update();
        }

        double G1LY_axial   = -gamepad1.left_stick_y;  // obs: está negativo pois o valor para frente é invertido por padrão
        double G1LX_lateral =  gamepad1.left_stick_x;
        double G1RX_yaw     =  gamepad1.right_stick_x;

       double X = G1LX_lateral * Math.cos(-orientation.getYaw(AngleUnit.RADIANS)) - G1LY_axial * Math.sin(-orientation.getYaw(AngleUnit.RADIANS));
       double Y = G1LX_lateral * Math.sin(-orientation.getYaw(AngleUnit.RADIANS)) + G1LY_axial * Math.cos(-orientation.getYaw(AngleUnit.RADIANS));

        max = Math.max(Math.abs(Y) + Math.abs(X) + Math.abs(G1RX_yaw), 1);

        PMEF = Y + X + G1RX_yaw/ max;
        PMET = Y - X + G1RX_yaw/ max;
        PMDF = Y - X - G1RX_yaw/ max;
        PMDT = Y + X - G1RX_yaw/ max;

        if ((Math.abs(G1LY_axial) > deadzone) || (Math.abs(G1LX_lateral) > deadzone) || (Math.abs(G1RX_yaw) > deadzone)) {
            MDT.setPower(PMDT);
            MDF.setPower(PMDF);
            MEF.setPower(PMEF);
            MET.setPower(PMET);
        }
        else {
            MDT.setPower(0);
            MDF.setPower(0);
            MEF.setPower(0);
            MET.setPower(0);

            MDT.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            MDF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            MET.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            MEF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

    }
    public void Telemetria(){

        telemetry.clearAll();

        telemetry.addData("Tempo passado: ", tempopassado.toString());
        telemetry.update();
    }

}


