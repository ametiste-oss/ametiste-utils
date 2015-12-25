package org.ametiste.utils.string;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class VerifiedStringTest {

	private VerifiedString verifiedString;

	@Before
	public void setUp() {
		verifiedString = VerifiedString.trim("some string?   with this \" :   ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testVerifyStringWithNull() {
		VerifiedString.trim(null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testVerifyStringStopWordsWithNull() {
		VerifiedString.trimStopWords(null);

	}

	@Test(expected = IllegalArgumentException.class)
	public void testVerifyStringWithEmpty() {
		VerifiedString.trim("");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testVerifyStringWithStopWordsEmpty() {
		VerifiedString.trimStopWords("");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testVerifyStringWithLongString() {
		VerifiedString.trim("qweqweqweqweqweqweqweqweqweqweqweqweqweqweqweqweqweqweqweqweqweqweqw");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testVerifyStringWithTooMuchKeywords() {
		VerifiedString
				.trim(
				"q w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w w ");

	}

	@Test
	public void testGetString() {
		assertEquals("some string? with this", verifiedString.getString());
	}

	@Test
	public void testGetKeywords() {
		List<String> keywords = Arrays.asList("some", "string?", "with", "this");
		assertEquals(keywords, verifiedString.getKeywords());
	}

	@Test
	public void testGetStringWithUtf8String() {
		VerifiedString verS = VerifiedString.trim("белый {} сцуко?  // \"кот");
		assertEquals("белый сцуко? кот", verS.getString());
	}

	@Test
	public void testGetStringWithStopWords() {
		verifiedString = VerifiedString.trimStopWords(" the some string?   with a this \" :   ");
		assertEquals("some string? with this", verifiedString.getString());
	}

}
