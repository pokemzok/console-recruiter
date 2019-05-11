package pokemzok.consolerecruiter.db.changelogs.question.domain;

class ChangelogObject {

    String getFieldName(String fieldName) {
        try {
            return Question.class.getDeclaredField(fieldName).getName();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}
