package pokemzok.consolerecruiter.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuestionRepository extends MongoRepository<Question, String> {

}
