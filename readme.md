# Project Title

Spring boot RESTful Deck cards challenge

### How to start the application

- Use git clone or download this project on your local machine.

- Import the gradle project in your favorite IDE such as Eclipse or IntelliJ and run the class challenge.deckcards.init.DeckcardsApplication.

- It can be also run using gradlew at the root of the project: ./gradlew build && java -jar build/libs/deckcards-0.0.1-SNAPSHOT.war

### How to use the application

GET request on http://localhost:8080/shuffle
	The request will return a dealDeckId to use with /dealOneCard

GET request on http://localhost:8080/dealOneCard?dealDeckId=<dealDeckId from shuffle call>
	The request will return the next dealed card from the deck represented by dealDeckId.
	The request will return an empty body once all the cards will be dealed from the deck.

### Quick project structure explanation

Root module
	Classes that interact with the client (who will call the rest request).
	
domain sub module
	Classes that contain the datas.
	
dao sub module
	Classes that init the datas depending of the implementation.
	
business sub module
	Classes that contain the business logic behind the application such as shuffle or dealOneCard
	
### TODO for future development

- Add more unit test scenario.
- Test the Spring Mvc controller itself.
- Add a mandatory X-Correlation-Id header to have a unique request Id provided from each client.
- Check for the synchronized code in case the application will be load balanced.
- Add REST request to clean the cache, improve the cache to maybe clean itself after some time, avoid to fill the memory server.
- Improve REST alignment with the TMFORM Specication (https://www.tmforum.org/)



