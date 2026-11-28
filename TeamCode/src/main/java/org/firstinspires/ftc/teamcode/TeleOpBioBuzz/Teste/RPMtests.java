package org.firstinspires.ftc.teamcode.TeleOpBioBuzz.Teste;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@Autonomous(name = "RPMtests")
public class RPMtests extends LinearOpMode {

    private ElapsedTime time;
    private DcMotorEx motor1;

   // Defina as constantes específicas do REV HD Hex Motor
    final double RPMDesejado = 3200.0;// o final double significa valores que são constantes, uma vez colocados não são mais alterados
    final double TicksperRevolution = 28;


    // Converta o RPM alvo para Graus por Segundo (3200 * 6 = 19200 graus/s)
    double TicksporSDesejado = RPMDesejado * 6;


    @Override
    public void runOpMode()  {
        motor1 = hardwareMap.get(DcMotorEx.class, "MotorConfig1");

        // Definir a velocidade do motor já que apenas aceita Graus por segundo com as informações do nosso input
       motor1.setVelocity(TicksporSDesejado, AngleUnit.DEGREES);



        waitForStart();
        time.reset();

        while (opModeIsActive()){


            //Esse valor recebido é em ticks por segundo
            double AtualTicksperS = motor1.getVelocity();

            double AtualRPM = (AtualTicksperS * 60)/ TicksperRevolution;

            telemetry.addData("RPM real do motor", AtualRPM);
            telemetry.addData("RPM desejado para o motor", RPMDesejado);
            telemetry.update();

            if (Math.abs(RPMDesejado - AtualRPM) <= 0){
                time.reset();

                telemetry.clear();
                telemetry.addData("Operação completa, RPM atingido", "Abaixo terá as informações dos RPMs");
                telemetry.addData("RPM real do motor", AtualRPM);
                telemetry.addData("RPM desejado para o motor", RPMDesejado);
                telemetry.addData("Tempo demorado para ocorrer esta ação em segundos", time.startTime());
                telemetry.update();

                stop();
            }
        }
    }





}

