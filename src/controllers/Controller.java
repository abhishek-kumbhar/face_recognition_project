/**
 * 'sample.fxml' Controller Class {LoginPage}
 * Author : Omkar Basavraj Ajagunde
 * email : ajagundeomkar@gmail.com
 * gitHub : https://github.com/omkarajagunde
 * <p>
 * Contribution for Core2Web Technologies Attendence System By Face_recognition
 */

package controllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Duration;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Controller {

    public static String adminFirstName = null;
    public static String adminLastName = null;

    private MongoCollection<Document> mongoCollection = null;
    private MongoClient mongoClient = null;

    @FXML // fx:id="usernameLoginPage"
    private JFXTextField usernameLoginPage; // Value injected by FXMLLoader

    @FXML
    private Label label;


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


    /**
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
        Parent fxml = FXMLLoader.load(getClass().getResource("../views/SignupPage.fxml"));
        contentLoader.getChildren().removeAll();
        contentLoader.getChildren().setAll(fxml);
    }

    /**
     * This method is used is order to check weather the person is
     * signed up already in the software and avoid the access to others
     *
     * @param event
     * @returns NULL
     */
    @FXML
    void loginBtnPressed(MouseEvent event) throws IOException {

        Stage newStage = new Stage();
        String username = usernameLoginPage.getText();
        String password = passwordLoginPage.getText();

        MongoCursor<Document> cursor = mongoCollection.find().iterator();

        if (!usernameLoginPage.getText().isEmpty()) {

            if (!passwordLoginPage.getText().isEmpty()) {

                while (cursor.hasNext()) {

                    Document document = cursor.next();
                    //System.out.println("Username --> " + document.getString("username") + "  Password --> " + document.getString("password"));
                    System.out.println("Username --> " + document.getString("username") + "  Password --> " + document.getString("password"));

                    if (document.getString("username").equals(username)) {

                        if (document.getString("password").equals(password)) {

                            System.out.println("Successfully Logged In ...");
                            adminFirstName = document.getString("firstName");
                            adminLastName = document.getString("lastName");
                            //dialogDisplay(stackPane, "Successfully Logged In ...");

                            // Go To Admin Panel DashBoard ... .. .



                            // Exit the Current Stage and Go to the dashboard Stage ...
                            Window window = ((Node) (event.getSource())).getScene().getWindow();
                            if (window instanceof Stage) {
                                ((Stage) window).close();
                            }

                            Parent root = FXMLLoader.load(getClass().getResource("../views/adminDashboard.fxml"));
                            newStage.initStyle(StageStyle.UNDECORATED);

                            newStage.setScene(new Scene(root, 1150, 768));
                            newStage.show();



                            break;

                        } else {
                            //password wrong
                            dialogDisplay(stackPane, "Password Wrong ...");
                            break;
                        }

                    } else {
                        if (!cursor.hasNext()) {
                            dialogDisplay(stackPane, "User Not Present In Database ...");
                            break;
                        } else
                            continue;
                    }


                }


            } else {
                dialogDisplay(stackPane, "Please fill Password ...");
            }
        } else {
            dialogDisplay(stackPane, "Please fill username ...");
        }

        //cursor.close();

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

    private void screenTransitionAnimation(){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(parent);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }

}
