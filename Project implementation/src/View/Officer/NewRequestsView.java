package View.Officer;

import Controller.OfficerViewController;
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
import javafx.scene.control.TextArea;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import View.login.Login;
import View.Utilities.UserRequestTableColumn;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author user
 */
public class NewRequestsView extends Application {

    Stage viewNewRequests;
    int officerID;
    TableView table = new TableView();
    Text t = new Text();
    OfficerViewController officerController;
    UserRequestTableColumn selectedColumn;
    boolean isSelectedColumnHasCorrectionRequest;
    TextArea requestReason = new TextArea();
    TextField requestTitle = new TextField();
    Label requestTitleLable = new Label("Title:       ");
    Label ruquestReasonLable = new Label("Reason:       ");
    VBox SendButtonBox = new VBox(10);
    Button SendButton = new Button("Send");

    public NewRequestsView() {
    }

    public NewRequestsView(int officerID) {
        this.officerID = officerID;
        officerController = new OfficerViewController(officerID);
    }

    @Override
    public void start(Stage stage) {
        GridPane allSections = new GridPane();
        allSections.setHgap(10);

        VBox leftSection = makeLeftSection();

        MakeLeftSwitchTap(leftSection);

        VBox RightSection = makeRightSection();

        Label rightSectionTitle = makeRightSectionTitle();

        setTableDefualts();

        table.setOnMouseClicked(e -> {
            tableClickeEvent();
        });

        TableColumn<UserRequestTableColumn, String> requestID = new TableColumn<>("Request ID");
        TableColumn<UserRequestTableColumn, String> requestState = new TableColumn<>("Request State");
        TableColumn<UserRequestTableColumn, String> ID = new TableColumn<>("Member ID");
        TableColumn<UserRequestTableColumn, String> name = new TableColumn<>("Name");
        TableColumn<UserRequestTableColumn, String> address = new TableColumn<>("Address");
        TableColumn<UserRequestTableColumn, String> education = new TableColumn("Education");
        TableColumn<UserRequestTableColumn, String> sex = new TableColumn("Sex");
        TableColumn<UserRequestTableColumn, String> occupation = new TableColumn("Occupation");
        TableColumn<UserRequestTableColumn, String> email = new TableColumn("Email");
        TableColumn<UserRequestTableColumn, String> phone = new TableColumn("phone");
        TableColumn<UserRequestTableColumn, String> DOB = new TableColumn("Date Of Birth");
        TableColumn<UserRequestTableColumn, String> area = new TableColumn("Area");

        setColumnsValuesSources(requestID, requestState, name, ID, address, education, sex, occupation, email, phone, DOB, area);

        setColumnsProperties(address, requestID, requestState, ID, name, education, sex, occupation, email, phone, DOB, area);

        table.getColumns().addAll(requestID, requestState, ID, name, address, education, sex, occupation, email, phone, DOB, area);
        table.setItems(officerController.getPendingUserRequests());

        ScrollPane scrollPane = new ScrollPane();
        addScrollPaneToTable(scrollPane);

        MakeCorrectionRequestForm(RightSection, rightSectionTitle, scrollPane);

        addToAllSections(allSections, leftSection, RightSection);

        allSections.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#a5cee5"), CornerRadii.EMPTY, Insets.EMPTY)));

        addStageToScene(allSections, stage);

        stage.setTitle("Officer Screen");
        stage.setResizable(false);

        stage.show();
        viewNewRequests = stage;
    }

    protected VBox makeRightSection() {
        //////////////////////////////////////
        VBox RightSection = new VBox(6);
        RightSection.setPrefSize(530, 400);
        RightSection.setMinHeight(300);
        return RightSection;
    }

    protected VBox makeLeftSection() {
        // side btn section
        VBox leftSection = new VBox();
        leftSection.setPrefSize(170, 500);
        return leftSection;
    }

    protected void MakeCorrectionRequestForm(VBox RightSection, Label rightSectionTitle, ScrollPane scrollPane) {
        HBox correctionRequestLablesBox = new HBox();
        correctionRequestLablesBox.setPadding(new Insets(30, 0, 0, 0));
        correctionRequestLablesBox.setAlignment(Pos.CENTER);

        setFormLablesDefaults();

        setRequestTitleDefualts();
        setRequestReasonDefaults();

        correctionRequestLablesBox.getChildren().addAll(requestTitleLable, requestTitle);

        //////////////////
        HBox correctionRequest = new HBox();
        correctionRequest.setPadding(new Insets(30, 0, 0, 0));
        correctionRequest.setAlignment(Pos.CENTER);

        correctionRequest.getChildren().addAll(ruquestReasonLable, requestReason);
        setSendButton();
        setSendButtonAction();

        setSendButtonBox();
        //////////////////////////////////////////////////////////////
        HBox buttons = new HBox(100);
        buttons.setPadding(new Insets(10, 0, 10, 130));

        Button accept = makeAcceptButton();
        setAcceptButtonAction(accept);

        Button reject = makeRejectButton();
        setRejectButtonAction(reject);

        buttons.getChildren().addAll(reject, accept);
        RightSection.getChildren().addAll(rightSectionTitle, scrollPane, buttons, requestTitleLable, requestTitle, ruquestReasonLable, requestReason, SendButtonBox);
    }

    protected void setRequestReasonDefaults() {
        requestReason.setPromptText("Message");
        requestReason.setMaxHeight(220);
        requestReason.setWrapText(true);
        requestReason.setVisible(false);
    }

    protected void setRequestTitleDefualts() {
        requestTitle.setPromptText("Title Of Correction Request  ");
        requestTitle.setPrefSize(200, 40);
        requestTitle.setVisible(false);
    }

    protected void setFormLablesDefaults() {
        requestTitleLable.setFont(Font.font("arial", FontWeight.BOLD, 17));
        requestTitleLable.setVisible(false);
        ruquestReasonLable.setFont(Font.font("arial", FontWeight.BOLD, 17));
        ruquestReasonLable.setVisible(false);
    }

    protected void setRejectButtonAction(Button reject) {
        reject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                if (tableNotEmpty()) {
                    requestTitleLable.setVisible(true);
                    requestTitle.setVisible(true);
                    requestReason.setVisible(true);
                    ruquestReasonLable.setVisible(true);
                    SendButtonBox.setVisible(true);
                    t.setVisible(false);
                    SendButton.setVisible(true);
                }
            }
        });
    }

    protected Button makeRejectButton() {
        Button reject = new Button("Reject");
        reject.setStyle("-fx-background-radius: 300px ;-fx-background-color:Red; ");
        reject.setMinWidth(110);
        reject.setFont(Font.font("tahoma", FontWeight.LIGHT, 16));
        reject.setTextFill(javafx.scene.paint.Color.BLACK);
        return reject;
    }

    protected void setAcceptButtonAction(Button accept) {
        accept.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent even) {

                requestTitleLable.setVisible(false);
                requestTitle.setVisible(false);
                requestReason.setVisible(false);
                ruquestReasonLable.setVisible(false);
                SendButtonBox.setVisible(false);
                t.setVisible(false);
                SendButton.setVisible(false);
                officerController.updateStateOfUserRequestToAccepted(selectedColumn.getRequestID());
                tableClickeEvent();
                table.setItems(officerController.getPendingUserRequests());
                if (isSelectedColumnHasCorrectionRequest) {
                    officerController.deleteCorrectionRequest(selectedColumn.getRequestID());

                    requestTitleLable.setVisible(false);
                    requestTitle.setVisible(false);
                    requestReason.setVisible(false);
                    ruquestReasonLable.setVisible(false);
                    SendButtonBox.setVisible(false);
                    t.setVisible(false);
                    SendButton.setVisible(false);
                }
                officerController.acceptUserRequest(selectedColumn.getRequestID());

            }
        });
    }

    protected Button makeAcceptButton() {
        Button accept = new Button("Accept");
        accept.setStyle("-fx-background-radius: 300px ;-fx-background-color:Orange;");
        accept.setMinWidth(110);
        accept.setFont(Font.font("tahoma", FontWeight.LIGHT, 16));
        accept.setTextFill(javafx.scene.paint.Color.BLACK);
        return accept;
    }

    protected void setSendButtonBox() {
        SendButtonBox.setPadding(new Insets(0, 0, 0, 230));
        SendButtonBox.getChildren().addAll(t, SendButton);
        SendButtonBox.setVisible(false);
    }

    protected void setSendButtonAction() {
        SendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                t.setText("Correction Request sent!");
                t.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20));
                if (isSelectedColumnHasCorrectionRequest) {
                    officerController.updateCorrectionRequest(selectedColumn.getRequestID(), requestTitle.getText(), requestReason.getText());
                    tableClickeEvent();
                } else {
                    officerController.makeCorrectionRequest(selectedColumn.getRequestID(), selectedColumn.getUserID(), officerID, requestTitle.getText(), requestReason.getText());
                }
                officerController.updateStateOfUserRequestToRejected(selectedColumn.getRequestID());
                table.setItems(officerController.getPendingUserRequests());
                requestTitleLable.setVisible(false);
                requestTitle.setVisible(false);
                requestReason.setVisible(false);
                ruquestReasonLable.setVisible(false);
                SendButton.setVisible(false);
                t.setVisible(true);
            }
        });
    }

    protected void setSendButton() {
        ///////////////////////////////////////////////////////////////////////////////////////
        SendButton.setFont(Font.font("tahoma", FontWeight.LIGHT, 16));
        SendButton.setTextFill(javafx.scene.paint.Color.BLACK);
        SendButton.setStyle("-fx-background-radius: 300px ;");
        SendButton.setMinWidth(120);
    }

    protected void addStageToScene(GridPane allSections, Stage stage) {
        Scene scene = new Scene(allSections, 800, 775);
        stage.setScene(scene);
    }

    protected void addToAllSections(GridPane allSections, VBox leftSection, VBox RightSection) {
        allSections.add(leftSection, 0, 0);
        allSections.add(RightSection, 1, 0);
    }

    protected void addScrollPaneToTable(ScrollPane scrollPane) {
        scrollPane.setContent(table);
        scrollPane.pannableProperty().set(true);
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.fitToHeightProperty().set(true);
        scrollPane.setPrefHeight(300);
        scrollPane.setMaxWidth(900);
        scrollPane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
    }

    protected void setColumnsProperties(TableColumn<UserRequestTableColumn, String> address, TableColumn<UserRequestTableColumn, String> requestID, TableColumn<UserRequestTableColumn, String> requestState, TableColumn<UserRequestTableColumn, String> ID, TableColumn<UserRequestTableColumn, String> name, TableColumn<UserRequestTableColumn, String> education, TableColumn<UserRequestTableColumn, String> sex, TableColumn<UserRequestTableColumn, String> occupation, TableColumn<UserRequestTableColumn, String> email, TableColumn<UserRequestTableColumn, String> phone, TableColumn<UserRequestTableColumn, String> DOB, TableColumn<UserRequestTableColumn, String> area) {
        address.setPrefWidth(120);
        requestID.setPrefWidth(80);
        requestState.setPrefWidth(100);
        ID.setPrefWidth(80);
        name.setPrefWidth(120);
        education.setPrefWidth(110);
        sex.setPrefWidth(50);
        occupation.setPrefWidth(100);
        email.setPrefWidth(100);
        phone.setPrefWidth(100);
        DOB.setPrefWidth(100);
        area.setPrefWidth(100);
    }

    protected void setColumnsValuesSources(TableColumn<UserRequestTableColumn, String> requestID, TableColumn<UserRequestTableColumn, String> requestState, TableColumn<UserRequestTableColumn, String> name, TableColumn<UserRequestTableColumn, String> ID, TableColumn<UserRequestTableColumn, String> address, TableColumn<UserRequestTableColumn, String> education, TableColumn<UserRequestTableColumn, String> sex, TableColumn<UserRequestTableColumn, String> occupation, TableColumn<UserRequestTableColumn, String> email, TableColumn<UserRequestTableColumn, String> phone, TableColumn<UserRequestTableColumn, String> DOB, TableColumn<UserRequestTableColumn, String> area) {
        requestID.setCellValueFactory(new PropertyValueFactory("requestID"));
        requestState.setCellValueFactory(new PropertyValueFactory("requestState"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        ID.setCellValueFactory(new PropertyValueFactory("memberID"));
        address.setCellValueFactory(new PropertyValueFactory("address"));
        education.setCellValueFactory(new PropertyValueFactory("education"));
        sex.setCellValueFactory(new PropertyValueFactory("Sex"));
        occupation.setCellValueFactory(new PropertyValueFactory("occupation"));
        email.setCellValueFactory(new PropertyValueFactory("Email"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        DOB.setCellValueFactory(new PropertyValueFactory("DOB"));
        area.setCellValueFactory(new PropertyValueFactory("areaName"));
    }

    protected void setTableDefualts() {
        table.setEditable(true);
        table.setMinHeight(1400);
        table.setMaxHeight(300);
        table.setMinWidth(1150);
    }

    protected Label makeRightSectionTitle() {
        Label rightSectionTitle = new Label("New Requests");
        rightSectionTitle.setFont(Font.font("Garamond", FontWeight.BOLD, 30));
        rightSectionTitle.setPadding(new Insets(5, 0, 0, 180));
        return rightSectionTitle;
    }

    protected void MakeLeftSwitchTap(VBox leftSection) {
        Button View_my_family = new Button("Add User");
        Button View_Correction = new Button("   View New \n"
                + "User Request");
        Button Logout = new Button("Logout");

        View_my_family.setMinSize(170, 258);
        View_my_family.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        View_my_family.setFocusTraversable(false);
        View_my_family.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                AddUserView c2 = new AddUserView(officerID);
                viewNewRequests.close();
                c2.start(new Stage());
            }
        });

        View_Correction.setMinSize(170, 258);
        View_Correction.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        View_Correction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                NewRequestsView c2 = new NewRequestsView(officerID);
                viewNewRequests.close();
                c2.start(new Stage());
            }
        });

        Logout.setMinSize(170, 259);
        Logout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.web("#bf1f21"), CornerRadii.EMPTY, Insets.EMPTY)));
        Logout.setBorder(new Border(new BorderStroke((javafx.scene.paint.Color.web("#79b5d9")), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Logout.setFont(Font.font("tahoma", FontWeight.BOLD, 15));
        Logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent even) {
                Login c2 = new Login();
                viewNewRequests.close();
                c2.start(new Stage());
            }
        });

        leftSection.getChildren().addAll(View_my_family, View_Correction, Logout);
    }

    private void tableClickeEvent() {
        selectedColumn = (UserRequestTableColumn) table.getSelectionModel().getSelectedItems().get(0);
        isSelectedColumnHasCorrectionRequest = officerController.checkSelectedColumnHasCorrectionRequest(selectedColumn.getRequestID());
        if (isSelectedColumnHasCorrectionRequest) {
            officerController.setTitleAndReasonOfCorrectionRequest(this.requestTitle, this.requestReason, selectedColumn.getRequestID());
            requestTitleLable.setVisible(true);
            requestTitle.setVisible(true);
            requestReason.setVisible(true);
            ruquestReasonLable.setVisible(true);
            SendButtonBox.setVisible(true);
            t.setVisible(false);
            SendButton.setVisible(true);
        } else {
            officerController.setTitleAndReasonOfCorrectionRequest(this.requestTitle, this.requestReason, selectedColumn.getRequestID());
            requestTitleLable.setVisible(false);
            requestTitle.setVisible(false);
            requestReason.setVisible(false);
            ruquestReasonLable.setVisible(false);
            SendButtonBox.setVisible(false);
            t.setVisible(false);
            SendButton.setVisible(false);
            requestTitle.setText("");
            requestReason.setText("");
        }
    }

    private boolean tableNotEmpty() {
        return table.getItems().size() != 0;
    }

}
