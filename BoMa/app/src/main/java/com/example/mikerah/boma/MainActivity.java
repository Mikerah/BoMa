package com.example.mikerah.boma;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mBookRecyclerView;
    private RecyclerView.Adapter mBookAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static final int RC_BARCODE_CAPTURE = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id
                .add_new_book_fab);

        // Launch Barcode Activity
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BarcodeCaptureActivity.class);
                startActivityForResult(intent, RC_BARCODE_CAPTURE);
            }
        });

        mBookRecyclerView = (RecyclerView)findViewById(R.id
                .books_recycler_view);

        // Since changes in the content don't change layout size of the
        // RecyclerView, we apply the following setting
        mBookRecyclerView.setHasFixedSize(true);

        // Initialize Linear Layout Manager
        mLayoutManager = new LinearLayoutManager(this);
        mBookRecyclerView.setLayoutManager(mLayoutManager);

        // Add adapter
        List<String> bookTitles = new ArrayList<>();
        bookTitles.add("1984");
        bookTitles.add("Animal Farm");
        mBookAdapter = new BookAdapter(bookTitles);
        mBookRecyclerView.setAdapter(mBookAdapter);

    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Toast.makeText(getApplicationContext(), "Scanned " +
                            "barcode", Toast.LENGTH_LONG);

                } else {
                    Toast.makeText(getApplicationContext(), " Didn't Scanned " +
                            "barcode", Toast.LENGTH_LONG);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Barcode error: " +
                        CommonStatusCodes.getStatusCodeString(resultCode), Toast
                        .LENGTH_LONG);
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;

        public BookHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.book_title);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public class BookAdapter extends RecyclerView.Adapter<BookHolder> {
        private List<String> mBookList;

        public BookAdapter(List<String> bookList){
            mBookList = bookList;
        }

        @Override
        public BookHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_item_book, parent,
                    false);
            return new BookHolder(view);
        }

        @Override
        public void onBindViewHolder(BookHolder holder, int position){
            holder.mTextView.setText(mBookList.get(position));
        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }
    }
}
