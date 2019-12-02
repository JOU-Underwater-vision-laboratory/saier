package com.hhit.anali.pojo;


/**
 * 血氧 SPO2
 */
public class Oxy {

    /**
     *  血氧数据
     */
    private int mSpo2Data;
    /**
     *  血氧波形
     */
    private int mPulseRate;

    public Oxy(int mSpo2Data, int mPulseRate) {
        this.mSpo2Data = mSpo2Data;
        this.mPulseRate = mPulseRate;
    }

    public int getmSpo2Data() {
        return mSpo2Data;
    }

    public void setmSpo2Data(int mSpo2Data) {
        this.mSpo2Data = mSpo2Data;
    }

    public int getmPulseRate() {
        return mPulseRate;
    }

    public void setmPulseRate(int mPulseRate) {
        this.mPulseRate = mPulseRate;
    }

    @Override
    public String toString() {
        return "Oxy{" +
                "mSpo2Data=" + mSpo2Data +
                ", mPulseRate=" + mPulseRate +
                '}';
    }
}
