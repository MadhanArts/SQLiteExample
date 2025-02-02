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


public class AddContactFragment extends Fragment {

    private Button bnSave;
    private EditText contactId, contactName, contactMail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_contact, container, false);

        bnSave = view.findViewById(R.id.contact_save_button);
        contactId = view.findViewById(R.id.text_contact_id);
        contactName = view.findViewById(R.id.text_contact_name);
        contactMail = view.findViewById(R.id.text_contact_email);

        bnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                save();

            }
        });

        return view;
    }

    private void save()
    {

        String id = contactId.getText().toString();
        String name = contactName.getText().toString();
        String email = contactMail.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask(getActivity());

        backgroundTask.execute("add_contact", id, name, email);

        contactId.setText("");
        contactName.setText("");
        contactMail.setText("");
    }

}