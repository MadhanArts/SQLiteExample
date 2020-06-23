package com.madhanarts.sqliteexample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button bnAdd, bnView, bnUpdate, bnDelete;

    OnDbOperationListener dbOperationListener;

    public interface OnDbOperationListener
    {
        void dbOperationPerformed(int method);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        bnAdd = view.findViewById(R.id.add_contact_button);
        bnView = view.findViewById(R.id.view_contact_button);
        bnUpdate = view.findViewById(R.id.update_contact_button);
        bnDelete = view.findViewById(R.id.delete_contact_button);


        bnAdd.setOnClickListener(this);
        bnView.setOnClickListener(this);
        bnUpdate.setOnClickListener(this);
        bnDelete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.add_contact_button:

                dbOperationListener.dbOperationPerformed(0);

                break;

            case R.id.view_contact_button:
                dbOperationListener.dbOperationPerformed(1);

                break;

            case R.id.update_contact_button:
                dbOperationListener.dbOperationPerformed(2);

                break;

            case R.id.delete_contact_button:
                dbOperationListener.dbOperationPerformed(3);
                break;

        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try {
            dbOperationListener = (OnDbOperationListener) activity;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(activity.toString() + " must implement the interface method");
        }


    }
}