package View.User;

import View.Utilities.UserRequestTableColumn;
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
 * @author userID
 */
public class YourRequestView extends Application {

    Stage viewYourRequest;
    TableView<UserRequestTableColumn> table = new TableView();
    int userID;
    UserViewController userController;

    public YourRequestView() {
    }

    public YourRequestView(int userID) {
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

        setTableProperties();

        TableColumn<UserRequestTableColumn, String> requestID = new TableColumn<>("Request ID");
        TableColumn<UserRequestTableColumn, String> requestState = new TableColumn<>("Request State");
        TableColumn<UserRequestTableColumn, String> name = new TableColumn<>("Name");
        TableColumn<UserRequestTableColumn, String> address = new TableColumn<>("Address");
        TableColumn<UserRequestTableColumn, String> education = new TableColumn("Education");
        TableColumn<UserRequestTableColumn, String> sex = new TableColumn("Sex");
        TableColumn<UserRequestTableColumn, String> occupation = new TableColumn("Occupation");
        TableColumn<UserRequestTableColumn, String> email = new TableColumn("Email");
        TableColumn<UserRequestTableColumn, String> phone = new TableColumn("phone");
        TableColumn<UserRequestTableColumn, String> DOB = new TableColumn("Date Of Birth");
        TableColumn<UserRequestTableColumn, String> area = new TableColumn("Area");

        setColumnsValuesSources(requestID, requestState, name, address, education, sex, occupation, email, phone, DOB, area);

        setColumnsProperties(requestID, requestState, name, address, education, sex, occupation, email, phone, DOB, area);

        table.getColumns().addAll(requestID, requestState, name, address, education, sex, occupation, email, phone, DOB, area);

        table.setItems(userController.getuserRequests());

        ScrollPane scrollPane = new ScrollPane();
        addScrollPaneToTable(scrollPane);
        
        rightSection.getChildren().addAll(rightSectionTitle, scrollPane);

        addToAllSections(allSections, leftSection, rightSection);
        
        allSections.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#a5cee5"), CornerRadii.EMPTY, Insets.EMPTY)));

        addStageToScene(allSections, stage);
        
        stage.setTitle("User Screen");
        stage.setResizable(false);

        stage.show();
        viewYourRequest = stage;
    }

    protected void addStageToScene(GridPane allSections, Stage stage) {
        Scene scene = new Scene(allSections, 800, 700);
        stage.setScene(scene);
    }

    protected void addToAllSections(GridPane allSections, VBox leftSection, VBox rightSection) {
        allSections.add(leftSection, 0, 0);
        allSections.add(rightSection, 1, 0);
    }

    protected void addScrollPaneToTable(ScrollPane scrollPane) {
        scrollPane.setContent(table);
        scrollPane.pannableProperty().set(true);
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.fitToHeightProperty().set(true);
        scrollPane.setPrefHeight(400);
        scrollPane.setMinWidth(600);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    protected void setColumnsProperties(TableColumn<UserRequestTableColumn, String> requestID, TableColumn<UserRequestTableColumn, String> requestState, TableColumn<UserRequestTableColumn, String> name, TableColumn<UserRequestTableColumn, String> address, TableColumn<UserRequestTableColumn, String> education, TableColumn<UserRequestTableColumn, String> sex, TableColumn<UserRequestTableColumn, String> occupation, TableColumn<UserRequestTableColumn, String> email, TableColumn<UserRequestTableColumn, String> phone, TableColumn<UserRequestTableColumn, String> DOB, TableColumn<UserRequestTableColumn, String> area) {
        requestID.setPrefWidth(80);
        requestState.setPrefWidth(100);
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

    protected void setColumnsValuesSources(TableColumn<UserRequestTableColumn, String> requestID, TableColumn<UserRequestTableColumn, String> requestState, TableColumn<UserRequestTableColumn, String> name, TableColumn<UserRequestTableColumn, String> address, TableColumn<UserRequestTableColumn, String> education, TableColumn<UserRequestTableColumn, String> sex, TableColumn<UserRequestTableColumn, String> occupation, TableColumn<UserRequestTableColumn, String> email, TableColumn<UserRequestTableColumn, String> phone, TableColumn<UserRequestTableColumn, String> DOB, TableColumn<UserRequestTableColumn, String> area) {
        requestID.setCellValueFactory(new PropertyValueFactory("requestID"));
        requestState.setCellValueFactory(new PropertyValueFactory("requestState"));
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

    protected void setTableProperties() {
        table.setEditable(true);
        table.setMinHeight(1000);
        table.setMinWidth(1150);
    }

    protected Label makeRightSectionTitle() {
        Label rightSectionTitle = new Label("Your Requests");
        rightSectionTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 30));
        rightSectionTitle.setPadding(new Insets(20, 0, 50, 200));
        return rightSectionTitle;
    }

    protected VBox makeRightSection() {
        VBox rightSection = new VBox();
        rightSection.setPrefSize(530, 500);
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
                viewYourRequest.close();
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
                viewYourRequest.close();
                c2.start(new Stage());
            }
        });

        view_request.setMinSize(170, 140);
        view_request.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        view_request.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                YourRequestView c2 = new YourRequestView(userID);
                viewYourRequest.close();
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
                viewYourRequest.close();
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
                viewYourRequest.close();
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
