package edu.aku.hassannaqvi.nns2018_teamleadersapp.other;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.ListingContract;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.ui.RandomizationActivity;

/**
 * Created by ali.azaz on 13/04/2017.
 */

public class randomListAdapter extends RecyclerView.Adapter<randomListAdapter.ViewHolder> {
    private ArrayList<ListingContract> list;

    Context mContext;
    ViewHolder holder;

    public randomListAdapter(Context context, ArrayList<ListingContract> list) {
        this.list = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View statusContainer = LayoutInflater.from(parent.getContext()).inflate(R.layout.lstview_random1, parent, false);
        return new ViewHolder(statusContainer);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        this.holder = holder;
        this.holder.bindUser(list.get(position));

//        if (list.get(position).getTotalhh().equals(list.get(position).randCount)) {
        if (list.get(position).getIsRandom().equals("1")) {
            RandomizationActivity.hhRandomise.add(position);
        } else if (list.get(position).getEligibleCluster()) {
            for (int pos : RandomizationActivity.hhClusterNotEligible) {
                if (pos == position) {
                    RandomizationActivity.hhClusterNotEligible.remove(position);
                }
            }
        } else {
            RandomizationActivity.hhClusterNotEligible.add(position);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkSelected)
        ImageView checkSelected;
        @BindView(R.id.clusterCode)
        TextView clusterCode;
        @BindView(R.id.resCount)
        TextView resCount;
        @BindView(R.id.childCount)
        TextView childCount;
        @BindView(R.id.rndCount)
        TextView rndCount;
        @BindView(R.id.totalCount)
        TextView totalCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindUser(ListingContract contact) {
            clusterCode.setText(contact.getClusterCode());
            resCount.setText("Residential: " + contact.getResCount());
            childCount.setText("Child < 5: " + contact.getChildCount());
            rndCount.setText("Randomized Structure: " + (contact.getIsRandom().equals("2") ? "0" : contact.getRandCount()));
            totalCount.setText("Total Structure Count: " + contact.getTotalhh());

            /*if (contact.getTotalhh().equals(contact.randCount)) {
                checkSelected.setVisibility(View.VISIBLE);
            }*/

            checkSelected.setVisibility(contact.getIsRandom().equals("1") ? View.VISIBLE : View.GONE);
        }
    }


    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        GestureDetector mGestureDetector;
        private OnItemClickListener mListener;
        private RecyclerView viewRecycle;

        public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
            mListener = listener;

            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = viewRecycle.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onItemLongClick(child, viewRecycle.getChildAdapterPosition(child));
                    }

                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            viewRecycle = view;
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public interface OnItemClickListener {
            void onItemClick(View view, int position);

            void onItemLongClick(View view, int position);
        }


    }

}
