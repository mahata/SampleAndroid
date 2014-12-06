package ymahata.slis.tsukuba.ac.jp.sampleandroid;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity implements TextWatcher {

    private static final String TAG = "MainActivity";
    private static final int MAX_STRING_LENGTH = 140;
    private static final String MEMO_KEY = "memo_key";
    private SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        myPrefs = getPreferences(MODE_PRIVATE);
        String memo = myPrefs.getString(MEMO_KEY, "");

        EditText ed = (EditText) findViewById(R.id.editText);
        ed.addTextChangedListener(this);
        ed.setText(memo);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void afterTextChanged(Editable s) {
        String memo = s.toString();

        TextView tv = (TextView) findViewById(R.id.textView);
        tv.setText("Chars remaining: " + (MAX_STRING_LENGTH - s.length()));

        myPrefs.edit().putString(MEMO_KEY, s.toString()).commit();
        Log.d(TAG, "Checking for " + memo);
    }
}
