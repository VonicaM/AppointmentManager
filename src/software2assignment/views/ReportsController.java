/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software2assignment.views;

import software2assignment.models.Appointment;
import software2assignment.models.Customer;
import software2assignment.models.Report;
import software2assignment.models.User;
import software2assignment.utilities.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class ReportsController implements Initializable {

    @FXML
    private Tab ReportsByMonth;
    @FXML
    private TableView<Report> ReportsByMonthTableView;
    @FXML
    private TableColumn<Report, String> ReportsByMonthMonthColumn;
    @FXML
    private TableColumn<Report, Integer> ReportsByMonthNewAccountColumn;
    @FXML
    private TableColumn<Report, Integer> ReportsByMonthConsultationColumn;
    @FXML
    private TableColumn<Report, Integer> ReportsByMonthFollowUpColumn;
    @FXML
    private TableColumn<Report, Integer> ReportsByMonthCloseAccountColumn;

    @FXML
    private Tab ReportsSchedule;
    @FXML
    private TableView<Appointment> ReportsScheduleTableView;
    @FXML
    private TableColumn<Appointment, String> ReportsScheduleCustomerColumn;
    @FXML
    private TableColumn<Appointment, String> ReportsScheduleTitleColumn;
    @FXML
    private TableColumn<Appointment, String> ReportsScheduleTypeColumn;
    @FXML
    private TableColumn<Appointment, String> ReportsScheduleStartColumn;
    @FXML
    private TableColumn<Appointment, String> ReportsScheduleEndColumn;

    @FXML
    private Tab ReportsContactByMonth;
    @FXML
    private TableView<Report> ReportsContactsByMonthTableView;
    @FXML
    private TableColumn<Report, String> ReportsContactsMonthColumn;
    @FXML
    private TableColumn<Report, Integer> ReportsContactEmailColumn;
    @FXML
    private TableColumn<Report, Integer> ReportsContactPhoneColumn;
    @FXML
    private TableColumn<Report, Integer> ReportsContactInpersonColumn;

    @FXML
    private Button ReportsMainMenuButton;

    Parent root;
    Stage stage;

    //creates observablelists used for the 3 available reports
    private ObservableList<Appointment> scheduleOL = FXCollections.observableArrayList();
    private ObservableList<Report> typesByMonthOL = FXCollections.observableArrayList();
    private ObservableList<Report> contactsByMonthOL = FXCollections.observableArrayList();
    private final DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //private final ZoneId localZoneID = ZoneId.of("UTC-8");
    private final ZoneId localZoneID = ZoneId.systemDefault();
    private final ZoneId utcZoneID = ZoneId.of("UTC");

    //create array for storing how many of each contacts are in each month
    private int monthContacts[][] = new int[][]{
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0},
        {0, 0, 0}
    };
    
    //create array for storing how many of each appointment types are in each month
    private int monthTypes[][] = new int[][]{
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {0, 0, 0, 0}
    };
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void ReportsMainMenuButtonHandler(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage = (Stage) ReportsMainMenuButton.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}