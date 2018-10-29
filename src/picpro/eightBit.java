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
public class eightBit {
    
    private final BufferedImage inputImage;
    static BufferedImage output = new BufferedImage(240, 320, BufferedImage.TYPE_INT_RGB);
        
     public eightBit (BufferedImage passedImage) {     
        //System.out.print("Hello");        
        this.inputImage = passedImage;
        
        // variable to hold th width and height of input image                
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        // variable to would the current pixel number
        int pixel;

        // through dividing gets the size of the the x and y values of the  
        // rectangles of the start image that will be needed to process
        int xSections = width / 8;
        int ySections = height / 10;

        // variable for the coordinates of start image
        int x, y;
        // variable for the coordinates of final image
        int i = 0, j = 0;
        // counters for the while loops that represent the number of pixels in
        // each rectangle of the image and the number of rectangles in the image
        int cnt = 1, cntTwo;
        // multiplies for the start coordinates
        int q = 1, r = 1;
        
        // outer loop will go through the total number of rectangles in the image
        while(cnt <= 48){
            cntTwo = 1;
            x = q * xSections;
            y = r * ySections;

            // inner loop will go throught the total number of pixels in each rectangle    
            while (cntTwo <= 1600) {
                // for every location that falls on the bottom right edge of the right most rectangle, for each line,
                // the x, or width, will be decremented by 239 setting it back to 0 and the y, or height, value will be 
                // incremented setting the next location to the start of the next set of rectangles
                if (i % 239 == 0 && j % 40 == 39 && i != 0) {
                    pixel = inputImage.getRGB(x, y);
                    setFinal(pixel, i, j);
                    x = x - 239;
                    i = i - 239;
                    y++;
                    j++;
                } 
                // for every location that falls on the bottom right edge of a rectangle and still has 
                // another rectangle to the right, the y, or height, value will be decremented by 39 setting it 
                // back to the first pixel on the rectangle's height and then makes x, or height, the start of next rectangle
                else if (i % 40 == 39 && j % 40 == 39) {
                    pixel = inputImage.getRGB(x, y);
                    setFinal(pixel, i, j);
                    i++;
                    x++;
                    j = j - 39;
                    y = y - 39;
                }
                // for every location that falls on the last pixel of each line in a single rectanle
                // the x or width value will be decremented by 39 setting it back to the first pixel 
                else if (i % 40 == 39) {
                    pixel = inputImage.getRGB(x, y);
                    setFinal(pixel, i, j);
                    i = i - 39;
                    x = x - 39;
                    j++;
                    y++;
                } 
                // for every other location will take the pixel color from the input and then
                // call the outputting method passing the pixel color to it, then icrements counters
                else {
                    pixel = inputImage.getRGB(x, y);
                    setFinal(pixel, i, j);
                    i++;
                    x++;
                }
                // increments the inner counter
                cntTwo++;
            }
            // increments the outer counter
            if (cnt % 6 == 0) {
                q = 1;
                r++;
            } 
            //
            else {
                q++;
            }
            //
            cnt++;
        }
     }
    
    // method that sets the new image pixel by pixel
    public static void setFinal(int pixel, int i, int j) {
        output.setRGB(i, j, pixel);
    }

    // method to return the newly made image to main
    public static BufferedImage returnImage(){
        return output;
    }
}
