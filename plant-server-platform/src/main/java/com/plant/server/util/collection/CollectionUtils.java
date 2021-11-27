package com.plant.server.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.list.SetUniqueList;

public class CollectionUtils {
	
	public static <T> Set<T> safeInit(Set<T> set) {
		Set<T> newSet = set;
		if (newSet==null) {
			newSet = new HashSet<T>();
		}
		return newSet;
	}
	public static <T> Set<T> nullSafeAdd(Set<T> set, T element) {
		Set<T> newSet = safeInit(set);
		newSet.add(element);
		return newSet;
	}

	public static <T> List<T> safeInit(List<T> list) {
		List<T> newList = list;
		if (newList==null) {
			newList = new ArrayList<T>();
		}
		return newList;
	}

	public static <T> List<T> nullSafeAdd(List<T> list, T element) {
		List<T> newList = safeInit(list);
		newList.add(element);
		return newList;
	}

	public static <T>boolean isEmpty(Collection<T> col) {
		return org.apache.commons.collections4.CollectionUtils.isEmpty(col);
	}

	public static <T>List<T> unique(List<T> list) {
		return list==null?new ArrayList<T>():SetUniqueList.setUniqueList(list);
	}

	public static boolean setsAreEqual(Set<?> set1, Set<?> set2) {
        if (set1 == null && set2 ==null){
        	return true;
        }
        if (set1 == null || set2 ==null){
            return false;
        }
        if(set1.size()!=set2.size()){
            return false;
        }
        return set1.containsAll(set2);
	}
}
