
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
// https://cs300-www.cs.wisc.edu/wp/wp-content/uploads/2020/12/sp2023/p7/javadocs/User.html
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;

/**
 * Constructs a new User object with the specified username and sets the user's
 * verification status to false (unverified) by default.
 * 
 * @param username The username of the Twitter user. It should be a non-null and
 *                 non-empty string.
 * @throws IllegalArgumentException If the provided username is null or empty.
 */
public class User {
	private boolean isVerified;
	private String username;

	/**
	 * Constructs a new User object with the specified username and sets the user's
	 * verification status to false (unverified) by default.
	 * 
	 * @param username The username of the Twitter user. It should be a non-null and
	 *                 non-empty string.
	 * @throws IllegalArgumentException If the provided username is null or empty.
	 */
	public User(String username) {
		this.username = username;
		this.isVerified = false; // Default value for isVerified
	}

	/**
	 * Returns the username of the Twitter user.
	 * 
	 * @return The username as a string.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Checks whether the Twitter user is verified.
	 * 
	 * @return true if the user is verified, false otherwise.
	 */
	public boolean isVerified() {
		return isVerified;
	}

	/**
	 * Revokes the verification status of the Twitter user. Sets the user's
	 * verification status to false (unverified).
	 */
	public void revokeVerification() {
		isVerified = false;
	}

	/**
	 * Verifies the Twitter user. Sets the user's verification status to true
	 * (verified).
	 */
	public void verify() {
		isVerified = true;
	}

	/**
	 * Returns a string representation of the Twitter user. If the user is verified,
	 * the representation includes an asterisk (*) at the end of the username,
	 * indicating the verified status.
	 * 
	 * @return A string representing the Twitter user in the format "@username" for
	 *         unverified users and "@username*" for verified users.
	 */
	@Override
	public String toString() {
		return isVerified ? "@" + username + "*" : "@" + username;
	}
}
