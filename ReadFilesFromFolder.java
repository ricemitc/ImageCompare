import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class ReadFilesFromFolder {



	public String location = null;
	public ArrayList<String> fileList = new ArrayList<String>();
	public ArrayList<byte[]> pictureArrays = new ArrayList<byte[]>();
	public ArrayList<String> fileLocations = new ArrayList<String>();
	public ArrayList<Dimension> fileSizes = new ArrayList<Dimension>();

	public static File folder;// = new File(location);
	static String tempFileName = "";
	String nameLowercase;

	public ReadFilesFromFolder() {
		
	}

	public ReadFilesFromFolder(String filePath) {
		location = filePath;
	}
	
	
	public ArrayList<String> getImageArray() {
		return fileLocations;
	}
	
	public ArrayList<String> getFileList() {
		return fileList;
	}
	
	public ArrayList<Dimension> getDimensionArray() {
		return fileSizes;
	}
	
	
	public void getImages(String filePath) {
		
		final File folder = new File(filePath);
		File imageFile;
		BufferedImage imageToAdd = null;
		
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				System.out.println("Reading files under the folder "+fileEntry.getAbsolutePath());
				getImages(fileEntry.getAbsolutePath());
			} else {
				if (fileEntry.isFile()) {
					
					// File name:
					tempFileName = fileEntry.getName();
					nameLowercase = tempFileName.toLowerCase();

					// Check for incompatible files
					if ((!nameLowercase.contains("jpg")
							&& !nameLowercase.contains("png")
							&& !nameLowercase.contains("gif") && !nameLowercase
								.contains("jpeg"))
							|| nameLowercase.contains("shortcut") || nameLowercase.substring(0,1).equalsIgnoreCase(".")) {
						continue;
					}
					
					else {
						// Get a path to the image
						String fullPathName = filePath + "/" + tempFileName;
						imageFile = new File(fullPathName);
						try {
							imageToAdd = ImageIO.read(imageFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						fileSizes.add(new Dimension(imageToAdd.getWidth(), imageToAdd.getHeight()));
						fileLocations.add(fullPathName);
						fileList.add(tempFileName);
					}
				}

			}
		}
	
		/*
		System.out.println("Printing file list of size: "+fileList.size());
		for(int m=0; m<fileList.size(); m++) {
			System.out.println(fileList.get(m));
		}
		*/
	}
	
	
	
	
	
	
	
	
	
	/*
	public ArrayList<String> listFilesForFolder(String filePath) {

		final File folder = new File(filePath);
		
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				//System.out.println("Reading files under the folder "+folder.getAbsolutePath());
				//listFilesForFolder(fileEntry.getAbsolutePath());
			} else {
				if (fileEntry.isFile()) {
					//System.out.println("fileEntry is file.");
					temp = fileEntry.getName();
					//System.out.println(temp);
					
					//Add the file name to the list
					fileList.add(temp);
					
				}

			}
		}
		return fileList;
	}
	
	*/
	
	
	
	
	/*
	public ArrayList<byte[]> getBytesFromFolder(String filePath) {

		byte[] tempBytes = null;
		final File folder = new File(filePath);
		
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				// System.out.println("Reading files under the folder "+folder.getAbsolutePath());
				listFilesForFolder(fileEntry.getAbsolutePath());
			} else {
				if (fileEntry.isFile()) {
					//System.out.println("fileEntry is file.");
					temp = fileEntry.getName();
					
					//Printing file name
					//System.out.println(temp);
					
					
					//Get a path to the image
					String fullPathName = filePath+ "/" + temp;
					Path path = Paths.get(fullPathName);
					
					System.out.println(fullPathName);
					
					//Read the bytes from the picture
					try {
						//tempBytes = Files.readAllBytes(path);
						tempBytes = Files.readAllBytes(Paths.get(fullPathName));
					} catch (IOException e) {
						e.printStackTrace();
					}
					

					
					System.out.println("Test size: " + tempBytes.length);
					
					//Add the file name to the list
					pictureArrays.add(tempBytes);
					
				}

			}
		}
		return pictureArrays;
	} */


}