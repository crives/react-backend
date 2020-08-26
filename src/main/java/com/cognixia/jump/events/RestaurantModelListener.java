package com.cognixia.jump.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.cognixia.jump.model.Restaurant;
import com.cognixia.jump.service.SequenceGeneratorService;

@Component
public class RestaurantModelListener extends AbstractMongoEventListener<Restaurant> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public RestaurantModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Restaurant> event) {
    	event.getSource().setId(sequenceGenerator.generateSequence(Restaurant.SEQUENCE_NAME));
    }
}
