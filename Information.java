package com.example.fourthtry;

public class Information {

//    private String itemId;
    private String itemName;
    private String itemDescription;

    public Information() {

    }

    public Information(String itemName, String itemDescription) {
//        this.itemId = itemId;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

//    public String getItemId() {
//        return itemId;
//    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}
