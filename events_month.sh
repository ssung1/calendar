#!/bin/bash

url=http://localhost:8080

curl $url/events/month?date=$1
echo
