package com.example.mikerah.boma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.UUID;

public class DetailActivity extends AppCompatActivity {
    private Book mBook;
    private BookManager mBookManager;

    private TextView mBookTitle;
    private TextView mBookAuthor;
    private TextView mBookGenre;
    private TextView mBookYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        UUID bookId = (UUID) intent.getSerializableExtra("BookID");
        mBookManager = BookManager.get(getApplicationContext());
        mBook = mBookManager.getBook(bookId);

        mBookTitle = (TextView) findViewById(R.id.detail_title);
        mBookTitle.setText(mBook.getTitle());

        mBookAuthor = (TextView) findViewById(R.id.detail_author);
        mBookAuthor.setText(mBook.getAuthor());

        mBookGenre = (TextView) findViewById(R.id.detail_genre);
        mBookGenre.setText(mBook.getGenre());

        mBookYear = (TextView) findViewById(R.id.detail_year_published);
        mBookYear.setText(Integer.toString(mBook.getYearPublished()));


    }
}
