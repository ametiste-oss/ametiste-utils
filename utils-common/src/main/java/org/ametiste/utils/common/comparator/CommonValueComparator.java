package org.ametiste.utils.common.comparator;

/**
 * implementation of ValueComparator with Comparable objects, most likely in common situations this one implementation
 * can be used. In case if you need some special comparation, you should make your own implementation of ValueComparator
 * 
 * @param <T>
 */
public class CommonValueComparator<T extends Comparable<T>> implements ValueComparator<T> {

	@Override
	public int compare(T value1, T value2) {
		return value1.compareTo(value2);
	}

}
