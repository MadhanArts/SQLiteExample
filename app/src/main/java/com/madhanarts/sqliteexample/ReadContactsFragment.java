package com.madhanarts.sqliteexample;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ReadContactsFragment extends Fragment {

    private TextView textDisplay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_read_contacts, container, false);

        textDisplay = view.findViewById(R.id.text_contact_display);

        readContacts();


        return view;
    }

    private void readContacts()
    {
        String contactInfo = "";

        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());

        SQLiteDatabase database = contactDbHelper.getReadableDatabase();

        Cursor cursor = contactDbHelper.readContacts(database);

        while (cursor.moveToNext())
        {
            String id = Integer.toString(cursor.getInt(cursor.getColumnIndex(ContactContract.ContactEntry.CONTACT_ID)));
            String name = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.NAME));
            String email = cursor.getString(cursor.getColumnIndex(ContactContract.ContactEntry.EMAIL));

            contactInfo = contactInfo + "\n\n" + "ID : " + id +
                    "\nName : " + name + "\nEmail : " + email;

        }
        textDisplay.setText(contactInfo);

        contactDbHelper.close();

    }

}