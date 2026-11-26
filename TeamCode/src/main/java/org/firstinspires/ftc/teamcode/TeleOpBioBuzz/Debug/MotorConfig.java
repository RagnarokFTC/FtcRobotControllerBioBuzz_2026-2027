package org.firstinspires.ftc.teamcode.TeleOpBioBuzz.Debug;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "MotorConfig", group = "Debug")

public class MotorConfig extends OpMode {

//Definindo as variaveis
    private DcMotorEx MotorConfig1, MotorConfig2;
    private Servo ServoELegal1, ServoELegal2;
    double SettingPower, SettingPosition;
    boolean Aapertado, Xapertado, Yapertado, Bapertado, SetinhaBaixoApertado, start; // Quando o botão A, X e Y é apertado respectivamente
    int MaisouMenosMotor, MaisouMenosServo;

    @Override
    public void init() {
//Definindo a configuração inicial dos motores e servos
        MotorConfig1 = hardwareMap.get(DcMotorEx.class, "MotorConfig1");
        MotorConfig2 = hardwareMap.get(DcMotorEx.class, "MotorConfig2");

        MotorConfig2.setDirection(DcMotorEx.Direction.REVERSE);

        ServoELegal1 = hardwareMap.get(Servo.class, "ServoDebug1");
        ServoELegal2 = hardwareMap.get(Servo.class, "ServoDebug2");

//Definindo booleans
        Aapertado = false;
        Xapertado = true;
        Yapertado = false;
        Bapertado = false;
        SetinhaBaixoApertado = false;
        start = false;
        ServoELegal1.setDirection(Servo.Direction.FORWARD);
        ServoELegal2.setDirection(Servo.Direction.REVERSE);

        SettingPosition = 0.5; // posição inicial do servo
        SettingPower = 0.5; // potencia inicial do motores

        ServoELegal1.setPosition(SettingPosition);
        ServoELegal2.setPosition(SettingPosition);

        telemetry.addData("ATENÇÃO A POSIÇÃO DOS SERVOS E DOS MOTORES PARA NÃO GIRAREM AO CONTRÁRIO E QUEBRAR A PEÇA", "  Inicial o 1 é Forward e 2 é Reverse");
        telemetry.addData("Servo é Gamepad1 e", " Motor é Gamepad2");

    }

    @Override
    public void loop() { //repete o codigo abaixo 50 vezes em um segundo


        if ((gamepad1.start || gamepad2.start) && !start) { //mensagem de AVISOS

            start = true;

        } else {

            telemetry.addData("ATENÇÃO A POSIÇÃO DOS SERVOS E DOS MOTORES PARA NÃO GIRAREM AO CONTRÁRIO E QUEBRAR A PEÇA", "  Inicial o 1 é Forward e 2 é Reverse");
            telemetry.addData("Servo é Gamepad1 e", " Motor é Gamepad2");

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
                /* Teste
                SettingPower = Math.min(Math.abs(MotorConfig1.getPower()) + 0.05, 1) * MaisouMenosMotor;
                */

                //Verifica entre dois número o modulo da potência do motor +0.05 e o número 0 e pega o menor entre ele
                MotorConfig1.setPower(Math.min(Math.abs(MotorConfig1.getPower()) + 0.05, 1) * MaisouMenosMotor);
                MotorConfig2.setPower(Math.min(Math.abs(MotorConfig2.getPower()) + 0.05, 1) * MaisouMenosMotor);
                SettingPower = Math.min(SettingPower + 0.05, 1); //garante que a potência colocada no motor seja a mesmo da variavel
            } else {
                SettingPower = Math.min(SettingPower + 0.05, 1); //Caso o motor não esteja ligado ele aumenta a variavel
            }
            Wait(200);

        }
        if (gamepad2.left_bumper) {// reduz a potência do motor quando clicado

            if (Aapertado || Yapertado) {
                /* Teste
                SettingPower = Math.max(Math.abs(MotorConfig1.getPower()) - 0.05, 0) * MaisouMenosMotor;
                */

                //Verifica entre dois número o modulo da potência do motor -0.05 e o número 0 e pega o maior entre ele
                MotorConfig1.setPower(Math.max(Math.abs(MotorConfig1.getPower()) - 0.05, 0) * MaisouMenosMotor);
                MotorConfig2.setPower(Math.max(Math.abs(MotorConfig2.getPower()) - 0.05, 0) * MaisouMenosMotor);
                SettingPower = Math.max(SettingPower - 0.05, 0);
            } else {
                SettingPower = Math.max(SettingPower - 0.05, 0);//Caso o motor não esteja ligado ele diminui a variavel
            }
            Wait(200);
        }
    }
    public void CalibradorServo(){
        /*Servo
        gamepad1.RB = aumenta a posição do servo com limite 1
        gamepad1.LB = reduz a posição do servo até o limite 0
         */
        if (gamepad1.right_bumper){

            if (Xapertado){
                SettingPosition = Math.min(SettingPosition + 0.05, 1);
               ServoELegal1.setPosition((SettingPosition) * MaisouMenosServo);
                ServoELegal2.setPosition((SettingPosition) * MaisouMenosServo);

            }
            else{
                SettingPosition = Math.min(SettingPosition + 0.05, 1);
            }
            Wait(200);
    }
        if (gamepad1.left_bumper){

            if (Xapertado){
                SettingPosition = Math.max(SettingPosition - 0.05, 0);
                ServoELegal1.setPosition(SettingPosition * MaisouMenosServo);
                ServoELegal2.setPosition((SettingPosition) * MaisouMenosServo);

            }
            else{
                SettingPosition = Math.max(SettingPosition - 0.05, 0);
            }
            Wait(200);
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
                ServoELegal1.setPosition(SettingPosition);
                ServoELegal2.setPosition(SettingPosition);

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
                MotorConfig1.setPower(SettingPower);
                MotorConfig2.setPower(SettingPower);

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
                MotorConfig1.setPower(-SettingPower);
                MotorConfig2.setPower(-SettingPower);
                Wait(200);

            }
        }
    }
    public void ReverseMotor() {

        if (gamepad2.b && !gamepad2.start) {
            if (Bapertado) {
                Bapertado = false;
                MotorConfig2.setDirection(DcMotorSimple.Direction.REVERSE);
                Wait(200);
            } else if (!Bapertado) {
                Bapertado = true;
                MotorConfig2.setDirection(DcMotorSimple.Direction.FORWARD);
                Wait(200);
            }
        }
    }
    public void ReverseServo(){

        if (gamepad1.b){
            if (SetinhaBaixoApertado){
                SetinhaBaixoApertado = false;
                ServoELegal2.setDirection(Servo.Direction.REVERSE);
                Wait(200);
            }
            else if (!SetinhaBaixoApertado){
                SetinhaBaixoApertado = true;
                ServoELegal2.setDirection(Servo.Direction.FORWARD);
                Wait(200);
            }
        }

    }

    public void TelemetryMotor(){ // Parte do código que é possível verificar algumas informações sobre os motores e os servos

        telemetry.addData("-----------------","-----------------");
        telemetry.addLine();
        telemetry.addData("Para ativar o Motor utilize o GAMEPAD 2", " Aperte Start + B");
        telemetry.addLine();

        telemetry.addData("-----------------","-----------------");
        telemetry.addData("Potencia do motor1", MotorConfig1.getPower());
        telemetry.addData("Ticks do motor1", MotorConfig1.getCurrentPosition());
        telemetry.addData("Velocidade do motor2", MotorConfig2.getPower());
        telemetry.addData("Ticks do motor2", MotorConfig2.getCurrentPosition());
        telemetry.addData("Poder configurado", SettingPower);
        telemetry.addData("Direção do 2° Motor", MotorConfig2.getDirection());

        telemetry.addLine();

        telemetry.addData("Para o motor ter a potencia positiva", "aperte botão Y");
        telemetry.addData("Para o motor ter a potencia negativa", "aperte botão A");
        telemetry.addData("Reverter a direção do motor", " aperte B");
        telemetry.addData("Para diminuir o falor do Poder configurado do Motor", "aperta LB");
        telemetry.addData("Para aumentar o falor do Poder configurado do Motor", "aperta RB");
        telemetry.addData("-----------------","-----------------");


    }
    public void TelemetryServo(){

        telemetry.addLine();
        telemetry.addData("Para ativar o Servo utilize o GAMEPAD 1", " Aperte Start + A");
        telemetry.addLine();

        telemetry.addData("-----------------","-----------------");
        telemetry.addData("Posição do Servo", ServoELegal1.getPosition());
        telemetry.addData("Posição configurada", SettingPosition);
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
