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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/sp2023/p7/javadocs/VerifiedTwiterator.html
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;
/**
 * The VerifiedTwiterator class implements an iterator for the TwitterFeed program that iterates through
 * tweets with verified users only. It allows iterating through tweets while skipping non-verified user tweets.
 * The iterator starts from a given start node and searches for the next node with a verified user tweet.
 */
public class VerifiedTwiterator implements Iterator<Tweet> {
    private TweetNode next;
    /**
     * Constructs a new VerifiedTwiterator starting from the given start node.
     * The iterator will skip non-verified user tweets and find the next node with a verified user tweet.
     *
     * @param startNode the starting node for the iterator.
     */
    public VerifiedTwiterator(TweetNode startNode) {
        // Find the next node with a verified user tweet
        this.next = findNextVerifiedNode(startNode);
    }
    /**
     * Helper method to find the next node with a tweet from a verified user, starting from the given node.
     *
     * @param node the starting node for the search.
     * @return the next node with a tweet from a verified user, or null if no such node is found.
     */
    private TweetNode findNextVerifiedNode(TweetNode node) {
        while (node != null && !node.getTweet().isUserVerified()) {
            node = node.getNext();
        }
        return node;
    }
    /**
     * Checks if there is another tweet with a verified user in the iteration sequence.
     *
     * @return true if there is another tweet with a verified user, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return next != null;
    }

	/**
	 * Returns the next tweet with a verified user in the iteration sequence.
	 *
	 * @return the next tweet with a verified user.
	 * @throws NoSuchElementException if there are no more tweets with verified
	 *                                users in the iteration sequence.
	 */
	@Override
	public Tweet next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Tweet tweet = next.getTweet();
		next = findNextVerifiedNode(next.getNext());
		return tweet;
	}
}