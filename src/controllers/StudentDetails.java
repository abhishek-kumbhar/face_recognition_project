/**
 * Sample Skeleton for 'StudentDetails.fxml' Controller Class
 */

package controllers;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class StudentDetails {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="adminOperationsArea"
    private AnchorPane adminOperationsArea; // Value injected by FXMLLoader

    @FXML // fx:id="addNewStudentBtn"
    private JFXButton addNewStudentBtn; // Value injected by FXMLLoader

    @FXML // fx:id="updateStudentBtn"
    private JFXButton updateStudentBtn; // Value injected by FXMLLoader

    @FXML // fx:id="deleteStudentBtn"
    private JFXButton deleteStudentBtn; // Value injected by FXMLLoader

    @FXML // fx:id="viewStudentBtn"
    private JFXButton viewStudentBtn; // Value injected by FXMLLoader

    @FXML
    void addNewStudentBtnClicked(MouseEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(getClass().getResource("../views/AddNewStudent.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);
    }

    @FXML
    void deleteStudentBtnClicked(MouseEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(getClass().getResource("../views/DeleteStudent.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);

    }

    @FXML
    void updateStudentBtnClicked(MouseEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(getClass().getResource("../views/UpdateStudent.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);

    }

    @FXML
    void viewStudentBtnClicked(MouseEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(getClass().getResource("../views/ViewStudents.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'StudentDetails.fxml'.";
        assert addNewStudentBtn != null : "fx:id=\"addNewStudentBtn\" was not injected: check your FXML file 'StudentDetails.fxml'.";
        assert updateStudentBtn != null : "fx:id=\"updateStudentBtn\" was not injected: check your FXML file 'StudentDetails.fxml'.";
        assert deleteStudentBtn != null : "fx:id=\"deleteStudentBtn\" was not injected: check your FXML file 'StudentDetails.fxml'.";
        assert viewStudentBtn != null : "fx:id=\"viewStudentBtn\" was not injected: check your FXML file 'StudentDetails.fxml'.";

    }
}
