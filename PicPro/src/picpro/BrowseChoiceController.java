/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.awt.Desktop;
import static java.awt.SystemColor.desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chris Badolato
 */
public class BrowseChoiceController implements Initializable {

    @FXML
    private Button openButton;
    @FXML
    private Button openMultipleButton;
    
    private Desktop desktop = Desktop.getDesktop();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
    }    
    @FXML
    private void clickOpenOneButton(MouseEvent event) throws IOException {
        
        Stage browseStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open An Image");
        fileChooser.showOpenDialog(browseStage);
                        
            File file = fileChooser.showOpenDialog(browseStage);
                if (file != null) {
                    openFile(file);                   
                }
            
    }
    
    
    @FXML
    private void clickOpenMultipleButton(MouseEvent event) throws IOException {
        
        Stage browseStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open An Image");
        fileChooser.showOpenDialog(browseStage);
        List<File> list = fileChooser.showOpenMultipleDialog(browseStage);
        
                    if (list != null) {
                        for (File file : list) {
                            openFile(file);
                        }
                    }
 
 
    }
        private void openFile(File file) throws IOException {
            desktop.open(file);
            
    }
    
    
}
