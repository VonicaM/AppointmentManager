package software2assignment.models;

import java.sql.Date;

/**
 * Customer manages information on customer's that the application needs in
 * order to provide information to users and mange appointments.
 * 
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class Customer {
    private int customerId;//auto generated
    private String customerName;
    private int active;
    private Date createDate;
    private String createdBy;
    private String address;
    private String postalCode;
    private String phone;
    private Date lastUpdate;
    private String lastUpdatedBy;
    private int divisionId;//auto generated
    
    /**
     * Constructor.
     */
    public Customer() {
    }

    /**
     * Constructor.
     * 
     * @param customerId
     * @param customerName
     * @param active
     * @param createDate
     * @param createdBy
     * @param address
     * @param postalCode
     * @param phone
     * @param country
     * @param lastUpdate
     * @param lastUpdatedBy 
     * @param divisionId 
     */
    public Customer(int customerId, String customerName, int active, Date createDate, String createdBy, String address,
            String postalCode, String phone, Date lastUpdate, String lastUpdatedBy, int divisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.active = active;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionId = divisionId;
    }

    /**
     * Constructor.
     * 
     * @param customerId
     * @param customerName 
     */
    public Customer(int customerId, String customerName) {
        this.customerId = customerId;
        this.customerName = customerName;
    }
    
    /**
     * Gets the requested customer's ID.
     * 
     * @return the customer's ID.
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Gets the requested customer's name.
     * 
     * @return the customer's name.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Gets the requested customer's active status.
     * 
     * @return the customer's activity number.
     */
    public int getCustomerActive() {
        return active;
    }
    
    /**
     * Gets the requested customer's creation date.
     * 
     * @return the customer's creation date.
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * Get the requested customer's creator.
     * 
     * @return the customer's creator.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * Gets the requested customer's address.
     * 
     * @return the customer's address.
     */
    public String getCustomerAddress() {
        return address;
    }

    /**
     * Get the requested customer's postal code.
     * 
     * @return the customer's postal code.
     */
    public String getCustomerPostalCode() {
        return postalCode;
    }

    /**
     * Get the requested customer's phone number.
     * 
     * @return the customer's phone number.
     */
    public String getCustomerPhone() {
        return phone;
    }

    /**
     * Get the requested customer's last update date.
     * 
     * @return the customer's last update date.
     */
    public Date getCustomerLastUpdate() {
        return lastUpdate;
    }

    /**
     * Get the requested customer's last updater.
     * 
     * @return the customer's last updater.
     */
    public String getCustomerLastUpdatedBy() {
        return lastUpdatedBy;
    }
    
    /**
     * Gets the division ID associated with the requested customer.
     * 
     * @return the associated division ID.
     */
    public int getDivisionId() {
        return divisionId;
    }
    
    /**
     * @param customerId 
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @param customerName 
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @param active 
     */
    public void setCustomerActive(int active) {
        this.active = active;
    }

    /**
     * @param address 
     */
    public void setCustomerAddress(String address) {
        this.address = address;
    }

    /**
     * @param postalCode 
     */
    public void setCustomerPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @param phone 
     */
    public void setCustomerPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @param lastUpdate 
     */
    public void setCustomerLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @param lastUpdatedBy 
     */
    public void setCustomerLastUpdateBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    /**
     * @param divisionId 
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
}