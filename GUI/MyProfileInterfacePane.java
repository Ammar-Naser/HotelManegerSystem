package hoelbookingsystem.demo;

//import hoelbookingsystem.SignInterfacePane;
//import hoelbookingsystem.User;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyProfileInterfacePane extends Application {
    public static Stage myprofileref;
    public static User logedinuser = SignInterfacePane.logedINUser;//sent when opening this stage
    //body public component
    VBox showProfilePane = new VBox(5);
    HBox lastVBox = new HBox(10);
    //labels 
    Label myprofile = new Label("My Profile");
    Label nameLabel = new Label("Name : ");
    Label IDLabel = new Label("ID : ");
    Label emailLabel = new Label("Email : ");
    Label ischanged = new Label();
    Label oldpassLabel = new Label("Old Password");
    //Text fields    
    TextField nameField = new TextField(logedinuser.getName());
    TextField IDField = new TextField(String.valueOf(logedinuser.getUserId()));
    TextField emailField = new TextField(logedinuser.getEmail());
    //Buttons    
    Button editbtn = new Button("Edit");
    Button saveChanges = new Button("Save");
    Button chPass = new Button("Change Pssword");


    public void saveChangesHandle() {
        saveChanges.setOnAction(e -> {
            logedinuser.changeName(nameField.getText());
            logedinuser.changeEmail(emailField.getText());
            nameField.setDisable(true);
            emailField.setDisable(true);
            lastVBox.getChildren().remove(saveChanges);
            lastVBox.getChildren().add(0, editbtn);
        });
    }

    public void editbtnHandle() {
        editbtn.setOnAction(e -> {
            nameField.setDisable(false);
            emailField.setDisable(false);
            lastVBox.getChildren().remove(editbtn);
            lastVBox.getChildren().add(0, saveChanges);
            showProfilePane.getChildren().remove(ischanged);
        });
    }

    public void ChangePassHandle() {

        TextField oldpassfield = new TextField();
        Label newpassLabel = new Label("New Password");
        TextField newpassfield = new TextField();

        Button saveChPass = new Button("Save Password");
        chPass.setOnAction(e -> {
            showProfilePane.getChildren().addAll(oldpassLabel, oldpassfield, newpassLabel, newpassfield);
            lastVBox.getChildren().remove(chPass);
            lastVBox.getChildren().add(1, saveChPass);
            showProfilePane.getChildren().remove(ischanged);
        });
        //save password change by checking if the old password is correct
        saveChPass.setOnAction(e -> {
            ischanged.setText(logedinuser.changePassword(newpassfield.getText(), oldpassfield.getText()));
            showProfilePane.getChildren().add(ischanged);
            showProfilePane.getChildren().removeAll(oldpassLabel, oldpassfield, newpassLabel, newpassfield);
            lastVBox.getChildren().remove(saveChPass);
            lastVBox.getChildren().add(1, chPass);
        });

    }

    public Button returnHomebtn() {
        Button returnHomebtn = new Button("Home");
        returnHomebtn.setOnAction(e -> {
            RoomInterfacePane.homestage.show();
            myprofileref.hide();
        });
        return returnHomebtn;
    }

    public void CSS() {
        lastVBox.setAlignment(Pos.CENTER);
        showProfilePane.setAlignment(Pos.TOP_CENTER);
        myprofile.setStyle("-fx-font-size:16px; -fx-text-fill: DarkKhaki;");
        nameField.setDisable(true);
        IDField.setDisable(true);
        emailField.setDisable(true);
    }

    public Button logoutbtn() {

        Button logoutbtn = new Button("Log Out");
        logoutbtn.setOnAction(e -> {
            SignInterfacePane.signStage.show();
            myprofileref.hide();
        });
        return logoutbtn;
    }

    @Override
    public void start(Stage stage) {
        myprofileref = stage;
        //css
        CSS();
        //handle buttons
        saveChangesHandle();
        editbtnHandle();
        ChangePassHandle();
        showProfilePane.getChildren().addAll(myprofile, nameLabel, nameField, IDLabel, IDField, emailLabel, emailField, editbtn);
        //add buttons 
        lastVBox.getChildren().addAll(editbtn, chPass, returnHomebtn(), logoutbtn());
        //show stage
        VBox pane = new VBox(10);
        pane.getChildren().addAll(showProfilePane, lastVBox);
        pane.setStyle("-fx-font-size: 16px;");
        Scene scene = new Scene(pane, 800, 650);
        stage.setTitle("Second View");
        stage.setScene(scene);
        stage.show();
    }


}
