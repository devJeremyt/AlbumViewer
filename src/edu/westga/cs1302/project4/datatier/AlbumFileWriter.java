package edu.westga.cs1302.project4.datatier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.westga.cs1302.project4.model.Photo;
import edu.westga.cs1302.project4.model.PhotoAlbum;
import edu.westga.cs1302.project4.resources.ExceptionMessages;

/**
 * The AlbumFileWriter class
 * 
 * @author jeremy.trimble
 * @version 12/4/2018
 */
public final class AlbumFileWriter {

	/**
	 * Saves the specified album's data to the specified file
	 * 
	 * @precondition file != null && album != null
	 * @postcondition file contains album data
	 * 
	 * @param file
	 *            the file to be written to
	 * @param album
	 *            the album to be written
	 */
	public static void createAlbumFile(File file, PhotoAlbum album) {
		if (file == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_FILE);
		}

		if (album == null) {
			throw new IllegalArgumentException(ExceptionMessages.NULL_ALBUM);
		}

		try (FileWriter writer = new FileWriter(file)) {
			String content = "";

			for (Photo current : album) {
				content += current.getFilePath() + System.lineSeparator();
			}
			writer.append(content);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
