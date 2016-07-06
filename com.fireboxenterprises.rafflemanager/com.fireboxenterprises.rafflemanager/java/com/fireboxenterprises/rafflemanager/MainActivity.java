package com.fireboxenterprises.rafflemanager;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {
    public static int totalTickets = 0;
    public final int MAX_TICKETS = 5000;
    SmsManager sms = SmsManager.getDefault();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ((AdView) findViewById(R.id.adView)).loadAd(new Builder().addTestDevice("6C67750F5C28165E4B84A6218F6FCCF8").build());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.about /*2131558589*/:
                startActivity(new Intent(this, about.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void buttonClick(View v) {
        if (v.getId() == R.id.currentListButton) {
            startActivity(new Intent(this, currentList.class));
        } else if (v.getId() == R.id.startRaffleButton) {
            String SENT = "SMS_SENT";
            Iterator it = currentList.tickets.iterator();
            while (it.hasNext()) {
                Ticket ticket = (Ticket) it.next();
                PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
                int start = currentList.current + 1;
                for (int i = 0; i < Integer.parseInt(ticket.numberOfTickets); i++) {
                    currentList.current++;
                    currentList.numbers.add(String.valueOf(currentList.current));
                }
                if (Integer.parseInt(ticket.numberOfTickets) > 1) {
                    this.sms.sendTextMessage(ticket.phoneNumber, null, "Your numbers are: " + start + "-" + currentList.current, sentPI, null);
                } else {
                    this.sms.sendTextMessage(ticket.phoneNumber, null, "Your number is: " + currentList.current, sentPI, null);
                }
            }
            if (currentList.tickets.size() > 0) {
                startActivity(new Intent(this, drawTicket.class));
            } else {
                Toast.makeText(this, "ERROR: No entries added", 0).show();
            }
        } else if (v.getId() == R.id.addButton) {
            EditText p = (EditText) findViewById(R.id.phoneInput);
            EditText t = (EditText) findViewById(R.id.ticketsInput);
            if (p.getText().toString().length() != 10 || t.getText().toString().length() <= 0 || t.getText().toString().length() >= 4 || totalTickets + Integer.parseInt(t.getText().toString()) >= 5000) {
                Toast.makeText(this, "ERROR: Invalid phone number or you have exceeded the maximun ticket number", 1).show();
            } else {
                currentList.tickets.add(new Ticket(p.getText().toString(), t.getText().toString()));
                Toast.makeText(this, "Phone number added", 1).show();
            }
            p.setText(BuildConfig.FLAVOR);
            t.setText(BuildConfig.FLAVOR);
        }
    }
}
