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
// https://canvas.wisc.edu/courses/355989/files/33161195?wrap=1
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.NoSuchElementException;
/**
* An enum representing the three states of the timeline:
* - Chronological (in decreasing time order)
* - Verified only (only tweets from verified users, in decreasing time order)
* - High engagement (only tweets with a total engagement level over a given
threshold, in
* decreasing time order)
*/
public enum TimelineMode {
CHRONOLOGICAL, VERIFIED_ONLY, LIKE_RATIO;
}
