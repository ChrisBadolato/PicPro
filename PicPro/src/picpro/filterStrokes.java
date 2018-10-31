import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class filterStrokes {
	
	public Random random = new Random();
	public BufferedImage finalimage;
	
	public filterStrokes(BufferedImage image, int fxP1, int fxP2, int fxP3){
		ImagePlus imp = new ImagePlus("",image);
		
		ImageProcessor ip = imp.getProcessor();
        ip.setLineWidth(fxP3);
        
        int h = ip.getHeight();
        int w = ip.getWidth();
        int[] rgb= new int[3];
        float[][] hsb = new float[1000000][3];
        int pxlcount =0;
        
        for (int i=0; i<h;i+=fxP2){
        	for (int j=0; j<w; j+=fxP2){
        		rgb = imp.getPixel(j, i);
        		System.out.println(Arrays.toString(rgb));
        		
        		Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], hsb[pxlcount]);
        		System.out.println(Arrays.toString(hsb[pxlcount]));
        		pxlcount++;
        	}
        }
        
        int p = 0;
        int y, x =0;
        for (int i=0; i<h;i+=fxP2){
        	for (int j=0; j<w; j+=fxP2){
        		ip.setColor(Color.getHSBColor(hsb[p][0], hsb[p][1], hsb[p][2]));
        		x= j+random.nextInt(fxP1) - (fxP1/2);
        		y= i+random.nextInt(fxP1) - (fxP1/2);
        		ip.drawLine(j, i, x, y);
        		p++;
        	}
        }
        
        finalimage = ip.getBufferedImage();
	}

	public BufferedImage returnImage(){
		return finalimage;
	}

}
