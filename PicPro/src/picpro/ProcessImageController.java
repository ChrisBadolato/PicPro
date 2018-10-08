
package picpro;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.FileChooser;
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
     @FXML
    private Button incrementPhotosUp;
    @FXML
    private Button incrementPhotosDown;
            
    int listValue = 0;
    
    public static ArrayList<imageObject> imageObjectList = new ArrayList<>();   
    public static ArrayList<BufferedImage> imageList = new ArrayList<>();
   


        //initalziation controller.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO              
    }  
    
    public int ListValue(){
        return listValue;
    }
        //Quits the program completely
    public ArrayList imageObjectList(){
        return imageObjectList;
    }

    
    
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
        Parent homeParent = loader.getRoot();  
        Scene homeMenuScene = new Scene(homeParent);
        Stage homeMenuStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        homeMenuStage.setScene(homeMenuScene);           
        homeMenuStage.show();
    } 
    
    @FXML
    public void selectButton(MouseEvent event) throws IOException{
        
        
        System.out.print(listValue);
        
            //Creates a new filter object with the currently viewed image.
        FilterOne filter1 = new FilterOne(imageObjectList.get(listValue).newImage);
            //Grabs our newly created image from our first filter.
        BufferedImage editedImage =  FilterOne.returnImage();     
            //
        imageObjectList.get(listValue).newImage = editedImage;
        
        WritableImage updatedImage;                    
        updatedImage = SwingFXUtils.toFXImage(editedImage, null);               
        imageSlot.setImage(updatedImage);
        
                      
    }
   
    @FXML
    public void browseButton(MouseEvent event) throws IOException{    
        
        
        Stage browseStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open An Image");  
        
        List<File> filesList = fileChooser.showOpenMultipleDialog(browseStage);
        
        for(File loop : filesList){
            if(loop != null){
                String fileName;
                fileName = loop.getPath();
                BufferedImage imageToWrite;            
                    //rewrites it so we can replace it back on the imageview panel 
                    //add new image to image array list
                imageToWrite = ImageIO.read(loop); 
                imageList.add(imageToWrite);                       
                 //create new imageObject
                    //add the image to the Image array list, and add the new object to the 
                    //object list. Used image object for easy acess to each attribute of the images.
                imageObject imageObject = new imageObject(fileName, loop, imageToWrite);                           
                 
                imageObjectList.add(imageObject);
                    //update the browseText field. Increment the list location.
                browseField.setText(imageObjectList.get(listValue).getFileName()); 
                System.out.println(listValue);
                System.out.println("Object Array value " + listValue);
                listValue++; 
                
            }
            
        }  
        /*
            /*String browseText;
            browseText = browseField.getText();
            System.out.println(browseText);   
                  
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
        */
        
           
   } 
   public void incrementPhotosDown(MouseEvent event) throws IOException{
        //if there is an item on our list we want to increment to the previous item
        //resetting the list value to the size of the list will take us to the end of the photo list
      if(!imageList.isEmpty()){          
            listValue--;                
            if(listValue < 0){                       
                listValue = imageList.size() - 1;
            }   
                //Reset the text of our browseField as well as the actual image on the UI
            browseField.setText(imageObjectList.get(listValue).getFileName());                
            BufferedImage imageToWrite = ImageIO.read(imageObjectList.get(listValue).getFile());                
            WritableImage updatedImage;
            updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);               
            imageSlot.setImage(updatedImage); 
      }
       

    }    
      public void incrementPhotosUp(MouseEvent event) throws IOException{
            //if there is an item on our list we want to increment to the Next item
            //resetting the list value to the size of the list will take us to the front of the photo list
       if(!imageList.isEmpty()){
            listValue++;        
            if(listValue > imageList.size() - 1){
                listValue = 0;
            }     
                //Reset the text of our browseField as well as the actual image on the UI
            browseField.setText(imageObjectList.get(listValue).getFileName());                
            BufferedImage imageToWrite = ImageIO.read(imageObjectList.get(listValue).getFile());                
            WritableImage updatedImage;
            updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);               
            imageSlot.setImage(updatedImage); 
       }
    } 
}  

     

