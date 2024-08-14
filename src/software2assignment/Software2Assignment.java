package software2assignment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import software2assignment.utilities.ConnectDB;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Inventory Application for C195, Software 2.
 * This application is a scheduling application that allows the user to 
 * manage customer records and appointments through a JavaFX GUI.
 * 
 * @author Michael Vonica michaelvonica@gmail.com
 */
public class Software2Assignment extends Application{
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Start...");
        Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        System.out.println("End of Start section");
    }
    
    public static void main(String[] args) throws SQLException, Exception {
        ConnectDB.makeConnection();
        launch(args);
        ConnectDB.closeConnection();
    }
}