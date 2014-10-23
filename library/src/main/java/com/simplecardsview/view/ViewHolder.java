package com.simplecardsview.view;

import android.view.View;

import com.simplecardsview.model.Entity;


/**
 * Created by artur on 03/09/14.
 */
public interface ViewHolder {

    void setEntity(Entity entity);

    void instanciate();

    void setParent(View parent);

}
