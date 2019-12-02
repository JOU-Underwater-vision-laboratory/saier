package com.hhit.anali.pojo;


/**
 * 呼吸 RESP
 */
public class Breath {

    /**
     *   呼吸数据
     */
    private int mRespRate;

    /**
     *  呼吸波形数据
     */
    private int mRespWaveData1;

    public Breath(int mRespRate, int mRespWaveData1) {
        this.mRespRate = mRespRate;
        this.mRespWaveData1 = mRespWaveData1;
    }

    public int getmRespRate() {
        return mRespRate;
    }

    public void setmRespRate(int mRespRate) {
        this.mRespRate = mRespRate;
    }

    public int getmRespWaveData1() {
        return mRespWaveData1;
    }

    public void setmRespWaveData1(int mRespWaveData1) {
        this.mRespWaveData1 = mRespWaveData1;
    }

    @Override
    public String toString() {
        return "Breath{" +
                "mRespRate=" + mRespRate +
                ", mRespWaveData1=" + mRespWaveData1 +
                '}';
    }
}
