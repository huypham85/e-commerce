package com.example.e_commerce.screens.home.spinner;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.e_commerce.network.model.response.product.TypeProductResponse;

import java.lang.reflect.Type;
import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<TypeProductResponse> implements AdapterView.OnItemSelectedListener {
    private List<TypeProductResponse> types;
    private Context context;
    private OnSpinnerItemSelected listener;

    public CustomSpinnerAdapter(Context context, int resource, List<TypeProductResponse> types, OnSpinnerItemSelected listener) {
        super(context, resource, types);
        this.context = context;
        this.types = types;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(types.get(position).getTypeProductName());
        textView.setTextSize(16);
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(types.get(position).getTypeProductName());
        textView.setTextSize(16);
        return textView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TypeProductResponse typeSelected = types.get(position);
        listener.onCategoryItemSelected(typeSelected);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void getTypeSelected(int position) {
        TypeProductResponse typeSelected = types.get(position);
        listener.onCategoryItemSelected(typeSelected);
    }
}

