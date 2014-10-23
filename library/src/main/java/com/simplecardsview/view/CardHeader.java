package com.simplecardsview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.simplecardsview.R;

/**
 * Created by artur on 01/09/14.
 */
public class CardHeader extends FrameLayout {

    private Context context;

    private ImageView button;

    private View thisCardHeader;

    private View customInnerLayout;

    private int idCustomInnerLayout;

    private int buttonType;

    private boolean hasButton = false,
                    hasCustomInnerLayout = false;

    private int buttonDrawable;

    private String title;

    public CardHeader(Context context) {
        super(context);
        this.context = context;

        init(null,0);
    }

    public CardHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init(attrs, 0);
    }

    public CardHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init(attrs, defStyleAttr);

    }

    protected void init(AttributeSet attrs, int defStyleAttr) {

        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.CardHeader,
                defStyleAttr, 0);

        try {
            buttonType = a.getInt(R.styleable.CardHeader_buttonType, 9);
            hasButton = a.getBoolean(R.styleable.CardHeader_hasButton, false);
            buttonDrawable = a.getResourceId(R.styleable.CardHeader_buttonDrawable, 0);
            hasCustomInnerLayout = a.getBoolean(R.styleable.CardHeader_hasCustomInnerLayout, false);
            idCustomInnerLayout = a.getResourceId(R.styleable.CardHeader_customInnerLayout, 0);
        } finally {
            a.recycle();
            if (!isInEditMode())
                initView();
        }
    }

    protected void initView() {

        if (hasButton || hasCustomInnerLayout) {
//            this.removeAllViews();
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            thisCardHeader = inflater.inflate(R.layout.card_header, this, true);
            if (hasCustomInnerLayout)
                customInnerLayout = inflater.inflate(idCustomInnerLayout, this, true);

            if (buttonType == 0) { //expand
                button = (ImageView) findViewById(R.id.card_header_button);
                button.setVisibility(VISIBLE);
            } else if (buttonType == 1) { //overflow
                button = (ImageView) findViewById(R.id.card_header_button);
                button.setVisibility(VISIBLE);
            }

            if (button instanceof ImageView) {
                button.setImageDrawable(getContext().getResources().getDrawable(buttonDrawable));
            }

            if (!hasButton) {
                if (button instanceof ImageButton) button.setVisibility(GONE);
            }
        }
    }

    public View getView() {
        return thisCardHeader;
    }

    public void setIdCustomInnerLayout(int idCustomInnerLayout) {
        this.idCustomInnerLayout = idCustomInnerLayout;
    }

    public ImageView getButton() {
        return button;
    }
}
