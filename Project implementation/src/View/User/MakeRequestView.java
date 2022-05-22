package View.User;

import Controller.UserViewController;
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
public class MakeRequestView extends Application {

    Stage make_Request;
    int userID = 1;
    UserViewController userController;
    TableView table = new TableView();

    public MakeRequestView() {
    }

    public MakeRequestView(int userID) {
        this.userID = userID;
        userController = new UserViewController(userID);
    }

    @Override
    public void start(Stage stage) {

        GridPane allSections = new GridPane();
        allSections.setHgap(10);

        VBox leftSection = makeLeftSection();

        makeLeftSwitchTap(leftSection);

        VBox rightSection = makeRightSection();
        Label rightSectionTitle = makeRightSectionTitle();

        HBox formSection = new HBox(50);
        VBox leftFormSection = new VBox(15);
        VBox rightFormSection = new VBox(20);

        makeMakeRequestForm(leftFormSection, rightFormSection, formSection, rightSection, rightSectionTitle);
        
        addToAllSections(allSections, leftSection, rightSection);
        
        allSections.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#a5cee5"), CornerRadii.EMPTY, Insets.EMPTY)));

        addStageToScene(allSections, stage);
        
        stage.setTitle("User Screen");
        stage.setResizable(false);

        stage.show();
        make_Request = stage;
    }

    protected void addStageToScene(GridPane allSections, Stage stage) {
        Scene scene = new Scene(allSections, 900, 700);
        stage.setScene(scene);
    }

    protected void addToAllSections(GridPane allSections, VBox leftSection, VBox rightSection) {
        allSections.add(leftSection, 0, 0);
        allSections.add(rightSection, 1, 0);
    }

    protected void makeMakeRequestForm(VBox leftFormSection, VBox rightFormSection, HBox formSection, VBox rightSection, Label rightSectionTitle) {
        Label name = makeNameLable();
        
        TextField name_field = makeNameTextFeild();
        
        HBox NameBox = new HBox(30);
        NameBox.getChildren().addAll(name, name_field);
        NameBox.setPadding(new Insets(0, 20, 25, 10));

        Label education = makeEducationLable();
        
        ComboBox education_cbox = new ComboBox();
        education_cbox.getItems().addAll("Graduate", "Undergraduate", "Secondary school", "Preparatory school", "Primary school", "KG", "none");
        education_cbox.setValue("none");
        education_cbox.setPrefWidth(150);
        
        HBox educationBox = new HBox(20);
        educationBox.getChildren().addAll(education, education_cbox);
        educationBox.setPadding(new Insets(0, 20, 25, 10));

        Label Email = makeEmailLable();
        
        TextField Email_field = new TextField();
        Email_field.setStyle("-fx-background-radius: 30px ;");
        Email_field.setPromptText("Email");
        Email_field.setMaxWidth(300);
        
        HBox emailBox = new HBox(40);
        emailBox.getChildren().addAll(Email, Email_field);
        emailBox.setPadding(new Insets(0, 20, 25, 10));

        Label sex = makeSexLable();
        
        ToggleGroup tg = new ToggleGroup();
        RadioButton Male = new RadioButton("Male ");
        RadioButton Female = new RadioButton("Female");
        tg.getToggles().addAll(Male, Female);
        
        HBox sexBox = new HBox(50);
        sexBox.getChildren().addAll(sex, Male, Female);
        sexBox.setPadding(new Insets(0, 20, 25, 10));

        Label Occupation = makeOccupationLable();
        
        TextField Occupation_field = makeOccupationTextField();
        
        HBox occupationBox = new HBox(10);
        occupationBox.getChildren().addAll(Occupation, Occupation_field);
        occupationBox.setPadding(new Insets(0, 20, 25, 10));

        Label address = makeAddressLable();
        
        TextField address_field = makeAddressTextField();
        
        HBox addressBox = new HBox(30);
        addressBox.getChildren().addAll(address, address_field);
        addressBox.setPadding(new Insets(0, 20, 15, 10));

        Label date = makeDateLable();
        
        DatePicker calendar = new DatePicker();
        
        HBox dataBox = new HBox(30);
        dataBox.getChildren().addAll(date, calendar);
        dataBox.setPadding(new Insets(0, 20, 25, 10));

        Label Phone = makePhoneLable();
        
        TextField Phone_field = makePhoneTextField();
        
        HBox phoneBox = new HBox(30);
        phoneBox.getChildren().addAll(Phone, Phone_field);
        phoneBox.setPadding(new Insets(0, 20, 18, 10));

        //////////////////button/////////////////
        HBox buttons = new HBox(100);
        buttons.setPadding(new Insets(10, 0, 0, 150));
        
        Button reset_botton = makeResetBotton();
        
        Button makeRequest = makeMakeRequestButton(buttons, reset_botton);
        setMakeRequestButtonAction(makeRequest, name_field, Occupation_field, address_field, education_cbox, calendar, Email_field, Phone_field, Female, Male);
        setResetButtonAction(reset_botton, name_field, Male, Female, Occupation_field, address_field, education_cbox, calendar, Email_field, Phone_field);
        ////////////////////////
        leftFormSection.getChildren().addAll(NameBox, educationBox, emailBox, phoneBox);
        rightFormSection.getChildren().addAll(sexBox, occupationBox, addressBox, dataBox);
        formSection.getChildren().addAll(leftFormSection, rightFormSection);
        rightSection.getChildren().addAll(rightSectionTitle, formSection, buttons);
    }

    protected void setResetButtonAction(Button reset_botton, TextField name_field, RadioButton Male, RadioButton Female, TextField Occupation_field, TextField address_field, ComboBox education_cbox, DatePicker calendar, TextField Email_field, TextField Phone_field) {
        /////////// reset botton /////////////
        reset_botton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                // clear the fields
                name_field.setText("");
                Male.setSelected(false);
                Female.setSelected(false);
//                area_cbox.setValue(areas.get(0).getAreaName());
Occupation_field.setText("");
address_field.setText("");
education_cbox.setValue("none");
calendar.setValue(null);
Email_field.setText("");
Phone_field.setText("");
            }
        });
    }

    protected void setMakeRequestButtonAction(Button makeRequest, TextField name_field, TextField Occupation_field, TextField address_field, ComboBox education_cbox, DatePicker calendar, TextField Email_field, TextField Phone_field, RadioButton Female, RadioButton Male) {
        ////////////// add botton /////////////////
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
                    if (Female.isSelected()) {
                        sex = "Female";
                    }
                    userController.addUserRequest(name, address, education, phone, email, date, sex, Occupation, userID);
                    // clear the fields
                    name_field.setText("");
                    Male.setSelected(false);
                    Female.setSelected(false);
//                area_cbox.setValue(areas.get(0).getAreaName());
Occupation_field.setText("");
address_field.setText("");
education_cbox.setValue("none");
calendar.setValue(null);
Email_field.setText("");
Phone_field.setText("");
                } catch (Exception e) {
                }

            }
        });
    }

    protected Button makeMakeRequestButton(HBox buttons, Button reset_botton) {
        Button makeRequest = new Button(" Make Request ");
        makeRequest.setStyle("-fx-background-radius: 300px ;-fx-background-color:Orange;");
        makeRequest.setMinWidth(120);
        makeRequest.setFont(Font.font("tahoma", FontWeight.BOLD, 15.5));
        makeRequest.setTextFill(javafx.scene.paint.Color.BLACK);
        buttons.getChildren().addAll(reset_botton, makeRequest);
        return makeRequest;
    }

    protected Button makeResetBotton() {
        Button reset_botton = new Button(" Reset ");
        reset_botton.setStyle("-fx-background-radius: 300px ;-fx-background-color:Red; ");
        reset_botton.setMinWidth(120);
        reset_botton.setFont(Font.font("tahoma", FontWeight.BOLD, 15.5));
        reset_botton.setTextFill(javafx.scene.paint.Color.BLACK);
        return reset_botton;
    }

    protected TextField makePhoneTextField() {
        TextField Phone_field = new TextField();
        Phone_field.setStyle("-fx-background-radius: 30px ;");
        Phone_field.setPromptText("Phone");
        Phone_field.setMaxWidth(300);
        return Phone_field;
    }

    protected Label makePhoneLable() {
        /////////////phone///////////////////
        Label Phone = new Label("Phone : ");
        Phone.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 17));
        return Phone;
    }

    protected Label makeDateLable() {
        //////date////////////////////
        Label date = new Label("Date : ");
        date.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 17));
        return date;
    }

    protected TextField makeAddressTextField() {
        TextField address_field = new TextField();
        address_field.setStyle("-fx-background-radius: 30px ;");
        address_field.setPromptText("Address");
        address_field.setMaxWidth(300);
        return address_field;
    }

    protected Label makeAddressLable() {
        ///////////////Address////////////////////////
        Label address = new Label("Address : ");
        address.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 17));
        return address;
    }

    protected TextField makeOccupationTextField() {
        TextField Occupation_field = new TextField();
        Occupation_field.setStyle("-fx-background-radius: 30px ;");
        Occupation_field.setPromptText("Occupation");
        Occupation_field.setMaxWidth(300);
        return Occupation_field;
    }

    protected Label makeOccupationLable() {
        ////////////////Occupation///////////////////
        Label Occupation = new Label("Occupation : ");
        Occupation.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 17));
        return Occupation;
    }

    protected Label makeSexLable() {
        ///////////sex /////////////
        Label sex = new Label("SEX :");
        sex.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 17));
        return sex;
    }

    protected Label makeEmailLable() {
        //////email //////////
        Label Email = new Label("Email : ");
        Email.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 16));
        return Email;
    }

    protected Label makeEducationLable() {
        ///////// education //////////
        Label education = new Label("Education: ");
        education.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 16));
        return education;
    }

    protected TextField makeNameTextFeild() {
        TextField name_field = new TextField();
        name_field.setPromptText("User's name");
        name_field.setStyle("-fx-background-radius: 10px ;");
        name_field.setPrefSize(150, 23);
        return name_field;
    }

    protected Label makeNameLable() {
        /////Name field//////
        Label name = new Label("Name :");
        name.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 20));
        return name;
    }

    protected Label makeRightSectionTitle() {
        Label rightSectionTitle = new Label("Add New Family Member");
        rightSectionTitle.setFont(Font.font("Garamond", FontWeight.EXTRA_BOLD, 30));
        rightSectionTitle.setPadding(new Insets(50, 0, 75, 140));
        return rightSectionTitle;
    }

    protected VBox makeRightSection() {
        //second section
        VBox rightSection = new VBox(15);
        rightSection.setPrefSize(700, 500);
        return rightSection;
    }

    protected void makeLeftSwitchTap(VBox leftSection) {
        Button View_my_family = new Button("View My Family\n" + "Members");
        Button Make_request = new Button("Make a request\n" + "for Adding New\n" + "member");
        Button view_request = new Button("View Your\n" + "Requests");
        Button View_Correction = new Button("View Correction\n" + "Requests");
        Button Logout = new Button("Logout");

        View_my_family.setMinSize(170, 140);
        View_my_family.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        View_my_family.setFocusTraversable(false);
        View_my_family.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                FamilyMembersView c2 = new FamilyMembersView(userID);
                make_Request.close();
                c2.start(new Stage());
            }
        });

        Make_request.setMinSize(170, 140);
        Make_request.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Make_request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                MakeRequestView c2 = new MakeRequestView(userID);
                make_Request.close();
                c2.start(new Stage());
            }
        });

        view_request.setMinSize(170, 140);
        view_request.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        view_request.setFocusTraversable(false);
        view_request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                YourRequestView c2 = new YourRequestView(userID);
                make_Request.close();
                c2.start(new Stage());
            }
        });

        View_Correction.setMinSize(170, 140);
        View_Correction.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        View_Correction.setFocusTraversable(false);
        View_Correction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                CorrectionRequestsView c2 = new CorrectionRequestsView(userID);
                make_Request.close();
                c2.start(new Stage());
            }
        });

        Logout.setMinSize(170, 160);
        Logout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#bf1f21"), CornerRadii.EMPTY, Insets.EMPTY)));
        Logout.setBorder(new Border(new BorderStroke((javafx.scene.paint.Color.web("#79b5d9")), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Logout.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                Login c2 = new Login();
                make_Request.close();
                c2.start(new Stage());
            }
        });

        leftSection.getChildren().addAll(View_my_family, Make_request, view_request, View_Correction, Logout);
    }

    protected VBox makeLeftSection() {
        // side btn section
        VBox leftSection = new VBox();
        leftSection.setPrefSize(170, 500);
        return leftSection;
    }

}
