package com.devmasterteam.festafimdeano.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.devmasterteam.festafimdeano.R;
import com.devmasterteam.festafimdeano.constants.FimDeAnoConstants;
import com.devmasterteam.festafimdeano.util.SecurityPrefences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPrefences mSecurityPrefences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        this.mSecurityPrefences = new SecurityPrefences(this);

        this.mViewHolder.checkParticipate = (CheckBox) findViewById(R.id.check_participate);

        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.check_participate) {
            if (this.mViewHolder.checkParticipate.isChecked()) {
                confirmedWillGo();
            } else {
                confirmedWontGo();
            }
        }
    }

    public void confirmedWillGo() {
        this.mSecurityPrefences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WILL_GO);
    }

    public void confirmedWontGo(){
        this.mSecurityPrefences.storeString(FimDeAnoConstants.PRESENCE, FimDeAnoConstants.CONFIRMED_WONT_GO);
    }


    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String presence = extras.getString(FimDeAnoConstants.PRESENCE);
            if (presence.equals(FimDeAnoConstants.CONFIRMED_WILL_GO)) {
                this.mViewHolder.checkParticipate.setChecked(true);
            } else {
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }
    }

    public static class ViewHolder {
        public CheckBox checkParticipate;
    }

    public ViewHolder getViewHolder() {
        return mViewHolder;
    }
}
