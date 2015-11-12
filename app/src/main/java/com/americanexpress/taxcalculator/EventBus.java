package com.americanexpress.taxcalculator;

import android.support.annotation.VisibleForTesting;

import com.americanexpress.taxcalculator.entities.events.Event;
import com.squareup.otto.Bus;

public class EventBus {
    static volatile EventBus singleton = null;

    private Bus bus;

    @VisibleForTesting
    EventBus(Bus bus) {
        this.bus = bus;
    }

    private EventBus() {
        super();
        this.bus = new Bus();
    }

    public static EventBus getInstance() {
        if (singleton == null) {
            synchronized (PresenterHolder.class) {
                if (singleton == null) {
                    singleton = new EventBus();
                }
            }
        }
        return singleton;
    }

    public void register(Object object) {
        if(object != null)
            this.bus.register(object);
    }

    public void unregister(Object object) {
        if(object != null)
            this.bus.unregister(object);
    }

    public void post(Event event) {
        if(event != null)
            this.bus.post(event);
    }
}
