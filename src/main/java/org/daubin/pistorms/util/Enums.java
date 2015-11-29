package org.daubin.pistorms.util;

import java.util.Collections;
import java.util.Map;

import com.google.common.base.Supplier;
import com.google.common.collect.Maps;

public class Enums {
	
	private Enums() { }

	public static <K,V extends Supplier<K>> Map<K, V> getMap(V[] values) {
		Map<K,V> map = Maps.newHashMap();
		
		for (V v : values) {
			map.put(v.get(), v);
		}
		
		return Collections.unmodifiableMap(map);
	}
}
