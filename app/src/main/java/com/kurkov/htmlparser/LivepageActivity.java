package com.kurkov.htmlparser;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class LivepageActivity extends Activity {
    private Document htmlDocument;
    private String htmlPageUrl = "";
    private TextView parsedHtmlNode;
    private String htmlContentInStringFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livepage);
        parsedHtmlNode = (TextView) findViewById(R.id.html_content);
        Button htmlTitleButton = (Button) findViewById(R.id.button);
        final EditText editText = (EditText) findViewById(R.id.editText);
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(editText.getText().toString())) {

                } else {
                    htmlPageUrl = "http://" + editText.getText().toString();
                    JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                    jsoupAsyncTask.execute();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_livepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                htmlDocument = Jsoup.connect(htmlPageUrl).get();
                htmlContentInStringFormat = htmlDocument.title();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            parsedHtmlNode.setText(htmlContentInStringFormat);
        }
    }
}