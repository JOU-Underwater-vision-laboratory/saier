package com.hhit.saier.domain;

import java.util.ArrayList;

public class GroupItemPlan {

    private String Name;
    private ArrayList<ChildItemPlan> Items;

    public GroupItemPlan(String group) {
        this.Name = group;
    }
    public GroupItemPlan build(ChildItemPlan item) {
        if (this.getItems()==null){
            this.Items = new ArrayList<>();
        }
        if (this.getItems().isEmpty()){
            this.Items.add(item);
            return this;
        }
        if (this.getItems().contains(item)){
            return this;
        }
        if (this.getName().equalsIgnoreCase(item.getGroup())){
            this.Items.add(item);
        }
        return this;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public ArrayList<ChildItemPlan> getItems() {
        return Items;
    }

    public void setItems(ArrayList<ChildItemPlan> Items) {
        this.Items = Items;
    }

}
