package software2assignment.models;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Customer manages information on customer's that the application needs in
 * order to provide information to users.
 * 
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class Appointment {
    private int appointmentId;//auto generated
    private int customerId;//auto generated
    private int userId;//auto generated
    private int contactId;//auto generated
    private String title;
    private String description;
    private String location;
    private String type;
    private Date startTime;
    private Date endTime;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    
    /**
     * Constructor.
     * 
     * @param appointmentId
     * @param customerId
     * @param userId
     * @param contactId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startTime
     * @param endTime
     * @param createDate
     * @param createdBy
     * @param lastUpdate
     * @param lastUpdatedBy 
     */
    public Appointment(int appointmentId, int customerId, int userId, int contactId, String title, String description, String location, 
                String type, Date startTime, Date endTime, Date createDate, String createdBy, Timestamp lastUpdate, String lastUpdatedBy) {
        this.appointmentId = appointmentId;//auto generated
        this.customerId = customerId;//auto generated
        this.userId = userId;//auto generated
        this.contactId = contactId;//auto generated
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;        
    }
    
    /**
     * Constructor.
     * 
     * @param appointmentId
     * @param customerId
     * @param startTime
     * @param endTime
     * @param title
     * @param type
     * @param createdBy 
     */
    public Appointment(int appointmentId, int customerId, Date startTime, Date endTime, String title, String type, String createdBy) {
        this.appointmentId = appointmentId;//auto generated
        this.customerId = customerId;//auto generated
        this.title = title;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdBy = createdBy;
    }
    
    /**
     * Constructor.
     * 
     * @param appointmentId
     * @param customerId
     * @param userId
     * @param contactId
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startTime
     * @param endTime
     * @param createdBy 
     */
    public Appointment(int appointmentId, int customerId, int userId, int contactId, String title, String description, String location, String type, 
                Date startTime, Date endTime, String createdBy) {
        this.appointmentId = appointmentId;//auto generated
        this.customerId = customerId;//auto generated
        this.userId = userId;//auto generated
        this.contactId = contactId;//auto generated
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdBy = createdBy;
    }
    
    /**
     * Constructor.
     * 
     * @param customerId
     * @param startTime
     * @param endTime
     * @param title
     * @param type
     * @param createdBy 
     */
    public Appointment(int customerId, Date startTime, Date endTime, String title, String type, String createdBy) {
        this.customerId = customerId;
        this.startTime = startTime; 
        this.endTime = endTime;
        this.title = title;
        this.type = type;
        this.createdBy = createdBy;
    }
   
    /**
     * Constructor.
     */
    public Appointment() {
    }
        
    /**
     * Gets the requested appointment's ID.
     * 
     * @return the appointment's ID.
     */
    public int getAppointmentId() {
        return this.appointmentId;
    }
    
    /**
     * Gets the customer ID associated with the requested appointment.
     * 
     * @return the associated customer ID.
     */
    public int getCustomerId() {
        return this.customerId;
    }
    
    /**
     * Gets the user ID associated with the requested appointment.
     * 
     * @return the associated user ID.
     */
    public int getUserId() {
        return this.userId;
    }
    
    /**
     * Gets the contact ID associated with the requested appointment.
     * 
     * @return the associated contact ID.
     */
    public int getContactId() {
        return this.contactId;
    }
    
    /**
     * Gets the requested appointment's title.
     * 
     * @return the appointment's title.
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Gets the requested appointment's description.
     * 
     * @return the appointment's description.
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Get the requested appointment's location.
     * 
     * @return the appointment's location.
     */
    public String getLocation() {
        return this.location;
    }
    
    /**
     * Gets the requested appointment's type.
     * 
     * @return the appointment's type.
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Gets the requested appointment's start time.
     * 
     * @return the appointment's start time.
     */
    public Date getStart() {
        return this.startTime;
    }
    
    /**
     * Gets the requested appointment's end time.
     * 
     * @return the appointment's end time.
     */
    public Date getEnd() {
        return this.endTime;
    }
    
    /**
     * Gets the requested appointment's creation date.
     * 
     * @return the appointment's creation date.
     */
    public Date getCreateDate() {
        return this.createDate;
    }
    
    /**
     * Gets the requested appointment's creator.
     * 
     * @return the appointment's creator.
     */
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    /**
     * Gets the requested appointment's last update time.
     * 
     * @return the appointment's last update time.
     */
    public Timestamp getLastUpdate() {
        return this.lastUpdate;
    }
    
    /**
     * Gets the requested appointment's last updater.
     * 
     * @return the appointment's last updater.
     */
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    
    /**
     * @param appointmentID 
     */
    private void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;//auto generated
    }
    
    /**
     * @param customerId 
     */
    private void setCustomerId(int customerId) {
        this.customerId = customerId;//auto generated
    }
    
    /**
     * @param userID 
     */
    private void setUserId(int userId) {
        this.userId = userId;//auto generated        
    }
    
    /**
     * @param contactId 
     */
    private void setContactId(int contactId) {
        this.contactId = contactId;//auto generated        
    }
    
    /**
     * @param title 
     */
    private void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * @param description 
     */
    private void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * @param location 
     */
    private void setLocation(String location) {
        this.location = location;
    }
    
    /**
     * @param type 
     */
    private void setType(String type) {
        this.type = type;
    }
    
    /**
     * @param startTime 
     */
    private void setStart(Date startTime) {
        this.startTime = startTime;
    }
    
    /**
     * @param endTime 
     */
    private void setEnd(Date endTime) {
        this.endTime = endTime;
    }
    
    /**
     * @param createDate 
     */
    private void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /**
     * @param createdBy 
     */
    private void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     * @param lastUpdate 
     */
    private void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    /**
     * @param lastUpdatedBy 
     */
    private void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
