package com.app.deafkeyboard.utils;

import com.app.deafkeyboard.R;

import java.util.ArrayList;

public class ArabicSignHelper {
    ArrayList<ArabicSign> arabicSigns = new ArrayList<>();
    public ArrayList<Integer> images = new ArrayList<>();
    String englishLetters = "ابتثجحخدذرزسشصضطظعغفقكلمنهويى ءأئؤإآ";

    public ArabicSignHelper(){
    }

    public ArabicSignHelper(String englishWord) {
        initList();
        images = new ArrayList<>();
        for (char character : englishWord.toCharArray()){
            images.add(getImage(String.valueOf(character)));
        }
    }


    public boolean isArabicLetter(String letter){
        return englishLetters.contains(letter.substring(letter.length()-1));
    }


    private int getImage(String letter){
        for (ArabicSign arabicSign : arabicSigns){
            if (arabicSign.getLetter().toLowerCase().equals(letter.toLowerCase())){
                return arabicSign.image;
            }
        }
        return R.mipmap.ic_launcher_round;
    }

    private void initList(){
        arabicSigns = new ArrayList<>();
        arabicSigns.add(new ArabicSign("ا", R.mipmap.a15555));
        arabicSigns.add(new ArabicSign("ب", R.mipmap.b1));
        arabicSigns.add(new ArabicSign("ت", R.mipmap.c1));
        arabicSigns.add(new ArabicSign("ث", R.mipmap.d1));
        arabicSigns.add(new ArabicSign("ج", R.mipmap.e1));
        arabicSigns.add(new ArabicSign("ح", R.mipmap.f1));
        arabicSigns.add(new ArabicSign("خ", R.mipmap.g1));
        arabicSigns.add(new ArabicSign("د", R.mipmap.h1));
        arabicSigns.add(new ArabicSign("ذ", R.mipmap.i1));
        arabicSigns.add(new ArabicSign("ر", R.mipmap.j1));
        arabicSigns.add(new ArabicSign("ز", R.mipmap.k1));
        arabicSigns.add(new ArabicSign("س", R.mipmap.l1));
        arabicSigns.add(new ArabicSign("ش", R.mipmap.m1));
        arabicSigns.add(new ArabicSign("ص", R.mipmap.n1));
        arabicSigns.add(new ArabicSign("ض", R.mipmap.o1));
        arabicSigns.add(new ArabicSign("ط", R.mipmap.q1));
        arabicSigns.add(new ArabicSign("ظ", R.mipmap.r1));
        arabicSigns.add(new ArabicSign("ع", R.mipmap.s1));
        arabicSigns.add(new ArabicSign("غ", R.mipmap.t1));
        arabicSigns.add(new ArabicSign("ف", R.mipmap.u1));
        arabicSigns.add(new ArabicSign("ق", R.mipmap.v1));
        arabicSigns.add(new ArabicSign("ك", R.mipmap.w1));
        arabicSigns.add(new ArabicSign("ل", R.mipmap.x1));
        arabicSigns.add(new ArabicSign("م", R.mipmap.y1));
        arabicSigns.add(new ArabicSign("ن", R.mipmap.z1));
        arabicSigns.add(new ArabicSign("ه", R.mipmap.zz1));
        arabicSigns.add(new ArabicSign("ة", R.mipmap.ccc));
        arabicSigns.add(new ArabicSign("و", R.mipmap.zzz));
        arabicSigns.add(new ArabicSign("ى", R.mipmap.vvv));
        arabicSigns.add(new ArabicSign("ي", R.mipmap.bb));
        arabicSigns.add(new ArabicSign("ؤ", R.mipmap.ggg));
        arabicSigns.add(new ArabicSign("ئ", R.mipmap.qq));
        arabicSigns.add(new ArabicSign("أ", R.mipmap.fff));
        arabicSigns.add(new ArabicSign("ء", R.mipmap.eee));
        arabicSigns.add(new ArabicSign("إ", R.mipmap.ww));
        arabicSigns.add(new ArabicSign("آ", R.mipmap.fffff));

        arabicSigns.add(new ArabicSign(" ", R.mipmap.ic_launcher));
    }

    class ArabicSign {
        private String letter;
        private int image;


        public ArabicSign() {
        }

        public ArabicSign(String letter, int image) {
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
