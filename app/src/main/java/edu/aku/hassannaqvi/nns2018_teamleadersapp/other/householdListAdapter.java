package edu.aku.hassannaqvi.nns2018_teamleadersapp.other;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.R;
import edu.aku.hassannaqvi.nns2018_teamleadersapp.contracts.BLRandomContract;

/**
 * Created by ali.azaz on 13/04/2017.
 */

public class householdListAdapter extends RecyclerView.Adapter<householdListAdapter.ViewHolder> {
    private ArrayList<BLRandomContract> list;

    Context mContext;

    public householdListAdapter(Context context, ArrayList<BLRandomContract> list) {
        this.list = list;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View statusContainer = LayoutInflater.from(parent.getContext()).inflate(R.layout.lstview_hh_list, parent, false);
        return new ViewHolder(statusContainer);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindUser(list.get(position));

        holder.checkSelected.setTag(position);

        holder.checkSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pos = (Integer) holder.checkSelected.getTag();

                if (list.get(pos).getAssignHH().equals("1")) {
                    list.get(pos).setAssignHH("0");
                } else {
                    list.get(pos).setAssignHH("1");
                }
            }
        });
/*
        if (list.get(position).getAssignHH().equals("1")) {
            holder.checkSelected.setEnabled(false);
        }*/

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkSelected)
        CheckBox checkSelected;
        @BindView(R.id.clusterCode)
        TextView clusterCode;
        @BindView(R.id.hhno)
        TextView hhno;
        @BindView(R.id.hhname)
        TextView hhname;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindUser(BLRandomContract contact) {

            clusterCode.setText(contact.getSubVillageCode());
            hhno.setText(String.format("%04d", Integer.valueOf(contact.getStructure())) + "-" + contact.getExtension());
            hhname.setText(contact.getHhhead().equals("") ? "NA" : contact.getHhhead());
            checkSelected.setChecked(contact.getAssignHH().equals("1"));
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
        }


    }

}
