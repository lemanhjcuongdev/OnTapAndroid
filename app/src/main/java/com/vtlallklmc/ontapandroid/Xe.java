package com.vtlallklmc.ontapandroid;

public class Xe {
    private byte[] pic;
    private String name;

    public Xe(byte[] pic, String name) {
        this.pic = pic;
        this.name = name;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
