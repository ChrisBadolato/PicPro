/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Chris Badolato
 */
public class FXMLDocumentController implements Initializable {
    
    ObservableList<String> choiceBoxList = FXCollections.observableArrayList("ProcessImage", "Process 2", "Process 3");
    
    private Label label;
    
    @FXML
    private Button selectButton;
    @FXML
    private Button quitButton;
    @FXML
    private ChoiceBox choiceBox;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        choiceBox.setItems(choiceBoxList);
        choiceBox.setValue("ProcessImage");
        
    }    

     
    @FXML
    private void clickSelectButton(MouseEvent event) throws IOException {
        //When select button is clicked we want to open the corresponding imageprocessing app
        String boxChoice;
        boxChoice = (String) choiceBox.getValue();
        System.out.println(boxChoice);
        
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(boxChoice+".fxml"));
        loader.load();
        
        // Retrieve root of loader to get reference to the Scene and Stage
        Parent nextMenuParent = loader.getRoot();
        Scene nextMenuScene = new Scene(nextMenuParent);
        Stage nextMenuStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        nextMenuStage.setScene(nextMenuScene);
        nextMenuStage.show();
 
    }
        //Closes our window. We can use this button event on each controller.
    @FXML
    private void clickCloseButton(MouseEvent event) {
        
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
        
    }
}
