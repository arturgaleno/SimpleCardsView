package com.simplecardsview.example.view;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.simplecardsview.adapter.CardAdapter;
import com.simplecardsview.example.R;
import com.simplecardsview.example.domain.EntityFactory;
import com.simplecardsview.example.domain.MyEntity;
import com.simplecardsview.example.view.viewholder.MyViewHolder;
import com.simplecardsview.model.Entity;
import com.simplecardsview.view.Card;
import com.simplecardsview.view.ViewHolder;

import java.util.ArrayList;
import java.util.List;


public class MyListFragment extends ListFragment {

    private CardAdapter cardAdapter;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(v.getContext(), "Item: " + position + " clicked!", Toast.LENGTH_SHORT).show();
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_card_list, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Entity> entities = new ArrayList<Entity>();

        for (int i = 0; i < 100; i++) {
            MyEntity myEntity = (MyEntity) EntityFactory.getInstance(MyEntity.class);

            myEntity.setValue1("An Example");
            myEntity.setValue2("An Example");

            entities.add(myEntity);
        }

        cardAdapter = new CardAdapter(this.getActivity(), R.layout.mycard, entities, MyViewHolder.class);

        cardAdapter.setConfigureListener(new CardAdapter.ConfigureListener() {
            @Override
            public void onConfigure(Card card, Entity entity, ViewHolder viewHolder, int position, boolean recycling) {

                Animation animation = AnimationUtils.makeInAnimation(getActivity(), true);
                animation.setStartOffset((position % 10)*75);
                card.startAnimation(animation);

                MyViewHolder myViewHolder = (MyViewHolder) viewHolder;

                if (position % 2 == 0) {
                    myViewHolder.getTxtvwHeader().setTextColor(getResources().getColor(R.color.status_example));
                } else {
                    myViewHolder.getTxtvwHeader().setTextColor(getResources().getColor(R.color.card_expand));
                }

                myViewHolder.getButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), "Hi, I was clicked!", Toast.LENGTH_SHORT).show();
                    }
                });

                myViewHolder.getButton2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(view.getContext(), "Hi, I was clicked!", Toast.LENGTH_SHORT).show();
                    }
                });

                myViewHolder.getCheckBox2().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (((CheckBox) view).isChecked())
                            Toast.makeText(view.getContext(), "Hi, I was checked!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(view.getContext(), "Hi, I was unchecked!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        setListAdapter(cardAdapter);

    }


}
