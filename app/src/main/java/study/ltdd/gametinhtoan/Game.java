package study.ltdd.gametinhtoan;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private List<Question> questionList;
    private int numberCorrect;
    private int totalQuestions;
    private int score;

    private Question currentQuestions;

    public Game(){
        numberCorrect = 0;
        totalQuestions = 0;
        score = 0;
        currentQuestions = new Question(10);
        questionList = new ArrayList<Question>();
    }

    public void makeNewQuestion(){
        if (totalQuestions <= 10) {
            currentQuestions = new Question(totalQuestions * 2 + 5);
        } else if(totalQuestions <= 60){
            currentQuestions = new Question(totalQuestions * 2 + 5, 2);
        } else {
            Random numberRan = new Random();
            int amongMathOperation = numberRan.nextInt(2) + 1;
            currentQuestions = new Question(totalQuestions * 2 + 5, amongMathOperation);
        }
        currentQuestions = new Question(totalQuestions * 2 + 5);
        totalQuestions++;
        questionList.add(currentQuestions);
    }

    public void makeNewQuestion(char mathOperation){
        currentQuestions = new Question(totalQuestions * 2 + 5, mathOperation);
        totalQuestions++;
        questionList.add(currentQuestions);
    }

    public void makeNewQuestion(char mathOperation, int number){
        currentQuestions = new Question(mathOperation, number);
        totalQuestions++;
        questionList.add(currentQuestions);
    }

    public boolean checkAnswer(double submittedAnswer, int remainingTime){
        boolean isCorrect;

        if(currentQuestions.getMathOperations() == 4 || (currentQuestions.getMathOperations() >= 10 && currentQuestions.getMathOperations() <= 16)){
            // convert answer -> 0.0000
            if(Double.parseDouble(String.format("%.4f", currentQuestions.getAnswerD())) == submittedAnswer){
                isCorrect = true;
            } else {
                isCorrect = false;
            }
        } else {
            if(currentQuestions.getAnswer() == submittedAnswer){
                isCorrect = true;
            } else {
                isCorrect = false;
            }
        }
        score += 10 + remainingTime * 2;
        return isCorrect;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Question getCurrentQuestions() {
        return currentQuestions;
    }

    public void setCurrentQuestions(Question currentQuestions) {
        this.currentQuestions = currentQuestions;
    }
}
