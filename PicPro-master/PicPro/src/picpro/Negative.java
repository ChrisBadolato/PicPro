/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picpro;

import java.awt.image.BufferedImage;


public class Negative {
       public BufferedImage inputImage = null;
        static BufferedImage outputImage;
        
        public Negative(BufferedImage passedImage) {

            this.inputImage = passedImage;
            int width = inputImage.getWidth();
            int height = inputImage.getHeight();

            if (width == 0 || height == 0) {
                System.out.println("---No image---");
            }

            outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            int a;
            int r;
            int g;
            int b;

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int p = inputImage.getRGB(x, y);

                    a = (p >> 24) & 0xff;
                    r = (p >> 16) & 0xff;
                    g = (p >> 8) & 0xff;
                    b = p & 0xff;

                    a = 255 - a;
                    r = 255 - r;
                    g = 255 - g;
                    b = 255 - b;

                    int pOut = (a << 24) | (r << 16) | (g << 8) | b;

                    outputImage.setRGB(x, y, pOut);
                }
            }
        }
        public static BufferedImage returnImage()
        {
            return outputImage;
        }

}
