package com.improving.sfgpetclinic;

import com.improving.sfgpetclinic.models.Visit;
import com.improving.sfgpetclinic.services.map.VisitServiceMap;
import com.improving.sfgpetclinic.services.springdatajpa.VisitJPAService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//scans everything in this package (com.improving.sfgpetclinic) and each package within it
public class SfgPetClinicApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(SfgPetClinicApplication.class, args);

	}

}
