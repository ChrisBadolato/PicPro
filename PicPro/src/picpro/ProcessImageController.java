/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chris
 */
public class ProcessImageController implements Initializable {

    @FXML
    private Button processButton;
    @FXML
    private Button backButton;
    @FXML
    private Button quitButton;
    @FXML
    private Button browseButton;
    @FXML
    private ImageView imageSlot;
    @FXML
    private TextField browseField;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
   @FXML
   public void quitButton(MouseEvent event){
       Stage stage = (Stage) quitButton.getScene().getWindow();
       stage.close();
   }
   
   @FXML 
   public void backButton(MouseEvent event) throws IOException{
       
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        loader.load();
        
        // Retrieve root of loader to get reference to the Scene and Stage
        Parent homeParent = loader.getRoot();
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
   }
   
   
   public void browseButton(MouseEvent event) throws IOException {
        
       
        
       
        String browseText;
        browseText = browseField.getText();
        System.out.println(browseText);
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("browseChoice.fxml"));
        loader.load();
        
        // Retrieve root of loader to get reference to the Scene and Stage
        Parent homeParent = loader.getRoot();
        Scene homeScene = new Scene(homeParent);
        Stage homeStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        homeStage.setScene(homeScene);
        homeStage.show();
        
        /*
        Stage browseStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open An Image");
        fileChooser.showOpenDialog(browseStage);
             */
        
        
   }
  
}
