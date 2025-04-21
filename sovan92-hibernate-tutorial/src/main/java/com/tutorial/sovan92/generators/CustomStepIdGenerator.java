package com.tutorial.sovan92.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.EventType;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.aot.generate.GenerationContext;


import java.util.EnumSet;
import java.util.concurrent.atomic.AtomicLong;

public class CustomStepIdGenerator implements IdentifierGenerator {

    private static final AtomicLong sequence = new AtomicLong(1000); // Start value
    private static final long STEP = 100;

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(EventType.INSERT);
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return Math.random() * STEP + sequence.getAndAdd(STEP);
    }
}
