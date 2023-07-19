package com.spring.restful.webservices.springrestfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.restful.webservices.springrestfulwebservice.model.Name;
import com.spring.restful.webservices.springrestfulwebservice.model.PersonV1;
import com.spring.restful.webservices.springrestfulwebservice.model.PersonV2;

@RestController
public class VersionPersonController {
	
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionpersonDetails() {
		return new PersonV1("Bob Charlie");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionpersonDetails() {
		return new PersonV2(new Name("Bob","Charlie"));
	}
	
	@GetMapping(path="/person", params="version=1")
	public PersonV1 getParamVersionpersonDetails() {
		return new PersonV1("Bob Charlie");
	}
	
	 
	@GetMapping(path="/person/header", headers="X-API-VERSION=1")
	public PersonV1 getParamVersionRequestHeaser() {
		return new PersonV1("Bob Charlie");
	}
	
	 
		@GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json")
		public PersonV1 getParamVersionAcceptMedia() {
			return new PersonV1("Bob Charlie");
		}
		
	

}
