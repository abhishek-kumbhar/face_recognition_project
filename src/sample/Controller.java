/**
 * 'sample.fxml' Controller Class {LoginPage}
 * Author : Omkar Basavraj Ajagunde
 * email : ajagundeomkar@gmail.com
 * gitHub : https://github.com/omkarajagunde
 *
 * Contribution for Core2Web Technologies Attendence System By Face_recognition
 */

package sample;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.bson.Document;

import javax.management.Query;


public class Controller {

    private MongoCollection mongoCollection = null;
    private MongoClient mongoClient = null;

    @FXML // fx:id="usernameLoginPage"
    private JFXTextField usernameLoginPage; // Value injected by FXMLLoader

    @FXML // fx:id="passwordLoginPage"
    private JFXPasswordField passwordLoginPage; // Value injected by FXMLLoader

    @FXML // fx:id="loginBtn"
    private JFXButton loginBtn; // Value injected by FXMLLoader

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="contentLoader"
    private Pane contentLoader; // Value injected by FXMLLoader

    @FXML // fx:id="stackPane"
    private StackPane stackPane; // Value injected by FXMLLoader

    @FXML
    private AnchorPane parent;

    @FXML // fx:id="signup"
    private Label signup; // Value injected by FXMLLoader

    @FXML // fx:id="closeBtn"
    private FontAwesomeIconView closeBtn; // Value injected by FXMLLoader


    /*
     * This method is used when the close or cross signed button on the
     * top right corner is clicked on a {@MouseEvent event}
     * Before closing the application the connections to the mongoDB cloud service
     * are closed.. and then the system is closed sending the Status "0"
     * which tell the JVM virtual Machine that the program was closed successfully
     * without any errors or exceptions ..
     *
     * in parameter : MouseEvent
     * out parameter : NULL
     */
    @FXML
    void closeApp(MouseEvent event) {
        mongoClient.close();
        System.exit(0);
    }

    @FXML
    void newSignupPage(MouseEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("SignupPage.fxml"));
        contentLoader.getChildren().removeAll();
        contentLoader.getChildren().setAll(fxml);
    }

    /**
     * This method is used is order to check weather the person is
     * signed up already in the software and avoid the access to others
     * @param event
     * @returns NULL
     */
    @FXML
    void loginBtnPressed(MouseEvent event) {

        String username = usernameLoginPage.getText();
        String password = passwordLoginPage.getText();

        if (!usernameLoginPage.getText().isEmpty()) {

            if (!passwordLoginPage.getText().isEmpty()) {

                MongoCursor<Document> cursor = mongoCollection.find().iterator();
                //long found = mongoCollection.count(Document.parse("{username : " + username + "}"));

                if (cursor.hasNext()) {
                    System.out.println("Found Element " + cursor.next());
                    while (cursor.hasNext()) {
                        System.out.println("In while Cursoring ...");
                        Document document = cursor.next();

                        String thisUsername = document.getString("username");
                        if (thisUsername.equals(username)) {

                            if (password.equals(document.getString("password"))) {
                                System.out.println("Successfully Logged In ...");
                                dialogDisplay(stackPane, "Successfully Logged In ...");
                                cursor.close();
                                // Go To Admin Panel DashBoard ... .. .
                                break;
                            } else {
                                dialogDisplay(stackPane, "Wrong Password Entered ...");
                            }

                        }

                    }

                } else {
                    System.out.println("No Elements found");
                    dialogDisplay(stackPane, "No Such User Found,\nPlease Sign Up ...");

                }

            } else {
                dialogDisplay(stackPane, "Please fill Password ...");
            }
        } else {
            dialogDisplay(stackPane, "Please fill username ...");
        }


    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'sample.fxml'.";
        assert contentLoader != null : "fx:id=\"contentLoader\" was not injected: check your FXML file 'sample.fxml'.";
        assert usernameLoginPage != null : "fx:id=\"usernameLoginPage\" was not injected: check your FXML file 'sample.fxml'.";
        assert passwordLoginPage != null : "fx:id=\"passwordLoginPage\" was not injected: check your FXML file 'sample.fxml'.";
        assert loginBtn != null : "fx:id=\"loginBtn\" was not injected: check your FXML file 'sample.fxml'.";
        assert signup != null : "fx:id=\"signup\" was not injected: check your FXML file 'sample.fxml'.";
        assert closeBtn != null : "fx:id=\"closeBtn\" was not injected: check your FXML file 'sample.fxml'.";


        connectToDatabase();

    }


    private void connectToDatabase() {

        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb://ajagundeomkar:VViratkoli%40%4011@core2webattendence-shard-00-00-m7irc.mongodb.net:27017,core2webattendence-shard-00-01-m7irc.mongodb.net:27017,core2webattendence-shard-00-02-m7irc.mongodb.net:27017/test?ssl=true&replicaSet=Core2WebAttendence-shard-0&authSource=admin&retryWrites=true&w=majority");

            mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase("core2webAttendenceApplication");
            mongoCollection = database.getCollection("admin_details");
            System.out.println("yes mongoCollection not null");
        } catch (MongoException e) {
            System.out.println("Failed To Connect");
        }
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

}
