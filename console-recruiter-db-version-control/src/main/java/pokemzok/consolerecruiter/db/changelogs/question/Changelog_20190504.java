package pokemzok.consolerecruiter.db.changelogs.question;

import com.github.mongobeej.changeset.ChangeLog;
import com.github.mongobeej.changeset.ChangeSet;
import com.google.common.collect.Lists;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import pokemzok.consolerecruiter.db.changelogs.question.domain.CorrectAnswer;
import pokemzok.consolerecruiter.db.changelogs.question.domain.Question;
import pokemzok.consolerecruiter.db.changelogs.question.domain.WrongAnswer;

import java.util.List;

@ChangeLog
public class Changelog_20190504 {

    @ChangeSet(order = "002", id = "20190504153307", author = "pokemzok")
    public void importantWorkToDo(MongoDatabase db) {
        MongoCollection<Document> mycollection = db.getCollection(Question.COLLECTION_NAME);
        List<Document> questions = Lists.newArrayList(
                firstQuestion(),
                secondQuestion(),
                thirdQuestion(),
                fourthQuestion(),
                fifthQuestion(),
                sixthQuestion(),
                seventhQuestion()
        );
        mycollection.insertMany(questions);
    }

    private Document firstQuestion() {
        var question = new Question(
                "Which GC implementation are supported in latest JVM?",
                List.of(new CorrectAnswer("Serial"), new CorrectAnswer("G1"), new CorrectAnswer("CMS")),
                List.of(new WrongAnswer("Parabolic"), new WrongAnswer("G2"), new WrongAnswer("UPC"))
        );
        return DocumentFromQuestionFactory.create(question);
    }

    private Document secondQuestion() {
        var question = new Question(
                "What new features introduced Java 8?",
                List.of(new CorrectAnswer("Stream API"), new CorrectAnswer("Functional Interfaces")),
                List.of(new WrongAnswer("Generics"), new WrongAnswer("Multiple Exception Handling"))
        );
        return DocumentFromQuestionFactory.create(question);
    }

    private Document thirdQuestion() {
        var question = new Question(
                "Which classes implements java.util.Collection interface?",
                List.of(new CorrectAnswer("Set"), new CorrectAnswer("Queue")),
                List.of(new WrongAnswer("Map"), new WrongAnswer("Array"))
        );
        return DocumentFromQuestionFactory.create(question);
    }

    private Document fourthQuestion() {
        var question = new Question(
                "How 'final' keyword can be used?",
                List.of(new CorrectAnswer("To create immutable variables"), new CorrectAnswer("To prevent inheritance"), new CorrectAnswer("To prevent method overriding")),
                List.of(new WrongAnswer("To create methods which would always execute last"), new WrongAnswer("To always execute code after try/catch block"))
        );
        return DocumentFromQuestionFactory.create(question);
    }

    private Document fifthQuestion() {
        var question = new Question(
                "Which sentences are correct?",
                List.of(new CorrectAnswer("Checked exception is a type of exception which must be either caught or declared in the method in which it is thrown")),
                List.of(new WrongAnswer("Checked exception extends RuntimeException class"), new WrongAnswer("Catching every Throwable is safe."))
        );
        return DocumentFromQuestionFactory.create(question);
    }

    private Document sixthQuestion() {
        var question = new Question(
                "Which sentences are correct?",
                List.of(new CorrectAnswer("Java volatile keyword guarantees visibility of changes to variables across threads."), new CorrectAnswer("Thread.sleep() does not release lock")),
                List.of(new WrongAnswer("Dead thread can be started again"), new WrongAnswer("There is no way to access private field outside of a class"))
        );
        return DocumentFromQuestionFactory.create(question);
    }

    private Document seventhQuestion() {
        var question = new Question(
                "How to create and start a thread?",
                List.of(new CorrectAnswer("new Thread(()-> System.out.println(\"running\")).start();")),
                List.of(new WrongAnswer("new Runnable().run();"), new WrongAnswer("new Thread(()-> System.out.println(\"running\")).run();"))
        );
        return DocumentFromQuestionFactory.create(question);
    }
}
