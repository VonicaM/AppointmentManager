/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software2assignment.views;

import software2assignment.models.Appointment;
import software2assignment.models.Customer;
import software2assignment.models.User;
import software2assignment.utilities.ConnectDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;
import java.util.ResourceBundle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class AppointmentsMainController implements Initializable {

    @FXML
    private Button AddNewAppointmentButton;
    @FXML
    private Button UpdateAppointmentButton;
    @FXML
    private Button DeleteAppointmentButton;
    @FXML
    private TableView<Appointment> AppointmentTableView;
    @FXML
    private TableColumn<Appointment, String> AppointmentStartColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentEndColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentTitleColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentTypeColumn;
    @FXML
    private TableColumn<Appointment, String> AppointmentCustomerColumn;
    @FXML
    private RadioButton AppointmentWeekRadioButton;
    @FXML
    private RadioButton AppointmentMonthRadioButton;
    @FXML
    private Button AppointmentsBackButton;

    private ToggleGroup RadioButtonToggleGroup;
    private boolean isWeekly;
    private static Appointment updateAppointment;
    private static int updateAppointmentIndex;
    public static int boxtype; //used to identify Add/Update label on AppointmentAddController
    private Customer customer = new Customer();
    private Appointment appointment = new Appointment();

    Parent root;
    Stage stage;

    ObservableList<Appointment> appointmentsOL = FXCollections.observableArrayList();
    private static Appointment selectedAppointment = new Appointment();

    //private User currentUser;    
    private final DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    //private final ZoneId localZoneID = ZoneId.of("UTC-8");
    private final ZoneId localZoneID = ZoneId.systemDefault();
    private final ZoneId utcZoneID = ZoneId.of("UTC");
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Populate CustomerTable with values        
        PropertyValueFactory<Appointment, String> apptStartFactory = new PropertyValueFactory<>("Start");
        PropertyValueFactory<Appointment, String> apptEndFactory = new PropertyValueFactory<>("End");
        PropertyValueFactory<Appointment, String> apptTitleFactory = new PropertyValueFactory<>("Title");
        PropertyValueFactory<Appointment, String> apptTypeFactory = new PropertyValueFactory<>("Type");
        PropertyValueFactory<Appointment, String> apptCustomerFactory = new PropertyValueFactory<>("CustomerName");

        AppointmentStartColumn.setCellValueFactory(apptStartFactory);
        AppointmentEndColumn.setCellValueFactory(apptEndFactory);
        AppointmentTitleColumn.setCellValueFactory(apptTitleFactory);
        AppointmentTypeColumn.setCellValueFactory(apptTypeFactory);
        AppointmentCustomerColumn.setCellValueFactory(apptCustomerFactory);

        //sets togglegroup
        RadioButtonToggleGroup = new ToggleGroup();
        AppointmentWeekRadioButton.setToggleGroup(RadioButtonToggleGroup);
        AppointmentMonthRadioButton.setToggleGroup(RadioButtonToggleGroup);
        AppointmentWeekRadioButton.setSelected(true);
        AppointmentMonthRadioButton.setSelected(false);

        isWeekly = true;

        /*try {
            setAppointmentsTable();
        } catch (SQLException ex) {
            System.out.println("SQL error when 'setAppointmentTable' was called.");
        }*/
    }    
    
    @FXML
    private void AddNewAppointmentButtonHandler(ActionEvent event) throws IOException {
        boxtype = 1;
        root = FXMLLoader.load(getClass().getResource("AppointmentAdd.fxml"));
        stage = (Stage) AddNewAppointmentButton.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void AppointmentWeekRadioButtonHandler(ActionEvent event) throws SQLException, Exception {
        isWeekly = true;
        //setAppointmentsTable();
    }

    @FXML
    private void AppointmentMonthRadioButtonHandler(ActionEvent event) throws SQLException, Exception {
        isWeekly = false;
        //setAppointmentsTable();
    }
    
    @FXML
    private void AppointmentsBackButtonHandler(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage = (Stage) UpdateAppointmentButton.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
