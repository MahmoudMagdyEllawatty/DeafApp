package com.app.deafkeyboard.utils;

import com.app.deafkeyboard.R;

import java.util.ArrayList;

public class LettersHelper {
    private ArrayList<Letters> letters;
    private boolean isEnglish;
    public LettersHelper(boolean isEnglish) {
        this.isEnglish = isEnglish;
        fillList();
    }

    public ArrayList<Letters> getLetters() {
        return letters;
    }

    private void fillList(){
        this.letters = new ArrayList<>();
        if(this.isEnglish){
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
        }else{
            this.letters.add(new Letters(R.mipmap.a15555,"ا"));
            this.letters.add(new Letters(R.mipmap.b1,"ب"));
            this.letters.add(new Letters(R.mipmap.c1,"ت"));
            this.letters.add(new Letters(R.mipmap.d1,"ث"));
            this.letters.add(new Letters(R.mipmap.e1,"ج"));
            this.letters.add(new Letters(R.mipmap.f1,"ح"));
            this.letters.add(new Letters(R.mipmap.g1,"خ"));
            this.letters.add(new Letters(R.mipmap.h1,"د"));
            this.letters.add(new Letters(R.mipmap.i1,"ذ"));
            this.letters.add(new Letters(R.mipmap.j1,"ر"));

            this.letters.add(new Letters(R.mipmap.k1,"ز"));
            this.letters.add(new Letters(R.mipmap.l1,"س"));
            this.letters.add(new Letters(R.mipmap.m1,"ش"));
            this.letters.add(new Letters(R.mipmap.n1,"ص"));
            this.letters.add(new Letters(R.mipmap.o1,"ض"));
            this.letters.add(new Letters(R.mipmap.q1,"ط"));
            this.letters.add(new Letters(R.mipmap.r1,"ظ"));
            this.letters.add(new Letters(R.mipmap.s1,"ع"));
            this.letters.add(new Letters(R.mipmap.t1,"غ"));
            this.letters.add(new Letters(R.mipmap.u1,"ف"));

            this.letters.add(new Letters(R.mipmap.v1,"ق"));
            this.letters.add(new Letters(R.mipmap.w1,"ك"));
            this.letters.add(new Letters(R.mipmap.x1,"ل"));
            this.letters.add(new Letters(R.mipmap.y1,"م"));
            this.letters.add(new Letters(R.mipmap.z1,"ن"));
            this.letters.add(new Letters(R.mipmap.zz1,"ه"));


            this.letters.add(new Letters(R.mipmap.ccc,"ة"));
            this.letters.add(new Letters(R.mipmap.zzz,"و"));
            this.letters.add(new Letters(R.mipmap.vvv,"ى"));
            this.letters.add(new Letters(R.mipmap.bb,"ي"));
            this.letters.add(new Letters(R.mipmap.ggg,"ؤ"));
            this.letters.add(new Letters(R.mipmap.qq,"ئ"));

            this.letters.add(new Letters(R.mipmap.fff,"أ"));
            this.letters.add(new Letters(R.mipmap.eee,"ء"));
            this.letters.add(new Letters(R.mipmap.ww,"إ"));
            this.letters.add(new Letters(R.mipmap.fffff,"آ"));
        }

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
