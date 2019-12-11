package controllers; /**
 * Sample Skeleton for 'adminDashboard.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;

public class AdminDashboard {


    @FXML private AnchorPane ap;
    private Stage stage = null;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="overviewBtn"
    private JFXButton overviewBtn; // Value injected by FXMLLoader

    @FXML // fx:id="studentDetailsBtn"
    private JFXButton studentDetailsBtn; // Value injected by FXMLLoader

    @FXML // fx:id="admissionDetailsBtn"
    private JFXButton admissionDetailsBtn; // Value injected by FXMLLoader

    @FXML // fx:id="attendanceBookBtn"
    private JFXButton attendanceBookBtn; // Value injected by FXMLLoader

    @FXML // fx:id="startBtn"
    private JFXButton startBtn; // Value injected by FXMLLoader

    @FXML // fx:id="adminDetailsBtn"
    private JFXButton adminDetailsBtn; // Value injected by FXMLLoader

    @FXML // fx:id="adminNameLabel"
    private Label adminNameLabel; // Value injected by FXMLLoader

    @FXML // fx:id="ppaLectureCount"
    private Label ppaLectureCount; // Value injected by FXMLLoader

    @FXML // fx:id="adminOperationsArea"
    private Pane adminOperationsArea; // Value injected by FXMLLoader

    @FXML // fx:id="javaLectureCount"
    private Label javaLectureCount; // Value injected by FXMLLoader

    @FXML // fx:id="presentCircle"
    private Circle presentCircle; // Value injected by FXMLLoader

    @FXML // fx:id="absentCircle"
    private Circle absentCircle; // Value injected by FXMLLoader



    @FXML // fx:id="pythonWeekendLectureCount"
    private Label pythonWeekendLectureCount; // Value injected by FXMLLoader

    @FXML // fx:id="pythonMorningLectureCount"
    private Label pythonMorningLectureCount; // Value injected by FXMLLoader

    @FXML // fx:id="adminCloseBtn"
    private FontAwesomeIconView adminCloseBtn; // Value injected by FXMLLoader

    @FXML // fx:id="tableView"
    private TableView<?> tableView; // Value injected by FXMLLoader

    @FXML
    void adminDetailsBtnClicked(MouseEvent event) throws IOException {

        Pane fxml = FXMLLoader.load(getClass().getResource("../views/AdminDetails.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);
    }

    @FXML
    void admissionDetailsBtnClicked(MouseEvent event) throws IOException {

        Pane fxml = FXMLLoader.load(getClass().getResource("../views/AdmissionDetails.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);
    }

    @FXML
    void attendanceBookBtnPressed(MouseEvent event) throws IOException {

        Pane fxml = FXMLLoader.load(getClass().getResource("../views/AttendanceBookDetails.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);
    }

    @FXML
    void startBtnPressed(MouseEvent event) throws InterruptedException {


        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.05), evt -> presentCircle.setVisible(false)),
                new KeyFrame(Duration.seconds( 0.1), evt -> presentCircle.setVisible(true)));
        timeline.setCycleCount(Animation.INDEFINITE);
        if (startBtn.getText().equals("Start attendence")){

            timeline.play();




            startBtn.setText("Stop attendence");


        }else {


            startBtn.setText("Start attendence");
            timeline.stop();
            

        }

    }





    @FXML
    void adminCloseBtnPressed(MouseEvent event) {

        System.exit(0);
    }

    @FXML
    void overviewBtnClicked(MouseEvent event) throws IOException {

        Stage newStage = new Stage();

        Window window =   ((Node)(event.getSource())).getScene().getWindow();
        if (window instanceof Stage){
            ((Stage) window).close();
        }

        Parent root = FXMLLoader.load(getClass().getResource("../views/adminDashboard.fxml"));
        newStage.initStyle(StageStyle.UNDECORATED);

        newStage.setScene(new Scene(root, 1150, 768));
        newStage.show();
    }

    @FXML
    void studentDetailsBtnClicked(MouseEvent event) throws IOException {

        Pane fxml = FXMLLoader.load(getClass().getResource("../views/StudentDetails.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert ap != null : "fx:id=\"ap\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert overviewBtn != null : "fx:id=\"overviewBtn\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert studentDetailsBtn != null : "fx:id=\"studentDetailsBtn\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert admissionDetailsBtn != null : "fx:id=\"admissionDetailsBtn\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert attendanceBookBtn != null : "fx:id=\"attendanceBookBtn\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert adminDetailsBtn != null : "fx:id=\"adminDetailsBtn\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert adminNameLabel != null : "fx:id=\"adminNameLabel\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert ppaLectureCount != null : "fx:id=\"ppaLectureCount\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert javaLectureCount != null : "fx:id=\"javaLectureCount\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert pythonWeekendLectureCount != null : "fx:id=\"pythonWeekendLectureCount\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert pythonMorningLectureCount != null : "fx:id=\"pythonMorningLectureCount\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert startBtn != null : "fx:id=\"startBtn\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert adminCloseBtn != null : "fx:id=\"adminCloseBtn\" was not injected: check your FXML file 'adminDashboard.fxml'.";

        assert presentCircle != null : "fx:id=\"presentCircle\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert absentCircle != null : "fx:id=\"absentCircle\" was not injected: check your FXML file 'adminDashboard.fxml'.";

    }


}

