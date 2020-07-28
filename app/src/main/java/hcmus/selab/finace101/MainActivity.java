package hcmus.selab.finace101;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.LinkedList;

import hcmus.selab.finace101.ui.main.PlaceholderFragment;

import java.util.ArrayList;

import hcmus.selab.finace101.support.RSSFeedActivity;

import hcmus.selab.finace101.ui.main.SectionsPagerAdapter;
import hcmus.selab.finace101.ui.main.recordRecyclerView;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity implements PlaceholderFragment.addRecordListener{
    int curr_money_saved = 0;
    ArrayList<String> rssLinks = new ArrayList<>();

    RecyclerView recordRecyclerView;
    LinkedList<String> mRecordAmount_list = new LinkedList<String>();
    LinkedList<String> mRecordTitle_list = new LinkedList<String>();
    LinkedList<String> mRecordCat_list = new LinkedList<String>();


    @Override
    public void getRecyclerView(RecyclerView recyclerView) {
        this.recordRecyclerView = recyclerView;
        this.recordRecyclerView.setAdapter(new recordRecyclerView(this,mRecordAmount_list, mRecordTitle_list,mRecordCat_list));
        this.recordRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration decor = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        this.recordRecyclerView.addItemDecoration(decor);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        final TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddRecord(view);
            }
        });

        rssLinks.add("https://tuoitre.vn/rss/kinh-doanh.rss");
        rssLinks.add("https://www.theguardian.com/profile/charliebrooker/rss");
        rssLinks.add("https://www.cnbc.com/id/21324812/device/rss/rss.html");

    }

    //test rss
    public void onclick_rss(View view) {
        switch (view.getId()) {
            case R.id.tuoitre_feed:
                startActivity(new Intent(MainActivity.this, RSSFeedActivity.class).putExtra("rssLink", rssLinks.get(0)));
                break;

            case R.id.theguardian_feed:
                startActivity(new Intent(MainActivity.this, RSSFeedActivity.class).putExtra("rssLink", rssLinks.get(1)));
                break;

            case R.id.cnbc_feed:
                startActivity(new Intent(MainActivity.this, RSSFeedActivity.class).putExtra("rssLink", rssLinks.get(2)));
                break;
        }
    }

    public void AddRecord(final View view){
        LayoutInflater inflater = LayoutInflater.from(this);
        final View addRecordDlog = inflater.inflate(R.layout.add_record_dialog, null);
        final EditText record_money_amount = (EditText) addRecordDlog.findViewById(R.id.add_amount);
        final EditText record_title = (EditText) addRecordDlog.findViewById(R.id.add_title);
        final String[] record_cat = new String[1];
        Spinner sp_cat = (Spinner) addRecordDlog.findViewById(R.id.add_cat_spinner);
        sp_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                try {
                    record_cat[0] = parentView.getItemAtPosition(position).toString();

                }catch (Exception e){
                    Toast.makeText(addRecordDlog.getContext(), "Add Record Error", Toast.LENGTH_LONG).show();
                    Log.wtf("AddRecord_Error:", e.getMessage());
                    return;
                }
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

        new AlertDialog.Builder(this)
                .setView(addRecordDlog)
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
                        String title ="";
                        String cat = null;

                        int Result;
                        int i = 0;
                        title = record_title.getText().toString();
                        if (title.isEmpty()){
                            Toast.makeText(curr_money_view.getContext(), "Add Record Error", Toast.LENGTH_LONG).show();
                            return;
                        }
                        try {
                            i = Integer.parseInt(change);
                            cat = record_cat[0].toString();
                        }
                        catch (Exception e){
                            dialog.dismiss();
                            Toast.makeText(curr_money_view.getContext(), "Add Record Error", Toast.LENGTH_LONG).show();
                            Log.wtf("AddRecord_Error:", e.getMessage());
                            return;
                        }

                        if(cat.equalsIgnoreCase("Expand")){
                            curr_money_saved -= i;
                            TextView current_money = findViewById(R.id.current_money);
                            current_money.setText(curr_money_saved + " vnd");
                        }else if(cat.equalsIgnoreCase("Income")){
                            curr_money_saved += i;
                            TextView current_money = findViewById(R.id.current_money);
                            current_money.setText(curr_money_saved + " vnd");
                        }else{
                            Toast.makeText(curr_money_view.getContext(), "Add Record Error", Toast.LENGTH_LONG).show();
                            return;
                        }

                        addRecord(String.valueOf(i) + " vnd",title,cat);


                    }
                })
                .show();

    }


    public void btn_curr_money_click(View view) {
        // inflate the layout and find the EditText that used to edit the current money
        LayoutInflater inflater = LayoutInflater.from(this);
        View edt_current_money = inflater.inflate(R.layout.edit_current_money, null);
        final EditText new_curr_money = (EditText) edt_current_money.findViewById(R.id.edt_current_money);

        // get the current money
        final int curr_money = curr_money_saved;

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
                                record = edited_money - abs(curr_money);

                                // add the record value to list of record with tittle = "unknow"
                                addRecord(String.valueOf(record) + " vnd","Unknown","Income");


                            }
                            else if(edited_money < curr_money){
                                // else the record = current money - new
                                record = abs(curr_money) - edited_money;

                                // add the record value to list of record with tittle = "unknow"
                                addRecord(String.valueOf(record) + " vnd","Unknown","Expand");
                            }
                        }

                    }
                })
                .show();
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof PlaceholderFragment){
            PlaceholderFragment recyclerViewFragment = (PlaceholderFragment) fragment;
            recyclerViewFragment.setFragListener(this);
        }
    }

    public void addRecord(String amount, String Tit, String cat){
        //Add item to list
        this.mRecordAmount_list.addFirst(amount);
        this.mRecordTitle_list.addFirst(Tit);
        this.mRecordCat_list.addFirst(cat);
        //Notify the recyclerView to change data
        this.recordRecyclerView.getAdapter().notifyItemInserted(0);

    }


}