package edu.westga.cs1302.project4.view;

import java.io.File;

import edu.westga.cs1302.project4.model.Photo;
import edu.westga.cs1302.project4.viewmodel.PhotoScrollerViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * The PhotoScrollerCodeBehind
 * 
 * @author jeremy.trimble
 * @version 12/3/2018
 */
public class PhotoScrollerCodeBehind {

	@FXML
	private AnchorPane mainPane;

	@FXML
	private MenuItem fileLoadAlbumMenuItem;

	@FXML
	private MenuItem fileSaveAlbumMenuItem;

	@FXML
	private MenuItem fileAddPhotoMenuItem;

	@FXML
	private ImageView imageView;

	@FXML
	private ListView<Photo> photosListView;

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
	void initialize() {
		this.setupBindings();
	}

	private void setupBindings() {
		this.photosListView.itemsProperty().bind(this.viewmodel.photoListProperty());

	}

	@FXML
	void saveAlbum(ActionEvent event) {

	}

	@FXML
	void addPhoto(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Add Photo");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("BMP", "*.bmp"),
				new ExtensionFilter("GIF", "*.gif"), new ExtensionFilter("JPEG", "*.jpg"),
				new ExtensionFilter("PNG", "*.png"), new ExtensionFilter("All Files", "*.*"));

		Stage stage = (Stage) this.mainPane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			Photo newPhoto = new Photo(selectedFile.getAbsolutePath(), this.imageView.getFitWidth());
			this.viewmodel.addPhoto(newPhoto);
		}

	}

	@FXML
	void loadAlbum(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Album");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PAF", "*.paf"),
				new ExtensionFilter("All Files", "*.*"));

		Stage stage = (Stage) this.mainPane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			this.viewmodel.loadAlbum(selectedFile.getAbsolutePath(), this.imageView.getFitWidth());
		}
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
