package software2assignment.views;

import software2assignment.models.Appointment;
import software2assignment.models.Customer;
import software2assignment.models.User;
import software2assignment.utilities.ConnectDB;
import software2assignment.utilities.DateTime;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class LoginController implements Initializable {

    @FXML
    private TextField UsernameTextField;
    @FXML
    private PasswordField PasswordTextField;
    @FXML
    private Button LoginButton;
    @FXML
    private Label LoginLabel;
    @FXML
    private Label LocationLabel;
    ObservableList<Appointment> appointmentReminderOL = FXCollections.observableArrayList();
    private DateTimeFormatter datetimeDTF = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private ZoneId localZoneId = ZoneId.systemDefault();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Before rb");
        try {
            ZoneId zoneid = ZoneId.systemDefault();
            rb = ResourceBundle.getBundle("software2assignment.Properties.Login", Locale.getDefault());
            System.out.println("After rb");
            LoginLabel.setText(rb.getString("title"));
            UsernameTextField.setPromptText(rb.getString("username"));
            PasswordTextField.setPromptText(rb.getString("password"));
            LoginButton.setText(rb.getString("signin"));
            LocationLabel.setText(zoneid.getDisplayName(TextStyle.FULL, Locale.getDefault()));
        } catch (MissingResourceException e) {
            System.out.println("Missing resource");
        }
    }    
    
    //filters the reminder list and alerts if appointment is within 15 minutes
    private void appointmentAlert() {
        System.out.println("**** Being appointmentAlert ****");

        Date now = new Date();
        System.out.println("Now: " + now.toString());

        FilteredList<Appointment> filteredData = new FilteredList<>(appointmentReminderOL);

        //lambda expression used to efficiently identify any appointment starting within the next 15 minutes
        filteredData.setPredicate(row -> {
            long start = row.getStart().getTime();
            long nowTime = now.getTime();
            return (nowTime > start - 900000) && (nowTime < start);
        }
        );
        if (filteredData.isEmpty()) {
            System.out.println("No upcoming appointment alerts.");
        }
        else {
            int Id = filteredData.get(0).getAppointmentId();
            Date start = filteredData.get(0).getStart();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Upcoming Appointment Reminder");
            alert.setHeaderText("Reminder - You have an appointment scheduled within the next 15 minutes.");
            alert.setContentText("Your upcoming appointment " + Id
                    + " is currently set to begin at " + start.toString() + ".");
            alert.showAndWait();
        }
        System.out.println("**** End appointmentAlert ****");

    }

    //creates reminder list to be checked with the appointment Alert
    private void createReminderList() {
        System.out.println("**** Begin createReminderList ****");
        try {
            PreparedStatement ps = ConnectDB.makeConnection().prepareStatement(
                    "SELECT appointments.Appointment_ID, appointments.Customer_ID, "
                    + "appointments.Start, appointments.[End], customers.Customer_ID, appointments.Created_By "
                    + "FROM appointments, customers "
                    + "WHERE appointments.Customer_ID = customers.customer_ID AND appointments.Created_By = ? "
                    + "ORDER BY Start");
            ps.setString(1, User.getUsername());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //pulls select data fields for use in appointmentReminderOL observablelist
                Timestamp startTimestamp = rs.getTimestamp("start");
                java.util.Date start = new Date(startTimestamp.getTime());
                Timestamp endTimestamp = rs.getTimestamp("end");
                java.util.Date end = new Date(endTimestamp.getTime());
                int appointmentId = rs.getInt("appointmentId");   
                String title = rs.getString("title");
                String type = rs.getString("type");
                int customerId = rs.getInt("customerId");
                String createdBy = rs.getString("createdBy");

                //prints values of data fields prior to being inserted into observablelist
                System.out.println("AppointmentID: " + appointmentId);
                System.out.println("newLocalStart: " + start.toString());
                System.out.println("newLocalEnd: " + end.toString());
                System.out.println("Title: " + title);
                System.out.println("Type: " + type);
                System.out.println("CustomerId: " + customerId);
                System.out.println("createdBy: " + createdBy);

                //inserts Appointment objects into observablelist
                appointmentReminderOL.add(new Appointment(appointmentId, customerId, start, end, title, type, createdBy));
            }

        } catch (SQLException sqe) {
            System.out.println("There is an error in your SQL preparedstatement");
            sqe.printStackTrace();
        } catch (Exception e) {
            System.out.println("An error other than your SQL has occurred.");
            e.printStackTrace();
        }
        System.out.println("**** End create Reminder List ****");
    }

    @FXML
    private void UserNameTextFieldHandler(ActionEvent event) {
    }

    @FXML
    private void PasswordTextFieldHandler(ActionEvent event) {
    }

    @FXML
    private void LoginButtonHandler(ActionEvent event) throws SQLException, IOException {
        String usernameInput = UsernameTextField.getText();
        String passwordInput = PasswordTextField.getText();
        int userId = getUserId(usernameInput);
        Parent root;
        Stage stage;
        User user = new User();

        if (isValidPassword(userId, passwordInput)) {
            user.setUserId(userId);
            user.setUsername(usernameInput);

            //prints entered fields to terminal for troubleshooting
            //System.out.println("User ID: " + user.getUserID());
            //System.out.println("Username: " + user.getUsername());
            
            //calls method to write current user to the log
            loginLog(user.getUsername());
            createReminderList();
            appointmentAlert();

            //calls mainscreen scene after successful login
            root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            stage = (Stage) LoginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("Incorrect Username and/or Password");
            alert.setContentText("Enter valid Username and Password");
            Optional<ButtonType> result = alert.showAndWait();
        }

    }

    //creates a new log file if one doesnt exist and inserts login information for current user
    public void loginLog(String user) {
        try {
            String fileName = "loginLog";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(DateTime.getTimeStamp() + " " + user + " " + "\n");
            System.out.println("New login recorded in log file.");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private boolean isValidPassword(int userID, String password) throws SQLException {

        //create statement object
        Statement statement = ConnectDB.conn.createStatement();

        //write SQL statement
        String sqlStatement = "SELECT Password FROM users WHERE user_ID ='" + userID + "'";;

        //create resultset object
        ResultSet result = statement.executeQuery(sqlStatement);

        while (result.next()) {
            if (result.getString("password").equals(password)) {
                return true;
            }
        }
        return false;
    }

    //gets User ID for current user
    private int getUserId(String username) throws SQLException {
        int userId = -1;

        //create statement object
        Statement statement = ConnectDB.conn.createStatement();
        
        
        //String sqlStatement0 = "INSERT INTO users (User_Name, Password) VALUES ('test', 'test')";
        //statement.execute(sqlStatement0);
        
        //write SQL statement
        String sqlStatement = "SELECT User_ID FROM users WHERE User_Name ='" + username + "'";

        //create resultset object
        ResultSet result = statement.executeQuery(sqlStatement);

        while (result.next()) {
            userId = result.getInt("User_ID");
        }
        return userId;
    }
}
