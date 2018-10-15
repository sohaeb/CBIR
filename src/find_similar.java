import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Arrays;

/*
 find_similar function:
	using array, read stored indexed file for close %
	example:
	image has array[7] = [0.20, 0.25, .08,    0,   0,   0.40, 0.05, 0.02];
	file has  array[7] = [   0, 0.10, .05, 0.20, 0.10,  0.30,    0, 0.25];
	white has 0.2 and 0 = 0% match (if denom/numer is 0 then match = 0)
	black has 0.25 and 0.10 = .175 match
	Image relevance/match = 0,17.5,6.5,0,0,35,0,13.5 = 72% match of image
	
	formula:
	database = NUM OF IMAGES;
	int match[database];
	int temp=0;
	for(x=0;x<64;x++)
		temp+= i[x]+d[x] / 2;
	match[database]= temp;

	return match[database]

 */
public class find_similar {
	
	public static int[] findSimilar(double[] index_Orig, int accuracy) throws FileNotFoundException, IOException
	{	
		int max_images = new File("c:/database/").listFiles().length; //max = number of files in database
		double[][] results = new double[max_images][2];
		int[] top_results = new int[250]; //max results to return
		int a = 0;
		
		try (BufferedReader br = new BufferedReader(new FileReader("C:/database/index.txt"))) //read indexd image file for comparison
		{
		    String line;
		    while ((line = br.readLine()) != null) //while each image exists
		    {
		    	String delims = " ";
		    	double[] index_compare = new double[65];
		    	String[] tokens = line.split(delims);
		    	for(int x=0;x<65;x++){
		    		index_compare[x] = Double.parseDouble(tokens[x]); //get each color for image from database, save to array
		    	}
		    	int img_no = (int) index_compare[64];
		    	double percentage = 0;
		    	double match;

		    	for(int x=0;x<64;x++) //compare all 64 colors between original and database
		    	{
		    		if((index_Orig[x]==0)||(index_compare[x]==0))
		    			match=0; //ignored if color does not exist
		    		else
		    			match = (index_compare[x]+index_Orig[x])/2; //average if color exists
			    	//System.out.println(x+"= "+match); //testing
			    	percentage += match;
		    	}
		    	//System.out.println("acc="+accuracy); //testing
		    	//System.out.println("percen="+percentage); //testing
		    	
		    	if(percentage >= accuracy) //if requested accuracy is in range
		    	{
		    		results[a][0] = (double) img_no;
		    		results[a][1] = Math.round(percentage*1000) / 1000.0;
		    		//System.out.println("img# = "+results[a][0]+" percentage = "+results[a][1]); //testing
			    	a++;
		    	}
		    }
		    
		    final Comparator<double[]> arrayComparator = new Comparator<double[]>() { //sort images by most accurate
		        public int compare(double[] o1, double[] o2) {
		            return Double.compare(o2[1],o1[1]);
		        }
		    };
		    
		    Arrays.sort(results, arrayComparator);
					    
		    System.out.println(results.length); //testing //how many results got saved
		    
		    for(int x=0; x<max_images; x++){
		    	System.out.println("img# = "+results[x][0]+" percentage = "+results[x][1]+" x="+x);
		    	if(x==500)
		    		x=max_images;
		    }

		    
		    for(int x=0;x<250;x++){
		    	top_results[x] = (int)results[x][0]; //return top 250 images without accuracy
		    }
		    
		}
		
		System.out.println(top_results.length); //testing //how many results are being sent
		return top_results;		
	}

}
