
package picpro;

import java.awt.Image;

/**
 *
 * @author 
 *          Chris Badolato
 *          Frank Volk
 *          Ryan Deyoung
 *          Triston Hernandez
 */
public class imageObject {
    
    Image newImage;
    String fileName;
        //sets and gets out image.
    public imageObject (Image newImage) {
        this.newImage = newImage;
    }

    imageObject(String fileName) {
        this.fileName = fileName;
    }
        //setters and getters for our image and file objects.
        //Will potentially make objects for each image to store on a list.
    public void set(Image newImage){
        this.newImage = newImage;         
    }  
    
    public Image getImage(){
        return this.newImage;  
    }
    
    public void setFile(String fileName){
        this.fileName = fileName;
    }
    
    public String getFileName(){
        return this.fileName;
    }
}
