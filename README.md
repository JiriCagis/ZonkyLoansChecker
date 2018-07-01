# Zonky Loans Checker
Terminal application periodically check new loans on the market company Zonky. Zonky is a Czech peer-to-peer online service that mediates online loans among people. For more information about working land you can visit [web](https://zonky.cz/). 
Application show today added loans after start and it check every 5 minutes whether was add new loans on market. If market contains new loans after time interval, application print new items into terminal.

### Note: 
You can specify your own check time or update URL to Zonky API in file **zonkyLoansChecker.properties**.

<p align='center'>
<img src="https://github.com/JiriCagis/ZonkyLoansChecker/blob/master/screenShotApp.png"/>
</p>

## How to build?
```
mvn clean package
```

## How to run?
```
mvn exec:java
```

## Technologies
* Java
* Maven
* Unirest
* Jakson
* Testing: Junit and Mockito
