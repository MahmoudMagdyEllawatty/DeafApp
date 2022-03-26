package com.app.deafkeyboard.utils;

import com.app.deafkeyboard.R;

import java.util.ArrayList;

public class EnglishSignHelper {
    ArrayList<EnglishSign> englishSigns = new ArrayList<>();
    public ArrayList<Integer> images = new ArrayList<>();
    String englishLetters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";

    public EnglishSignHelper(){
    }

    public EnglishSignHelper(String englishWord) {
        initList();
        images = new ArrayList<>();
        for (char character : englishWord.toCharArray()){
            images.add(getImage(String.valueOf(character)));
        }
    }


    public boolean isEnglishLetter(String letter){
        return englishLetters.contains(letter.substring(letter.length()-1));
    }


    private int getImage(String letter){
        for (EnglishSign englishSign : englishSigns){
            if (englishSign.getLetter().toLowerCase().equals(letter.toLowerCase())){
                return englishSign.image;
            }
        }
        return R.mipmap.ic_launcher_round;
    }

    private void initList(){
        englishSigns = new ArrayList<>();
        englishSigns.add(new EnglishSign("a", R.mipmap.a));
        englishSigns.add(new EnglishSign("b", R.mipmap.b));
        englishSigns.add(new EnglishSign("c", R.mipmap.c));
        englishSigns.add(new EnglishSign("d", R.mipmap.d));
        englishSigns.add(new EnglishSign("e", R.mipmap.e));
        englishSigns.add(new EnglishSign("f", R.mipmap.f));
        englishSigns.add(new EnglishSign("g", R.mipmap.g));
        englishSigns.add(new EnglishSign("h", R.mipmap.h));
        englishSigns.add(new EnglishSign("i", R.mipmap.i));
        englishSigns.add(new EnglishSign("j", R.mipmap.j));
        englishSigns.add(new EnglishSign("k", R.mipmap.k));
        englishSigns.add(new EnglishSign("l", R.mipmap.l));
        englishSigns.add(new EnglishSign("m", R.mipmap.m));
        englishSigns.add(new EnglishSign("n", R.mipmap.n));
        englishSigns.add(new EnglishSign("o", R.mipmap.o));
        englishSigns.add(new EnglishSign("p", R.mipmap.p));
        englishSigns.add(new EnglishSign("q", R.mipmap.q));
        englishSigns.add(new EnglishSign("r", R.mipmap.r));
        englishSigns.add(new EnglishSign("s", R.mipmap.s));
        englishSigns.add(new EnglishSign("t", R.mipmap.t));
        englishSigns.add(new EnglishSign("u", R.mipmap.u));
        englishSigns.add(new EnglishSign("v", R.mipmap.v));
        englishSigns.add(new EnglishSign("w", R.mipmap.w));
        englishSigns.add(new EnglishSign("x", R.mipmap.x));
        englishSigns.add(new EnglishSign("y", R.mipmap.y));
        englishSigns.add(new EnglishSign("z", R.mipmap.z));
        englishSigns.add(new EnglishSign(" ", R.mipmap.ic_launcher));
    }

    class EnglishSign{
        private String letter;
        private int image;


        public EnglishSign() {
        }

        public EnglishSign(String letter, int image) {
            this.letter = letter;
            this.image = image;
        }

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }
    }
}
