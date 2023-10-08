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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/sp2023/p7/javadocs/TwitterFeed.html
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * The TwitterFeed class represents a linked list of Tweet objects, forming a
 * timeline of tweets. It implements the ListADT<Tweet> interface and
 * Iterable<Tweet> interface, allowing it to manage tweets as a list and provide
 * iteration capabilities. The class supports various modes for iterating
 * through tweets, including chronological, verified only, and by like ratio.
 */
public class TwitterFeed implements ListADT<Tweet>, Iterable<Tweet> {
	private TweetNode head;
	private TweetNode tail;
	private TimelineMode mode;
	private static double ratio = 0.5;
	private int size;

	/**
	 * Constructs a new empty TwitterFeed with default chronological mode and ratio
	 * value.
	 */
	public TwitterFeed() {
		this.head = null;
		this.tail = null;
		this.mode = TimelineMode.CHRONOLOGICAL;
		this.size = 0;
	}

	/**
	 * Returns the number of tweets in the TwitterFeed.
	 *
	 * @return The number of tweets in the TwitterFeed.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Checks if the TwitterFeed is empty (contains no tweets).
	 *
	 * @return true if the TwitterFeed is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Checks if the TwitterFeed contains the specified tweet.
	 *
	 * @param findObject The tweet to search for in the TwitterFeed.
	 * @return true if the tweet is found in the TwitterFeed, false otherwise.
	 */
	@Override
	public boolean contains(Tweet findObject) {
		TweetNode current = head;
		while (current != null) {
			if (current.getTweet().equals(findObject)) {
				return true;
			}
			current = current.getNext();
		}
		return false;
	}

	/**
	 * Returns the index of the first occurrence of the specified tweet in the
	 * TwitterFeed, or -1 if the tweet is not found.
	 *
	 * @param findObject The tweet to search for in the TwitterFeed.
	 * @return The index of the first occurrence of the tweet, or -1 if the tweet is
	 *         not found.
	 */
	@Override
	public int indexOf(Tweet findObject) {
		TweetNode current = head;
		int index = 0;
		while (current != null) {
			if (current.getTweet().equals(findObject)) {
				return index;
			}
			current = current.getNext();
			index++;
		}
		return -1;
	}

	/**
	 * Returns the tweet at the specified index in the TwitterFeed.
	 *
	 * @param index The index of the tweet to retrieve.
	 * @return The tweet at the specified index.
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size()).
	 */
	@Override
	public Tweet get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		TweetNode current = head;
		for (int i = 0; i < index; i++) {
			current = current.getNext();
		}
		return current.getTweet();
	}

	/**
	 * Adds the specified tweet to the end of the TwitterFeed.
	 *
	 * @param newObject The tweet to add to the TwitterFeed.
	 */
	@Override
	public void addLast(Tweet newObject) {
		if (isEmpty()) {
			head = new TweetNode(newObject);
			tail = head;
		} else {
			TweetNode newNode = new TweetNode(newObject);
			tail.setNext(newNode);
			tail = newNode;
		}
		size++;
	}

	/**
	 * Adds the specified tweet to the beginning of the TwitterFeed.
	 *
	 * @param newObject The tweet to add to the TwitterFeed.
	 */
	@Override
	public void addFirst(Tweet newObject) {
		if (isEmpty()) {
			head = new TweetNode(newObject);
			tail = head;
		} else {
			TweetNode newNode = new TweetNode(newObject);
			newNode.setNext(head);
			head = newNode;
		}
		size++;
	}

	/**
	 * Adds the specified tweet at the specified index in the TwitterFeed. Shifts
	 * the subsequent tweets to the right (increases their indices).
	 *
	 * @param index     The index at which to add the tweet.
	 * @param newObject The tweet to add to the TwitterFeed.
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index > size()).
	 */
	@Override
	public void add(int index, Tweet newObject) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		if (index == 0) {
			addFirst(newObject);
		} else if (index == size) {
			addLast(newObject);
		} else {
			TweetNode current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.getNext();
			}
			TweetNode newNode = new TweetNode(newObject);
			newNode.setNext(current.getNext());
			current.setNext(newNode);
			size++;
		}
	}

	/**
	 * Deletes the tweet at the specified index in the TwitterFeed. Shifts the
	 * subsequent tweets to the left (decreases their indices).
	 *
	 * @param index The index of the tweet to delete.
	 * @return The deleted tweet.
	 * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
	 *                                   index >= size()).
	 */
	@Override
	public Tweet delete(int index) {
		if (isEmpty() || index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Invalid index: " + index);
		}
		TweetNode deletedNode;
		if (index == 0) {
			deletedNode = head;
			head = head.getNext();
			if (head == null) {
				tail = null;
			}
		} else {
			TweetNode current = head;
			for (int i = 0; i < index - 1; i++) {
				current = current.getNext();
			}
			deletedNode = current.getNext();
			current.setNext(current.getNext().getNext());
			if (current.getNext() == null) {
				tail = current;
			}
		}
		size--;
		return deletedNode.getTweet();
	}

	/**
	 * Sets the iteration mode for the TwitterFeed.
	 *
	 * @param m The TimelineMode to set for the TwitterFeed.
	 */
	public void setMode(TimelineMode m) {
		this.mode = m;
	}

	/**
	 * Returns the first tweet in the TwitterFeed (the head of the timeline).
	 *
	 * @return The first tweet in the TwitterFeed.
	 * @throws NoSuchElementException if the TwitterFeed is empty.
	 */
	public Tweet getHead() {
		if (isEmpty()) {
			throw new NoSuchElementException("Twitter feed is empty.");
		}
		return head.getTweet();
	}

	/**
	 * Returns the last tweet in the TwitterFeed (the tail of the timeline).
	 *
	 * @return The last tweet in the TwitterFeed.
	 * @throws NoSuchElementException if the TwitterFeed is empty.
	 */
	public Tweet getTail() {
		if (isEmpty()) {
			throw new NoSuchElementException("Twitter feed is empty.");
		}
		return tail.getTweet();
	}

	/**
	 * Returns an iterator over the tweets in the TwitterFeed, providing iteration
	 * according to the current mode set for the TwitterFeed.
	 *
	 * @return An iterator over the tweets in the TwitterFeed.
	 */
	@Override
	public Iterator<Tweet> iterator() {
		switch (mode) {
		case CHRONOLOGICAL:
			return new ChronoTwiterator(head);
		case VERIFIED_ONLY:
			return new VerifiedTwiterator(head);
		case LIKE_RATIO:
			return new RatioTwiterator(head, ratio);
		default:
			return null;
		}
	}
}
