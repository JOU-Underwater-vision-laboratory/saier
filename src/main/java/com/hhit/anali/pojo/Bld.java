package com.hhit.anali.pojo;


/**
 *   NIBP 血压
 */
public class Bld {

    private int mNbpCuff;
    private int mNbpPulse;
    private int mSysPressure;
    private int mDisPressure;
    private int mAvePressure;

    public Bld(int mNbpCuff, int mNbpPulse, int mSysPressure, int mDisPressure, int mAvePressure) {
        this.mNbpCuff = mNbpCuff;
        this.mNbpPulse = mNbpPulse;
        this.mSysPressure = mSysPressure;
        this.mDisPressure = mDisPressure;
        this.mAvePressure = mAvePressure;
    }

    public int getmNbpCuff() {
        return mNbpCuff;
    }

    public void setmNbpCuff(int mNbpCuff) {
        this.mNbpCuff = mNbpCuff;
    }

    public int getmNbpPulse() {
        return mNbpPulse;
    }

    public void setmNbpPulse(int mNbpPulse) {
        this.mNbpPulse = mNbpPulse;
    }

    public int getmSysPressure() {
        return mSysPressure;
    }

    public void setmSysPressure(int mSysPressure) {
        this.mSysPressure = mSysPressure;
    }

    public int getmDisPressure() {
        return mDisPressure;
    }

    public void setmDisPressure(int mDisPressure) {
        this.mDisPressure = mDisPressure;
    }

    public int getmAvePressure() {
        return mAvePressure;
    }

    public void setmAvePressure(int mAvePressure) {
        this.mAvePressure = mAvePressure;
    }

    @Override
    public String toString() {
        return "Bld{" +
                "mNbpCuff=" + mNbpCuff +
                ", mNbpPulse=" + mNbpPulse +
                ", mSysPressure=" + mSysPressure +
                ", mDisPressure=" + mDisPressure +
                ", mAvePressure=" + mAvePressure +
                '}';
    }
}
