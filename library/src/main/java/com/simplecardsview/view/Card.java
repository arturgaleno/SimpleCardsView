package com.simplecardsview.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ValueAnimator;
import com.simplecardsview.R;

public class Card extends FrameLayout {

    private Context context;

    private int idCardHeader;

    private int idCardBody;

    private int idCardExpand;

    private View thisView;

    private LinearLayout bodyLayout;

    private LinearLayout headerLayout;

    private LinearLayout expandLayout;

    private AbsListView listViewOrGridView;

    private CardHeader cardHeader;

    private CardBody cardBody;

    private CardExpand cardExpand;

    private Integer indentifier;

    protected Animator expandAnimator, collapseAnimator;

    private boolean expanded;

    private boolean refreshed = false;

    public Card(Context context) {
        super(context);
        this.context = context;

        init(null, 0);
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        init(attrs, 0);
    }

    public Card(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        init(attrs, defStyleAttr);

    }

    public void findCardBody(ViewGroup viewToSeach) {

        boolean ended = false;
        int count = 0;
        int totalCounts = 0;
        while (!ended) {
            if (totalCounts == 100) {
                throw new RuntimeException(this.getClass().getCanonicalName() + "->findCardBody() do 50 loops, forced stop. Please, optimize your layout tree.");
            }
            if (viewToSeach instanceof ViewGroup) {
                if (viewToSeach.getChildAt(count) instanceof CardBody) {
                    cardBody = (CardBody) viewToSeach.getChildAt(count);
                    ended = true;
                } else {
                    if (count >= viewToSeach.getChildCount() - 1) {
                        viewToSeach = (ViewGroup) viewToSeach.getChildAt(count);
                        count = 0;
                    } else {
                        count++;
                    }
                }
            } else {
                ended = true;
            }
            totalCounts++;
        }
    }

    public void findCardHeader(ViewGroup viewToSeach) {

        boolean ended = false;
        int count = 0;
        int totalCounts = 0;
        while (!ended) {
            if (totalCounts == 100) {
                throw new RuntimeException(this.getClass().getCanonicalName() + "->findCardHeader() do 50 loops, forced stop. Please, optimize your layout tree.");
            }
            if (viewToSeach instanceof ViewGroup) {
                if (viewToSeach.getChildAt(count) instanceof CardHeader) {
                    cardHeader = (CardHeader) viewToSeach.getChildAt(count);
                    ended = true;
                } else {
                    if (count >= viewToSeach.getChildCount() - 1) {
                        viewToSeach = (ViewGroup) viewToSeach.getChildAt(count);
                        count = 0;
                    } else {
                        count++;
                    }
                }
            } else {
                ended = true;
            }
            totalCounts++;
        }
    }

    public void findCardExpand(ViewGroup viewToSeach) {

        boolean ended = false;
        int count = 0;
        int totalCounts = 0;
        while (!ended) {
            if (totalCounts == 100) {
                throw new RuntimeException(this.getClass().getCanonicalName() + "->findCardExpand() do 50 loops, forced stop. Please, optimize your layout tree.");
            }
            if (viewToSeach instanceof ViewGroup) {
                if (viewToSeach.getChildAt(count) instanceof CardExpand) {
                    cardExpand = (CardExpand) viewToSeach.getChildAt(count);
                    ended = true;
                } else {
                    if (count >= viewToSeach.getChildCount() - 1) {
                        viewToSeach = (ViewGroup) viewToSeach.getChildAt(count);
                        count = 0;
                    } else {
                        count++;
                    }
                }
            } else {
                ended = true;
            }
            totalCounts++;
        }
    }

    protected void init(AttributeSet attrs, int defStyleAttr) {

        TypedArray a = context.obtainStyledAttributes(
                attrs,
                R.styleable.Card,
                defStyleAttr, 0);


        try {
            idCardHeader = a.getResourceId(R.styleable.Card_header, 0);
            idCardBody = a.getResourceId(R.styleable.Card_body, 0);
            idCardExpand = a.getResourceId(R.styleable.Card_cardToExpand, 0);
        } finally {
            a.recycle();
            if (!isInEditMode()) {
                initView();
            }
        }
    }

    protected void initView() {

//        this.removeAllViews();

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        thisView = inflater.inflate(R.layout.card, this, true);

        bodyLayout = (LinearLayout) findViewById(R.id.bodyLayout);
        headerLayout = (LinearLayout) findViewById(R.id.headerLayout);
        expandLayout = (LinearLayout) findViewById(R.id.expandLayout);

        ViewGroup bodyViewGroup = (ViewGroup) inflater.inflate(idCardBody, bodyLayout, true);
        ViewGroup headerViewGroup = (ViewGroup) inflater.inflate(idCardHeader, headerLayout, true);
        ViewGroup expandViewGroup = (ViewGroup) inflater.inflate(idCardExpand, expandLayout, true);

        findCardBody(bodyViewGroup);
        findCardHeader(headerViewGroup);
        findCardExpand(expandViewGroup);

        setupExpandAction();

    }

    public void refresh() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        bodyLayout = (LinearLayout) findViewById(R.id.bodyLayout);
        headerLayout = (LinearLayout) findViewById(R.id.headerLayout);
        expandLayout = (LinearLayout) findViewById(R.id.expandLayout);

        bodyLayout.removeAllViews();
        headerLayout.removeAllViews();
        expandLayout.removeAllViews();

        ViewGroup bodyViewGroup = (ViewGroup) inflater.inflate(idCardBody, bodyLayout, true);
        ViewGroup headerViewGroup = (ViewGroup) inflater.inflate(idCardHeader, headerLayout, true);
        ViewGroup expandViewGroup = (ViewGroup) inflater.inflate(idCardExpand, expandLayout, true);

        findCardBody(bodyViewGroup);
        findCardHeader(headerViewGroup);
        findCardExpand(expandViewGroup);

        configButton();

        setupExpandAction();
    }

    public static ValueAnimator createHeightAnimator(final View view, final int start, final int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                int value = (Integer) valueAnimator.getAnimatedValue();

                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = value;
                view.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    public AbsListView isInListViewOrGridView(View view) {
        int i = 0;
        boolean end = false;

        View viewToSearch = view;

        while (!end) {

            if (i == 50) {  //avoid infite loop
                throw new RuntimeException(this.getClass().getCanonicalName() + "->isInListViewOrGridView() do 50 loops, forced stop. Please, optimize your layout tree.");
            }

            View parent = (View) viewToSearch.getParent();

            if (parent == null) end = true;

            if (parent instanceof ListView) {
                return (AbsListView) parent;
            }

            if (parent instanceof GridView) {
                return (AbsListView) parent;
            }

            viewToSearch = parent;

            i++;
        }

        return null;
    }

    public void setupExpandAction() {

        if (cardExpand.getView() != null) {

            cardExpand.getView().getViewTreeObserver().addOnPreDrawListener(
                    new ViewTreeObserver.OnPreDrawListener() {

                        @Override
                        public boolean onPreDraw() {
                            cardExpand.getView().getViewTreeObserver().removeOnPreDrawListener(this);

                            View parent = (View) cardExpand.getView().getParent();
                            final int widthSpec = MeasureSpec.makeMeasureSpec(parent.getMeasuredWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), MeasureSpec.AT_MOST);
                            final int heightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                            cardExpand.getView().measure(widthSpec, heightSpec);

                            expandAnimator = createHeightAnimator(cardExpand.getView(), 0, cardExpand.getView().getMeasuredHeight());
                            collapseAnimator = createHeightAnimator(cardExpand.getView(), cardExpand.getView().getMeasuredHeight(), 0);

                            listViewOrGridView = isInListViewOrGridView(cardExpand.getView());

                            if (listViewOrGridView != null) {
                                listViewAnimatorExpand();
                                listViewAnimatorCollapse();

                            }
                            return true;
                        }
                    });
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        configButton();
    }

    public void configButton() {
        if (cardHeader.getButton() != null) {
            cardHeader.getButton().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardExpand.getVisibility() == View.GONE) {
                        expandAnimator.start();
                    } else {
                        collapseAnimator.start();
                    }

                }
            });
        }
    }

    public void listViewAnimatorExpand() {


        ((ValueAnimator) expandAnimator).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            final int listViewHeight = listViewOrGridView.getHeight();
            final int listViewBottomPadding = listViewOrGridView.getPaddingBottom();

            @Override
            public void onAnimationUpdate(final ValueAnimator valueAnimator) {
                final int bottom = thisView.getBottom();
                if (bottom > listViewHeight) {
                    final int top = thisView.getTop();
                    if (top > 0) {
                        listViewOrGridView.smoothScrollBy(Math.min(bottom - listViewHeight + listViewBottomPadding, top) + cardExpand.getView().getMeasuredHeight(), 0);
                    }
                }
            }
        });
        expandAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                cardExpand.setVisibility(View.VISIBLE);
                cardHeader.getButton().setSelected(true);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setExpanded(true);
                ((ArrayAdapter) listViewOrGridView.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    public void listViewAnimatorCollapse() {

        collapseAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                cardExpand.setVisibility(View.GONE);
                cardHeader.getButton().setSelected(false);
                setExpanded(false);
                ((ArrayAdapter) listViewOrGridView.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    public CardHeader getCardHeader() {
        return cardHeader;
    }

    public CardExpand getCardExpand() {
        return cardExpand;
    }

    public CardBody getCardBody() {
        return cardBody;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public Animator getExpandAnimator() {
        return expandAnimator;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public Animator getCollapseAnimator() {
        return collapseAnimator;
    }

    public boolean isRefreshed() {
        return refreshed;
    }

    public void setRefreshed(boolean refreshed) {
        this.refreshed = refreshed;
    }

    public Integer getIndentifier() {
        return indentifier;
    }

    public void setIndentifier(Integer indentifier) {
        this.indentifier = indentifier;
    }
}
