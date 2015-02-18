package com.simplecardsview.model;

import java.io.Serializable;

/**
 * Created by artur on 03/09/14.
 */
public class Entity implements Serializable {

    private boolean expanded;

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}
