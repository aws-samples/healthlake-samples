# Sample Python code for Amazon HealthLake CRUD Operations on NDJSON files.

## Prerequisites:

- Install the required Python packages. "`pip3 install requirements.txt`"
- Configure AWS Access Key and Secret Access Key either by exporting the values of "`AWS_ACCESS_KEY_ID`" and "`AWS_SECRET_ACCESS_KEY`" or by using "aws configure".

## Notes:

- Sample input files for POST operation code are available in "`hl-BATCH-operations/Sample-Input-Files`" folder
- Example command line invocation:
  ```
  python3 hl-BATCH-operations/hl-ndjson-process.py https://healthlake.us-east-1.amazonaws.com/datastore/<data_store_id>/r4/ hl-BATCH-operations/Sample-Input-Files/sample_patients.ndjson us-east-1
  ```
- When in doubt, use the "`-h`" or "`--help`" option available in each of these Python scripts to understand the required inputs and the order in which they are expected. For example: "`python3 hl-ndjson-process.py -h`"
