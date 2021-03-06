package com.softserve.edu.greencity.rest.dto;

public enum ContentTypes {

	APPLICATION_JSON("application/json"),
	TEXT_HTML("text/html"),
	TEXT_JSON("text/json"),
	MULTIPART_FORM_DATA("multipart/form-data"),
	IMAGE_JPEG("image/jpeg"),
	ALL_TYPES("*/*");

    private String type;

    private ContentTypes(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        //return String.valueOf(key);
    	return type;
    }
}
