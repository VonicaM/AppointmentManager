/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software2assignment.views;

import software2assignment.models.Country;
import software2assignment.models.Customer;
import software2assignment.models.User;
import software2assignment.utilities.ConnectDB;
import java.io.IOException;
import static java.lang.System.load;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class CustomerScreenController implements Initializable {

    @FXML
    private AnchorPane CustomerAddLabel;
    @FXML
    private TableView<Customer> CustomerTable;
    @FXML
    private TableColumn<Customer, String> CustomerCustomerNameColumn;
    @FXML
    private TableColumn<Customer, String> CustomerAddressColumn;
    @FXML
    private TableColumn<Customer, String> CustomerPostalCodeColumn;
    @FXML
    private TableColumn<Customer, String> CustomerPhoneColumn;
    @FXML
    private TextField CustomerCustomerIDTextField;
    @FXML
    private ComboBox<String> CustomerDivisionComboBox;
    @FXML
    private ComboBox<String> CustomerCountryComboBox;
    @FXML
    private Button CustomerSaveButton;
    @FXML
    private Button CustomerCancelButton;
    @FXML
    private Button CustomerAddButton;    
    @FXML
    private Button CustomerDeleteButton;
    @FXML
    private TextField CustomerCustomerNameTextField;
    @FXML
    private TextField CustomerAddressTextField;
    @FXML
    private TextField CustomerPostalCodeTextField;
    @FXML
    private TextField CustomerPhoneTextField;
    @FXML
    private RadioButton CustomerActiveRadioButton;
    @FXML
    private RadioButton CustomerInactiveRadioButton;
    @FXML
    private Button CustomerBackButton;

    Parent root;
    Stage stage;

    //create ObservableLists
    ObservableList<Customer> customerOL = FXCollections.observableArrayList();
    ObservableList<String> divisionOptions = FXCollections.observableArrayList();
    ObservableList<String> countryOptions = FXCollections.observableArrayList();

    private static Customer selectedCustomer = new Customer();
    private boolean customerUpdate = false; //used to determine whether to UPDATE customer in the database
    private boolean customerAdd = false; //used to determine whether to INSERT customer in the database
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Populate CustomerTable with values
        PropertyValueFactory<Customer, String> custNameFactory = new PropertyValueFactory<>("CustomerName");
        PropertyValueFactory<Customer, String> custAddressFactory = new PropertyValueFactory<>("Address");
        PropertyValueFactory<Customer, String> custPostalCodeFactory = new PropertyValueFactory<>("PostalCode");
        PropertyValueFactory<Customer, String> custPhoneFactory = new PropertyValueFactory<>("CustomerPhone"); //String value "CustomerPhone" calls getCustomerPhone method
        CustomerCustomerNameColumn.setCellValueFactory(custNameFactory);
        CustomerAddressColumn.setCellValueFactory(custAddressFactory);
        CustomerPostalCodeColumn.setCellValueFactory(custPostalCodeFactory);
        CustomerPhoneColumn.setCellValueFactory(custPhoneFactory);

        CustomerCustomerIDTextField.setText("Auto Generated");

        //disable input for CustomerID since its auto-generated
        disableCustomerFields();

        try {
            CustomerActiveRadioButton.setSelected(false);
            CustomerInactiveRadioButton.setSelected(false);
            selectedCustomer.setCustomerActive(1);

            System.out.println("Current userID: " + User.getUserId());
            System.out.println("Current Username: " + User.getUsername());
            updateCustomerTable();
            try {
                fillDivisionComboBox();
            } catch (Exception ex) {
                Logger.getLogger(CustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fillCountryComboBox();
            } catch (Exception ex) {
                Logger.getLogger(CustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Listen for mouse click on item in Customer Table
        CustomerTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        customerListener(newValue);
                    } catch (Exception ex) {
                        System.out.println("Customer Listener had an error!");
                    }
                });
    }    
    
    //prevents inputs in customer fields when not adding or updating a customer
    public void disableCustomerFields(){
        CustomerActiveRadioButton.setDisable(true);
        CustomerInactiveRadioButton.setDisable(true);
        CustomerCustomerIDTextField.setDisable(true);
        CustomerCustomerNameTextField.setDisable(true);
        CustomerAddressTextField.setDisable(true);
        CustomerDivisionComboBox.setDisable(true);
        CustomerCountryComboBox.setDisable(true);
        CustomerPostalCodeTextField.setDisable(true);
        CustomerPhoneTextField.setDisable(true);
        CustomerSaveButton.setDisable(true);
        CustomerCancelButton.setDisable(true);
        CustomerDeleteButton.setDisable(true);
    }
    
    //prevents inputs in customer fields when not adding or updating a customer
    public void enableCustomerFields(){
        CustomerActiveRadioButton.setDisable(false);
        CustomerInactiveRadioButton.setDisable(false);
        CustomerCustomerIDTextField.setDisable(false);
        CustomerCustomerNameTextField.setDisable(false);
        CustomerAddressTextField.setDisable(false);
        CustomerDivisionComboBox.setDisable(false);
        CustomerCountryComboBox.setDisable(false);
        CustomerPostalCodeTextField.setDisable(false);
        CustomerPhoneTextField.setDisable(false);
        CustomerSaveButton.setDisable(false);
        CustomerCancelButton.setDisable(false);
        CustomerDeleteButton.setDisable(false);
    }
    
    public void updateCustomerTable() throws SQLException {
        System.out.println("***** Begin Update Customer Table *****");
        customerOL.clear();
        //create statement object
        Statement stmt = ConnectDB.conn.createStatement();

        //Write SQL statement (columns from two tables)
        String sqlStatement = "SELECT customers.Customer_Name, customers.Address, customers.Postal_Code, customers.Phone "
                + "FROM customers "
                + "ORDER BY customer.Customer_Name";

        //execute statement and create resultset object
        ResultSet result = stmt.executeQuery(sqlStatement);

        //get all records from resultset object
        while (result.next()) {
            Customer cust = new Customer();
            cust.setCustomerName(result.getString("customerName"));
            cust.setCustomerAddress(result.getString("address"));
            cust.setCustomerPostalCode(result.getString("postalCode"));
            cust.setCustomerPhone(result.getString("phone"));            
            customerOL.addAll(cust);
        }
        CustomerTable.setItems(customerOL);
        System.out.println("***** End Update Customer Table *****");
    }
    
    //populate CityComboBox with all available cities
    public void fillDivisionComboBox() throws SQLException, Exception {
        //Write SQL statement 
        String sqlStatement = "SELECT first-level_divisions.Division FROM first-level_divisions";

        //create statement object
        PreparedStatement pst = ConnectDB.makeConnection().prepareStatement(sqlStatement);

        //execute statement and create resultset object
        ResultSet result = pst.executeQuery(sqlStatement);

        //get all records from resultset obj
        while (result.next()) {
            divisionOptions.add(result.getString("city"));
            CustomerDivisionComboBox.setItems(divisionOptions);

        }
        pst.close();
        result.close();
    }

    //populate CountryComboBox with all available countries
    public void fillCountryComboBox() throws SQLException, Exception {
        //create statement object
        Statement stmt = ConnectDB.makeConnection().createStatement();

        //Write SQL statement (columns from two tables)
        String sqlStatement = "SELECT countries.Country FROM countries";

        //execute statement and create resultset object
        ResultSet result = stmt.executeQuery(sqlStatement);

        //get all records from resultset obj
        while (result.next()) {
            countryOptions.add(result.getString("country"));
            CustomerCountryComboBox.setItems(countryOptions);
        }
        stmt.close();
        result.close();
    }
    
    //method is called when customer table listener detects customer selection to update customer
    public void customerListener(Customer customer) throws SQLException, Exception {

        System.out.println("***** Begin Customer Listener *****");
        
        Customer cust = new Customer();
        cust = customer;
        String custName = cust.getCustomerName();
        int custId = cust.getCustomerId();
        ObservableList<Customer> customerOL = FXCollections.observableArrayList();

        customerUpdate = true;
        customerAdd = false;
        enableCustomerFields();

        //create statement object
        PreparedStatement ps = ConnectDB.makeConnection().prepareStatement("SELECT * "
                + "FROM customers, first-level_divisions, countries "
                + "WHERE customers.Customer_ID = ? AND customers.Division_ID = first-level_divisions.Division_ID AND first-level_divisions.Country_ID = countries.Country_ID");
        
        //execute statement and create resultset object
        ps.setInt(1, custId);
        ResultSet result = ps.executeQuery();
        System.out.println("SQL Statement: " + ps);
        while (result.next()) {
            System.out.println("CustomerID: " + result.getInt("customerID"));

            CustomerCustomerIDTextField.setText(Integer.toString(result.getInt("customerId")));
            CustomerCustomerNameTextField.setText(result.getString("customerName"));
            CustomerAddressTextField.setText(result.getString("address"));
            CustomerDivisionComboBox.setValue(result.getString("division"));
            CustomerCountryComboBox.setValue(result.getString("country"));
            CustomerPostalCodeTextField.setText(result.getString("postalCode"));
            CustomerPhoneTextField.setText(result.getString("phone"));
            if (result.getInt("active") == 0) {
                System.out.println("Active: " + result.getInt("active"));
                CustomerActiveRadioButton.setSelected(false);
                CustomerInactiveRadioButton.setSelected(true);
            } else {
                System.out.println("Active: " + result.getInt("active"));
                CustomerActiveRadioButton.setSelected(true);
                CustomerInactiveRadioButton.setSelected(false);
            }

            System.out.println("***** End Customer Listener *****");
        }
    }
    
    @FXML
    private void CustomerActiveRadioButtonHandler(ActionEvent event
    ) {
        selectedCustomer.setCustomerActive(1);
        System.out.println("Active: " + selectedCustomer.getCustomerActive());
    }

    @FXML
    private void CustomerInactiveRadioButtonHandler(ActionEvent event
    ) {
        selectedCustomer.setCustomerActive(0);
        System.out.println("Active: " + selectedCustomer.getCustomerActive());
    }

    @FXML
    private void CustomerBackButtonHandler(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage = (Stage) CustomerBackButton.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
