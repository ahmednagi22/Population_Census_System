package View.User;

import View.Utilities.MemberTableColumn;
import Controller.UserViewController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import View.login.Login;

/**
 *
 * @author dell
 */
public class FamilyMembersView extends Application {

    Stage View_Family_Members;
    TableView<MemberTableColumn> table = new TableView();
    int userID;
    UserViewController userController;

    public FamilyMembersView() {
    }

    public FamilyMembersView(int userID) {
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
        Label rightSectionLable = makeRightSectionLable();

        setTableDefauts();

        TableColumn<MemberTableColumn, String> ID = new TableColumn<>("Member ID");
        TableColumn<MemberTableColumn, String> name = new TableColumn<>("Name");
        TableColumn<MemberTableColumn, String> address = new TableColumn<>("Address");
        TableColumn<MemberTableColumn, String> education = new TableColumn("Education");
        TableColumn<MemberTableColumn, String> sex = new TableColumn("Sex");
        TableColumn<MemberTableColumn, String> occupation = new TableColumn("Occupation");
        TableColumn<MemberTableColumn, String> email = new TableColumn("Email");
        TableColumn<MemberTableColumn, String> phone = new TableColumn("phone");
        TableColumn<MemberTableColumn, String> DOB = new TableColumn("Date Of Birth");
        TableColumn<MemberTableColumn, String> area = new TableColumn("Area");

        setColumnsValuesSources(ID, name, address, education, sex, occupation, email, phone, DOB, area);

        setColumnsProperties(ID, name, address, education, sex, occupation, email, phone, DOB, area);

        table.getColumns().addAll(ID, name, address, education, sex, occupation, email, phone, DOB, area);

        table.setItems(userController.getMembers());

        ScrollPane scrollPane = new ScrollPane();
        addScrollPanetoTable(scrollPane);

        rightSection.getChildren().addAll(rightSectionLable, scrollPane);

        addToAllSections(allSections, leftSection, rightSection);
        allSections.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#a5cee5"), CornerRadii.EMPTY, Insets.EMPTY)));

        addStageToScene(allSections, stage);
        
        stage.setTitle("User Screen");
        stage.setResizable(false);

        stage.show();
        View_Family_Members = stage;
    }

    protected void addStageToScene(GridPane allSections, Stage stage) {
        Scene scene = new Scene(allSections, 800, 700);
        stage.setScene(scene);
    }

    protected void addToAllSections(GridPane allSections, VBox leftSection, VBox rightSection) {
        allSections.add(leftSection, 0, 0);
        allSections.add(rightSection, 1, 0);
    }

    protected void addScrollPanetoTable(ScrollPane scrollPane) {
        scrollPane.setContent(table);
        scrollPane.pannableProperty().set(true);
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.fitToHeightProperty().set(true);
        scrollPane.setPrefHeight(400);
        scrollPane.setMinWidth(600);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    protected void setColumnsProperties(TableColumn<MemberTableColumn, String> ID, TableColumn<MemberTableColumn, String> name, TableColumn<MemberTableColumn, String> address, TableColumn<MemberTableColumn, String> education, TableColumn<MemberTableColumn, String> sex, TableColumn<MemberTableColumn, String> occupation, TableColumn<MemberTableColumn, String> email, TableColumn<MemberTableColumn, String> phone, TableColumn<MemberTableColumn, String> DOB, TableColumn<MemberTableColumn, String> area) {
        ID.setPrefWidth(100);
        name.setPrefWidth(120);
        address.setPrefWidth(120);
        education.setPrefWidth(110);
        sex.setPrefWidth(50);
        occupation.setPrefWidth(100);
        email.setPrefWidth(100);
        phone.setPrefWidth(100);
        DOB.setPrefWidth(100);
        area.setPrefWidth(100);
    }

    protected void setColumnsValuesSources(TableColumn<MemberTableColumn, String> ID, TableColumn<MemberTableColumn, String> name, TableColumn<MemberTableColumn, String> address, TableColumn<MemberTableColumn, String> education, TableColumn<MemberTableColumn, String> sex, TableColumn<MemberTableColumn, String> occupation, TableColumn<MemberTableColumn, String> email, TableColumn<MemberTableColumn, String> phone, TableColumn<MemberTableColumn, String> DOB, TableColumn<MemberTableColumn, String> area) {
        ID.setCellValueFactory(new PropertyValueFactory("memberID"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        address.setCellValueFactory(new PropertyValueFactory("address"));
        education.setCellValueFactory(new PropertyValueFactory("education"));
        sex.setCellValueFactory(new PropertyValueFactory("Sex"));
        occupation.setCellValueFactory(new PropertyValueFactory("occupation"));
        email.setCellValueFactory(new PropertyValueFactory("Email"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        DOB.setCellValueFactory(new PropertyValueFactory("DOB"));
        area.setCellValueFactory(new PropertyValueFactory("areaName"));
    }

    protected void setTableDefauts() {
        table.setEditable(true);
        table.setMinHeight(1000);
        table.setMinWidth(900);
    }

    protected Label makeRightSectionLable() {
        Label rightSectionLable = new Label("Members");
        rightSectionLable.setFont(Font.font("Garamond", FontWeight.BOLD, 30));
        rightSectionLable.setPadding(new Insets(50, 0, 50, 200));
        return rightSectionLable;
    }

    protected VBox makeRightSection() {
        VBox section2 = new VBox();
        section2.setPrefSize(530, 500);
        return section2;
    }

    protected void makeLeftSwitchTap(VBox leftSection) {
        Button View_my_family = new Button("View My Family\n" + "Members");
        Button Make_request = new Button("Make a request\n" + "for Adding New\n" + "member");
        Button view_request = new Button("View Your\n" + "Requests");
        Button View_Correction = new Button("View Correction\n" + "Requests");
        Button Logout = new Button("Logout");

        View_my_family.setMinSize(170, 140);
        View_my_family.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        View_my_family.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                FamilyMembersView c2 = new FamilyMembersView(userID);
                View_Family_Members.close();
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
                View_Family_Members.close();
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
                View_Family_Members.close();
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
                View_Family_Members.close();
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
                View_Family_Members.close();
                c2.start(new Stage());
            }
        });

        leftSection.getChildren().addAll(View_my_family, Make_request, view_request, View_Correction, Logout);
//second section
    }

    protected VBox makeLeftSection() {
        // side btn section
        VBox leftSection = new VBox();
        leftSection.setPrefSize(170, 500);
        return leftSection;
    }

}
