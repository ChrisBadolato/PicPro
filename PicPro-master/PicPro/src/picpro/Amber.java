/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.awt.image.BufferedImage;


public class Amber {
        public BufferedImage inputImage = null;
    static BufferedImage outputImage;

        public Amber(BufferedImage passedImage){


            this.inputImage = passedImage;

            int width = inputImage.getWidth();
            int height = inputImage.getHeight();

            outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            int a;
            int r;
            int g;
            int b;

            for(int x = 0; x < width; x++)
            {
                for(int y = 0; y < height; y++)
                {
                    int p = inputImage.getRGB(x,y);


                    a = (p>>24) & 0xff;
                    r = (p>>16) & 0xff;
                    g = (p>>8) & 0xff;
                    b = p & 0xff;

                    //b = 255;
                    if(r < 150) {
                        r = r + 85;
                    }

                    int pOut = (a<<24) | (r<<16) | (g<<8) | b;

                    outputImage.setRGB(x, y, pOut);
                }
            }
        }
        public static BufferedImage returnImage()
        {
            return outputImage;
        }


    
}