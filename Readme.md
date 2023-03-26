## Distance calculator
### Description
A simple project that allows to calculate distance between cities.
There 2 calculation modes:

 1. **Crowflight.** Calculates the shortest distance between two cities based on their coordinates online using formula.
 2. **Distance matrix** Retrieves distance stored in the database.
### Prerequisites
 1. Tomcat 8.
	- If using Tomcat 10+, you will have to place `war` archive in `webapps-javaee` folder instead of standart `webapps`.
 2. MySQL Server with `distance-calculator` database. Tables will be created automatically via Liquibase.
### Instalation
 1. Run `mvn clean package`
 2.  Deploy the `war` archive to Tomcat server.
### Usage
**Main endpoints:**
|**Method**|**Endpoint**  |**Notes**|
|--|--|--|
|GET|api/cities|List of cities.|
|POST|api/calculator|Calculate distances. Request must be in format of [CalculationRequestDto](https://github.com/FireShine1/distance-calculator/blob/master/src/main/java/org/fireshine/distance-calculator/dto/CalculationRequestDto.java).|
|POST|api/upload|Allows to upload xml file with cities and distances data and then save it into database.|
There are also support for CRUD operations with cities, and Create/Read operations for distances.
