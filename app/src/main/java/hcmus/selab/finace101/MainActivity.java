package hcmus.selab.finace101;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.DialogCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.zip.Inflater;

import hcmus.selab.finace101.support.ExtractNumber;
import hcmus.selab.finace101.ui.main.PlaceholderFragment;
import hcmus.selab.finace101.ui.main.SectionsPagerAdapter;
import hcmus.selab.finace101.ui.main.recordRecyclerView;

public class MainActivity extends AppCompatActivity implements PlaceholderFragment.addRecordListener{
    int curr_money_saved = 0;

    RecyclerView mRecyclerView;
    recordRecyclerView mAdapter;

    // Saved instance state keys.
    static final String STATE_FRAGMENT = "state_of_fragment";
    static String STATE_AMOUNT, STATE_TITLE, STATE_CAT;
    // Initialize the String title, amount, cat to the default.
//    private String mRec_amount, mRec_title, mRec_title;

    private boolean isFragmentDisplayed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

//        if (savedInstanceState != null) {
//            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
//            mRec_amount = savedInstanceState.getString(STATE_AMOUNT);
//            mRec_title = savedInstanceState.getString(STATE_TITLE);
//            mRec_amount = savedInstanceState.getString(STATE_CAT);
//        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddRecord(view);
            }
        });

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof PlaceholderFragment) {
            PlaceholderFragment headlinesFragment = (PlaceholderFragment) fragment;
            headlinesFragment.setRecordListener(this);
        }
    }

//    public void displayFragment() {
//        // Instantiate the fragment.
//        PlaceholderFragment placeholderFragment =
//                PlaceholderFragment.newInstance(mRadioButtonChoice);
//        // Get the FragmentManager and start a transaction.
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager
//                .beginTransaction();
//
//        // Add the SimpleFragment.
//        fragmentTransaction.add(R.id.fragment_container,
//                simpleFragment).addToBackStack(null).commit();
//
//        // Update the Button text.
//        mButton.setText(R.string.close);
//        // Set boolean flag to indicate fragment is open.
//        isFragmentDisplayed = true;
//    }

    public void AddRecord(final View view){
        LayoutInflater inflater = LayoutInflater.from(this);
        View addRecordDlog = inflater.inflate(R.layout.add_record_dialog, null);
        final EditText record_money_amount = (EditText) addRecordDlog.findViewById(R.id.add_amount);
        final EditText record_title = (EditText) addRecordDlog.findViewById(R.id.add_title);
        final String[] record_cat = new String[1];
        Spinner sp_cat = (Spinner) addRecordDlog.findViewById(R.id.add_cat_spinner);
        sp_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                record_cat[0] = parentView.getItemAtPosition(position).toString();
                Log.d("TAG", "onItemSelected: " + record_cat[0].toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //Assign Array String (name="addRecordDialogSpinner") in values/strings.xml to spinner
        ArrayAdapter<String> mSpinnerAddapter = new ArrayAdapter<String>(addRecordDlog.getContext(),
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.addRecordDialogSpinner));

        //Set spinner adapter
        mSpinnerAddapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_cat.setAdapter(mSpinnerAddapter);

        LayoutInflater inflater2 = LayoutInflater.from(this);
        final View curr_money_view = inflater2.inflate(R.layout.fragment_wallet, null);
        TextView curr_money_text = (TextView) curr_money_view.findViewById(R.id.current_money);
        final int curr_money = ExtractNumber.main(curr_money_text.getText().toString());

        new AlertDialog.Builder(this)
                .setView(addRecordDlog)
                .setTitle("Add Record")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // check if user inputted new value. If not set text the new_curr_money to 0
                        String change = record_money_amount.getText().toString();
                        String title = record_title.getText().toString();
                        String cat = null;

                        int Result;
                        int i = 0;

                        try {
                            i = Integer.parseInt(change);
                            cat = record_cat[0].toString();
                        }
                        catch (NumberFormatException e){
                            dialog.dismiss();
                            Toast.makeText(curr_money_view.getContext(), "Add Record Error", Toast.LENGTH_LONG).show();
                            Log.wtf("AddRecord_Error:", e.getMessage());
                        }

                        if(cat.equalsIgnoreCase("Expand")){
                            Log.d("TAG", "onClick: Expand");
                            Result = (int) curr_money - i;
                            TextView current_money = findViewById(R.id.current_money);
                            current_money.setText(Result + " vnd");
                        }else if(cat.equalsIgnoreCase("Income")){
                            Log.d("TAG", "onClick: Income");
                            Result = (int) curr_money + i;
                            TextView current_money = findViewById(R.id.current_money);
                            current_money.setText(Result + " vnd");
                        }else{
                            Toast.makeText(curr_money_view.getContext(), "Add Record Error", Toast.LENGTH_LONG).show();
                            return;
                        }

//                        addRecord(String.valueOf(i),title,cat);
//                        mRec_cat = cat;
//                        mRec_money = String.valueOf(i);
//                        mRec_title = title;


                        // get the new current money that users input
                        // the current error: cannot save the textView's android:text permanent


                    }
                })
                .show();

    }

    public void btn_curr_money_click(View view) {
        LayoutInflater inflater = LayoutInflater.from(this);
        // inflate the layout and find the EditText that used to edit the current money
        View edt_current_money = inflater.inflate(R.layout.edit_current_money, null);
        final EditText new_curr_money = (EditText) edt_current_money.findViewById(R.id.edt_current_money);

        LayoutInflater inflater2 = LayoutInflater.from(this);
        View curr_money_view = inflater2.inflate(R.layout.fragment_wallet, null);
        final TextView curr_money_text = (TextView) curr_money_view.findViewById(R.id.current_money);
        final int curr_money = ExtractNumber.main(curr_money_text.getText().toString());
        Log.d("curr_money", String.valueOf(curr_money_saved));

        new AlertDialog.Builder(this)
                .setView(edt_current_money)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // check if user inputted new value. If not set text the new_curr_money to 0
                        String change = new_curr_money.getText().toString();
                        int i = 0;
                        try {
                            i = Integer.parseInt(change);
                        }
                        catch (NumberFormatException e){
                            dialog.dismiss();
                            new_curr_money.setText("0");
                            Log.d("catch the error", "I saw you!");
                        }

                        // create a new edited_money variable with int type
                        String money = new_curr_money.getText().toString();
                        int record = 0;
                        int edited_money = Integer.parseInt(money);

                        // check if the new inputted money is 0
                        if (edited_money != 0) {
                            // set the view to the new value of the money
                            TextView current_money = findViewById(R.id.current_money);
                            current_money.setText(money + " vnd");
                            curr_money_saved = edited_money;

                            // if the new money > the current money
                            if (edited_money > curr_money)
                            {
                                // the record = new money - current money
                                record = edited_money - curr_money;

                                // add the record value to list of record with tittle = "unknow"
                                Log.d("I know you are there", "The new value is greater " + String.valueOf(record));


                            }
                            else {
                                // else the record = current money - new
                                record = curr_money - edited_money;

                                // add the record value to list of record with tittle = "unknow"
                                Log.d("I know you are there", "The new value is smaller " + String.valueOf(record));
                            }
                        }

                        // get the new current money that users input
                        // the current error: cannot save the textView's android:text permanent

                        Log.d("@@@@@@@@@", money);

                    }
                })
                .show();

        Log.d("After update", curr_money_text.getText().toString());
    }

    @Override
    public void addRecord(String moneyAmount, String mtitle, String mcat) {
//        LinkedList<String> amount_list = new LinkedList<>(), title_list = new LinkedList<>(), cat_list = new LinkedList<>();
//        amount_list.addLast(moneyAmount);
//        title_list.addLast(title);
//        cat_list.addLast(cat);


    }

//    public interface addRecordListener {
//        public void addRecord(String moneyAmount, String title, String cat);
//    }

//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        // Save the state of the fragment (true=open, false=closed).
//        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
//        savedInstanceState.putInt(STATE_CHOICE, mRadioButtonChoice);
//        super.onSaveInstanceState(savedInstanceState);
//    }
}