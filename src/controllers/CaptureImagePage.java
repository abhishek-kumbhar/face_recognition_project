/**
 * Sample Skeleton for 'CaptureImagePage.fxml' Controller Class
 */

package controllers;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class CaptureImagePage {

    private boolean stopCamera = false;
    private BufferedImage grabbedImage;
    //	private WebcamPanel selWebCamPanel = null;
    private Webcam selWebCam = null;



    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="adminOperationsArea"
    private AnchorPane adminOperationsArea; // Value injected by FXMLLoader

    @FXML // fx:id="stackPane"
    private StackPane stackPane; // Value injected by FXMLLoader

    @FXML // fx:id="webcamDisplay"
    private Canvas webcamDisplay; // Value injected by FXMLLoader

    @FXML // fx:id="takeImageBtn"
    private JFXButton startCamera; // Value injected by FXMLLoader

    @FXML // fx:id="loginBtn1"
    private JFXButton stopCam; // Value injected by FXMLLoader

    @FXML // fx:id="deleteImageBtn"
    private JFXButton deleteImageBtn; // Value injected by FXMLLoader

    @FXML // fx:id="studentID"
    private Label studentID; // Value injected by FXMLLoader

    /*                                  this part is commented today 14 - 07 2020
    @FXML
    void deleteImageBtnPressed(MouseEvent event) {
        System.out.println("abhishek hello");
        try(PythonInterpreter pyInterp = new PythonInterpreter()) {
            pyInterp.execfile("mainProgram");
        }
    }
    */

    @FXML
    void openImageBtn(MouseDragEvent event) {

    }

    @FXML
    void openImageBtnPressed(MouseEvent event) throws IOException, InterruptedException {

       /*
        System.out.println("abhishek K");
        Webcam webcam = Webcam.getDefault();
        webcam.open();

        // get image
        BufferedImage image = webcam.getImage();

        // save image to PNG file
        ImageIO.write(image, "JPG", new File("C:\\Users\\Abhi\\Desktop\\dataSet\\test.jpg"));
        */
        String[] command = {"cmd.exe", "/c", "Start", "C:\\Users\\Abhi\\Desktop\\face_rec_test\\a.bat"};
        Process p = Runtime.getRuntime().exec(command);
        /*
       Thread t = new Thread() {

           public void run(){

               try {
                   Process p = Runtime.getRuntime().exec("python C:\\Users\\Abhi\\Desktop\\test\\abhi.py");
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       };
       t.start();

         */

    }

    private void closeCamera()
    {
        if(selWebCam != null)
        {
            selWebCam.close();
        }
    }
    public void stopCamera(ActionEvent event)
    {
        stopCamera = true;
        startCamera.setDisable(false);
        stopCam.setDisable(true);
    }


    @FXML
    void takeImageBtnPressed(MouseEvent event) throws IOException {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel panel = new WebcamPanel(webcam);
       /*
        panel.setFPSDisplayed(true);
        panel.setDisplayDebugInfo(true);
        panel.setImageSizeDisplayed(true);
        panel.setMirrored(true);
      */

        JFrame window = new JFrame("Test webcam panel");
        window.add(panel);
        window.setLocation(790,210);

        window.setUndecorated(true);
        window.setResizable(false);
       // window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);

        System.out.println("Done");
    /*
        String command = "python3 scripts/capture_images.py";
        Process p = Runtime.getRuntime().exec(command + " omkar" );
        System.out.println("reached yake image pressed button");
    */
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize()  {
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert webcamDisplay != null : "fx:id=\"webcamDisplay\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert startCamera != null : "fx:id=\"takeImageBtn\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert stopCam != null : "fx:id=\"loginBtn1\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert deleteImageBtn != null : "fx:id=\"deleteImageBtn\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert studentID != null : "fx:id=\"studentID\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";



    }







}

