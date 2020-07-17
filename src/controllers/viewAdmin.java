/**
 * Sample Skeleton for 'ViewStudents.fxml' Controller Class
 */

package controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class viewAdmin {

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
    private JFXTreeTableView<AdminTableShow> studentTableView; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'ViewStudents.fxml'.";
        assert studentTableView != null : "fx:id=\"studentTableView\" was not injected: check your FXML file 'ViewStudents.fxml'.";
        assert searchInput != null : "fx:id=\"searchInput\" was not injected: check your FXML file 'ViewStudents.fxml'.";


        JFXTreeTableColumn<AdminTableShow,String> username = new JFXTreeTableColumn<>("Username");
        username.setPrefWidth(150);
        username.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<AdminTableShow, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().uname;

        });

        JFXTreeTableColumn<AdminTableShow,String> firstName = new JFXTreeTableColumn<>("first name");
        firstName.setPrefWidth(150);
        firstName.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<AdminTableShow, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().fname;

        });

        JFXTreeTableColumn<AdminTableShow,String> lastName = new JFXTreeTableColumn<>("last name");
        lastName.setPrefWidth(150);
        lastName.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<AdminTableShow, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().lname;

        });

        JFXTreeTableColumn<AdminTableShow,String> email = new JFXTreeTableColumn<>("email");
        email.setPrefWidth(150);
        email.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<AdminTableShow, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().email;

        });

        JFXTreeTableColumn<AdminTableShow,String> pnum = new JFXTreeTableColumn<>("primary num");
        pnum.setPrefWidth(150);
        pnum.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<AdminTableShow, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().pnumber;

        });

        JFXTreeTableColumn<AdminTableShow,String> wnum = new JFXTreeTableColumn<>("whatsapp num");
        wnum.setPrefWidth(150);
        wnum.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<AdminTableShow, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().wnumber;

        });

        JFXTreeTableColumn<AdminTableShow,String> batch = new JFXTreeTableColumn<>("batch");
        batch.setPrefWidth(150);
        batch.setCellValueFactory((Callback<TreeTableColumn.CellDataFeatures<AdminTableShow, String>, ObservableValue<String>>) param -> {

            return param.getValue().getValue().batch;

        });

        ObservableList<AdminTableShow> adminInfo = FXCollections.observableArrayList();
        connectToDatabase();

        //MongoCursor<Document> cursor = mongoCollection.find().iterator();

        FindIterable<Document> fi = mongoCollection.find();
        MongoCursor<Document> cursor = fi.iterator();






        while(cursor.hasNext()){

            Document document = cursor.next();

            String fname = document.getString("firstName");
            String lname = document.getString("lastName");
            String emaill = document.getString("email");
            String uname = document.getString("username");
            String pno = document.getString("primaryPhoneNumber");
            String wno = document.getString("whatsappPhoneNumber");
            String previousBatch = document.getString("batch");

            adminInfo.add(new AdminTableShow(uname,fname,lname,emaill,pno,wno,previousBatch));

            //Build Tree
            final TreeItem<AdminTableShow> root = new RecursiveTreeItem<AdminTableShow>(adminInfo, RecursiveTreeObject::getChildren);
            studentTableView.getColumns().setAll(username, firstName,lastName,email,pnum,
                    wnum,batch);
            studentTableView.setRoot(root);
            studentTableView.setShowRoot(false);

            searchInput.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    studentTableView.setPredicate(new Predicate<TreeItem<AdminTableShow>>() {
                        @Override
                        public boolean test(TreeItem<AdminTableShow> studentTableInfoTreeItem) {
                            boolean flag =studentTableInfoTreeItem.getValue().uname.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().fname.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().lname.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().pnumber.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().email.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().wnumber.getValue().contains(newValue) ||
                                    studentTableInfoTreeItem.getValue().batch.getValue().contains(newValue)
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
            mongoCollection = database.getCollection("admin_details");
            System.out.println("yes mongoCollection not null");
        } catch (MongoException e) {
            System.out.println("Failed To Connect");
        }
    }
}
