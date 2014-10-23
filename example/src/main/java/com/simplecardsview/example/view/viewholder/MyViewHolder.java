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

     TextView txtvwHeader,
             txtvwExpand;

    private Button button;

    private Button button2;

    private CheckBox checkBox2;

    @Override
    public void setEntity(Entity entity) {

        txtvwHeader.setText(((MyEntity) entity).getValue1());
        txtvwExpand.setText(((MyEntity) entity).getValue2());
    }

    @Override
    public void instanciate() {

        txtvwHeader = (TextView) parent.findViewById(R.id.txtvwHeaderValue1);

        txtvwExpand = (TextView) parent.findViewById(R.id.txtvwExpandValue3);

        button = (Button) parent.findViewById(R.id.button);

        button2 = (Button) parent.findViewById(R.id.button2);

        checkBox2 = (CheckBox) parent.findViewById(R.id.checkBox2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Hi, I was clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Hi, I was clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked())
                    Toast.makeText(view.getContext(), "Hi, I was checked!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(view.getContext(), "Hi, I was unchecked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setParent(View parent) {
        this.parent = parent;
    }

    public View getParent() {
        return parent;
    }
}
