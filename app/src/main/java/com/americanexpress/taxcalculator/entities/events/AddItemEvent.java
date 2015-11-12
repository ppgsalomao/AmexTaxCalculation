package com.americanexpress.taxcalculator.entities.events;

public class AddItemEvent implements Event {

    private String input;

    public AddItemEvent(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }
}
