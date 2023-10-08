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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/sp2023/p7/javadocs/TweetNode.html
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * The TweetNode class represents a node in the linked list used to store tweets
 * in the TwitterFeed program. Each node contains a reference to a Tweet object
 * and a reference to the next node in the list.
 */
public class TweetNode {
	private TweetNode nextTweet;
	private Tweet tweet;

	/**
	 * Constructs a new TweetNode with the given Tweet and sets its next node
	 * reference to null.
	 * 
	 * @param tweet The Tweet object to store in this node.
	 */
	public TweetNode(Tweet tweet) {
		this.tweet = tweet;
		this.nextTweet = null;
	}

	/**
	 * Constructs a new TweetNode with the given Tweet and sets its next node
	 * reference to the provided next node.
	 * 
	 * @param tweet The Tweet object to store in this node.
	 * @param next  The next TweetNode in the linked list.
	 */
	public TweetNode(Tweet tweet, TweetNode next) {
		this.tweet = tweet;
		this.nextTweet = next;
	}

	/**
	 * Returns the next TweetNode in the linked list.
	 * 
	 * @return The next TweetNode in the linked list, or null if this is the last
	 *         node.
	 */
	public TweetNode getNext() {
		return nextTweet;
	}

	/**
	 * Returns the Tweet object stored in this node.
	 * 
	 * @return The Tweet object stored in this node.
	 */
	public Tweet getTweet() {
		return tweet;
	}

	/**
	 * Sets the next node in the linked list.
	 * 
	 * @param next The next TweetNode to set for this node.
	 */
	public void setNext(TweetNode next) {
		nextTweet = next;
	}
}
