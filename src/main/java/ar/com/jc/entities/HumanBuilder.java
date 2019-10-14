package ar.com.jc.entities;

import ar.com.jc.model.Human;

public class HumanBuilder {
	
	private String id;
	private String dna;
	private Boolean isMutant;
	
	public HumanBuilder() {
	}
	
	public HumanBuilder id(String id) {
		this.id = id;
		return this;
	}
	
	public HumanBuilder dna(String dna) {
		this.dna = dna;
		return this;
	}
	
	public HumanBuilder isMutant(Boolean isMutant) {
		this.isMutant = isMutant;
		return this;
	}
	
	public Human build() {
		return new Human(this);
	}
	
	public String getId() {
		return id;
	}
	
	public String getDna() {
		return dna;
	}
	
	public Boolean getIsMutant() {
		return isMutant;
	}
}
