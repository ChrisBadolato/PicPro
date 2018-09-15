
package picpro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 
 *          Chris Badolato
 *          Frank Volk
 *          Ryan Deyoung
 *          Triston Hernandez
 */
public class PicPro extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
            //Loads up our FXML document controller.
            //Initalizes the main menu screen as our FMXL
        Parent menu = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));       
        Scene mainMenuScene = new Scene(menu);     
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);   
    }   
}
