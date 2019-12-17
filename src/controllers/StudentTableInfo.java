package controllers;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentTableInfo extends RecursiveTreeObject<StudentTableInfo> {

    StringProperty StudentID;
    StringProperty firstName;
    StringProperty middleName;
    StringProperty lastName;
    StringProperty dob;

    StringProperty age;

    StringProperty primaryNumber;
    StringProperty email;
    StringProperty whatsappNumber;

    StringProperty flatNumber;
    StringProperty road;
    StringProperty area;
    StringProperty city;
    StringProperty pincode;
    StringProperty address;

    StringProperty collegeName;
    StringProperty branch;
    StringProperty pursuingYear;

    StringProperty referredBy;
    StringProperty addedBy;

    public StudentTableInfo(String studentID, String firstName, String middleName,
                            String lastName, String dob, String age, String primaryNumber,
                            String email, String whatsappNumber, String flatNumber,
                            String road, String area, String city, String pincode,
                            String collegeName, String branch, String pursuingYear,
                            String referredBy, String addedBy) {


        this.StudentID = new SimpleStringProperty(studentID);
        this.firstName = new SimpleStringProperty(firstName);
        this.middleName = new SimpleStringProperty(middleName);
        this.flatNumber = new SimpleStringProperty(flatNumber);
        this.road = new SimpleStringProperty(road);
        this.area = new SimpleStringProperty(area);
        this.lastName = new SimpleStringProperty(lastName);
        this.dob = new SimpleStringProperty(dob);
        this.age = new SimpleStringProperty(age);
        this.primaryNumber = new SimpleStringProperty(primaryNumber);
        this.email = new SimpleStringProperty(email);
        this.whatsappNumber = new SimpleStringProperty(whatsappNumber);
        this.address = new SimpleStringProperty(flatNumber + ", " + road + ", " + area + ", " + city + ", " + pincode );
        this.pincode = new SimpleStringProperty(pincode);
        this.collegeName = new SimpleStringProperty(collegeName);
        this.branch = new SimpleStringProperty(branch);
        this.pursuingYear = new SimpleStringProperty(pursuingYear);
        this.referredBy = new SimpleStringProperty(referredBy);
        this.addedBy = new SimpleStringProperty(addedBy);
    }
}
