package com.simplecardsview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.simplecardsview.R;
import com.simplecardsview.model.Entity;
import com.simplecardsview.view.Card;
import com.simplecardsview.view.CardHeader;
import com.simplecardsview.view.ViewHolder;

import java.util.List;


public class CardAdapter extends ArrayAdapter<Entity> {

    private List<Entity> objects;
    private Context context;
    private Class viewHolderClaz;
    private int resource;
    private ConfigureListener configureListener;

    public CardAdapter(Context context, int resource, List<Entity> objects, Class viewHolderClaz) {
        super(context, resource, objects);

        this.viewHolderClaz = viewHolderClaz;
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        boolean reciclando;

        ViewHolder viewHolder = null;
        try {
            viewHolder = (ViewHolder) viewHolderClaz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (convertView == null) {

            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.resource, parent, false);

            viewHolder.setParent(convertView);
            viewHolder.instanciate();

            convertView.setTag(viewHolder);

            reciclando = false;

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            reciclando = true;
        }

        Entity entity = getItem(position);

        if (entity != null) {

            if (!entity.isExpanded()) {
                if (((Card) convertView).getCardExpand() != null) {
                    if (((Card) convertView).getCardExpand().getVisibility() != View.GONE) {
                        ((Card) convertView).getCardExpand().setVisibility(View.GONE);
                    }
                }
                if (((Card) convertView).getCardHeader() != null && ((Card) convertView).getCardHeader().getButton() != null) {
                    if (((Card) convertView).getCardHeader().getButton().isSelected()) {
                        ((Card) convertView).getCardHeader().getButton().setSelected(false);
                    }
                }
            } else if (entity.isExpanded()) {
                if (((Card) convertView).getCardExpand() != null) {
                    if (((Card) convertView).getCardExpand().getVisibility() != View.VISIBLE) {
                        ((Card) convertView).getCardExpand().setVisibility(View.VISIBLE);
                    }
                }
                if (((Card) convertView).getCardHeader() != null && ((Card) convertView).getCardHeader().getButton() != null) {
                    if (!((Card) convertView).getCardHeader().getButton().isSelected()) {
                        ((Card) convertView).getCardHeader().getButton().setSelected(true);
                    }
                }
            }

            if (configureListener != null) configureListener.onConfigure((Card) convertView, entity, viewHolder, position, reciclando);
            viewHolder.setEntity(entity);
        }

        return convertView;
    }

    public ConfigureListener getConfigureListener(ConfigureListener configureListener) {
        return this.configureListener;
    }

    public void setConfigureListener(ConfigureListener configureListener) {
        this.configureListener = configureListener;
    }

    public interface ConfigureListener {
        public void onConfigure(Card card, Entity entity, ViewHolder viewHolder, int position, boolean reciclando);
    }

    public List<Entity> getObjects() {
        return objects;
    }

    public void setObjects(List<Entity> objects) {
        this.objects = objects;
    }
}