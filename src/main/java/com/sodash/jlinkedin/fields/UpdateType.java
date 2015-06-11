package com.sodash.jlinkedin.fields;

public enum UpdateType {
	JobPosting("job-posting"),
	NewProduct("new-product"),
	StatusUpdate("status-update");
	
	private final String value;
	
    UpdateType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }
}
