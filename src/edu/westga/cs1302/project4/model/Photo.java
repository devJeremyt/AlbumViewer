package edu.westga.cs1302.project4.model;

import javafx.scene.image.Image;

/**
 * The Photo Class
 * 
 * @author jeremy.trimble
 * @version 12/3/2018
 */
public class Photo extends Image {
	private String filePath;
	private String fileName;

	/**
	 * Creates a new Photo
	 * 
	 * @precondition filePath != null && requestedWidth > 0
	 * @param filePath
	 *            the absolute path of the file
	 * @param requestedWidth
	 *            the requested width of the photo
	 */
	public Photo(String filePath, double requestedWidth) {
		super(filePath, requestedWidth, 0, true, false);

		this.fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
		this.filePath = filePath;

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
		return this.fileName;
	}

	@Override
	public String toString() {
		return this.fileName;
	}

}
