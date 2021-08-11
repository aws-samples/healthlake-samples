package com.example.healthlake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashMap;
import java.util.Map;

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
        final String USAGE = "\n" + "Usage:\n"
                    + "java -jar <PATH_TO_YOUR_JAR> datastore-id=<Healthlake_Datastore_ID> region=<REGION> - To describe a HealthLake Datastore\n"
                    + "OR \n" + "java -jar <PATH_TO_YOUR_JAR> region=<REGION> - To list all HealthLake Datastores\n\n";
        SpringApplication.run(HealthlakeApplication.class, args);        
    }

    @Override
    public void run(String... args) {

        Region region = Region.US_EAST_1;     
        
        String dataStoreID = "";       

        Map<String, String> argMap = new HashMap<>();

        if (args.length > 0 ) {
            for (int i = 0; i < args.length; i++) {                
                argMap.put(args[i].split("=")[0], args[i].split("=")[1]);
            }

        }

        if (argMap.containsKey("region")) {            
            System.setProperty("aws.region", argMap.get("region"));     
        }           
        HealthLakeClient hlClient = HealthLakeClient.builder().build();
        
        if (argMap.containsKey("datastore-id")) {
            dataStoreID = argMap.get("datastore-id");                       
            
            DescribeFhirDatastoreRequest descFhirDSReq = DescribeFhirDatastoreRequest.builder().datastoreId(dataStoreID).build();
            DescribeFhirDatastoreResponse descFhirDSRes = hlClient.describeFHIRDatastore(descFhirDSReq);
            System.out.println("HealthLake Datastore Description : \n" + descFhirDSRes.toString());
        } else {
            ListFhirDatastoresRequest listFhirDSReq = ListFhirDatastoresRequest.builder().build();
            ListFhirDatastoresResponse listFhirDSRes = hlClient.listFHIRDatastores(listFhirDSReq);
            System.out.println("List of HealthLake Datastores : \n" + listFhirDSRes.toString());
        } 

    }

}
