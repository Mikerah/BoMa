package com.example.mikerah.boma;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.UUID;

public class DetailActivity extends AppCompatActivity {
    private Book mBook;
    private BookManager mBookManager;

    private TextView mBookTitle;
    private TextView mBookAuthor;
    private TextView mBookGenre;
    private TextView mBookYear;
    private EditText mYearAquiredField;
    private EditText mPersonalRating;
    private ToggleButton mIsToKeep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        UUID bookId = (UUID) getIntent()
                .getSerializableExtra
                ("BookID");
        mBookManager = BookManager.get(getApplicationContext());
        mBook = mBookManager.getBook(bookId);

        mBookTitle = (TextView) findViewById(R.id.detail_title);
        mBookTitle.setText(mBook.getTitle());

        mBookAuthor = (TextView) findViewById(R.id.detail_author);
        mBookAuthor.setText(mBook.getAuthor());

        mBookGenre = (TextView) findViewById(R.id.detail_genre);
        mBookGenre.setText(mBook.getGenre());

        mBookYear = (TextView) findViewById(R.id.detail_year_published);
        mBookYear.setText(mBook.getYearPublished());

        mYearAquiredField = (EditText) findViewById(R.id.detail_year_acquired);
        mYearAquiredField.setText(mBook.getYearAcquired());
        mYearAquiredField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBook.setYearAcquired(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Intentionally left blank
            }
        });

        mPersonalRating = (EditText) findViewById(R.id.detail_personal_rating);
        mPersonalRating.setText(mBook.getPersonalRating());
        mPersonalRating.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mBook.setPersonalRating(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Intentionally left blank
            }
        });

        mIsToKeep = (ToggleButton) findViewById(R.id.detail_to_keep);
        mIsToKeep.setChecked(mBook.isToKeep());
        mIsToKeep.setOnCheckedChangeListener(new CompoundButton
                .OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mBook.setToKeep(isChecked);
            }
        });

    }
}
