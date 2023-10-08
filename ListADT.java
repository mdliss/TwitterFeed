///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           TwitterFeed Program
// Course:          CS 300, Summer, 2023
//
// Author:          Max Liss-'s-Gravemade
// Email:           lisssgravema@wisc.edu
// Lecturer's Name: Michelle Jensen
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// https://canvas.wisc.edu/courses/355989/files/33161196?wrap=1
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * The ListADT interface represents a generic list abstract data type. It
 * provides methods to manage a list of elements of type T.
 *
 * @param <T> the type of elements stored in the list.
 */
public interface ListADT<T> {
	/**
	 * Returns the number of elements in the list.
	 *
	 * @return the number of elements in the list.
	 */
	public int size();

	/**
	 * Checks if the list is empty, i.e., it contains no elements.
	 *
	 * @return true if the list is empty, false otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Checks if the list contains a specific element.
	 *
	 * @param findObject the element to be searched in the list.
	 * @return true if the element is found in the list, false otherwise.
	 */
	public boolean contains(T findObject);

	/**
	 * Returns the index of the first occurrence of a specific element in the list.
	 *
	 * @param findObject the element to be searched in the list.
	 * @return the index of the first occurrence of the element, or -1 if not found.
	 */
	public int indexOf(T findObject);

	/**
	 * Retrieves the element at the specified index in the list.
	 *
	 * @param index the index of the element to retrieve.
	 * @return the element at the specified index.
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size()).
	 */
	public T get(int index);

	/**
	 * Adds a new element to the end of the list.
	 *
	 * @param newObject the element to be added to the end of the list.
	 */
	public void addLast(T newObject);

	/**
	 * Adds a new element to the beginning of the list.
	 *
	 * @param newObject the element to be added to the beginning of the list.
	 */
	public void addFirst(T newObject);

	/**
	 * Inserts a new element at the specified index in the list.
	 *
	 * @param index     the index at which the new element will be inserted.
	 * @param newObject the element to be inserted into the list.
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index > size()).
	 */
	public void add(int index, T newObject);

	/**
	 * Removes the element at the specified index from the list.
	 *
	 * @param index the index of the element to be deleted.
	 * @return the element that was removed.
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size()).
	 */
	public T delete(int index);
}
