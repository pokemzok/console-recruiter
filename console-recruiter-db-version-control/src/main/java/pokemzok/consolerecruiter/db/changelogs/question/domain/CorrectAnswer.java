package pokemzok.consolerecruiter.db.changelogs.question.domain;

public class CorrectAnswer extends ChangelogObject{

    private final String content;

    public CorrectAnswer(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getContentFieldName() {
        return getFieldName("content");
    }

}
