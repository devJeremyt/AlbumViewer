package edu.westga.cs1302.project4.viewmodel;

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
	private ListProperty photoListProperty;

	/**
	 * Creates a new PhotoScrollerViewModel
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public PhotoScrollerViewModel() {
		this.album = new PhotoAlbum();
		this.photoListProperty = new SimpleListProperty();
		this.photoListProperty.set(FXCollections.observableArrayList(this.album));
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
		return this.album.add(photo);
	}
}
