import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class selectiveFilterGlitch {
	public Random random = new Random();
	public BufferedImage finalimage;
	public int fxp1, fxp2,fxp3;
	public ImagePlus imp;
	
	public selectiveFilterGlitch(ImagePlus immp, int fxP1, int fxP2, int fxP3){
		
		this.fxp1=fxP1;
		this.fxp2=fxP2;
		this.fxp3=fxP3;
		this.imp=immp;
		
		ImageProcessor ip = imp.getProcessor();
        ip.setLineWidth(fxP3);
        
        int h = ip.getHeight();
        int w = ip.getWidth();
        int[] rgb= new int[3];
        float[][] hsb = new float[1000000][3];
        int pxlcount =0;
        int[] avgrgb= new int[3];
        float[] avghsb= new float[3];
        
        for (int i=0; i<h;i+=fxP2){
        	for (int j=0; j<w; j+=fxP2){
        		rgb = imp.getPixel(j, i);
        		
        		avgrgb[0] += rgb[0];
        		avgrgb[1] += rgb[1];
        		avgrgb[2] += rgb[2];
        		//System.out.println(Arrays.toString(rgb));
        		
        		Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], hsb[pxlcount]);
        		//System.out.println(Arrays.toString(hsb[pxlcount]));
        		pxlcount++;
        	}
        }
        
        avgrgb[0] = avgrgb[0]/pxlcount;
        avgrgb[1] = avgrgb[1]/pxlcount;
        avgrgb[2] = avgrgb[2]/pxlcount;
        Color.RGBtoHSB(avgrgb[0], avgrgb[1], avgrgb[2], avghsb);
        
        int p = 0;
        int y, x =0;
        for (int i=0; i<h;i+=fxP2){
        	for (int j=0; j<w; j+=fxP2){
        		if (isGood(hsb[p],avghsb)){
        			ip.setColor(Color.getHSBColor(hsb[p][0], hsb[p][1], hsb[p][2]));
            		
            		ip.drawRect(j+random.nextInt(fxP1)-(fxP1/2), i+random.nextInt(fxP1)-(fxP1/2), j+random.nextInt(fxP1)-(fxP1/2), i+random.nextInt(fxP1)-(fxP1/2));

	        	}
        		p++;
        	}
        }
        
        finalimage= ip.getBufferedImage();
	}
	
	private boolean isGood(float[] hsb, float[] avg) {
		float tolerance = (float) 0.1;
		if(hsb[0]<(avg[0]+tolerance) && hsb[0]>(avg[0]-tolerance)){
			return false;
			
		}
		return true;
	}
	
	public BufferedImage returnImage(){
		return finalimage;
	}
}