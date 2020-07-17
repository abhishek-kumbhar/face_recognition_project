/**
 * Sample Skeleton for 'StudentDetails.fxml' Controller Class
 */

package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminDetails {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="adminOperationsArea"
    private AnchorPane adminOperationsArea; // Value injected by FXMLLoader

    @FXML // fx:id="addNewStudentBtn"
    private JFXButton addNewStudentBtn; // Value injected by FXMLLoader

    @FXML // fx:id="updateDatabase"
    private JFXButton updateDatabase; // Value injected by FXMLLoader


    @FXML // fx:id="deleteStudentBtn"
    private JFXButton deleteStudentBtn; // Value injected by FXMLLoader

    @FXML // fx:id="stackPane"
    private StackPane stackPane; // Value injected by FXMLLoader

    @FXML // fx:id="viewStudentBtn"
    private JFXButton viewStudentBtn; // Value injected by FXMLLoader

    public String batch;

    @FXML
    void addNewStudentBtnClicked(MouseEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(getClass().getResource("../views/AddNewAdmin.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);
    }

    @FXML
    void deleteStudentBtnClicked(MouseEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(getClass().getResource("../views/deleteAdmin.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);

    }

    @FXML
    void updateStudentBtnClicked(MouseEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(getClass().getResource("../views/UpdateAdmin.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);

    }



    @FXML // ->> This is to be done by abhi to update the database  when new student is been added
    void updateDatabaseClicked(MouseEvent event) {
        chooseBatch();

    }

    @FXML
    void viewStudentBtnClicked(MouseEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(getClass().getResource("../views/ViewAdmins.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'StudentDetails.fxml'.";
        assert addNewStudentBtn != null : "fx:id=\"addNewStudentBtn\" was not injected: check your FXML file 'StudentDetails.fxml'.";
        assert updateDatabase != null : "fx:id=\"updateDatabase\" was not injected: check your FXML file 'AdminDetails.fxml'.";
        assert deleteStudentBtn != null : "fx:id=\"deleteStudentBtn\" was not injected: check your FXML file 'StudentDetails.fxml'.";
        assert viewStudentBtn != null : "fx:id=\"viewStudentBtn\" was not injected: check your FXML file 'StudentDetails.fxml'.";
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'AdminDetails.fxml'.";


    }


    private void dialogDisplaySuccess(StackPane stackPane, String bodyMessage) {
        BoxBlur blur = new BoxBlur(3, 3, 3);


        JFXButton button = new JFXButton("Okay");
        button.getStyleClass().add("dialogBtn");

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.getStyleClass().add("dialog");
        dialogLayout.setHeading(new Label("ATTENTION !"));

        JFXDialog dialogL = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mEvent) -> {
            stackPane.setEffect(null);
            dialogL.close();

        });

        dialogLayout.setBody(new Text(bodyMessage));
        dialogLayout.setActions(button);
        dialogL.show();
        dialogL.setOnDialogClosed((JFXDialogEvent mEvent) -> {

            stackPane.setEffect(null);
        });
       // stackPane.setEffect(blur);
    }

    public void chooseBatch(){
        List<String> Option = new ArrayList<>();

        Option.add("C2W_PPA5");
        Option.add("C2W_JAVA9");
        Option.add("C2W_PYTHON_MORN");
        Option.add("C2W_PYTHON_EVEN");


        ChoiceDialog<String> dialog = new ChoiceDialog<>("Click here", Option);

        dialog.setX(800);
        dialog.setY(400);
        DialogPane dp = dialog.getDialogPane();
        dp.getStyleClass().add("dialog");
        dialog.setDialogPane(dp);
        dialog.setHeaderText("Choose a Batch from dropDown Please ...");
        Optional<String> batchSelect = dialog.showAndWait();
        if (!batchSelect.get().equals("Click here")) {

            dialogDisplaySuccess(stackPane,batchSelect.get()+" Updation was successful ...");
            batch = batchSelect.get();

        }else
            dialogDisplaySuccess(stackPane,"Please select a batch ...");


    }

}
