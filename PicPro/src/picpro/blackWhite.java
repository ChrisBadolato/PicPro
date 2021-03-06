/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.awt.image.BufferedImage;

/**
 *
 * @author ryand
 */
public class blackWhite {
    
    // declaration of the buffered images
    public BufferedImage inputImage;
    static BufferedImage output;
    /**
     * @param passedImage
     */
    public blackWhite (BufferedImage passedImage) {
        // sets the input image to the passed image
        this.inputImage = passedImage;
        
        // variable to hold th width and height of input image                
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        
        // variable to hold the current pixel number
        int pixel;
        
        // variable to hold the red componet of each pixel
        int  red;
        
        // inialization of the output buffered image    
        output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // for loop to go through the input image and output it in black and white
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                // sets the pixel to the current input image pixel
                pixel = inputImage.getRGB(i, j);
                
                // sets the red component to match its corresponding pixel component
                red = (pixel>>16) &0xff;
                
                // sets the output pixel to equal the new color
                pixel = (red<<16) | (red<<8) | red;
                
                // sets the pixel in the output image 
                output.setRGB(i, j, pixel);
            }    
        }          
    }
    
    // method to return the newly made image to main
    public static BufferedImage returnImage(){
        return output;
    }
}
