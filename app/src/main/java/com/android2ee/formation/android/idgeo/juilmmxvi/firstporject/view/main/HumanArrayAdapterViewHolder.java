/**
 * <ul>
 * <li>HumanArrayAdapterViewHolder</li>
 * <li>com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.view</li>
 * <li>02/08/2016</li>
 * <p/>
 * <li>======================================================</li>
 * <p/>
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 * <p/>
 * /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br>
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 * Belongs to <strong>Mathias Seguy</strong></br>
 * ***************************************************************************************************************</br>
 * This code is free for any usage but can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * <p/>
 * *****************************************************************************************************************</br>
 * Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 * Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br>
 * Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */

package com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.view.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.R;

/**
 * Created by Mathias Seguy - Android2EE on 02/08/2016.
 */
public class HumanArrayAdapterViewHolder {
    private static final String TAG = "HumanAAdapterVHolder";
    private TextView txvNameAndFirstname;
    private TextView txvMessage;
    private ImageView imvPicture;
    private ImageView imvDelete;
    private HumanArrayAdapter humanArrayAdapter;
    private int currentPosition;

    public HumanArrayAdapterViewHolder(View rootView,HumanArrayAdapter arrayAdpt) {
        //instantiate elements
        humanArrayAdapter=arrayAdpt;
        txvMessage= (TextView) rootView.findViewById(R.id.txvMessage);
        txvNameAndFirstname= (TextView) rootView.findViewById(R.id.txvNameFirstName);
        imvPicture=(ImageView)rootView.findViewById(R.id.imvPicture);
        imvDelete= (ImageView) rootView.findViewById(R.id.imvDelete);
        //add listeners
        imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCurrentItem();
            }
        });
        imvPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCurrentItem();
            }
        });
    }

    /**
     * Try to delete the current item
     */
    private void deleteCurrentItem(){
        //To do that, you need to tell it to the ArrayAdapter
        humanArrayAdapter.deleteItem(currentPosition);
    }

    /**
     * Ask somewhere to display the current otem
     */
    private void displayCurrentItem(){
        humanArrayAdapter.displayItem(currentPosition);
    }
    /***********************************************************
    *  Getters/Setters
    **********************************************************/

    public ImageView getImvPicture() {
        return imvPicture;
    }

    public TextView getTxvMessage() {
        return txvMessage;
    }

    public TextView getTxvNameAndFirstname() {
        return txvNameAndFirstname;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
