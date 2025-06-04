package hoelbookingsystem.demo;

public abstract class User {
    protected  int userId;
    protected  String name;
    protected String email;
    protected String password;
    public static int Id=1;
    
    public User(String name, String email,String Password) {
        userId=Id++;
        this.name = name;
        this.email = email;
        this.password=Password;
    }

    public abstract void displayInfo();

    
    //getters
    public int getUserId() { 
        return userId; 
    }
    public String getName() {
        return name; 
    }
    public String getEmail() {
        return email; 
    }
    public String getPassword() {
        return password; 
    }
    
    //change info
    public void changeName(String newName){
        name=newName;
    }
    public void changeEmail(String newEmail){
        email=newEmail;
    }
    public String changePassword(String newPassword,String oldPassword){
        if(password.equals(oldPassword)){
            password=newPassword;
               return " Password  Changed successfully";
                }
        else{
            return "Incorect Old Password ";}
    }
}