
package com.example.dchen.firstadroidapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dchen.firstadroidapplication.Book;

import java.util.ArrayList;
import java.util.List;

public class BooklistAdapter extends RecyclerView.Adapter<BooklistAdapter.ViewHolder> {

    private List<Book> mBooks = new ArrayList<>();



    public void addAll(List<Book> newBooks) {
        mBooks.addAll(newBooks);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_main, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Book data = mBooks.get(position);

        holder.title.setText(data.getTitle());
        holder.summary.setText(data.getSummary());
        holder.information.setText(data.getInformation());
        holder.ratingBar.setRating((float) (data.getRate() / 2));
        holder.ratingVal.setText(String.valueOf(data.getRate()));


        Glide
                .with(FirstAdroidApplication.getApplication())
                .load(data.getImage())
                .centerCrop()
                .placeholder(R.drawable.ic_default_cover)
                .crossFade()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public void clearAll() {
        mBooks.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView information;
        public TextView summary;
        public ImageView image;
        public RatingBar ratingBar;
        public TextView ratingVal;

        public ViewHolder(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.book_title);
            information = (TextView) v.findViewById(R.id.information);
            summary = (TextView) v.findViewById(R.id.summary);
            image = (ImageView) v.findViewById(R.id.book_image);
            ratingBar = (RatingBar) v.findViewById(R.id.rating);
            ratingVal = (TextView) v.findViewById(R.id.ratingValue);
        }
    }
}