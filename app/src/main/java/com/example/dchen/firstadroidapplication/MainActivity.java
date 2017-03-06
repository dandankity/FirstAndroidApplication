package com.example.dchen.firstadroidapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private ListView listView;
    private ArrayAdapter<Book> bookArrayAdapter;
    private SwipeRefreshLayout sWip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(android.R.id.list);
//        sWip = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
//        sWip.setOnRefreshListener(this);



        new LoadDataAsyncTask() {
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);
                bookArrayAdapter.addAll(new BookData(jsonObject).getBooks());
                bookArrayAdapter.notifyDataSetChanged();

            }
        }.execute("https://api.douban.com/v2/book/search?tag=IT&count=100");



        bookArrayAdapter = new ArrayAdapter<Book>(this, R.layout.content_main){
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.content_main, parent, false);

                final ImageView bookImage = (ImageView) view.findViewById(R.id.book_image);
                TextView bookTitle = (TextView) view.findViewById(R.id.book_title);
                TextView bookSummary = (TextView) view.findViewById(R.id.summary);
                TextView bookInformation = (TextView) view.findViewById(R.id.information);
                TextView ratingVal = (TextView) view.findViewById(R.id.ratingValue);
                RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating);

                final Book book = getItem(position);

                bookTitle.setText(book.getTitle());
                bookSummary.setText(book.getSummary());
                bookInformation.setText(book.getInformation());
                ratingBar.setRating((float)(book.getRate()/2));
                ratingVal.setText(String.valueOf(book.getRate()));

                Glide
                        .with(getContext())
                        .load(book.getImage())
                        .centerCrop()
                        .placeholder(R.drawable.ic_default_cover)
                        .crossFade()
                        .into(bookImage);



                return view;
            }
        };
        listView.setAdapter(bookArrayAdapter);
    }

    @Override
    public void onRefresh() {

        doRefresh();

    }

    private void doRefresh() {
        new LoadDataAsyncTask() {
            @Override
            protected void onPostExecute(JSONObject jsonObject) {
                super.onPostExecute(jsonObject);
                sWip.setRefreshing(false);
                bookArrayAdapter.addAll(new BookData(jsonObject).getBooks());
                bookArrayAdapter.notifyDataSetChanged();

            }
        }.execute("https://api.douban.com/v2/book/search?tag=IT&count=100");
    }


    static class LoadDataAsyncTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            return DataLoader.loadData(params[0]);
        }
    }




}
