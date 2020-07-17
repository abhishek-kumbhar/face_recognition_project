/**
 * Sample Skeleton for 'AboutDetails.fxml' Controller Class
 */

package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AboutDetails {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="adminOperationsArea"
    private AnchorPane adminOperationsArea; // Value injected by FXMLLoader

    @FXML // fx:id="techView"
    private HBox techView; // Value injected by FXMLLoader

    @FXML // fx:id="backwardBtn"
    private JFXButton backwardBtn; // Value injected by FXMLLoader

    @FXML // fx:id="imageOne"
    private ImageView imageOne; // Value injected by FXMLLoader

    @FXML // fx:id="imageTwo"
    private ImageView imageTwo; // Value injected by FXMLLoader

    @FXML // fx:id="imageThree"
    private ImageView imageThree; // Value injected by FXMLLoader

    @FXML // fx:id="forwardBtn"
    private JFXButton forwardBtn; // Value injected by FXMLLoader

    int img1 = 0;


    ArrayList<FileInputStream> img = new ArrayList<>();

    int[] index = new int[]{0,1,2};
    int clickCnt = 0;
    int clickCntBack = 0;





    @FXML
    void backBtnClicked(MouseEvent event) {

//        if(clickCntBack == 2){
//            forwardBtn.setDisable(false);
//            backwardBtn.setDisable(true);
//        }else {
//            forwardBtn.setDisable(true);
//            backwardBtn.setDisable(false);
//        }


        for (int i = 0;i<3;i++)
            index[i] -= 3;

        for (int i = 0; i < 3; i++)
            System.out.println(index[i]);

        setPhotos();

       /* System.out.println("Index inc dec bwd"+ img1);



        if (img1 <= 3)
            backwardBtn.setDisable(true);
        else {
            backwardBtn.setDisable(false);
            img1 -= 3;
        }

        imageOne.setImage(new Image(img.get(img1-2)));
        imageTwo.setImage(new Image(img.get(img1-1)));
        imageThree.setImage(new Image(img.get(img1)));


        */

    }

    public void setPhotos (){

        imageOne.setImage(new Image(img.get(index[0])));
        imageTwo.setImage(new Image(img.get(index[1])));
        imageThree.setImage(new Image(img.get(index[2])));
    }

    @FXML
    void forwardBtnClicked(MouseEvent event) {

//        if(clickCnt == 2){
//            forwardBtn.setDisable(true);
//            backwardBtn.setDisable(false);
//
//        }else {
//            forwardBtn.setDisable(false);
//            backwardBtn.setDisable(true);
//        }

        if(clickCnt == 2)
            forwardBtn.setDisable(true);


        for (int i = 0;i<3;i++)
            index[i] += 3;

        for (int i = 0; i < 3; i++)
            System.out.println(index[i]);

        setPhotos();
        clickCnt++;



     /*   if (img1 == 6)
            forwardBtn.setDisable(true);
        else {
            forwardBtn.setDisable(false);
            img1 += 3;
        }



        imageOne.setImage(new Image(img.get(img1)));
        imageTwo.setImage(new Image(img.get(img1+1)));
        imageThree.setImage(new Image(img.get(img1+2)));

      */
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws FileNotFoundException {
        assert adminOperationsArea != null : "fx:id=\"adminOperationsArea\" was not injected: check your FXML file 'AboutDetails.fxml'.";
        assert techView != null : "fx:id=\"techView\" was not injected: check your FXML file 'AboutDetails.fxml'.";
        assert backwardBtn != null : "fx:id=\"backwardBtn\" was not injected: check your FXML file 'AboutDetails.fxml'.";
        assert imageOne != null : "fx:id=\"imageOne\" was not injected: check your FXML file 'AboutDetails.fxml'.";
        assert imageTwo != null : "fx:id=\"imageTwo\" was not injected: check your FXML file 'AboutDetails.fxml'.";
        assert imageThree != null : "fx:id=\"imageThree\" was not injected: check your FXML file 'AboutDetails.fxml'.";
        assert forwardBtn != null : "fx:id=\"forwardBtn\" was not injected: check your FXML file 'AboutDetails.fxml'.";

//        img1 = 0;


        img.add(new FileInputStream("C:\\Users\\Abhi\\IdeaProjects\\Core2Web_Attendance_new\\src\\images\\pythonlogo.png"));
        img.add(new FileInputStream("C:\\Users\\Abhi\\IdeaProjects\\Core2Web_Attendance_new\\src\\images\\javafxlogo.png"));
        img.add(new FileInputStream("C:\\Users\\Abhi\\IdeaProjects\\Core2Web_Attendance_new\\src\\images\\mongodbCloud.png"));

        img.add(new FileInputStream("C:\\Users\\Abhi\\IdeaProjects\\Core2Web_Attendance_new\\src\\images\\amazonsnslogo.png"));
        img.add(new FileInputStream("C:\\Users\\Abhi\\IdeaProjects\\Core2Web_Attendance_new\\src\\images\\cv2logo.png"));
        img.add(new FileInputStream("C:\\Users\\Abhi\\IdeaProjects\\Core2Web_Attendance_new\\src\\images\\jfeonixlogo.png"));

        img.add(new FileInputStream("C:\\Users\\Abhi\\IdeaProjects\\Core2Web_Attendance_new\\src\\images\\SceneBuilderLogo.png"));
        img.add(new FileInputStream("C:\\Users\\Abhi\\IdeaProjects\\Core2Web_Attendance_new\\src\\images\\pythonlogo.png"));
        img.add(new FileInputStream("C:\\Users\\Abhi\\IdeaProjects\\Core2Web_Attendance_new\\src\\images\\pythonlogo.png"));


        setPhotos();


//        imageOne.setImage(new Image(img.get(img1)));
//        imageTwo.setImage(new Image(img.get(img1+1)));
//        imageThree.setImage(new Image(img.get(img1+2)));




    }
}
