// olá amiguinho programador da Raganarök, lá pela linha 82 até a 97, vc vai achar o orienta campo
//é simplesmente a maior aplicação de matemática que vc vai ver (potencialmente na tua vida)

// recomendação do seu amiguinho neiva*, estuda matriz de rotação <3

package org.firstinspires.ftc.teamcode.TeleOp.Decode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.Telemetry;  // Importe necessário para a telemetria
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Disabled // "Invisibilizando" a programação no driver hub
@TeleOp(name = "Movimentacao", group = "OpMode")
public class Movimentacao {

    // Definindo as variáveis usadas na programação
    private HardwareMap hardwareMap;
    private Gamepad gamepad1;// Não é mas necessário para usar gamepad
    private Telemetry telemetry;// Não é mais necessário para usar telemetria
    private DcMotor MDF, MEF, MDT, MET;
    private IMU imu;

    double G1LY, G1LX, G1RX;
    double orientation;
    double PMEF, PMET, PMDF, PMDT;
    double X, Y;

    // Definindo uma função padrão para Movimentacao
    public Movimentacao(HardwareMap hardwareMap, Gamepad gamepad1, Telemetry telemetry) {
        this.hardwareMap = hardwareMap;
        this.gamepad1 = gamepad1;
        this.telemetry = telemetry;
        initHardware();

    }

    // Definindo os comandos para a função initHardware
    private void initHardware() {

        // Definindo os valores das variáveis como os motres do hardwareMap
        MEF = hardwareMap.get(DcMotor.class, "LeftDriveUp");
        MET = hardwareMap.get(DcMotor.class, "LeftDriveDown");
        MDF = hardwareMap.get(DcMotor.class, "RightDriveUp");
        MDT = hardwareMap.get(DcMotor.class, "RightDriveDown");

        // Definindo a direção dos motores
        MDF.setDirection(DcMotor.Direction.FORWARD);
        MDT.setDirection(DcMotor.Direction.FORWARD);
        MEF.setDirection(DcMotor.Direction.REVERSE);
        MET.setDirection(DcMotor.Direction.FORWARD);

        // Resetando e definindo que os motores não usarão seu encoder
        MDT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MEF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        MDF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MEF.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MDT.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        MET.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Definindo os parametros do imu para a posição e guinada do robô
        imu = hardwareMap.get(IMU.class, "imu");

        //Parâmetros do imu

        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.LEFT;

        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);

        imu.resetYaw();
    }

    // Definindo os comandos da função Mov
    public void Mov(double deadzone, double bindmin, double bindmax) {
        orientation = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        G1LY =  -gamepad1.left_stick_y; // Invertendo o eixo Y (cima positivo)
        G1LX = gamepad1.left_stick_x;
        G1RX = gamepad1.right_stick_x;

        // Orienta campo java
        if (gamepad1.dpad_left) {
            imu.resetYaw();
        }

        // Cálculo do vetor de direção do robô (Matriz de rotação)
        YawPitchRollAngles orientationAngles = imu.getRobotYawPitchRollAngles();
        X = G1LX * Math.cos(-orientationAngles.getYaw(AngleUnit.RADIANS)) - G1LY * Math.sin(-orientationAngles.getYaw(AngleUnit.RADIANS));
        Y = G1LX * Math.sin(-orientationAngles.getYaw(AngleUnit.RADIANS)) + G1LY * Math.cos(-orientationAngles.getYaw(AngleUnit.RADIANS));

        double denominador = Math.max(Math.abs(Y) + Math.abs(X) + Math.abs(G1RX), 1);//Math.Abs define o valor absoluto, ou seja não tem sinal negativo

        // Aplicação do cálculo nas variáveis de potencia do robô
        PMEF = (Y + X + G1RX) / denominador;
        PMET = (Y - X + G1RX) / denominador;
        PMDF = (Y - X - G1RX) / denominador;
        PMDT = (Y + X - G1RX) / denominador;

        // Definindo a movimentação do robô de fato, seguindo gamepad
        if ((Math.abs(G1LY) > deadzone) || (Math.abs(G1LX) > deadzone) || (Math.abs(G1RX) > deadzone)) {
            if (gamepad1.right_trigger > 0 && gamepad1.right_trigger < 0.8) {
                MEF.setPower(PMEF * bindmin);
                MET.setPower(PMET * bindmin);
                MDF.setPower(PMDF * bindmin);
                MDT.setPower(PMDT * bindmin);
            } else if (gamepad1.right_trigger > 0.8) {
                MEF.setPower(PMEF * bindmax);
                MET.setPower(PMET * bindmax);
                MDF.setPower(PMDF * bindmax);
                MDT.setPower(PMDT * bindmax);
            } else {
                MEF.setPower(PMEF);
                MET.setPower(PMET);
                MDF.setPower(PMDF);
                MDT.setPower(PMDT);
            }
        } else {
            MEF.setPower(0);
            MET.setPower(0);
            MDF.setPower(0);
            MDT.setPower(0);

            MEF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            MET.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            MDF.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            MDT.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    // Atualizando a telemetria com valores de potência dos motores
    public void movTelemetry() {
        telemetry.addData("PMEF", String.format("%.2f", PMEF));
        telemetry.addData("PMET", String.format("%.2f", PMET));
        telemetry.addData("PMDF", String.format("%.2f", PMDF));
        telemetry.addData("PMDT", String.format("%.2f", PMDT));

        telemetry.addData("rotY", String.format("%.2f", Y));
        telemetry.addData("rotX", String.format("%.2f", X));

        telemetry.addData("G1LY", String.format("%.2f", G1LY));
        telemetry.addData("G1LX", String.format("%.2f", G1LX));

        telemetry.addData("RTG1", String.format("%.2f", gamepad1.right_trigger));

        telemetry.addData("orientation (yaw)", String.format("%.2f", orientation));
        telemetry.addData("TicksFrente:",MDT.getCurrentPosition());
        telemetry.addData("TicksLado: ",MEF.getCurrentPosition());

        telemetry.update();

    }
}