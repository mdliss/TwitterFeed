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
// 
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * The TwiteratorTester class contains a set of methods for testing various
 * functionalities of the TwitterFeed program.
 * 
 * @author maxliss
 *
 */
public class TwiteratorTester {
	/**
	 * Tests the User class by validating various User methods.
	 * 
	 * @return true if all User tests pass, otherwise false.
	 */
	public static boolean testUser() {
		boolean allTestsPassed = true;

		// Test getUsername()
		User user1 = new User("uwmadison");
		User user2 = new User("dril");
		if (!user1.getUsername().equals("uwmadison") || !user2.getUsername().equals("dril")) {
			allTestsPassed = false;
		}

		// Test isVerified() and verify()
		if (user1.isVerified() || user2.isVerified()) {
			allTestsPassed = false;
		}
		user1.verify();
		user2.verify();
		if (!user1.isVerified() || !user2.isVerified()) {
			allTestsPassed = false;
		}

		// Test revokeVerification()
		user1.revokeVerification();
		if (user1.isVerified()) {
			allTestsPassed = false;
		}

		// Test toString()
		if (!user1.toString().equals("@uwmadison") || !user2.toString().equals("@dril*")) {
			allTestsPassed = false;
		}

		// Test invalid username (should throw IllegalArgumentException)
		if (!testInvalidUsername("user$3") || !testInvalidUsername("user*name") || !testInvalidUsername("user#2")) {
			allTestsPassed = false;
		}

		return allTestsPassed;
	}

	/**
	 * Helper method for testing invalid usernames with User class.
	 * 
	 * @param username The username to test for invalidity.
	 * @return true if the username throws an IllegalArgumentException, otherwise
	 *         false.
	 */
	private static boolean testInvalidUsername(String username) {
		try {
			new User(username);
			// If the above line doesn't throw an exception, the test passed, return false.
			return false;
		} catch (IllegalArgumentException e) {
			// The exception was caught, which means the test passed for an invalid
			// username.
			return true;
		}
	}

	/**
	 * Tests the Tweet class by validating various Tweet methods.
	 * 
	 * @return true if all Tweet tests pass, otherwise false.
	 */
	public static boolean testTweet() {
		User user = new User("dril");
		Tweet.setCalendar(Calendar.getInstance()); // Set the dateGenerator for consistent timestamps

		Tweet tweet = new Tweet(user,
				"IF THE ZOO BANS ME FOR HOLLERING AT THE ANIMALS I WILL FACE GOD AND WALK BACKWARDS INTO HELL");

		// Test getText()
		if (!tweet.getText()
				.equals("IF THE ZOO BANS ME FOR HOLLERING AT THE ANIMALS I WILL FACE GOD AND WALK BACKWARDS INTO HELL"))
			return false;

		// Test getTotalEngagement()
		tweet.like();
		tweet.retweet();
		if (tweet.getTotalEngagement() != 2)
			return false;

		// Test getLikesRatio()
		if (tweet.getLikesRatio() != 0.5)
			return false;

		// Test isUserVerified()
		if (!tweet.isUserVerified())
			return false;

		// Test toString()
		String expectedOutput = "tweet from @dril* at [timestamp]:\n"
				+ "-- IF THE ZOO BANS ME FOR HOLLERING AT THE ANIMALS I WILL FACE GOD AND WALK BACKWARDS INTO HELL\n"
				+ "-- likes: 1\n" + "-- retweets: 1";

		String actualOutput = tweet.toString();
		if (!actualOutput.startsWith("tweet from @dril* at ") || !actualOutput.endsWith("1"))
			return false;

		return true;
	}

	/**
	 * Tests the TweetNode class by validating the TweetNode constructor and its
	 * methods.
	 * 
	 * @return true if all TweetNode tests pass, otherwise false.
	 */
	public static boolean testNode() {

		Tweet.setCalendar(Calendar.getInstance());

		User user1 = new User("user1");
		User user2 = new User("user2");

		Tweet tweet1 = new Tweet(user1, "Tweet 1");
		Tweet tweet2 = new Tweet(user2, "Tweet 2");

		// Modify TweetNode constructor to correctly set the next field
		TweetNode node1 = new TweetNode(tweet1);
		TweetNode node2 = new TweetNode(tweet2);

		// Connect node1 to node2
		node1.setNext(node2);

		// Test getNext() and getTweet() methods
		if (node1.getNext() != node2)
			return false;
		if (node1.getTweet() != tweet1)
			return false;
		if (node2.getTweet() != tweet2)
			return false;

		return true;
	}

	/**
	 * Tests the TwitterFeed class by validating the addFirst, addLast, isEmpty,
	 * size, contains, get, getHead, and getTail methods.
	 * 
	 * @return true if all TwitterFeed tests pass, otherwise false.
	 */
	public static boolean testAddTweet() {
		TwitterFeed feed = new TwitterFeed();

		// Test isEmpty() and size() for an empty feed
		if (!feed.isEmpty())
			return false;
		if (feed.size() != 0)
			return false;

		// Add the first tweet using addFirst()
		Tweet tweet1 = new Tweet(new User("user1"), "Tweet 1");
		feed.addFirst(tweet1);

		// Test isEmpty(), size(), contains(), get(), getHead(), and getTail() after
		// adding the first tweet
		if (feed.isEmpty())
			return false;
		if (feed.size() != 1)
			return false;
		if (!feed.contains(tweet1))
			return false;
		if (feed.get(0) != tweet1)
			return false;
		if (feed.getHead() != tweet1 || feed.getTail() != tweet1)
			return false;

		// Add the second tweet using addLast()
		Tweet tweet2 = new Tweet(new User("user2"), "Tweet 2");
		feed.addLast(tweet2);

		// Test isEmpty(), size(), contains(), get(), getHead(), and getTail() after
		// adding the second tweet
		if (feed.isEmpty())
			return false;
		if (feed.size() != 2)
			return false;
		if (!feed.contains(tweet2))
			return false;
		if (feed.get(1) != tweet2)
			return false;
		if (feed.getHead() != tweet1 || feed.getTail() != tweet2)
			return false;

		return true;
	}

	/**
	 * Tests the TwitterFeed class by validating the add method with various
	 * indexes, and the getHead, and getTail methods.
	 * 
	 * @return true if all TwitterFeed tests pass, otherwise false.
	 */
	public static boolean testInsertTweet() {
		TwitterFeed feed = new TwitterFeed();

		// Create some tweets
		Tweet tweet1 = new Tweet(new User("user1"), "Tweet 1");
		Tweet tweet2 = new Tweet(new User("user2"), "Tweet 2");
		Tweet tweet3 = new Tweet(new User("user3"), "Tweet 3");

		// Add tweets with various indexes
		feed.addFirst(tweet1); // Index 0
		feed.add(1, tweet2); // Index 1
		feed.addLast(tweet3); // Index 2

		// Test size() and get() with various indexes
		if (feed.size() != 3)
			return false;
		if (feed.get(0) != tweet1)
			return false;
		if (feed.get(1) != tweet2)
			return false;
		if (feed.get(2) != tweet3)
			return false;

		// Test getHead() and getTail()
		if (feed.getHead() != tweet1 || feed.getTail() != tweet3)
			return false;

		return true;
	}

	/**
	 * Tests the TwitterFeed class by validating the delete method for deleting
	 * tweets from the feed.
	 * 
	 * @return true if all TwitterFeed tests pass, otherwise false.
	 */
	public static boolean testDeleteTweet() {
		TwitterFeed feed = new TwitterFeed();

		// Create some tweets
		Tweet tweet1 = new Tweet(new User("user1"), "Tweet 1");
		Tweet tweet2 = new Tweet(new User("user2"), "Tweet 2");
		Tweet tweet3 = new Tweet(new User("user3"), "Tweet 3");
		Tweet tweet4 = new Tweet(new User("user4"), "Tweet 4");
		Tweet tweet5 = new Tweet(new User("user5"), "Tweet 5");

		// Add tweets to the feed
		feed.addLast(tweet1);
		feed.addLast(tweet2);
		feed.addLast(tweet3);
		feed.addLast(tweet4);
		feed.addLast(tweet5);

		// Test size() and getTail() before deleting
		if (feed.size() != 5)
			return false;
		if (feed.getTail() != tweet5)
			return false;

		// Delete the last tweet
		Tweet deletedTweet = feed.delete(4);

		// Test size() and getTail() after deleting
		if (feed.size() != 4)
			return false;
		if (feed.getTail() != tweet4)
			return false;
		if (deletedTweet != tweet5)
			return false;

		// Delete the first tweet
		deletedTweet = feed.delete(0);

		// Test size() and getHead() after deleting
		if (feed.size() != 3)
			return false;
		if (feed.getHead() != tweet2)
			return false;
		if (deletedTweet != tweet1)
			return false;

		// Delete a tweet from the middle
		deletedTweet = feed.delete(1);

		// Test size() and get() after deleting from the middle
		if (feed.size() != 2)
			return false;
		if (feed.get(1) != tweet4)
			return false;
		if (deletedTweet != tweet3)
			return false;

		return true;
	}

	/**
	 * Retrieves the first tweet from the TwitterFeed.
	 * 
	 * @param feed The TwitterFeed to retrieve the first tweet from.
	 * @return The first tweet in the TwitterFeed.
	 * @throws NoSuchElementException If the TwitterFeed is empty.
	 */
	private static Tweet getFirstTweet(TwitterFeed feed) {
		// Implement the logic to get the first tweet from the feed
		if (feed.isEmpty()) {
			throw new NoSuchElementException("TwitterFeed is empty.");
		}
		return feed.iterator().next();
	}

	/**
	 * Tests the ChronoTwiterator class by iterating through the tweets in
	 * chronological order and comparing them with the original feed.
	 * 
	 * @return true if the ChronoTwiterator test passes, otherwise false.
	 */
	public static boolean testChronoTwiterator() {
		TwitterFeed feed = new TwitterFeed();
		User user1 = new User("user1");
		User user2 = new User("user2");
		User user3 = new User("user3");

		Tweet tweet1 = new Tweet(user1, "Tweet 1");
		Tweet tweet2 = new Tweet(user2, "Tweet 2");
		Tweet tweet3 = new Tweet(user3, "Tweet 3");

		feed.addLast(tweet1);
		feed.addLast(tweet2);
		feed.addLast(tweet3);

		// Get the first tweet from the feed using the private helper method
		Tweet firstTweet = getFirstTweet(feed);

		// Create an iterator starting from the first tweet
		ChronoTwiterator chronoTwiterator = new ChronoTwiterator(new TweetNode(firstTweet));

		// Iterate through the ChronoTwiterator and compare tweets with the original
		// feed
		int i = 0;
		while (chronoTwiterator.hasNext()) {
			Tweet tweet = chronoTwiterator.next();

			// Compare the tweet from ChronoTwiterator with the corresponding tweet in the
			// original feed
			if (!tweet.equals(feed.get(i))) {
				System.out.println("Expected tweet:");
				System.out.println(feed.get(i));
				System.out.println("Actual tweet:");
				System.out.println(tweet);
				return false;
			}
			i++;
		}

		return true;
	}

	/**
	 * Tests the VerifiedTwiterator class by iterating through the verified tweets
	 * in the feed.
	 * 
	 * @return true if the VerifiedTwiterator test passes, otherwise false.
	 */
	public static boolean testVerifiedTwiterator() {
		TwitterFeed feed = new TwitterFeed();
		User user1 = new User("user1");
		User user2 = new User("user2");
		User user3 = new User("user3");

		Tweet tweet1 = new Tweet(user1, "Tweet 1");
		Tweet tweet2 = new Tweet(user2, "Tweet 2");
		Tweet tweet3 = new Tweet(user3, "Tweet 3");

		feed.addLast(tweet1);
		feed.addLast(tweet2);
		feed.addLast(tweet3);

		feed.setMode(TimelineMode.VERIFIED_ONLY);

		for (Tweet tweet : feed) {
			if (!tweet.isUserVerified()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Tests the RatioTwiterator class by iterating through the tweets with a
	 * minimum like ratio.
	 * 
	 * @return true if the RatioTwiterator test passes, otherwise false.
	 */
	public static boolean testRatioTwiterator() {
		TwitterFeed feed = new TwitterFeed();
		User user1 = new User("user1");
		User user2 = new User("user2");
		User user3 = new User("user3");

		Tweet tweet1 = new Tweet(user1, "Tweet 1");
		tweet1.like(); // Increase likes for ratio
		tweet1.retweet(); // Increase retweets for ratio

		Tweet tweet2 = new Tweet(user2, "Tweet 2");
		tweet2.like(); // Increase likes for ratio

		Tweet tweet3 = new Tweet(user3, "Tweet 3");
		tweet3.retweet(); // Increase retweets for ratio

		feed.addLast(tweet1);
		feed.addLast(tweet2);
		feed.addLast(tweet3);

		feed.setMode(TimelineMode.LIKE_RATIO);

		for (Tweet tweet : feed) {
			if (tweet.getLikesRatio() < 0.5) { // Assuming the ratio threshold is 0.5
				return false;
			}
		}
		return true;
	}

	/**
	 * The main method of the TwiteratorTester class. Calls various test methods and
	 * prints the results.
	 * 
	 * @param args Command-line arguments (not used in this program).
	 */
	public static void main(String[] args) {
		// Call setCalendar before using TwitterFeed
		Tweet.setCalendar(Calendar.getInstance());

		System.out.println("testUser(): " + testUser());
		System.out.println("testNode(): " + testNode());
		System.out.println("testAddTweet(): " + testAddTweet());
		System.out.println("testInsertTweet(): " + testInsertTweet());
		System.out.println("testDeleteTweet(): " + testDeleteTweet());
		System.out.println("testChronoTwiterator(): " + testChronoTwiterator());
		System.out.println("testVerifiedTwiterator(): " + testVerifiedTwiterator());
		System.out.println("testRatioTwiterator(): " + testRatioTwiterator());
	}
}