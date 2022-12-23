package com.example.supplychainproject;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChainMain extends Application {

public static int width=600, height=400, headerbar=50;
Pane bodyPane = new Pane();
    public GridPane headerBar()
    {
        TextField searchArea=new TextField();
        Button searchButton=new Button("Search");

        GridPane gridPane=new GridPane();
        gridPane.setHgap(10);
        gridPane.setMinSize(bodyPane.getMinWidth(),headerbar);
        gridPane.setAlignment(Pos.CENTER_RIGHT);
        gridPane.setStyle("-fx-background-color:black;");
        gridPane.add(searchArea,0,0);
        gridPane.add(searchButton,1,0);
        return gridPane;
    }
    public GridPane loginPage()
    {
        GridPane gridPane=new GridPane();

        LoginPage loginPage=new LoginPage();
        Label email=new Label("Email");
        Label password=new Label("Password");

        TextField emailField=new TextField();
        PasswordField passwordField=new PasswordField();

        Button loginSubmit=new Button("Submit");

        Label messageLabel=new Label();

        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setStyle("-fx-background-color:grey;");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(email,1,1);
        gridPane.add(password,1,2);
        gridPane.add(emailField,2,1);
        gridPane.add(passwordField,2,2);
        gridPane.add(loginSubmit,2,3);
        gridPane.add(messageLabel,2,4);
        loginSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email=emailField.getText();
                String password=passwordField.getText();

                if(loginPage.checking(email,password))
                {
                    messageLabel.setText("Login Succesfull");
                }
                else{
                    messageLabel.setText("Login Failed");
                }

            }
        });

        return gridPane;
    }
ProductDetails productDetails=new ProductDetails();
    public Pane createContent()
    {
        Pane root=new Pane();
        root.setPrefSize(width,height+headerbar);

        bodyPane.setMinHeight(height);
        bodyPane.setMinWidth(width);
        bodyPane.setTranslateY(headerbar);
        bodyPane.getChildren().addAll(productDetails.getAllProducts());
        root.getChildren().addAll(headerBar(),bodyPane);
        return root;
    }
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}