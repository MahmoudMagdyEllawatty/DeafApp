package com.app.deafkeyboard.utils;

import com.app.deafkeyboard.R;

import java.util.ArrayList;

public class NumbersHelper {
    private ArrayList<Letters> letters;

    public NumbersHelper() {
        fillList();
    }

    public ArrayList<Letters> getLetters() {
        return letters;
    }

    private void fillList(){
        this.letters = new ArrayList<>();
        this.letters.add(new Letters(R.mipmap.a1,"1"));
        this.letters.add(new Letters(R.mipmap.a2,"2"));
        this.letters.add(new Letters(R.mipmap.a3,"3"));
        this.letters.add(new Letters(R.mipmap.a4,"4"));
        this.letters.add(new Letters(R.mipmap.a5,"5"));
        this.letters.add(new Letters(R.mipmap.a6,"6"));
        this.letters.add(new Letters(R.mipmap.a7,"7"));
        this.letters.add(new Letters(R.mipmap.a8,"8"));
        this.letters.add(new Letters(R.mipmap.a9,"9"));
        this.letters.add(new Letters(R.mipmap.a10,"10"));
    }



    public static class Letters {
        private int image;
        private String name;

        public Letters(int image, String name) {
            this.image = image;
            this.name = name;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
