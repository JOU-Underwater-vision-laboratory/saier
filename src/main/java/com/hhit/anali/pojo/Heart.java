package com.hhit.anali.pojo;

/**
 *   心电 ECG
 */
public class Heart {

    /**
     *  心率
     */
    private int mHrData;

    public Heart(int mHrData) {
        this.mHrData = mHrData;
    }

    public int getmHrData() {
        return mHrData;
    }

    public void setmHrData(int mHrData) {
        this.mHrData = mHrData;
    }

    @Override
    public String toString() {
        return "Heart{" +
                "mHrData=" + mHrData +
                '}';
    }
}
