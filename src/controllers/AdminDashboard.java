package controllers; /**
 * Sample Skeleton for 'adminDashboard.fxml' Controller Class
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AdminDashboard {

    static boolean success;
    boolean sflag = false;
    private int ppaLecCount, javaLecCount, pythonMornLecturesCount, pythonWeekLecturesCount = 0;

    @FXML // fx:id="ppaLectureCount"
    private Label ppaLectureCount; // Value injected by FXMLLoader
    @FXML // fx:id="javaLectureCount"
    private Label javaLectureCount; // Value injected by FXMLLoader
    @FXML // fx:id="pythonWeekendLectureCount"
    private Label pythonWeekendLectureCount; // Value injected by FXMLLoader
    @FXML // fx:id="pythonMorningLectureCount"
    private Label pythonMorningLectureCount; // Value injected by FXMLLoader
    @FXML
    private AnchorPane ap;

    @FXML
    private Label presentStudentCount;

    @FXML
    private Label absentStudentCount;
    private Stage stage = null;

    @FXML
    private JFXButton aboutClicked;
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    @FXML // fx:id="overviewBtn"
    private JFXButton overviewBtn; // Value injected by FXMLLoader
    @FXML // fx:id="studentDetailsBtn"
    private JFXButton studentDetailsBtn; // Value injected by FXMLLoader
    @FXML // fx:id="batchSelector"
    private JFXComboBox<String> batchSelector; // Value injected by FXMLLoader
    @FXML // fx:id="logoutBtn"
    private JFXButton logoutBtn; // Value injected by FXMLLoader
    @FXML // fx:id="stackPane"
    private StackPane stackPane; // Value injected by FXMLLoader
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
    @FXML // fx:id="adminOperationsArea"
    private Pane adminOperationsArea; // Value injected by FXMLLoader
    @FXML // fx:id="presentCircle"
    private Circle presentCircle; // Value injected by FXMLLoader
    @FXML // fx:id="absentCircle"
    private Circle absentCircle; // Value injected by FXMLLoader
    @FXML // fx:id="adminCloseBtn"
    private FontAwesomeIconView adminCloseBtn; // Value injected by FXMLLoader
    @FXML // fx:id="tableView"
    private TableView<adminDashboardTable> tableView; // Value injected by FXMLLoader

    @FXML

    void logoutBtn(MouseEvent event) throws IOException {
        Stage newStage = new Stage();
        Window window = ((Node) (event.getSource())).getScene().getWindow();
        if (window instanceof Stage) {
            ((Stage) window).close();
        }
        Parent root = FXMLLoader.load(getClass().getResource("../views/sample.fxml"));
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.setScene(new Scene(root, 800, 600));
        newStage.show();
    }

    @FXML
    void adminDetailsBtnClicked(MouseEvent event) throws IOException {
        Pane fxml = FXMLLoader.load(getClass().getResource("../views/AdminDetails.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);
    }

    @FXML
    void admissionDetailsBtnClicked(MouseEvent event) throws IOException {
        Pane fxml = FXMLLoader.load(getClass().getResource("../views/AboutDetails.fxml"));
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
    void startBtnPressed(MouseEvent event) throws  IOException {
        FileWriter fw = new FileWriter("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\selectedBatchForAttendance.txt");
        fw.write(batchSelector.getValue());
        fw.close();



        if (startBtn.getText().equals("Start attendence") && batchSelector.getValue() != null) {
            if (batchSelector.getValue() != null){
                Thread t = new Thread() {

                    public void run(){
                        try {
                            Process p = Runtime.getRuntime().exec("python C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\finalFaceRecCode.py");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.start();
                success = true;



            startBtn.setText("Stop attendence");
            }else
                dialogDisplay(stackPane,"Please Select a batch and Try again ! ...");

        } else {
            //reading prev lec count
            String d = null;
            try {
                File myObj = new File("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\" + batchSelector.getValue() + "\\totalLecCount\\totalLecCount.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    d = myReader.nextLine();
                }
                System.out.println("Old readed String - " + d);
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            //writing new lec count
            FileWriter fx = new FileWriter("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\" + batchSelector.getValue() + "\\totalLecCount\\totalLecCount.txt");
            int k = Integer.valueOf(d);
            k = k + 1;
            String str = Integer.toString(k);
            System.out.println("new proposed String - " + str);
            fx.write(str);
            fx.close();

            startBtn.setText("Start attendence");

            //here, getting total student's for particular batch
            int totalStudent = 0;
            try {
                File totalSt = new File("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\" + batchSelector.getValue() + "\\allIds.txt");
                Scanner myscanner = new Scanner(totalSt);
                while (myscanner.hasNextLine()) {
                    myscanner.nextLine();
                    totalStudent++;
                }
                System.out.println("total student - " + totalStudent);
                myscanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            //today's total count
            int j = 0;
            try {
                File m = new File("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\" + batchSelector.getValue() + "\\todaysAttendance.txt");
                Scanner my = new Scanner(m);
                while (my.hasNextLine()) {
                    my.nextLine();
                    j++;
                }
                System.out.println("total count today's - " + j);
                my.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            presentStudentCount.setText(Integer.toString(j));
            absentStudentCount.setText(Integer.toString(totalStudent - j));

            // making list of today's absent students

            try {
                File myObj = new File("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\" + batchSelector.getValue() + "\\todaysAttendance.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    System.out.println(myReader.nextLine());
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }



           System.out.println("attendance is stopped ");
        }

    }

    @FXML
    void adminCloseBtnPressed(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void overviewBtnClicked(MouseEvent event) throws IOException {

        Stage newStage = new Stage();

        Window window = ((Node) (event.getSource())).getScene().getWindow();
        if (window instanceof Stage) {
            ((Stage) window).close();
        }

        Parent root = FXMLLoader.load(getClass().getResource("../views/adminDashboard.fxml"));
        newStage.initStyle(StageStyle.UNDECORATED);

        newStage.setScene(new Scene(root, 1150, 768));
        newStage.show();
    }


    @FXML
    void aboutClicked(MouseEvent event) throws IOException {
        Pane fxml = FXMLLoader.load(getClass().getResource("../views/AboutDetails.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);
    }

    @FXML
    void studentDetailsBtnClicked(MouseEvent event) throws IOException {

        Pane fxml = FXMLLoader.load(getClass().getResource("../views/StudentDetails.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
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
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert logoutBtn != null : "fx:id=\"logoutBtn\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert presentStudentCount != null : "fx:id=\"presentStudentCount\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert absentStudentCount != null : "fx:id=\"absentStudentCount\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert aboutClicked != null : "fx:id=\"aboutClicked\" was not injected: check your FXML file 'adminDashboard.fxml'.";




        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert adminCloseBtn != null : "fx:id=\"adminCloseBtn\" was not injected: check your FXML file 'adminDashboard.fxml'.";

        assert presentCircle != null : "fx:id=\"presentCircle\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert absentCircle != null : "fx:id=\"absentCircle\" was not injected: check your FXML file 'adminDashboard.fxml'.";
        assert batchSelector != null : "fx:id=\"batchSelector\" was not injected: check your FXML file 'adminDashboard.fxml'.";


        batchSelector.getItems().add("C2W_PPA5");
        batchSelector.getItems().add("C2W_JAVA9");
        batchSelector.getItems().add("C2W_PYTHON_MORN");
        batchSelector.getItems().add("C2W_PYTHON_EVEN");



        String data = null;
        String data1 = null;
        String data2 = null;
        String data3 = null;

        try {
            File myObj = new File("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\C2W_PPA5\\totalLecCount\\totalLecCount.txt");
            File myObj1 = new File("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\C2W_JAVA9\\totalLecCount\\totalLecCount.txt");
            File myObj2 = new File("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\C2W_PYTHON_MORN\\totalLecCount\\totalLecCount.txt");
            File myObj3 = new File("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\C2W_PYTHON_EVEN\\totalLecCount\\totalLecCount.txt");

            Scanner myReader = new Scanner(myObj);
            Scanner myReader1 = new Scanner(myObj1);
            Scanner myReader2 = new Scanner(myObj2);
            Scanner myReader3 = new Scanner(myObj3);

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            while (myReader1.hasNextLine()) {
                data1 = myReader1.nextLine();
            }
            while (myReader2.hasNextLine()) {
                data2 = myReader2.nextLine();
            }
            while (myReader3.hasNextLine()) {
                data3= myReader3.nextLine();
            }
            myReader.close();
            myReader1.close();
            myReader2.close();
            myReader3.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        ppaLectureCount.setText(data);
        javaLectureCount.setText(data1);
        pythonMorningLectureCount.setText(data2);
        pythonWeekendLectureCount.setText(data3);

        adminNameLabel.setText(Controller.adminFirstName + " " + Controller.adminLastName);

    }

    private void dialogDisplay(StackPane stackPane, String bodyMessage) {
        BoxBlur blur = new BoxBlur(3, 3, 3);


        JFXButton button = new JFXButton("Okay");
        button.getStyleClass().add("dialogBtn");
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.getStyleClass().add("dialog");
        dialogLayout.setHeading(new Label("ATTENTION !"));

        JFXDialog dialogL = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mEvent) -> {
            adminOperationsArea.setEffect(null);
            dialogL.close();

        });

        dialogLayout.setBody(new Text(bodyMessage));
        dialogLayout.setActions(button);
        dialogL.show();
        dialogL.setOnDialogClosed((JFXDialogEvent mEvent) -> {

            adminOperationsArea.setEffect(null);
        });
        adminOperationsArea.setEffect(blur);
    }



}


