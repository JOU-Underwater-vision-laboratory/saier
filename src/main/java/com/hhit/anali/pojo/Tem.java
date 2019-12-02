package com.hhit.anali.pojo;

/**
 *  体温
 */
public class Tem {

    /**
     *  体温数据1
     */
    private float mTemp1Data;
    /**
     *  体温数据1
     */
    private float mTemp2Data;

    public Tem(float mTemp1Data, float mTemp2Data) {
        this.mTemp1Data = mTemp1Data;
        this.mTemp2Data = mTemp2Data;
    }

    public float getmTemp1Data() {
        return mTemp1Data;
    }

    public void setmTemp1Data(float mTemp1Data) {
        this.mTemp1Data = mTemp1Data;
    }

    public float getmTemp2Data() {
        return mTemp2Data;
    }

    public void setmTemp2Data(float mTemp2Data) {
        this.mTemp2Data = mTemp2Data;
    }

    @Override
    public String toString() {
        return "Tem{" +
                "mTemp1Data=" + mTemp1Data +
                ", mTemp2Data=" + mTemp2Data +
                '}';
    }
}
