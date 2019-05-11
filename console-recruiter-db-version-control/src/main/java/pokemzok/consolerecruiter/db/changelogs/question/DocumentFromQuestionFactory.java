package pokemzok.consolerecruiter.db.changelogs.question;

import org.bson.Document;
import pokemzok.consolerecruiter.db.changelogs.question.domain.CorrectAnswer;
import pokemzok.consolerecruiter.db.changelogs.question.domain.Question;
import pokemzok.consolerecruiter.db.changelogs.question.domain.WrongAnswer;

import java.util.List;
import java.util.stream.Collectors;

class DocumentFromQuestionFactory {

    static Document create(Question question) {
        return new Document(question.getContentFieldName(), question.getContent())
                .append(question.getCorrectAnswersFieldName(), correctAnswersToDocuments(question.getCorrectAnswers()))
                .append(question.getWrongAnswersFieldName(), wrongAnswersToDocuments(question.getWrongAnswers()));
    }

    private static List<Document> correctAnswersToDocuments(List<CorrectAnswer> correctAnswers) {
        return correctAnswers.stream()
                .map(answer -> new Document(answer.getContentFieldName(), answer.getContent()))
                .collect(Collectors.toList());
    }

    private static List<Document> wrongAnswersToDocuments(List<WrongAnswer> correctAnswers) {
        return correctAnswers.stream()
                .map(answer -> new Document(answer.getContentFieldName(), answer.getContent()))
                .collect(Collectors.toList());
    }
}
