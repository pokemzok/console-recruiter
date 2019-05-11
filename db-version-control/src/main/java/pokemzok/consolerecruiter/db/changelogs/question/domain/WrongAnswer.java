package pokemzok.consolerecruiter.db.changelogs.question.domain;

public class WrongAnswer extends ChangelogObject{

    private final String content;

    public WrongAnswer(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getContentFieldName() {
        return getFieldName("content");
    }
}
