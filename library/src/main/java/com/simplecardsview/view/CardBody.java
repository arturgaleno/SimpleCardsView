package com.simplecardsview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.simplecardsview.R;

/**
 * Created by artur on 01/09/14.
 */
public class CardBody extends FrameLayout {

    private Context context;

    private View thisView;

    private int idCardBody;

    public CardBody(Context context) {
        super(context);
        this.context = context;

        init(null,0);
    }

    public CardBody(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init(attrs, 0);
    }

    public CardBody(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init(attrs, defStyleAttr);

    }

    protected void init (AttributeSet attrs, int defStyleAttr) {

        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.CardBody,
                defStyleAttr, 0);


        try {
            idCardBody = a.getResourceId(R.styleable.CardBody_content, 0);
        } finally {
            a.recycle();
            if (!isInEditMode()) {
                initView();
            }
        }
    }

    protected void initView() {
        if (idCardBody != 0) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            thisView = inflater.inflate(idCardBody, this, true);
        }
    }

    public View getView() {
        return thisView;
    }

    public void setIdCardBody(int idCardBody) {
        this.idCardBody = idCardBody;
    }
}
