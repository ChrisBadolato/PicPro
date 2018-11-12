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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class TristonsFiltersController implements Initializable {

    @FXML
    private CheckBox twoLayerBorder;
    @FXML
    private CheckBox amber;
    @FXML
    private CheckBox cerulean;
    @FXML
    private CheckBox negative;
    @FXML
    private CheckBox america;
    @FXML
    private CheckBox forest;
    @FXML
    private CheckBox borderOne; 
    
    
    @FXML
    private Button browseButton;
    @FXML
    private ImageView imageSlot;
    @FXML
    private TextField browseField;
    @FXML
    private Button SaveButton;
    private Button quitButton;   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

        // variable to hold the current picture place to be used in the rotate button
    int listValue = 0;
        // variable to keep count of the number of times the rotate button as been pressed per image
    int counter;
        // variable to hold the last picture place for the rotate button 
    int last = -1;
        // buffered image to hold the image after filter has been applied
    BufferedImage editedImage ;
    
    public static ArrayList<imageObject> imageObjectList = new ArrayList<>();   
    

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
        
        //This user interface has checkboxes as opposed to the choicebox.
        //if our checkbox is selected apply the corresponding filter.
        //each one calls seperate filters on the Buffered Image.
        //Only one will be applied at a time.
    @FXML
    public void processButton(MouseEvent event) throws IOException{                     
        editedImage = imageObjectList.get(listValue).newImage;
        if(twoLayerBorder.isSelected()){
            TwoLayerBorder twoLayer = new TwoLayerBorder(editedImage);
            editedImage = TwoLayerBorder.returnImage();
        }
        else if(amber.isSelected()){
            Amber newAmber = new Amber(editedImage);
            editedImage = Amber.returnImage();
        }
        else if(borderOne.isSelected()){
            BorderTwo newBorder = new BorderTwo(editedImage);
            editedImage = BorderTwo.returnImage();
        }
        else if(cerulean.isSelected()){
            Cerulean newCerulean = new Cerulean(editedImage);
            editedImage = Cerulean.returnImage();
        }
        else if(america.isSelected()){
            America newAmerica = new America(editedImage);
            editedImage = America.returnImage();
        }
        else if(forest.isSelected()){
            Forest newForest = new Forest(editedImage);
            editedImage = Forest.returnImage();
        }
        else if(negative.isSelected()){
            Negative newNegative = new Negative(editedImage);
            editedImage = Negative.returnImage();
        }
        else{
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
            listValue++;          
            if(listValue > imageObjectList.size() - 1){
                listValue = 0;            
                    //Reset the text of our browseField as well as the actual image on the UI    
                browseField.setText(imageObjectList.get(listValue).getFileName());                
                BufferedImage imageToWrite = ImageIO.read(imageObjectList.get(listValue).getFile());                
                WritableImage updatedImage;
                updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);               
                imageSlot.setImage(updatedImage);   
            }  
            else{ 
                    
                System.out.println("else");
                browseField.setText(imageObjectList.get(listValue).getFileName());                
                BufferedImage imageToWrite = ImageIO.read(imageObjectList.get(listValue).getFile());                
                WritableImage updatedImage;
                updatedImage = SwingFXUtils.toFXImage(imageToWrite, null);               
                imageSlot.setImage(updatedImage); 
            }
        }
    } 
        //calls our save function on click.
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
            imageObject imageObject = new imageObject(fileString, file, imageToSave);  
            imageObjectList.add(imageObject);  
            listValue++;
        }           
    }  
}
