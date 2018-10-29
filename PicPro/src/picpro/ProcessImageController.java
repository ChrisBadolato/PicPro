
package picpro;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    @FXML
    private Button rotateButton;
    
            
    int listValue = 0;
    int counter;
    int last = -1;
    
    public static ArrayList<imageObject> imageObjectList = new ArrayList<>();   
    //public static ArrayList<BufferedImage> imageList = new ArrayList<>();
    @FXML
    private Button SaveButton;
    
    ObservableList<String> choiceBoxList = FXCollections.observableArrayList("Original Image", "8-bit Filter","Black and White Filter", "Sepia Filter");
    
    @FXML
    private ChoiceBox filterChoice;
   


        //initalziation controller.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filterChoice.setItems(choiceBoxList);
        filterChoice.setValue("Original Image");
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
    public void rotateButton(MouseEvent event) throws IOException{
        if(counter == 4){
            counter = 1;
        }
        else{
            counter++;
        }   
        BufferedImage editedImage;
        editedImage = imageObjectList.get(listValue).newImage;
        
        if(last == -1 || listValue != last){
            counter = 1;
        }
        
        last = listValue;
        
        rotate filter1 = new rotate(editedImage, counter);
        editedImage = rotate.returnImage();
        
        WritableImage updatedImage;  
        updatedImage = SwingFXUtils.toFXImage(editedImage, null);       
        imageSlot.setImage(updatedImage);
    }
    
    @FXML
    public void processButton(MouseEvent event) throws IOException{
        BufferedImage editedImage ;
        editedImage = imageObjectList.get(listValue).newImage;
        String boxChoice;
        boxChoice = (String) filterChoice.getValue();
        if("8-bit Filter".equals(boxChoice)){
        //Grabs our newly created image from our first filter.
            eightBit filter1 = new eightBit(editedImage);           
            editedImage =  eightBit.returnImage();
        }
        if("Black and White Filter".equals(boxChoice)){
            blackWhite filter1 = new blackWhite(editedImage);           
            editedImage =  blackWhite.returnImage();
        }
        if("Sepia Filter".equals(boxChoice)){
        //Grabs our newly created image from our first filter.
            sepia filter1 = new sepia(editedImage);           
            editedImage =  sepia.returnImage();
        }
        else if("Orginal Image".equals(boxChoice)){
            editedImage = imageObjectList.get(listValue).newImage;
        }

        WritableImage updatedImage;  
        updatedImage = SwingFXUtils.toFXImage(editedImage, null);       
        imageSlot.setImage(updatedImage);
        
    }
   
    @FXML
    public void browseButton(MouseEvent event) throws IOException{    
        
        Stage browseStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Images");  
        
        List<File> filesList = fileChooser.showOpenMultipleDialog(browseStage);
        int positionForNewImages = listValue;
        if(filesList != null){
            for(File loop : filesList){
                if(loop != null){
                    String fileName;
                    fileName = loop.getPath();
                    BufferedImage imageToWrite;            
                        //rewrites it so we can replace it back on the imageview panel 
                        //add new image to image array list
                    imageToWrite = ImageIO.read(loop);                       
                        //create new imageObject
                        //add the image to the Image array list, and add the new object to the 
                        //object list. Used image object for easy acess to each attribute of the images.
                    imageObject imageObject = new imageObject(fileName, loop, imageToWrite);                           
                    imageObjectList.add(imageObject);
                        //update the browseText field. Increment the list location.                
                        //System.out.println("Object Array value " + positionForNewImages);
                    positionForNewImages++;    
                } 
            } 
            browseField.setText(imageObjectList.get(0).getFileName());
            WritableImage updatedImage = SwingFXUtils.toFXImage(imageObjectList.get(0).getImage(), null);               
            imageSlot.setImage(updatedImage);
        }         
   } 
    
    @FXML
    public void incrementPhotosDown(MouseEvent event) throws IOException{
        //if there is an item on our list we want to increment to the previous item
        //resetting the list value to the size of the list will take us to the end of the photo list
        if(!imageObjectList.isEmpty()){          
            listValue--;                
            if(listValue < 0){                       
                listValue = imageObjectList.size() - 1;
            }   
                //Reset the text of our browseField as well as the actual image on the UI
            browseField.setText(imageObjectList.get(listValue).getFileName());                
            BufferedImage imageToWrite = ImageIO.read(imageObjectList.get(listValue).getFile());                
            WritableImage updatedImage;
            updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);               
            imageSlot.setImage(updatedImage); 
        }      
    }    
   
    @FXML
    public void incrementPhotosUp(MouseEvent event) throws IOException{
            //if there is an item on our list we want to increment to the Next item
            //resetting the list value to the size of the list will take us to the front of the photo list
        if(!imageObjectList.isEmpty()){                   
            System.out.println("ListValue" + listValue);
            //System.out.println("imageList size" + imageList.size());
            listValue++;
            // System.out.print("ListValue" + listValue);
            if(listValue > imageObjectList.size() - 1){
                listValue = 0;   
                //System.out.print("ListValue" + listValue);
                browseField.setText(imageObjectList.get(listValue).getFileName());                
                BufferedImage imageToWrite = ImageIO.read(imageObjectList.get(listValue).getFile());                
                WritableImage updatedImage;
                updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);               
                imageSlot.setImage(updatedImage);   
            }  
            else{ 
                //Reset the text of our browseField as well as the actual image on the UI
                System.out.println("else");
                browseField.setText(imageObjectList.get(listValue).getFileName());                
                BufferedImage imageToWrite = ImageIO.read(imageObjectList.get(listValue).getFile());                
                WritableImage updatedImage;
                updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);               
                imageSlot.setImage(updatedImage); 
            }
        }
    } 

    @FXML
    private void saveButton(MouseEvent event) throws IOException {
        
        if(counter == 1){
            BufferedImage editedImage;
            editedImage = imageObjectList.get(listValue).newImage;
            rotate filter1 = new rotate(editedImage, counter);
            editedImage = rotate.returnImage();
            Stage saveStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            save(saveStage, editedImage);
        }
        if(counter == 2){
            BufferedImage editedImage;
            editedImage = imageObjectList.get(listValue).newImage;
            rotate filter1 = new rotate(editedImage, counter);
            editedImage = rotate.returnImage();
            Stage saveStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            save(saveStage, editedImage);
        }
        if(counter == 3){
            BufferedImage editedImage;
            editedImage = imageObjectList.get(listValue).newImage;
            rotate filter1 = new rotate(editedImage, counter);
            editedImage = rotate.returnImage();
            Stage saveStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            save(saveStage, editedImage);
        }
        if(counter == 4){
            BufferedImage editedImage;
            editedImage = imageObjectList.get(listValue).newImage;
            rotate filter1 = new rotate(editedImage, counter);
            editedImage = rotate.returnImage();
            Stage saveStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            save(saveStage, editedImage);
        }
        
        String boxChoice;
        boxChoice = (String) filterChoice.getValue();
        if("8-bit Filter".equals(boxChoice)){
            BufferedImage editedImage ;
            editedImage = imageObjectList.get(listValue).newImage;
                //Creates a new filter object with the currently viewed image.
                //Grabs our newly created image from our first filter.
            eightBit filter1 = new eightBit(editedImage);            
            editedImage =  eightBit.returnImage();
            Stage saveStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            save(saveStage, editedImage);
        } 
        if("Black and White Filter".equals(boxChoice)){
            BufferedImage editedImage ;
            editedImage = imageObjectList.get(listValue).newImage;
                //Creates a new filter object with the currently viewed image.
                //Grabs our newly created image from our first filter.
            blackWhite filter1 = new blackWhite(editedImage);            
            editedImage =  blackWhite.returnImage();
            Stage saveStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            save(saveStage, editedImage);
        } 
        if("Sepia Filter".equals(boxChoice)){
            BufferedImage editedImage ;
            editedImage = imageObjectList.get(listValue).newImage;
                //Creates a new filter object with the currently viewed image.
                //Grabs our newly created image from our first filter.
            sepia filter1 = new sepia(editedImage);            
            editedImage =  sepia.returnImage();
            Stage saveStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            save(saveStage, editedImage);
        }       
        else if("Orginal Image".equals(boxChoice)){
            Stage saveStage = (Stage)((Node) event.getSource()).getScene().getWindow();
            save(saveStage, imageObjectList.get(listValue).newImage);
        }
    }
    
    public void save(Stage saveStage, BufferedImage imageToSave) throws IOException{        
        WritableImage updatedImage;  
        updatedImage = SwingFXUtils.toFXImage(imageToSave, null);       
            //imageSlot.setImage(updatedImage);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        File file = fileChooser.showSaveDialog(saveStage);
        if (file != null) {  
            ImageIO.write(SwingFXUtils.fromFXImage(updatedImage, null), "png", file); 
            String fileString = file.getPath();
            System.out.println(fileString);
            imageObject imageObject = new imageObject(fileString, file, imageToSave);  
            imageObjectList.add(imageObject);  
            listValue++;
        }           
    }
}  