package com.example.pethelp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewArticleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArticleAdapter articleAdapter;
    private List<Article> articleList;
    private List<Article> filteredArticleList;

    private AddFoodDBOpenHelper dbHelper;
    private EditText searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_article);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize DB Helper
        dbHelper = new AddFoodDBOpenHelper(this);

        // Fetch articles from the database
        articleList = fetchArticlesFromDB();
        filteredArticleList = new ArrayList<>(articleList);

        // Set the adapter
        articleAdapter = new ArticleAdapter(filteredArticleList);
        recyclerView.setAdapter(articleAdapter);

        // Initialize the search bar
        searchBar = findViewById(R.id.search_bar);
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No action needed before text changes
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterArticles(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No action needed after text changes
            }
        });
    }

    private List<Article> fetchArticlesFromDB() {
        List<Article> articles = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("articles", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
                String link = cursor.getString(cursor.getColumnIndexOrThrow("link"));

                articles.add(new Article(title, description, link));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return articles;
    }

    private void filterArticles(String query) {
        filteredArticleList.clear();

        if (query.isEmpty()) {
            filteredArticleList.addAll(articleList);
        } else {
            for (Article article : articleList) {
                if (article.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredArticleList.add(article);
                }
            }
        }

        articleAdapter.notifyDataSetChanged();
    }
}
