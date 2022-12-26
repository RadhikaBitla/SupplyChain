package com.example.supplychainproject;

import javafx.application.Application;
import javafx.collections.ObservableList;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplyChainMain extends Application {

public static int width=600, height=400, headerbar=50;
public static Pane bodyPane = new Pane();
    public GridPane headerBar()
    {
        TextField searchArea=new TextField();
        Button searchButton=new Button("Search");

        Button LoginButton=new Button("Login In");

        Button signInButton=new Button("Sign Up");
        GridPane gridPane=new GridPane();
        gridPane.setHgap(20);
        gridPane.setMinSize(bodyPane.getMinWidth(),headerbar);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color:black;");
        gridPane.add(LoginButton,0,0);
        gridPane.add(signInButton,1,0);
        gridPane.add(searchArea,2,0);
        gridPane.add(searchButton,3,0);


        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().add(loginPage());
            }
        });

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().add(signPage());
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product=new Product();
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productDetails.getProductsByName(searchArea.getText()));

            }
        });
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
    public GridPane signPage()
    {
        GridPane gridPane=new GridPane();

        Label email=new Label("Email");
        Label password=new Label("Password");
        Label confirmPassword=new Label("Confirm Password");

        TextField emailField=new TextField();
        PasswordField passwordField=new PasswordField();
        PasswordField confirmPasswordField=new PasswordField();

        Button Signup=new Button("Sign Up");

        Label messageLabel=new Label();

        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());
        gridPane.setStyle("-fx-background-color:grey;");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(email,1,1);
        gridPane.add(password,1,2);
        gridPane.add(confirmPassword,1,3);
        gridPane.add(emailField,2,1);
        gridPane.add(passwordField,2,2);
        gridPane.add(confirmPasswordField,2,3);
        gridPane.add(Signup,2,4);
        gridPane.add(messageLabel,2,5);

        Signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!passwordField.getText().equals(confirmPasswordField.getText()))
                {
                    messageLabel.setText("Password doesn't match");
                    messageLabel.setTextFill(Color.RED);
                }
                else
                {
                    messageLabel.setText("Sign Up Successful");
                    messageLabel.setTextFill(Color.GREEN);
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