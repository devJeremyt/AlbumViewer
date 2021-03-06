package edu.westga.cs1302.project4.viewmodel;

import java.io.File;
import java.util.List;

import edu.westga.cs1302.project4.datatier.AlbumFileReader;
import edu.westga.cs1302.project4.datatier.AlbumFileWriter;
import edu.westga.cs1302.project4.model.ColorFilterOptions;
import edu.westga.cs1302.project4.model.Photo;
import edu.westga.cs1302.project4.model.PhotoAlbum;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The PhotoScrollerViewModel
 * 
 * @author jeremy.trimble
 * @version 12/3/2018
 */
public class PhotoScrollerViewModel {
	private PhotoAlbum album;
	private ListProperty<Photo> photoListProperty;
	private BooleanProperty emptyAlbumProperty;
	private ListProperty<ColorFilterOptions> colorFilterProperty;

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
		this.emptyAlbumProperty = new SimpleBooleanProperty(true);
		this.colorFilterProperty = new SimpleListProperty<ColorFilterOptions>();
		this.colorFilterProperty.set(FXCollections.observableArrayList(ColorFilterOptions.values()));
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
	 * Gets the emptyAlbumProperty
	 * 
	 * @return the emptuAlbumProperty
	 */
	public BooleanProperty emptyAlbumProperty() {
		return this.emptyAlbumProperty;
	}

	/**
	 * Gets the next Photo in the list
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param currentIndex
	 *            the current index
	 * 
	 * @return the next Photo
	 */
	public Photo getNext(int currentIndex) {
		return this.album.getNext(currentIndex);
	}

	/**
	 * Gets the previous Photo in the list
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param currentIndex
	 *            the current index
	 * 
	 * @return the previous Photo in the list
	 */
	public Photo getPrevious(int currentIndex) {
		return this.album.getPrevious(currentIndex);
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
		if (!answer) {
			Alert failed = new Alert(AlertType.INFORMATION);
			failed.setTitle("Photo Not Loaded");
			failed.setHeaderText("Duplicate Photo");
			failed.setContentText(photo.getFileName() + " is a duplicate, and was not loaded.");
			failed.showAndWait();
		}
		this.updateProperties();
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
		List<String> duplicates = this.album.getDuplicatePhotos();
		if (!duplicates.isEmpty()) {
			String content = "";
			Alert failed = new Alert(AlertType.INFORMATION);
			failed.setTitle("Duplicate Photos Exist");
			failed.setHeaderText("The following photos were duplicates and were only loaded once");
			for (String current : duplicates) {
				content += current + System.lineSeparator();
			}
			failed.setContentText(content);
			failed.showAndWait();
		}
		this.updateProperties();

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
		this.updateProperties();
		return result;

	}

	/**
	 * Saves the current album to the specified file
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param selectedFile
	 *            the file to be saved too
	 */
	public void saveAlbum(File selectedFile) {
		AlbumFileWriter.createAlbumFile(selectedFile, this.album);

	}

	private void updateProperties() {
		this.photoListProperty.set(FXCollections.observableArrayList(this.album));

		if (this.album.isEmpty()) {
			this.emptyAlbumProperty.set(true);
		} else {
			this.emptyAlbumProperty.set(false);
		}
	}

	public ListProperty<ColorFilterOptions> colorFilterProperty() {
		return this.colorFilterProperty;
	}
}
