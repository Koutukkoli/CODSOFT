import java.util.List;

public class Quiz {
    private List<QuizQuestion> questions;
    private int currentQuestionIndex;
    private int score;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public QuizQuestion getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public int getScore() {
        return score;
    }

    public void nextQuestion() {
        currentQuestionIndex++;
    }

    public boolean hasMoreQuestions() {
        return currentQuestionIndex < questions.size();
    }

    public void answerCurrentQuestion(int answerIndex) {
        QuizQuestion question = getCurrentQuestion();
        if (question.isCorrectAnswer(answerIndex)) {
            score++;
        }
        nextQuestion();
    }
}