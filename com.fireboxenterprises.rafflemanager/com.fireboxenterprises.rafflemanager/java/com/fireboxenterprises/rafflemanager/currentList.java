package com.fireboxenterprises.rafflemanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import java.util.ArrayList;
import java.util.Iterator;

public class currentList extends AppCompatActivity {
    public static int current = 0;
    public static ArrayList<String> numbers = new ArrayList();
    public static ArrayList<Ticket> tickets = new ArrayList();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_list);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        TextView textView = (TextView) findViewById(R.id.ticketList);
        textView.setMovementMethod(new ScrollingMovementMethod());
        Iterator it = tickets.iterator();
        while (it.hasNext()) {
            Ticket t = (Ticket) it.next();
            textView.setText(textView.getText() + "\n" + t.phoneNumber + "    " + t.numberOfTickets);
        }
        ((AdView) findViewById(R.id.adView)).loadAd(new Builder().addTestDevice("6C67750F5C28165E4B84A6218F6FCCF8").build());
    }

    public void listButtonClick(View v) {
        if (v.getId() == R.id.deleteButton) {
            if (tickets.size() > 0) {
                new AlertDialog.Builder(this).setTitle("Delete").setMessage("Are you sure you want to delete the entries?").setPositiveButton(17039379, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        currentList.tickets.clear();
                        currentList.this.finish();
                    }
                }).setNegativeButton(17039369, new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setIcon(17301543).show();
            }
        } else if (v.getId() == R.id.returnButton) {
            finish();
        }
    }
}
