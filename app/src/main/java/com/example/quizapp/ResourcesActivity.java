package com.example.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class ResourcesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setLinkClickListener(R.id.tvCResources, "https://www.learn-c.org/");
        setLinkClickListener(R.id.tvPythonResources, "https://docs.python.org/3/");
        setLinkClickListener(R.id.tvJavaResources, "https://docs.oracle.com/javase/tutorial/");
        setLinkClickListener(R.id.tvJavaScriptResources, "https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide");
        setLinkClickListener(R.id.tvHtmlResources, "https://html.spec.whatwg.org/");

    }

    private void setLinkClickListener(int textViewId, final String url) {
        TextView textView = findViewById(textViewId);
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }
}