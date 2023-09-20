package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * Filtering is done to ignore properties that we do not want to send in the response body.
 * Static Filtering is done on the bean
 * You can do it on two levels. 
 * 1. Class level with @JsonIgnoreProperties
 * 2. Field level with @JsonIgnore
 * 
 * */
// @JsonIgnoreProperties({"field1", "field2"})

@JsonFilter("SomeBeanFilter")
public class SomeBean {

	private String field1;
	
	// @JsonIgnore	// now, field2 will not be there in the response body. (hit /filtering and check)
	private String field2;
	private String field3;

	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}

	public String getField1() {
		return field1;
	}

	public String getField2() {
		return field2;
	}

	public String getField3() {
		return field3;
	}

	@Override
	public String toString() {
		return "someBean [field1=" + field1 + ", field2=" + field2 + ", field3=" + field3 + "]";
	}

}
