package rotate;

import java.awt.image.BufferedImage;

/**
 *
 * @author ryand
 */
public class Rotate {

    // declaration of needed buffered images
    public BufferedImage inputImage;
    static BufferedImage output;
        
    public Rotate (BufferedImage passedImage) {     
        this.inputImage = passedImage;
        
        // variable to hold th width and height of input image                
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        
        // variable to hold the current pixel number
        int pixel;

        // variables that will allow program to invert the image without going out of bounds
        int subWidth = width - 1;
        int subHeight = height - 1;
        
        // declaration and initializarion of invert buffered image to be used as intermediary image
        BufferedImage invert = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        
        // setting up the paraments for the output buffered image
        output = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        
        int count = 0;
        // switch statement to that takes in the number of times the rotate button has
        // been pressed and does the correct rotation for it
        switch(count){
            // the first time rotate is pressed the image will rotate left
            case 1:
                for(int i = 0; i < height; i++){
                    for(int j = 0; j < width; j++){
                        pixel = inputImage.getRGB(j, i);
                        output.setRGB(i, j, pixel);
                    }
                }
                break;
            
            // the second time rotate is pressed the image will invert and rotate left    
            case 2:
                for(int i = 0; i < width; i++){
                    for(int j = 0; j < height; j++){
                        pixel = inputImage.getRGB(i, j);
                        invert.setRGB(subWidth, subHeight, pixel);
                        subHeight--;
                    }
                    subHeight = height - 1;
                    subWidth--;
                }
                
                for(int i = 0; i < height; i++){
                    for(int j = 0; j < width; j++){
                        pixel = invert.getRGB(j, i);
                        output.setRGB(i, j, pixel);
                    }
                }
                break;
                
            // the third time rotate is pressed the image will rotate left    
            case 3:
                for(int i = 0; i < height; i++){
                    for(int j = 0; j < width; j++){
                        pixel = inputImage.getRGB(j, i);
                        output.setRGB(i, j, pixel);
                    }
                }
                break;
                
            // the fourth time rotate is pressed the image will invert and rotate left     
            case 4:
                for(int i = 0; i < width; i++){
                    for(int j = 0; j < height; j++){
                        pixel = inputImage.getRGB(i, j);
                        invert.setRGB(subWidth, subHeight, pixel);
                        subHeight--;
                    }
                    subHeight = height - 1;
                    subWidth--;
                }
                
                for(int i = 0; i < height; i++){
                    for(int j = 0; j < width; j++){
                        pixel = invert.getRGB(j, i);
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
