package com.simplecardsview.example.domain;

import com.simplecardsview.model.Entity;

/**
 * Created by artur on 03/09/14.
 */

public class MyEntity implements Entity {

    private String  value1, value2;

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }
}
