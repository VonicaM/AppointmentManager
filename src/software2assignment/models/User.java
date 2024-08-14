package software2assignment.models;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * User manages user information in order to allow for an easier identification
 * process during login, as well as with other actions that can be done in the
 * application.
 * 
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class User {
    private static int userId; //auto incremented in database
    private static String username;
    private String password;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    
    /**
     * Constructor.
     * 
     * @param userId
     * @param username
     * @param password 
     * @param createDate 
     * @param createdBy 
     * @param lastUpdate 
     * @param lastUpdatedBy 
     */
    public User(int userId, String username, String password, Date createDate, String createdBy, Timestamp lastUpdate,
            String lastUpdatedBy) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    /**
     * Constructor.
     */
    public User() {
    }
    
    /**
     * Gets the requested user's ID.
     * 
     * @return the user's ID.
     */
    public static int getUserId() {
        return userId;
    }
    
    /**
     * Gets the requested user's username.
     * 
     * @return the user's username.
     */
    public static String getUsername() {
        return username;
    }
    
    /**
     * Gets the requested user's password.
     * 
     * @return the user's password.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Gets the requested user's creation date.
     * 
     * @return the user's creation date.
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * Gets the requested user's creator.
     * 
     * @return the user's creator.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * Gets the requested user's last update time.
     * 
     * @return the user's last update time.
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    
    /**
     * Gets the requested user's last updater.
     * 
     * @return the user's last updater.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param userId 
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @param createDate 
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    /**
     * @param createdBy 
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    /**
     * @param lastUpdate 
     */
    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    /**
     * @param lastUpdatedBy 
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}