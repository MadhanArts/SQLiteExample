package com.madhanarts.sqliteexample;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteContactFragment extends Fragment {

    private EditText deleteId;
    private Button deleteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_contact, container, false);

        deleteId = view.findViewById(R.id.delete_contact_id);
        deleteButton = view.findViewById(R.id.contact_delete_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact();
            }
        });

        return view;
    }

    private void deleteContact()
    {

        String id = deleteId.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask(getActivity());

        backgroundTask.execute("delete_contact", id);

        deleteId.setText("");

        Toast.makeText(getActivity(), "Contact deleted successfully", Toast.LENGTH_SHORT).show();

    }

}