package com.example.pethelp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;  // Import this line
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddArticleActivity extends AppCompatActivity {

    private EditText titleInput, descriptionInput, linkInput;
    private Button submitButton;
    private AddFoodDBOpenHelper db;
    private SQLiteDatabase writableDb;  // Use the imported class here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_article);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize AddFoodDBOpenHelper
        db = new AddFoodDBOpenHelper(this);
        writableDb = db.getWritableDatabase();

        // Initialize fields
        titleInput = findViewById(R.id.titleInput);
        descriptionInput = findViewById(R.id.descriptionInput);
        linkInput = findViewById(R.id.linkInput);
        submitButton = findViewById(R.id.registerButton);

        // Set OnClickListener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleInput.getText().toString().trim();
                String description = descriptionInput.getText().toString().trim();
                String link = linkInput.getText().toString().trim();

                // Validate input
                if (title.isEmpty() || description.isEmpty() || link.isEmpty()) {
                    Toast.makeText(AddArticleActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert data into database
                    boolean isInserted = db.addArticle(writableDb, title, description, link);
                    if (isInserted) {
                        Toast.makeText(AddArticleActivity.this, "Article added successfully", Toast.LENGTH_SHORT).show();
                        // Clear fields
                        titleInput.setText("");
                        descriptionInput.setText("");
                        linkInput.setText("");


                    } else {
                        Toast.makeText(AddArticleActivity.this, "Failed to add article", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
