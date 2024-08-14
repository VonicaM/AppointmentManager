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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class AppointmentAddController implements Initializable {

    @FXML
    private TextField AppointmentTitleTextField;
    @FXML
    private TextField AppointmentDescriptionTextField;
    @FXML
    private DatePicker AppointmentDatePicker;
    @FXML
    private TextField AppointmentUrlTextField;
    @FXML
    private ComboBox<String> AppointmentTypeComboBox;
    @FXML
    private ComboBox<String> AppointmentContactComboBox;
    @FXML
    private ComboBox<String> AppointmentLocationComboBox;
    @FXML
    private ComboBox<String> AppointmentStartComboBox;
    @FXML
    private ComboBox<String> AppointmentEndComboBox;
    @FXML
    private Button AppointmentSaveButton;
    @FXML
    private Button AppointmentCancelButton;
    @FXML
    private TableView<Customer> AppointmentCustomerTable;
    @FXML
    private TableColumn<Customer, Integer> AppointmentCustomerTableCustomerIDColumn;
    @FXML
    private TableColumn<Customer, String> AppointmentCustomerTableCustomerNameColumn;
    @FXML
    private TextField AppointmentCustomerTextField;

    Parent root;
    Stage stage;

    Customer selectedCustomer = new Customer();
    
    private Appointment selectedAppointment;
    private final ZoneId localZoneID = ZoneId.systemDefault(); //local zoneId

    private ObservableList<Customer> customerOL = FXCollections.observableArrayList();
    private final ObservableList<String> startTimes = FXCollections.observableArrayList();
    private final ObservableList<String> endTimes = FXCollections.observableArrayList();
    private final DateTimeFormatter timeDTF = DateTimeFormatter.ofPattern("HH:mm:ss");//ISO standard time formaat
    private final DateTimeFormatter dateDTF = DateTimeFormatter.ofPattern("yyyy-mm-dd");//ISO standard date format
    private final DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final ZoneId utcZoneID = ZoneId.of("UTC");
    ObservableList<Appointment> apptTimeList;
    
    @FXML
    private Button AppointmentBackButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void AppointmentBackButtonHandler(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("AppointmentsMain.fxml"));
        stage = (Stage) AppointmentBackButton.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
