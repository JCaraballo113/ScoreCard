package jcaraballo.com.scorecard.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import Golf.Hole;
import jcaraballo.com.scorecard.Adapters.HoleAdapter;
import jcaraballo.com.scorecard.R;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE  = "com.jcaraballo.scorecard.preferences";
    private static final String LABEL_KEY = "LABEL_KEY";
    private static final String SCORE_KEY = "SCORE_KEY";

    private Hole[] mHoles = new Hole[18];
    private RecyclerView mRecyclerView;
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;
    private HoleAdapter mHoleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mSharedPreferences = getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        int strokes = 0;
        for (int i = 0; i < mHoles.length; i++) {
            strokes = mSharedPreferences.getInt(SCORE_KEY + i, 0);
            mHoles[i] = new Hole("Hole " + (i + 1) + " :", strokes);
        }

        mHoleAdapter = new HoleAdapter(mHoles,this);

        mRecyclerView.setAdapter(mHoleAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


    }

    @Override
    protected void onPause() {
        super.onPause();

        for (int i = 0; i < mHoles.length; i++) {
            mEditor.putInt(SCORE_KEY + i, mHoles[i].getScore());
        }

        mEditor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        if (id == R.id.clear_score) {
            mEditor.clear();
            mEditor.apply();

            for(Hole hole : mHoles) {
                hole.setScore(0);
            }
            mHoleAdapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }
}
