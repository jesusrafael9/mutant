package ar.com.jc.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.com.jc.entities.Dna;
import ar.com.jc.entities.HumanBuilder;
import ar.com.jc.exception.BadRequestException;
import ar.com.jc.exception.ForbiddenException;
import ar.com.jc.model.Human;
import ar.com.jc.repository.DnaRepositoryImpl;


@RestController
@RequestMapping("/")
public class MutantDetect{
	
	@Autowired
	private DnaRepositoryImpl repository;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Integer IS_MUTANT = 1; 
	private final Integer NOT_MUTANT = 0;
	
	@RequestMapping(value = "/mutant", method =  RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
    public void verify(@RequestBody(required=false) Dna dna) {
		
		boolean isMutant; 
		if (dna == null || dna.getDna().isEmpty() || dna.generateId().equals("") || checkExistDna(dna.generateId()) ) {
			logger.info("El dna es invalido o ya existe: "+dna.getDna());
			throw new BadRequestException();
		}
		isMutant = Detect.instance().isMutant(dna.getDna().toArray(new String[dna.getDna().size()])); 
		 
        Human human = new HumanBuilder().id(dna.generateId()).dna(dna.getDna().toString()).isMutant(isMutant).build();
 		repository.insertIntoDynamoDB(human);
 		
 		if (!isMutant){
        	throw new ForbiddenException(); 
        }
	}
	
	@RequestMapping(value="/stats", method = RequestMethod.GET)
	public ResponseEntity<String> getStats() {
		return new ResponseEntity<String>(builderStats(), HttpStatus.OK);
	}
	
	private String builderStats() {
		return  "{'count_mutan_dna':" + repository.getCant(IS_MUTANT) +
				", 'count_human_dna':" + repository.getCant(NOT_MUTANT)  +
				", 'ratio':" + ( (double) repository.getCant(IS_MUTANT) / repository.getCant(NOT_MUTANT)) + "}";
	}
	
	private boolean checkExistDna(String id) {
		Human human = repository.getOneHuman(id); 
		return human != null;
	}
}