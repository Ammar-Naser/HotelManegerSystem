package hoelbookingsystem.demo;


import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class SignInterfacePane{
    public static Stage signStage;
    public static User logedINUser;
    //backgroundimage
        Image backgroundimg =new Image("file:///D:\\00-spring 2025\\project\\advanced\\istockphoto-472899538-612x612.jpg");
        ImageView backgroundImageView = new ImageView(backgroundimg);
        public void BackgroundImg(){
            backgroundImageView.setOpacity(0.75);
            backgroundImageView.setFitWidth(signStage.getWidth());  
            backgroundImageView.setFitHeight(signStage.getHeight());  
            backgroundImageView.setPreserveRatio(false); 
        }
    //signin /signup pane
        VBox signPane =new VBox();
    //sign in component
        Text welcome = new Text("BOOK YOUR ROOM NOW !");
        Label emailLabel=new Label("Email :");
        TextField emailInput =new TextField();
        Label passwordLabel=new Label("Password :"); 
        PasswordField paawordInput=new PasswordField();
        Button signin=new Button("Sign in");
        HBox newaccount =new HBox();
    //sign up component
        Label signUp =new Label("Sign Up");
        Label nameLabel =new Label("Enter Full Name : ");
        TextField nameInput =new TextField();
        Button register =new Button("Register");
        ComboBox<String> comboBox = new ComboBox<>();
        Label retunToSigninBtn= new Label("Have An Account?  Sign in");
        VBox returnToSigninBox =new VBox(retunToSigninBtn);
        Label ex1=new Label("Invalid user Data");
        Label notRegisteredEmailOrincorrectPassword=new Label("Email Address isn't registered or Incorrect Password");
        Label signup=new Label("Dont have Account? Sign Up");

    public void displaySignin(){
        signPane.getChildren().clear();
        signPane.getChildren().addAll(welcome,emailLabel,emailInput,passwordLabel,paawordInput,signin,newaccount);
    }
   
    public void displaySignup(){
        signPane.getChildren().clear();
        signPane.getChildren().addAll(signUp,nameLabel,nameInput,emailLabel,emailInput,passwordLabel,paawordInput,comboBox,register,returnToSigninBox);
    }
    
    
    public void labelCSS(){
        notRegisteredEmailOrincorrectPassword.setTextFill(Color.RED); 
    //welcome message adjusment 
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        welcome.setFill(Color.ORANGE); 
    //signup click and hover
        signup.setOnMouseEntered(e -> signup.setTextFill(Color.DARKBLUE));
        signup.setOnMouseExited(e -> signup.setTextFill(Color.BLACK));
   //Sign up
        signUp.setFont(Font.font(15));
        signUp.setTextFill(Color.BURLYWOOD);
   //return to signin click and hover
        retunToSigninBtn.setOnMouseEntered(e -> retunToSigninBtn.setTextFill(Color.DARKBLUE));
        retunToSigninBtn.setOnMouseExited(e -> retunToSigninBtn.setTextFill(Color.BLACK));
// sign pane fitst display
        signPane.setSpacing(10);
        signPane.setStyle("-fx-background-color: white; -fx-padding:15px" );
        signPane.setAlignment(Pos.CENTER);
    }
   
public void signinhandle() {
    signin.setOnAction(e -> {

        signPane.getChildren().remove(notRegisteredEmailOrincorrectPassword);
        boolean userFound = false;
        for(int i = 0; i < Main.users.size(); i++) {
            String returnInput = emailInput.getText();
            String userEmail = Main.users.get(i).getEmail();
            if(returnInput.equals(userEmail)) {
                if(Main.users.get(i).getPassword().equals(paawordInput.getText())) {
                    userFound = true;
                    logedINUser = Main.users.get(i);
                    if (Main.users.get(i) instanceof Guest) {
                        RoomInterfacePane RoomView = new RoomInterfacePane();
                        RoomView.start(new Stage());
                        signStage.hide();
                    } else {
                        bookRoomInterfacePane bookRoomView = new bookRoomInterfacePane();
                        bookRoomView.start(new Stage());
                        signStage.hide();
                    }
                    break;
                }
            }
        }
        if (!userFound) {
            signPane.getChildren().add(notRegisteredEmailOrincorrectPassword);
        }
    });
}
    
    public void RegisterHandle(){
        register.setOnAction(e->{
        String accountType=comboBox.getValue();
              try{
                signPane.getChildren().remove(ex1);        
              switch(accountType){
                          case("Guest"):
                              Main.users.add(new Guest(nameInput.getText(),emailInput.getText() ,paawordInput.getText() ));
                              break;
                          case("Admin"):
                             Main.users.add(new Admin(nameInput.getText(),emailInput.getText() ,paawordInput.getText() ));
                              break;
                          default:break;
                      }
              displaySignin();
              }catch(UnsupportedOperationException exception){
                  System.out.println(exception);
              } 
              catch(NullPointerException exception){
                  ex1.setTextFill(Color.RED);
                  signPane.getChildren().add(ex1);
              }
          }); 
    }
    
    public void onChangeWidthHandle(){
            signStage.widthProperty().addListener((observable, oldValue, newValue) -> {
                backgroundImageView.setFitWidth(newValue.doubleValue()); 
                signPane.setMaxWidth(newValue.doubleValue()/2);
             });
            signStage.heightProperty().addListener((observable, oldValue, newValue) -> {
                backgroundImageView.setFitHeight(newValue.doubleValue());
                signPane.setMaxHeight(newValue.doubleValue()*0.40);
            });
    } 
   
    
    
    public StackPane SignPane(Stage primaryStage) {
        
    signStage=primaryStage;
   
    // Background image
        BackgroundImg();
    //CSS
        labelCSS();
    //body
        newaccount.getChildren().add(signup);
        signinhandle();
        displaySignin();
        comboBox.getItems().addAll("Guest","Admin");
        RegisterHandle();
        onChangeWidthHandle();
        signup.setOnMouseClicked(e->displaySignup());
        returnToSigninBox.setOnMouseClicked(e->{displaySignin();  });
  
  
  
        BorderPane signinBorder=new BorderPane();
        signinBorder.setCenter(signPane);
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView,signinBorder);
        return root;       
    }

}