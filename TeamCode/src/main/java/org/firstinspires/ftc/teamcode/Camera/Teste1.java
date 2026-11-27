package org.firstinspires.ftc.teamcode.Camera;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp(name = "CamTest", group = "OpMode")
public class Teste1 extends OpMode {


    private VisionPortal visionPortal;

    private AprilTagProcessor aprilTagProcessor;


    @Override
    public void init() {


        initcamera();
    }

    @Override
    public void loop() {

    }


    public void initcamera(){
        aprilTagProcessor = new AprilTagProcessor.Builder()
                //Informações da camera aqui


                .build();




    VisionPortal.Builder builder = new VisionPortal.Builder();


    builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam Logi"));

    builder.addProcessor(aprilTagProcessor);

    visionPortal = builder.build();
    }
}
