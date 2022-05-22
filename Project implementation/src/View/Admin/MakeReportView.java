package View.Admin;

import View.Utilities.OfficerTableColumn;
import Controller.AdminViewController;
import View.Utilities.UserTableColumn;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import View.login.Login;

/**
 *
 * @author user
 */
public class MakeReportView extends Application {

    Stage makeReport;
    int adminID;
    ComboBox SearchOn = new ComboBox();
    ComboBox SearchOptions = new ComboBox();
    ComboBox SortByOption = new ComboBox();
    ComboBox OrderOption = new ComboBox();
    TableView table = new TableView();
    ScrollPane scrollPane = new ScrollPane();
    AdminViewController adminController;

    public MakeReportView() {
    }

    public MakeReportView(int adminID) {
        this.adminID = adminID;
        adminController = new AdminViewController(adminID);
    }

    @Override
    public void start(Stage stage) {
        GridPane allSections = new GridPane();
        allSections.setHgap(10);

        // leftSection
        VBox leftSection = new VBox();
        leftSection.setPrefSize(170, 500);

        makeLeftSwitchTap(leftSection);

        //RightSection
        VBox RightSection = new VBox(30);
        RightSection.setPrefSize(530, 500);

        Label rightSectionLable = makeSectionLable();

        setTablePreProperties();

        addScrollPaneToTable();

        HBox search = makeSearchOnBox();

        makeSearchOnAction();

        HBox searchOptionBox = MakeSearchOptionBox();
        HBox sortOptionBox = makeSortOnOptionBox();
        HBox OrderOptionBox = makeOrderOptionBox();

        setComboBoxesOfBoxesDefaults();

        HBox makeReportButtonBox = makeReportButtonBox();
        Button makeReportButton = makeMakeReportButton();
        setMakeReportButtonAction(makeReportButton);
        makeReportButtonBox.getChildren().add(makeReportButton);

        RightSection.getChildren().addAll(rightSectionLable, search, searchOptionBox, sortOptionBox, OrderOptionBox, makeReportButtonBox, scrollPane);

        addSections(allSections, leftSection, RightSection);
        allSections.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#a5cee5"), CornerRadii.EMPTY, Insets.EMPTY)));
        addSectionsToScene(allSections, stage);

        stage.setTitle("Admin Screen");
        stage.setResizable(false);

        stage.show();
        makeReport = stage;

    }

    protected HBox makeReportButtonBox() {
        HBox makeReportButtonBox = new HBox(100);
        makeReportButtonBox.setPadding(new Insets(0, 0, 0, 180));
        return makeReportButtonBox;
    }

    protected void setMakeReportButtonAction(Button makeReportButton) {
        makeReportButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                table.getColumns().clear();
                table.getItems().clear();
                table.setDisable(false);
                String selectionValue = SearchOn.getValue().toString();
                if (selectionValue.equals("User")) {

                    TableColumn<UserTableColumn, Integer> ID = new TableColumn<>("userID");
                    TableColumn<UserTableColumn, String> name = new TableColumn<>("Name");
                    TableColumn<UserTableColumn, String> sex = new TableColumn<>("Sex");
                    TableColumn<UserTableColumn, String> email = new TableColumn<>("Email");
                    TableColumn<UserTableColumn, String> phone = new TableColumn<>("Phone");
                    TableColumn<UserTableColumn, String> areaName = new TableColumn<>("Area");
                    TableColumn<UserTableColumn, String> address = new TableColumn<>("Address");

                    setFirstColumnsValueSources(ID, name, sex, email, phone, areaName, address);

                    setFirstColumnsProperties(ID, name, sex, email, phone, areaName, address);

                    table.getColumns().addAll(ID, name, sex, email, phone, areaName, address);
                } else if (selectionValue.equals("Officer")) {

                    TableColumn<OfficerTableColumn, Integer> ID = new TableColumn<>("officerID");
                    TableColumn<OfficerTableColumn, String> name = new TableColumn<>("Name");
                    TableColumn<OfficerTableColumn, String> sex = new TableColumn<>("Sex");
                    TableColumn<OfficerTableColumn, String> email = new TableColumn<>("Email");
                    TableColumn<OfficerTableColumn, String> phone = new TableColumn<>("Phone");
                    TableColumn<OfficerTableColumn, String> areaName = new TableColumn<>("Area");
                    TableColumn<OfficerTableColumn, String> username = new TableColumn<>("Username");
                    TableColumn<OfficerTableColumn, String> password = new TableColumn<>("Password");

                    setSecondColumnsValuesSources(ID, name, sex, email, phone, areaName, username, password);

                    setColumnsProperties(ID, name, sex, email, phone, areaName, username, password);

                    table.getColumns().addAll(ID, name, sex, email, username, password, phone, areaName);

                }
                setTableData();
            }

            protected void setFirstColumnsValueSources(TableColumn<UserTableColumn, Integer> ID, TableColumn<UserTableColumn, String> name, TableColumn<UserTableColumn, String> sex, TableColumn<UserTableColumn, String> email, TableColumn<UserTableColumn, String> phone, TableColumn<UserTableColumn, String> areaName, TableColumn<UserTableColumn, String> address) {
                ID.setCellValueFactory(new PropertyValueFactory("userID"));
                name.setCellValueFactory(new PropertyValueFactory("name"));
                sex.setCellValueFactory(new PropertyValueFactory("Sex"));
                email.setCellValueFactory(new PropertyValueFactory("Email"));
                phone.setCellValueFactory(new PropertyValueFactory("phone"));
                areaName.setCellValueFactory(new PropertyValueFactory("areaName"));
                address.setCellValueFactory(new PropertyValueFactory("address"));
            }

            protected void setFirstColumnsProperties(TableColumn<UserTableColumn, Integer> ID, TableColumn<UserTableColumn, String> name, TableColumn<UserTableColumn, String> sex, TableColumn<UserTableColumn, String> email, TableColumn<UserTableColumn, String> phone, TableColumn<UserTableColumn, String> areaName, TableColumn<UserTableColumn, String> address) {
                ID.setPrefWidth(50);
                name.setPrefWidth(110);
                sex.setPrefWidth(60);
                email.setPrefWidth(120);
                phone.setPrefWidth(120);
                areaName.setPrefWidth(100);
                address.setPrefWidth(100);
            }

            protected void setColumnsProperties(TableColumn<OfficerTableColumn, Integer> ID, TableColumn<OfficerTableColumn, String> name, TableColumn<OfficerTableColumn, String> sex, TableColumn<OfficerTableColumn, String> email, TableColumn<OfficerTableColumn, String> phone, TableColumn<OfficerTableColumn, String> areaName, TableColumn<OfficerTableColumn, String> username, TableColumn<OfficerTableColumn, String> password) {
                ID.setPrefWidth(50);
                name.setPrefWidth(110);
                sex.setPrefWidth(60);
                email.setPrefWidth(120);
                phone.setPrefWidth(120);
                areaName.setPrefWidth(100);
                username.setPrefWidth(100);
                password.setPrefWidth(100);
            }

            protected void setSecondColumnsValuesSources(TableColumn<OfficerTableColumn, Integer> ID, TableColumn<OfficerTableColumn, String> name, TableColumn<OfficerTableColumn, String> sex, TableColumn<OfficerTableColumn, String> email, TableColumn<OfficerTableColumn, String> phone, TableColumn<OfficerTableColumn, String> areaName, TableColumn<OfficerTableColumn, String> username, TableColumn<OfficerTableColumn, String> password) {
                ID.setCellValueFactory(new PropertyValueFactory("officerID"));
                name.setCellValueFactory(new PropertyValueFactory("name"));
                sex.setCellValueFactory(new PropertyValueFactory("Sex"));
                email.setCellValueFactory(new PropertyValueFactory("Email"));
                phone.setCellValueFactory(new PropertyValueFactory("phone"));
                areaName.setCellValueFactory(new PropertyValueFactory("areaName"));
                username.setCellValueFactory(new PropertyValueFactory("username"));
                password.setCellValueFactory(new PropertyValueFactory("password"));
            }

        });
    }

    protected Button makeMakeReportButton() {
        Button makeReportButton = new Button("Make a Report");
        makeReportButton.setStyle("-fx-background-radius: 300px ;-fx-background-color:yellow; ");
        makeReportButton.setMinWidth(120);
        makeReportButton.setFont(Font.font("tahoma", FontWeight.BOLD, 15.5));
        makeReportButton.setTextFill(javafx.scene.paint.Color.BLACK);
        return makeReportButton;
    }

    protected HBox makeOrderOptionBox() {
        /////Select an order to sort on/////////////
        HBox optionOrder = new HBox(15);
        Label option = new Label("Order: ");
        option.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 18));
        OrderOption.setPrefWidth(120);
        optionOrder.setPadding(new Insets(10, 0, 0, 20));
        optionOrder.getChildren().addAll(option, OrderOption);
        return optionOrder;
    }

    protected HBox makeSortOnOptionBox() {
        /////Select an option to sort on/////////////
        HBox sortOptionBox = new HBox(15);
        Label sortByLable = new Label("Sort By: ");
        sortByLable.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 18));
        SortByOption.setPrefWidth(120);
        sortOptionBox.setPadding(new Insets(10, 0, 0, 20));
        sortOptionBox.getChildren().addAll(sortByLable, SortByOption);
        return sortOptionBox;
    }

    protected HBox MakeSearchOptionBox() {
        /////////////Select an option to display////////////////
        HBox Display_search = new HBox(15);
        Label display = new Label("Select an option to display: ");
        display.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 18));
        SearchOptions.setPrefWidth(180);
        Display_search.setPadding(new Insets(0, 0, 20, 20));
        Display_search.getChildren().addAll(display, SearchOptions);
        return Display_search;
    }

    protected void setTableData() {
        table.setItems(adminController.getReport(SearchOn.getValue().toString(), SearchOptions.getValue().toString(), SortByOption.getValue().toString(), OrderOption.getValue().toString()));
    }

    protected void addSections(GridPane allSections, VBox leftSection, VBox RightSection) {
        allSections.add(leftSection, 0, 0);
        allSections.add(RightSection, 1, 0);
    }

    protected void addSectionsToScene(GridPane allSections, Stage stage) {
        Scene scene = new Scene(allSections, 800, 700);
        stage.setScene(scene);
    }

    protected void makeSearchOnAction() {
        SearchOn.setOnAction(event -> {
            String selectionValue = SearchOn.getValue().toString();
            SearchOptions.getItems().clear();
            SortByOption.getItems().clear();
            if (selectionValue.equals("User")) {
                SearchOptions.getItems().addAll("Has descendants", "Doesn`t has descendants");
                SearchOptions.setValue("Has descendants");
                SortByOption.getItems().addAll("userID", "Name", "Sex", "Email", "Phone", "Address");
                SortByOption.setValue("userID");
            } else if (selectionValue.equals("Officer")) {
                SearchOptions.getItems().addAll("Has Users", "Doesn`t has Users");
                SearchOptions.setValue("Has Users");
                SortByOption.getItems().addAll("officerID", "Name", "Sex", "Email", "Phone", "Username", "Password");
                SortByOption.setValue("officerID");
            }
        });
    }

    protected HBox makeSearchOnBox() {
        /////searching on//////
        HBox search = new HBox(15);
        Label Searching = new Label("Searching on: ");
        Searching.setFont(Font.font("Arial", FontWeight.LIGHT, FontPosture.ITALIC, 18));
        SearchOn.setPrefWidth(100);
        search.setPadding(new Insets(10, 0, 0, 70));
        search.getChildren().addAll(Searching, SearchOn);
        return search;
    }

    protected void addScrollPaneToTable() {
        scrollPane.setContent(table);
        scrollPane.pannableProperty().set(true);
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.fitToHeightProperty().set(true);
        scrollPane.setPrefHeight(300);
        scrollPane.setMaxWidth(900);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    protected void setTablePreProperties() {
        table.setEditable(true);
        table.setMinHeight(1000);
        table.setMinWidth(760);
        table.setDisable(true);
    }

    protected Label makeSectionLable() {
        Label H = new Label("Report on Family members");
        H.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 22));
        H.setPadding(new Insets(5, 0, 0, 100));
        return H;
    }

    protected void makeLeftSwitchTap(VBox leftSection) {
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
                makeReport.close();
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
                makeReport.close();
                c2.start(new Stage());
            }
        });

        Make_report.setMinSize(170, 175);
        Make_report.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Make_report.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                MakeReportView c2 = new MakeReportView(adminID);
                makeReport.close();
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
                makeReport.close();
                c2.start(new Stage());
            }
        });

        leftSection.getChildren().addAll(Add_Officer, Officers_list, Make_report, Logout);
    }

    private void setComboBoxesOfBoxesDefaults() {
        SearchOn.getItems().addAll("User", "Officer");
        SearchOn.setValue("User");
        SearchOptions.getItems().addAll("Has descendants", "Doesn`t has descendants");
        SearchOptions.setValue("Has descendants");
        SortByOption.getItems().addAll("userID", "Name", "Sex", "Email", "Phone", "Address");
        SortByOption.setValue("userID");
        OrderOption.getItems().addAll("Ascending", "Descending");
        OrderOption.setValue("Ascending");
    }

}
