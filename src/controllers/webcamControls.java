package controllers;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class webcamControls implements Initializable {


    @FXML
    JFXButton btnStartCamera;
    @FXML
    JFXButton btnClickImage;
    @FXML JFXButton btnStopCamera;
    @FXML JFXButton btnDisposeCamera;
    @FXML ComboBox<WebCamInfo> cbCameraOptions;
    @FXML BorderPane bpWebCamPaneHolder;
    @FXML
    private StackPane contentLoader;
    @FXML FlowPane fpBottomPane;
    @FXML ImageView imgWebCamCapturedImage;
    private BufferedImage grabbedImage;
    //	private WebcamPanel selWebCamPanel = null;
    private Webcam selWebCam = null;
    private boolean stopCamera = false;
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();

    public static String rollno = "omkartest";
    private String cameraListPromptText = "Choose Camera";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        fpBottomPane.setDisable(true);
//        btnClickImage.setDisable(false);
        ObservableList<WebCamInfo> options = FXCollections.observableArrayList(
        );
        int webCamCounter = 0;
        for(Webcam webcam:Webcam.getWebcams())
        {
            WebCamInfo webCamInfo = new WebCamInfo();
            webCamInfo.setWebCamIndex(webCamCounter);
            webCamInfo.setWebCamName(webcam.getName());
            options.add(webCamInfo);
            webCamCounter ++;
        }
        cbCameraOptions.setItems(options);
        cbCameraOptions.setPromptText(cameraListPromptText);
        cbCameraOptions.getSelectionModel().selectedItemProperty().addListener(new  ChangeListener<WebCamInfo>() {

            @Override
            public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {
                if (arg2 != null) {

                    System.out.println("WebCam Index: " + arg2.getWebCamIndex()+": WebCam Name:"+ arg2.getWebCamName());
                    initializeWebCam(arg2.getWebCamIndex());
                }
            }
        });
        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                setImageViewSize();


            }
        });

    }
    protected void setImageViewSize() {

        double height = bpWebCamPaneHolder.getHeight();
        double width  = bpWebCamPaneHolder.getWidth();
        imgWebCamCapturedImage.setFitHeight(height);
        imgWebCamCapturedImage.setFitWidth(width);
        imgWebCamCapturedImage.prefHeight(height);
        imgWebCamCapturedImage.prefWidth(width);
        imgWebCamCapturedImage.setPreserveRatio(true);

    }
    protected void initializeWebCam(final int webCamIndex) {

        Task<Void> webCamIntilizer = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                if(selWebCam == null)
                {
                    selWebCam = Webcam.getWebcams().get(webCamIndex);
                    selWebCam.open();
                }else
                {
                    closeCamera();
                    selWebCam = Webcam.getWebcams().get(webCamIndex);
                    selWebCam.open();

                }
                startWebCamStream();
                return null;
            }

        };

        new Thread(webCamIntilizer).start();
        fpBottomPane.setDisable(false);
        btnStartCamera.setDisable(true);
    }

    protected void startWebCamStream() {

        stopCamera  = false;
        Task<Void> task = new Task<Void>() {


            @Override
            protected Void call() throws Exception {

                selWebCam.close();
                Dimension d = new Dimension(1024, 768);
                selWebCam.setCustomViewSizes(new Dimension[] { d });
                selWebCam.setViewSize(d);
                selWebCam.open();

                while (!stopCamera) {
                    try {
                        if ((grabbedImage = selWebCam.getImage()) != null) {

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    final Image mainiamge = SwingFXUtils
                                            .toFXImage(grabbedImage,null);
                                    imageProperty.set(mainiamge);
                                }
                            });

                            grabbedImage.flush();

                        }
                    } catch (Exception e) {
                    } finally {

                    }

                }

                return null;

            }

        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        imgWebCamCapturedImage.imageProperty().bind(imageProperty);

    }

    private void closeCamera()
    {
        if(selWebCam != null)
        {
            selWebCam.close();
        }
    }
    public void stopCamera(ActionEvent event)
    {
        stopCamera = true;
        btnStartCamera.setDisable(false);
        btnStopCamera.setDisable(true);
    }

    public  void capture(ActionEvent event) throws IOException {

        selWebCam.close();
        Dimension d = new Dimension(1024, 768);
        selWebCam.setCustomViewSizes(new Dimension[] { d });
        selWebCam.setViewSize(d);
        selWebCam.open();
        if (!selWebCam.isOpen()) {
            selWebCam.open();

        }

        try {
            ImageIO.write(selWebCam.getImage(), "PNG", new File("C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\javaFiles\\" + AddNewStudent.selectedBatch + "\\Images\\" + AddNewStudent.finalStudId+".png"));
        } catch (IOException e) {
            throw new WebcamException(e);
        }
        stopCamera = true;
        closeCamera();
        Webcam.resetDriver();
        btnStopCamera.setDisable(true);
        btnStartCamera.setDisable(true);
        dialogDisplay(contentLoader,"Student "+rollno+" information and image recorded successfully ...",event);
        System.out.println(AddNewStudent.studentName);
        System.out.println("Mamil id = " + AddNewStudent.studentEmail);

        Thread t = new Thread() {
            public void run(){
                try {
                    String pqr = "+91" + AddNewStudent.studentNumber;
                    String cmd = "python C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\finalMailMessage.py " + AddNewStudent.studentName + " " + AddNewStudent.selectedBatch + " " + AddNewStudent.studentEmail + " " + pqr;
                    Process p = Runtime.getRuntime().exec(cmd);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        String x = "python C:\\Users\\Abhi\\Desktop\\Core2Web\\faceRecognition_v2\\finalMakeEncodings.py " + AddNewStudent.selectedBatch;
        Thread t1 = new Thread() {

            public void run(){
                try {
                    Process p = Runtime.getRuntime().exec(x);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        System.out.println("abhishek END of Program 1");
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
    private void dialogDisplay(StackPane stackPane, String bodyMessage,ActionEvent event) {
        //BoxBlur blur = new BoxBlur(3, 3, 3);


        JFXButton button = new JFXButton("Go to dashboard");
        button.getStyleClass().add("dialogBtn");
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.getStyleClass().add("dialog");
        dialogLayout.setHeading(new Label("ATTENTION !"));

        JFXDialog dialogL = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mEvent) -> {
            contentLoader.setEffect(null);
            dialogL.close();
            Stage newStage = new Stage();

            Window window = ((Node) (event.getSource())).getScene().getWindow();
            if (window instanceof Stage) {
                ((Stage) window).close();
            }

            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../views/adminDashboard.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            newStage.initStyle(StageStyle.UNDECORATED);

            newStage.setScene(new Scene(root, 1150, 768));
            newStage.show();

        });



        dialogLayout.setBody(new Text(bodyMessage));
        dialogLayout.setActions(button);
        dialogL.show();
        dialogL.setOnDialogClosed((JFXDialogEvent mEvent) -> {

            contentLoader.setEffect(null);
        });
       // contentLoader.setEffect(blur);
    }


    public void startCamera(ActionEvent event) throws IOException {
        stopCamera = false;
        //takeImageBtnPressed();
       startWebCamStream();
        btnStartCamera.setDisable(true);
        btnStopCamera.setDisable(false);
    }

    public void disposeCamera(ActionEvent event)
    {
        stopCamera = true;
        closeCamera();
        Webcam.resetDriver();
        btnStopCamera.setDisable(true);
        btnStartCamera.setDisable(true);
    }

    class WebCamInfo
    {
        private String webCamName ;
        private int webCamIndex ;

        public String getWebCamName() {
            return webCamName;
        }
        public void setWebCamName(String webCamName) {
            this.webCamName = webCamName;
        }
        public int getWebCamIndex() {
            return webCamIndex;
        }
        public void setWebCamIndex(int webCamIndex) {
            this.webCamIndex = webCamIndex;
        }

        @Override
        public String toString() {
            return webCamName;
        }

    }
}
