package com.cognixia.jump.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.cognixia.jump.model.Review;
import com.cognixia.jump.service.SequenceGeneratorService;

@Component
public class ReviewModelListener extends AbstractMongoEventListener<Review> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public ReviewModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Review> event) {
    	event.getSource().setId(sequenceGenerator.generateSequence(Review.SEQUENCE_NAME));
    }
}
