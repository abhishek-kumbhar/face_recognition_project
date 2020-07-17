/**
 * Sample Skeleton for 'UpdateAdmin.fxml' Controller Class
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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class updateAdmin {

    private MongoCollection<Document> mongoCollection = null;
    private MongoClient mongoClient = null;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="adminOperationsArea"
    private AnchorPane adminOperationsArea; // Value injected by FXMLLoader

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

    @FXML // fx:id="lastName"
    private JFXTextField lastName; // Value injected by FXMLLoader

    @FXML // fx:id="javaCheckbox"
    private JFXCheckBox javaCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="ppaCheckbox"
    private JFXCheckBox ppaCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="pyCheckbox"
    private JFXCheckBox pyCheckbox; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private JFXTextField username; // Value injected by FXMLLoader

    @FXML // fx:id="primaryNumber"
    private JFXTextField primaryNumber; // Value injected by FXMLLoader

    @FXML // fx:id="whatsappNumber"
    private JFXTextField whatsappNumber; // Value injected by FXMLLoader

    @FXML // fx:id="email"
    private JFXTextField email; // Value injected by FXMLLoader

    @FXML // fx:id="batchSelector"
    private JFXComboBox<String> batchSelector; // Value injected by FXMLLoader

    @FXML // fx:id="updateAdmin"
    private JFXButton updateAdmin; // Value injected by FXMLLoader

    @FXML // fx:id="searchBar"
    private JFXTextField searchBar; // Value injected by FXMLLoader

    @FXML // fx:id="filterSelector"
    private JFXComboBox<String> filterSelector; // Value injected by FXMLLoader

    @FXML // fx:id="searchBtn"
    private JFXButton searchBtn; // Value injected by FXMLLoader

    @FXML
    void addNewStudentBtnPressed(MouseEvent event) {

        if (!filterSelector.getValue().isEmpty() && !searchBar.getText().isEmpty()) {

            if (!firstName.getText().isEmpty()  && !lastName.getText().isEmpty() &&
                    !email.getText().isEmpty() && !primaryNumber.getText().isEmpty() && !whatsappNumber.getText().isEmpty() &&
                    !username.getText().isEmpty()) {

                if (isValidPhoneNumber(primaryNumber.getText())) {

                    if (isValidPhoneNumber(whatsappNumber.getText())) {


                        if (isValidEmail(email.getText())) {



                                if (javaCheckbox.isSelected() || ppaCheckbox.isSelected() || pyCheckbox.isSelected()) {

                                        //Insert Data
                                    Document document = new Document("firstName", firstName.getText());
                                    document.append("lastName", lastName.getText());
                                    document.append("email", email.getText());
                                    document.append("username", username.getText());
                                    document.append("primaryPhoneNumber", primaryNumber.getText());
                                    document.append("whatsappPhoneNumber", whatsappNumber.getText());

                                    // check for weather either one of three checkboxes is clicked and checked
                                    if (ppaCheckbox.isSelected())
                                        document.append("batch", "PPA");
                                    if (javaCheckbox.isSelected())
                                        document.append("batch", "JAVA");
                                    if (pyCheckbox.isSelected())
                                        document.append("batch", "PYTHON");

                                    if (filterSelector.getValue().equals("email"))
                                        mongoCollection.updateOne(Filters.eq("email",email.getText()),new Document("$set",new Document(document)));
                                    else
                                        mongoCollection.updateOne(Filters.eq("username",username.getText()),new Document("$set",new Document(document)));


                                    // Clear the text once the user account is created
                                    firstName.setText("");
                                    lastName.setText("");
                                    email.setText("");
                                    username.setText("");
                                    primaryNumber.setText("");
                                    whatsappNumber.setText("");
                                    ppaCheckbox.setSelected(false);
                                    javaCheckbox.setSelected(false);
                                    pyCheckbox.setSelected(false);

                                    dialogDisplay(stackPane, "User Account updated successfully ...");



                                } else {
                                    // pop up please select one Year
                                    dialogDisplay(stackPane, "Please select one batch checkbox please ...");
                                    javaCheckbox.requestFocus();
                                }


                        } else {
                            // pop up invalid email
                            email.setPromptText("Email");
                            email.setPromptText("* Email");
                            dialogDisplay(stackPane, "Enter a valid Email id please ...");
                            email.requestFocus();
                        }
                    } else {
                        // pop up invalid phone number
                        whatsappNumber.setPromptText("whatsapp no.");
                        whatsappNumber.setPromptText("* whatsapp no.");
                        dialogDisplay(stackPane, "Enter a valid whatsapp number ...");
                        whatsappNumber.requestFocus();
                    }


                } else {
                    // pop up invalid phone number
                    primaryNumber.setPromptText("Primary no.");
                    primaryNumber.setPromptText("* Primary no.");
                    dialogDisplay(stackPane, "Enter a valid primary number ...");
                    primaryNumber.requestFocus();
                }


            }else{


                // Pop Up that fill all feild with Red Marks ...
                firstName.setPromptText("* First name");
                lastName.setPromptText("* Last name");
                primaryNumber.setPromptText("* Primary no.");
                whatsappNumber.setPromptText("* Whatsapp no.");
                email.setPromptText("* Email");
                username.setPromptText("* Username");

                ppaCheckbox.setTextFill(Paint.valueOf("#FE0312"));
                javaCheckbox.setTextFill(Paint.valueOf("#FE0312"));
                pyCheckbox.setTextFill(Paint.valueOf("#FE0312"));



                dialogDisplay(stackPane, "Fill all Fields marked as '*'");
            }

        }else{
            dialogDisplay(stackPane, "Cannot update a value that is never searched for\nNote: You can try adding a new Student from Student Details tab ...");
        }

    }

    @FXML
    void backBtnPressed(MouseEvent event) throws IOException {

        AnchorPane fxml = FXMLLoader.load(getClass().getResource("../views/AdminDetails.fxml"));
        adminOperationsArea.getChildren().removeAll();
        adminOperationsArea.getChildren().setAll(fxml);

    }

    @FXML
    void closeApp(MouseEvent event) {
        System.exit(0);

    }

    @FXML
    void searchBtnPressed(MouseEvent event) {

        String searchQuery = searchBar.getText();
        String filter = filterSelector.getValue();

        if (filterSelector.getValue() != null && !filterSelector.getValue().isEmpty()) {

            if (!searchBar.getText().isEmpty()) {

                if (filter.equals("username")) {

                    FindIterable<Document> fi = mongoCollection.find(Filters.eq("username", searchQuery));
                    MongoCursor<Document> cursor = fi.iterator();

                    if (!cursor.hasNext())
                        dialogDisplay(stackPane,"No such entry found in the Student Database ...");

                    while (cursor.hasNext()) {

                        Document document = cursor.next();

                        String fname = document.getString("firstName");
                        String lname = document.getString("lastName");
                        String emaill = document.getString("email");
                        String uname = document.getString("username");
                        String pno = document.getString("primaryPhoneNumber");
                        String wno = document.getString("whatsappPhoneNumber");
                        String previousBatch = document.getString("batch");

                        firstName.setText(fname);
                        lastName.setText(lname);
                        primaryNumber.setText(pno);
                        email.setText(emaill);
                        whatsappNumber.setText(wno);
                        username.setText(uname);



                    }


                }

                if (filter.equals("email")) {
                    if (isValidEmail(searchBar.getText())) {

                        FindIterable<Document> fi = mongoCollection.find(Filters.eq("email", searchQuery));
                        MongoCursor<Document> cursor = fi.iterator();

                        if (!cursor.hasNext())
                            dialogDisplay(stackPane, "No such entry found in the Student Database ...");

                        while (cursor.hasNext()) {

                            Document document = cursor.next();

                            String fname = document.getString("firstName");
                            String lname = document.getString("lastName");
                            String emaill = document.getString("email");
                            String uname = document.getString("username");
                            String pno = document.getString("primaryPhoneNumber");
                            String wno = document.getString("whatsappPhoneNumber");
                            String previousBatch = document.getString("batch");

                            firstName.setText(fname);
                            lastName.setText(lname);
                            primaryNumber.setText(pno);
                            email.setText(emaill);
                            whatsappNumber.setText(wno);
                            username.setText(uname);

                            if (previousBatch.equals("JAVA"))
                                javaCheckbox.setSelected(true);

                            if (previousBatch.equals("PPA"))
                                ppaCheckbox.setSelected(true);

                            if (previousBatch.equals("PYTHON"))
                                pyCheckbox.setSelected(true);

                        }

                    }else {
                        dialogDisplay(stackPane, "Please enter a valid email to search ...");
                        searchBar.requestFocus();


                    }
                }

            } else {
                // Pop up of search query empty
                dialogDisplay(stackPane,"Please enter text is Search bar ...");
                searchBar.requestFocus();
            }

        }else{
            // Pop up of Fliter not selected
            dialogDisplay(stackPane,"Please select one Filter to search ...");
            filterSelector.requestFocus();
        }


    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert stackPane != null : "fx:id=\"stackPane\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert contentLoader != null : "fx:id=\"contentLoader\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert closeBtn != null : "fx:id=\"closeBtn\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert firstName != null : "fx:id=\"firstName\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert lastName != null : "fx:id=\"lastName\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert primaryNumber != null : "fx:id=\"primaryNumber\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert whatsappNumber != null : "fx:id=\"whatsappNumber\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert batchSelector != null : "fx:id=\"batchSelector\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert updateAdmin != null : "fx:id=\"updateAdmin\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert searchBar != null : "fx:id=\"searchBar\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert filterSelector != null : "fx:id=\"filterSelector\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert javaCheckbox != null : "fx:id=\"javaCheckbox\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert ppaCheckbox != null : "fx:id=\"ppaCheckbox\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert pyCheckbox != null : "fx:id=\"pyCheckbox\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";
        assert searchBtn != null : "fx:id=\"searchBtn\" was not injected: check your FXML file 'UpdateAdmin.fxml'.";

        connectToDatabase();


        filterSelector.getItems().add("username");
        filterSelector.getItems().add("email");

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

    /**
     * Method to check weather the entered Email is valid or not
     * This is done by checking weather the String contains '@','.' etc ..
     *
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
