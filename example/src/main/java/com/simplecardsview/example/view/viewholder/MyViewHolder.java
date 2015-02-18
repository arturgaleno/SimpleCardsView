package com.simplecardsview.example.view.viewholder;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.simplecardsview.example.R;
import com.simplecardsview.example.domain.MyEntity;
import com.simplecardsview.model.Entity;
import com.simplecardsview.view.ViewHolder;

/**
 * Created by artur on 03/09/14.
 */
public class MyViewHolder implements ViewHolder {

     View parent;

     Entity entity;

     TextView txtvwHeader,
             txtvwExpand;

    private Button button;

    private Button button2;

    private CheckBox checkBox2;

    @Override
    public void setEntity(Entity entity) {
        this.entity = entity;
        txtvwHeader.setText(((MyEntity) entity).getValue1());
        txtvwExpand.setText(((MyEntity) entity).getValue2());
    }

    @Override
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public void instanciate() {

        txtvwHeader = (TextView) parent.findViewById(R.id.txtvwHeaderValue1);

        txtvwExpand = (TextView) parent.findViewById(R.id.txtvwExpandValue3);

        button = (Button) parent.findViewById(R.id.button);

        button2 = (Button) parent.findViewById(R.id.button2);

        checkBox2 = (CheckBox) parent.findViewById(R.id.checkBox2);
    }

    @Override
    public void setParent(View parent) {
        this.parent = parent;
    }

    public View getParent() {
        return parent;
    }

    public TextView getTxtvwHeader() {
        return txtvwHeader;
    }

    public void setTxtvwHeader(TextView txtvwHeader) {
        this.txtvwHeader = txtvwHeader;
    }

    public TextView getTxtvwExpand() {
        return txtvwExpand;
    }

    public void setTxtvwExpand(TextView txtvwExpand) {
        this.txtvwExpand = txtvwExpand;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Button getButton2() {
        return button2;
    }

    public void setButton2(Button button2) {
        this.button2 = button2;
    }

    public CheckBox getCheckBox2() {
        return checkBox2;
    }

    public void setCheckBox2(CheckBox checkBox2) {
        this.checkBox2 = checkBox2;
    }
}
