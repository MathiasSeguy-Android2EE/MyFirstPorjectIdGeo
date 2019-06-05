/**
 * <ul>
 * <li>HumanArrayAdapter</li>
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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.R;
import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.model.Human;

import java.util.ArrayList;

/**
 * Created by Mathias Seguy - Android2EE on 02/08/2016.
 */
public class HumanArrayAdapter extends ArrayAdapter<Human> {
    /***********************************************************
     *  Attributes
     **********************************************************/
    /**
     * The guy who can read xml file of layout type and build the associated Java View (the objects)
     */
    LayoutInflater inflater;
    /***********************************************************
     *  Temp var
     **********************************************************/

    /**HumanTemp
     *
     */
    Human humanTemp;
    /**
     * View Temp
     */
    View rowViewTemp;
    /**
     * The temp ViewHolder
     */
    HumanArrayAdapterViewHolder vh;
    /**
     * The Activity that contains/uses this
     */
    private MainActivity mainActivity;
    /***********************************************************
     *  Constructors
     **********************************************************/
    /**
     * Constructor
     */
    public HumanArrayAdapter(Context context, ArrayList<Human> dataset) {
        super(context, R.layout.human_even,dataset);
        inflater=LayoutInflater.from(context);
        mainActivity= (MainActivity) context;
    }
    /***********************************************************
    *  Business Methods
    **********************************************************/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //find the item
        humanTemp=getItem(position);
        //initialize the view
        rowViewTemp=convertView;
        //if rowViewTemp is null: create it
        if(rowViewTemp==null){
            if(getItemViewType(position)==0){
                rowViewTemp=inflater.inflate(R.layout.human_even,parent,false);
            }else {
                rowViewTemp = inflater.inflate(R.layout.human_odd, parent, false);
            }
            vh=new HumanArrayAdapterViewHolder(rowViewTemp,this);
            rowViewTemp.setTag(vh);
        }
        //now update the view using the ViewHolder
        vh= (HumanArrayAdapterViewHolder) rowViewTemp.getTag();
        vh.setCurrentPosition(position);
        vh.getImvPicture().setImageResource(humanTemp.getPictureId());
        vh.getTxvNameAndFirstname().setText(humanTemp.getName()+" "+humanTemp.getFirstname());
        vh.getTxvMessage().setText(humanTemp.getMessage());
        return rowViewTemp;
    }
    /***********************************************************
     *  Business method
     **********************************************************/
    /**
     * Delete the current item at the position below:
     * @param position
     */
    public void deleteItem(int position){
        //So you just need to removed it
        Human human=getItem(position);
        remove(human);
        human.delete();
        notifyDataSetChanged();
    }
    /**
     * Display the current item at the position below:
     * @param position
     */
    public void displayItem(int position){
        //ask somewhere to display the selected item in the DetailActivity
        mainActivity.displayHuman(getItem(position));
    }
    /***********************************************************
     * Managing Odd/even view
     **********************************************************/
    @Override
    public int getItemViewType(int position) {
        //depending on the position (== item) in which pool of convertview I take the view
        humanTemp=getItem(position);
        return humanTemp.getFirstname().equals("John")?0:1;
//        return position%2==0?0:1;
    }

    @Override
    public int getViewTypeCount() {
        //how much convert view pools do I have
        return 2;
    }
}
