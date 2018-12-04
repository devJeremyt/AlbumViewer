package edu.westga.cs1302.project4.datatier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.westga.cs1302.project4.model.Photo;
import edu.westga.cs1302.project4.model.PhotoAlbum;
import edu.westga.cs1302.project4.resources.ExceptionMessages;

/**
 * The albumFileReader Class
 * 
 * @author jeremy.trimble
 * @version 12/4/2018
 */
public final class AlbumFileReader {

	/**
	 * Returns a new album created from the file specified
	 * 
	 * @precondition photoWidth > 0 && file != null
	 * 
	 * @param filePath
	 *            the file path of the photo album
	 * @param photosWidth
	 *            the requested width of the photo
	 * 
	 * @return album created from file
	 */
	public static PhotoAlbum readAlbumFile(String filePath, double photosWidth) {
		if (photosWidth <= 0) {
			throw new IllegalArgumentException(ExceptionMessages.PHOTO_WIDTH_GREATER_THAN_ZERO);
		}
		if (filePath == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_FILE);
		}
		PhotoAlbum album = new PhotoAlbum();
		try (Scanner scanner = new Scanner(new File(filePath))) {
			while (scanner.hasNextLine()) {
				String absPath = scanner.nextLine();
				Photo newPhoto = new Photo(absPath, photosWidth);
				album.add(newPhoto);
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		}
		return album;
	}
}
