package com.cognixia.jump.repository.custom;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cognixia.jump.model.Restaurant;

public class RestaurantRepositoryImpl implements RestaurantRepositoryCustomUpdate{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public Restaurant patch(Long id, String field, Object value) {
		Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set(field, value);
        return mongoTemplate.findAndModify(query, update, options().returnNew(true).upsert(true), Restaurant.class);
	}
}
