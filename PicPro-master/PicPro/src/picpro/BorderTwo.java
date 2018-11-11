/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.awt.image.BufferedImage;

public class BorderTwo{
    
   
        // TODO code application logic here
        public BufferedImage inputImage = null;
        static BufferedImage outputImage;

        public BorderTwo(BufferedImage passedImage){

        this.inputImage = passedImage;

        int width = inputImage.getWidth();
        int height = inputImage.getHeight();

        outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int a;
        int r;
        int g;
        int b;
        /*
        for(int x = 0; x < (width*4/5); x++)
        {
            for(int y = 0; y < height; y++)
            {
                int p = inputImage.getRGB(x,y);


                a = (p>>24) & 0xff;
                r = (p>>16) & 0xff;
                g = (p>>8) & 0xff;
                b = p & 0xff;

                r = r + 255;
                g = g + 255;
                b = b + 255;

                int pOut = (a<<24) | (r<<16) | (g<<8) | b;

                outputImage.setRGB(x, y, pOut);
            }
        }
        */


        for( int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                boolean one = x > 25 && x < width - 25 && y > 25 && y < height - 25;
                //boolean two = x > 50 && x < width - 50 && y > 50 && y < height - 50;
                if (one) {
                    int p = inputImage.getRGB(x, y);

                    a = (p >> 24) & 0xff;
                    r = (p >> 16) & 0xff;
                    g = (p >> 8) & 0xff;
                    b = p & 0xff;


                    int pOut = (a << 24) | (r << 16) | (g << 8) | b;

                    outputImage.setRGB(x, y, pOut);
                } else {
                    int p = inputImage.getRGB(x, y);
                    a = 0;
                    r = 0;
                    g = 0;
                    b = 255;
                    int pOut = (a << 24) | (r << 16) | (g << 8) | b;
                    outputImage.setRGB(x, y, pOut);

                }
            }
        }


        }
        public static BufferedImage returnImage()
        {
            return outputImage;
        }

}
