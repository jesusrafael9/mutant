package ar.com.jc.repository;

import ar.com.jc.model.Human;

public interface DnaRepository {
	
	public void insertIntoDynamoDB(Human human);

	
	public Human getOneHuman(String id);

	
	public long getCant(Integer t);
}
