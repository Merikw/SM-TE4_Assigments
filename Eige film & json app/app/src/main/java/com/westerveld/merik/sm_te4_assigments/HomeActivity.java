package com.westerveld.merik.sm_te4_assigments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.R.attr.bitmap;

public class HomeActivity extends AppCompatActivity {

    private String jsonText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
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

    public void searchMovie() {
        System.out.println("Test");
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        JSONclass json = new JSONclass(txtTitle);
        EditText txtSearch = (EditText) findViewById(R.id.txtSearch);

        try {
            String s = "http://www.omdbapi.com/?s=" + txtSearch.getText();
            s = s.replaceAll("\\s+", "%20");
            System.out.println("Link:  " + s);
            jsonText = json.execute(s).get();
            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray jsonArray = jsonObject.getJSONArray("Search");
            ArrayList<Movie> movieList = new ArrayList<>();
            HorizontalScrollView hsv = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
            LinearLayout hsvLL = (LinearLayout) findViewById(R.id.hsvLinearLayout);
            hsvLL.removeAllViews();
            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println(i);
                JSONObject movie = jsonArray.getJSONObject(i);
                System.out.println(jsonArray.getJSONObject(i));
                Movie movieObj = new Movie(movie.getString("Title"), movie.getInt("Year"), movie.getString("Poster"));
                movieList.add(movieObj);
                System.out.println(movieObj.getTitle());

                LinearLayout lLayour = new LinearLayout(this.getBaseContext());
                lLayour.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(900, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.gravity=Gravity.CENTER;

                TextView txt = new TextView(this);
                txt.setText(movieObj.getTitle() + " ("+movieObj.getYear()+")");
                ImageView image = new ImageView(this);
                image.setPadding(25,25,25,25);
                Bitmap img = new DownloadImageTask(image).execute(movieObj.getPicURL()).get();
                lLayour.addView(txt);
                lLayour.addView(image);
                txt.setPadding(25, 25, 25 ,25);
                lLayour.setPadding(25, 25, 25, 25);
                Palette p  = Palette.from(img).generate();
                lLayour.setBackgroundColor(p.getLightMutedColor(0) * 2);
                hsvLL.addView(lLayour);
            }
//            txtTitle.setText(movieList.get(0).getTitle());
//            ImageView imgView = (ImageView) findViewById(R.id.imgView);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
