package com.app.deafkeyboard.utils;

import com.app.deafkeyboard.R;

import java.util.ArrayList;

public class AnimalHelper {
    private ArrayList<Letters> letters;

    public AnimalHelper() {
        fillList();
    }

    public ArrayList<Letters> getLetters() {
        return letters;
    }

    private void fillList(){
        this.letters = new ArrayList<>();
        this.letters.add(new Letters(R.mipmap.dog,"Dog"));
        this.letters.add(new Letters(R.mipmap.cat,"Cat"));
        this.letters.add(new Letters(R.mipmap.monkey,"Monkey"));
        this.letters.add(new Letters(R.mipmap.fish,"Fish"));
        this.letters.add(new Letters(R.mipmap.cow,"Cow"));
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
