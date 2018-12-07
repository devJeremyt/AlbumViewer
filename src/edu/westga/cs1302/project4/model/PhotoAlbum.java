package edu.westga.cs1302.project4.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import edu.westga.cs1302.project4.resources.ExceptionMessages;

/**
 * The PhotoAlbum Class
 * 
 * @author jeremy.trimble
 * @version 12/3/2018
 */
public class PhotoAlbum implements Collection<Photo> {
	private List<Photo> photos;
	private int currentIndex;
	private List<String> duplicatePhotos;

	/**
	 * Creates a new PhotoAlbum
	 * 
	 */
	public PhotoAlbum() {
		this.photos = new ArrayList<Photo>();
		this.duplicatePhotos = new ArrayList<String>();
		this.currentIndex = 0;
	}

	/**
	 * Returns a list of duplicate photos in the album
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return a list of duplicate photos
	 */
	public List<String> getDuplicatePhotos() {
		return this.duplicatePhotos;
	}

	/**
	 * Returns the next photo in the album, starts over at the beginning if user
	 * reaches the last image
	 * 
	 * @precondition !isEmpty()
	 * @postcondition none
	 * 
	 * @param currentIndex
	 *            the current index of the collection
	 * 
	 * @return the next Photo in the album
	 */
	public Photo getNext(int currentIndex) {
		if (this.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_ALBUM);
		}
		if (currentIndex < 0) {
			throw new IllegalArgumentException(ExceptionMessages.INDEX_LESS_THAN_ZERO);
		}
		if (currentIndex >= this.photos.size()) {
			throw new IllegalArgumentException(ExceptionMessages.INDEX_OUT_OF_BOUNDS);
		}
		int newIndex = 0;
		if (currentIndex != this.photos.size() - 1) {
			newIndex = currentIndex + 1;
		}
		return this.photos.get(newIndex);
	}

	/**
	 * Returns the previous photo in the album, goes to the last image in the album
	 * once it reaches the beginning.
	 * 
	 * @precondition !isEmpty() && currentIndex >= 0 && currentIndex < size()
	 * @postcondition none
	 * 
	 * @param currentIndex
	 *            the current index of the collection
	 * 
	 * @return the previous Photo in the album
	 */
	public Photo getPrevious(int currentIndex) {
		if (this.isEmpty()) {
			throw new IllegalArgumentException(ExceptionMessages.EMPTY_ALBUM);
		}
		if (currentIndex < 0) {
			throw new IllegalArgumentException(ExceptionMessages.INDEX_LESS_THAN_ZERO);
		}
		if (currentIndex >= this.photos.size()) {
			throw new IllegalArgumentException(ExceptionMessages.INDEX_OUT_OF_BOUNDS);
		}
		int newIndex = this.photos.size() - 1;
		if (currentIndex != 0) {
			newIndex = currentIndex - 1;
		}
		return this.photos.get(newIndex);
	}

	@Override
	public boolean add(Photo photo) {
		if (!this.contains(photo.getFilePath())) {
			return this.photos.add(photo);
		}

		this.duplicatePhotos.add(photo.getFileName());
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Photo> photos) {
		return this.photos.addAll(photos);
	}

	@Override
	public void clear() {
		this.photos.clear();

	}

	@Override
	public boolean contains(Object photo) {
		return this.photos.contains(photo);
	}

	/**
	 * Returns whether the same file exist in the album
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return true if a photo with the same file path exist
	 */
	public boolean contains(String filePath) {
		for (Photo current : this.photos) {
			if (current.getFilePath().equals(filePath)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> photos) {
		return this.photos.containsAll(photos);
	}

	@Override
	public boolean isEmpty() {
		return this.photos.isEmpty();
	}

	@Override
	public Iterator<Photo> iterator() {
		return this.photos.iterator();
	}

	@Override
	public boolean remove(Object photo) {
		return this.photos.remove(photo);
	}

	@Override
	public boolean removeAll(Collection<?> photos) {
		return this.photos.removeAll(photos);
	}

	@Override
	public boolean retainAll(Collection<?> photos) {
		return this.photos.retainAll(photos);
	}

	@Override
	public int size() {
		return this.photos.size();
	}

	@Override
	public Object[] toArray() {
		return this.photos.toArray();
	}

	@Override
	public <T> T[] toArray(T[] photo) {
		return this.photos.toArray(photo);
	}

	/**
	 * Returns the current index of the photo in the album
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the current index
	 */
	public int getCurrentIndex() {
		return this.currentIndex;
	}

}
