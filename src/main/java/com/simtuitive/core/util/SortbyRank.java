package com.simtuitive.core.util;

import java.util.Comparator;

import com.simtuitive.core.model.Permissions;

public class SortbyRank implements Comparator<Permissions> {

	@Override
	public int compare(Permissions o1, Permissions o2) {
		// TODO Auto-generated method stub
		return o1.getRank()-o2.getRank();
	}

}
