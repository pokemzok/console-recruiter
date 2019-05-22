# Console Recruiter
Have you ever wanted to test your knowledge under time pressure? Me neither, but now we both can, thanks to the console recruiter!  
Console recruiter allows you to feel a joy of recruitment process in your calm home environment.   
Console recruiter allows you to actually select how much time pressure you want to feel.   
Console recruiter allows you to create your own question list.    
**Now lets jump straight into it!**  

<p align="center">
    <img alt="recruitment" src="https://raw.githubusercontent.com/pokemzok/console-recruiter/master/app/images/recruitment.gif" />
</p>  

## But before you do
0. Make sure you have Java 11 and running Docker
1. Run [docker-up.bat](/console-recruiter-app/docker/docker-up.bat) (or if you on linux, just copy the command from this script)
2. Complete [README](/db-version-control) from db project (how to use section)
3. Build application with maven
    ```
     mvn clean install
    ```
4. Deploy an application. Go to the target location of your build and run
    ```
    java -jar app-0.0.1-SNAPSHOT.jar
    ```
5. Enjoy the time race!    

## The time pressure is too much! How to reduce it?
Edit [application.property](/app/src/main/resources/application.properties) parameter and feel the shame. Every recruiter on the planet is looking down on you.  
```
time.left=3 // in minutes
```

## I do not like having two rating strategy. Can I turn one of them off?
Yes you can disable university like counting strategy. But be warned, because you will stop feeling student-like thrill of the University examination.  
Edit [application.property](/app/src/main/resources/application.properties) like so. 
```
# Counting strategies
lenient-strategy.enabled=true
university-strategy.enabled=false
``` 
## This is too easy! I want more challenging questions! 
That's the spirit! Every recruiter is moderately but sincerely clapping. Better clean your email box, because tonight you will get plenty of job offers!  
Go to the db-version-control [README](/db-version-control) page and do as it says (writing your own migration section)
 
# Time for some boring technical stuff
## Persistence
Application uses MongoDB. If you want to change some db parameter there are three places of interests.  
1. [docker-compose.yml](/app/docker/docker-compose.yml) - here you can configure your mongoDb docker image.  
2. [application.properties](/app/src/main/resources/application.properties) - here you can change your mongoDb spring related properties.  
3. [application.properties](/db-version-control/src/main/resources/application.properties) - here you can change your mongoDb migration tool related properties.  

## Logging
Application stores log into the logs directory. More information about how the logger would behave are available in [logback-spring.xml](/app/src/main/resources/logback-spring.xml) 

## Multithreading
Recruit rating use multithreading. For every counting strategy there would be a new thread created during rating process.  
If you want to write your own counting strategy implement CountingPointsStrategy and than configure your new bean in the similar way as LenientCountingStrategy.

## MongoDb migration tool
If you looking for an example how to manage your database changes make sure to look into the db-version-control [README](/db-version-control) page.


