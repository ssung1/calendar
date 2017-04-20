#!/bin/bash

url=http://localhost:8080

curl $url/events/day?date=$1
echo
