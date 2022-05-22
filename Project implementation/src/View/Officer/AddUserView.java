package View.Officer;

import Controller.OfficerViewController;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import View.login.Login;

/**
 *
 * @author dell
 */
public class AddUserView extends Application {

    Stage addUser;
    int officerID;
    OfficerViewController officerController;
    TableView table = new TableView();

    public AddUserView() {
    }

    public AddUserView(int OfficerID) {
        this.officerID = OfficerID;
        officerController = new OfficerViewController(OfficerID);

    }

    @Override
    public void start(Stage stage) {

        GridPane allSections = new GridPane();
        allSections.setHgap(10);

// side btn section
        VBox leftSection = new VBox();
        leftSection.setPrefSize(170, 500);

        makeLeftSwitchTap(leftSection);

//second section
        VBox rightSection = new VBox(15);
        rightSection.setPrefSize(700, 500);

        makeAddUserForm(rightSection);

        allSections.add(leftSection, 0, 0);
        allSections.add(rightSection, 1, 0);
        allSections.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#a5cee5"), CornerRadii.EMPTY, Insets.EMPTY)));

        addSectionsToScene(allSections, stage);

        stage.setTitle("Officer Screen");
        stage.setResizable(false);

        stage.show();
        addUser = stage;
    }

    protected void addSectionsToScene(GridPane allSections, Stage stage) {
        Scene scene = new Scene(allSections, 900, 700);
        stage.setScene(scene);
    }

    protected void makeAddUserForm(VBox section2) {
        VBox fromLeftSection = new VBox(15);
        VBox formRightSection = new VBox(20);
        HBox formSections = new HBox(50);
        Label formRightSectionTitle = makeFormRightSectionTitle();

        /////Name field//////
        Label name = makeLable("Name :");

        TextField name_field = makeTextField("User's name");

        HBox NameBox = new HBox(30);
        NameBox.getChildren().addAll(name, name_field);
        NameBox.setPadding(new Insets(0, 20, 25, 10));
        ///////// education //////////

        Label education = makeLable("Education: ");

        ComboBox education_cbox = new ComboBox();
        education_cbox.getItems().addAll("Graduate", "Undergraduate", "Secondary school", "Preparatory school", "Primary school", "KG", "none");
        education_cbox.setValue("none");
        education_cbox.setPrefWidth(150);

        HBox educationBox = new HBox(20);
        educationBox.getChildren().addAll(education, education_cbox);
        educationBox.setPadding(new Insets(0, 20, 25, 10));
        //////email //////////
        Label Email = makeLable("Email : ");

        TextField Email_field = makeTextField("Email");

        HBox emailBox = new HBox(40);
        emailBox.setPadding(new Insets(0, 20, 25, 10));
        emailBox.getChildren().addAll(Email, Email_field);

        ///////////sex /////////////
        Label sex = makeLable("SEX :");

        RadioButton Male = new RadioButton("Male ");
        RadioButton Female = new RadioButton("Female");

        ToggleGroup tg = new ToggleGroup();
        tg.getToggles().addAll(Male, Female);

        HBox genderBox = new HBox(50);
        genderBox.getChildren().addAll(sex, Male, Female);
        genderBox.setPadding(new Insets(0, 20, 25, 10));

        ////////////////Occupation///////////////////
        Label Occupation = new Label("Occupation : ");
        Occupation.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 17));

        TextField Occupation_field = makeTextField("Occupation");

        HBox occupationBox = new HBox(10);
        occupationBox.getChildren().addAll(Occupation, Occupation_field);
        occupationBox.setPadding(new Insets(0, 20, 25, 10));

        ///////////////Address////////////////////////
        Label address = new Label("Address : ");
        address.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 17));

        TextField address_field = makeTextField("Address");
       
        HBox addressBox = new HBox(30);
        addressBox.getChildren().addAll(address, address_field);
        addressBox.setPadding(new Insets(0, 20, 15, 10));

        //////date////////////////////
        HBox dateBox = new HBox(30);
        Label date = new Label("Date : ");
        date.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 17));
        DatePicker calendar = new DatePicker();
        dateBox.getChildren().addAll(date, calendar);
        dateBox.setPadding(new Insets(0, 20, 25, 10));

        /////////////phone///////////////////
        Label Phone = new Label("Phone : ");
        Phone.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 17));

        TextField Phone_field = makeTextField("Phone");

        HBox phoneBox = new HBox(30);
        phoneBox.getChildren().addAll(Phone, Phone_field);
        phoneBox.setPadding(new Insets(0, 20, 18, 10));

        ////////////username/////////////
        Label user_name = new Label("User Name : ");
        user_name.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 16));

        TextField user_name_field = makeTextField("User Name");

        HBox usernameBox = new HBox(1);
        usernameBox.getChildren().addAll(user_name, user_name_field);
        usernameBox.setPadding(new Insets(0, 20, 25, 10));

        /////////// password /////////////
        Label pass = new Label("Password : ");
        pass.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 16));

        PasswordField pass_field = makePasswordField();

        HBox passwordBox = new HBox(10);
        passwordBox.getChildren().addAll(pass, pass_field);
        passwordBox.setPadding(new Insets(0, 20, 25, 10));

        //////////////////button/////////////////
        HBox formButtons = new HBox(100);
        formButtons.setPadding(new Insets(10, 0, 0, 150));

        Button reset_botton = makeResetButton();

        Button AddButton = makeAddButton();

        formButtons.getChildren().addAll(reset_botton, AddButton);

        setAddRequestButtonAction(AddButton, name_field, Occupation_field, address_field, education_cbox, calendar, Email_field, Phone_field, user_name_field, pass_field, Female, Male);

        setResetButtonAction(reset_botton, name_field, Male, Female, Occupation_field, address_field, education_cbox, calendar, Email_field, Phone_field, user_name_field, pass_field);

////////////////////////
        fromLeftSection.getChildren().addAll(NameBox, educationBox, emailBox, phoneBox, usernameBox);
        formRightSection.getChildren().addAll(genderBox, occupationBox, addressBox, dateBox, passwordBox);
        formSections.getChildren().addAll(fromLeftSection, formRightSection);
        section2.getChildren().addAll(formRightSectionTitle, formSections, formButtons);
    }

    protected PasswordField makePasswordField() {
        PasswordField pass_field = new PasswordField();
        pass_field.setMaxWidth(300);
        pass_field.setStyle("-fx-background-radius: 30px ;");
        pass_field.setPromptText("User Password");
        return pass_field;
    }

    protected void setResetButtonAction(Button reset_botton, TextField name_field, RadioButton Male, RadioButton Female, TextField Occupation_field, TextField address_field, ComboBox education_cbox, DatePicker calendar, TextField Email_field, TextField Phone_field, TextField user_name_field, PasswordField pass_field) {
        reset_botton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                resetAddUserForm();
            }

            protected void resetAddUserForm() {
                // clear the fields
                name_field.setText("");
                Male.setSelected(false);
                Female.setSelected(false);
                Occupation_field.setText("");
                address_field.setText("");
                education_cbox.setValue("none");
                calendar.setValue(null);
                Email_field.setText("");
                Phone_field.setText("");
                user_name_field.setText("");
                pass_field.setText("");
            }
        });
    }

    protected void setAddRequestButtonAction(Button makeRequest, TextField name_field, TextField Occupation_field, TextField address_field, ComboBox education_cbox, DatePicker calendar, TextField Email_field, TextField Phone_field, TextField user_name_field, PasswordField pass_field, RadioButton Female, RadioButton Male) {
        makeRequest.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                try {
                    String name = name_field.getText();
                    String sex = "Male";
                    String Occupation = Occupation_field.getText();
                    String address = address_field.getText();
                    String education = education_cbox.getSelectionModel().getSelectedItem().toString();
                    String date = calendar.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    String email = Email_field.getText();
                    String phone = Phone_field.getText();
                    String username = user_name_field.getText();
                    String password = pass_field.getText(); //we should add them later to the table of usernaems and passwords
                    if (Female.isSelected()) {
                        sex = "Female";
                    }
                    officerController.addUser(name, address, education, phone, email, date, sex, Occupation, username, password, officerID);
                    resetAddUserForm();
                } catch (Exception e) {
                }

            }

            protected void resetAddUserForm() {
                // clear the fields
                name_field.setText("");
                Male.setSelected(false);
                Female.setSelected(false);
                Occupation_field.setText("");
                address_field.setText("");
                education_cbox.setValue("none");
                calendar.setValue(null);
                Email_field.setText("");
                Phone_field.setText("");
                user_name_field.setText("");
                pass_field.setText("");
            }
        });
    }

    protected Button makeAddButton() {
        Button makeRequest = new Button(" Add ");
        makeRequest.setStyle("-fx-background-radius: 300px ;-fx-background-color:Orange;");
        makeRequest.setMinWidth(120);
        makeRequest.setFont(Font.font("tahoma", FontWeight.BOLD, 15.5));
        makeRequest.setTextFill(javafx.scene.paint.Color.BLACK);
        return makeRequest;
    }

    protected Button makeResetButton() {
        Button reset_botton = new Button(" Reset ");
        reset_botton.setStyle("-fx-background-radius: 300px ;-fx-background-color:Red; ");
        reset_botton.setMinWidth(120);
        reset_botton.setFont(Font.font("tahoma", FontWeight.BOLD, 15.5));
        reset_botton.setTextFill(javafx.scene.paint.Color.BLACK);
        return reset_botton;
    }

    protected Label makeFormRightSectionTitle() {
        Label formRightSectionTitle = new Label("Add New User");
        formRightSectionTitle.setFont(Font.font("Garamond", FontWeight.EXTRA_BOLD, 30));
        formRightSectionTitle.setPadding(new Insets(50, 0, 75, 140));
        return formRightSectionTitle;
    }

    protected void makeLeftSwitchTap(VBox leftSection) {
        Button View_my_family = new Button("Add User");
        Button View_Correction = new Button("   View New \n"
                + "User Request");
        Button Logout = new Button("Logout");

        View_my_family.setMinSize(170, 233);
        View_my_family.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        View_my_family.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                AddUserView c2 = new AddUserView(officerID);
                addUser.close();
                c2.start(new Stage());
            }
        });

        View_Correction.setMinSize(170, 233);
        View_Correction.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        View_Correction.setFocusTraversable(false);
        View_Correction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                NewRequestsView c2 = new NewRequestsView(officerID);
                addUser.close();
                c2.start(new Stage());
            }
        });

        Logout.setMinSize(170, 234);
        Logout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#bf1f21"), CornerRadii.EMPTY, Insets.EMPTY)));
        Logout.setBorder(new Border(new BorderStroke((javafx.scene.paint.Color.web("#79b5d9")), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Logout.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                Login c2 = new Login();
                addUser.close();
                c2.start(new Stage());
            }
        });

        leftSection.getChildren().addAll(View_my_family, View_Correction, Logout);
    }

    private Label makeLable(String textInsideLable) {
        Label name = new Label(textInsideLable);
        name.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 20));
        return name;
    }

    private TextField makeTextField(String proptText) {
        TextField name_field = new TextField();
        name_field.setPromptText(proptText);
        name_field.setStyle("-fx-background-radius: 10px ;");
        name_field.setPrefSize(150, 23);
        return name_field;
    }

}
