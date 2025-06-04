package hoelbookingsystem.demo;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class RoomInterfacePane extends Application {
    public static Stage homestage;
    //Public Body Component
    BorderPane header = new BorderPane();
    Label seemyProfile = new Label("My Profile   ");
    Label leftHeaderLabel = new Label("   Welcome " + SignInterfacePane.logedINUser.getName());
    Button arrangeBtn=new Button("Arrange Rooms");
    VBox arrangebtnbox=new VBox(arrangeBtn);
    VBox pane = new VBox();
    ScrollPane body = new ScrollPane(allHotelsPrint());

    public VBox allHotelsPrint() {
        ArrayList<Room> DeluxeRooms = new ArrayList<>();
        ArrayList<Room> SuiteRooms = new ArrayList<>();
        ArrayList<Room> standardRooms = new ArrayList<>();
        for (int i = 0; i < Main.rooms.size(); i++) {
            Room room = Main.rooms.get(i);
            if (room instanceof DeluxeRoom) {
                DeluxeRooms.add(room);
            } else if (room instanceof SuiteRoom) {
                SuiteRooms.add(room);
            } else if (room instanceof StandardRoom) {
                standardRooms.add(room);
            }
        }
        VBox scrollContent = new VBox(10);
        System.out.println(DeluxeRooms.size() + "\n" + standardRooms.size());
        scrollContent.getChildren().add(printRooms(" Deluxe Rooms", DeluxeRooms));
        scrollContent.getChildren().add(printRooms(" Suite Rooms", SuiteRooms));
        scrollContent.getChildren().add(printRooms(" standard Rooms", standardRooms));

        return scrollContent;
    }

    public VBox printOneRoom(int index, ArrayList<Room> rooms) {
        VBox roomDetails = new VBox();
        //Room Styling 
        roomDetails.setAlignment(Pos.CENTER);
        Image roomimg = new Image("file:///D:\\00-spring 2025\\project\\advanced\\room.jpeg", 180, 100, false, false);
        ImageView roomImgView = new ImageView(roomimg);
        Label roomPrice = new Label("Price :" + String.valueOf(rooms.get(index).getPricePerNight()) + "$");
        roomPrice.setStyle("-fx-text-fill: red;");
        roomDetails.setStyle("-fx-background-color: white;");
        roomDetails.setOnMouseExited(e -> {
            roomDetails.setStyle("-fx-background-color: white;");
        });
        roomDetails.setOnMouseEntered(e -> {
            roomDetails.setStyle("-fx-background-color: LightYellow;");
        });
        //labels    
        Label roomnum = new Label("Room Number : " + String.valueOf(rooms.get(index).getRoomNumber()));
        //add room items to Vbox
        roomDetails.getChildren().addAll(roomImgView, roomnum, roomPrice);
        //onclick on one room handle
        roomDetails.setOnMouseClicked(e -> {
            bookRoomInterfacePane.roomToBook = rooms.get(index);
            new bookRoomInterfacePane().start(new Stage());
            homestage.hide();
        });
        return roomDetails;
    }

    public VBox printRooms(String roomType, ArrayList<Room> hotelRooms) {
        VBox roomPrint = new VBox(10);
        VBox RoomTypeVBox = new VBox();
        Label RoomType = new Label(roomType);
        RoomTypeVBox.setStyle("-fx-font-size: 18px; -fx-background-color: LightGoldenrodYellow; -fx-text-fill: black;");
        RoomTypeVBox.getChildren().add(RoomType);
        roomPrint.getChildren().add(RoomTypeVBox);
        roomPrint.setPadding(new Insets(10));

        //printing  all rooms
        for (int i = 0; i < (hotelRooms.size() / 4); i++) {
            HBox threeOnOneLine = new HBox(10);
            for (int j = 0; j < 4; j++) {
                int index = 4 * i + j;
                threeOnOneLine.getChildren().add(printOneRoom(index, hotelRooms));
            }
            roomPrint.getChildren().add(threeOnOneLine);
        }

        HBox lastLine = new HBox(10);

        for (int i = 0; i < (hotelRooms.size() % 4); i++) {
            int index = 4 * (hotelRooms.size() / 4) + i;
            lastLine.getChildren().add(printOneRoom(index, hotelRooms));
        }

        roomPrint.getChildren().add(lastLine);
        VBox roomScroll = new VBox(roomPrint);

        return roomScroll;

    }

    public void MyProfileHandle() {
        seemyProfile.setOnMouseClicked(e -> {
            MyProfileInterfacePane showmyprofile = new MyProfileInterfacePane();
            showmyprofile.start(new Stage());
            homestage.hide();
        });

    }

    public void CSS() {
        header.setStyle("-fx-background-color: khaki; -fx-font-size:20;");
        header.setLeft(leftHeaderLabel);
        header.setRight(seemyProfile);
        seemyProfile.setOnMouseEntered(e -> {
            seemyProfile.setTextFill(Color.WHITE);
        });
        seemyProfile.setOnMouseExited(e -> {
            seemyProfile.setTextFill(Color.BLACK);
        });
        arrangebtnbox.setAlignment(Pos.CENTER);
    }

    @Override
    public void start(Stage stage) {
        homestage = stage;
        //CSS
        CSS();
        //Handle my profile 
        MyProfileHandle();
        //  Generic sort of rooms by their price per night
        arrangeBtn.setOnAction(e->{
            Collections.sort(Main.rooms);
            pane.getChildren().remove(body);
            body = new ScrollPane(allHotelsPrint());
            pane.getChildren().add(1,body);
        });

        //show stage

        pane.getChildren().addAll(header, body,arrangebtnbox);
        pane.setStyle("-fx-font-size: 16px;");
        Scene scene = new Scene(pane, 800, 650);
        stage.setTitle("Second View");
        stage.setScene(scene);
        stage.show();
    }


}