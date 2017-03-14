package com.example.hp.credainatcon17;

/**
 * Created by poornima 1 on 28-01-2017.
 */
public class Venue {
    private int image_id;
    private String name;

    public Venue(int image_id, String name)
    {
        this.setImage_id(image_id);
        this.setName(name);


    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }
}
