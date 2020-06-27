package hcmus.selab.finace101;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import hcmus.selab.finace101.support.RSSFeedActivity;
import hcmus.selab.finace101.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {
    int curr_money_saved = 0;
    ArrayList<String> rssLinks = new ArrayList<>();

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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
                        Log.d("@@@@@@@@@", money);

                    }
                })
                .show();
    }



}