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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/sp2023/p7/javadocs/ChronoTwiterator.html
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;
/**
 * The ChronoTwiterator class is an iterator that allows sequential access to a series of tweets in
 * chronological order. It implements the Iterator interface with the Tweet type.
 */
public class ChronoTwiterator implements Iterator<Tweet> {
    private TweetNode next;
    /**
     * Constructs a new ChronoTwiterator with the specified starting TweetNode. The iterator will
     * traverse the tweets in chronological order starting from the provided node.
     * 
     * @param startNode The starting TweetNode from which the iteration begins.
     */
    public ChronoTwiterator(TweetNode startNode) {
        this.next = startNode;
    }
    /**
     * Checks if there are more tweets to iterate over in chronological order.
     * 
     * @return true if there is another tweet to retrieve, false otherwise.
     */
    @Override
    public boolean hasNext() {
        return next != null;
    }
    /**
     * Returns the next tweet in chronological order and advances the iterator to the next tweet.
     * 
     * @return The next Tweet in chronological order.
     * @throws NoSuchElementException If there are no more tweets to retrieve (i.e., the iterator has
     *         reached the end of the linked list).
     */
    @Override
    public Tweet next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Tweet tweet = next.getTweet();
        next = next.getNext();
		return tweet;
	}
}

