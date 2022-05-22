/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import Controller.LoginViewController;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author userName_field
 */
public class Login extends Application {

    Stage loginStage;
    String userName;
    String password;
    LoginViewController loginController = new LoginViewController();

    @Override
    public void start(Stage primaryStage) {

        GridPane allSections = new GridPane();

        ////////////////////////////////////////
        Pane leftSection = new Pane();
        leftSection.setPrefSize(400, 700);
        ImageView WelcomImage = makeWelcomeImage();
        Label firstPartLable = makeFirstPartLable();
        Label secondPartLable = makeSecondPartLable();

        leftSection.getChildren().addAll(firstPartLable, secondPartLable, WelcomImage);
        setLeftSectionProperties(leftSection);
        allSections.add(leftSection, 0, 0);

        ///////////////////////////////////////
        Pane rightSection = makeRightSection();

        ImageView LoginImage = makeLoginImage();
        /////////////////////////////////////////////
        Label email = makeLable("Email");

        TextField userName_field = makeUsernameField();

        Label pass = makePasswordLable();

        PasswordField password_Field = makePasswordField();

        Button login = makeLoginButton();

        setLoginButtonAction(login, userName_field, password_Field);

        rightSection.getChildren().addAll(email, userName_field, pass, password_Field, login, LoginImage);
        allSections.add(rightSection, 1, 0);

        Scene LoginScene = new Scene(allSections, 700, 500);
        primaryStage.setScene(LoginScene);
        LoginScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                if(key.getCode().equals(KeyCode.ENTER)){
                    login.fire();
                }
            }

        });
        
        primaryStage.setTitle("Login Screen");
        primaryStage.setResizable(false);

        primaryStage.show();
        loginStage = primaryStage;
    }

    protected Label makePasswordLable() {
        Label pass = new Label("Password");
        pass.setLayoutY(240);
        pass.setLayoutX(25);
        pass.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 17));
        return pass;
    }

    protected void setLoginButtonAction(Button login, TextField userName_field, PasswordField password_Field) {
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                userName = userName_field.getText();
                password = password_Field.getText();
                boolean userExsist = loginController.checkIfUserExsist(userName, password);
                if (userExsist) {
                    loginController.openSystemUserAccount(userName, password);
                    loginStage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("User Not Found");
                    alert.setContentText("Username or Password is Wrong!");
                    alert.showAndWait();
                }
            }

        });
    }

    protected Button makeLoginButton() {
        Button login = new Button("Login");
        login.setStyle("-fx-background-radius: 300px ;");
        login.setLayoutY(320);
        login.setLayoutX(135);
        login.setPrefWidth(100);
        return login;
    }

    protected PasswordField makePasswordField() {
        PasswordField password_Field = new PasswordField();
        password_Field.setStyle("-fx-background-radius: 30px ;");
        password_Field.setPromptText("Password");
        password_Field.setLayoutY(270);
        password_Field.setLayoutX(25);
        password_Field.setPrefWidth(330);
        return password_Field;
    }

    protected TextField makeUsernameField() {
        TextField userName_field = new TextField();
        userName_field.setStyle("-fx-background-radius: 30px ;");
        userName_field.setPromptText("Email");
        userName_field.setLayoutY(180);
        userName_field.setLayoutX(25);
        userName_field.setPrefWidth(330);
        return userName_field;
    }

    private Label makeLable(String textInsideLable) {
        Label lable = new Label(textInsideLable);
        lable.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 17));
        lable.setLayoutY(150);
        lable.setLayoutX(25);
        return lable;

    }

    protected ImageView makeLoginImage() {
        ImageView LoginImage = new ImageView("Images/240_F_31331324_bqXgqwmlnnXaOeXwFv8CrO6oMHpAKPum.jpg");
        LoginImage.setFitWidth(300);
        LoginImage.setFitHeight(100);
        LoginImage.setLayoutY(40);
        LoginImage.setLayoutX(25);
        return LoginImage;
    }

    protected Pane makeRightSection() {
        /////////////////////////////////////////////
        Pane rightSection = new Pane();
        rightSection.setPadding(new Insets(0, 10, 0, 30));
        rightSection.setPrefSize(450, 700);
        rightSection.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2))));
        rightSection.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        return rightSection;
    }

    protected void setLeftSectionProperties(Pane leftSection) {
        leftSection.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
        leftSection.setBackground(new Background(new BackgroundFill(Color.web("#2596be"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    protected Label makeSecondPartLable() {
        Label secondPartLable = new Label("Population_Census_System -->");
        secondPartLable.setLayoutY(248);
        secondPartLable.setFont(Font.font("Arial", FontWeight.BOLD, 21));
        secondPartLable.setPadding(new Insets(0, 0, 0, 0));
        secondPartLable.setLayoutX(4);
        secondPartLable.setTextFill(Color.WHITE);
        return secondPartLable;
    }

    protected Label makeFirstPartLable() {
        Label firstPartLable = new Label("Welcome to");
        firstPartLable.setLayoutY(220);
        firstPartLable.setLayoutX(95);
        firstPartLable.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 22));
        firstPartLable.setTextFill(Color.WHITE);
        return firstPartLable;
    }

    protected ImageView makeWelcomeImage() {
        ImageView image = new ImageView("Images/members.png");
        image.setFitWidth(250);
        image.setFitHeight(150);
        image.setLayoutY(90);
        image.setLayoutX(40);
        return image;
    }

}
