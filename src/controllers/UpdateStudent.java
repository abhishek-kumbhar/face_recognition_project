/**
 * Sample Skeleton for 'AddNewStudent.fxml' Controller Class
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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateStudent {

    // Instance variables so all methods in this class can have access .. to mongoDB operations
    // once the connection is established by the call to {@connectToDatabase}
    private MongoCollection<Document> mongoCollection = null;
    private MongoClient mongoClient = null;
    private int studentID = 1;
    public static int totalStudentCount = 1;
    private String localVariableStudentID;
    private String localVariablePrimaryNUmber;
    private String localVariableEmail;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML
    private AnchorPane adminOperationsArea;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="stackPane"
    private StackPane stackPane; // Value injected by FXMLLoader


    @FXML // fx:id="searchBtn"
    private JFXButton searchBtn; // Value injected by FXMLLoader


    @FXML // fx:id="contentLoader"
    private Pane contentLoader; // Value injected by FXMLLoader


    @FXML // fx:id="searchBar"
    private JFXTextField searchBar; // Value injected by FXMLLoader

    @FXML // fx:id="filterSelector"
    private JFXComboBox<String> filterSelector; // Value injected by FXMLLoader


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

    @FXML
    void addNewStudentBtnPressed(MouseEvent event) {



        if (!filterSelector.getValue().isEmpty() && !searchBar.getText().isEmpty()) {
            // check weather all feilds are entered or not ..
            if (!firstName.getText().isEmpty() && !middleName.getText().isEmpty() && !lastName.getText().isEmpty() &&
                    !email.getText().isEmpty() && !primaryNumber.getText().isEmpty() && !whatsappNumber.getText().isEmpty() &&
                    !dob.getValue().toString().isEmpty() && !flatNumber.getText().isEmpty() &&
                    !road.getText().isEmpty() && !area.getText().isEmpty() && !city.getText().isEmpty() &&
                    !pincode.getText().isEmpty() && !collegeName.getText().isEmpty() &&
                    !branch.getText().isEmpty()) {


                if (isValidPhoneNumber(primaryNumber.getText())) {

                    if (isValidPhoneNumber(whatsappNumber.getText())) {


                        if (isValidEmail(email.getText())) {

                            if (isValidPincode(pincode.getText())) {

                                if (feCheckbox.isSelected() || seCheckbox.isSelected() || teCheckbox.isSelected()
                                        || beCheckbox.isSelected()) {

                                    if (websiteCheckbox.isSelected() || friendCheckbox.isSelected() || directCheckbox.isSelected()) {

                                        if (friendCheckbox.isSelected() && referalFriendName.getText().isEmpty()) {
                                            // pop up please select at least one referral
                                            dialogDisplay(stackPane, "Please enter referral friend name ...");
                                            referalFriendName.requestFocus();
                                        }

                                        //Insert Data
                                        Document identityDocument = new Document("firstName", firstName.getText());
                                        identityDocument.append("middleName", middleName.getText());
                                        identityDocument.append("lastName", lastName.getText());
                                        identityDocument.append("dob", dob.getValue().toString());
                                        identityDocument.append("age", calculateExactAge(dob.getValue().toString()));

                                        System.out.println(dob.getValue() + " " + dob.getValue().toString());

                                        Document contactInfoDocument = new Document("primaryNumber", primaryNumber.getText());
                                        contactInfoDocument.append("whatsappNumber", whatsappNumber.getText());
                                        contactInfoDocument.append("email", email.getText());

                                        Document addressDetails = new Document("houseNo", flatNumber.getText());
                                        addressDetails.append("road", road.getText());
                                        addressDetails.append("area", area.getText());
                                        addressDetails.append("city", city.getText());
                                        addressDetails.append("pincode", pincode.getText());

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
                                        document.append("address_details", addressDetails);

                                        if (websiteCheckbox.isSelected())
                                            document.append("reference", "c2w-website");
                                        else if (friendCheckbox.isSelected())
                                            document.append("reference", referalFriendName.getText());
                                        else
                                            document.append("reference", "direct");

                                        document.append("studentID", batchSelector.getValue() + "-" + studentID);
                                        document.append("entryAddedBy", Controller.adminFirstName + " " + Controller.adminLastName);

                                        if (filterSelector.getValue().equals("email"))
                                        mongoCollection.updateOne(Filters.eq("email",localVariableEmail),new Document("$set",new Document(document)));
                                        else if (filterSelector.getValue().equals("studentID"))
                                            mongoCollection.updateOne(Filters.eq("studentID",localVariableStudentID),new Document("$set",new Document(document)));
                                        else
                                            mongoCollection.updateOne(Filters.eq("primaryNumber",localVariableEmail),new Document("$set",new Document(document)));



                                        dialogDisplay(stackPane, "Student information updated Successfully ...");
                                        studentID++;
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

        }else{
            dialogDisplay(stackPane, "Cannot update a value that is never searched for\nNote: You can try adding a new Student from Student Details tab ...");
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

        System.exit(0);

    }

    @FXML
    void searchBtnPressed(MouseEvent event) {

        String searchQuery = searchBar.getText();
        String filter = filterSelector.getValue();



        if (filterSelector.getValue() != null && !filterSelector.getValue().isEmpty()) {


            if (!searchBar.getText().isEmpty()) {

                if (filter.equals("StudentID")) {

                    FindIterable<Document> fi = mongoCollection.find(Filters.eq("studentID", searchQuery));
                    MongoCursor<Document> cursor = fi.iterator();

                    if (!cursor.hasNext())
                        dialogDisplay(stackPane,"No such entry found in the Student Database ...");

                    while (cursor.hasNext()) {

                        Document document = cursor.next();
                        Document identity = (Document) document.get("identity");
                        Document contactDetails = (Document) document.get("contact_details");
                        Document collegeDetails = (Document) document.get("college_details");
                        Document addressDetails = (Document) document.get("address_details");

                        localVariableStudentID = document.getString("studentID");
                        String localVariableFirstName = identity.getString("firstName");
                        String localVariableMiddleName = identity.getString("middleName");
                        String localVariableLastName = identity.getString("lastName");
                        String localVariableDOB = identity.getString("dob");
                        String localVariableAge = identity.getString("age");

                        localVariablePrimaryNUmber = contactDetails.getString("primaryNumber");
                        localVariableEmail = contactDetails.getString("email");
                        String localVariableWhatsappNumber = contactDetails.getString("whatsappNumber");

                        String localVariableFlatNumber = addressDetails.getString("houseNo");
                        String localVariableRoad = addressDetails.getString("road");
                        String localVariableArea = addressDetails.getString("area");
                        String localVariableCity = addressDetails.getString("city");
                        String localVariablePincode = addressDetails.getString("pincode");

                        String localVariableCollegeName = collegeDetails.getString("collegeName");
                        String localVariableBranch = collegeDetails.getString("branch");
                        String localVariablePursuingYear = collegeDetails.getString("pursuingYear");

                        String localEntryAddedBy = document.getString("entryAddedBy");
                        String localVariableRefferedBy = document.getString("reference");

                        firstName.setText(localVariableFirstName);
                        middleName.setText(localVariableMiddleName);
                        lastName.setText(localVariableLastName);

                        dob.setValue(LocalDate.parse(localVariableDOB));
                        primaryNumber.setText(localVariablePrimaryNUmber);
                        email.setText(localVariableEmail);
                        whatsappNumber.setText(localVariableWhatsappNumber);
                        flatNumber.setText(localVariableFlatNumber);
                        area.setText(localVariableArea);
                        road.setText(localVariableRoad);
                        city.setText(localVariableCity);
                        pincode.setText(localVariablePincode);

                        collegeName.setText(localVariableCollegeName);
                        branch.setText(localVariableBranch);

                        if (localVariablePursuingYear.equals("FE"))
                            feCheckbox.setSelected(true);

                        if (localVariablePursuingYear.equals("SE"))
                            seCheckbox.setSelected(true);

                        if (localVariablePursuingYear.equals("TE"))
                            teCheckbox.setSelected(true);

                        if (localVariablePursuingYear.equals("BE"))
                            beCheckbox.setSelected(true);

                        if (localVariableRefferedBy.equals("c2w-website"))
                            websiteCheckbox.setSelected(true);
                        else if (localVariableRefferedBy.equals("direct"))
                            directCheckbox.setSelected(true);
                        else {
                            friendCheckbox.setSelected(true);
                            referalFriendName.setText(localVariableRefferedBy);
                        }

                    }

                }

                if (filter.equals("Primary no.")) {

                    if (isValidPhoneNumber(searchBar.getText())) {


                        FindIterable<Document> fi = mongoCollection.find(Filters.eq("contact_details.primaryNumber", searchQuery));
                        MongoCursor<Document> cursor = fi.iterator();

                        if (!cursor.hasNext())
                            dialogDisplay(stackPane, "No such entry found in the Student Database ...");


                        while (cursor.hasNext()) {


                            Document document = cursor.next();
                            Document identity = (Document) document.get("identity");
                            Document contactDetails = (Document) document.get("contact_details");
                            Document collegeDetails = (Document) document.get("college_details");
                            Document addressDetails = (Document) document.get("address_details");

                            String localVariableStudentID = document.getString("studentID");
                            String localVariableFirstName = identity.getString("firstName");
                            String localVariableMiddleName = identity.getString("middleName");
                            String localVariableLastName = identity.getString("lastName");
                            String localVariableDOB = identity.getString("dob");
                            String localVariableAge = identity.getString("age");

                            String localVariablePrimaryNUmber = contactDetails.getString("primaryNumber");
                            String localVariableEmail = contactDetails.getString("email");
                            String localVariableWhatsappNumber = contactDetails.getString("whatsappNumber");

                            String localVariableFlatNumber = addressDetails.getString("houseNo");
                            String localVariableRoad = addressDetails.getString("road");
                            String localVariableArea = addressDetails.getString("area");
                            String localVariableCity = addressDetails.getString("city");
                            String localVariablePincode = addressDetails.getString("pincode");

                            String localVariableCollegeName = collegeDetails.getString("collegeName");
                            String localVariableBranch = collegeDetails.getString("branch");
                            String localVariablePursuingYear = collegeDetails.getString("pursuingYear");

                            String localEntryAddedBy = document.getString("entryAddedBy");
                            String localVariableRefferedBy = document.getString("reference");

                            firstName.setText(localVariableFirstName);
                            middleName.setText(localVariableMiddleName);
                            lastName.setText(localVariableLastName);

                            dob.setValue(LocalDate.parse(localVariableDOB));
                            primaryNumber.setText(localVariablePrimaryNUmber);
                            primaryNumber.setEditable(false);
                            email.setText(localVariableEmail);
                            whatsappNumber.setText(localVariableWhatsappNumber);
                            flatNumber.setText(localVariableFlatNumber);
                            area.setText(localVariableArea);
                            road.setText(localVariableRoad);
                            city.setText(localVariableCity);
                            pincode.setText(localVariablePincode);

                            collegeName.setText(localVariableCollegeName);
                            branch.setText(localVariableBranch);

                            if (localVariablePursuingYear.equals("FE"))
                                feCheckbox.setSelected(true);

                            if (localVariablePursuingYear.equals("SE"))
                                seCheckbox.setSelected(true);

                            if (localVariablePursuingYear.equals("TE"))
                                teCheckbox.setSelected(true);

                            if (localVariablePursuingYear.equals("BE"))
                                beCheckbox.setSelected(true);

                            if (localVariableRefferedBy.equals("c2w-website"))
                                websiteCheckbox.setSelected(true);
                            else if (localVariableRefferedBy.equals("direct"))
                                directCheckbox.setSelected(true);
                            else {
                                friendCheckbox.setSelected(true);
                                referalFriendName.setText(localVariableRefferedBy);
                            }

                        }

                    } else {
                        dialogDisplay(stackPane, "Please enter a valid phone number to search ...");
                        searchBar.requestFocus();


                    }
                }

                if (filter.equals("email")) {

                    if (isValidEmail(searchBar.getText())) {


                        FindIterable<Document> fi = mongoCollection.find(Filters.eq("contact_details.email", searchQuery));
                        MongoCursor<Document> cursor = fi.iterator();

                        if (!cursor.hasNext())
                            dialogDisplay(stackPane, "No such entry found in the Student Database ...");

                        while (cursor.hasNext()) {

                            Document document = cursor.next();
                            Document identity = (Document) document.get("identity");
                            Document contactDetails = (Document) document.get("contact_details");
                            Document collegeDetails = (Document) document.get("college_details");
                            Document addressDetails = (Document) document.get("address_details");

                            String localVariableStudentID = document.getString("studentID");
                            String localVariableFirstName = identity.getString("firstName");
                            String localVariableMiddleName = identity.getString("middleName");
                            String localVariableLastName = identity.getString("lastName");
                            String localVariableDOB = identity.getString("dob");
                            String localVariableAge = identity.getString("age");

                            String localVariablePrimaryNUmber = contactDetails.getString("primaryNumber");
                            String localVariableEmail = contactDetails.getString("email");
                            String localVariableWhatsappNumber = contactDetails.getString("whatsappNumber");

                            String localVariableFlatNumber = addressDetails.getString("houseNo");
                            String localVariableRoad = addressDetails.getString("road");
                            String localVariableArea = addressDetails.getString("area");
                            String localVariableCity = addressDetails.getString("city");
                            String localVariablePincode = addressDetails.getString("pincode");

                            String localVariableCollegeName = collegeDetails.getString("collegeName");
                            String localVariableBranch = collegeDetails.getString("branch");
                            String localVariablePursuingYear = collegeDetails.getString("pursuingYear");

                            String localEntryAddedBy = document.getString("entryAddedBy");
                            String localVariableRefferedBy = document.getString("reference");

                            firstName.setText(localVariableFirstName);
                            middleName.setText(localVariableMiddleName);
                            lastName.setText(localVariableLastName);

                            dob.setValue(LocalDate.parse(localVariableDOB));
                            primaryNumber.setText(localVariablePrimaryNUmber);
                            email.setText(localVariableEmail);
                            email.setEditable(false);
                            whatsappNumber.setText(localVariableWhatsappNumber);
                            flatNumber.setText(localVariableFlatNumber);
                            area.setText(localVariableArea);
                            road.setText(localVariableRoad);
                            city.setText(localVariableCity);
                            pincode.setText(localVariablePincode);

                            collegeName.setText(localVariableCollegeName);
                            branch.setText(localVariableBranch);

                            if (localVariablePursuingYear.equals("FE"))
                                feCheckbox.setSelected(true);

                            if (localVariablePursuingYear.equals("SE"))
                                seCheckbox.setSelected(true);

                            if (localVariablePursuingYear.equals("TE"))
                                teCheckbox.setSelected(true);

                            if (localVariablePursuingYear.equals("BE"))
                                beCheckbox.setSelected(true);

                            if (localVariableRefferedBy.equals("c2w-website"))
                                websiteCheckbox.setSelected(true);
                            else if (localVariableRefferedBy.equals("direct"))
                                directCheckbox.setSelected(true);
                            else {
                                friendCheckbox.setSelected(true);
                                referalFriendName.setText(localVariableRefferedBy);
                            }

                        }

                    } else {
                        dialogDisplay(stackPane, "Enter a Valid Email to search ...");
                        searchBar.requestFocus();
                    }
                }


            } else {
                // Pop up of search query empty
                dialogDisplay(stackPane,"Please enter text is Search bar ...");
                searchBar.requestFocus();
            }

        } else {
            // Pop up of Fliter not selected
            dialogDisplay(stackPane,"Please select one Filter to search ...");
            filterSelector.requestFocus();
        }

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
        assert searchBar != null : "fx:id=\"searchBar\" was not injected: check your FXML file 'UpdateStudent.fxml'.";
        assert filterSelector != null : "fx:id=\"filterSelector\" was not injected: check your FXML file 'UpdateStudent.fxml'.";
        assert searchBtn != null : "fx:id=\"searchBtn\" was not injected: check your FXML file 'UpdateStudent.fxml'.";


        connectToDatabase();
        batchSelector.getItems().add("C2W-JAVA-8");
        batchSelector.getItems().add("C2W-PPA-5");
        batchSelector.getItems().add("C2w-PYTHON-1");

        filterSelector.getItems().add("StudentID");
        filterSelector.getItems().add("Primary no.");
        filterSelector.getItems().add("email");


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


        });

        dialogLayout.setBody(new Text(bodyMessage));
        dialogLayout.setActions(button);
        dialogL.show();
        dialogL.setOnDialogClosed((JFXDialogEvent mEvent) -> {

            contentLoader.setEffect(null);
        });
        contentLoader.setEffect(blur);
    }

    private String calculateExactAge(String dob) {

        LocalDate today = LocalDate.now();                          //Today's date
        StringTokenizer st = new StringTokenizer(dob, "-");
        String year = st.nextToken();
        String month = st.nextToken();
        String day = st.nextToken();

        LocalDate birthday = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));  //Birth date
        Period p = Period.between(birthday, today);

        return p.getYears() + " yrs " + p.getMonths() + " months " + p.getDays() + " days ";
    }

}
