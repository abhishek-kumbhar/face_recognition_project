/**
 * Sample Skeleton for 'ViewStudents.fxml' Controller Class
 */

package controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.bson.BSONObject;
import org.bson.Document;

import javax.print.Doc;

public class ViewStudents {

    private MongoCollection<Document> mongoCollection = null;
    private MongoClient mongoClient = null;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="adminOperationsArea"
    private AnchorPane adminOperationsArea; // Value injected by FXMLLoader

    @FXML // fx:id="searchInput"
    private JFXTextField searchInput; // Value injected by FXMLLoader


    @FXML // fx:id="studentTableView"
    private JFXTreeTableView<StudentTableInfo> studentTableView; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'ViewStudents.fxml'.";
        assert studentTableView != null : "fx:id=\"studentTableView\" was not injected: check your FXML file 'ViewStudents.fxml'.";
        assert searchInput != null : "fx:id=\"searchInput\" was not injected: check your FXML file 'ViewStudents.fxml'.";


        JFXTreeTableColumn<StudentTableInfo,String> studentID = new JFXTreeTableColumn<>("Student ID");
        studentID.setPrefWidth(150);
        studentID.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().StudentID;

        });

        JFXTreeTableColumn<StudentTableInfo,String> firstName = new JFXTreeTableColumn<>("First name");
        firstName.setPrefWidth(100);
        firstName.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().firstName;

        });

        JFXTreeTableColumn<StudentTableInfo,String> middleName = new JFXTreeTableColumn<>("Middle name");
        middleName.setPrefWidth(100);
        middleName.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().middleName;

        });

        JFXTreeTableColumn<StudentTableInfo,String> lastName = new JFXTreeTableColumn<>("Last name");
        lastName.setPrefWidth(100);
        lastName.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().lastName;

        });

        JFXTreeTableColumn<StudentTableInfo,String> age = new JFXTreeTableColumn<>("Age");
        age.setPrefWidth(100);
        age.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().age;

        });

        JFXTreeTableColumn<StudentTableInfo,String> dob = new JFXTreeTableColumn<>("Date of Birth");
        dob.setPrefWidth(100);
        dob.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().dob;

        });

        JFXTreeTableColumn<StudentTableInfo,String> primaryNumber = new JFXTreeTableColumn<>("Primary no.");
        primaryNumber.setPrefWidth(100);
        primaryNumber.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().primaryNumber;

        });

        JFXTreeTableColumn<StudentTableInfo,String> email = new JFXTreeTableColumn<>("Email ID");
        email.setPrefWidth(220);
        email.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().email;

        });

        JFXTreeTableColumn<StudentTableInfo,String> whatsappNumber = new JFXTreeTableColumn<>("Whatsapp no.");
        whatsappNumber.setPrefWidth(100);
        whatsappNumber.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().whatsappNumber;

        });

        JFXTreeTableColumn<StudentTableInfo,String> address = new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(300);
        address.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().address;

        });

        JFXTreeTableColumn<StudentTableInfo,String> collegeName = new JFXTreeTableColumn<>("College name");
        collegeName.setPrefWidth(100);
        collegeName.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().collegeName;

        });

        JFXTreeTableColumn<StudentTableInfo,String> branch = new JFXTreeTableColumn<>("branch");
        branch.setPrefWidth(60);
        branch.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().branch;

        });



        JFXTreeTableColumn<StudentTableInfo,String> refferedBy = new JFXTreeTableColumn<>("referral By");
        refferedBy.setPrefWidth(200);
        refferedBy.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().referredBy;

        });

        JFXTreeTableColumn<StudentTableInfo,String> entryAddedBy = new JFXTreeTableColumn<>("Entry added by");
        entryAddedBy.setPrefWidth(100);
        entryAddedBy.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<StudentTableInfo, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().addedBy;

        });

        ObservableList<StudentTableInfo> studentInfo = FXCollections.observableArrayList();
        connectToDatabase();

        //MongoCursor<Document> cursor = mongoCollection.find().iterator();

        FindIterable<Document> fi = mongoCollection.find();
        MongoCursor<Document> cursor = fi.iterator();






        while(cursor.hasNext()){

            Document document =  cursor.next();
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


            studentInfo.add(new StudentTableInfo(localVariableStudentID,localVariableFirstName,localVariableMiddleName,
                    localVariableLastName,localVariableDOB,localVariableAge,localVariablePrimaryNUmber,localVariableEmail,localVariableWhatsappNumber,
                    localVariableFlatNumber,localVariableRoad,localVariableArea,localVariableCity,localVariablePincode,
                    localVariableCollegeName,localVariableBranch,localVariablePursuingYear,localVariableRefferedBy,
                    localEntryAddedBy));

            //Build Tree
            final TreeItem<StudentTableInfo> root = new RecursiveTreeItem<StudentTableInfo>(studentInfo, RecursiveTreeObject::getChildren);
            studentTableView.getColumns().setAll(studentID, firstName,middleName,lastName,age,dob,primaryNumber,email,
                    whatsappNumber,address,collegeName,branch,refferedBy,entryAddedBy);
            studentTableView.setRoot(root);
            studentTableView.setShowRoot(false);

            searchInput.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    studentTableView.setPredicate(new Predicate<TreeItem<StudentTableInfo>>() {
                        @Override
                        public boolean test(TreeItem<StudentTableInfo> studentTableInfoTreeItem) {
                            boolean flag =studentTableInfoTreeItem.getValue().StudentID.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().firstName.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().middleName.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().lastName.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().dob.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().age.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().primaryNumber.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().email.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().whatsappNumber.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().address.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().collegeName.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().branch.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().pursuingYear.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().addedBy.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().referredBy.getValue().contains(newValue)
                                    ;
                            return flag;
                        }
                    });
                }
            });


        }


    }

    private void connectToDatabase() {

        try {
            MongoClientURI uri = new MongoClientURI(
                    "mongodb://ajagundeomkar:VViratkoli%40%4011@core2webattendence-shard-00-00-m7irc.mongodb.net:27017,core2webattendence-shard-00-01-m7irc.mongodb.net:27017,core2webattendence-shard-00-02-m7irc.mongodb.net:27017/test?ssl=true&replicaSet=Core2WebAttendence-shard-0&authSource=admin&retryWrites=true&w=majority");

            mongoClient = new MongoClient(uri);
            MongoDatabase database = mongoClient.getDatabase("core2webAttendenceApplication");
            mongoCollection = database.getCollection("student_details");
            System.out.println("yes mongoCollection not null");
        } catch (MongoException e) {
            System.out.println("Failed To Connect");
        }
    }
}
