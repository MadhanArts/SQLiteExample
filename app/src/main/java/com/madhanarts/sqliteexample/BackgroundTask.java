package com.madhanarts.sqliteexample;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

public class BackgroundTask extends AsyncTask<String, String, String> {

    private Context context;
    private Activity activity;


    private TextView textDisplay;

    private String contactInfo;

    public BackgroundTask(Context context) {
        this.context = context;
        activity = (Activity) context;
    }



    // onPreExecute() will work on main Thread
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // doInBackground() will work in separate Thread
    @Override
    protected String doInBackground(String... params) {

        if (params[0].equals("add_contact"))
        {

            ContactDbHelper contactDbHelper = new ContactDbHelper(context);

            SQLiteDatabase database = contactDbHelper.getWritableDatabase();

            String id = params[1];
            String name = params[2];
            String email = params[3];

            contactDbHelper.addContact(Integer.parseInt(id), name, email, database);

            contactDbHelper.close();

            return "Contact saved successfully...";

        }
        else if (params[0].equals("get_contacts"))
        {

            //Should not set the value of view in doInBackground()
            // doInBackground() will run in separate thread... View can only be initialized in main thread

            textDisplay = activity.findViewById(R.id.text_contact_display);

            contactInfo = "";

            ContactDbHelper contactDbHelper = new ContactDbHelper(context);

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

            publishProgress(contactInfo);

            contactDbHelper.close();
            cursor.close();

            return "get_contacts";

        }

        return null;
    }


    // onProgressUpdate() will work on main Thread
    @Override
    protected void onProgressUpdate(String... values) {

        textDisplay.setText(values[0]);
    }


    // onPostExecute() will work on main Thread
    @Override
    protected void onPostExecute(String result) {

        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

    }

}
