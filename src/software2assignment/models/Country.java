package software2assignment.models;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Country contains and manages information on countries for customers, allowing
 * for this information to be stored with minimum repetition of information.
 * 
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class Country {
    private int countryId;
    private String country;
    private Date createDate;
    private String createdBy;
    private Timestamp lastUpdate;
    private String lastUpdatedBy;
    
    public Country(int countryId, String country, Date createDate, String createdBy, Timestamp lastUpdate,
            String lastUpdatedBy) {
        this.countryId = countryId;
        this.country = country;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    /**
     * Gets the requested country's ID.
     * 
     * @return the country's ID.
     */
    private int getCountryId() {
        return countryId;
    }
    
    /**
     * Get the requested country's name.
     * 
     * @return the country's name.
     */
    private String getCountry() {
        return country;
    }
    
    /**
     * Gets the requested country's creation date.
     * 
     * @return the country's creation date.
     */
    public Date getCreateDate() {
        return createDate;
    }
    
    /**
     * Gets the requested country's creator.
     * 
     * @return the country's creator.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    
    /**
     * Gets the requested country's last update time.
     * 
     * @return the country's last update time.
     */
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }
    
    /**
     * Gets the requested country's last updater.
     * 
     * @return the country's last updater.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }
    
    /**
     * @param countryId 
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    
    /**
     * @param country 
     */
    public void setCountry(String country) {
        this.country = country;
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