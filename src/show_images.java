import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
show_images function:
	read slider accuray
	check match[database] for numbers equal or higher
	display images from db which meet requirements
	[do not display more than max = 250]
 */

public class show_images {
	static int[] results;
	static int x=0;
	static BufferedImage image;
	static JFrame result_frame = new JFrame();
	static JLabel label = new JLabel();
	static JLabel count = new JLabel();
	static int max;
	
	//main function show images
	public static void showImages(int[] similar) throws IOException{
		
		max = similar.length;
		
		for(int a=0;a<similar.length;a++){ //how many images will be shown
			if(similar[a]==0){
				max = a;
				a = similar.length;
			}
		}
		
		
		results = similar;
	    result_frame.setSize(500,500);	
		result_frame.setVisible(true);
		result_frame.setLocation(650,75);
		
		String files = "c:/Database/"+results[0]+".jpg"; //define pointing to similar images
		try { //read and show first image
			System.out.println(files);
			image = ImageIO.read(new File(files));
			label.setIcon( new ImageIcon(image));
			result_frame.getContentPane().add(label);
			result_frame.pack();
			result_frame.revalidate();
			result_frame.repaint();
		} catch (IOException e) { // if unable to read image
		}
		
		JFrame frame_b = new JFrame();
		frame_b.setLocation(800,0);
		frame_b.setSize(350, 75);
		frame_b.setVisible(true);
		
		JPanel panel = new JPanel();
		frame_b.add(panel);
		JButton button2 = new JButton("Next"); 
		count.setText((x+1)+" of "+max);
		JButton button1 = new JButton("Previous");
		panel.add(button1);
		panel.add(count);
		panel.add(button2);
		button1.addActionListener(new Action1());//define action listener for previous image
		button2.addActionListener(new Action2());//define action listener for next image
		result_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame_b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
	
	//action listener for next image result
	static class Action2 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {
			  if(x==max){
				  System.out.println("no more images"); //if past max
			  }
			  else{
				  x++;//showing next image
				  System.out.println(x);
				  count.setText((x+1)+" of "+max); 
				  count.revalidate();
				  count.repaint();
				  show(); //show image
			  }
		  }
	}
	
	//action listener for previous image result
	static class Action1 implements ActionListener {        
		  public void actionPerformed (ActionEvent e) {
			  if(x==0){
				  System.out.println("Thats as far back as you go"); //if back to first image
			  }
			  else{
				  x--; //show previous image
				  System.out.println(x);
				  count.setText((x+1)+" of "+max);
				  count.revalidate();
				  count.repaint();
				  show(); //show image
			  }
		  }
	}

	//image box to show resulting image
	public static void show(){
		 String files = "c:/Database/"+results[x]+".jpg"; //which image to read
		  try { //show image
			System.out.println(files);
			image = ImageIO.read(new File(files)); //read image
			label.setIcon(new ImageIcon(image));
			result_frame.getContentPane().add(label);
			result_frame.pack(); 
			result_frame.revalidate();
			result_frame.repaint();
		} catch (IOException e) { //if cant read image
		}
	}
}