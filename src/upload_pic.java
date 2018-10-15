import java.io.FileWriter;
import java.io.IOException;

import java.io.File;
import java.nio.file.Files;

/*
upload_pic function:
	save image index data into file C:\database\index.txt and copy image into directory C:\database
	index data should 64 colors followed by image#
	ex: 0 ,20,65,0,0,35 ,0,15, 1
		10,10,35,2,6,0.5,0,0 , 2
	when image is copied to database it should be renamed to the image#
*/

public class upload_pic {
	
	//main function upload pic
	public static void uploadPic(double[] index, String path) throws IOException{

		File yourFile = new File("c:/database/index.txt"); //file with indexed image data
	    if(!yourFile.exists()){ //if file does not exist, create
				yourFile.createNewFile();
	    } 
		int num_images = new File("c:/database/").listFiles().length; //how many images are in database
		FileWriter fw = new FileWriter("c:/database/index.txt", true);
	    for (int i = 0; i < index.length; i++)
	    	fw.write(index[i] + " "); //write image data to file
	    fw.write(num_images+" \n"); //write image number to file and end with new line
	    fw.close();
	    File source = new File(path);
	    File dest = new File("c:/database/"+num_images+".jpg"); //copy image to database with jpg extension
	    Files.copy(source.toPath(), dest.toPath());
	    
	}

}
