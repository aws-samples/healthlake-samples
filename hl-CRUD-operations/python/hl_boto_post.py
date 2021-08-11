# Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
# SPDX-License-Identifier: MIT-0

# PyPI dependencies and pip command to install them:
# pip3 install boto3 requests requests-auth-aws-sigv4

# This python script to POST/Insert a FHIR resource requires the following mandatory CLI arguments. 
# Run "hl_boto_put.py --help" for more details
    # HealthLake data store endpoint
    # Resource Path
    # File containing the Request body
    # AWS region

import boto3
import requests
import argparse
import json
from requests_auth_aws_sigv4 import AWSSigV4

# Define the required input arguments
args_parser = argparse.ArgumentParser()
args_parser.add_argument(
    "data_store_endpoint", 
    help="The HealthLake Data Store Endpoint including the resource path. \
        Example: https://healthlake.<AWS Region>.amazonaws.com/datastore/<FHIR DATA STORE ID/r4/")
args_parser.add_argument(
    "resource_path", 
    help="The resource path. Example: Patient/")
args_parser.add_argument(
    "request_body_file", 
    help="The full file path where the POST request body contents are available. \
        Refer to https://docs.aws.amazon.com/healthlake/latest/devguide/create-example.html for sample \
        POST request body")
args_parser.add_argument(
    "region", 
    help="The AWS region in which this HealthLake Data Store Exists. Example: us-east-1")

# Parse the input arguments
input_args = args_parser.parse_args()

data_store_endpoint = input_args.data_store_endpoint
resource_path = input_args.resource_path
request_body_file = input_args.request_body_file
region = input_args.region

#Frame the resource endpoint
resource_endpoint = data_store_endpoint+resource_path

session = boto3.session.Session(region_name=region)
client = session.client("healthlake")

# Frame authorization
auth = AWSSigV4("healthlake", session=session)

# Read the request body from input file
with open(request_body_file) as json_body:
    json_data = json.load(json_body)

# Calling data store FHIR endpoint using SigV4 auth
r = requests.post(resource_endpoint, json=json_data, auth=auth)
print(r.json())