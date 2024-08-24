package com.example.pethelp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SeeProducts extends AppCompatActivity {

    private Cursor mycursor;
    SQLiteDatabase db;
    AddFoodDBOpenHelper dbhelp;
    EditText txtStudentID;
    EditText txtS1;
    EditText txtS2;
    EditText txtS3;
    private int len;
    private int currentRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_products);

        try {
            txtStudentID = findViewById(R.id.foodid);
            txtS1 = findViewById(R.id.s1);
            txtS2 = findViewById(R.id.s2);
            txtS3 = findViewById(R.id.s3);

            dbhelp = new AddFoodDBOpenHelper(this);
            db = dbhelp.getWritableDatabase();

            readdata();
            if (mycursor != null && mycursor.moveToFirst()) {
                currentRec = 1;
                setdata();
            } else {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void readdata() {
        mycursor = db.query("foods", new String[]{"foodid", "s1", "s2", "s3"}, null, null, null, null, null);
        len = mycursor != null ? mycursor.getCount() : 0;
    }

    private void setdata() {
        if (mycursor != null && mycursor.moveToPosition(currentRec - 1)) {
            txtStudentID.setText(mycursor.getString(0));
            txtS1.setText(mycursor.getString(1));
            txtS2.setText(mycursor.getString(2));
            txtS3.setText(mycursor.getString(3));
        }
    }

    public void first(View v) {
        if (mycursor != null && len > 0) {
            mycursor.moveToFirst();
            currentRec = 1;
            setdata();
        }
    }

    public void next(View v) {
        if (mycursor != null && currentRec < len) {
            mycursor.moveToNext();
            currentRec++;
            setdata();
        } else {
            Toast.makeText(this, "This is the last record", Toast.LENGTH_SHORT).show();
        }
    }

    public void previous(View v) {
        if (mycursor != null && currentRec > 1) {
            mycursor.moveToPrevious();
            currentRec--;
            setdata();
        } else {
            Toast.makeText(this, "This is the first record", Toast.LENGTH_SHORT).show();
        }
    }

    public void last(View v) {
        if (mycursor != null && len > 0) {
            mycursor.moveToLast();
            currentRec = len;
            setdata();
        }
    }

    public void delete(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Record");
        builder.setMessage("Are you sure you want to delete this record?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String studentID = txtStudentID.getText().toString();
                dbhelp.deleteMark(db, studentID);
                Toast.makeText(SeeProducts.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                readdata();
                if (mycursor != null && mycursor.moveToFirst()) {
                    currentRec = 1;
                    setdata();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void update(View v) {
        String foodID = txtStudentID.getText().toString();
        String s1 = txtS1.getText().toString();
        String s2 = txtS2.getText().toString();
        String s3 = txtS3.getText().toString();

        ContentValues values = new ContentValues();
        values.put("s1", s1);
        values.put("s2", s2);
        values.put("s3", s3);

        int rowsAffected = db.update("foods", values, "foodid=?", new String[]{foodID});

        if (rowsAffected > 0) {
            Toast.makeText(this, "Record Updated", Toast.LENGTH_SHORT).show();
            readdata(); // Refresh the data
            setdata();  // Update the displayed data
        } else {
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
