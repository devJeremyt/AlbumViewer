package edu.westga.cs1302.project4.view;

import edu.westga.cs1302.project4.viewmodel.PhotoScrollerViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

/**
 * The PhotoScrollerCodeBehind
 * 
 * @author jeremy.trimble
 * @version 12/3/2018
 */
public class PhotoScrollerCodeBehind {

	@FXML
	private MenuItem fileLoadAlbumMenuItem;

	@FXML
	private MenuItem fileSaveAlbumMenuItem;

	@FXML
	private MenuItem fileAddPhotoMenuItem;

	@FXML
	private Button removeButton;

	@FXML
	private Button previousButton;

	@FXML
	private Button nextButton;

	private PhotoScrollerViewModel viewmodel;

	/**
	 * Creates a new code behind for the PhotoScroller
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 */
	public PhotoScrollerCodeBehind() {
		this.viewmodel = new PhotoScrollerViewModel();
	}

	@FXML
	void saveAlbum(ActionEvent event) {

	}

	@FXML
	void addPhoto(ActionEvent event) {

	}

	@FXML
	void loadAlbum(ActionEvent event) {

	}

	@FXML
	void nextPhoto() {
		this.viewmodel.getNext();
	}

	@FXML
	void previousPhoto(ActionEvent event) {
		this.viewmodel.getPrevious();
	}

	@FXML
	void removePhoto(ActionEvent event) {

	}

}
