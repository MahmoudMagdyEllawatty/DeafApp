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
            this.letters.add(new Letters(R.mipmap.a15555,"??"));
            this.letters.add(new Letters(R.mipmap.b1,"??"));
            this.letters.add(new Letters(R.mipmap.c1,"??"));
            this.letters.add(new Letters(R.mipmap.d1,"??"));
            this.letters.add(new Letters(R.mipmap.e1,"??"));
            this.letters.add(new Letters(R.mipmap.f1,"??"));
            this.letters.add(new Letters(R.mipmap.g1,"??"));
            this.letters.add(new Letters(R.mipmap.h1,"??"));
            this.letters.add(new Letters(R.mipmap.i1,"??"));
            this.letters.add(new Letters(R.mipmap.j1,"??"));

            this.letters.add(new Letters(R.mipmap.k1,"??"));
            this.letters.add(new Letters(R.mipmap.l1,"??"));
            this.letters.add(new Letters(R.mipmap.m1,"??"));
            this.letters.add(new Letters(R.mipmap.n1,"??"));
            this.letters.add(new Letters(R.mipmap.o1,"??"));
            this.letters.add(new Letters(R.mipmap.q1,"??"));
            this.letters.add(new Letters(R.mipmap.r1,"??"));
            this.letters.add(new Letters(R.mipmap.s1,"??"));
            this.letters.add(new Letters(R.mipmap.t1,"??"));
            this.letters.add(new Letters(R.mipmap.u1,"??"));

            this.letters.add(new Letters(R.mipmap.v1,"??"));
            this.letters.add(new Letters(R.mipmap.w1,"??"));
            this.letters.add(new Letters(R.mipmap.x1,"??"));
            this.letters.add(new Letters(R.mipmap.y1,"??"));
            this.letters.add(new Letters(R.mipmap.z1,"??"));
            this.letters.add(new Letters(R.mipmap.zz1,"??"));


            this.letters.add(new Letters(R.mipmap.ccc,"??"));
            this.letters.add(new Letters(R.mipmap.zzz,"??"));
            this.letters.add(new Letters(R.mipmap.vvv,"??"));
            this.letters.add(new Letters(R.mipmap.bb,"??"));
            this.letters.add(new Letters(R.mipmap.ggg,"??"));
            this.letters.add(new Letters(R.mipmap.qq,"??"));

            this.letters.add(new Letters(R.mipmap.fff,"??"));
            this.letters.add(new Letters(R.mipmap.eee,"??"));
            this.letters.add(new Letters(R.mipmap.ww,"??"));
            this.letters.add(new Letters(R.mipmap.fffff,"??"));
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
