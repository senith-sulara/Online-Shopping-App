package com.example.kingship;

import static com.example.kingship.database.FragranceContract.FragranceEntry.CART_TABLE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LoaderManager;

import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kingship.count.Utils;
import com.example.kingship.database.FragranceContract;
import com.example.kingship.database.FragranceDbHelper;
import com.example.kingship.entities.Account;

public class Customer extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView recyclerView;
    FragranceAdapter fragranceAdapter;
    private static final int FRAGRANCE_LOADER = 0;
    FragranceDbHelper fragranceDbHelper;
    private SQLiteDatabase mDb;

    private int mNotificationsCount = 0;

    private Account account;
    TextView welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        FragranceDbHelper dbHelper = new FragranceDbHelper(this);
        mDb = dbHelper.getWritableDatabase();


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }

        fragranceAdapter = new FragranceAdapter(this, null);
        recyclerView.setAdapter(fragranceAdapter);

        getLoaderManager().initLoader(FRAGRANCE_LOADER,null,this);

        new FetchCountTask().execute();

        welcome = findViewById(R.id.welcmtxt);
        Intent intent = getIntent();
        account = (Account) intent.getSerializableExtra("account");

        welcome.setText("Welcome "+ " " + account.getUsername());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        // Get the notifications MenuItem and
        // its LayerDrawable (layer-list)
        MenuItem item = menu.findItem(R.id.action_notifications);
        LayerDrawable icon = (LayerDrawable) item.getIcon();

        // Update LayerDrawable's BadgeDrawable
        Utils.setBadgeCount(this, icon, mNotificationsCount);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_notifications: {
                Intent intent = new Intent(this, Cart.class);
                startActivity(intent);
                return true;
            }
            case R.id.profile:  {
                Intent intent = new Intent(this, Profile.class);
                intent.putExtra("account", account);
                startActivity(intent);
                return true;
            }
            case R.id.logoutbtn:  {
                Intent intent = new Intent(this, Signin.class);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateNotificationsBadge(int count) {
        mNotificationsCount = count;

        // force the ActionBar to relayout its MenuItems.
        // onCreateOptionsMenu(Menu) will be called again.
        invalidateOptionsMenu();
    }
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                FragranceContract.FragranceEntry._ID,
                FragranceContract.FragranceEntry.COLUMN_NAME,
                FragranceContract.FragranceEntry.COLUMN_DESCRIPTION,
                FragranceContract.FragranceEntry.COLUMN_IMAGE,
                FragranceContract.FragranceEntry.COLUMN_PRICE,
                FragranceContract.FragranceEntry.COLUMN_USERRATING
        };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                FragranceContract.FragranceEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        fragranceAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        fragranceAdapter.swapCursor(null);

    }


    class FetchCountTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            String countQuery = "SELECT  * FROM " + CART_TABLE;
            Cursor cursor = mDb.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cursor.close();
            return count;
        }

        @Override
        public void onPostExecute(Integer count) {
            updateNotificationsBadge(count);
        }
    }
}