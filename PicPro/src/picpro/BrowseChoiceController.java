
package picpro;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
 * @author 
 *          Chris Badolato
 *          Frank Volk
 *          Ryan Deyoung
 *          Triston Hernandez
 */
public class BrowseChoiceController implements Initializable {
        //variable controllers for each added button.
    @FXML
    private Button openButton;
    @FXML
    private Button openMultipleButton;
    
    private Desktop desktop = Desktop.getDesktop();

    /**
     * Initializes the controller class for the current FXML file.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }    
        
        //controller to Open one File of any type. 
        //Will need to change the image to Image object type.
    @FXML
    private void clickOpenOneButton(MouseEvent event) throws IOException {
            //Shows the dialog box to access the computers files for
            //selection of our image or at this point any file type.
        Stage browseStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open An Image");  
        File file = fileChooser.showOpenDialog(browseStage);             
            if (file != null) {
                openFile(file);                   
            }               
    }
        //controller for open multiple files
        //currently under construction and does not open more than one file.
       
    @FXML
    private void clickOpenMultipleButton(MouseEvent event) throws IOException {
                           
        Stage browseStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open An Image");
            //As each file is selected store it on to our list.
        List<File> list = fileChooser.showOpenMultipleDialog(browseStage);    
            if (list != null) {                       
                for (File file : list) {                         
                        openFile(file);
                        list.add(file);
                    }
            }                 
    }
        //opens file method.
    private void openFile(File file) throws IOException {
                //Opens the file that you select 
                //Will only open file does not store it anywhere yet.
            desktop.open(file);
            String fileString;
            fileString = file.getName();
                    
                //Create new image file, store it as an object.
                
                //System.out.print("FileName: " + fileString);           
                //FileString fileName = new FileString();
                //fileName.setFile(fileString);
                //imageObject image1 = new imageObject();
                //image1.setFile(fileString);                      
    }       
}
