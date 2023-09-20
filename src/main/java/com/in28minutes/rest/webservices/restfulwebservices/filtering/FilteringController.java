package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	/*
	 * Filtering is done to ignore properties that we do not want to send in the response body.
	 * Dynamic Filtering is done on the REST API.
	 * Although, add @JsonFilter("SomeBeanFilter") on bean declaration.
	 * 
	 * */
	
	@GetMapping("/filtering") // do not return filed2
	public MappingJacksonValue filtering() {
		
		// 1.
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
		// 2.
		// MappingJacksonValue allows you to add serialization logic in addition to your data.
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		// 3.
		// To be able to add this serialization logic, we will need filters.
		// Here, we are using SimpleBeanPropertyFilter as a filter. 
		// We want to allow only field1 & field3 in the response.
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		
		// 4.
		// We are creating a filter provider defining our specific filter.
		// This filter should be used on Bean declaration.
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		// 5.
		// Once we have the filter ready, we are setting the filter into our mappingJacksonValue.
		mappingJacksonValue.setFilters(filters);
		
		// Here we have the bean as well as the serialization logic back
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-list") // only return field2 & field3
	public MappingJacksonValue filteringList() {
		
		// 1. extract into a local variable
		List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value4", "value7", "value6"),
				new SomeBean("value1", "value8", "value9"));
		
		// 2. make a mappingJacksonValue object
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		// 3. create a filter
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		
		// 4. create a filterProvider
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		
		// 5. set it into the mappingJacksonValue
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
	
}
