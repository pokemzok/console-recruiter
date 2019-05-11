package pokemzok.consolerecruiter.db;

import com.github.mongobeej.Mongobee;
import com.github.mongobeej.exception.MongobeeException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbApplication implements CommandLineRunner {

    private final MongoDbConnectionUri uri;

    public DbApplication(MongoDbConnectionUri uri) {
        this.uri = uri;
    }

    public static void main(String[] args) {
        SpringApplication.run(DbApplication.class, args);
    }

    @Override
    public void run(String... args) throws MongobeeException {
        Mongobee runner = new Mongobee(uri.toString());
        runner.setChangeLogsScanPackage(
                "pokemzok.consolerecruiter.db.changelogs");
        runner.execute();
    }
}
