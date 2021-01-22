package com.example.flashcards;

public class QuestionsItem {
    private int theText;
    private boolean theAnswer;

    /**constorcor that defines what the format of a question
     *
     * @param textId this int shows the value of the question as well as acts as the way to enter what a question will be.
     * @param answer this boolean acts as the way to define what a answer is either being true or false.
     */

    public QuestionsItem(int textId, boolean answer){
        theText = textId;
        theAnswer = answer;
    }


    /**
     * @return int the Text ID
     * A function takes the text that is inside the constructor as passed return it to wherever its been called to.
     */
    public int setText() {
        return theText;
    }

    public boolean checkAnswer() {
        return theAnswer;
    }

}
