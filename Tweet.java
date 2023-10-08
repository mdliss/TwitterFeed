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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/sp2023/p7/javadocs/Tweet.html
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * The Tweet class represents a single tweet in the TwitterFeed program. Each
 * tweet has a user, text, timestamp, and engagement metrics (likes and
 * retweets).
 */
public class Tweet {
	private static Calendar dateGenerator;
	private static final int MAX_LENGTH = 280;

	private int numLikes;
	private int numRetweets;
	private String text;
	private Date timestamp;
	private User user;

	/**
	 * Constructs a new Tweet with the specified user and text. The timestamp is set
	 * to the current date and time using the dateGenerator provided through the
	 * setCalendar() method.
	 * 
	 * @param user The User who posted the tweet.
	 * @param text The content of the tweet.
	 * @throws IllegalArgumentException if the text length exceeds the maximum
	 *                                  allowed characters.
	 */
	public Tweet(User user, String text) {
		if (text.length() > MAX_LENGTH) {
			throw new IllegalArgumentException("Tweet length exceeds maximum allowed characters.");
		}

		this.user = user;
		this.text = text;
		this.timestamp = dateGenerator.getTime();
	}

	/**
	 * Checks if this Tweet is equal to another object. Two tweets are considered
	 * equal if they have the same number of likes, retweets, text, timestamp, and
	 * user.
	 * 
	 * @param o The object to compare with this Tweet.
	 * @return true if the object is equal to this Tweet, false otherwise.
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Tweet tweet = (Tweet) o;
		return numLikes == tweet.numLikes && numRetweets == tweet.numRetweets && text.equals(tweet.text)
				&& timestamp.equals(tweet.timestamp) && user.equals(tweet.user);
	}

	/**
	 * Returns the ratio of likes to total engagement (likes + retweets) for this
	 * Tweet. The ratio is represented as a decimal value between 0 and 1.
	 * 
	 * @return The ratio of likes to total engagement for this Tweet.
	 */
	public double getLikesRatio() {
		int totalEngagement = numLikes + numRetweets;
		return (double) numLikes / totalEngagement;
	}

	/**
	 * Returns the text content of this Tweet.
	 * 
	 * @return The text content of this Tweet.
	 */
	public String getText() {
		return text;
	}

	/**
	 * Returns the total engagement of this Tweet, which is the sum of likes and
	 * retweets.
	 * 
	 * @return The total engagement of this Tweet.
	 */
	public int getTotalEngagement() {
		return numLikes + numRetweets;
	}

	/**
	 * Checks if the user who posted this Tweet is verified (has a verified
	 * account).
	 * 
	 * @return true if the user is verified, false otherwise.
	 */
	public boolean isUserVerified() {
		return user.isVerified();
	}

	/**
	 * Increases the number of likes for this Tweet by one.
	 */
	public void like() {
		numLikes++;
	}

	/**
	 * Increases the number of retweets for this Tweet by one.
	 */
	public void retweet() {
		numRetweets++;
	}

	/**
	 * Sets the static dateGenerator used for timestamping tweets.
	 * 
	 * @param c The Calendar instance to use for timestamp generation.
	 */
	public static void setCalendar(Calendar c) {
		dateGenerator = c;
	}

	/**
	 * Returns a string representation of this Tweet, including the user, timestamp,
	 * text, number of likes, and number of retweets.
	 * 
	 * @return A string representation of this Tweet.
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("tweet from ").append(user).append(" at ").append(timestamp).append(":\n");
		stringBuilder.append("-- ").append(text).append("\n");
		stringBuilder.append("-- likes: ").append(numLikes).append("\n");
		stringBuilder.append("-- retweets: ").append(numRetweets);
		return stringBuilder.toString();
	}
}
