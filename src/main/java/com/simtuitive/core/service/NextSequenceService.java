package com.simtuitive.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.simtuitive.core.model.CustomSequences;



@Service
public class NextSequenceService {
	@Autowired
	private MongoOperations mongo;

	public String getNextSequence(String seqName) {
		Query query = new Query(Criteria.where("_id").is(seqName));
		Update update = new Update();
		update.inc("seq", 1);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		CustomSequences seqId = mongo.findAndModify(query, update, options, CustomSequences.class);
		return seqId.getId();
	}
}
