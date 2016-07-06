package com.fireboxenterprises.rafflemanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;

public class drawTicket extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_ticket);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ((AdView) findViewById(R.id.adView)).loadAd(new Builder().addTestDevice("6C67750F5C28165E4B84A6218F6FCCF8").build());
        TextView winningNumber = (TextView) findViewById(R.id.winningRaffle);
        if (currentList.numbers.size() > 0) {
            winningNumber.setText((CharSequence) currentList.numbers.get((int) Math.floor(Math.random() * ((double) currentList.current))));
        }
    }

    public void resultButtonClick(View v) {
        if (v.getId() == R.id.newRaffleButton) {
            currentList.current = 0;
            currentList.tickets.clear();
            currentList.numbers.clear();
            finish();
        } else if (v.getId() == R.id.keepRaffleButton) {
            currentList.current = 0;
            currentList.numbers.clear();
            finish();
        }
    }
}
