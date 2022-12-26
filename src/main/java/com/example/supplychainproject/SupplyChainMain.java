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

TextField emailField=new TextField();
    TextField searchArea=new TextField();
    Button searchButton=new Button("Search");
    public Pane root=new Pane();
Label welcomeMessage=new Label();
    public GridPane headerBar()
    {

        Button LoginButton=new Button("Login In");

        Button signInButton=new Button("Sign Up");

        welcomeMessage.setText("Welcome user");
        welcomeMessage.setTextFill(Color.WHITE);

        GridPane gridPane=new GridPane();
        gridPane.setHgap(20);
        gridPane.setMinSize(bodyPane.getMinWidth(),headerbar);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color:brown;");
        gridPane.add(LoginButton,0,0);
        gridPane.add(signInButton,1,0);
        gridPane.add(searchArea,2,0);
        gridPane.add(searchButton,3,0);
        gridPane.add(welcomeMessage,4,0);

        LoginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                root.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                root.getChildren().addAll(bodyPane,searchArea,searchButton,welcomeMessage);
                welcomeMessage.setTextFill(Color.BLACK);
            }
        });

        signInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().clear();
                bodyPane.getChildren().add(signPage());
                root.getChildren().add(bodyPane);
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

        emailField=new TextField();
        PasswordField passwordField=new PasswordField();

        Button loginSubmit=new Button("Login In");

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

        Product product=new Product();
        loginSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String email=emailField.getText();
                String password=passwordField.getText();

                if(loginPage.checking(email,password))
                {
                    String name=product.getUserName(email);
                    name=name.substring(0,1).toUpperCase()+name.substring(1,name.length());
                    welcomeMessage.setText("Welcome "+name);
                    bodyPane.getChildren().add(productDetails.getAllProducts());
                    root.getChildren().clear();
                    root.getChildren().addAll(bodyPane,footerBar(),searchButton,searchArea,welcomeMessage);
                    messageLabel.setText("Login Succesfull");
                }
                else{
                    messageLabel.setText("Login Failed");
                    messageLabel.setTextFill(Color.RED);
                }

            }
        });

        return gridPane;
    }
    public GridPane footerBar()
    {
        Button addToCart=new Button("Add to Cart");

        Button buyNow=new Button("Buy Now");

        Label indication=new Label();
        GridPane gridPane=new GridPane();
        gridPane.setHgap(20);
        gridPane.setMinSize(bodyPane.getMinWidth(),headerbar);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color:brown;");
        gridPane.add(addToCart,0,0);
        gridPane.add(buyNow,1,0);
        gridPane.add(indication,2,0);
        gridPane.setTranslateY(height);
        gridPane.setPrefSize(width,50);
        buyNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                PlaceOrder placeOrder=new PlaceOrder();
                boolean booking=placeOrder.ordering(emailField.getText(),productDetails.getSelectedProduct());
                if(booking == true)
                {
                    indication.setText("Order Placed");
                    indication.setTextFill(Color.GREEN);
                }
                else
                {
                    indication.setText("Order Failed");
                    indication.setTextFill(Color.RED);
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

        root.setPrefSize(width,height+headerbar);

        bodyPane.setMinHeight(height-50);
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