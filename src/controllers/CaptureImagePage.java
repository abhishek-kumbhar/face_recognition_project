/**
 * Sample Skeleton for 'CaptureImagePage.fxml' Controller Class
 */

package controllers;

import com.jfoenix.controls.JFXButton;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;


public class CaptureImagePage {


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
    private JFXButton takeImageBtn; // Value injected by FXMLLoader

    @FXML // fx:id="loginBtn1"
    private JFXButton loginBtn1; // Value injected by FXMLLoader

    @FXML // fx:id="deleteImageBtn"
    private JFXButton deleteImageBtn; // Value injected by FXMLLoader

    @FXML // fx:id="studentID"
    private Label studentID; // Value injected by FXMLLoader

    @FXML
    void deleteImageBtnPressed(MouseEvent event) {

    }

    @FXML
    void openImageBtn(MouseDragEvent event) {

    }

    @FXML
    void openImageBtnPressed(MouseEvent event) {

    }

    @FXML
    void takeImageBtnPressed(MouseEvent event) throws IOException {

        String command = "python3 scripts/capture_images.py";
        Process p = Runtime.getRuntime().exec(command + " omkar" );

        System.out.println("reached yake image pressed button");

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize()  {
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert webcamDisplay != null : "fx:id=\"webcamDisplay\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert takeImageBtn != null : "fx:id=\"takeImageBtn\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert loginBtn1 != null : "fx:id=\"loginBtn1\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert deleteImageBtn != null : "fx:id=\"deleteImageBtn\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";
        assert studentID != null : "fx:id=\"studentID\" was not injected: check your FXML file 'CaptureImagePage.fxml'.";



    }



}

