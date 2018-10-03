
package picpro;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * @author 
 *          Chris Badolato
 *          Frank Volk
 *          Ryan Deyoung
 *          Triston Hernandez
 */
public class imageObject {
    
    //BufferedImage newImage;
    String fileName;
    File sourceFile;
    BufferedImage newImage;
        //sets and gets out image.
    imageObject (String fileName, File file, BufferedImage newImage) {
        
        this.fileName = fileName;
        this.sourceFile = file;
        this.newImage = newImage;
    }                      
        //setters and getters for our image and file objects.
        //Will potentially make objects for each image to store on a list.
    public void set(BufferedImage newImage){
       // this.newImage = newImage;         
    }  
   
    public void setFile(File file){
        this.sourceFile = file;
    }
    public File getFile(){
        return this.sourceFile;
    }
    
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    
    public String getFileName(){
        return this.fileName;
    }
    public void setImage(BufferedImage newImage){
        this.newImage = newImage;
    }
    public BufferedImage getImage(){
        return this.newImage;
    }
    
}
