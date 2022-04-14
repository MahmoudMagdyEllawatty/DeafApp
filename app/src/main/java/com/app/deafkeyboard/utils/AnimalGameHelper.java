package com.app.deafkeyboard.utils;

import com.app.deafkeyboard.R;

import java.util.ArrayList;
import java.util.Random;

public class AnimalGameHelper {

    private ArrayList<AnimalGame> animalGames;

    public AnimalGameHelper(){
        fillList();
    }


    //0   1    2   3   4   5   6   7   8   9
    int max = 30;

    public Question getQuestion(){
        Random rn = new Random();
        int animalRandomPicker = rn.nextInt(max)+1;
        int firstAnimalChoice = animalRandomPicker > (max-1) ? (max-1) : animalRandomPicker < 2 ? 3  : animalRandomPicker - 2;
        int secondAnimalChoice = animalRandomPicker > (max-1) ? (max-2) : animalRandomPicker < 2 ? 4 :  animalRandomPicker - 1;
        int thirdAnimalChoice = animalRandomPicker > (max-1) ? (max-3) : animalRandomPicker < 2 ? 5 : animalRandomPicker + 1;


        int correctAnswerPosition = rn.nextInt(3)+1;


        AnimalGame selectedAnimal = this.animalGames.get(animalRandomPicker);
        AnimalGame firstAnimal    = this.animalGames.get(firstAnimalChoice);
        AnimalGame secondAnimal   = this.animalGames.get(secondAnimalChoice);
        AnimalGame thirdAnimal    = this.animalGames.get(thirdAnimalChoice);

        int firstAnswer = correctAnswerPosition == 0 ? selectedAnimal.getImage() : firstAnimal.getImage();
        int secondAnswer = correctAnswerPosition == 1 ? selectedAnimal.getImage() : secondAnimal.getImage();
        int thirdAnswer = correctAnswerPosition == 2 ? selectedAnimal.getImage() : thirdAnimal.getImage();
        int forthAnswer = correctAnswerPosition == 3 ? selectedAnimal.getImage() :
                correctAnswerPosition == 0 ? firstAnimal.getImage() :
                correctAnswerPosition == 1 ? secondAnimal.getImage() : thirdAnimal.getImage();

        return new Question(
                "Where Is  "+selectedAnimal.getName(),
                firstAnswer,
                secondAnswer,
                thirdAnswer,
                forthAnswer,
                correctAnswerPosition
        );
    }

    private void fillList(){
        this.animalGames = new ArrayList<>();
        this.animalGames.add(new AnimalGame(R.mipmap.a,"Letter A"));
        this.animalGames.add(new AnimalGame(R.mipmap.b,"Letter B"));
        this.animalGames.add(new AnimalGame(R.mipmap.c,"Letter C"));
        this.animalGames.add(new AnimalGame(R.mipmap.d,"Letter D"));
        this.animalGames.add(new AnimalGame(R.mipmap.e,"Letter E"));
        this.animalGames.add(new AnimalGame(R.mipmap.f,"Letter F"));
        this.animalGames.add(new AnimalGame(R.mipmap.g,"Letter G"));
        this.animalGames.add(new AnimalGame(R.mipmap.h,"Letter H"));
        this.animalGames.add(new AnimalGame(R.mipmap.i,"Letter I"));
        this.animalGames.add(new AnimalGame(R.mipmap.j,"Letter J"));
        this.animalGames.add(new AnimalGame(R.mipmap.k,"Letter K"));
        this.animalGames.add(new AnimalGame(R.mipmap.l,"Letter L"));
        this.animalGames.add(new AnimalGame(R.mipmap.m,"Letter M"));
        this.animalGames.add(new AnimalGame(R.mipmap.n,"Letter N"));
        this.animalGames.add(new AnimalGame(R.mipmap.o,"Letter O"));
        this.animalGames.add(new AnimalGame(R.mipmap.p,"Letter P"));
        this.animalGames.add(new AnimalGame(R.mipmap.q,"Letter Q"));
        this.animalGames.add(new AnimalGame(R.mipmap.r,"Letter R"));
        this.animalGames.add(new AnimalGame(R.mipmap.s,"Letter S"));
        this.animalGames.add(new AnimalGame(R.mipmap.t,"Letter T"));
        this.animalGames.add(new AnimalGame(R.mipmap.u,"Letter U"));
        this.animalGames.add(new AnimalGame(R.mipmap.v,"Letter V"));
        this.animalGames.add(new AnimalGame(R.mipmap.w,"Letter W"));
        this.animalGames.add(new AnimalGame(R.mipmap.x,"Letter X"));
        this.animalGames.add(new AnimalGame(R.mipmap.y,"Letter Y"));
        this.animalGames.add(new AnimalGame(R.mipmap.z,"Letter Z"));

        this.animalGames.add(new AnimalGame(R.mipmap.dog,"Dog"));
        this.animalGames.add(new AnimalGame(R.mipmap.cat,"Cat"));
        this.animalGames.add(new AnimalGame(R.mipmap.monkey,"Monkey"));
        this.animalGames.add(new AnimalGame(R.mipmap.fish,"Fish"));
        this.animalGames.add(new AnimalGame(R.mipmap.cow,"Cow"));


    }

    public ArrayList<AnimalGame> getAnimalGames() {
        return animalGames;
    }

    public void setAnimalGames(ArrayList<AnimalGame> animalGames) {
        this.animalGames = animalGames;
    }


    public static class Question{
        private String question;
        private int answer1;
        private int answer2;
        private int answer3;
        private int answer4;
        private int correctAnswer;

        public Question(String question, int answer1, int answer2, int answer3, int answer4, int correctAnswer) {
            this.question = question;
            this.answer1 = answer1;
            this.answer2 = answer2;
            this.answer3 = answer3;
            this.answer4 = answer4;
            this.correctAnswer = correctAnswer;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public int getAnswer1() {
            return answer1;
        }

        public void setAnswer1(int answer1) {
            this.answer1 = answer1;
        }

        public int getAnswer2() {
            return answer2;
        }

        public void setAnswer2(int answer2) {
            this.answer2 = answer2;
        }

        public int getAnswer3() {
            return answer3;
        }

        public void setAnswer3(int answer3) {
            this.answer3 = answer3;
        }

        public int getAnswer4() {
            return answer4;
        }

        public void setAnswer4(int answer4) {
            this.answer4 = answer4;
        }

        public int getCorrectAnswer() {
            return correctAnswer;
        }

        public void setCorrectAnswer(int correctAnswer) {
            this.correctAnswer = correctAnswer;
        }
    }

    public static class AnimalGame{
        private int image;
        private String name;

        public AnimalGame(int image, String name) {
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
