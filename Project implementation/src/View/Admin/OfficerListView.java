package View.Admin;

import View.Utilities.OfficerTableColumn;
import Controller.AdminViewController;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * @author email
 */
public class OfficerListView extends Application {

    Stage officerList;
    TableView<OfficerTableColumn> table = new TableView();
    OfficerTableColumn selectedColumn;
    TextField nameField;
    TextField email;
    TextField username;
    PasswordField Password;
    RadioButton Female;
    RadioButton Male;
    TextField Phone;
    ComboBox Area;
    int adminID;
    AdminViewController adminController;

    public OfficerListView() {
    }

    public OfficerListView(int adminID) {
        this.adminID = adminID;
        adminController = new AdminViewController(adminID);
    }

    @Override
    public void start(Stage stage) {

        GridPane stageSections = new GridPane();
        stageSections.setHgap(10);

// side btn section
        VBox leftSection = new VBox();
        leftSection.setPrefSize(170, 500);

        MakeLeftTapSwitch(leftSection);

//second section
        VBox rightSection = new VBox(15);
        rightSection.setPrefSize(600, 500);

        MakeOfficerUpdateSections(rightSection);

        addAllSections(stageSections, leftSection, rightSection);

        addSectionsToScene(stageSections, stage);

        stage.setTitle("Admin Screen");
        stage.setResizable(false);

        stage.show();
        officerList = stage;
    }

    protected void addSectionsToScene(GridPane stageSections, Stage stage) {
        Scene scene = new Scene(stageSections, 800, 700);
        stage.setScene(scene);
    }

    protected void addAllSections(GridPane stageSections, VBox leftSection, VBox rightSection) {
        stageSections.add(leftSection, 0, 0);
        stageSections.add(rightSection, 1, 0);
        stageSections.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#a5cee5"), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    protected void MakeOfficerUpdateSections(VBox rightSection) {
        VBox formRightSection = new VBox(15);
        VBox fromLeftSection = new VBox(20);
        HBox officerUpdateForm = new HBox(15);
        Label stageTitle = MakeLable("Office List");

        ScrollPane scrollPane = addScrollPaneToTable();
        
        TableColumn<OfficerTableColumn, Integer> ID = new TableColumn<>("ID");
        TableColumn<OfficerTableColumn, String> name = new TableColumn<>("Name");
        TableColumn<OfficerTableColumn, String> sex = new TableColumn<>("Sex");
        TableColumn<OfficerTableColumn, String> email = new TableColumn<>("Email");
        TableColumn<OfficerTableColumn, String> phone = new TableColumn<>("Phone");
        TableColumn<OfficerTableColumn, String> areaName = new TableColumn<>("Area");
        TableColumn<OfficerTableColumn, String> username = new TableColumn<>("Username");
        TableColumn<OfficerTableColumn, String> password = new TableColumn<>("Password");

        setColumnsValueSources(ID, name, sex, email, phone, areaName, username, password);
        
        setColumnsWidth(ID, name, sex, email, phone, areaName, username, password);
        
        addColumnsToTable(ID, name, sex, email, phone, areaName, username, password);

        setTableProperties();
        
        setTableContent();
        
        setTableAction();

        /////Name field//////
        HBox NameBox = new HBox(30);
        Label nameLable = makeLable("Name : ");
        MakeNameField();
        NameBox.getChildren().addAll(nameLable, nameField);
        
        ///////Area////////////
        HBox areaBox = new HBox(50);
        Label areaLable = makeLable("Area: ");
        this.Area = new ComboBox();
        Area.setPrefWidth(150);
        areaBox.getChildren().addAll(areaLable, Area);
        
        //////email //////////
        HBox EmailBox = new HBox(40);
        Label emailLable = makeLable("Email : ");
        MakeEmailField();
        EmailBox.getChildren().addAll(emailLable, this.email);
        
        ////////////username/////////////
        HBox UsernameBox = new HBox(1);
        Label usernameLable = makeLable("User Name : ");
        MakeUsernameField();
        UsernameBox.getChildren().addAll(usernameLable, this.username);
        
        /////////// passwordBox /////////////
        HBox passwordBox = new HBox(10);
        Label passwordLable = makeLable("Password : ");
        MakePasswordField();
        passwordBox.getChildren().addAll(passwordLable, Password);
        
        ///////////sex /////////////
        HBox SexBox = new HBox(50);
        Label sexLable = new Label("SEX :");
        ToggleGroup tg = new ToggleGroup();
        this.Male = new RadioButton("Male: ");
        this.Female = new RadioButton("Female");
        tg.getToggles().addAll(Male, Female);
        SexBox.getChildren().addAll(sexLable, Male, Female);
        
        ///////////phone /////////////
        HBox phoneBox = new HBox(30);
        Label phoneLable = makeLable("Phone : ");
        makePasswordField();
        phoneBox.getChildren().addAll(phoneLable, Phone);
        
        //////////////////button///////
        HBox buttons = new HBox(100);
        buttons.setPadding(new Insets(10, 0, 0, 80));

        Button delete = makeDeleteButton();
        Button update = makeUpdateButton();

        buttons.getChildren().addAll(delete, update);

        setDeleteButtonAction(delete);

        setUpdateButtonAction(update);
        
        
        formRightSection.getChildren().addAll(NameBox, areaBox, EmailBox, UsernameBox);
        fromLeftSection.getChildren().addAll(SexBox, phoneBox, passwordBox);
        officerUpdateForm.getChildren().addAll(formRightSection, fromLeftSection);

        rightSection.getChildren().addAll(stageTitle, scrollPane, officerUpdateForm, buttons);
    }

    protected void setColumnsValueSources(TableColumn<OfficerTableColumn, Integer> ID, TableColumn<OfficerTableColumn, String> name, TableColumn<OfficerTableColumn, String> sex, TableColumn<OfficerTableColumn, String> email1, TableColumn<OfficerTableColumn, String> phone, TableColumn<OfficerTableColumn, String> areaName, TableColumn<OfficerTableColumn, String> username1, TableColumn<OfficerTableColumn, String> password) {
        ///////////////
        ID.setCellValueFactory(new PropertyValueFactory("officerID"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        sex.setCellValueFactory(new PropertyValueFactory("Sex"));
        email1.setCellValueFactory(new PropertyValueFactory("Email"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        areaName.setCellValueFactory(new PropertyValueFactory("areaName"));
        username1.setCellValueFactory(new PropertyValueFactory("username"));
        password.setCellValueFactory(new PropertyValueFactory("password"));
    }

    protected void setTableAction() {
        table.setOnMouseClicked(e -> {
            tableClickeEvent();
        });
    }

    protected void setTableContent() {
        table.setItems(adminController.getOfficers());
    }

    protected void setTableProperties() {
        table.setEditable(true);
        table.setMinHeight(1000);
        table.setMinWidth(760);
        table.setMinHeight(1000);
    }

    protected void addColumnsToTable(TableColumn<OfficerTableColumn, Integer> ID, TableColumn<OfficerTableColumn, String> name, TableColumn<OfficerTableColumn, String> sex, TableColumn<OfficerTableColumn, String> email1, TableColumn<OfficerTableColumn, String> phone, TableColumn<OfficerTableColumn, String> areaName, TableColumn<OfficerTableColumn, String> username1, TableColumn<OfficerTableColumn, String> password) {
        table.getColumns().addAll(ID, name, sex, email1, phone, areaName, username1, password);
    }

    protected void setUpdateButtonAction(Button update) {
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                String Sex = "Male";
                if (Female.isSelected()) {
                    Sex = "Female";
                }
                adminController.updateOfficer(Phone.getText(), email.getText(), selectedColumn.getOfficerID(), nameField.getText(), Area.getSelectionModel().getSelectedItem().toString(), Sex, username.getText(), Password.getText(), adminID);
                setTableContent();
            }
        });
    }

    protected void setDeleteButtonAction(Button delete) {
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                adminController.deleteOfficer(selectedColumn.getOfficerID());

                resetUpdateOfficerForm();

                setTableContent();

            }

            protected void resetUpdateOfficerForm() {
                nameField.setText("");
                username.setText("");
                email.setText("");
                Password.setText("");
                Phone.setText("");
                Male.setSelected(false);
                Female.setSelected(false);
                Area.getItems().clear();
                Area.setValue("");
            }
        });
    }

    protected Button makeUpdateButton() {
        Button update = new Button("Update Officer");
        update.setStyle("-fx-background-radius: 300px ;-fx-background-color:Orange;");
        update.setMinWidth(120);
        update.setFont(Font.font("tahoma", FontWeight.BOLD, 15.5));
        update.setTextFill(javafx.scene.paint.Color.BLACK);
        return update;
    }

    protected Button makeDeleteButton() {
        Button delete = new Button("Delete Officer");
        delete.setStyle("-fx-background-radius: 300px ;-fx-background-color:Red; ");
        delete.setMinWidth(120);
        delete.setFont(Font.font("tahoma", FontWeight.BOLD, 15.5));
        delete.setTextFill(javafx.scene.paint.Color.BLACK);
        return delete;
    }

    protected void makePasswordField() {
        this.Phone = new TextField();
        Phone.setStyle("-fx-background-radius: 30px ;");
        Phone.setPromptText("Phone");
        Phone.setMaxWidth(300);
    }

    protected void MakePasswordField() {
        this.Password = new PasswordField();
        Password.setMaxWidth(300);
        Password.setStyle("-fx-background-radius: 30px ;");
        Password.setPromptText("Password");
    }

    protected void MakeUsernameField() {
        this.username = new TextField();
        this.username.setStyle("-fx-background-radius: 30px ;");
        this.username.setPromptText("UserName");
        this.username.setMaxWidth(300);
    }

    protected void MakeEmailField() {
        this.email = new TextField();
        this.email.setStyle("-fx-background-radius: 30px ;");
        this.email.setPromptText("Email");
        this.email.setMaxWidth(300);
    }

    protected void MakeNameField() {
        this.nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setStyle("-fx-background-radius: 30px ;");
        nameField.setMaxWidth(300);
    }

    protected Label makeLable(String name) {
        Label nameLable = new Label(name);
        nameLable.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 16));
        return nameLable;
    }

    protected void setColumnsWidth(TableColumn<OfficerTableColumn, Integer> ID, TableColumn<OfficerTableColumn, String> name, TableColumn<OfficerTableColumn, String> sex, TableColumn<OfficerTableColumn, String> email1, TableColumn<OfficerTableColumn, String> phone, TableColumn<OfficerTableColumn, String> areaName, TableColumn<OfficerTableColumn, String> username1, TableColumn<OfficerTableColumn, String> password) {
        ID.setPrefWidth(50);
        name.setPrefWidth(110);
        sex.setPrefWidth(60);
        email1.setPrefWidth(120);
        phone.setPrefWidth(120);
        areaName.setPrefWidth(100);
        username1.setPrefWidth(100);
        password.setPrefWidth(100);
    }

    protected ScrollPane addScrollPaneToTable() {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(table);
        scrollPane.pannableProperty().set(true);
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.fitToHeightProperty().set(true);
        scrollPane.setPrefHeight(300);
        scrollPane.setMaxWidth(900);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        return scrollPane;
    }

    protected Label MakeLable(String name) {
        Label stageTitle = new Label(name);
        stageTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 30));
        stageTitle.setPadding(new Insets(5, 0, 0, 250));
        return stageTitle;
    }

    protected void MakeLeftTapSwitch(VBox leftSection) {
        Button Add_Officer = new Button("Add Officer");
        Button Officers_list = new Button("Officers list");
        Button Make_report = new Button("Make a report");
        Button Logout = new Button("Logout");

        Add_Officer.setMinSize(170, 175);
        Add_Officer.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Add_Officer.setFocusTraversable(false);
        Add_Officer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                AddOfficerView c2 = new AddOfficerView(adminID);
                officerList.close();
                c2.start(new Stage());
            }
        });

        Officers_list.setMinSize(170, 175);
        Officers_list.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Officers_list.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                OfficerListView c2 = new OfficerListView(adminID);
                officerList.close();
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
                officerList.close();
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
                officerList.close();
                c2.start(new Stage());
            }
        });

        leftSection.getChildren().addAll(Add_Officer, Officers_list, Make_report, Logout);
    }

    private void tableClickeEvent() {
        selectedColumn = table.getSelectionModel().getSelectedItems().get(0);
        nameField.setText(selectedColumn.getName());
        username.setText(selectedColumn.getUsername());
        email.setText(selectedColumn.getEmail());
        Password.setText(selectedColumn.getPassword());
        Phone.setText(selectedColumn.getPhone());

        if (selectedColumn.getSex().equals("Male")) {
            Male.setSelected(true);
        } else {
            Female.setSelected(true);
        }
        Area.getItems().clear();

        Area.setValue(adminController.getAreaName(selectedColumn.getAreaID()));

        ArrayList<String> areaNames = adminController.getAreasForComboBox();
        for (int i = 0; i < areaNames.size(); i++) {
            Area.getItems().add(areaNames.get(i));
        }
        Area.setValue(areaNames.get(0));
    }

}
