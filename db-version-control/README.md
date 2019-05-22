# MongoDB migration tool for the console recruiter

## How to use
1. Change [application.properties](/db-version-control/src/main/resources/application.properties) file if it has different connection parameters values
2. Build the application using maven
    ```
        mvn clean install
    ```  
3. Go to target location. There, run application jar
    ```
        java -jar db-version-control-0.0.1-SNAPSHOT.jar
    ```
4. Check console logs to make sure everything went smoothly

## Writing your own migrations
Add new migration class to pokemzok.consolerecruiter.db.changelog package. Use [Changelog_20190420](/db-version-control/src/main/java/pokemzok/consolerecruiter/db/changelogs/question/Changelog_20190420.java) class as reference (an insert example). More information about how to write more sophisticated changelog is available [here](https://github.com/cybuch/mongobeeJ).

