package com.example.dara.miriamrecipes.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Step implements Parcelable {

    //Fields
    private int mId;
    private String mShortDescription;
    private String mVideoUrl;
    private String mThumbnailUrl;

    //Getter methods
    public Step (int id, String shortDescription, String videoUrl, String thumbnailUrl) {
        mId = id;
        mShortDescription = shortDescription;
        mVideoUrl = videoUrl;
        mThumbnailUrl = thumbnailUrl;
    }

    //Getter methods
    public int getId() {
        return mId;
    }

    public String getShortDescription() {
        return mShortDescription;
    }

    public String getVideoUrl() {
        return mVideoUrl;
    }

    public String getThumbnailUrl() {
        return mThumbnailUrl;
    }

    protected Step(Parcel in) {
        mId = in.readInt();
        mShortDescription = in.readString();
        mVideoUrl = in.readString();
        mThumbnailUrl = in.readString();
    }

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mShortDescription);
        dest.writeString(mVideoUrl);
        dest.writeString(mThumbnailUrl);
    }
}
