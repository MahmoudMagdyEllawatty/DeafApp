package com.app.deafkeyboard.utils;

import com.app.deafkeyboard.R;

import java.util.ArrayList;

public class LettersHelper {
    private ArrayList<Letters> letters;

    public LettersHelper() {
        fillList();
    }

    public ArrayList<Letters> getLetters() {
        return letters;
    }

    private void fillList(){
        this.letters = new ArrayList<>();
        this.letters.add(new Letters(R.mipmap.a,"A"));
        this.letters.add(new Letters(R.mipmap.b,"B"));
        this.letters.add(new Letters(R.mipmap.c,"C"));
        this.letters.add(new Letters(R.mipmap.d,"D"));
        this.letters.add(new Letters(R.mipmap.e,"E"));
        this.letters.add(new Letters(R.mipmap.f,"F"));
        this.letters.add(new Letters(R.mipmap.g,"G"));
        this.letters.add(new Letters(R.mipmap.h,"H"));
        this.letters.add(new Letters(R.mipmap.i,"I"));
        this.letters.add(new Letters(R.mipmap.j,"J"));

        this.letters.add(new Letters(R.mipmap.k,"K"));
        this.letters.add(new Letters(R.mipmap.l,"L"));
        this.letters.add(new Letters(R.mipmap.m,"M"));
        this.letters.add(new Letters(R.mipmap.n,"N"));
        this.letters.add(new Letters(R.mipmap.o,"O"));
        this.letters.add(new Letters(R.mipmap.p,"P"));
        this.letters.add(new Letters(R.mipmap.q,"Q"));
        this.letters.add(new Letters(R.mipmap.r,"R"));
        this.letters.add(new Letters(R.mipmap.s,"S"));
        this.letters.add(new Letters(R.mipmap.t,"T"));

        this.letters.add(new Letters(R.mipmap.u,"U"));
        this.letters.add(new Letters(R.mipmap.v,"V"));
        this.letters.add(new Letters(R.mipmap.w,"W"));
        this.letters.add(new Letters(R.mipmap.x,"X"));
        this.letters.add(new Letters(R.mipmap.y,"Y"));
        this.letters.add(new Letters(R.mipmap.z,"Z"));
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
