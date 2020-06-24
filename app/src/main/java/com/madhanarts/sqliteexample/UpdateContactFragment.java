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

public class UpdateContactFragment extends Fragment {

    private EditText contactId, contactName, contactMail;
    private Button updateButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_contact, container, false);

        contactId = view.findViewById(R.id.update_contact_id);
        contactName = view.findViewById(R.id.update_contact_name);
        contactMail = view.findViewById(R.id.update_contact_email);
        updateButton = view.findViewById(R.id.contact_update_button);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateContact();

            }
        });

        return view;
    }

    private void updateContact()
    {

        String id = contactId.getText().toString();
        String name = contactName.getText().toString();
        String email = contactMail.getText().toString();

        BackgroundTask backgroundTask = new BackgroundTask(getActivity());

        backgroundTask.execute("update_contact", id, name, email);


        contactId.setText("");
        contactName.setText("");
        contactMail.setText("");

    }

}