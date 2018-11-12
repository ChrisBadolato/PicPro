package picpro;

import java.awt.image.BufferedImage;



/**
 *
 * @author Ryan DeYoung
 */
public class sepia {
    
    
    public BufferedImage inputImage;
    static BufferedImage output;
        
     public sepia (BufferedImage passedImage) {           
        this.inputImage = passedImage;
        
        // variable to hold th width and height of input image                
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        
        // variable to hold the current pixel number
        int pixel;
        
        // variables to hold the initial and new components of each pixel 
        int  red, green, blue;
        int sepiaRed, sepiaGreen, sepiaBlue;
        
        // declares and initializes the output buffered image
        output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        // loops the image and converts each pixel to its sepia version
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                // sets pixel to equal the number of each pixel from the input image
                pixel = inputImage.getRGB(i, j);
                
                // divides the pixel value into each of the seperate components red, green and blue
                red = (pixel>>16) &0xff;
                green = (pixel>>8) &0xff;
                blue = pixel &0xff;
                
                // using a mathematical formula found online at 
                // https://www.techrepublic.com/blog/how-do-i/how-do-i-convert-images-to-grayscale-and-sepia-tone-using-c/
                // this converts each component to represent its sepia version 
                sepiaRed = (int)(0.393 * red + 0.769 * green + 0.189 * blue);
                sepiaGreen = (int)(0.349 * red + 0.686 * green + 0.168 * blue);
                sepiaBlue = (int)(0.272 * red + 0.534 * green + 0.131 * blue);
                
                // checks to see if each of the new components exceeds the color limit of 255 
                // if it does it sets the value to the color limit
                if (sepiaRed > 255){
                    sepiaRed = 255;
                }
                if (sepiaGreen > 255){
                    sepiaGreen = 255;
                }
                if (sepiaBlue > 255){
                    sepiaBlue = 255;
                }
                
                // resets pixel to equal the new sepia color
                pixel = (sepiaRed<<16) | (sepiaGreen<<8) | sepiaBlue;
                
                // puts new pixel into the output image
                output.setRGB(i, j, pixel);
            }    
        }
    }

    // method to return the newly made image to main
    public static BufferedImage returnImage(){
        return output;
    }
    
}