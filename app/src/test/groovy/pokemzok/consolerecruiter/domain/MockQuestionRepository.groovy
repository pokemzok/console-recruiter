package pokemzok.consolerecruiter.domain

import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

class MockQuestionRepository implements QuestionRepository {
    private def questions = new LinkedList<Question>()

    MockQuestionRepository() {
        questions.add(
                new Question(
                        "Which programming language is the best?",
                        List.of(new CorrectAnswer("Java"), new CorrectAnswer("C#"), new CorrectAnswer("Kotlin")),
                        List.of(new WrongAnswer("English"), new WrongAnswer("Japanese"))
                )
        )
        questions.add(
                new Question(
                        "Who is Java father?",
                        List.of(new CorrectAnswer("James Gosling")),
                        List.of(new WrongAnswer("Ryan Gosling"), new WrongAnswer("Matt Damon"))
                )
        )
        questions.add(
                new Question(
                        "What do you know about Garbage Collector?",
                        List.of(new CorrectAnswer("It is a form of automatic memory management"), new CorrectAnswer("Most of recruiters can not answer how the knowledge about GC will be useful in context of the project")),
                        List.of(new WrongAnswer("Its those dudes from Mass Effect 2!"), new WrongAnswer("Thanks to garbage collection cities are clean, which prevents global warming"))
                )
        )
        questions.add(
                new Question(
                        "What is a callback hell?",
                        List.of(new CorrectAnswer("It is an anti-pattern which consists of multiple nested callbacks. It makes the code hard to read and debug."), new CorrectAnswer("It is nasty piece of code!")),
                        List.of(new WrongAnswer("It defines the situation in which you did not notice that your mom tried to call you already ten times and now you have to call back"), new WrongAnswer("It occurs when you talk about very important stuff during phone call. Your phone suddenly turns off because you forgot to charge your battery and you can not find charger, therefore you can not callback. "))

                )
        )
    }

    // FIXME my compiler demands this, fix the issue
    Object save (Object s){
        throw new RuntimeException("Not supported")
    }

    @Override
    <S extends Question> S save(S s) {
        throw new RuntimeException("Not supported")
    }

    @Override
    <S extends Question> List<S> saveAll(Iterable<S> iterable) {
        throw new RuntimeException("Not supported")
    }

    Optional<Question> findById(String s) {
        throw new RuntimeException("Not supported")
    }

    boolean existsById(String s) {
        throw new RuntimeException("Not supported")
    }

    @Override
    List<Question> findAll() {
        return questions
    }

    @Override
    List<Question> findAll(Sort sort) {
        throw new RuntimeException("Not supported")
    }

    @Override
    Page<Question> findAll(Pageable pageable) {
        throw new RuntimeException("Not supported")
    }

    @Override
    <S extends Question> List<S> findAll(Example<S> example, Sort sort) {
        throw new RuntimeException("Not supported")
    }

    @Override
    <S extends Question> List<S> findAll(Example<S> example) {
        throw new RuntimeException("Not supported")
    }

    @Override
    <S extends Question> List<S> insert(Iterable<S> iterable) {
        throw new RuntimeException("Not supported")
    }
    //FIXME my compiler demands this, fix the issue
    Object insert(Object object){
        throw new RuntimeException("Not supported")
    }

    @Override
    <S extends Question> S insert(S s) {
        throw new RuntimeException("Not supported")
    }

    @Override
    List<Question> findAllById(Iterable<String> iterable) {
        throw new RuntimeException("Not supported")
    }

    @Override
    long count() {
        return questions.size()
    }

    void deleteById(String s) {
        throw new RuntimeException("Not supported")
    }

    @Override
    void delete(Question question) {
        throw new RuntimeException("Not supported")
    }

    @Override
    void deleteAll(Iterable<? extends Question> iterable) {
        throw new RuntimeException("Not supported")
    }

    @Override
    void deleteAll() {
        throw new RuntimeException("Not supported")
    }

    @Override
    <S extends Question> Optional<S> findOne(Example<S> example) {
        throw new RuntimeException("Not supported")
    }

    @Override
    <S extends Question> Page<S> findAll(Example<S> example, Pageable pageable) {
        throw new RuntimeException("Not supported")
    }

    @Override
    <S extends Question> long count(Example<S> example) {
        throw new RuntimeException("Not supported")
    }

    @Override
    <S extends Question> boolean exists(Example<S> example) {
        throw new RuntimeException("Not supported")
    }
}
