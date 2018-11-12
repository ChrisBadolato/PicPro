

//This is the skeleton on the project.
//we can access each user interface from here.



package picpro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {
        //creates a list for our choicebox.
    ObservableList<String> choiceBoxList = FXCollections.observableArrayList("ProcessImage", "Franks Filters", "Tristons Filters");
        //Button controllers.
    @FXML
    private Button selectButton;
    @FXML
    private Button quitButton;
    @FXML
    private ChoiceBox choiceBox;
         //Initialize the FXML funtions.
    @Override
    public void initialize(URL url, ResourceBundle rb) {            
        choiceBox.setItems(choiceBoxList);
        choiceBox.setValue("ProcessImage");
    }    
        
    @FXML
    private void clickSelectButton(MouseEvent event) throws IOException {
            //When select button is clicked we want to open the corresponding imageprocessing app
            //The Choicebox corrisponds to the FXML to be opened.
        String boxChoice;
        boxChoice = (String) choiceBox.getValue();
        System.out.println(boxChoice);
            // Retrieve root of loader to get reference to the Scene and Stage
            //Based on our choice box string.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(boxChoice+".fxml"));
        loader.load();            
            //loads fxml
        Parent nextMenuParent = loader.getRoot();
            //set the scene and stage from that loader.
        Scene nextMenuScene = new Scene(nextMenuParent);
        Stage nextMenuStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        nextMenuStage.setScene(nextMenuScene);
            //show the selected menu
        nextMenuStage.show();
 
    }
        //Closes our window. We can use this button event on each controller.
    @FXML
    private void clickCloseButton(MouseEvent event) {     
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();   
    }
}
