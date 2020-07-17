package controllers;

public class adminDashboardTable {

    private String studentID;
    private String firstName;
    private String lastName;
    private String collegeName;
    private String emailID;
    private String mobileNumber;

    public adminDashboardTable(String studentID, String firstName, String lastName, String collegeName, String emailID, String mobileNumber) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.collegeName = collegeName;
        this.emailID = emailID;
        this.mobileNumber = mobileNumber;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
