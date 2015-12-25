package org.ametiste.utils.common.comparator;

/**
 * interface has only one method compare() with two generic arguments. ValueComparator object that passed to
 * MapValueComparator constructor should have same generic type that map value.
 * 
 */
public interface ValueComparator<T> {
	int compare(T value1, T value2);

}
