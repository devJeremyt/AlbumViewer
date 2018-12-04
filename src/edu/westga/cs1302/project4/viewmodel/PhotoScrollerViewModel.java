package edu.westga.cs1302.project4.viewmodel;

import edu.westga.cs1302.project4.datatier.AlbumFileReader;
import edu.westga.cs1302.project4.model.Photo;
import edu.westga.cs1302.project4.model.PhotoAlbum;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;

/**
 * The PhotoScrollerViewModel
 * 
 * @author jeremy.trimble
 * @version 12/3/2018
 */
public class PhotoScrollerViewModel {
	private PhotoAlbum album;
	private ListProperty<Photo> photoListProperty;

	/**
	 * Creates a new PhotoScrollerViewModel
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public PhotoScrollerViewModel() {
		this.album = new PhotoAlbum();
		this.photoListProperty = new SimpleListProperty<Photo>();
		this.photoListProperty.set(FXCollections.observableArrayList(this.album));
	}

	/**
	 * Returns the album
	 * 
	 * @return the album
	 */
	public PhotoAlbum getAlbum() {
		return this.album;
	}

	/**
	 * Returns the photo list property
	 * 
	 * @return the photo list property
	 */
	public ListProperty<Photo> photoListProperty() {
		return this.photoListProperty;
	}

	/**
	 * Gets the next Photo in the list
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the next Photo
	 */
	public Photo getNext() {
		return this.album.getNext();
	}

	/**
	 * Gets the previous Photo in the list
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the previous Photo in the list
	 */
	public Photo getPrevious() {
		return this.album.getPrevious();
	}

	/**
	 * Adds the specified Photo to PhotoAlbum
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param photo
	 *            the photo to be added
	 * 
	 * @return true if the photo is added
	 */
	public boolean addPhoto(Photo photo) {
		boolean answer = this.album.add(photo);
		this.photoListProperty.set(FXCollections.observableArrayList(this.album));
		return answer;
	}

	/**
	 * Loads a new PhotoAlbum
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param filePath
	 *            the file Path of the new album
	 * @param photosWidth
	 *            requestedWidth of the photos
	 * 
	 */
	public void loadAlbum(String filePath, double photosWidth) {
		this.album = AlbumFileReader.readAlbumFile(filePath, photosWidth);
		this.photoListProperty.set(FXCollections.observableArrayList(this.album));

	}

	/**
	 * Removes the specified photo from the album
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param photo
	 *            the photo to be removed
	 * @return true if the photo was removed, else false
	 */
	public boolean removePhoto(Photo photo) {
		boolean result = this.album.remove(photo);
		this.photoListProperty.set(FXCollections.observableArrayList(this.album));
		return result;

	}
}
