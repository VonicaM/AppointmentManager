package software2assignment.models;

/**
 *
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class Report {
    private String month;
    private int newAccount;
    private int consultation;
    private int followUp;
    private int closeAccount;
    private int email;
    private int phone;
    private int inperson;
    private int typesArray[];
    
    //constructors
    public Report(String month, int typesArray[]) {
        this.month = month;
        this.typesArray = typesArray;
    }
    
    public Report(String month, int newAccount, int consultation, int followUp, int closeAccount){
        this.month = month;
        this.newAccount = newAccount;
        this.consultation = consultation;
        this.followUp = followUp;
        this.closeAccount = closeAccount;        
    }
    
    public Report(String month, int email, int phone, int inperson){
        this.month = month;
        this.email = email;
        this.phone = phone;
        this.inperson = inperson;
    }
    
    /**
     * Gets the requested report's month.
     * 
     * @return the report's month.
     */
    public String getMonth() {
        return this.month;
    }

    /**
     * Gets the requested report's types array.
     * 
     * @return the report's types array.
     */
    public int[] getTypesArray() {
        return this.typesArray;
    }

    /**
     * Gets the requested report's new account.
     * 
     * @return the report's new account.
     */
    public int getNewAccount(){
        return this.newAccount;
    }
    
    /**
     * Gets the requested report's consultation.
     * 
     * @return the report's consultation.
     */
    public int getConsultation(){
        return this.consultation;
    }
    
    /**
     * Gets the requested report's follow up.
     * 
     * @return the report's follow up.
     */
    public int getFollowUp(){
        return this.followUp;
    }
    
    /**
     * Gets the report's close account.
     * 
     * @return the report's close account.
     */
    public int getCloseAccount(){
        return this.closeAccount;
    }
    
    /**
     * Gets the report's email.
     * 
     * @return the report's email.
     */
    public int getEmail(){
        return this.email;
    }
    
    /**
     * Gets the report's phone.
     * 
     * @return the report's phone.
     */
    public int getPhone(){
        return this.phone;
    }
    
    /**
     * Gets the report's inperson.
     * 
     * @return the report's inperson.
     */
    public int getInperson(){
        return this.inperson;
    }
    
    /**
     * @param month 
     */
    private void setMonth(String month) {
        this.month = month;
    }

    /**
     * @param newAccount 
     */
    private void setNewAccount(int newAccount){
        this.newAccount = newAccount;
    }
    
    /**
     * @param consultation 
     */
    private void setConsultation(int consultation){
        this.consultation = consultation;
    }
    
    /**
     * @param followUp 
     */
    private void setFollowUp(int followUp){
        this.followUp = followUp;
    }
    
    private void setCloseAccount(int closeAccount){
        this.closeAccount = closeAccount;
    }
    
    /**
     * @param email 
     */
    private void setEmail(int email){
        this.email = email;
    }
    
    /**
     * @param phone 
     */
    private void setPhone(int phone){
        this.phone = phone;
    }
    
    /**
     * @param inperson 
     */
    private void setInperson(int inperson){
        this.inperson = inperson;
    }
    
    /**
     * @param typesArray 
     */
    private void setTypesArray(int[] typesArray) {
        this.typesArray = typesArray;
    }
}