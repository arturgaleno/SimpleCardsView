package com.simplecardsview.example.view;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
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

        List<Entity> pedidos = new ArrayList<Entity>();

        for (int i = 0; i < 100; i++) {
            MyEntity myEntity = (MyEntity) EntityFactory.getInstance(MyEntity.class);

            myEntity.setValue1("An Example");
            myEntity.setValue2("An Example");

            pedidos.add(myEntity);
        }

        cardAdapter = new CardAdapter(this.getActivity(), R.layout.mycard, pedidos, MyViewHolder.class);

        setListAdapter(cardAdapter);

    }


}
