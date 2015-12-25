package org.ametiste.utils.common.comparator;

import java.util.Comparator;
import java.util.Map;

/**
 * generic class, that is parametrized with 2 types, basically same that map we are going to sort - key type and value
 * type. MapValueComparator accepts generic object of ValueComparator interface and map that requires to be sorted as
 * constructor arguments. MapValueComparator cant have deal with null values so you need to guarantee that map doesnt
 * have null values If both keys dont exist in map set up in constructor, compare method returns 0 If 1st comparable key
 * doesnt exist, comparator assumes second is bigger and returns -1 If 2nd comparable key doesnt exist, comparator
 * assumes 1st is bigger and returns 1 Done for avoiding null pointer exceptions during comparator work
 * 
 */
public class MapValueComparator<T, V> implements Comparator<T> {

	private final Map<T, V> base;
	private final ValueComparator<V> valueComparator;

	public MapValueComparator(Map<T, V> base, ValueComparator<V> valueComparator) {
		this.valueComparator = valueComparator;
		if (base == null)
			throw new IllegalArgumentException("Base map cant be null");

		this.base = base;
	}

	// Note: this comparator imposes orderings that are inconsistent with
	// equals.
	@Override
	public int compare(T a, T b) {
		if (!base.containsKey(a)) {
			if (!base.containsKey(b))
				return 0;
			else
				return -1;
		} else {
			if (!base.containsKey(b))
				return 1;
			else {
				if (valueComparator.compare(base.get(a), base.get(b)) > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		} // returning 0 would merge keys
	}

}