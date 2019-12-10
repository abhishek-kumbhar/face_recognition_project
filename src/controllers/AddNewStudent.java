/**
 * Sample Skeleton for 'AddNewStudent.fxml' Controller Class
 */

package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class AddNewStudent {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML
    private AnchorPane adminOperationsArea;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="stackPane"
    private StackPane stackPane; // Value injected by FXMLLoader

    @FXML // fx:id="contentLoader"
    private Pane contentLoader; // Value injected by FXMLLoader

    @FXML // fx:id="closeBtn"
    private FontAwesomeIconView closeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private FontAwesomeIconView backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="firstName"
    private JFXTextField firstName; // Value injected by FXMLLoader

    @FXML // fx:id="middleName"
    private JFXTextField middleName; // Value injected by FXMLLoader

    @FXML // fx:id="lastName"
    private JFXTextField lastName; // Value injected by FXMLLoader

    @FXML // fx:id="dob"
    private JFXDatePicker dob; // Value injected by FXMLLoader

    @FXML // fx:id="primaryNumber"
    private JFXTextField primaryNumber; // Value injected by FXMLLoader

    @FXML // fx:id="whatsappNumber"
    private JFXTextField whatsappNumber; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private JFXTextField email; // Value injected by FXMLLoader

    @FXML // fx:id="guardianNumber"
    private JFXTextField guardianNumber; // Value injected by FXMLLoader

    @FXML // fx:id="flatNumber"
    private JFXTextField flatNumber; // Value injected by FXMLLoader

    @FXML // fx:id="road"
    private JFXTextField road; // Value injected by FXMLLoader

    @FXML // fx:id="area"
    private JFXTextField area; // Value injected by FXMLLoader

    @FXML // fx:id="city"
    private JFXTextField city; // Value injected by FXMLLoader

    @FXML // fx:id="pincode"
    private JFXTextField pincode; // Value injected by FXMLLoader

    @FXML // fx:id="collegeName"
    private JFXTextField collegeName; // Value injected by FXMLLoader

    @FXML // fx:id="branch"
    private JFXTextField branch; // Value injected by FXMLLoader

    @FXML // fx:id="collegeArea"
    private JFXTextField collegeArea; // Value injected by FXMLLoader

    @FXML // fx:id="feCheckbox"
    private CheckBox feCheckbox; // Value injected by FXMLLoader


    @FXML // fx:id="seCheckbox"
    private CheckBox seCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="teCheckbox"
    private CheckBox teCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="beCheckbox"
    private CheckBox beCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="websiteCheckbox"
    private CheckBox websiteCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="friendCheckbox"
    private CheckBox friendCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="referalFriendName"
    private JFXTextField referalFriendName; // Value injected by FXMLLoader

    @FXML // fx:id="directCheckbox"
    private CheckBox directCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="addNewStudentBtn"
    private JFXButton addNewStudentBtn; // Value injected by FXMLLoader

    @FXML
    void addNewStudentBtnPressed(MouseEvent event) {

    }

    @FXML
    void backBtnPressed(MouseEvent event) throws IOException {
        AnchorPane fxml = FXMLLoader.load(getClass().getResource("../views/StudentDetails.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);
    }

    @FXML
    void closeApp(MouseEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert contentLoader != null : "fx:id=\"contentLoader\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert closeBtn != null : "fx:id=\"closeBtn\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert middleName != null : "fx:id=\"middleName\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert dob != null : "fx:id=\"dob\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert primaryNumber != null : "fx:id=\"primaryNumber\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert whatsappNumber != null : "fx:id=\"whatsappNumber\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert guardianNumber != null : "fx:id=\"guardianNumber\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert flatNumber != null : "fx:id=\"flatNumber\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert road != null : "fx:id=\"road\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert area != null : "fx:id=\"area\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert city != null : "fx:id=\"city\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert pincode != null : "fx:id=\"pincode\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert collegeName != null : "fx:id=\"collegeName\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert branch != null : "fx:id=\"branch\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert collegeArea != null : "fx:id=\"collegeArea\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert feCheckbox != null : "fx:id=\"feCheckbox\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert seCheckbox != null : "fx:id=\"seCheckbox\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert teCheckbox != null : "fx:id=\"teCheckbox\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert beCheckbox != null : "fx:id=\"beCheckbox\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert websiteCheckbox != null : "fx:id=\"websiteCheckbox\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert friendCheckbox != null : "fx:id=\"friendCheckbox\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert referalFriendName != null : "fx:id=\"referalFriendName\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert directCheckbox != null : "fx:id=\"directCheckbox\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert addNewStudentBtn != null : "fx:id=\"addNewStudentBtn\" was not injected: check your FXML file 'AddNewStudent.fxml'.";

    }
}
