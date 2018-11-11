/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

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
import javafx.scene.control.CheckBox;
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
 * @author Chris Badolato
 */
public class FranksFiltersController implements Initializable {

    @FXML
    private TextField instaSearchBar;
    @FXML
    private Button instaSearch;
    @FXML
    private TextField magnitudeBar;
    @FXML
    private TextField densityBar;
    @FXML
    private TextField strokeBar;
    @FXML
    private CheckBox boxesCheck;
    @FXML
    private CheckBox glitchCheck;
    @FXML
    private CheckBox strokesCheck;
    @FXML
    private ImageView imageSlot;
       
    BufferedImage editedImage;
    int listValue = 0;  
    //public static ArrayList<imageObject> imageObjectList = new ArrayList<>();  
    public static ArrayList<BufferedImage> imageList = new ArrayList<>();
    @FXML
    private TextField imageCountsField;
   


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getInstagramPictures(MouseEvent event) {
        String instagramToFind = instaSearchBar.getText();
        String numberOfPhotos = imageCountsField.getText();
        System.out.print(instagramToFind);     
        int photoNumbers = Integer.parseInt(numberOfPhotos);
        
        instaDriver newDriver = new instaDriver(instagramToFind, photoNumbers);
        imageList = instaDriver.returnImages();
        newDriver.close();
        
        if(!imageList.isEmpty()){
            WritableImage updatedImage;
            updatedImage = SwingFXUtils.toFXImage(imageList.get(0), null);       
            imageSlot.setImage(updatedImage);
        }    
    }

    @FXML
    private void applyFilter(MouseEvent event) { 
        editedImage = imageList.get(listValue); 
        
        String magnitudeString = magnitudeBar.getText();
        String densityString = densityBar.getText();
        String strokesString = strokeBar.getText();
        
        int magnitude = Integer.parseInt(magnitudeString);
        int density = Integer.parseInt(densityString);
        int strokes = Integer.parseInt(strokesString);
        
        if(boxesCheck.isSelected()){
            filterBoxes boxes = new filterBoxes(editedImage, magnitude, density, strokes);           
            editedImage =  filterBoxes.returnImage();
        }
        else if(glitchCheck.isSelected()){
            filterGlitch glitch = new filterGlitch(editedImage, magnitude, density, strokes);           
            editedImage =  filterGlitch.returnImage();
        }
        else if(strokesCheck.isSelected()){
            filterStrokes filter = new filterStrokes(editedImage, magnitude, density, strokes);
            editedImage = filterStrokes.returnImage();
        }
        else{
            editedImage = imageList.get(listValue);
        }
        WritableImage updatedImage;  
        updatedImage = SwingFXUtils.toFXImage(editedImage, null);       
        imageSlot.setImage(updatedImage);               
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
    public void browseButton(MouseEvent event) throws IOException{   
            //loader for our file chooser
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
                    imageList.add(imageToWrite);
                        //update the browseText field. Increment the list location.                
                    positionForNewImages++;    
                } 
            } 
            WritableImage updatedImage = SwingFXUtils.toFXImage(imageList.get(0), null);               
            imageSlot.setImage(updatedImage);
        }         
   } 
    
    @FXML
    public void incrementPhotosDown(MouseEvent event) throws IOException{
            //if there is an item on our list we want to increment to the previous item
            //resetting the list value to the size of the list will take us to the end of the photo list
        if(!imageList.isEmpty()){          
            listValue--;                
            if(listValue < 0){                       
                listValue = imageList.size() - 1;
            }   
                //Reset the text of our browseField as well as the actual image on the UI
            BufferedImage imageToWrite = imageList.get(listValue);                
            WritableImage updatedImage;
            updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);               
            imageSlot.setImage(updatedImage); 
        }      
    }    
   
    @FXML
    public void incrementPhotosUp(MouseEvent event) throws IOException{
            //if there is at least one item on our list we want to increment to the Next item
            //resetting the list value to the size of the list will take us to the front of the photo list
        if(!imageList.isEmpty()){                   
            listValue++;
            if(listValue > imageList.size() - 1){
                listValue = 0;                                
                BufferedImage imageToWrite = imageList.get(listValue);                
                WritableImage updatedImage;
                updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);               
                imageSlot.setImage(updatedImage);   
            }  
            else{ 
                    //Reset the text of our browseField as well as the actual image on the UI                
                BufferedImage imageToWrite = imageList.get(listValue);                
                WritableImage updatedImage;
                updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);               
                imageSlot.setImage(updatedImage); 
            }
        }
    } 
        //calls our save function on click
    @FXML
    private void saveButton(MouseEvent event) throws IOException {
        Stage saveStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        save(saveStage, editedImage);     
    }
        //Save function
        //opens File chooser so user can select where to save
        //creates new image object and adds it to the list for viewing
    public void save(Stage saveStage, BufferedImage imageToSave) throws IOException{        
        WritableImage updatedImage;  
        updatedImage = SwingFXUtils.toFXImage(imageToSave, null);               
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        File file = fileChooser.showSaveDialog(saveStage);
        if (file != null) {  
            ImageIO.write(SwingFXUtils.fromFXImage(updatedImage, null), "png", file); 
            String fileString = file.getPath();
            System.out.println(fileString); 
            imageList.add(imageToSave);  
            listValue++;
        }           
    }    
}
