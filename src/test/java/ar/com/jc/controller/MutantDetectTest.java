package ar.com.jc.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ar.com.jc.repository.DnaRepositoryImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MutantDetect.class, secure = false)
public class MutantDetectTest {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@MockBean
	private DnaRepositoryImpl repository;
	
	@Autowired
	private MockMvc mockMvc;
	
	String humanDNA = "{\"dna\":[\"AATCGG\",\"TTGACG\",\"CGTTAA\",\"AGTCGT\",\"AGTCCG\",\"CTGCAC\"]}";
	String mutantDNA = "{\"dna\":[\"AATCGG\",\"TTGACG\",\"CGTTAA\",\"AGTCGT\",\"AGTCCG\",\"CGGCAC\"]}";
	String errorDNA = "{\"dna\":[\"test\"]}";

	
	@Test
	public void testVerifyMutant() throws Exception  {
		logger.info("Inicia test vericar mutante...");
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/mutant")
				.accept(MediaType.APPLICATION_JSON).content(mutantDNA)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals("Es mutante",HttpStatus.OK.value(), response.getStatus()) ;
	}
	
	@Test
	public void testVerifyNotMutant() throws Exception  {
		logger.info("Inicia test vericar humano...");
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/mutant")
				.accept(MediaType.APPLICATION_JSON).content(humanDNA)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals("No es mutante",HttpStatus.FORBIDDEN.value(), response.getStatus());
	}
	
	@Test
	public void testVerifyError() throws Exception  {
		logger.info("Inicia test de error DNA...");
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/mutant")
				.accept(MediaType.APPLICATION_JSON).content(errorDNA)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals("Request con error",HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	@Test
	public void testStats() throws Exception{
		logger.info("Inicia test estadisticas...");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/stats");
		MvcResult rs = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = rs.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
}