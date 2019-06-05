package com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.view.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.R;
import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.model.Human;
import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.transverse.Constants;

public class DetailActivity extends AppCompatActivity {
    ImageView imvHumanPicture;
    TextView txvHumanName;
    TextView txvHumanFirstName;
    TextView txvHumanMessage;
    Human myHuman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txvHumanFirstName= (TextView) findViewById(R.id.txvHumanFirstName);
        txvHumanName= (TextView) findViewById(R.id.txvHumanName);
        txvHumanMessage= (TextView) findViewById(R.id.txvHumanMessage);
        imvHumanPicture=(ImageView)findViewById(R.id.imvHumanPicture);
        //then find the fucking human to display says the bot
        myHuman= (Human) getIntent().getExtras().get(Constants.HUMAN_BUNDLE_KEY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        txvHumanName.setText(myHuman.getName());
        txvHumanFirstName.setText(myHuman.getFirstname());
        txvHumanMessage.setText(myHuman.getMessage());
        imvHumanPicture.setImageResource(myHuman.getPictureId());
    }
}
