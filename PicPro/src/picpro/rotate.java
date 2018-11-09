/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.awt.image.BufferedImage;
/**
 *
 * @author Ryan DeYoung
 */
public class rotate {
     // declaration of needed buffered images
    public BufferedImage inputImage;
    static BufferedImage output;
    static BufferedImage holding;
        
    public rotate (BufferedImage passedImage, int count) {     
        this.inputImage = passedImage;
        
        // variable to hold th width and height of input image                
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        
        // variable to hold the current pixel number
        int pixel;

        // variables that will allow program to invert the image without going out of bounds
        int subWidth = width - 1;
        int subHeight = height - 1;
        
        // switch statement to that takes in the number of times the rotate button has
        // been pressed and does the correct rotation for it
        switch(count){
            // the first time rotate is pressed the image will rotate left
            case 1:
                // setting up the paraments for the output buffered image for this case
                output = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
                for(int i = 0; i < height; i++){
                    for(int j = 0; j < width; j++){
                        pixel = inputImage.getRGB(j, i);
                        output.setRGB(i, j, pixel);
                    }
                }
                break;
            
            // the second time rotate is pressed the image will invert and rotate left    
            case 2:
                // setting up the paraments for the output buffered image for this case
                output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                for(int i = 0; i < width; i++){
                    for(int j = 0; j < height; j++){
                        pixel = inputImage.getRGB(i, j);
                        output.setRGB(subWidth, subHeight, pixel);
                        subHeight--;
                    }
                    subHeight = height - 1;
                    subWidth--;
                }
                break;
                
            // the third time rotate is pressed the image will rotate left    
            case 3:
                // declaration and initializarion of invert buffered image to be used as intermediary image
                holding = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
                for(int i = 0; i < height; i++){
                    for(int j = 0; j < width; j++){
                        pixel = inputImage.getRGB(j, i);
                        holding.setRGB(i, j, pixel);
                    }
                }
                // resetting the width and height related variable to equal the holding image
                width = holding.getWidth();
                height = holding.getHeight();
                subWidth = width - 1;
                subHeight = height - 1;
                // setting up the paraments for the output buffered image for this case
                output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                for(int i = 0; i < width; i++){
                    for(int j = 0; j < height; j++){
                        pixel = holding.getRGB(i, j);
                        output.setRGB(subWidth, subHeight, pixel);
                        subHeight--;
                    }
                    subHeight = height - 1;
                    subWidth--;
                }
                break;
                
            // the fourth time rotate is pressed the image will invert and rotate left     
            case 4:
                // setting up the paraments for the output buffered image for this case
                output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                for(int i = 0; i < width; i++){
                    for(int j = 0; j < height; j++){
                        pixel = inputImage.getRGB(i, j);
                        output.setRGB(i, j, pixel);
                    }
                }
                break;
            default:
                break;
        }
     }

    // method to return the newly made image to main
    public static BufferedImage returnImage(){
        return output;
    }
}
