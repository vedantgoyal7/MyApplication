package com.example.vedantgoyal.myapplication;

import android.arch.lifecycle.LifecycleObserver;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vedantgoyal.myapplication.fragment.ExampleFragm;
import com.example.vedantgoyal.myapplication.fragment.ExampleFragment;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements LifecycleObserver{
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static final String TAG = "button1";
    private static final String GAME_STATE_KEY = "GAME_STATE_KEY";
    private static final String TEXT_VIEW_KEY = "TEXT_VIEW_KEY";
    static final String STATE_SCORE = "playerScore";
    static final String STATE_LEVEL = "playerLevel";
    EditText editText;
    TextView mTextView;
    Uri uri= Uri.parse("content://contacts/");
    static final int PICK_CONTACT_REQUEST = 0;
    Boolean mSwitch=true;

    // some transient state for the activity instance
    int mGameState=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        mTextView = (TextView) findViewById(R.id.textView);


        // recovering the instance state
        if (savedInstanceState != null) {
            mGameState = savedInstanceState.getInt(STATE_SCORE);
            mTextView.setText(mGameState+"");

        }



    }

    public void sendMessage(View view) {

        Intent intent=new Intent(this,DisplayMessageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        startActivity(intent);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Log.i(TAG, "onActivityResult: works");
                String dcf= String.valueOf(data.getData());
                mTextView.setText(dcf);
            }
        }
    }

    public void replaceFragment(){
        // Create new fragment and transaction
        Fragment newFragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        if (mSwitch){
            mSwitch=false;
            newFragment = new ExampleFragment();
        }else {
            mSwitch=true;
            newFragment = new ExampleFragm();
        }
        transaction.replace(R.id.fragment_container,newFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(STATE_SCORE, 27);
        savedInstanceState.putInt(STATE_LEVEL, 7);
        Log.i(TAG, "onSaveInstanceState: ");
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    public void onGroupItemClick(MenuItem item) {
        // One of the group items (using the onClick attribute) was clicked
        // The item parameter passed here indicates which item it is
        // All other menu item clicks are handled by <code><a href="/reference/android/app/Activity.html#onOptionsItemSelected(android.view.MenuItem)">onOptionsItemSelected()</a></code>
    }



    @RequiresApi(api = Build.VERSION_CODES.O)

    public void onClick(View view) {
        //Create an instance of the ShortcutManager//

        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);

//Create a ShortcutInfo object that defines all the shortcut’s characteristics//

        ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "pinned-shortcut")
                .setShortLabel("Android Auth")
                .setLongLabel("Launch Android Authority")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .setIntent(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.androidauthority.com/")))
                .build();

        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut));

//Check that the device's default launcher supports pinned shortcuts//

//        if (shortcutManager.isRequestPinShortcutSupported()) {
//            ShortcutInfo pinShortcutInfo = new ShortcutInfo
//                    .Builder(MainActivity.this,"pinned-shortcut")
//                    .build();
//            Intent pinnedShortcutCallbackIntent =
//                    shortcutManager.createShortcutResultIntent(pinShortcutInfo);
//
////Get notified when a shortcut is pinned successfully//
//
//            PendingIntent successCallback = PendingIntent.getBroadcast(MainActivity.this, 0,
//                    pinnedShortcutCallbackIntent, 0);
//            shortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.getIntentSender());
//        }
    }
}