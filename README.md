Assignment for ABN AMRO backend developer test on November/22.

APi was made with Spring Boot, Java 11, Jpa and H2 in memory database.
It runs with docker and API doc is made with Swagger.
There are 2 environments, which are dev and prod.
You can change between them by adding the command below in VM Options:
[-Dspring.profiles.active=prod]
Standard is dev.

Swagger API documentation AFTER RUNNING THE APPLICATION: [http://localhost:8080/swagger-ui.html#/]

Contains 1 Controller, which is:

RecipeController: 
With the methods:

@GetMapping
Get all recipes.

@GetMapping("/{recipeId}")
Get recipe by recipeId.

@GetMapping("/filter")
Get recipe by given filters

@PostMapping
Create new recipe.

@PutMapping({recipeId})
Update recipe by recipeId

@DeleteMapping("/{recipeId}")
Delete recipe by recipeId.

To run it:
- Clone this repo to your machine
- Via command line, use 'cd' command to go the folder of the project
- Execute the command [mvn clean install] - It will generate the jar of the application 
- Execute the command [java -jar target/abnamro-0.0.1-SNAPSHOT.jar] to run the application

- You can use Postman to try these apis or test them through Swagger. You can use the collection 
in postman-collection.json file.

- And also you can run via Docker.
- Use 'cd' command to go to the folder of the project and run the command:
- Execute: [mvn clean install]

  - build docker file
    [docker build -t recipe-api .]
  - run dockerfile
    [docker run -dp8080:8080 recipe-api]

You can also execute queries through H2 UI.
H2:[http://localhost:8080/h2/]
JDBC URL: jdbc:h2:file:~/abn-dev  -> for dev environment
JDBC URL: jdbc:h2:file:~/abn-prod  -> for prod environment
Passwords are in properties file.