package com.devmasterteam.festafimdeano;

import com.devmasterteam.festafimdeano.views.DetailsActivity;
import com.devmasterteam.festafimdeano.views.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;


/**
 * Created by E898 on 12/7/2018.
 */
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void confirmedWillGo() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        assertEquals(activity.getViewHolder().buttonConfirm.getText().toString(), "Não Confirmado");

        DetailsActivity activity2 = Robolectric.setupActivity(DetailsActivity.class);
        activity2.getViewHolder().checkParticipate.performClick();

        assertEquals(activity2.getViewHolder().checkParticipate.isChecked(), true);

        MainActivity activity3= Robolectric.setupActivity(MainActivity.class);
        assertEquals(activity3.getViewHolder().buttonConfirm.getText().toString(), "sim");
    }

   

}
