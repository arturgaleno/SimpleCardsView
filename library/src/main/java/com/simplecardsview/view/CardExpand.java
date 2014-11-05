package com.simplecardsview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.simplecardsview.R;

/**
 * Created by artur on 02/09/14.
 */
public class CardExpand extends FrameLayout {

    private Context context;

    private View thisView;

    private int idCardExpand;

    public CardExpand(Context context) {
        super(context);
        this.context = context;

        init(null,0);
    }

    public CardExpand(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init(attrs, 0);
    }

    public CardExpand(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init(attrs, defStyleAttr);

    }

    protected void init (AttributeSet attrs, int defStyleAttr) {

        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.CardExpand,
                defStyleAttr, 0);


        try {
            idCardExpand = a.getResourceId(R.styleable.CardExpand_contentToExpand, 0);
        } finally {
            a.recycle();
            if (!isInEditMode()) {
                initView();
            }
        }
    }

    protected void initView() {
        if (idCardExpand != 0) {
//            this.removeAllViews();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            thisView = inflater.inflate(idCardExpand, this, true);
            thisView.setVisibility(View.GONE);
        }
    }

    public View getView() {
        return thisView;
    }

    public void setIdCardExpand(int idCardExpand) {
        this.idCardExpand = idCardExpand;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }
}
