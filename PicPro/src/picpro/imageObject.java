/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.awt.Image;

/**
 *
 * @author Chris Badolato
 */
public class imageObject {
    Image newImage;
    
        //sets and gets out image.
    public imageObject (Image newImage) {
        this.newImage = newImage;
    }
    public void set(Image newImage){
        this.newImage = newImage;
          
    }  
    public Image getImage(){
        return this.newImage;  
    }
}
