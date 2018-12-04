package edu.westga.cs1302.project4.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * The PhotoAlbum Class
 * 
 * @author jeremy.trimble
 * @version 12/3/2018
 */
public class PhotoAlbum implements Collection<Photo> {
	private Collection<Photo> photos;

	/**
	 * Creates a new PhotoAlbum
	 * 
	 */
	public PhotoAlbum() {
		this.photos = new ArrayList<Photo>();
	}

	@Override
	public boolean add(Photo photo) {
		return this.photos.add(photo);
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

}
