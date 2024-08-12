package com.example.axf_assets;

public class ListData {
    String name , price , number , trending;
    int banner_image , detail_image;
    int desc;

    public ListData(String name, int desc, String price ,String number, String trending,int banner_image, int detail_image ) {
        this.name = name;
        this.desc = desc;
        this.banner_image = banner_image;
        this.detail_image = detail_image;
        this.price = price;
        this.number = number;
        this.trending = trending;
    }
    public String getName() {
        return name;
    }
    public String getTrending() {
        return trending;
    }

    public int getDesc() {
        return desc;
    }

    public String getPrice() {
        return price;
    }

    public String getNumber() {
        return number;
    }

    public int getImage() {
        return detail_image;
    }

    public int getImageBanner() {
        return banner_image;
    }
}
