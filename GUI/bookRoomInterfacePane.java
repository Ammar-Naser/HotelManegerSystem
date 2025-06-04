package hoelbookingsystem.demo;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class bookRoomInterfacePane extends Application {
    public static User LogedINUser;
    public static Room roomToBook;
    public static Stage bookRoom;
    public static Guest bookinguser;
    //public body component
    HBox goToHomeHBox = new HBox(10);
    VBox body = new VBox();
    Label roomPriceLabel = new Label("Room Price : ");
    TextField roomPriceField = new TextField();
    Label roomNumberLabel = new Label("Room Number : ");
    TextField roomNumberField = new TextField();
    Label roomAmenitiesLabel = new Label(" Room Amenities : ");
    TextField roomAmenitiesField = new TextField();
    Label customerNameLabel = new Label("Customer Name : ");
    TextField customerNameField = new TextField();
    Label customerEmailLabel = new Label("Customer Email : ");
    TextField customerEmailField = new TextField();
    Label bookSuccess = new Label("Successfully Booked");
    Button bookbtn = new Button("Book Room");
    HBox bookbtnVBox = new HBox(bookbtn);
    //for staff only
    Label selectRoomTypeLabel = new Label("Select Room Type: ");//selectRoomTypeLabel replpace selecthotellabel
    Label selectRoomNumberlabel = new Label("Select Room Numberl : ");
    ComboBox<String> typesComboBox = new ComboBox<>();//>>trpecombobox replace hotelcombobox
    ComboBox<String> roomsComboBox = new ComboBox<>();

    public void initBookinUser() {
        if (LogedINUser instanceof Guest) bookinguser = (Guest) LogedINUser;
    }

    public VBox Header() {
        Label headerLabel = new Label("Book Room");
        headerLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: Olive;");
        VBox header = new VBox(headerLabel);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: Moccasin;");
        return header;
    }

    public VBox RoomDetailsLabel() {
        Label roomDetailsLabel = new Label("Room Details");
        roomDetailsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: Coral ;");
        VBox roomDetailsLabelVBox = new VBox(roomDetailsLabel);
        roomDetailsLabelVBox.setStyle("-fx-background-color:Beige;");
        return roomDetailsLabelVBox;
    }

    public VBox CustomerDetailsLabel() {
        Label customerDetailsLabel = new Label("Customer Details");
        customerDetailsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: Coral ;");
        VBox customerDetailsLabelVBox = new VBox(customerDetailsLabel);
        customerDetailsLabelVBox.setStyle("-fx-background-color:Beige;");
        return customerDetailsLabelVBox;
    }

    public void DisableTextField() {
        roomAmenitiesField.setDisable(true);
        roomNumberField.setDisable(true);
        roomPriceField.setDisable(true);
        customerNameField.setDisable(true);
    }

    public void roomstypehandle() {
        typesComboBox.setValue("Select Room Type");
        typesComboBox.getItems().addAll("Deluxe Room", "Suite Room", "Standard Room");
        body.getChildren().add(1, selectRoomTypeLabel);
        body.getChildren().add(2, typesComboBox);
    }

    public VBox paymentMethodLabel() {
        Label customerDetailsLabel = new Label("payment Method");
        customerDetailsLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: Coral ;");
        VBox customerDetailsLabelVBox = new VBox(customerDetailsLabel);
        customerDetailsLabelVBox.setStyle("-fx-background-color:Beige;");
        return customerDetailsLabelVBox;
    }

    public VBox paymentMethod() {
        VBox paymentVBox = new VBox(15);

        // Credit Card Section
        CheckBox creditCardCheckBox = new CheckBox();
        Label creditCardLabel = new Label("Credit Card");
        HBox creditCardBox = new HBox(10, creditCardCheckBox, creditCardLabel);

        VBox creditCardFields = new VBox(5);
        TextField cardNumberField = new TextField();
        TextField cardHolderField = new TextField();
        creditCardFields.getChildren().addAll(
                new Label("Card Number:"), cardNumberField,
                new Label("Card Holder:"), cardHolderField
        );
        creditCardFields.setPadding(new Insets(10));
        creditCardFields.setVisible(false);

        // PayPal Section
        CheckBox paypalCheckBox = new CheckBox();
        Label paypalLabel = new Label("PayPal");
        HBox paypalBox = new HBox(10, paypalCheckBox, paypalLabel);

        VBox paypalFields = new VBox(5);
        TextField paypalEmailField = new TextField();
        paypalFields.getChildren().addAll(
                new Label("PayPal Email:"), paypalEmailField
        );
        paypalFields.setPadding(new Insets(10));
        paypalFields.setVisible(false);

        // Logic to toggle visibility and mutual exclusion
        creditCardCheckBox.setOnAction(e -> {
            if (creditCardCheckBox.isSelected()) {
                paypalCheckBox.setSelected(false);
                creditCardFields.setVisible(true);
                paypalFields.setVisible(false);
            } else {
                creditCardFields.setVisible(false);
            }
        });

        paypalCheckBox.setOnAction(e -> {
            if (paypalCheckBox.isSelected()) {
                creditCardCheckBox.setSelected(false);
                paypalFields.setVisible(true);
                creditCardFields.setVisible(false);
            } else {
                paypalFields.setVisible(false);
            }
        });

        paymentVBox.getChildren().addAll(
                creditCardBox, creditCardFields,
                paypalBox, paypalFields
        );

        return paymentVBox;
    }


    public void AddRoomsNumberlist() {
        String selected = typesComboBox.getValue();
        int number;
        for (int j = 0; j < Main.rooms.size(); j++) {
            if (Main.rooms.get(j) instanceof DeluxeRoom && selected.equals("Deluxe Room")) {
                number = Main.rooms.get(j).getRoomNumber();
                roomsComboBox.getItems().add(String.valueOf(number));
            }
            if (Main.rooms.get(j) instanceof SuiteRoom && selected.equals("Suite Room")) {
                number = Main.rooms.get(j).getRoomNumber();
                roomsComboBox.getItems().add(String.valueOf(number));
            }
            if (Main.rooms.get(j) instanceof StandardRoom && selected.equals("Standard Room")) {
                number = Main.rooms.get(j).getRoomNumber();
                roomsComboBox.getItems().add(String.valueOf(number));
            }
        }
        roomsComboBox.setValue("select Room Number");
        body.getChildren().add(3, selectRoomNumberlabel);
        body.getChildren().add(4, roomsComboBox);
    }

    public void typesBoxHandle() {
        typesComboBox.setOnAction(e -> {
            body.getChildren().remove(bookSuccess);
            if (typesComboBox != null) {
                body.getChildren().removeAll(roomsComboBox, selectRoomNumberlabel);
                roomsComboBox.getItems().clear();
                AddRoomsNumberlist();
                roomsBoxHandle();
            }
        });
    }

    public void roomsBoxHandle() {
        roomsComboBox.setOnAction(e2 -> {
            Room selectedRoom = Main.rooms.get(0);
            int selectedroomNumber = Integer.parseInt(roomsComboBox.getValue());
            String selected = typesComboBox.getValue();
            for (int j = 0; j < Main.rooms.size(); j++) {
                if (Main.rooms.get(j) instanceof DeluxeRoom && selected.equals("Deluxe Room")) {
                    if (selectedroomNumber == Main.rooms.get(j).getRoomNumber()) {
                        selectedRoom = Main.rooms.get(j);
                    }
                }
                if (Main.rooms.get(j) instanceof SuiteRoom && selected.equals("Suite Room")) {
                    if (selectedroomNumber == Main.rooms.get(j).getRoomNumber()) {
                        selectedRoom = Main.rooms.get(j);
                    }
                }
                if (Main.rooms.get(j) instanceof StandardRoom && selected.equals("Standard Room")) {
                    if (selectedroomNumber == Main.rooms.get(j).getRoomNumber()) {
                        selectedRoom = Main.rooms.get(j);
                    }
                }
            }
            printRoomData(selectedRoom);
        });
    }

    public void printRoomData(Room room) {
        roomAmenitiesField.setText(room.getAmenities());
        roomNumberField.setText(String.valueOf(room.getRoomNumber()));
        roomPriceField.setText(String.valueOf(room.getPricePerNight()));
    }

    public void SearchUseerButton() {
        bookbtn.setDisable(true);
        Button searchuser = new Button("search user");
        goToHomeHBox.getChildren().add(searchuser);
        searchuser.setOnAction(e -> {
            for (int i = 0; i < Main.users.size(); i++) {
                if (Main.users.get(i).getEmail().equals(customerEmailField.getText())) {
                    customerNameField.setText(Main.users.get(i).getName());
                    bookbtn.setDisable(false);
                }
            }
        });
    }

    public void bookBtnStaff() {
        bookbtn.setOnAction(e -> {
            body.getChildren().remove(bookSuccess);
            Room bookedRoom = Main.rooms.get(0);
            for (int i = 0; i < Main.users.size(); i++) {
                if (Main.users.get(i).getEmail().equals(customerEmailField.getText())) {
                    bookinguser = (Guest) Main.users.get(i);
                }
            }
            Main.manage.createBooking(bookinguser, bookedRoom, LocalDate.MAX, LocalDate.MAX);
            body.getChildren().add(bookSuccess);
        });
    }

    public void bookBtnCustomer() {
        bookbtn.setOnAction(e -> {
            body.getChildren().remove(bookSuccess);
            Main.manage.createBooking(bookinguser, roomToBook, LocalDate.MAX, LocalDate.MAX);
            body.getChildren().add(bookSuccess);
        });
    }

    public void CustomerTextField() {
        customerEmailField.setDisable(true);
        roomAmenitiesField.setText(roomToBook.getAmenities());
        roomNumberField.setText(String.valueOf(roomToBook.getRoomNumber()));
        roomPriceField.setText(String.valueOf(roomToBook.getPricePerNight()));
        customerNameField.setText(bookinguser.getName());
        customerEmailField.setText(bookinguser.getEmail());
    }

    public Button goToHomebtn() {
        Button goToHome = new Button("Home");
        goToHome.setOnAction(e -> {
            if (SignInterfacePane.logedINUser instanceof Guest) {
                new RoomInterfacePane().start(new Stage());
                RoomInterfacePane.homestage.show();
            } else {
                AddRoomInterfacepane addRoomVIEW = new AddRoomInterfacepane();
                addRoomVIEW.start(new Stage());
            }
            bookRoom.hide();
        });
        return goToHome;
    }

    @Override
    public void start(Stage stage) {
        LogedINUser = SignInterfacePane.logedINUser;
        initBookinUser();
        bookRoom = stage;
        //css
        body.setAlignment(Pos.CENTER);
        bookSuccess.setStyle("-fx-text-fill: green;");
        bookbtnVBox.setAlignment(Pos.BASELINE_RIGHT);
        //add items to body
        body.getChildren().addAll(RoomDetailsLabel(), roomPriceLabel, roomPriceField, roomNumberLabel, roomNumberField, roomAmenitiesLabel, roomAmenitiesField);
        body.getChildren().addAll(CustomerDetailsLabel(), customerNameLabel, customerNameField, customerEmailLabel, customerEmailField, bookbtnVBox);
        body.getChildren().addAll(paymentMethodLabel(), paymentMethod());
        DisableTextField();
        //handle if the page for staff or customer 
        if (LogedINUser instanceof Admin) {
            roomstypehandle();
            typesBoxHandle();
            SearchUseerButton();
            bookBtnStaff();
        } else if (LogedINUser instanceof Guest) {
            CustomerTextField();
            bookBtnCustomer();
        }
        //show stage
        goToHomeHBox.getChildren().add(goToHomebtn());
        VBox root = new VBox(Header(), body, goToHomeHBox);
        ScrollPane scrollpage = new ScrollPane(root);
        scrollpage.setFitToWidth(true);
        root.setPadding(new Insets(20));
        Scene scene = new Scene(scrollpage, 800, 650);
        stage.setTitle("Book Room");
        stage.setScene(scene);
        stage.show();
    }
}
