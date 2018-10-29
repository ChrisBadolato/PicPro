/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.awt.image.BufferedImage;



/**
 *
 * @author Chris Badolato
 */
public class sepia {
    
    
    public BufferedImage inputImage;
    static BufferedImage output;
        
     public sepia (BufferedImage passedImage) {     
        //System.out.print("Hello");        
        this.inputImage = passedImage;
        
        // variable to hold th width and height of input image                
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        
        // variable to hold the current pixel number
        int pixel;

       int  red, green, blue;
        int sepiaRed, sepiaGreen, sepiaBlue;
            
        output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                pixel = inputImage.getRGB(i, j);
                
                red = (pixel>>16) &0xff;
                green = (pixel>>8) &0xff;
                blue = pixel &0xff;
                
                sepiaRed = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                sepiaGreen = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                sepiaBlue = (int)(0.272 * red + 0.534 * green + 0.131 * blue);
                
                if (sepiaRed > 255){
                    red= 255;
                }
                else{
                    red = sepiaRed;
                }
                
                if (sepiaGreen > 255){
                    green = 255;
                }
                else{
                    green = sepiaGreen;
                }
                
                if (sepiaBlue > 255){
                    blue = 255;
                }
                else{
                    blue = sepiaBlue;
                }
                
                pixel = (red<<16) | (green<<8) | blue;
                
                output.setRGB(i, j, pixel);
            }    
        }
    }

    // method to return the newly made image to main
    public static BufferedImage returnImage(){
        return output;
    }
    
}