package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

	// 1. VERSIONING THRU URL
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPersonURL() {
		return new PersonV1("Bob Charlie");
	}

	// 1. VERSIONING THRU URL
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPersonURL() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// 2. VERSIONING THRU REQUEST PARAMS
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParam() {
		return new PersonV1("Bob Charlie");
	}

	// 2. VERSIONING THRU REQUEST PARAMS
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParam() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
//
//	// 3. VERSIONING THRU REQUEST HEADER
//	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
//	public PersonV1 getFirstVersionOfPersonRequestHeader() {
//		return new PersonV1("Bob Charlie");
//	}
//
//	// 3. VERSIONING THRU REQUEST HEADER
//	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
//	public PersonV2 getSecondVersionOfPersonRequestHeader() {
//		return new PersonV2(new Name("Bob", "Charlie"));
//	}

	// 4. VERSIONING THRU REQUEST HEADER
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonMediaType() {
		return new PersonV1("Bob Charlie");
	}

	// 4. VERSIONING THRU REQUEST HEADER
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonMediaType() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
}
