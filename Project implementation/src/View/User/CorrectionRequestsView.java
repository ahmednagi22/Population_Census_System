package View.User;

import View.Utilities.CorrectionRequestTableColumn;
import View.Utilities.UserRequestTableColumn;
import Controller.UserViewController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author email_field
 */
public class CorrectionRequestsView extends Application {

    Stage viewCorrection;
    TableView<CorrectionRequestTableColumn> table = new TableView();
    CorrectionRequestTableColumn selectedColumn;
    UserRequestTableColumn UserRequest;
    TextField nameField;
    ComboBox education_field;
    TextField email_field;
    RadioButton Female;
    RadioButton Male;
    TextField Phone;
    TextField occupation_field;
    TextField address_field;
    DatePicker Date;

    int userID = 1;
    UserViewController userController;

    public CorrectionRequestsView() {
    }

    public CorrectionRequestsView(int userID) {
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

        VBox leftFormSection = new VBox(15);
        VBox rightFormSection = new VBox(20);
        HBox formSection = makeFormSection();
        Label formSectionTitle = makeFormSectionTitle();

        setTableProperties();

        TableColumn<CorrectionRequestTableColumn, String> UserRequest_ID = new TableColumn<>("UserRequest ID");
        TableColumn<CorrectionRequestTableColumn, String> Request_Title = new TableColumn<>("Request Title");
        TableColumn<CorrectionRequestTableColumn, String> Request_Content = new TableColumn<>("Request content");

        setColumnsValuesSources(UserRequest_ID, Request_Title, Request_Content);

        setColumnsProperties(UserRequest_ID, Request_Title, Request_Content);

        table.getColumns().addAll(UserRequest_ID, Request_Title, Request_Content);

        table.setItems(userController.getCorrectionRequests());
        table.setOnMouseClicked(e -> {
            tableClickeEvent();
        });

        ScrollPane scrollPane = new ScrollPane();
        addScrollPaneToTable(scrollPane);
        
        makeUpdateUserRequestForm(leftFormSection, rightFormSection, formSection, rightSection, formSectionTitle, scrollPane);
        
        addSectionsToAllSections(allSections, leftSection, rightSection);
        
        allSections.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#a5cee5"), CornerRadii.EMPTY, Insets.EMPTY)));

        addStageToScene(allSections, stage);
        
        stage.setTitle("User Screen");
        stage.setResizable(false);

        stage.show();
        viewCorrection = stage;
    }

    protected void addStageToScene(GridPane allSections, Stage stage) {
        Scene scene = new Scene(allSections, 900, 700);
        stage.setScene(scene);
    }

    protected void addSectionsToAllSections(GridPane allSections, VBox leftSection, VBox rightSection) {
        allSections.add(leftSection, 0, 0);
        allSections.add(rightSection, 1, 0);
    }

    protected void makeUpdateUserRequestForm(VBox leftFormSection, VBox rightFormSection, HBox formSection, VBox rightSection, Label formSectionTitle, ScrollPane scrollPane) {
        Label name = makeNameLable();
        
        makeNameTextField();
        
        HBox nameBox = makeNameBox(name);
        
        Label education = makeEducationLable();
        
        makeEducationComboBox();
        
        HBox educationBox = makeEducationBox(education);
        
        Label email = makeEmailLable();
        
        makeEmailTextField();
        
        HBox Email = makeEmailBox(email);
        Label sex = makeSexLable();
        
        ToggleGroup tg = new ToggleGroup();
        Male = new RadioButton("Male: ");
        Female = new RadioButton("Female");
        tg.getToggles().addAll(Male, Female);
        
        HBox sexBox = makeSexBox(sex);
        
        Label phone = makePhoneLable();
        
        makePhoneTextField();
        
        HBox phoneBox = makePhoneBox(phone);
        
        Label occupation = makeOccupationLable();
        
        makeOccupationField();
        
        HBox occupationBox = makeOccupationBox(occupation);
        
        Label address = makeAddressLable();
        
        makeAddressTextField();
        
        HBox Address = makeAddressBox(address);
        
        Label data = makeDateBox();
        Date = new DatePicker();
        
        HBox dateBox = makeDateBox(data);
        
        Button updateButton = makeUpdateButton();
        
        HBox updateButtonBox = makeUpdateButtonBox(updateButton);

        setMakeUpdateButtonAction(updateButton);
        ////////////////////////
        leftFormSection.getChildren().addAll(nameBox, educationBox, Email, phoneBox);
        rightFormSection.getChildren().addAll(sexBox, occupationBox, Address, dateBox);
        formSection.getChildren().addAll(leftFormSection, rightFormSection);
        rightSection.getChildren().addAll(formSectionTitle, scrollPane, formSection, updateButtonBox);
    }

    protected Label makeNameLable() {
        /////Name field//////
        Label name = new Label("Name : ");
        name.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 16));
        return name;
    }

    protected void makeNameTextField() {
        nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setStyle("-fx-background-radius: 30px ;");
        nameField.setMaxWidth(300);
    }

    protected HBox makeNameBox(Label name) {
        HBox nameBox = new HBox(30);
        nameBox.getChildren().addAll(name, nameField);
        return nameBox;
    }

    protected Label makeEducationLable() {
        //////education///////////////
        Label education = new Label("Education: ");
        education.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 16));
        return education;
    }

    protected void makeEducationComboBox() {
        education_field = new ComboBox();
        education_field.setPrefWidth(150);
    }

    protected HBox makeEducationBox(Label education) {
        HBox educationBox = new HBox(15);
        educationBox.getChildren().addAll(education, education_field);
        return educationBox;
    }

    protected Label makeEmailLable() {
        //////email //////////
        Label email = new Label("Email : ");
        email.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 16));
        return email;
    }

    protected void makeEmailTextField() {
        email_field = new TextField();
        email_field.setStyle("-fx-background-radius: 30px ;");
        email_field.setPromptText("Email");
        email_field.setMaxWidth(300);
    }

    protected HBox makeEmailBox(Label email) {
        HBox Email = new HBox(40);
        Email.getChildren().addAll(email, email_field);
        return Email;
    }

    protected Label makeSexLable() {
        ///////////sex /////////////
        Label sex = new Label("SEX :");
        sex.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 17));
        return sex;
    }

    protected HBox makeSexBox(Label sex) {
        HBox SexBox = new HBox(50);
        SexBox.getChildren().addAll(sex, Male, Female);
        return SexBox;
    }

    protected Label makePhoneLable() {
        ///////////////////phone/////////////
        Label phone = new Label("Phone : ");
        phone.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 17));
        return phone;
    }

    protected void makePhoneTextField() {
        Phone = new TextField();
        Phone.setStyle("-fx-background-radius: 30px ;");
        Phone.setPromptText("Phone");
        Phone.setMaxWidth(300);
    }

    protected HBox makePhoneBox(Label phone) {
        HBox phoneBox = new HBox(30);
        phoneBox.getChildren().addAll(phone, Phone);
        return phoneBox;
    }

    protected Label makeOccupationLable() {
        ///////////////////////////////////
        Label occupation = new Label("Occupation : ");
        occupation.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 17));
        return occupation;
    }

    protected void makeOccupationField() {
        occupation_field = new TextField();
        occupation_field.setStyle("-fx-background-radius: 30px ;");
        occupation_field.setPromptText("Occupation");
        occupation_field.setMaxWidth(300);
    }

    protected HBox makeOccupationBox(Label occupation) {
        HBox occupationBox = new HBox(10);
        occupationBox.getChildren().addAll(occupation, occupation_field);
        return occupationBox;
    }

    protected Label makeAddressLable() {
        ///////////////////////////////////////
        Label address = new Label("Address : ");
        address.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 17));
        return address;
    }

    protected void makeAddressTextField() {
        address_field = new TextField();
        address_field.setStyle("-fx-background-radius: 30px ;");
        address_field.setPromptText("Address");
        address_field.setMaxWidth(300);
    }

    protected HBox makeAddressBox(Label address) {
        HBox Address = new HBox(30);
        Address.getChildren().addAll(address, address_field);
        return Address;
    }

    protected Label makeDateBox() {
        //////Date////////////////
        Label data = new Label("Date : ");
        data.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 17));
        return data;
    }

    protected HBox makeDateBox(Label data) {
        HBox dateBox = new HBox(40);
        dateBox.getChildren().addAll(data, Date);
        return dateBox;
    }

    protected Button makeUpdateButton() {
        //////////////////button///////
        Button updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-radius: 300px ;-fx-background-color:Orange;");
        updateButton.setMinWidth(120);
        updateButton.setFont(Font.font("tahoma", FontWeight.BOLD, 15.5));
        updateButton.setTextFill(javafx.scene.paint.Color.BLACK);
        return updateButton;
    }

    protected HBox makeUpdateButtonBox(Button updateButton) {
        HBox updateButtonBox = new HBox(100);
        updateButtonBox.setPadding(new Insets(10, 0, 0, 260));
        updateButtonBox.getChildren().addAll(updateButton);
        return updateButtonBox;
    }

    protected void setMakeUpdateButtonAction(Button updateButton) {
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                String name = nameField.getText();
                String sex = "Male";
                String Occupation = occupation_field.getText();
                String address = address_field.getText();
                String education = education_field.getSelectionModel().getSelectedItem().toString();
                String date = Date.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                String email = email_field.getText();
                String phone = Phone.getText();
                String Sex = "Male";
                if (Female.isSelected()) {
                    Sex = "Female";
                }

                userController.updateUserRequest(UserRequest, name, sex, Occupation, address, education, date, email, phone, Sex);
                table.setItems(userController.getCorrectionRequests());

            }
        });
    }

    protected void addScrollPaneToTable(ScrollPane scrollPane) {
        scrollPane.setContent(table);
        scrollPane.pannableProperty().set(true);
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.fitToHeightProperty().set(true);
        scrollPane.setPrefHeight(300);
        scrollPane.setMaxWidth(900);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    protected void setColumnsProperties(TableColumn<CorrectionRequestTableColumn, String> UserRequest_ID, TableColumn<CorrectionRequestTableColumn, String> Request_Title, TableColumn<CorrectionRequestTableColumn, String> Request_Content) {
        UserRequest_ID.setPrefWidth(100);
        Request_Title.setPrefWidth(250);
        Request_Content.setPrefWidth(550);
    }

    protected void setColumnsValuesSources(TableColumn<CorrectionRequestTableColumn, String> UserRequest_ID, TableColumn<CorrectionRequestTableColumn, String> Request_Title, TableColumn<CorrectionRequestTableColumn, String> Request_Content) {
        UserRequest_ID.setCellValueFactory(new PropertyValueFactory("UserRequestID"));
        Request_Title.setCellValueFactory(new PropertyValueFactory("RequestTitle"));
        Request_Content.setCellValueFactory(new PropertyValueFactory("RequestContent"));
    }

    protected void setTableProperties() {
        table.setMinHeight(800);
        table.setMinWidth(900);
        table.setEditable(true);
    }

    protected Label makeFormSectionTitle() {
        Label formSectionTitle = new Label("Correction Requests");
        formSectionTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 30));
        formSectionTitle.setPadding(new Insets(5, 0, 0, 200));
        return formSectionTitle;
    }

    protected HBox makeFormSection() {
        HBox formSection = new HBox(50);
        formSection.setPadding(new Insets(5, 0, 0, 25));
        return formSection;
    }

    protected VBox makeRightSection() {
        //second section
        VBox rightSection = new VBox(15);
        rightSection.setPrefSize(700, 550);
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
                viewCorrection.close();
                c2.start(new Stage());
            }
        });

        Make_request.setMinSize(170, 140);
        Make_request.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Make_request.setFocusTraversable(false);
        Make_request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                MakeRequestView c2 = new MakeRequestView(userID);
                viewCorrection.close();
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
                viewCorrection.close();
                c2.start(new Stage());
            }
        });

        View_Correction.setMinSize(170, 140);
        View_Correction.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        View_Correction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                CorrectionRequestsView c2 = new CorrectionRequestsView(userID);
                viewCorrection.close();
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
                viewCorrection.close();
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

    private void tableClickeEvent() {
        selectedColumn = table.getSelectionModel().getSelectedItems().get(0);
        UserRequest = userController.getUserRequestTableColumn(selectedColumn.getUserRequestID());
        nameField.setText(UserRequest.getName());
        education_field.setValue(UserRequest.getEducation());
        email_field.setText(UserRequest.getEmail());
        Phone.setText(UserRequest.getPhone());
        occupation_field.setText(UserRequest.getOccupation());
        address_field.setText(UserRequest.getAddress());
        Date.setValue(LocalDate.parse(UserRequest.getDOB(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        if (UserRequest.getSex().equals("Male")) {
            Male.setSelected(true);
        } else {
            Female.setSelected(true);
        }

        education_field.getItems().addAll("Graduate", "Undergraduate", "Secondary school", "Preparatory school", "Primary school", "KG", "none");

    }

}
