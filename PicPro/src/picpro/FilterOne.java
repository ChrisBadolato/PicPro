/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;



/**
 *
 * @author Chris Badolato
 */
public class FilterOne {
    
    
    private final BufferedImage inputImage;
    static BufferedImage output = new BufferedImage(240, 320, BufferedImage.TYPE_INT_RGB);
        
     public FilterOne (BufferedImage passedImage) {
        
         System.out.print("Hello");
         
        this.inputImage = passedImage;
        
                        
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        int pixel;

        int xSections = width / 8;
        int ySections = height / 10;

        // coordinates for start image
        int x, y;
        // coordinates for final image
        int i = 0, j = 0;
        // counters for the while loops
        int cnt = 1, cntTwo;
        // multiplies for the start coordinates
        int q = 1, r = 1;
        
        while(cnt <= 48){
            cntTwo = 1;
            x = q * xSections;
            y = r * ySections;

            while (cntTwo <= 1600) {
                if (i % 239 == 0 && j % 40 == 39 && i != 0) {
                    pixel = inputImage.getRGB(x, y);
                    setFinal(pixel, i, j);
                    x = x - 239;
                    i = i - 239;
                    y++;
                    j++;
                } else if (i % 40 == 39 && j % 40 == 39) {
                    pixel = inputImage.getRGB(x, y);
                    setFinal(pixel, i, j);
                    i++;
                    x++;
                    j = j - 39;
                    y = y - 39;
                } else if (i % 40 == 39) {
                    pixel = inputImage.getRGB(x, y);
                    setFinal(pixel, i, j);
                    i = i - 39;
                    x = x - 39;
                    j++;
                    y++;
                } else {
                    pixel = inputImage.getRGB(x, y);
                    setFinal(pixel, i, j);
                    i++;
                    x++;
                }
                cntTwo++;
            }
            if (cnt % 6 == 0) {
                q = 1;
                r++;
            } else {
                q++;
            }
            cnt++;
        }
     }
    
    public static void setFinal(int pixel, int i, int j) {
        
        output.setRGB(i, j, pixel);
    }
    public static BufferedImage returnImage(){
        return output;
    }
    
}
