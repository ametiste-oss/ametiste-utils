package org.ametiste.utils.string;

import java.util.Iterator;
import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

public class VerifiedString {

	private final static String REGEXP_TO_CUT = "[^\\p{L}0-9.? -]";
    private final static String ARTICLES_TO_CUT = "(((A|a)n?)|((T|t)he))\\s";
	private final static String REPLACE_SYMBOL = "";
	private final static int MAX_WORD_LENGTH = 40;
	private final static int MAX_KEYWORDS_COUNT = 40;
	private final String query;
	private final List<String> keywordsList;
	private static final Splitter MY_SPLITTER = Splitter.on(' ').trimResults().omitEmptyStrings();

	private VerifiedString(String query) {

		this.query = query;

		if (this.query.length() > MAX_KEYWORDS_COUNT * MAX_WORD_LENGTH) {
			throw new IllegalArgumentException("String is too long");
		}
		Iterable<String> splittedKeywords = MY_SPLITTER.split(this.query);
		Iterator<String> iterator = splittedKeywords.iterator();
		while (iterator.hasNext()) {
			String keyword = iterator.next();
			if (keyword.length() > MAX_WORD_LENGTH) {
				throw new IllegalArgumentException("One of keywords length is too long");
			}

		}
		this.keywordsList = Lists.newArrayList(splittedKeywords);
		if (keywordsList.size() > MAX_KEYWORDS_COUNT) {
			throw new IllegalArgumentException("Too many keywords");
		}
	}

	public static VerifiedString trim(String query) {

		checkEmptyString(query);
		String trimmedQuery = query.replaceAll(REGEXP_TO_CUT, REPLACE_SYMBOL).trim().replaceAll(" +", " ");

		return new VerifiedString(trimmedQuery);
	}

	public static VerifiedString trimStopWords(String query) {
		checkEmptyString(query);

		String trimmedQuery = query.replaceAll(REGEXP_TO_CUT, REPLACE_SYMBOL).trim().replaceAll(" +", " ")
				.replaceAll(ARTICLES_TO_CUT, REPLACE_SYMBOL);

		return new VerifiedString(trimmedQuery);
	}

	private static void checkEmptyString(String query) {

		if (query == null || query.isEmpty())
			throw new IllegalArgumentException("Query shouldnt be null or empty");
	}

	public String getString() {
		return this.query;

	}

	public List<String> getKeywords() {
		return this.keywordsList;
	}

}
