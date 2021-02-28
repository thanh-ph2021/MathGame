package study.ltdd.gametinhtoan;

import java.util.ArrayList;
import java.util.Random;

public class Question {
    private int number1;
    private int number2;
    private int number3;
    private int mathOperations;
    private int answer;
    private double answerD;

    private int [] answerArray;
    private double [] answerArrayD;

    private int answerPosition;

    //giới hạn của câu hỏi
    private int limit;

    //vd: "3 + 3 = "
    private String questionPhrase;

    public Question(int limit){
        this.limit = limit;
        //sinh số ngẫu nhiên
        Random numberRan = new Random();

        this.number1 = numberRan.nextInt(limit);
        this.number2 = numberRan.nextInt(limit);
        // + - * / %
        this.mathOperations = numberRan.nextInt(5) + 1;
        switch (this.mathOperations){
            case 1:
                this.answer = this.number1 + this.number2;
                this.questionPhrase = number1 + " + " + number2 + " = ";
                break;
            case 2:
                this.answer = this.number1 - this.number2;
                this.questionPhrase = number1 + " - " + number2 + " = ";
                break;
            case 3:
                this.answer = this.number1 * this.number2;
                this.questionPhrase = number1 + " * " + number2 + " = ";
                break;
            case 4:
                while (this.number2 == 0){
                    this.number2 = numberRan.nextInt(limit);
                }
                this.answerD = this.number1 / (this.number2*1.0);
                this.questionPhrase = number1 + " / " + number2 + " = ";
                break;
            case 5:
                while (this.number2 == 0){
                    this.number2 = numberRan.nextInt(limit);
                }
                this.answer = this.number1 % this.number2;
                this.questionPhrase = number1 + " % " + number2 + " = ";
                break;
        }

        this.answerPosition = numberRan.nextInt(4);
        if(mathOperations == 4){
            this.answerArrayD = new double[] {0, 1, 2, 3};

            this.answerArrayD[0] = this.answer + 1;
            this.answerArrayD[1] = this.answer * 20 - 1;
            this.answerArrayD[2] = this.answer - 5;
            this.answerArrayD[3] = this.answer / 2.0 + 5;
            this.answerArrayD = shuffleArrayD(this.answerArrayD);
            answerArrayD[answerPosition] = answerD;
        } else {
            this.answerArray = new int[] {0, 1, 2, 3};

            this.answerArray[0] = this.answer + 10;
            this.answerArray[1] = this.answer * 10 - 2;
            this.answerArray[2] = this.answer - 5;
            this.answerArray[3] = this.answer % 2 + 3;

            // trộn đáp án
            this.answerArray = shuffleArray(this.answerArray);
            answerArray[answerPosition] = answer;
        }
    }

    /*
        MathOperation : '+', '-', '*', '/', '%'
     */
    public Question(int limit, char MathOperation){
        this.limit = limit;
        //sinh số ngẫu nhiên
        Random numberRan = new Random();

        this.number1 = numberRan.nextInt(limit);
        this.number2 = numberRan.nextInt(limit);
        switch(MathOperation){
            case '+':
                this.answer = this.number1 + this.number2;
                this.questionPhrase = number1 + " + " + number2 + " = ";
                break;
            case '-':
                this.answer = this.number1 - this.number2;
                this.questionPhrase = number1 + " - " + number2 + " = ";
                break;
            case '*':
                this.answer = this.number1 * this.number2;
                this.questionPhrase = number1 + " * " + number2 + " = ";
                break;
            case '/':
                this.mathOperations = 4;
                while (this.number2 == 0){
                    this.number2 = numberRan.nextInt(limit);
                }
                this.answerD = this.number1 / (this.number2*1.0);
                this.questionPhrase = number1 + " / " + number2 + " = ";
                break;
            case '%':
                while (this.number2 == 0){
                    this.number2 = numberRan.nextInt(limit);
                }
                this.answer = this.number1 % this.number2;
                this.questionPhrase = number1 + " % " + number2 + " = ";
                break;
            default:
                break;
        }
        this.answerPosition = numberRan.nextInt(4);
        if(MathOperation == '/'){
            this.answerArrayD = new double[] {0, 1, 2, 3};

            this.answerArrayD[0] = this.answer + 1;
            this.answerArrayD[1] = this.answer * 20 - 1;
            this.answerArrayD[2] = this.answer - 5;
            this.answerArrayD[3] = this.answer / 2.0 + 5;
            this.answerArrayD = shuffleArrayD(this.answerArrayD);
            answerArrayD[answerPosition] = answerD;
        } else {
            this.answerArray = new int[] {0, 1, 2, 3};

            this.answerArray[0] = this.answer + 10;
            this.answerArray[1] = this.answer * 10 - 2;
            this.answerArray[2] = this.answer - 5;
            this.answerArray[3] = this.answer % 2 + 3;

            // trộn đáp án
            this.answerArray = shuffleArray(this.answerArray);
            answerArray[answerPosition] = answer;
        }
    }

    //amongMathOperation >= 1
    public Question(int limit, int amongMathOperation){
        if(amongMathOperation == 1){
            new Question(limit);
        } else if(amongMathOperation == 2) {
            this.limit = limit;
            //sinh số ngẫu nhiên
            Random numberRan = new Random();

            this.number1 = numberRan.nextInt(limit);
            this.number2 = numberRan.nextInt(limit);
            this.number3 = numberRan.nextInt(limit);
            // + - * / %
            this.mathOperations = numberRan.nextInt(23) + 1;
            switch (this.mathOperations){
                case 1:
                    this.answer = this.number1 + this.number2 + this.number3;
                    this.questionPhrase = number1 + " + " + number2 + " + " + number3 + " = ";
                    break;
                case 2:
                    this.answer = this.number1 + this.number2 - this.number3;
                    this.questionPhrase = number1 + " + " + number2 + " - " + number3 + " = ";
                    break;
                case 3:
                    this.answer = this.number1 - this.number2 + this.number3;
                    this.questionPhrase = number1 + " + " + number2 + " + " + number3 + " = ";
                    break;
                case 4:
                    this.answer = this.number1 - this.number2 - this.number3;
                    this.questionPhrase = number1 + " - " + number2 + " - " + number3 + " = ";
                    break;
                case 5:
                    this.answer = this.number1 * this.number2 * this.number3;
                    this.questionPhrase = number1 + " * " + number2 + " * " + number3 + " = ";
                    break;
                case 6:
                    this.answer = this.number1 * this.number2 - this.number3;
                    this.questionPhrase = number1 + " * " + number2 + " - " + number3 + " = ";
                    break;
                case 7:
                    this.answer = this.number1 - this.number2 * this.number3;
                    this.questionPhrase = number1 + " - " + number2 + " * " + number3 + " = ";
                    break;
                case 8:
                    this.answer = this.number1 * this.number2 + this.number3;
                    this.questionPhrase = number1 + " * " + number2 + " + " + number3 + " = ";
                    break;
                case 9:
                    this.answer = this.number1 + this.number2 * this.number3;
                    this.questionPhrase = number1 + " + " + number2 + " * " + number3 + " = ";
                    break;
                case 10:
                    while (this.number2 == 0){
                        this.number2 = numberRan.nextInt(limit);
                    }
                    this.answerD = this.number1 / (this.number2*1.0) * this.number3;
                    this.questionPhrase = number1 + " / " + number2 + " * " + number3 + " = ";
                    break;
                case 11:
                    while (this.number2 == 0){
                        this.number2 = numberRan.nextInt(limit);
                    }
                    this.answerD = this.number1 / (this.number2*1.0) + this.number3;
                    this.questionPhrase = number1 + " / " + number2 + " + " + number3 + " = ";
                    break;
                case 12:
                    while (this.number2 == 0){
                        this.number2 = numberRan.nextInt(limit);
                    }
                    this.answerD = this.number1 / (this.number2*1.0) - this.number3;
                    this.questionPhrase = number1 + " / " + number2 + " - " + number3 + " = ";
                    break;
                case 13:
                    while (this.number3 == 0){
                        this.number3 = numberRan.nextInt(limit);
                    }
                    this.answerD = this.number1 * this.number2 / (this.number3*1.0);
                    this.questionPhrase = number1 + " * " + number2 + " / " + number3 + " = ";
                    break;
                case 14:
                    while (this.number3 == 0){
                        this.number3 = numberRan.nextInt(limit);
                    }
                    this.answerD = this.number1 + this.number2 / (this.number3*1.0);
                    this.questionPhrase = number1 + " + " + number2 + " / " + number3 + " = ";
                    break;
                case 15:
                    while (this.number3 == 0){
                        this.number3 = numberRan.nextInt(limit);
                    }
                    this.answerD = this.number1 - this.number2 / (this.number3*1.0);
                    this.questionPhrase = number1 + " - " + number2 + " / " + number3 + " = ";
                    break;
                case 16:
                    while (this.number2 == 0){
                        this.number2 = numberRan.nextInt(limit);
                    }
                    while (this.number3 == 0){
                        this.number3 = numberRan.nextInt(limit);
                    }
                    this.answerD = this.number1 / (this.number2*1.0) / (this.number3*1.0);
                    this.questionPhrase = number1 + " / " + number2 + " / " + number3 + " = ";
                    break;
                case 17:
                    while (this.number2 == 0){
                        this.number2 = numberRan.nextInt(limit);
                    }
                    this.answer = this.number1 % this.number2 + this.number3;
                    this.questionPhrase = number1 + " % " + number2 + " + " + number3 + " = ";
                    break;
                case 18:
                    while (this.number2 == 0){
                        this.number2 = numberRan.nextInt(limit);
                    }
                    this.answer = this.number1 % this.number2 - this.number3;
                    this.questionPhrase = number1 + " % " + number2 + " - " + number3 + " = ";
                    break;
                case 19:
                    while (this.number2 == 0){
                        this.number2 = numberRan.nextInt(limit);
                    }
                    this.answer = this.number1 % this.number2 * this.number3;
                    this.questionPhrase = number1 + " % " + number2 + " * " + number3 + " = ";
                    break;
                case 20:
                    while (this.number2 == 0){
                        this.number2 = numberRan.nextInt(limit);
                    }
                    while (this.number3 == 0){
                        this.number3 = numberRan.nextInt(limit);
                    }
                    this.answer = this.number1 % this.number2 % this.number3;
                    this.questionPhrase = number1 + " % " + number2 + " % " + number3 + " = ";
                    break;
                case 21:
                    while (this.number3 == 0){
                        this.number3 = numberRan.nextInt(limit);
                    }
                    this.answer = this.number1 + this.number2 % this.number3;
                    this.questionPhrase = number1 + " + " + number2 + " % " + number3 + " = ";
                    break;
                case 22:
                    while (this.number3 == 0){
                        this.number3 = numberRan.nextInt(limit);
                    }
                    this.answer = this.number1 - this.number2 % this.number3;
                    this.questionPhrase = number1 + " - " + number2 + " % " + number3 + " = ";
                    break;
                case 23:
                    while (this.number3 == 0){
                        this.number3 = numberRan.nextInt(limit);
                    }
                    this.answer = this.number1 * this.number2 % this.number3;
                    this.questionPhrase = number1 + " * " + number2 + " % " + number3 + " = ";
                    break;
            }

            this.answerPosition = numberRan.nextInt(4);
            if(mathOperations == 4 || (mathOperations >= 10 && mathOperations <= 16)){
                this.answerArrayD = new double[] {0, 1, 2, 3};

                this.answerArrayD[0] = this.answer + 1;
                this.answerArrayD[1] = this.answer * 2 + 3;
                this.answerArrayD[2] = this.answer - 5;
                this.answerArrayD[3] = this.answer / 2.0 - 5;
                this.answerArrayD = shuffleArrayD(this.answerArrayD);
                answerArrayD[answerPosition] = answerD;
            } else {
                this.answerArray = new int[] {0, 1, 2, 3};

                this.answerArray[0] = this.answer + 1;
                this.answerArray[1] = this.answer * 10 - 10;
                this.answerArray[2] = this.answer - 5;
                this.answerArray[3] = this.answer * 2 + 2;

                // trộn đáp án
                this.answerArray = shuffleArray(this.answerArray);
                answerArray[answerPosition] = this.answer;
            }
        }

    }

    /*
        mathOperationTable: '*', '/'
        number: 2, 3, 4, 5, 6, 7, 8, 9
    */
    public Question(char mathOperationTable, int number ){
        Random numberRan = new Random();
        this.number1 = number;
        switch(mathOperationTable){
            case '*':
                this.number2 = numberRan.nextInt(10) + 1;
                this.answer = this.number1 * this.number2;
                this.questionPhrase = this.number1 + " * " + this.number2 + " = ";
                break;
            case '/':
                do{
                    this.number2 = numberRan.nextInt(number * 10) + number;
                } while (this.number2 % number != 0);
                this.answer = this.number2 / this.number1;
                this.questionPhrase = this.number2 + " / " + this.number1 + " = ";
                break;
            default:
                break;
        }
        this.answerPosition = numberRan.nextInt(4);
        this.answerArray = new int[] {0, 1, 2, 3};

        this.answerArray[0] = this.answer + 10;
        this.answerArray[1] = this.answer * 10 - 2;
        this.answerArray[2] = this.answer - 5;
        this.answerArray[3] = this.answer + 3;

        // trộn đáp án
        this.answerArray = shuffleArray(this.answerArray);
        answerArray[answerPosition] = answer;
    }

    private double[] shuffleArrayD(double [] array){
        int pos;
        double temp;
        Random numberRan = new Random();

        for (int i = array.length-1; i > 0; i--) {
            pos = numberRan.nextInt(i+1);
            temp = array[pos];
            array[pos] = array[i];
            array[i] = temp;
        }
        return array;
    }

    private int [] shuffleArray(int [] array){
        int pos, temp;
        Random numberRan = new Random();

        for (int i = array.length-1; i > 0; i--) {
            pos = numberRan.nextInt(i+1);
            temp = array[pos];
            array[pos] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getNumber2() {
        return number2;
    }

    public double getAnswer() {
        return answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public int getLimit() {
        return limit;
    }

    public double getAnswerD() {
        return answerD;
    }

    public void setAnswerD(double answerD) {
        this.answerD = answerD;
    }

    public double[] getAnswerArrayD() {
        return answerArrayD;
    }

    public void setAnswerArrayD(double[] answerArrayD) {
        this.answerArrayD = answerArrayD;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }
    public int getMathOperations() {
        return mathOperations;
    }

    public void setMathOperations(int mathOperations) {
        this.mathOperations = mathOperations;
    }
}
