# Sample code to interact with Amazon HealthLake service.

You can see specific instructions under each folder. 

## Repo Organization

- hl-BATCH-operations folder contains samples in different lanagues to perform batch operations. Think of a batch operation as creating multiple patient resources in Amazon HealthLake from a file (ndjson with multiple records).
- hl-CRUD-operations folder contains samples to perform get, post, put and delete operations on a single resource in HealthLake.
- hl-MGMT-operations folder contains samples to perform management operations like listing all HealthLake datastores in an AWS Account, describing a specific HealthLake datastore or creating a HealthLake datastore (with Synthetic data from Synthea)

## Prerequisites:
- Configure AWS Access Key and Secret Access Key either by exporting the values of "`AWS_ACCESS_KEY_ID`" and "`AWS_SECRET_ACCESS_KEY`" or by using "aws configure" CLI command.

## Security

See [CONTRIBUTING](CONTRIBUTING.md#security-issue-notifications) for more information.

## License

This library is licensed under the MIT-0 License. See the LICENSE file.

