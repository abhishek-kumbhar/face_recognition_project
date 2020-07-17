/**
 * Sample Skeleton for 'DeleteStudent.fxml' Controller Class
 */

package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class deleteAdmin {

    private MongoCollection<Document> mongoCollection = null;
    private MongoClient mongoClient = null;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="adminOperationsArea"
    private AnchorPane adminOperationsArea; // Value injected by FXMLLoader

    @FXML
    private Pane contentLoader;

    @FXML // fx:id="stackPane"
    private StackPane stackPane; // Value injected by FXMLLoader

    @FXML // fx:id="searchBtn"
    private JFXButton searchBtn; // Value injected by FXMLLoader

    @FXML // fx:id="searchBar"
    private JFXTextField searchBar; // Value injected by FXMLLoader

    @FXML // fx:id="filterSelector"
    private JFXComboBox<String> filterSelector; // Value injected by FXMLLoader

    @FXML // fx:id="pane"
    private Pane pane; // Value injected by FXMLLoader

    @FXML
    void searchBtnPressed(MouseEvent event) {

        String searchQuery = searchBar.getText();

        if (filterSelector.getValue() != null && !filterSelector.getValue().isEmpty()){

            if (!searchBar.getText().isEmpty()){

                if (filterSelector.getValue().equals("email")){


                    if (isValidEmail(searchBar.getText())) {


                        FindIterable<Document> fi = mongoCollection.find(Filters.eq("email", searchQuery));
                        MongoCursor<Document> cursor = fi.iterator();

                        if (!cursor.hasNext())
                            dialogDisplay(stackPane, "No such entry found in the Student Database ...");


                        while (cursor.hasNext()) {


                            Document document = cursor.next();
                            mongoCollection.deleteOne(document);

                            dialogDisplay(stackPane, "Successfully deleted student with email : "+ searchQuery);


                        }

                    } else {
                        dialogDisplay(stackPane, "Please enter a valid email to search ...");
                        searchBar.requestFocus();


                    }
                }

                if (filterSelector.getValue().equals("username")){





                        FindIterable<Document> fi = mongoCollection.find(Filters.eq("username", searchQuery));
                        MongoCursor<Document> cursor = fi.iterator();

                        if (!cursor.hasNext())
                            dialogDisplay(stackPane, "No such entry found in the Student Database ...");


                        while (cursor.hasNext()) {


                            Document document = cursor.next();
                            mongoCollection.deleteOne(document);

                            dialogDisplay(stackPane, "Successfully deleted student with username : "+ searchQuery);


                        }

                }

            }else{
                // pop up for text not entered in SearchBar .... ... .. .
                dialogDisplay(stackPane, "Please enter a valid email to search ...");
                searchBar.requestFocus();
            }

        }else{
            // pop up for filter not selected .... ... .. .
            dialogDisplay(stackPane, "Please select a filter to search ...");
            filterSelector.requestFocus();

        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'DeleteStudent.fxml'.";
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'DeleteStudent.fxml'.";
        assert searchBtn != null : "fx:id=\"searchBtn\" was not injected: check your FXML file 'DeleteStudent.fxml'.";
        assert searchBar != null : "fx:id=\"searchBar\" was not injected: check your FXML file 'DeleteStudent.fxml'.";
        assert contentLoader != null : "fx:id=\"contentLoader\" was not injected: check your FXML file 'DeleteStudent.fxml'.";
        assert filterSelector != null : "fx:id=\"filterSelector\" was not injected: check your FXML file 'DeleteStudent.fxml'.";

        filterSelector.getItems().add("username");
        filterSelector.getItems().add("email");

        connectToDatabase();

    }

    private void dialogDisplay(StackPane stackPane, String bodyMessage) {
        BoxBlur blur = new BoxBlur(3, 3, 3);


        JFXButton button = new JFXButton("Okay");
        button.getStyleClass().add("dialogBtn");
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.getStyleClass().add("dialog");
        dialogLayout.setHeading(new Label("ATTENTION !"));

        JFXDialog dialogL = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mEvent) -> {
            contentLoader.setEffect(null);
            dialogL.close();


        });

        dialogLayout.setBody(new Text(bodyMessage));
        dialogLayout.setActions(button);
        dialogL.show();
        dialogL.setOnDialogClosed((JFXDialogEvent mEvent) -> {

            contentLoader.setEffect(null);
        });
        contentLoader.setEffect(blur);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    /**
     * Method to check Weather the Phone number entered is of exactly
     * 10 numeric digits and contains no other letter and special symbols in it
     *
     * @params : String
     * @returns : boolean
     */
    private boolean isValidPhoneNumber(String s) {
        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // 1) Begins with 0 or 91
        // 2) Then contains 7 or 8 or 9.
        // 3) Then contains 9 digits
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    private void connectToDatabase() {

        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb://ajagundeomkar:VViratkoli%40%4011@core2webattendence-shard-00-00-m7irc.mongodb.net:27017,core2webattendence-shard-00-01-m7irc.mongodb.net:27017,core2webattendence-shard-00-02-m7irc.mongodb.net:27017/test?ssl=true&replicaSet=Core2WebAttendence-shard-0&authSource=admin&retryWrites=true&w=majority");

            mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase("core2webAttendenceApplication");
            mongoCollection = database.getCollection("admin_details");
        } catch (MongoException e) {
            System.out.println("Failed To Connect");
            dialogDisplay(stackPane, "Check Internet");
        }
    }
}
