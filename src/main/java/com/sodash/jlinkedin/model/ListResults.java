package com.sodash.jlinkedin.model;

import java.util.ArrayList;

/**
 * A List of returned results -- which may have other pages of info!
 * @author daniel
 *
 */
public class ListResults<X> extends ArrayList<X> {

	private static final long serialVersionUID = 1L;
	private int total;

	public void setTotal(int total) {
		this.total = total;
	}
	
	public int getTotal() {
		return total;
	}

}
