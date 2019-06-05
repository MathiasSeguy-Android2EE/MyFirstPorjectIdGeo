package com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.view.main;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.R;
import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.model.Human;
import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.transverse.Constants;
import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.view.detail.DetailActivity;
import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.view.generic.MotherActivity;

import java.util.ArrayList;

public class MainActivity extends MotherActivity {
    /***********************************************************
     * Constants
     **********************************************************/
    /**
     * Used as Key of the Bundle associated to the life cycle of this
     */
    public static final String EDT_VALUE = "EDT_VALUE";
    public static final String LIST_VALUE = "LIST_VALUE";
    private static final String TAG = "MainActivity";
    /**
     * The constant used for displaying
     */
    private static final int ALERTDIALOG_COPY=110274;
    /***********************************************************
     *  Attributes
     **********************************************************/

    /**
     * The EditText that contains the text of the user
     */
    private EditText edtMessage = null;
    /**
     * The Area where you display all the string of the user
     */
    private ListView lsvResult = null;
    /**
     * The list of messages of the user
     */
    private ArrayList<Human> humen = null;
    /**
     * The arrayAdapter of the list view can be thought as the brain of the listview
     */
    private HumanArrayAdapter humanArrayAdapter = null;
    /**
     * The Button that add the content of the EditText within the Result area
     */
    private Button btnAdd = null;
    /**
     * The tween animation
     */
    private Animation animBtnAddGingerbread=null;
    /**
     * The AnimatorSet for post Gingerbread animations
     */
    private AnimatorSet animatorSet=null;
    /**
     * To know if post Gingerbread or not
     */
    private boolean postGingerbread;
    /**
     * When an item is selected for something we use that temp variable:
     */
    private int positionOfSelectedItem;

    /***********************************************************
     * Managing LifeCycle
     **********************************************************/

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //blabla this is a comment
        super.onCreate(savedInstanceState);
        postGingerbread=getResources().getBoolean(R.bool.postGinger);
        //create your view:parse the xml and create the View Java object
        setContentView(R.layout.activity_main);
        //then initialize the graphical components
        edtMessage = (EditText) findViewById(R.id.edtMessage);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        lsvResult = (ListView) findViewById(R.id.lsvResult);
        //then initialize the "friends" of the listview
        humen = new ArrayList<>();
        if(savedInstanceState!=null){
            for(Parcelable mes:savedInstanceState.getParcelableArrayList(LIST_VALUE)){
                humen.add((Human) mes);
            }
        }else{
            //load humen from database
            for(Human human:Human.listAll(Human.class)) {
                Log.e(TAG,"one more human found in database");
                humen.add(0,human);
            }
        }
        humanArrayAdapter = new HumanArrayAdapter(this, humen);
        lsvResult.setItemsCanFocus(true);
        lsvResult.setAdapter(humanArrayAdapter);
        if(postGingerbread) {
            //Do ics animation
            animatorSet= (AnimatorSet) AnimatorInflater.loadAnimator(this,R.animator.anim_btn_add);
            animatorSet.setTarget(btnAdd);
        }else{
            //Do gingerbread animation
            animBtnAddGingerbread= AnimationUtils.loadAnimation(this,R.anim.bump_btn_add);
        }



        //then add the listeners
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyFromEdtToResultArea();
            }
        });
        //the click listener of the listview
        lsvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tryCopyItemInEditText(position);
            }
        });
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EDT_VALUE,edtMessage.getText().toString());
//        outState.putStringArrayList("LIST_VALUE", humen);
        outState.putParcelableArrayList(LIST_VALUE,humen);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        edtMessage.setText(savedInstanceState.getString(EDT_VALUE));

    }
    /***********************************************************
     *  Managing Menu
     **********************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itmDeleteAll:
                deleteAll();
                        return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /***********************************************************
     *  Managing AlertDialog
     **********************************************************/
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case ALERTDIALOG_COPY:
                //build and return it;
                return buildAlertDialogCopy();
            default:
                return super.onCreateDialog(id);
        }
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        if(id==ALERTDIALOG_COPY){
            ((AlertDialog)dialog).setMessage(getString(R.string.alertDialogMessage,humanArrayAdapter.getItem(positionOfSelectedItem).getMessage()));
        }
        super.onPrepareDialog(id, dialog);
    }

    /**
     * Build the AlertDialog when trying to copy Item's message in EDT
     * @return
     */
    private Dialog buildAlertDialogCopy(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //define your Dialog
        builder.setMessage(getString(R.string.alertDialogMessage,"toto"))
                .setTitle(getString(R.string.alertDialogTitle))
                .setPositiveButton(R.string.alertDialogPositive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        copyItemInEditText();
                    }
                })
                .setNegativeButton(R.string.alertDialogNegative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        copyNotAllowed();
                    }
                });

        //and return it
        return builder.create();
    }

    /***********************************************************
     *  Business Methods
     **********************************************************/
    /**
     * The temp string
     */
    private String messageTemp;

    /**
     * Copy the content of the EditText within the Result Area
     */
    @SuppressLint("NewApi")
    public void copyFromEdtToResultArea() {
        if(postGingerbread) {
            //Do ics animation
            animatorSet.start();
        }else{
            //Do gingerbread animation
            btnAdd.startAnimation(animBtnAddGingerbread);
        }
        messageTemp = edtMessage.getText().toString();
        //first way
        Human human=new Human(messageTemp,humen.size());
        human.save();
        humen.add(0,human);
        humanArrayAdapter.notifyDataSetChanged();
        //second
//        arrayAdapter.add(messageTemp);
        edtMessage.setText("");
        
    }

    /**
     * Ask if we copy the value of the item at the position position within the edittext
     *
     * @param position
     */
    private void tryCopyItemInEditText(int position) {
        positionOfSelectedItem=position;
        showDialog(ALERTDIALOG_COPY);
    }

    /**
     * Copy the value of the item at the position position within the edittext
     */
    private  void copyItemInEditText() {
        edtMessage.setText(humanArrayAdapter.getItem(positionOfSelectedItem).getMessage());
    }

    /**
     * Do NOT Copy the value of the item at the position position within the edittext
     * And display a stupid toast instead
     */
    private void copyNotAllowed(){
        Toast.makeText(this,getString(R.string.tstCopyNotAllowed),Toast.LENGTH_LONG).show();
    }

    /**
     * Delete all the humen from the list
     */
    private void deleteAll(){
        humanArrayAdapter.clear();
        Human.deleteAll(Human.class);
    }

    /***********************************************************
     *  Start others activities
     **********************************************************/
    /**
     * Launch the activity DetailActivity and displays the current item
     * @param hum
     */
    public void displayHuman(Human hum){
        Intent startDetAct=new Intent(this, DetailActivity.class);
        Bundle toto=new Bundle();
        toto.putParcelable(Constants.HUMAN_BUNDLE_KEY,hum);
        startDetAct.putExtras(toto);
        startActivity(startDetAct);
    }
}
