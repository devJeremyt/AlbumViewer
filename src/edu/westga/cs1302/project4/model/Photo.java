package edu.westga.cs1302.project4.model;

import javafx.scene.image.Image;

public class Photo extends Image {
	private String filePath;
	private String fileName;

	public Photo(String filePath, double requestedWidth) {
		super(filePath, requestedWidth, 0, true, false);

		fileName = filePath.substring(filePath.lastIndexOf("/"));

	}

	/**
	 * Returns the fileName of the Photo
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the file name of the photo
	 */
	public String getFileName() {
		return fileName;
	}

}
