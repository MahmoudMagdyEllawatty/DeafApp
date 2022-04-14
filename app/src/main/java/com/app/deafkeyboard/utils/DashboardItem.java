package com.app.deafkeyboard.utils;

import java.util.ArrayList;

public class DashboardItem {
    private String english;
    private String arabic;
    private ArrayList<Integer> englishSigns;
    private ArrayList<Integer> arabicSigns;

    public DashboardItem() {
    }

    public DashboardItem(String english, String arabic, ArrayList<Integer> englishSigns, ArrayList<Integer> arabicSigns) {
        this.english = english;
        this.arabic = arabic;
        this.englishSigns = englishSigns;
        this.arabicSigns = arabicSigns;
    }


    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getArabic() {
        return arabic;
    }

    public void setArabic(String arabic) {
        this.arabic = arabic;
    }

    public ArrayList<Integer> getEnglishSigns() {
        return englishSigns;
    }

    public void setEnglishSigns(ArrayList<Integer> englishSigns) {
        this.englishSigns = englishSigns;
    }

    public ArrayList<Integer> getArabicSigns() {
        return arabicSigns;
    }

    public void setArabicSigns(ArrayList<Integer> arabicSigns) {
        this.arabicSigns = arabicSigns;
    }
}
