/*
create GUI:

options with 3 buttons- 1. choose file
						2. upload picture to database (store)
						3. upload pic to find similar (temp)
						
slider for accuracy to find similar
box to drop image

upload pic to database:
	when pic is chosen run "index_picture"
	then run "upload_pic"

index to find similar:
	when pic is chosen run "index_picture"
	then run "find_similar"
	then run 'show_images"
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import java.io.IOException;
import java.lang.String;

@SuppressWarnings("serial")
public class gui extends JFrame{
	static File files[]; //files to upload or search
	static JSlider slider;
	static JFrame frame = new JFrame("cbir");
	
/*
 * Main frame GUI with slider buttons and drop box for files 
 */
	public static void main(String[] args) {
		  frame.setVisible(true);
		  frame.setSize(500,150);
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
		  JPanel panel = new JPanel();
		  frame.add(panel);
		  
		  JButton button1 = new JButton("Choose File");
		  panel.add(button1);
		  button1.addActionListener (new Action1()); //action listener 1, choose file to browse
		  
		  JButton button2 = new JButton("Upload to Database");
		  panel.add(button2);
		  button2.addActionListener (new Action2()); // action listener 2, upload to database

		  JButton button3 = new JButton("Find Similar Images");
		  panel.add(button3);
		  button3.addActionListener (new Action3()); // action listener 3, find similar images
		  
		  slider = new JSlider(0, 100, 50); //slider for accuracy
		  slider.setValue(80);
		  panel.add(slider);
		  slider.setBorder(BorderFactory.createTitledBorder("Slider for accuracy of results"));
		  slider.setMinorTickSpacing(5);
		  slider.setMajorTickSpacing(25);
		  slider.setPaintTicks(true);
		  slider.setSnapToTicks(true);
		  slider.setPaintLabels(true);
		  
		  JFrame drop_frame = new JFrame(); // frame to drop images
		  JPanel  drop_Panel = new JPanel();
		  JLabel drop_label = new JLabel("DROP IMAGE(S) HERE!!");
		  drop_frame.add(drop_Panel);
		  drop_Panel.add(drop_label);
		  drop_frame.setVisible(true);
		  drop_frame.setSize(500, 150);
		  drop_frame.setLocation(0, 150);
		  
		  new  FileDrop( drop_Panel, new FileDrop.Listener() //listener for dropped files
		  {   public void  filesDropped( java.io.File[] file )
		      {   
			  	files = file; //get all information of chosen file(s)
				 if(files.length==1){ //if only 1 image chosen display image
					 BufferedImage image;
				try{
					image = ImageIO.read(files[0]);
				    JLabel label = new JLabel(new ImageIcon(image));
				    JFrame f = new JFrame();
				    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				    f.getContentPane().add(label);
				    f.pack();
				    f.setLocation(0,150);
				    f.setVisible(true);
			
				} catch (IOException e1){
					
					}
		      }
		     }
		  });// end fileDrop
		  
		}
	
		//Action listener for choose file
		static class Action1 implements ActionListener {
		  public void actionPerformed (ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(true);
			chooser.setCurrentDirectory(new File("c:/"));
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
			      "JPG & GIF Images", "jpg", "gif"); //extensions which can be chosen
			  chooser.setFileFilter(filter);
			  int returnVal = chooser.showOpenDialog(null);
			  if(returnVal == JFileChooser.APPROVE_OPTION) {
				 files = chooser.getSelectedFiles(); //get all information of chosen file(s)
				 if(files.length==1){ //if only 1 image chosen display image
					 BufferedImage image; 
					try{
						image = ImageIO.read(files[0]);
					    JLabel label = new JLabel(new ImageIcon(image));
					    JFrame f = new JFrame();
				        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				        f.getContentPane().add(label);
				        f.pack();
				        f.setLocation(0,150);
				        f.setVisible(true);
					} catch (IOException e1){
					}
				 }	 
			  }
		  }
		}
	
		//Action listener for upload file
		static class Action2 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
		    JFrame frame2 = new JFrame("upload");
		    frame2.setVisible(true);
		    frame2.setSize(250,75);
		    frame2.setLocation(100, 100);
		    JLabel label = new JLabel();
		    frame2.add(label);
		    File database = new File("c:/Database"); //directory of database to be uploaded
		    if (!database.exists())
		    	database.mkdir(); //if directory does not exist CREATE
		    	    	
		    
			  for (int i = 0; i < files.length; i++) // loop for all images
			  {
				  label.setText((i+1)+" of "+files.length+" uploaded"); //state how many done and how many left
				  frame2.revalidate();
				  frame2.repaint();
			      String path = files[i].getPath();
			      double[] index = index_picture.indexPicture(path);//index picture
			      try{
			    	  upload_pic.uploadPic(index, path); //upload indexed image
					}
				    catch (IOException e1){
					    label.setText("error"); //if error uploaded image
					    frame2.add(label);
				    }
			  }     
		  }
		}
		
		//Action listener for find similar images
		static class Action3 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {     
		  	JFrame frame3 = new JFrame("find");
		    frame3.setSize(150,150);
		    JLabel label = new JLabel();
		    frame3.add(label);
		    int accuracy = slider.getValue(); //get value of slider
		    double[] index;
		    	  
			  
		    if(files.length==1){ //find similar images ONLY IF 1 image is selected
		    	index = index_picture.indexPicture(files[0].getPath()); //index image
			    try
			    {
					int[] similar = find_similar.findSimilar(index, accuracy); //run find similar to search for similar images
				    show_images.showImages(similar);
				}
			    catch (IOException e1)
			    {
					label.setText("error"); //if error searching for images
					frame3.setVisible(true);
				}
		    }
		  }
		}
		
}



