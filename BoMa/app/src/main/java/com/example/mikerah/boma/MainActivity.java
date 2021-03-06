package com.example.mikerah.boma;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mikerah.boma.BarcodeTools.BarcodeCaptureActivity;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.api.services.books.Books;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mBookRecyclerView;
    private BookAdapter mBookAdapter;
    private LinearLayoutManager mLayoutManager;
    private android.support.v7.widget.DividerItemDecoration
            mDividerItemDecorator;

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

        // Add divider
        mDividerItemDecorator = new android.support.v7.widget
                .DividerItemDecoration(getApplicationContext(),
                mLayoutManager.getOrientation());
        mBookRecyclerView.addItemDecoration(mDividerItemDecorator);

        updateUI();
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    private void  updateUI(){
        BookManager bookManager = BookManager.get(getApplicationContext());
        List<Book> books = bookManager.getBooks();

        if(mBookAdapter == null){
            mBookAdapter = new BookAdapter(books);
            mBookRecyclerView.setAdapter(mBookAdapter);
        } else {
            mBookAdapter.setBookList(books);
            mBookAdapter.notifyDataSetChanged();
        }

    }


    private class BookHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mBookTitle;
        private TextView mBookAuthor;
        private TextView mBookGenre;
        private TextView mBookYear;

        private Book mBook;

        public BookHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mBookTitle = (TextView) itemView.findViewById(R.id.book_title);
            mBookAuthor = (TextView) itemView.findViewById(R.id.book_author);
            mBookGenre = (TextView) itemView.findViewById(R.id.book_genre);
            mBookYear = (TextView) itemView.findViewById(R.id.book_year);
        }

        public void bindBook(Book book){
            mBook = book;
            mBookTitle.setText(getString(R.string.book_title, book.getTitle()));
            mBookAuthor.setText(getString(R.string.book_author, book.getAuthor()));
            mBookGenre.setText(getString(R.string.book_genre, book.getGenre()));
            mBookYear.setText(getString(R.string.book_year, book.getYearPublished()));

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),
                    DetailActivity.class);
            intent.putExtra("BookID",mBook.getId());
            startActivity(intent);
        }
    }

    public class BookAdapter extends RecyclerView.Adapter<BookHolder> {
        private List<Book> mBookList;

        public BookAdapter(List<Book> bookList){
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
            Book book = mBookList.get(position);
            holder.bindBook(book);

        }

        @Override
        public int getItemCount() {
            return mBookList.size();
        }

        public void setBookList(List<Book> books) {
            mBookList = books;
        }

    }

    private class GetBookTask extends AsyncTask<Void, Void, Book> {
        private Barcode mBarcode;

        public GetBookTask(Barcode barcode){
            mBarcode = barcode;
        }

        @Override
        protected Book doInBackground(Void... voids) {
            Books books = GoogleBooksHelper.GetBooksObj(getApplicationContext());
            Book book = GoogleBooksHelper.getBook(books, mBarcode);
            return book;
        }

        @Override
        protected void onPostExecute(Book book){
            BookManager.get(getApplicationContext()).addBook(book);
            updateUI();
        }
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
     **/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    new GetBookTask(barcode).execute();

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

}
