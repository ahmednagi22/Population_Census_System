package View.Admin;

import Controller.AdminViewController;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import View.login.Login;

/**
 *
 * @author user
 */
public class AddOfficerView extends Application {

    Stage addOfficer;
    int adminID;
    AdminViewController adminController;

    public AddOfficerView() {
    }

    public AddOfficerView(int adminID) {
        this.adminID = adminID;
        adminController = new AdminViewController(adminID);
    }

    @Override
    public void start(Stage stage) {
        HBox stageSections = new HBox();

        VBox leftSection = makeLeftSection();

        MakeSwitchTap(leftSection);

        VBox rightSection = makeRightSection();

        MakeAddOfficerForm(rightSection);

        addAllSections(stageSections, leftSection, rightSection);

        addSectionsToScene(stageSections, stage);

        stage.setTitle("Admin Screen");
        stage.setResizable(false);

        stage.show();
        addOfficer = stage;
    }

    protected void addSectionsToScene(HBox stageSections, Stage stage) {
        Scene scene = new Scene(stageSections, 800, 700);
        stage.setScene(scene);
    }

    protected void addAllSections(HBox stageSections, VBox leftSection, VBox rightSection) {
        stageSections.getChildren().addAll(leftSection, rightSection);
        stageSections.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#a5cee5"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    protected VBox makeRightSection() {
        VBox rightSection = new VBox();
        rightSection.setAlignment(Pos.TOP_CENTER);
        rightSection.setPrefSize(530, 500);
        return rightSection;
    }

    protected VBox makeLeftSection() {
        VBox leftSection = new VBox();
        leftSection.setPrefSize(170, 500);
        return leftSection;
    }

    protected void MakeAddOfficerForm(VBox rightSection) {
        //Name Box
        Label rightSectionTitle = setStageTitle();
        HBox nameBox = MakeBox();
        Label name = MakeLable("NAME       ");
        TextField name_field = MakeTextFeild("Officer name  ");
        nameBox.getChildren().addAll(name, name_field);

        //sex box
        HBox sexBox = MakeBox();
        Label sex = MakeLable("SEX\t \t ");
        RadioButton male = MakeMaleButton();
        RadioButton female = MakeFemaleButton();
        sexBox.getChildren().addAll(sex, male, female);
        addToToggleSection(male, female);
        
        //area box
        HBox areaBox = MakeBox();
        Label area = MakeLable("Area       ");
        ComboBox areaComboBox = new ComboBox();
        FillAreaComboBox(areaComboBox);
        areaBox.getChildren().addAll(area, areaComboBox);

        //email box
        HBox emailBox = MakeBox();
        Label Email = MakeLable("Email       ");
        TextField Email_field = MakeTextFeild("Officer Email  ");
        emailBox.getChildren().addAll(Email, Email_field);

        //phone box
        HBox phoneBox = MakeBox();
        Label phone = MakeLable("Phone       ");
        TextField phone_field = MakeTextFeild("Officer Phone number  ");
        phoneBox.getChildren().addAll(phone, phone_field);

        //user name field
        HBox usernameBox = MakeBox();
        Label user_name = MakeLable("User Name");
        TextField user_name_field = MakeTextFeild("Officer user name");
        usernameBox.getChildren().addAll(user_name, user_name_field);

        //password field
        HBox passwordBox = MakeBox();
        Label pass = MakeLable("password");
        PasswordField pass_field = MakePasswordFeild("Officer Password");
        passwordBox.getChildren().addAll(pass, pass_field);
        
//button field

        HBox add_officer_btn_Box = new HBox(100);
        add_officer_btn_Box.setPadding(new Insets(10, 0, 0, 190));
        Button add_officer_btn = MakeButton("Add Officer");  
        add_officer_btn_Box.getChildren().addAll(add_officer_btn);
        
        
        setAddOfficerButtonAction(add_officer_btn, name_field, areaComboBox, Email_field, phone_field, user_name_field, pass_field, female, male);

        //add All Sections to right section
        rightSection.getChildren().addAll(rightSectionTitle, nameBox, sexBox, areaBox, emailBox, phoneBox, usernameBox, passwordBox, add_officer_btn_Box);
    }

    protected void addToToggleSection(RadioButton male, RadioButton female) {
        ToggleGroup sexToggleSection = new ToggleGroup();
        sexToggleSection.getToggles().addAll(male, female);
    }

    protected Button MakeButton(String name) {
        // add officer btn
        Button add_officer_btn = new Button(name);
        add_officer_btn.setPrefSize(200, 50);
        return add_officer_btn;
    }

    protected void setAddOfficerButtonAction(Button add_officer_btn, TextField name_field, ComboBox areaComboBox, TextField Email_field, TextField phone_field, TextField user_name_field, PasswordField pass_field, RadioButton female, RadioButton male) {
        add_officer_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                String name = name_field.getText();
                String sex = "Male";
                String area = areaComboBox.getSelectionModel().getSelectedItem().toString();
                String email = Email_field.getText();
                String phone = phone_field.getText();
                String username = user_name_field.getText();
                String password = pass_field.getText(); //we should add them later to the table of usernaems and passwords
                if (female.isSelected()) {
                    sex = "Female";
                }

                resetAddOfficerForm();

                adminController.addOfficer(phone, email, name, area, sex, username, password);
            }

            protected void resetAddOfficerForm() {
                name_field.setText("");
                Email_field.setText("");
                user_name_field.setText("");
                phone_field.setText("");
                pass_field.setText("");
                male.setSelected(false);
                female.setSelected(false);
            }
        });
    }

    protected PasswordField MakePasswordFeild(String promptText) {
        PasswordField pass_field = new PasswordField();
        pass_field.setPrefSize(300, 40);
        pass_field.setPromptText(promptText);
        return pass_field;
    }

    protected void FillAreaComboBox(ComboBox areaComboBox) {
        ArrayList<String> areaNames = adminController.getAreasForComboBox();
        areaComboBox.setValue(areaNames.get(0));
        for (int i = 0; i < areaNames.size(); i++) {
            areaComboBox.getItems().add(areaNames.get(i));
        }
        areaComboBox.setPrefSize(300, 40);
    }

    protected RadioButton MakeFemaleButton() {
        RadioButton female = new RadioButton("Female");
        female.setFont(Font.font("arial", FontWeight.MEDIUM, 15));
        female.setPadding(new Insets(0, 0, 0, 33));
        return female;
    }

    protected RadioButton MakeMaleButton() {
        RadioButton male = new RadioButton("Male");
        male.setFont(Font.font("arial", FontWeight.MEDIUM, 15));
        male.setPadding(new Insets(0, 60, 0, 60));
        return male;
    }

    protected Label MakeLable(String title) {
        Label sex = new Label(title);
        sex.setFont(Font.font("arial", FontWeight.MEDIUM, 15));
        sex.setLayoutX(10);
        return sex;
    }

    protected TextField MakeTextFeild(String PromptText) {
        TextField name_field = new TextField();
        name_field.setPromptText(PromptText);
        name_field.setPrefSize(300, 40);
        return name_field;
    }

    protected HBox MakeBox() {
        //name field
        HBox h_name = new HBox();
        h_name.setPadding(new Insets(30, 0, 0, 0));
        h_name.setAlignment(Pos.CENTER);
        return h_name;
    }

    protected Label setStageTitle() {
        Label stage2_title = new Label("\tAdd Officer \n Officer information");
        stage2_title.setFont(Font.font("Garamond", FontWeight.BOLD, 30));
        return stage2_title;
    }

    protected void MakeSwitchTap(VBox leftSection) {
        Button Add_Officer = new Button("Add Officer");
        Button Officers_list = new Button("Officers list");
        Button Make_report = new Button("Make a report");
        Button Logout = new Button("Logout");

        Add_Officer.setMinSize(170, 175);
        Add_Officer.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Add_Officer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                AddOfficerView c2 = new AddOfficerView(adminID);
                addOfficer.close();
                c2.start(new Stage());
            }
        });
        Officers_list.setMinSize(170, 175);
        Officers_list.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Officers_list.setFocusTraversable(false);
        Officers_list.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                OfficerListView c2 = new OfficerListView(adminID);
                addOfficer.close();
                c2.start(new Stage());
            }
        });

        Make_report.setMinSize(170, 175);
        Make_report.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Make_report.setFocusTraversable(false);
        Make_report.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                MakeReportView c2 = new MakeReportView(adminID);
                addOfficer.close();
                c2.start(new Stage());
            }
        });

        Logout.setMinSize(170, 175);
        Logout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#bf1f21"), CornerRadii.EMPTY, Insets.EMPTY)));
        Logout.setBorder(new Border(new BorderStroke((javafx.scene.paint.Color.web("#79b5d9")), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Logout.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                Login c2 = new Login();
                addOfficer.close();
                c2.start(new Stage());
            }
        });

        leftSection.getChildren().addAll(Add_Officer, Officers_list, Make_report, Logout);
    }

}
