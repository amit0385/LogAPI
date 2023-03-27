# Read Me First
* This project is a basic log message saving application for different log destination. 
At the point of creating the application it supports log destinations - Kafka, File and Database 
 
* The application build to interact with legacy application via REST call. Legacy application needs to follow the request contract and need to send the request in below format.

# Rest call format

- > Generic call - /v1/savelog/{destination}

* Kafka save request call  --  
   > **/v1/savelog/kafka**

* File save request call  --  
  > **/v1/savelog/file**

* Database save request call  --  
  > **/v1/savelog/db**

# Request format

```sh
{ 
 "applicationID" : "fromLegacy121",
  "traceId" : "a101",
  "severity" : "DEBUG",
  "timestamp" : "2023-03-26T01:01:01",
  "message" : "My json debug message"
}
```
# 

# To Run the application

### Assumption

* The application uses in-memory H2 database for DB rest call.
* This application assumes you have kafka server up and running. 
* Create a kafka topic on your running kafka server (assuming the server is on localhost:9092).
* IMPORTANT - Kafka topic name should be **'logmessage'**

>  kafka-topics.bat –-create –-topic logmessage -–bootstrap-server localhost:9092

* Clone the git repository
* execute - mvn clean install (assuming you have Maven configured in system or else use maven embedded in IDE)
* Go to LogApiApplication.java and right click - run as Java application
* On a REST call to file save - Check the 'logs' directory under project's root folder, where you can find 'microservice.log'

# Load Testing

### Apache Benchmark have been used for load testing

* Please check the script under project's root folder named "Apache_Benchmark_Script.txt"
* An observation has been attached at same location with name "Apache_Benchmark_Observation.pdf"
