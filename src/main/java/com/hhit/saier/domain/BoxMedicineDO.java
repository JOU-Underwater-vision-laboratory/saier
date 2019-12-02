package com.hhit.saier.domain;

import javax.swing.*;

public class BoxMedicineDO {
   private BoxDO box;
   private MedicineDO medicineDO;


    public BoxMedicineDO() {
    }

    public BoxMedicineDO(BoxDO box, MedicineDO medicineDO) {
        this.box = box;
        this.medicineDO = medicineDO;
    }

    public BoxDO getBox() {
        return box;
    }

    public void setBox(BoxDO box) {
        this.box = box;
    }

    public MedicineDO getMedicineDO() {
        return medicineDO;
    }

    public void setMedicineDO(MedicineDO medicineDO) {
        this.medicineDO = medicineDO;
    }

}
