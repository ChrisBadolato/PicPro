
package picpro;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author
 *          Chris Badolato
 *          Frank Volk
 *          Ryan Deyoung
 *          Triston Hernandez
 */



public class ProcessImageController implements Initializable {
          
        //Inializes button controllers
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
    
    int listValue = 0;
    
    public static ArrayList<imageObject> imageList = new ArrayList<>();
            


        //initalziation controller.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        imageList = BrowseChoiceController.getList();
                
    }  
        //Quits the program completely
    @FXML
    public void quitButton(MouseEvent event){
       Stage stage = (Stage) quitButton.getScene().getWindow();
       stage.close();
    }
   
    @FXML 
    public void backButton(MouseEvent event) throws IOException{
            //loads the FXML of the previous menu. 
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        loader.load();
       // Parent homeParent = loader.getRoot();               
    } 
    
    @FXML
    public void selectButton(MouseEvent event){
        
        //imageList = BrowseChoiceController.getList();
        BufferedImage imageToWrite = null;
        
        
        File in;
        
            //opens image file
        try
        {
            in = imageList.get(listValue).sourceFile;
            imageToWrite = ImageIO.read(in);
            System.out.println("Reading complete.");
        }
        catch(IOException e)
        {
            System.out.println("Error:" +e);
        } 
            //rewrites it so we can replace it back on the imageview panel
        WritableImage updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);
        imageSlot.setImage(updatedImage);      
        browseField.setText(imageList.get(listValue).getFileName()); 
        System.out.println(listValue);
        listValue++;                        
    }
   
    @FXML
    public void browseButton(MouseEvent event) throws IOException{    
        
        
            /*String browseText;
            browseText = browseField.getText();
            System.out.println(browseText);   
            */     
            
            // Loads the FXML of the main menu.                
            // creates the stage for our browse buttons to open one or more files.  
            // Dialog leaves the previous window open while opening the browse selection          
        Node node = (Node) event.getSource();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("browseChoice.fxml"));
        loader.load();         
        Parent browseParent = loader.getRoot();      
        Scene browseScene = new Scene(browseParent);
        Stage parentStage = (Stage) node.getScene().getWindow();         
        final Stage dialog = new Stage();
        
            // Modality makes it so we cannot click the previous window while the dialog is opened    
            
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(parentStage);
        dialog.setScene(browseScene);
        dialog.show();     

           
   } 
     
}
