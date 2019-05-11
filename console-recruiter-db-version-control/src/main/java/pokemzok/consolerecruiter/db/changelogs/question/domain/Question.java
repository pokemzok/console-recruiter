package pokemzok.consolerecruiter.db.changelogs.question.domain;

import java.util.List;


public class Question extends ChangelogObject {
    public final static String COLLECTION_NAME = Question.class.getSimpleName().toLowerCase();

    private String content;
    private List<CorrectAnswer> correctAnswers;
    private List<WrongAnswer> wrongAnswers;

    public Question(String content, List<CorrectAnswer> correctAnswers, List<WrongAnswer> wrongAnswers) {
        this.content = content;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
    }

    public String getContent() {
        return content;
    }

    public List<CorrectAnswer> getCorrectAnswers() {
        return correctAnswers;
    }

    public List<WrongAnswer> getWrongAnswers() {
        return wrongAnswers;
    }

    public String getContentFieldName() {
        return getFieldName("content");
    }

    public String getCorrectAnswersFieldName() {
        return getFieldName("correctAnswers");
    }

    public String getWrongAnswersFieldName() {
        return getFieldName("wrongAnswers");
    }


}
