package com.app.javalistdemo.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.javalistdemo.R;
import com.app.javalistdemo.model.Users;
import com.app.javalistdemo.utils.ItemTouchHelperAdapter;

import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements ItemTouchHelperAdapter {
    Context mContext;
    List<Users> userList;
    private ItemTouchHelper mItemTouchHelper;

    public UserAdapter(List<Users> usersList) {
        this.mContext = mContext;
        this.userList = usersList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {
        Users userModel = userList.get(position);

        userViewHolder.name.setText("Name: " + userModel.getName());
        userViewHolder.email.setText("Email: " + userModel.getEmail());
        userViewHolder.gender.setText("Gender: " + userModel.getGender());
        userViewHolder.status.setText("Status: " + userModel.getStatus());
    }

    @Override
    public void onItemMove(int fromposition, int toposition) {

        Users mUsers = userList.get(fromposition);
        userList.remove(mUsers);
        userList.add(toposition, mUsers);
        notifyItemMoved(fromposition, toposition);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener,
            View.OnTouchListener, GestureDetector.OnGestureListener {

        GestureDetector mGestureDetector;

        TextView name, email, gender, status;

        public UserViewHolder(@NonNull View itemView) {

            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            gender = itemView.findViewById(R.id.tv_gender);
            email = itemView.findViewById(R.id.tv_email);
            status = itemView.findViewById(R.id.tv_status);

            mGestureDetector = new GestureDetector(itemView.getContext(), this);

            itemView.setOnTouchListener(this);

        }

        @Override
        public boolean onDown(@NonNull MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(@NonNull MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(@NonNull MotionEvent motionEvent) {
            mItemTouchHelper.startDrag(this);

        }

        @Override
        public boolean onFling(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onClick(View view) {

        }

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mGestureDetector.onTouchEvent(motionEvent);
            return true;
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setTouchHelp(ItemTouchHelper itemTouchHelper) {
        this.mItemTouchHelper = itemTouchHelper;
    }

}
