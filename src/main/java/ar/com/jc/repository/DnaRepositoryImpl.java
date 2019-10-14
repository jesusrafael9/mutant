package ar.com.jc.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import ar.com.jc.model.Human;

@Repository
public class DnaRepositoryImpl implements DnaRepository{

	@Autowired
	private DynamoDBMapper mapper;
	
	public void insertIntoDynamoDB(Human human) {
		mapper.save(human);
	}

	public Human getOneHuman(String id) {
		return mapper.load(Human.class, id);
	}
	
	public long getCant(Integer t) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue> ();
		eav.put(":status", new AttributeValue().withN(t.toString()));
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
			    .withFilterExpression("isMutant = :status")
			    .withExpressionAttributeValues(eav); 
		return  mapper.count(Human.class, scanExpression);
	}
}

