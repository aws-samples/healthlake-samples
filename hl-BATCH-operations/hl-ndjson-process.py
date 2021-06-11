# Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
# SPDX-License-Identifier: MIT-0

import argparse
import json

import boto3
import requests
from requests_auth_aws_sigv4 import AWSSigV4

# Define the required input arguments
args_parser = argparse.ArgumentParser()
args_parser.add_argument(
    "data_store_endpoint",
    help="The HealthLake Data Store Endpoint including the resource path. \
        Example: https://healthlake.<AWS Region>.amazonaws.com/datastore/<FHIR DATA STORE ID/r4/",
)
args_parser.add_argument(
    "ndjson_file", help="The full file path to NDJSON file containing FHIR resources"
)
args_parser.add_argument(
    "region",
    help="The AWS region in which this HealthLake Data Store Exists. Example: us-east-1",
)

# Parse the input arguments
input_args = args_parser.parse_args()

data_store_endpoint = input_args.data_store_endpoint
ndjson_file = input_args.ndjson_file
region = input_args.region


session = boto3.session.Session(region_name=region)
client = session.client("healthlake")

auth = AWSSigV4("healthlake", session=session)

with open(ndjson_file) as ndjson:
    for json_line in ndjson:
        json_data = json.loads(json_line.rstrip())
        resource_type = json_data["resourceType"]
        resource_endpoint = f"{data_store_endpoint}{resource_type}"
        r = requests.post(resource_endpoint, json=json_data, auth=auth)
        print(r.json())
