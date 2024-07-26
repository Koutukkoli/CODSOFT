import java.util.List;

public class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctAnswerIndex;

    public QuizQuestion(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public boolean isCorrectAnswer(int answerIndex) {
        return answerIndex == correctAnswerIndex;
    }
}