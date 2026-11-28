package org.firstinspires.ftc.teamcode.TeleOpBioBuzz.Debug;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp(name = "MotorConfig", group = "Debug")

public class MotorConfig extends OpMode {

//Definindo as variaveis
    private DcMotorEx MotorConfig1, MotorConfig2;
    private Servo ServoELegal1, ServoELegal2;

    final double TicksperRevolutionHDHEX = 28;

    double SettingPower1, SettingPower2, SettingPosition1, SettingPosition2;
    boolean Aapertado, Xapertado, Yapertado, BapertadoMotor, BapertadoServo , start; // Quando o botão A, X e Y é apertado respectivamente
    int MaisouMenosMotor, MaisouMenosServo;

    //Ticks Por Segundo
    double Velocidade1, Velocidade2;
    double RPM1, RPM2;

    @Override
    public void init() {
//Definindo a configuração inicial dos motores e servos
        MotorConfig1 = hardwareMap.get(DcMotorEx.class, "MotorConfig1");
        MotorConfig2 = hardwareMap.get(DcMotorEx.class, "MotorConfig2");

        MotorConfig2.setDirection(DcMotorEx.Direction.REVERSE);

        MotorConfig1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        MotorConfig2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        MotorConfig1.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        MotorConfig2.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        ServoELegal1 = hardwareMap.get(Servo.class, "ServoDebug1");
        ServoELegal2 = hardwareMap.get(Servo.class, "ServoDebug2");




//Definindo booleans
        Aapertado = false;
        Xapertado = true;
        Yapertado = false;

        BapertadoMotor = false;
        BapertadoServo = false;

        start = false;

        Velocidade1 = MotorConfig1.getVelocity();
        Velocidade2 = MotorConfig2.getVelocity();

        RPM1 = (Velocidade1 * 60)/ TicksperRevolutionHDHEX;
        RPM2 = (Velocidade2 * 60)/ TicksperRevolutionHDHEX;

        ServoELegal1.setDirection(Servo.Direction.FORWARD);
        ServoELegal2.setDirection(Servo.Direction.REVERSE);

        SettingPosition1 = 0.5; // posição inicial do servo
        SettingPosition2 = 0.5;
        SettingPower1 = 0.5; // potencia inicial do motores
        SettingPower2 = 0.5;

        ServoELegal1.setPosition(0);
        ServoELegal2.setPosition(0);



        telemetry.addData("ATENÇÃO A POSIÇÃO DOS SERVOS E DOS MOTORES PARA NÃO GIRAREM AO CONTRÁRIO E QUEBRAR A PEÇA", "  Inicial o 1 é Forward e 2 é Reverse");
        telemetry.addData("Servo é Gamepad1 e", " Motor é Gamepad2");

    }

    @Override
    public void loop() { //repete o codigo abaixo 50 vezes em um segundo


        if ((gamepad1.start || gamepad2.start) && !start ) { //mensagem de AVISOS

            start = true;

        } else {

            telemetry.addData("ATENÇÃO A POSIÇÃO DOS SERVOS E DOS MOTORES PARA NÃO GIRAREM AO CONTRÁRIO E QUEBRAR A PEÇA", "  Inicial o 1 é Forward e 2 é Reverse");
            telemetry.addData("Servo é Gamepad1 e", " Motor é Gamepad2");

            telemetry.addLine();

            telemetry.addData("Pressione Start para começar", "Só esperando você ler isso pra começar");

        }
        if (start) {


            CalibradorMotor();// ativa a função dos calibradores dos motores
            CalibradorServo();// ativa a função dos calibradores dos Servos
            RotacaoMotor();
            RotacaoServo();
            ReverseMotor();
            ReverseServo();
            TelemetryMotor();// ativa o sistema de telemetria
            TelemetryServo();
        }
    }


    public void CalibradorMotor() { /* O nome é auto explicativo, a ideia da função é calibrar os motores e servos, escolhendo a potência que
    os motores irão girar e a posição dos servos
    */
        double MaisSettingPower1 = Math.min(SettingPower1 + 0.05, 1);
        double MaisSettingPower2 = Math.min(SettingPower2 + 0.05, 1);
        double MenosSettingPower1 =  Math.max(SettingPower1 - 0.05, 0);
        double MenosSettingPower2 =  Math.max(SettingPower2 - 0.05, 0);

        /* Ambos comparam dois números, mas
        Math.min(10, 20); // Retorna o menor número, no caso o 10
        Math.max(10, 20); // Returns o maior número, no caso o 20

        Já o Math.abs, retorna o módulo/valor absoluto dos números, ignorando o sinal deles
        Math.abs(5); // Retorna 5
        Math.abs(-5); // Retorna 5
         */

        //Motor
        if (gamepad2.right_bumper) {// aumenta a potência do motor quando clicado
            if (Aapertado || Yapertado) {
                if (gamepad2.dpad_left){

                //Verifica entre dois número o modulo da potência do motor +0.05 e o número 0 e pega o menor entre ele
                MotorConfig1.setPower(Math.min(Math.abs(MotorConfig1.getPower()) + 0.05, 1) * MaisouMenosMotor);
                SettingPower1 = MaisSettingPower1;//MaisSettingPower é alto explicativo e está um pouco mais acima a definição dele
                    Wait(200);
                }

                if (gamepad2.dpad_right) {

                    MotorConfig2.setPower(Math.min(Math.abs(MotorConfig2.getPower()) + 0.05, 1) * MaisouMenosMotor);
                    SettingPower2 = MaisSettingPower2; //garante que a potência colocada no motor seja a mesmo da variavel
                    Wait(200);
                }

                if (!gamepad2.dpad_left && !gamepad2.dpad_right) {
                    MotorConfig1.setPower(Math.min(Math.abs(MotorConfig1.getPower()) + 0.05, 1) * MaisouMenosMotor);
                    MotorConfig2.setPower(Math.min(Math.abs(MotorConfig2.getPower()) + 0.05, 1) * MaisouMenosMotor);
                    SettingPower1 = MaisSettingPower1;
                    SettingPower2 = MaisSettingPower2;
                    Wait(200);
                }

            } else {
                if (gamepad2.dpad_left) {
                    SettingPower1 = MaisSettingPower1;//Caso o motor não esteja ligado ele aumenta a variavel
                    Wait(200);
                }
                if (gamepad2.dpad_right){
                    SettingPower2 = MaisSettingPower2;
                    Wait(200);
                }
                if (!gamepad2.dpad_right && !gamepad2.dpad_left){
                    SettingPower1 = MaisSettingPower1;
                    SettingPower2 = MaisSettingPower2;
                    Wait(200);
                }
            }
            Wait(200);

        }
        if (gamepad2.left_bumper) {// reduz a potência do motor quando clicado
            if (Aapertado || Yapertado) {
                if (gamepad2.dpad_left) {
                    //Verifica entre dois número o modulo da potência do motor -0.05 e o número 0 e pega o maior entre ele
                    MotorConfig1.setPower(Math.max(Math.abs(MotorConfig1.getPower()) - 0.05, 0) * MaisouMenosMotor);
                    SettingPower1 = MenosSettingPower1; //MenosSettingPower é alto explicativo e está um pouco mais acima a definição dele
                    Wait(200);
                }

                if (gamepad2.dpad_right) {
                    MotorConfig2.setPower(Math.max(Math.abs(MotorConfig2.getPower()) - 0.05, 0) * MaisouMenosMotor);
                    SettingPower2 = MenosSettingPower2;
                    Wait(200);
                }

                if (!gamepad2.dpad_left && !gamepad2.dpad_right){
                    MotorConfig1.setPower(Math.max(Math.abs(MotorConfig1.getPower()) - 0.05, 0) * MaisouMenosMotor);
                    MotorConfig2.setPower(Math.max(Math.abs(MotorConfig2.getPower()) - 0.05, 0) * MaisouMenosMotor);
                    SettingPower1 = MenosSettingPower1;
                    SettingPower2 = MenosSettingPower2;
                    Wait(200);
                }

            } else {
                //Caso o motor não esteja ligado ele diminui a variavel
                if (gamepad2.dpad_left) {
                    SettingPower1 = MenosSettingPower1;
                    Wait(200);
                }
                if (gamepad2.dpad_right){
                    SettingPower2 = MenosSettingPower2;
                    Wait(200);
                }
                if (!gamepad2.dpad_right && !gamepad2.dpad_left){
                    SettingPower1 = MenosSettingPower1;
                    SettingPower2 = MenosSettingPower2;
                    Wait(200);
                }
            }
            Wait(200);
        }

        if (gamepad2.dpad_down){

            SettingPower1 = SettingPower2 = 0.5;
            MotorConfig1.setPower(SettingPower1 * MaisouMenosMotor);
            MotorConfig2.setPower(SettingPower2 * MaisouMenosMotor);
            Wait(1000);
        }

    }


    public void CalibradorServo(){
        double MaisSettingPosition1 = Math.min(SettingPosition1 + 0.05, 1);//redução das linhas dos códigos para serem mais legiveis e simples
        double MaisSettingPosition2 = Math.min(SettingPosition2 + 0.05, 1);
        double MenosSettingPosition1 = Math.max(SettingPosition1 - 0.05, 0);
        double MenosSettingPosition2 = Math.max(SettingPosition2 - 0.05, 0);

        /*Servo
        gamepad1.RB = aumenta a posição do servo com limite 1
        gamepad1.LB = reduz a posição do servo até o limite 0
         */
        if (gamepad1.right_bumper){

            if (Xapertado){
                if (gamepad1.dpad_left) {
                    SettingPosition1 = MaisSettingPosition1;
                    ServoELegal1.setPosition((SettingPosition1) * MaisouMenosServo);
                    Wait(200);
                }

                if (gamepad1.dpad_right) {
                    ServoELegal2.setPosition((SettingPosition2) * MaisouMenosServo);
                    SettingPosition2 = MaisSettingPosition2;
                    Wait(200);
                }

                if (!gamepad1.dpad_left && !gamepad1.dpad_right){
                    ServoELegal1.setPosition((SettingPosition1) * MaisouMenosServo);
                    ServoELegal2.setPosition((SettingPosition2) * MaisouMenosServo);
                    SettingPosition1 = MaisSettingPosition1;
                    SettingPosition2 = MaisSettingPosition2;
                    Wait(200);
                }
            }
            else{
                if (gamepad1.dpad_left) {
                    SettingPosition1 = MaisSettingPosition1;
                    Wait(200);
                }

                if (gamepad1.dpad_right) {
                    SettingPosition2 = MaisSettingPosition2;
                    Wait(200);
                }

                if (!gamepad1.dpad_left && !gamepad2.dpad_right) {
                    SettingPosition1 = MaisSettingPosition1;
                    SettingPosition2 = MaisSettingPosition2;
                    Wait(200);

                }
            }
            Wait(200);
    }
        if (gamepad1.left_bumper){

            if (Xapertado){
                if (gamepad1.dpad_left) {
                    SettingPosition1 = MenosSettingPosition1;
                    ServoELegal1.setPosition(SettingPosition1 * MaisouMenosServo);
                    Wait(200);
                }

                if (gamepad1.dpad_right) {
                    SettingPosition2 = MenosSettingPosition2;
                    ServoELegal2.setPosition((SettingPosition2) * MaisouMenosServo);
                    Wait(200);
                }

                if (!gamepad1.dpad_right && !gamepad1.dpad_left){
                SettingPosition1 = MenosSettingPosition1;
                SettingPosition2 = MenosSettingPosition2;
                ServoELegal1.setPosition(SettingPosition1 * MaisouMenosServo);
                ServoELegal2.setPosition((SettingPosition2) * MaisouMenosServo);
                    Wait(200);

              }
            }
            else{
                if (gamepad1.dpad_left) {
                    SettingPosition1 = MenosSettingPosition1;
                    Wait(200);
                }

                if (gamepad1.dpad_right) {
                    SettingPosition2 = MenosSettingPosition2;
                    Wait(200);
                }

                if (!gamepad1.dpad_right && !gamepad1.dpad_left){
                    SettingPosition1 = MenosSettingPosition1;
                    SettingPosition2 = MenosSettingPosition2;
                    Wait(200);
                }
            }
            Wait(200);
        }

    if(gamepad1.dpad_down){

        SettingPosition1 = SettingPosition2 = 0.5;
        ServoELegal1.setPosition(SettingPosition1 * MaisouMenosServo);
        ServoELegal2.setPosition(SettingPosition2 * MaisouMenosServo);

        Wait(1000);
    }
    }


    public void RotacaoServo(){
         /*Servo
        gamepad2.x = quando apertado uma vez o servo vai para a posição 0, quando apertado de novo
        vai para a posição 1 e repete o ciclo

         */
        if (gamepad1.x) {
            if (Xapertado) {

                Xapertado = false;
                ServoELegal1.setPosition(0);
                ServoELegal2.setPosition(0);

                Wait(200);


            } else if (!Xapertado) {

                MaisouMenosServo = 1;
                CalibradorServo();
                ServoELegal1.setPosition(SettingPosition1);
                ServoELegal2.setPosition(SettingPosition2);

                Xapertado = true;
                Wait(200);

            }
        }
    }


    public void RotacaoMotor(){
        /*Motor:
        Gamepad2.Y = sentido horário do motor
        Gamepad2.A = sentido anti-horário do motor
         */
        if (gamepad2.y && !Aapertado) { // verifica se o Y do gamepad2 está pressionado e se A NÃO está apertado
            if (Yapertado) { // essa condicional so funciona se Yapertado = true


                Yapertado = false; //desativa essa linha para que possa ser executado a linha 62 se Y for apertado de novo

                //Desativação dos motores
                MotorConfig1.setPower(0);
                MotorConfig1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                MotorConfig2.setPower(0);
                MotorConfig2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                Wait(200);//a espera é necessário para evitar repetição do mesmo código de forma muito rápida que pode causar erros


            } else if (!Yapertado) { // funciona apenas se Yapertado = false

                MaisouMenosMotor = 1; // Define a rotação do motor, como é positivo será no horário, é crucial na hora do calculo do calibrador
                CalibradorMotor();

                //Define a potência dos motores após realizar o calibração
                MotorConfig1.setPower(SettingPower1);
                MotorConfig2.setPower(SettingPower2);

                Yapertado = true;// Y é considerado apertado
                Wait(200);

            }
        } else if (gamepad2.a && !Yapertado) {// verifica se o A do gamepad2 está pressionado e se Y NÃO está apertado
            if (Aapertado) { // essa condicional só funciona se Aapertado = true

                Aapertado = false;
                MotorConfig1.setPower(0);
                MotorConfig1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                MotorConfig2.setPower(0);
                MotorConfig2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                Wait(200);


            } else if (!Aapertado) { // funciona apenas se Aapertado = false

                MaisouMenosMotor = -1; // Calibra o sentido de rotação do motor, como é negativo roda no anti-horário
                CalibradorMotor();
                Aapertado = true;
                MotorConfig1.setPower(-SettingPower1);
                MotorConfig2.setPower(-SettingPower2);
                Wait(200);

            }
        }
    }


    public void ReverseMotor() {

        if (gamepad2.b && !gamepad2.start) {
            if (BapertadoMotor) {
                BapertadoMotor = false;
                MotorConfig2.setDirection(DcMotorSimple.Direction.REVERSE);
                Wait(200);
            } else if (!BapertadoMotor) {
                BapertadoMotor = true;
                MotorConfig2.setDirection(DcMotorSimple.Direction.FORWARD);
                Wait(200);
            }
        }
    }


    public void ReverseServo(){

        if (gamepad1.b){
            if (BapertadoServo){
                BapertadoServo = false;
                ServoELegal2.setDirection(Servo.Direction.REVERSE);
                Wait(200);
            }
            else if (!BapertadoServo){
                BapertadoServo = true;
                ServoELegal2.setDirection(Servo.Direction.FORWARD);
                Wait(200);
            }
        }
    }


    public void TelemetryMotor(){ // Parte do código que é possível verificar algumas informações sobre os motores e os servos

        telemetry.addData("-----------------","-----------------");
        telemetry.addLine();
        telemetry.addData("Para ativar o Motor utilize o GAMEPAD 2", " Aperte Start + B");
        telemetry.addData("O Dpad Left é 1", " O Dpad Right é o 2");
        telemetry.addData("DpadDown reseta os valores para 0.5", "Se nenhum for pressionado aplica em ambos");


        telemetry.addLine();

        telemetry.addData("-----------------","-----------------");
        telemetry.addData("Motor 1 Selecionado", gamepad2.dpad_left);
        telemetry.addData("Potencia do motor1", MotorConfig1.getPower());
        telemetry.addData("Ticks do motor1", MotorConfig1.getCurrentPosition());
        telemetry.addData("Corrente do motor1", MotorConfig1.getCurrent(CurrentUnit.MILLIAMPS));
        telemetry.addData("Poder configurado do motor1", SettingPower1);
        telemetry.addData("RPM do motor1", RPM1);

        telemetry.addLine();

        telemetry.addData("Motor 2 Selecionado", gamepad2.dpad_right);
        telemetry.addData("Potência do motor2", MotorConfig2.getPower());
        telemetry.addData("Ticks do motor2", MotorConfig2.getCurrentPosition());
        telemetry.addData("Corrente do motor2", MotorConfig1.getCurrent(CurrentUnit.MILLIAMPS));
        telemetry.addData("Direção do 2° Motor", MotorConfig2.getDirection());
        telemetry.addData("Poder configurado do motor2", SettingPower2);
        telemetry.addData("RPM do motor1", RPM2);


        telemetry.addLine();

        telemetry.addData("Para o motor ter a potencia positiva", "aperte botão Y");
        telemetry.addData("Para o motor ter a potencia negativa", "aperte botão A");
        telemetry.addData("Reverter a direção do motor", " aperte B");
        telemetry.addData("Para diminuir o falor do Poder configurado do Motor", "aperta LB");
        telemetry.addData("Para aumentar o falor do Poder configurado do Motor", "aperta RB");
        telemetry.addData("------------------------------","------------------------------------");


    }
    public void TelemetryServo(){

        telemetry.addLine();
        telemetry.addData("Para ativar o Servo utilize o GAMEPAD 1", " Aperte Start + A");
        telemetry.addLine();

        telemetry.addData("-----------------","-----------------");
        telemetry.addData("Servo1 Selecionado", gamepad1.dpad_left);
        telemetry.addData("Posição do Servo1", ServoELegal1.getPosition());
        telemetry.addData("Posição configurada do Servo1", SettingPosition1);

        telemetry.addLine();

        telemetry.addData("Servo2 Selecionado", gamepad1.dpad_right);
        telemetry.addData("Posição do Servo2", ServoELegal2.getPosition());
        telemetry.addData("Posição configurada do Servo2", SettingPosition2);
        telemetry.addData("Direção do 2° Servo", ServoELegal2.getDirection());

        telemetry.addLine();

        telemetry.addData("Para o Servo ir para a posição positiva", "aperte X");
        telemetry.addData("Para diminuir o falor do Poder configurado do Servo", "aperta Setinha Esquerda");
        telemetry.addData("Para aumentar o falor do Poder configurado do Servo", "aperta Setinha Direita");
        telemetry.addData("Reverter a direção do servo", " aperte Setinha Baixo");
        telemetry.addData("----------------------------","-------------------------------------------------");
    }
    public void Wait(int milissecs){//função que implementa o tempo de espera
        try {
            Thread.sleep(milissecs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
