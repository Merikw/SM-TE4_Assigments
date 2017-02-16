package com.westerveld.merik.sm_te4_assigments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class HomeActivity extends AppCompatActivity{

    private String jsonText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                searchMovie();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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

    public void searchMovie(){
        System.out.println("Test");
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        JSONclass json = new JSONclass(txtTitle);
        EditText txtSearch = (EditText) findViewById(R.id.txtSearch);
        try {
            jsonText = json.execute("https://api.themoviedb.org/3/search/movie?api_key=6f4b1af5610cf0cf87fbaf18631e5823&language=en-US&query="+txtSearch.getText()+"&page=1&include_adult=false").get();
            JSONObject jsonObject = new JSONObject(jsonText);
            Movie movie = new Movie((String)jsonObject.get("Title"), Integer.parseInt((String)jsonObject.get("Year")), (String)jsonObject.get("Rated"),
                    (String)jsonObject.get("Runtime"), (String)jsonObject.get("Genre"), (String)jsonObject.get("Director"),
                    (String)jsonObject.get("Writer"), (String)jsonObject.get("Plot"), Double.parseDouble((String)jsonObject.get("imdbRating")),
                    (String)jsonObject.get("Poster"));
            txtTitle.setText(movie.getTitle());
            ImageView imgView = (ImageView) findViewById(R.id.imgView);
            new DownloadImageTask(imgView).execute(movie.getPicURL());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
