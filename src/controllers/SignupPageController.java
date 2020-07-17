/**
 * 'SignupPage.fxml' Controller Class
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
import com.mongodb.client.MongoDatabase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import org.bson.Document;
import sample.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupPageController {


    // Instance variables so all methods in this class can have access .. to mongoDB operations
    // once the connection is established by the call to {@connectToDatabase}
    private MongoCollection<Document> mongoCollection = null;
    private MongoClient mongoClient = null;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="firstName"
    private JFXTextField firstName; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private JFXPasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="lastName"
    private JFXTextField lastName; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private JFXTextField email; // Value injected by FXMLLoader

    @FXML // fx:id="ppaCheckbox"
    private JFXCheckBox ppaCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="javaCheckbox"
    private JFXCheckBox javaCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="pythonCheckbox"
    private JFXCheckBox pythonCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="primaryPhoneNumber"
    private JFXTextField primaryPhoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="whatsappNumber"
    private JFXTextField whatsappNumber; // Value injected by FXMLLoader

    @FXML // fx:id="rePassword"
    private JFXPasswordField rePassword; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private JFXTextField username; // Value injected by FXMLLoader

    @FXML // fx:id="contentLoader"
    private Pane contentLoader; // Value injected by FXMLLoader

    @FXML // fx:id="signupBtn"
    private JFXButton signupBtn; // Value injected by FXMLLoader

    @FXML // fx:id="closeBtn"
    private FontAwesomeIconView closeBtn; // Value injected by FXMLLoader

    @FXML // fx:id="stackPane"
    private StackPane stackPane; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private FontAwesomeIconView backBtn; // Value injected by FXMLLoader

    /**
     * This method is called when the back button or left directed arrow is pressed
     * on the top left of the application on the signup Screen..
     * The back pressed loads a new login screen on a part of the scene {@Pane},
     * it does not loads whole {@AnchorPane}
     *
     * Exceptions : Compile Time {@IOEXception} thrown
     * @param  : MouseEvent
     * @returns : NULL
     */
    @FXML
    void backBtnPressed(MouseEvent event) throws IOException {

        Parent fxml = FXMLLoader.load(getClass().getResource("../views/sample.fxml"));
        Main.stage.getScene().setRoot(fxml);
    }

    /**
     * This method is used when the close or cross signed button on the
     * top right corner is clicked on a {@MouseEvent event}
     * Before closing the application the connections to the mongoDB cloud service
     * are closed.. and then the system is closed sending the Status "0"
     * which tell the JVM virtual Machine that the program was closed successfully
     * without any errors or exceptions ..
     *
     * @param  : MouseEvent
     * @returns : NULL
     */
    @FXML
    void closeApp(MouseEvent event) {
        mongoClient.close();
        System.exit(0);
    }

    /**
     * Once the signup button is pressed the call reaches the follwoing function,
     * Here @{signupBtnPressed code} a new User account is created and stored in a BSON
     * mongoDB object Over the mongoDB cloud
     *
     * @params : MouseEvent {@code Once mouse button is clicked the method starts executing ...}
     * @returns : NULL {@signupBtnPressed is void function}

     */
    @FXML
    void signupBtnPressed(MouseEvent event) {

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


        // check weather all feilds are entered or not ..
        if (!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !username.getText().isEmpty() &&
                !email.getText().isEmpty() && !primaryPhoneNumber.getText().isEmpty() && !whatsappNumber.getText().isEmpty() &&
                !password.getText().isEmpty() && !rePassword.getText().isEmpty()) {

            // check weather the email entered is valid or not ..
            if (isValidEmail(email.getText())) {

                // check Weather the re-Entered password is same as the above password or not ..
                if (password.getText().equals(rePassword.getText())) {

                    // Check weather the entered phone number has 10 digit valid number ..
                    if (isValidPhoneNumber(primaryPhoneNumber.getText()) && isValidPhoneNumber(whatsappNumber.getText())) {

                        // Check weather either atleast One check box is selected or not
                        if (ppaCheckbox.isSelected() || javaCheckbox.isSelected() || pythonCheckbox.isSelected()) {

                            try {

                                // Create a JSON document that is to be stored in MongoDB cloud database
                                Document document = new Document("firstName", firstName.getText());
                                document.append("lastName", lastName.getText());
                                document.append("email", email.getText());
                                document.append("username", username.getText());
                                document.append("primaryPhoneNumber", primaryPhoneNumber.getText());
                                document.append("whatsappPhoneNumber", whatsappNumber.getText());
                                document.append("password", password.getText());

                                // check for weather either one of three checkboxes is clicked and checked
                                if (ppaCheckbox.isSelected())
                                    document.append("batch", "PPA");
                                else if (javaCheckbox.isSelected())
                                    document.append("batch", "JAVA");
                                else
                                    document.append("batch", "PYTHON");

                                mongoCollection.insertOne(document);


                                // Clear the text once the user account is created
                                firstName.setText("");
                                lastName.setText("");
                                email.setText("");
                                username.setText("");
                                primaryPhoneNumber.setText("");
                                whatsappNumber.setText("");
                                ppaCheckbox.setSelected(false);
                                javaCheckbox.setSelected(false);
                                pythonCheckbox.setSelected(false);

                                dialogDisplay(stackPane, "User Account created successfully ...");

                                Parent fxml = FXMLLoader.load(getClass().getResource("sample.fxml"));
                                Main.stage.getScene().setRoot(fxml);


                            } catch (Exception e) {
                                System.out.println("Err");
                               // dialogDisplay(stackPane, "Error Creating New Account,\nTry Again ...");
                            }


                        } else {

                            // Pop Up Please select at least one checkBox ...
                            dialogDisplay(stackPane, "Check atleast One CheckBox ...");
                        }

                    } else {

                        // Pop Up Phone Number Does Not Match
                        dialogDisplay(stackPane, "Enter a Valid Phone Number ...");
                    }


                } else {

                    // Pop Up Password Does not matches ...
                    dialogDisplay(stackPane, "Password Does Not match");
                }

            } else {

                // Pop Up Email is not Valid Try Again
                dialogDisplay(stackPane, "Enter a valid Email");
            }


        } else {

            // Pop Up that fill all feild with Red Marks ...
            dialogDisplay(stackPane, "Please enter all feilds ...");

        }

    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert contentLoader != null : "fx:id=\"contentLoader\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert signupBtn != null : "fx:id=\"signupBtn\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert closeBtn != null : "fx:id=\"closeBtn\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert ppaCheckbox != null : "fx:id=\"ppaCheckbox\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert javaCheckbox != null : "fx:id=\"javaCheckbox\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert pythonCheckbox != null : "fx:id=\"pythonCheckbox\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert primaryPhoneNumber != null : "fx:id=\"primaryPhoneNumber\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert whatsappNumber != null : "fx:id=\"whatsappNumber\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert rePassword != null : "fx:id=\"rePassword\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'SignupPage.fxml'.";
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'SignupPage.fxml'.";

        connectToDatabase();


    }

    /**
     * connectToDatabase is a void function that accepts no parameters and instantiates a new connection
     * to the mongoDB cloud Atlas ..
     *
     * @params : NULL
     * @returns : NULL
     */
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

    /**
     * Method to check weather the entered Email is valid or not
     * This is done by checking weather the String contains '@','.' etc ..
     * @params : String
     * @returns : boolean
     */

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


    /**
     * Method to create a Pop-Up overlay or modern looking dialog box
     * in order to give alert and successful message to the user interactions
     * This methods needs and works only on a scene of {@StackPane object}
     * the dialog box should be the child of {@StackPane objject}
     *
     * @params : StackPane, String
     * @returns : NULL
     */
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
