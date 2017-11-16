package edu.uark.uarkregisterapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.uark.uarkregisterapp.adapters.ProductListAdapter;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.services.ProductService;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class CartViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        this.items = new ArrayList<>();
        this.itemListAdapter = new ProductListAdapter(this, this.items);

        this.getItemsListView().setAdapter(this.itemListAdapter);
        this.getItemsListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ProductViewActivity.class);

                intent.putExtra(
                        getString(R.string.intent_extra_product),
                        new ProductTransition((Product) getItemsListView().getItemAtPosition(position))
                );

                startActivity(intent);
            }
        });

        this.loadingItemsAlert = new AlertDialog.Builder(this).
                setMessage(R.string.alert_dialog_items_loading).
                create();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.loadingItemsAlert.show();
        (new RetrieveItemsTask()).execute();
    }

    private ListView getItemsListView() {
        return (ListView) this.findViewById(R.id.cart_list_view);
    }

    private class RetrieveItemsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            items.clear();
            items.addAll(
                    (new ProductService()).getProducts()
            );

            return null;
        }

        @Override
        protected void onPostExecute(Void param) {
            itemListAdapter.notifyDataSetChanged();
            loadingItemsAlert.dismiss();
        }
    }

    private List<Product> items;
    private AlertDialog loadingItemsAlert;
    private ProductListAdapter itemListAdapter;
}
