package com.americanexpress.taxcalculator;

import com.americanexpress.taxcalculator.entities.events.Event;
import com.squareup.otto.Bus;

import org.junit.Test;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class EventBusTest {

    @Test
    public void testRegister() {
        Bus bus = mock(Bus.class);
        EventBus eventBus = new EventBus(bus);

        eventBus.register(null);
        verify(bus, never()).register(anyObject());

        eventBus.register(mock(Object.class));
        verify(bus).register(anyObject());
    }

    @Test
    public void testUnregister() {
        Bus bus = mock(Bus.class);
        EventBus eventBus = new EventBus(bus);

        eventBus.unregister(null);
        verify(bus, never()).unregister(anyObject());

        eventBus.unregister(mock(Object.class));
        verify(bus).unregister(anyObject());
    }

    @Test
    public void testPost() {
        Bus bus = mock(Bus.class);
        EventBus eventBus = new EventBus(bus);

        eventBus.post(null);
        verify(bus, never()).post(anyObject());

        eventBus.post(mock(Event.class));
        verify(bus).post(anyObject());
    }
}