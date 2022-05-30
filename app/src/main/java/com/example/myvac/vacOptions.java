package com.example.myvac;

public class vacOptions {
    private String name;
    private int image;

    public vacOptions(String name, int image)
    {
        this.image = image;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    @Override
    public String toString() {
        return
                "name=" + name +
                "image=" + image;
    }
}
