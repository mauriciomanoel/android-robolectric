package com.devmasterteam.festafimdeano.views;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devmasterteam.festafimdeano.R;
import com.devmasterteam.festafimdeano.constants.FimDeAnoConstants;
import com.devmasterteam.festafimdeano.util.SecurityPrefences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPrefences mSecurityPrefences;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mViewHolder.textToday = (TextView) findViewById(R.id.text_today);
        this.mViewHolder.textDaysLeft = (TextView) findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = (Button) findViewById(R.id.button_confirm);
        this.mViewHolder.btnDetailContracheque = (Button) findViewById(R.id.btn_show_detail_contracheque);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);
        this.mViewHolder.btnDetailContracheque.setOnClickListener(this);

        this.mSecurityPrefences = new SecurityPrefences(this);

        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeftToEndOfYear()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_confirm) {

            String presence = this.mSecurityPrefences.getStoredString(FimDeAnoConstants.PRESENCE);

            // Lógica de navegação
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE, presence);

            startActivity(intent);
        } else if (id == R.id.btn_show_detail_contracheque) {

            showNotification();
            //Intent intent = new Intent(this, ExampleActivity.class);
            //startActivity(intent);

        }
    }

    private void showNotification() {

        String CHANNEL_ID = "my_channel_01";
        NotificationCompat.Builder mBuilder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_name";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("textTitle1")
                    .setContentText("textContent1")
                    .setChannelId(CHANNEL_ID)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        } else {

            mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("textTitle")
                    .setContentText("textContent")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, mBuilder.build());


    }


    private int getDaysLeftToEndOfYear(){
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);

        Calendar calendarLastDay = Calendar.getInstance();
        int dayDeceber31 = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayDeceber31 - today;
    }

    private void verifyPresence(){
        String presence = this.mSecurityPrefences.getStoredString(FimDeAnoConstants.PRESENCE);
        if (presence.equals(""))
            this.mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        else if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO))
            this.mViewHolder.buttonConfirm.setText(R.string.sim);
        else
            this.mViewHolder.buttonConfirm.setText(R.string.nao);
    }

    public static class ViewHolder {
        public TextView textToday;
        public TextView textDaysLeft;
        public Button buttonConfirm;
        public Button btnDetailContracheque;
    }

    public ViewHolder getViewHolder() {
        return this.mViewHolder;
    }
}
