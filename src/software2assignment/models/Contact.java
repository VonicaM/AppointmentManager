package software2assignment.models;

/**
 * Contact contains and manages information on contacts for appointments,
 * allowing for this information to be stored with minimum repetition of
 * information.
 * 
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class Contact {
    private int contactId;
    private String contactName;
    private String email;
    
    /**
     * Constructor.
     * 
     * @param contactId
     * @param contactName
     * @param email 
     */
    public Contact(int contactId, String contactName, String email) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.email = email;
    }
    
    /**
     * Constructor.
     */
    public Contact() {
    }
    
    /**
     * Gets the requested contact's ID.
     * 
     * @return the contact's ID.
     */
    public int getContactId() {
        return contactId;
    }
    
    /**
     * Gets the requested contact's name.
     * 
     * @return the contact's name.
     */
    public String getContactName() {
        return contactName;
    }
    
    /**
     * Gets the requested contact's email.
     * 
     * @return the contact's email.
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @param contactId 
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    
    /**
     * @param contactName 
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    /**
     * @param email 
     */
    public void setContactEmail(String email) {
        this.email = email;
    }
}
