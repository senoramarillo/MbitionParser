The app will read the "logs" file and create an "analysis.json" file containing the analyzed data in JSON format.
The output for date and times are not great, should be tweaked with some annotations.

The root JSON object contains the following fields:
- appTime: Time spent per each application
- searchOccurrences: Most used searched words in the search box
- vehicleStateOccurrences: Moving state of the car (was the car parked, moving or moving at high speed)

Requirements:
- Maven 3
- Java 8

Usage:
> mvn clean install
> java -jar target/mbit-1.0-SNAPSHOT-jar-with-dependencies.jar
