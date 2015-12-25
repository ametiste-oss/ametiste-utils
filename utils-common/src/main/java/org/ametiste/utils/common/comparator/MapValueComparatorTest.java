package org.ametiste.utils.common.comparator;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapValueComparatorTest {

	public static void main(String[] args) {

		Map<Integer, String> map = new HashMap<>();
		map.put(1, "apple");
		map.put(2, "zipper");
		map.put(3, "nearest");
		map.put(4, "bubbles");
		map.put(5, "bubbles");
		System.out.println("Before:");
		System.out.println(map);

		MapValueComparator<Integer, String> comparator = new MapValueComparator<>(map,
				new CommonValueComparator<>());

		TreeMap<Integer, String> sortedMap = new TreeMap<>(comparator);
		sortedMap.putAll(map);
		System.out.println("After:");
		System.out.println(sortedMap);
	}

}
