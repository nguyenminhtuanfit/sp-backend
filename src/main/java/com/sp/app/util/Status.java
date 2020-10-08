package com.sp.app.util;

public enum Status {
	OLD("Old"),
	UNUSED("Unused"),
	SOLD("Sold");
	
	private String text;

	Status(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Status fromString(String text) {
        for (Status status : Status.values()) {
            if (status.text.equalsIgnoreCase(text)) {
                return status;
            }
        }
        return null;
    }
}
