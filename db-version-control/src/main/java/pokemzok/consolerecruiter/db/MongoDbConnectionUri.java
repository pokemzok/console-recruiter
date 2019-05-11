package pokemzok.consolerecruiter.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("mongeez")
@Component
public class MongoDbConnectionUri {

    private final static String URI_PREFIX = "mongodb://";
    private String database;
    private int port;
    private String host;
    private String username;
    private String password;

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        checkConnectionParameters();
        return URI_PREFIX + username +
                ":" +
                password +
                "@" +
                host +
                ":" +
                port +
                "/" +
                database;
    }

    private void checkConnectionParameters() {
        throwIfBlank(username, new RuntimeException("username value in application properties is blank or missing"));
        throwIfBlank(password, new RuntimeException("password value in application properties is blank or missing"));
        throwIfBlank(host, new RuntimeException("host value in application properties is blank or missing"));
        throwIfBlank(database, new RuntimeException("database value in application properties is blank or missing"));
        throwIfPortEqualsZero();
    }

    private void throwIfBlank(String param, RuntimeException exception){
        if(param == null || param.isBlank()){
            throw exception;
        }
    }

    private void throwIfPortEqualsZero(){
        if(port == 0){
            throw new RuntimeException("port value in application properties is zero or missing");
        }
    }
}
