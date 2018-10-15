import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.lang.ArrayUtils;

import java.io.File;
import java.awt.image.BufferedImage;

/*
index_picture function:
	read pixel by pixel in for loop
	make pixel 6bit
	if else for 64 colors
	if pixel falls in one of the categories +1 to that
	continue until done reading picture
	
	insert % of pixel into each index for picture to be referred to
	ex: image size = 756x504 = 381024 pixels if 8000 white pixels insert .021 for white

	computer each color % white/max pixels etc and store into array and return
 */

public class index_picture {

	//index picture main function
	static double[] indexPicture(String path){
		System.out.println(path);
		BufferedImage img = null;
        double array[] = new double[64];
        
	        try
	        {
	            img = ImageIO.read(new File(path)); //get path of image to start
	        
	        int width = img.getWidth();
	        int height = img.getHeight();
	        int size = width*height;
	        System.out.println(width+" "+height+" "+size); //test output of file width height and size
	        int[] rgb;
	        
	        for(int i = 0; i < height; i++){    //Read pixel by pixel
	            for(int j = 0; j < width; j++){
	                rgb = getPixelData(img, j, i);     //impelmentation of function below
	                
	                 if (ArrayUtils.isEquals(rgb, new int[] {0, 0, 0})) //if else statement for 64 colors
	                     array[0]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 0, 0}))
	                     array[1]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 0, 0}))
	                	 array[2]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 0, 0}))
	                	 array[3]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 1, 0}))
	                	 array[4]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 1, 0}))
	                	 array[5]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 1, 0}))
	                	 array[6]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 1, 0}))
	                	 array[7]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 2, 0}))
	                     array[8]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 2, 0}))
	                     array[9]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 2, 0}))
	                	 array[10]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 2, 0}))
	                	 array[11]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 3, 0}))
	                	 array[12]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 3, 0}))
	                	 array[13]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 3, 0}))
	                	 array[14]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 3, 0}))
	                	 array[15]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 0, 1}))
	                     array[16]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 0, 1}))
	                     array[17]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 0, 1}))
	                	 array[18]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 0, 1}))
	                	 array[19]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 1, 1}))
	                	 array[20]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 1, 1}))
	                	 array[21]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 1, 1}))
	                	 array[22]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 1, 1}))
	                	 array[23]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 2, 1}))
	                     array[24]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 2, 1}))
	                     array[25]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 2, 1}))
	                	 array[26]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 2, 1}))
	                	 array[27]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 3, 1}))
	                	 array[28]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 3, 1}))
	                	 array[29]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 3, 1}))
	                	 array[30]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 3, 1}))
	                	 array[31]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 0, 2}))
	                     array[32]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 0, 2}))
	                     array[33]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 0, 2}))
	                	 array[34]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 0, 2}))
	                	 array[35]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 1, 2}))
	                	 array[36]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 1, 2}))
	                	 array[37]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 1, 2}))
	                	 array[38]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 1, 2}))
	                	 array[39]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 2, 2}))
	                     array[40]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 2, 2}))
	                     array[41]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 2, 2}))
	                	 array[42]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 2, 2}))
	                	 array[43]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 3, 2}))
	                	 array[44]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 3, 2}))
	                	 array[45]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 3, 2}))
	                	 array[46]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 3, 2}))
	                	 array[47]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 0, 3}))
	                     array[48]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 0, 3}))
	                     array[49]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 0, 3}))
	                	 array[50]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 0, 3}))
	                	 array[51]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 1, 3}))
	                	 array[52]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 1, 3}))
	                	 array[53]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 1, 3}))
	                	 array[54]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 1, 3}))
	                	 array[55]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 2, 3}))
	                     array[56]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 2, 3}))
	                     array[57]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 2, 3}))
	                	 array[58]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 2, 3}))
	                	 array[59]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {0, 3, 3}))
	                	 array[60]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {1, 3, 3}))
	                	 array[61]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {2, 3, 3}))
	                	 array[62]++;
	                 else if (ArrayUtils.isEquals(rgb, new int[] {3, 3, 3}))
	                	 array[63]++;
	            }
	        }
	        
	        for(int x=0;x<64;x++)
	        	array[x] = Math.round(array[x]/size * 100000) / 1000.0; //get percentage of pixel in 3 decimals
	        }
	        catch (IOException e)
	        {
	        	System.out.println("invalid path"); //if path not found
	        }
	        
	        return array; //return array with all 64 color pixels
	}
	    
//breaking image to 6bit
	private static int[] getPixelData(BufferedImage img, int x, int y)
	{
		int argb = img.getRGB(x, y);
	
		int rgb[] = new int[] { //RGB divided by 85 to become 6bit image
		    ((argb >> 16) & 0xff)/85, //red 
		    ((argb >>  8) & 0xff)/85, //green
		    ((argb      ) & 0xff)/85  //blue
		};
	
		//System.out.println("rgb: " + rgb[0] + " " + rgb[1] + " " + rgb[2]); //just for testing
		return rgb; //returning rgb as 6bit
	}
}
