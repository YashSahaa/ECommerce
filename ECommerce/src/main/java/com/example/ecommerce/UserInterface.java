package com.example.ecommerce;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import javax.xml.parsers.SAXParser;

public class UserInterface {
    GridPane loginPage;
    GridPane signupPage;
    HBox headerBar;
    HBox footerBar;
    Button signinButton;
    Label welcomeLabel;
    VBox body;
    Customer loggedInCutomer;
    ProductList productList = new ProductList();
    OrderList orderList = new OrderList();
    ObservableList<Product> itemToCart = FXCollections.observableArrayList();
    Button placeOrderButton = new Button("Place Order");
    VBox productPage;
    VBox orderpage;
    BorderPane createContent(){
        BorderPane root = new BorderPane();
        root.setPrefSize(800,600);
        root.setTop(headerBar);
        body = new VBox();
        body.setPadding(new Insets(10));
        body.setAlignment(Pos.CENTER);
       // root.setCenter(loginPage);
        root.setCenter(body);
        productPage = productList.getAllProduct();
        body.getChildren().add(productPage);
        root.setBottom(footerBar);
        return root;
    }
    UserInterface(){
        createSignupPage();
        createLoginPage();
        createHeaderBar();
        createfooterBar();
    }
    private  void createSignupPage(){
        Text usernameSText = new Text("Name");
        Text emailSText = new Text("Email");
        Text mobileSText = new Text("Mobile no");
        Text passwordSText = new Text("Password");
        Text addressSText = new Text("Address");

        TextField sName = new TextField();
        sName.setPromptText("Type your Name here");
        TextField sEmail = new TextField();
        sEmail.setPromptText("Type your Email Address here");
        TextField sMobile = new TextField();
        sMobile.setPromptText("Type your Mobile number here");
        TextField sPass = new TextField();
        sPass.setPromptText("Tyoe Password");
        TextField sAddress = new TextField();
        sAddress.setPromptText("Type your Address here");

        Button registerButton = new Button("Register");

        signupPage = new GridPane();
        signupPage.setAlignment(Pos.CENTER);
        signupPage.setHgap(20);
        signupPage.setVgap(20);
        signupPage.add(usernameSText,0,0);
        signupPage.add(sName,1,0);
        signupPage.add(emailSText,0,1);
        signupPage.add(sEmail,1,1);
        signupPage.add(mobileSText,0,2);
        signupPage.add(sMobile,1,2);
        signupPage.add(passwordSText,0,3);
        signupPage.add(sPass,1,3);
        signupPage.add(addressSText,0,4);
        signupPage.add(sAddress,1,4);
        signupPage.add(registerButton,1,5);

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = sName.getText();
                String email = sEmail.getText();
                String mob = sMobile.getText();
                String pass = sPass.getText();
                String add = sAddress.getText();
                Signup signup = new Signup();
                boolean reg = signup.registerUser(name,email,mob,pass,add);
                if(reg==true){
                    body.getChildren().clear();
                    body.getChildren().add(loginPage);
                    showDialog("You have successfully registered please login ");
                }
                else {
                    showDialog("Please fill your details");
                }
            }
        });
    }
    private void createLoginPage(){
        Text userNameText = new Text("Email");
        Text passwordText = new Text("Password");

        TextField userName = new TextField();
        userName.setPromptText("Type Your Register Email Here");
        PasswordField password = new PasswordField();
        password.setPromptText("Type Your Password Here");
        Button loginButton = new Button("Login");
       // Label messagelabel = new Label("Hi");
        Button signupButton = new Button("SignUp");

        loginPage = new GridPane();
        //loginPage.setStyle("-fx-Background-Color:grey;");
        loginPage.setAlignment(Pos.CENTER);
        loginPage.setHgap(20);
        loginPage.setVgap(20);
        loginPage.add(userNameText,0,0);
        loginPage.add(userName,1,0);
        loginPage.add(passwordText,0,1);
        loginPage.add(password,1,1);
        loginPage.add(signupButton,0,2);
        loginPage.add(loginButton,1,2);
        //loginPage.add(messagelabel,1,3);


        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String name = userName.getText();
                String pass = password.getText();
                Login login = new Login();
                loggedInCutomer = login.customeLogin(name,pass);
                if(loggedInCutomer != null){
                   // messagelabel.setText("Welcome : "+loggedInCutomer.getName());
                    welcomeLabel.setText("Welcome : "+loggedInCutomer.getName());
                    headerBar.getChildren().add(welcomeLabel);
                    body.getChildren().clear();
                    body.getChildren().add(productPage);
                    footerBar.setVisible(true);
                }
                else showDialog("Login Failed ! please use correct credentials");
            }
        });
        signupButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(signupPage);
            }
        });
    }
    private void createHeaderBar(){
        Button homeButton = new Button();
        Image image = new Image("C:\\Users\\Dell\\IdeaProjects\\ECommerce\\src\\pngegg.png");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(20);
        imageView.setFitWidth(80);
        homeButton.setGraphic(imageView);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Here");
        searchBar.setPrefWidth(250);

        Button searchButton = new Button("Search");
        signinButton = new Button("Sign In");
        welcomeLabel = new Label();

        Button cartButton = new Button("Cart");
        Button orderButton = new Button("My Orders");

        headerBar = new HBox();
        headerBar.setPadding(new Insets(10));
        headerBar.setSpacing(10);
        headerBar.setAlignment(Pos.CENTER);
        headerBar.getChildren().addAll(homeButton,searchBar,searchButton,signinButton,cartButton,orderButton);

        signinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(loginPage);
                headerBar.getChildren().remove(signinButton);
                footerBar.setVisible(false);
            }
        });
        cartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                VBox prodPage = productList.getProductsTocart(itemToCart);
                prodPage.setAlignment(Pos.CENTER);
                prodPage.setSpacing(10);
                prodPage.getChildren().add(placeOrderButton);
                body.getChildren().add(prodPage);
                footerBar.setVisible(false);
            }
        });
        placeOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(loggedInCutomer==null){
                    showDialog("Please login first to place an order!");
                    return;
                }
                if(itemToCart==null){
                    showDialog("Please add some product in the cart to place order");
                    return;
                }
                int count = Order.placeMultipleOrder(loggedInCutomer,itemToCart);
                if(count!=0){
                    showDialog("Order for "+count+" Products Placed Successfully!!");
                }
                else {
                    showDialog("Order Failed!!");
                }
            }
        });
        homeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                body.getChildren().clear();
                body.getChildren().add(productPage);
                footerBar.setVisible(true);
                if(loggedInCutomer==null && headerBar.getChildren().indexOf(signinButton)==-1){
                    headerBar.getChildren().add(signinButton);
                }
            }
        });
        orderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                orderpage = orderList.getAllOrder();
                body.getChildren().clear();
                body.getChildren().add(orderpage);
            }
        });
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String ser = searchBar.getText();
            }
        });
    }
    private void createfooterBar(){

        Button buyNowButton = new Button("Buy Now");
        Button addTocartButton = new Button("Add to Cart");

        footerBar = new HBox();
        footerBar.setPadding(new Insets(10));
        footerBar.setSpacing(10);
        footerBar.setAlignment(Pos.CENTER);
        footerBar.getChildren().addAll(buyNowButton,addTocartButton);

        buyNowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(loggedInCutomer==null){
                    showDialog("Please login first to place an order!");
                    return;
                }
                if(product==null){
                    showDialog("Please select a product to place an order!");
                    return;
                }
                boolean status = Order.placeOrder(loggedInCutomer,product);
                if(status==true){
                    showDialog("Order Placed Successfully!!");
                }
                else {
                    showDialog("Order Failed!!");
                }
            }
        });
        addTocartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Product product = productList.getSelectedProduct();
                if(product==null){
                    showDialog("Please select a product to add item into cart");
                    return;
                }
                itemToCart.add(product);
                showDialog("Product Successfully added to cart");
            }
        });
    }
    private void showDialog(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle("Message");
        alert.showAndWait();
    }
}
