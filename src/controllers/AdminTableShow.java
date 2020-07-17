package controllers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdminTableShow extends RecursiveTreeObject<AdminTableShow> {

    StringProperty uname;
    StringProperty fname;
    StringProperty lname;
    StringProperty email;
    StringProperty pnumber;
    StringProperty wnumber;
    StringProperty batch;

    public AdminTableShow(String uname, String fname, String lname, String email, String pnumber, String wnumber, String batch) {
        this.uname = new SimpleStringProperty(uname);
        this.fname = new SimpleStringProperty(fname);
        this.lname = new SimpleStringProperty(lname);
        this.email = new SimpleStringProperty(email);
        this.pnumber = new SimpleStringProperty(pnumber);
        this.wnumber = new SimpleStringProperty(wnumber);
        this.batch = new SimpleStringProperty(batch);
    }
}
