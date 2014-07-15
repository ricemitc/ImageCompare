import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class Run {

	
	public static void main(String[] args) {
		
		final long startTime = System.currentTimeMillis();
		Scanner scan = new Scanner(System.in);
		int numDuplicates = 0;
		
		String filePath;
		ArrayList<String> fileNames = new ArrayList<String>();
		ArrayList<String> fileLocations = new ArrayList<String>();
		ArrayList<Dimension> fileDimensions = new ArrayList<Dimension>();
		
		//Get the file path (Already hardcoded in for testing)
		System.out.println("Please enter the exact file path for the pictures: ");
		//filePath = scan.nextLine();
		filePath = "C:/Users/Mitch Desktop/Desktop/Test";
		
		
		
		
		File folder = new File(filePath);
		ReadFilesFromFolder reader = new ReadFilesFromFolder();
		System.out.println("Reading files under the folder "+ folder.getAbsolutePath());
		
		
		
		//Fill the Name array and BufferedImage array
		reader.getImages(filePath);
		fileNames = reader.getFileList();
		fileLocations = reader.getImageArray();
		fileDimensions = reader.getDimensionArray();
		
		
		//Setting up variables to compare 2 images
		BufferedImage originalImage = null;
		BufferedImage compareToImage = null;
		boolean isSame = true;
		
		
		
		System.out.println("Images loaded. Begin comparing images.");

		
		//Loop through the array of images
		for(int i=0; i<fileLocations.size(); i++) {
			//System.out.println("Comparing: "+i+" out of: "+fileLocations.size());
			//Assign the original image to the current position
			try {
				originalImage = ImageIO.read(new File(fileLocations.get(i)));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			//Begin looping through the rest of the images and comparing each
			for(int j=i+1; j<fileLocations.size(); j++) {

				/*
				//Skip if comparing the same location
				if(fileLocations.get(i).equalsIgnoreCase(fileLocations.get(j))) {
					continue;
				}
				*/
						
				//If images aren't the same size, skip to next image. Already know they are different images
				if((fileDimensions.get(i).getHeight() != fileDimensions.get(j).getHeight()) || (fileDimensions.get(i).getWidth() != fileDimensions.get(j).getWidth())) {
					continue;
				}
				
			   //Else, they are the same size so begin comparing pixel by pixel
				else {

					try {
						compareToImage = ImageIO.read(new File(fileLocations.get(j)));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					//Assume the pixels in the images are the same
					isSame = true;
					
					//Loop through all pixels in both images and compare them.
					for(int m=0; m<originalImage.getHeight(); m++) {
						for(int n=0; n<originalImage.getWidth(); n++) {
							
							//If we find a pixel that differs between them, we know they aren't the same picture. Break loop.
							if(compareToImage.getRGB(n, m) != originalImage.getRGB(n,  m)) {
								isSame = false;
								break;
							}
							
							
						}
						
						//If after comparing each pixel isSame is still true after 10 rows of pixels, they are assumed to be the same image. Break the loop
						if(isSame == true && m > 9) {
							numDuplicates++;
							System.out.println("Image: "+ fileNames.get(i) +" and: " + fileNames.get(j) + " ARE THE SAME IMAGE.");
							System.out.println("Locations for each: "+fileLocations.get(i) + " and: "+ fileLocations.get(j));
							break;
						}
					}
				}
				
			}
		}
		
		

		
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (double)(endTime - startTime)/1000 + " seconds for: " + fileNames.size() + " images.");
		System.out.println("Number of duplicates: "+numDuplicates);
	} //End main

} //End class
