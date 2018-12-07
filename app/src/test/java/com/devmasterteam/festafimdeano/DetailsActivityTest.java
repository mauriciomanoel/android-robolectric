package com.devmasterteam.festafimdeano;



import com.devmasterteam.festafimdeano.views.DetailsActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by E898 on 12/7/2018.
 */

@RunWith(RobolectricTestRunner.class)
public class DetailsActivityTest {

    @Test
   public void setYouParticipate() {

       DetailsActivity activity = Robolectric.setupActivity(DetailsActivity.class);
       activity.getViewHolder().checkParticipate.performClick();

       assertEquals(activity.getViewHolder().checkParticipate.isChecked(), true);

   }




}
