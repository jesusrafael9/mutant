package ar.com.jc.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DetectTest {
	
	String[] dnaOblicua = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCTTA","TCACTG"};
	String[] dnaHV =      {"AAAAGC","CAGTGC","TTCTCT","AGAATG","CCGCTA","TCACTG"};
	
	@Test
	public void testIsNotMutantOblicua() {
		boolean rs = Detect.instance().isMutant(dnaOblicua);
		assertTrue("oblicua",rs);
		
	}
	
	@Test
	public void testIsMutantHorizontal(){
		boolean rs = Detect.instance().isMutant(dnaHV);
		assertTrue("Horizontal / Vertical", rs);
	}
}
