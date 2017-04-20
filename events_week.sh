#!/bin/bash

url=http://localhost:8080

curl $url/events/week?date=$1
echo
