# Sample code to interact with Amazon HealthLake service.

## Prerequisites:
- Configure AWS Access Key and Secret Access Key either by exporting the values of "`AWS_ACCESS_KEY_ID`" and "`AWS_SECRET_ACCESS_KEY`" or by using "aws configure" CLI command.

## Java
- JDK 8 or above
### How to Run?
- Clone the repo `git clone https://github.com/aws-samples/healthlake-samples.git`
- Traverse to java samples folder `cd healthlake-samples/hl-MGMT-operations/java`
- Build the java project `./gradlew build` on Mac or `gradlew.bat build` on windows or `gradle build` (if gradle cli is installed)
- Run the application : 
- To List all HealthLake datastores `java -jar build/libs/healthlake-0.0.1-SNAPSHOT.jar` 
- To describe a specific datastore `java -jar build/libs/healthlake-0.0.1-SNAPSHOT.jar <Datastore_ID>`

## Security

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## License

This library is licensed under the MIT-0 License. See the LICENSE file.
