package ar.com.jc.entities;
import java.util.Base64;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Dna{

	public Dna() {}
	
	private List<String> dna = null;

	public List<String> getDna() {
		return dna;
	}

	public void setDna(List<String> dna) {
		this.dna = dna;
	} 
	
	public String generateId(){
		//TODO Mejorar el metodo de explotacion de id se puede utilizar sha256
		return Base64.getEncoder().encodeToString(dna.toString().replaceAll("[^ATCG]", "").getBytes()).toString();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("dna", dna).toString();
	}
}
