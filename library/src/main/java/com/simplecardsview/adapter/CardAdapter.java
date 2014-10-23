package com.simplecardsview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.simplecardsview.model.Entity;
import com.simplecardsview.view.Card;
import com.simplecardsview.view.CardHeader;
import com.simplecardsview.view.ViewHolder;

import java.util.List;


public class CardAdapter extends ArrayAdapter<Entity> {

    private Context context;
    private Class viewHolderClaz;
    private int resource;

    public CardAdapter(Context context, int resource, List<Entity> objects, Class viewHolderClaz) {
        super(context, resource, objects);

        this.viewHolderClaz = viewHolderClaz;
        this.context = context;
        this.resource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        try {
            viewHolder = (ViewHolder) viewHolderClaz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Entity entity = getItem(position);

        if (convertView == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.resource, parent, false);

            viewHolder.setParent(convertView);
            viewHolder.instanciate();

            viewHolder.setEntity(entity);

            convertView.setTag(viewHolder);

            ((Card) convertView).setIndentifier(position);

        } else {

            Card card = (Card) convertView;

            if (position != card.getIndentifier()) {
                if (card.isExpanded()) {

                    card.getCardExpand().setVisibility(View.GONE);
                    ((CardHeader) card.getCardHeader()).getButton().setSelected(false);
                    card.setExpanded(false);

                }
            }

            viewHolder = (ViewHolder) card.getTag();
            viewHolder.setEntity(entity);
            card.setTag(viewHolder);
            card.setIndentifier(position);
        }

        return convertView;
    }

}