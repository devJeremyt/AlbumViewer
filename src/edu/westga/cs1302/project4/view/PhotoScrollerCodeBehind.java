package edu.westga.cs1302.project4.view;

import java.io.File;

import edu.westga.cs1302.project4.model.ColorFilterOptions;
import edu.westga.cs1302.project4.model.Photo;
import edu.westga.cs1302.project4.viewmodel.PhotoScrollerViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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
	private ComboBox<ColorFilterOptions> colorFilterComboBox;

	@FXML
	private ListView<Photo> photosListView;

	@FXML
	private Button removeButton;

	@FXML
	private Button previousButton;

	@FXML
	private MenuItem removePhotoContextMenu;

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
		this.setupListeners();
	}

	private void setupListeners() {
		this.photosListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				this.imageView.setImage(this.createFilteredImage());
			}
		});

		this.colorFilterComboBox.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					this.imageView.setImage(this.createFilteredImage());
				});

	}

	private Image createFilteredImage() {
		Photo originalPhoto = this.photosListView.getSelectionModel().getSelectedItem();
		ColorFilterOptions filter = this.colorFilterComboBox.getSelectionModel().getSelectedItem();
		PixelReader reader = originalPhoto.getPixelReader();
		WritableImage newImage = new WritableImage(reader, (int) originalPhoto.getWidth(),
				(int) originalPhoto.getHeight());
		PixelWriter writer = newImage.getPixelWriter();

		for (int i = 0; i < originalPhoto.getWidth(); i++) {
			for (int j = 0; j < originalPhoto.getHeight(); j++) {
				updatePixel(reader, writer, i, j, filter);

			}
		}

		return newImage;
	}

	private void updatePixel(PixelReader reader, PixelWriter writer, int i, int j, ColorFilterOptions filter) {
		Color originalColor = reader.getColor(i, j);
		Color newColor = Color.color(originalColor.getRed(), originalColor.getGreen(), originalColor.getBlue());

		if (filter == ColorFilterOptions.GREEN) {
			newColor = Color.color(originalColor.getRed(), 1, originalColor.getBlue());
		}
		if (filter == ColorFilterOptions.BLUE) {
			newColor = Color.color(originalColor.getRed(), originalColor.getGreen(), 1);
		}
		if (filter == ColorFilterOptions.RED) {
			newColor = Color.color(1, originalColor.getGreen(), originalColor.getBlue());
		}
		if (filter == ColorFilterOptions.NONE) {
			newColor = Color.color(originalColor.getRed(), originalColor.getGreen(), originalColor.getBlue());
		}
		writer.setColor(i, j, newColor);
	}

	private void setupBindings() {
		this.photosListView.itemsProperty().bind(this.viewmodel.photoListProperty());
		this.colorFilterComboBox.itemsProperty().bind(this.viewmodel.colorFilterProperty());
		this.nextButton.disableProperty().bind(this.viewmodel.emptyAlbumProperty());
		this.previousButton.disableProperty().bind(this.viewmodel.emptyAlbumProperty());
		this.removeButton.disableProperty().bind(this.viewmodel.emptyAlbumProperty()
				.or(this.photosListView.getSelectionModel().selectedItemProperty().isNull()));
		this.removePhotoContextMenu.disableProperty().bind(this.removeButton.disableProperty());

	}

	@FXML
	void saveAlbum() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Album");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PAF", "*.paf"),
				new ExtensionFilter("All Files", "*.*"));

		Stage stage = (Stage) this.mainPane.getScene().getWindow();
		File selectedFile = fileChooser.showSaveDialog(stage);
		if (selectedFile != null) {
			this.viewmodel.saveAlbum(selectedFile);
		}
	}

	@FXML
	void addPhoto() {
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
			this.photosListView.getSelectionModel().select(newPhoto);
		}

	}

	@FXML
	void loadAlbum() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Load Album");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("PAF", "*.paf"),
				new ExtensionFilter("All Files", "*.*"));

		Stage stage = (Stage) this.mainPane.getScene().getWindow();
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			this.viewmodel.loadAlbum(selectedFile.getAbsolutePath(), this.imageView.getFitWidth());
			this.photosListView.getSelectionModel().selectIndices(0);
		}
	}

	@FXML
	void nextPhoto() {
		int currentIndex = this.photosListView.getSelectionModel().getSelectedIndex();
		this.photosListView.getSelectionModel().select(this.viewmodel.getNext(currentIndex));

	}

	@FXML
	void previousPhoto(ActionEvent event) {
		int currentIndex = this.photosListView.getSelectionModel().getSelectedIndex();
		this.photosListView.getSelectionModel().select(this.viewmodel.getPrevious(currentIndex));
	}

	@FXML
	void removePhoto(ActionEvent event) {
		this.viewmodel.removePhoto(this.photosListView.getSelectionModel().getSelectedItem());
		this.photosListView.getSelectionModel().selectNext();

	}

	@FXML
	void displayAbout(ActionEvent event) {
		Alert about = new Alert(AlertType.INFORMATION);
		about.setTitle("About");
		about.setHeaderText("PhotoScroller by Jeremy Trimble");
		about.setContentText("Version 1.0");
		about.showAndWait();
	}

}
