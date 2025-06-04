package hoelbookingsystem.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddRoomInterfacepane extends Application {
    public static Stage addRoomRef;
    public static User LogedINUser;
    //public body Components >> used by many functions
    VBox body = new VBox(5);
    //text fields and combobox<choose hotel>
    ComboBox<String> roomTypeComboBox = new ComboBox<>();
    TextField roomPriceField = new TextField();
    TextField roomNumberField = new TextField();
    TextField CapicityTextfield = new TextField();

    //Labels
    Label invaledData = new Label("Invalid Data");
    Label addedsuccessfully = new Label("Room Added successfully");
    Label newHotelNameLabel = new Label("new Hotel Name : ");
    Label newHoteladdressLabel = new Label("new Hotel address : ");
    Label Capicitylabel = new Label("Capicity");
    Label roomAmenitiesLabel = new Label(" Room Amenities : ");
    //Amenities
    Label jacuzziLabel = new Label("Jacuzzi");
    CheckBox jacuzziCheckBox = new CheckBox();
    HBox jacuzziBox = new HBox(10, jacuzziLabel, jacuzziCheckBox);
    Label livingAreaLabel = new Label("Living Area");
    CheckBox livingAreaCheckBox = new CheckBox();
    HBox livingAreaBox = new HBox(10, livingAreaLabel, livingAreaCheckBox);
    VBox SuiteRoomAmenities = new VBox(10, jacuzziBox, livingAreaBox);
    Label wifiLabel = new Label("WiFi");
    CheckBox wifiCheckBox = new CheckBox();
    HBox WiFiBox = new HBox(10, wifiLabel, wifiCheckBox);
    VBox StandardRoomAmenities = new VBox(10, WiFiBox);
    Label balconyLabel = new Label("Balcony");
    CheckBox balconyCheckBox = new CheckBox();
    HBox balconyBox = new HBox(10, balconyLabel, balconyCheckBox);
    Label acLabel = new Label("Air Conditioner");
    CheckBox acCheckBox = new CheckBox();
    HBox acBox = new HBox(10, acLabel, acCheckBox);
    VBox DeluxeRoomAmenities = new VBox(10, balconyBox, acBox);
    //Header

    public VBox Header() {
        Label headerLabel = new Label("Add Room");
        headerLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: Olive;");
        VBox header = new VBox(headerLabel);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: Moccasin;");
        return header;
    }

    //Body

    public VBox Body() {
        body.setAlignment(Pos.CENTER);
        //body Labels
        Label roomTypeLabel = new Label("Room Type : ");
        Label roomPriceLabel = new Label("Room Price : ");
        Label roomNumberLabel = new Label("Room Number : ");

        //combo box

        body.getChildren().addAll(roomNumberLabel, roomNumberField, roomTypeLabel, roomTypeComboBox, roomPriceLabel, roomPriceField, Capicitylabel, CapicityTextfield);
        addedsuccessfully.setStyle("-fx-text-fill:green;");
        return body;
    }

    public VBox Amenitieshandle() {
        try {
            String type = roomTypeComboBox.getValue();
            return switch (type) {
                case "StandardRoom" -> StandardRoomAmenities;
                case "DeluxeRoom" -> DeluxeRoomAmenities;
                case "Suite" -> SuiteRoomAmenities;
                default -> new VBox();
            };
        }catch (NullPointerException ex){
            return new VBox();
        }
    }

    //Footer

    public Button returToBookRoom() {
        Button bookRoombtn = new Button("Book Room");
        bookRoombtn.setOnAction(e -> {
            bookRoomInterfacePane.bookRoom.show();
            addRoomRef.hide();
        });
        return bookRoombtn;
    }

    public Button RefreshButton() {
        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(e -> {
            body.getChildren().removeAll(newHotelNameLabel,invaledData,addedsuccessfully, newHoteladdressLabel);
            roomPriceField.setText(null);
            CapicityTextfield.setText(null);
            roomNumberField.setText(null);
            roomTypeComboBox.getSelectionModel().clearSelection();
        });
        return refreshBtn;
    }

    public VBox addRoomButton() {
        Button addRoombtn = new Button("ADD");
        addRoombtn.setOnAction(e -> {
            try {
                body.getChildren().removeAll(addedsuccessfully, invaledData);
                //adding rooom
                String selected = roomTypeComboBox.getValue();
                Room room = null;
                int price = Integer.parseInt(roomPriceField.getText());
                int number = Integer.parseInt(roomNumberField.getText());
                int capicty = Integer.parseInt(CapicityTextfield.getText());
                switch (selected) {
                    case "SuiteRoom":
                        room = new SuiteRoom(price, number, capicty, jacuzziCheckBox.isSelected(), wifiCheckBox.isSelected());
                        break;
                    case "StandardRoom":
                        room = new StandardRoom(price, number, capicty, wifiCheckBox.isSelected());
                        break;
                    case "DeluxeRoom":
                        room = new DeluxeRoom(price, number, capicty, balconyCheckBox.isSelected(), acCheckBox.isSelected());
                        break;
                    default:
                        System.out.println("Unknown room type.");
                }
                Main.rooms.add(room);
                body.getChildren().add(addedsuccessfully);


            } catch (NullPointerException ex1) {
                body.getChildren().add(invaledData);
            } catch (NumberFormatException ex2) {
                body.getChildren().add(invaledData);
            }
        });
        VBox addRoomVBox = new VBox(addRoombtn);
        addRoomVBox.setAlignment(Pos.BASELINE_RIGHT);
        return addRoomVBox;
    }

    public HBox footerButtons() {
        HBox lastLinebtnsHbox = new HBox(10);
        lastLinebtnsHbox.getChildren().addAll(returToBookRoom(), RefreshButton());
        return lastLinebtnsHbox;
    }

    public void roomTypeBoxHandle() {
        roomTypeComboBox.getItems().addAll(
                "DeluxeRoom",
                "StandardRoom",
                "Suite"
        );
        roomTypeComboBox.setOnAction(e -> {
            body.getChildren().removeAll(roomAmenitiesLabel, DeluxeRoomAmenities, StandardRoomAmenities, SuiteRoomAmenities);
            body.getChildren().addAll(roomAmenitiesLabel, Amenitieshandle());
        });

    }

    @Override
    public void start(Stage stage) {
        addRoomRef = stage;

        roomTypeBoxHandle();

        VBox root = new VBox(Header(), Body(), addRoomButton(), footerButtons());
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 800, 650);
        stage.setTitle("ADD NEW ROOM");
        stage.setScene(scene);
        stage.show();
    }
}
