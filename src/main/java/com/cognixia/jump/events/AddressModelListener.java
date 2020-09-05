package com.cognixia.jump.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import com.cognixia.jump.model.Address;
import com.cognixia.jump.service.SequenceGeneratorService;

@Component
public class AddressModelListener extends AbstractMongoEventListener<Address> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public AddressModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Address> event) {
    	event.getSource().setId(sequenceGenerator.generateSequence(Address.SEQUENCE_NAME));
    }
}