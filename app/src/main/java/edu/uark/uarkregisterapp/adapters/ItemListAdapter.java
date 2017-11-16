package edu.uark.uarkregisterapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import edu.uark.uarkregisterapp.R;
import edu.uark.uarkregisterapp.models.api.Product;

public class ItemListAdapter extends ArrayAdapter<Product> {
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(this.getContext());
			view = inflater.inflate(R.layout.list_view_item_cart, parent, false);
		}

		Product item = this.getItem(position);
		if (item != null) {
			TextView lookupCodeTextView = (TextView) view.findViewById(R.id.list_view_item_cart_lookup_code);
			if (lookupCodeTextView != null) {
				lookupCodeTextView.setText(item.getLookupCode());
			}

			TextView countTextView = (TextView) view.findViewById(R.id.list_view_item_cart_count);
			if (countTextView != null) {
				countTextView.setText(String.format(Locale.getDefault(), "%d", item.getCount()));
			}
		}

		return view;
	}

	public ItemListAdapter(Context context, List<Product> items) {
		super(context, R.layout.list_view_item_cart, items);
	}
}
