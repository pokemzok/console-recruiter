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
public class Changelog_20190420 {

    @ChangeSet(order = "001", id = "20190420140707", author = "pokemzok")
    public void importantWorkToDo(MongoDatabase db) {
        MongoCollection<Document> mycollection = db.getCollection(Question.COLLECTION_NAME);
        List<Document> questions = Lists.newArrayList(
                firstQuestion(),
                secondQuestion(),
                thirdQuestion()
        );
        mycollection.insertMany(questions);
    }

    private Document firstQuestion() {
        var question = new Question(
                "What person is considered to be Java father?",
                List.of(new CorrectAnswer("James Gosling")),
                List.of(new WrongAnswer("Ryan Gosling"), new WrongAnswer("Matt Damon"))
        );
        return DocumentFromQuestionFactory.create(question);
    }

    private Document secondQuestion() {
        var question = new Question(
                "What do you know about Garbage Collector?",
                List.of(new CorrectAnswer("It is a form of automatic memory management"), new CorrectAnswer("Most of recruiters can not answer how the knowledge about GC will be useful in context of the project")),
                List.of(new WrongAnswer("Its those dudes from Mass Effect 2!"), new WrongAnswer("Thanks to garbage collection cities are clean, which prevents global warming"))
        );
        return DocumentFromQuestionFactory.create(question);
    }

    private Document thirdQuestion() {
        var question = new Question(
                "What is a callback hell?",
                List.of(new CorrectAnswer("It is an anti-pattern which consists of multiple nested callbacks. It makes the code hard to read and debug."), new CorrectAnswer("It is nasty piece of code!")),
                List.of(new WrongAnswer("It defines the situation in which you did not notice that your mom tried to call you already ten times and now you have to call back"), new WrongAnswer("It occurs when you talk about very important stuff during phone call. Your phone suddenly turns off because you forgot to charge your battery and you can not find charger, therefore you can not callback. "))

        );
        return DocumentFromQuestionFactory.create(question);
    }
}
