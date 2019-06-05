/**
 * <ul>
 * <li>Human</li>
 * <li>com.android2ee.formation.android.idgeo.juilmmxvi.firstporject</li>
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

package com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.android2ee.formation.android.idgeo.juilmmxvi.firstporject.R;
import com.orm.SugarRecord;

/**
 * Created by Mathias Seguy - Android2EE on 02/08/2016.
 */
public class Human extends SugarRecord implements Parcelable {
    private String name;
    private String firstname;
    private String message;
    private int pictureId;

    public Human() {
    }

    public Human(String name,String message){
        this.name=name;
        this.message=message;
        firstname="Unknow";
        pictureId=R.mipmap.ic_unknown;
    }
    public Human(String firstname, String message, String name, int pictureId) {
        this.firstname = firstname;
        this.message = message;
        this.name = name;
        this.pictureId = pictureId;
    }

    public Human(String message, int position) {
        this.message = message;
        if(position%2==0) {
            name = "Doe";
            firstname = "John";
            pictureId = R.mipmap.ic_human_even;
        }else{

            name = "Doe";
            firstname = "Jane";
            pictureId = R.mipmap.ic_human_odd;
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    protected Human(Parcel in) {
        name = in.readString();
        firstname = in.readString();
        message = in.readString();
        pictureId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(firstname);
        dest.writeString(message);
        dest.writeInt(pictureId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Human> CREATOR = new Parcelable.Creator<Human>() {
        @Override
        public Human createFromParcel(Parcel in) {
            return new Human(in);
        }

        @Override
        public Human[] newArray(int size) {
            return new Human[size];
        }
    };
}

