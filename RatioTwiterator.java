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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/sp2023/p7/javadocs/RatioTwiterator.html
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * The RatioTwiterator class implements an iterator for the TwitterFeed program
 * that iterates through tweets based on their likes ratio. It allows iterating
 * through tweets while skipping those with a likes ratio below a specified
 * threshold. The iterator starts from a given start node and searches for the
 * next node with a tweet that meets the threshold requirement.
 */
public class RatioTwiterator implements Iterator<Tweet> {
	private TweetNode next;
	private final double THRESHOLD;

	/**
	 * Constructs a new RatioTwiterator starting from the given start node and with
	 * the specified threshold for the likes ratio. The iterator will skip tweets
	 * with a likes ratio below the threshold and find the next node with a tweet
	 * that meets the threshold requirement.
	 *
	 * @param startNode the starting node for the iterator.
	 * @param threshold the threshold for the likes ratio. Tweets with a ratio below
	 *                  this value will be skipped.
	 */
	public RatioTwiterator(TweetNode startNode, double threshold) {
		this.next = findNextQualifiedNode(startNode, threshold);
		this.THRESHOLD = threshold;
	}

	/**
	 * Helper method to find the next node with a tweet that meets the threshold
	 * requirement, starting from the given node.
	 *
	 * @param node      the starting node for the search.
	 * @param threshold the threshold for the likes ratio. Tweets with a ratio below
	 *                  this value will be skipped.
	 * @return the next node with a tweet that meets the threshold requirement, or
	 *         null if no such node is found.
	 */
	private TweetNode findNextQualifiedNode(TweetNode node, double threshold) {
		while (node != null && node.getTweet().getLikesRatio() < threshold) {
			node = node.getNext();
		}
		return node;
	}

	/**
	 * Checks if there is another tweet that meets the threshold requirement in the
	 * iteration sequence.
	 *
	 * @return true if there is another tweet that meets the threshold requirement,
	 *         false otherwise.
	 */
	@Override
	public boolean hasNext() {
		return next != null;
	}

	/**
	 * Returns the next tweet that meets the threshold requirement in the iteration
	 * sequence.
	 *
	 * @return the next tweet that meets the threshold requirement.
	 * @throws NoSuchElementException if there are no more tweets that meet the
	 *                                threshold requirement in the iteration
	 *                                sequence.
	 */
	@Override
	public Tweet next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Tweet tweet = next.getTweet();
		next = findNextQualifiedNode(next.getNext(), THRESHOLD);
		return tweet;
	}
}
