package com.example.healthlake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.healthlake.HealthLakeClient;
import software.amazon.awssdk.services.healthlake.model.DescribeFhirDatastoreRequest;
import software.amazon.awssdk.services.healthlake.model.DescribeFhirDatastoreResponse;
import software.amazon.awssdk.services.healthlake.model.ListFhirDatastoresRequest;
import software.amazon.awssdk.services.healthlake.model.ListFhirDatastoresResponse;

@SpringBootApplication
public class HealthlakeApplication implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(HealthlakeApplication.class);

    public static void main(String[] args) {
        LOG.info("Running your spring boot console application");
        SpringApplication.run(HealthlakeApplication.class, args);
    }

    @Override
    public void run(String... args) {

        Region region = Region.US_EAST_1;
        HealthLakeClient hlClient = HealthLakeClient.builder().region(region).build();
        String dataStoreID = "";
        if (args.length == 1) {
            dataStoreID = args[0];
            DescribeFhirDatastoreRequest descFhirDSReq = DescribeFhirDatastoreRequest.builder().datastoreId(dataStoreID)
                    .build();
            DescribeFhirDatastoreResponse descFhirDSRes = hlClient.describeFHIRDatastore(descFhirDSReq);
            System.out.println("HealthLake Datastore Description : \n" + descFhirDSRes.toString());
        } else if (args.length == 0) {
            ListFhirDatastoresRequest listFhirDSReq = ListFhirDatastoresRequest.builder().build();
            ListFhirDatastoresResponse listFhirDSRes = hlClient.listFHIRDatastores(listFhirDSReq);
            System.out.println("List of HealthLake Datastores : \n" + listFhirDSRes.toString());
        } else {
            final String USAGE = "\n" + "Usage:\n"
                    + "java -jar <PATH_TO_YOUR_JAR> <Healthlake_Datastore_ID> - To describe a HealthLake Datastore\n"
                    + "OR \n" + "java -jar <PATH_TO_YOUR_JAR> - To list all HealthLake Datastores\n\n";
            System.out.println(USAGE);
        }

    }

}
