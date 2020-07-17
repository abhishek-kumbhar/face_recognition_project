/**
 * Sample Skeleton for 'AddNewStudent.fxml' Controller Class
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import org.bson.Document;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewStudent {

    // Instance variables so all methods in this class can have access .. to mongoDB operations
    // once the connection is established by the call to {@connectToDatabase}
    private MongoCollection<Document> mongoCollection = null;
    private MongoClient mongoClient = null;
    public static int studentID;
    public static int totalStudentCount = 1;
    public static String finalStudId = null;
    public static String selectedBatch = null;
    public static String studentName = null;
    public static String studentEmail = null;
    public static String studentNumber = null;


    //file handling for python code



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

    @FXML
    private JFXComboBox<String> batchSelector;

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

    public AddNewStudent() throws IOException {
    }

    @FXML
    void addNewStudentBtnPressed(MouseEvent event) throws IOException {

        DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");


        // check weather all feilds are entered or not ..
        if (!firstName.getText().isEmpty() && !middleName.getText().isEmpty() && !lastName.getText().isEmpty() &&
                !email.getText().isEmpty() && !primaryNumber.getText().isEmpty() && !whatsappNumber.getText().isEmpty() &&
                !dob.getValue().toString().isEmpty() && !flatNumber.getText().isEmpty() &&
                !road.getText().isEmpty() && !area.getText().isEmpty() && !city.getText().isEmpty() &&
                !pincode.getText().isEmpty() && !collegeName.getText().isEmpty() && !collegeArea.getText().isEmpty() &&
                !branch.getText().isEmpty()) {


            if (isValidPhoneNumber(primaryNumber.getText())) {

                if (isValidPhoneNumber(whatsappNumber.getText())) {


                    if (isValidEmail(email.getText())) {

                        if (isValidPincode(pincode.getText())) {

                            if (feCheckbox.isSelected() || seCheckbox.isSelected() || teCheckbox.isSelected()
                                    || beCheckbox.isSelected()) {

                                if (websiteCheckbox.isSelected() || friendCheckbox.isSelected() || directCheckbox.isSelected()) {

                                    if (friendCheckbox.isSelected() && referalFriendName.getText().isEmpty()){
                                        // pop up please select at least one referral
                                        dialogDisplay(stackPane, "Please enter referral friend name ...");
                                        referalFriendName.requestFocus();
                                    }

                                     //Insert Data
                                    Document identityDocument = new Document("firstName", firstName.getText());
                                    identityDocument.append("middleName", middleName.getText());
                                    identityDocument.append("lastName", lastName.getText());
                                    identityDocument.append("dob",dob.getValue().toString());
                                    identityDocument.append("age",calculateExactAge(dob.getValue().toString()));

                                    //System.out.println(dob.getValue() + " " + dob.getValue().toString());

                                    Document contactInfoDocument = new Document("primaryNumber", primaryNumber.getText());
                                    contactInfoDocument.append("whatsappNumber", whatsappNumber.getText());
                                    contactInfoDocument.append("email", email.getText());

                                    Document addressDetails = new Document("houseNo",flatNumber.getText());
                                    addressDetails.append("road",road.getText());
                                    addressDetails.append("area",area.getText());
                                    addressDetails.append("city",city.getText());
                                    addressDetails.append("pincode",pincode.getText());

                                    Document collegeDetails = new Document("collegeName", collegeName.getText());
                                    collegeDetails.append("branch", branch.getText());
                                    if (feCheckbox.isSelected())
                                        collegeDetails.append("pursuingYear", "FE");
                                    else if (seCheckbox.isSelected())
                                        collegeDetails.append("pursuingYear", "SE");
                                    else if (teCheckbox.isSelected())
                                        collegeDetails.append("pursuingYear", "TE");
                                    else
                                        collegeDetails.append("pursuingYear", "BE");


                                    Document document = new Document("identity", identityDocument);
                                    document.append("contact_details", contactInfoDocument);
                                    document.append("college_details", collegeDetails);
                                    document.append("address_details",addressDetails);

                                    if (websiteCheckbox.isSelected())
                                        document.append("reference", "c2w-website");
                                    else if (friendCheckbox.isSelected())
                                        document.append("reference", referalFriendName.getText());
                                    else
                                        document.append("reference", "direct");

                                    //reading prev id given by c2w
                                    String data = null;
                                    try {
                                        File myObj = new File("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\" + batchSelector.getValue() + "\\prevId.txt");
                                        Scanner myReader = new Scanner(myObj);
                                        while (myReader.hasNextLine()) {
                                            data = myReader.nextLine();
                                        }
                                        myReader.close();
                                    } catch (FileNotFoundException e) {
                                        System.out.println("An error occurred.");
                                        e.printStackTrace();
                                    }

                                    System.out.println(data);

                                    //writing new id in the file
                                    FileWriter fw=new FileWriter("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\" + batchSelector.getValue() + "\\prevId.txt");
                                    int k = Integer.valueOf(data);
                                    k = k + 1;
                                    String str = Integer.toString(k);
                                    fw.write(str);
                                    fw.close();
                                    System.out.println(k);

                                    studentID = k - 1;
                                    selectedBatch = batchSelector.getValue();
                                    studentName = firstName.getText() + " " + lastName.getText();
                                    studentEmail = email.getText();
                                    studentNumber = primaryNumber.getText();


                                    finalStudId = batchSelector.getValue() + "_" + Integer.toString(studentID);
                                    System.out.println("finalStud id = " + finalStudId);


                                    //write id's to another file for python execution
                                    BufferedWriter bw = null;
                                    FileWriter f = null;

                                    try {
                                        File file = new File ("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\" + batchSelector.getValue() + "\\allIds.txt");
                                        f = new FileWriter(file.getAbsoluteFile(), true);
                                        bw = new BufferedWriter(f);
                                        bw.write(batchSelector.getValue() + "_" + Integer.toString(studentID) + "\n");
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } finally {
                                        try {
                                            if (bw != null)
                                                bw.close();
                                            if (f != null)
                                                f.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }


                                    //document.append("studentID",batchSelector.getValue() + "-"+ studentID);
                                    document.append("studentID", finalStudId);
                                    document.append("entryAddedBy",Controller.adminFirstName + " " + Controller.adminLastName);

                                    mongoCollection.insertOne(document);

                                    dialogDisplay(stackPane, "Student has been added Successfully ...");

                                    Pane fxml = FXMLLoader.load(getClass().getResource("../views/WebcamControls.fxml"));
                                    adminOperationsArea.getChildren().removeAll();
                                    adminOperationsArea.getChildren().setAll(fxml);

                                    webcamControls.rollno = batchSelector.getValue()+"-"+studentID;

//                                    if (batchSelector.getValue().equals("C2W-JAVA-8"))
//                                        javaBufferedWriter.write(batchSelector.getValue()+"-"+studentID);
//                                    else if (batchSelector.getValue().equals("C2W-PPA-5"))
//                                        ppaBufferedWriter.write(batchSelector.getValue()+"-"+studentID);
//                                    else if (batchSelector.getValue().equals("C2W-PYTHON-1-MORN"))
//                                        pymorningBufferedWriter.write(batchSelector.getValue()+"-"+studentID);
//                                    else
//                                        pyweekendBufferedWriter.write(batchSelector.getValue()+"-"+studentID);
//
//                                    javaBufferedWriter.close();
//                                    ppaBufferedWriter.close();
//                                    pyweekendBufferedWriter.close();
//                                    pymorningBufferedWriter.close();
//
//                                    ppafileWriter.close();
//                                    javafileWriter.close();
//                                    pymorningWriter.close();
//                                    pyweekendWriter.close();
//


                                    //studentID++;
                                    totalStudentCount++;


                                } else {
                                    // pop up please select at least one referral
                                    dialogDisplay(stackPane, "Please select one referral checkbox ...");
                                    websiteCheckbox.requestFocus();
                                }




                            } else {
                                // pop up please select one Year
                                dialogDisplay(stackPane, "Please select one pursuing year checkbox please ...");
                                feCheckbox.requestFocus();
                            }

                        } else {
                            // pop up invalid PinCode
                            pincode.setPromptText("Pincode");
                            pincode.setPromptText("* Pincode");
                            dialogDisplay(stackPane, "Enter a valid Indian 6 Digit pincode please ...");
                            pincode.requestFocus();
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

        } else {

            // Pop Up that fill all feild with Red Marks ...
            firstName.setPromptText("* First name");
            middleName.setPromptText("* Middle name");
            lastName.setPromptText("* Last name");

            dob.setPromptText("* Date of birth");
            primaryNumber.setPromptText("* Primary no.");
            whatsappNumber.setPromptText("* Whatsapp no.");
            email.setPromptText("* Email");

            flatNumber.setPromptText("* House/Flat no.");
            road.setPromptText("* Road");
            area.setPromptText("* Area");
            city.setPromptText("* City/Taluka");
            pincode.setPromptText("* Pincode");

            collegeName.setPromptText("* College name");
            branch.setPromptText("* Branch");
            collegeArea.setPromptText("* College area");

            feCheckbox.setTextFill(Paint.valueOf("#FE0312"));
            seCheckbox.setTextFill(Paint.valueOf("#FE0312"));
            feCheckbox.setTextFill(Paint.valueOf("#FE0312"));
            beCheckbox.setTextFill(Paint.valueOf("#FE0312"));

            websiteCheckbox.setTextFill(Paint.valueOf("#FE0312"));
            friendCheckbox.setTextFill(Paint.valueOf("#FE0312"));
            directCheckbox.setTextFill(Paint.valueOf("#FE0312"));


            dialogDisplay(stackPane, "Fill all Fields marked as '*'");


        }

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

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
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
        assert flatNumber != null : "fx:id=\"flatNumber\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert road != null : "fx:id=\"road\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert area != null : "fx:id=\"area\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert city != null : "fx:id=\"city\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
        assert batchSelector != null : "fx:id=\"batchSelector\" was not injected: check your FXML file 'AddNewStudent.fxml'.";
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

        connectToDatabase();
        batchSelector.getItems().add("C2W_PPA5");
        batchSelector.getItems().add("C2W_JAVA9");
        batchSelector.getItems().add("C2W_PYTHON_MORN");
        batchSelector.getItems().add("C2W_PYTHON_EVEN");


    }

    private void connectToDatabase() {

        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb://ajagundeomkar:VViratkoli%40%4011@core2webattendence-shard-00-00-m7irc.mongodb.net:27017,core2webattendence-shard-00-01-m7irc.mongodb.net:27017,core2webattendence-shard-00-02-m7irc.mongodb.net:27017/test?ssl=true&replicaSet=Core2WebAttendence-shard-0&authSource=admin&retryWrites=true&w=majority");

            mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase("core2webAttendenceApplication");
            mongoCollection = database.getCollection("student_details");
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

    private boolean isValidPincode(String s) {

        String regex = "^[0-9]{6}?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches())
            return true;
        else
            return false;
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
            if (firstName.getText().isEmpty())
                firstName.requestFocus();
            else if (middleName.getText().isEmpty())
                middleName.requestFocus();
            else if (lastName.getText().isEmpty())
                lastName.requestFocus();
            else if (dob.getValue().toString().isEmpty())
                dob.requestFocus();
            else if (primaryNumber.getText().isEmpty())
                primaryNumber.requestFocus();
            else if (whatsappNumber.getText().isEmpty())
                whatsappNumber.requestFocus();
            else if (email.getText().isEmpty())
                email.requestFocus();
            else if (flatNumber.getText().isEmpty())
                flatNumber.requestFocus();
            else if (road.getText().isEmpty())
                road.requestFocus();
            else if (area.getText().isEmpty())
                area.requestFocus();
            else if (city.getText().isEmpty())
                city.requestFocus();
            else if (pincode.getText().isEmpty())
                pincode.requestFocus();
            else if (collegeName.getText().isEmpty())
                collegeName.requestFocus();
            else if (branch.getText().isEmpty())
                branch.requestFocus();
            else if (collegeArea.getText().isEmpty())
                collegeArea.requestFocus();
            else if (!feCheckbox.isSelected())
                feCheckbox.requestFocus();
            else if (!seCheckbox.isSelected())
                seCheckbox.requestFocus();
            else if (!teCheckbox.isSelected())
                teCheckbox.requestFocus();
            else if (!beCheckbox.isSelected())
                beCheckbox.requestFocus();
            else if (!websiteCheckbox.isSelected())
                websiteCheckbox.requestFocus();
            else if (!friendCheckbox.isSelected())
                friendCheckbox.requestFocus();
            else
                directCheckbox.requestFocus();

        });

        dialogLayout.setBody(new Text(bodyMessage));
        dialogLayout.setActions(button);
        dialogL.show();
        dialogL.setOnDialogClosed((JFXDialogEvent mEvent) -> {

            contentLoader.setEffect(null);
        });
        contentLoader.setEffect(blur);
    }

    private String calculateExactAge(String dob){

        LocalDate today = LocalDate.now();                          //Today's date
        StringTokenizer st = new StringTokenizer(dob,"-");
        String year = st.nextToken();
        String month = st.nextToken();
        String day = st.nextToken();

        LocalDate birthday = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));  //Birth date
        Period p = Period.between(birthday, today);

        //return p.getYears() + " yrs " + p.getMonths() + " months " + p.getDays() + " days ";
        return Integer.toString(p.getYears());
    }

}
