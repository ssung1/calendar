#!/bin/bash

url=http://localhost:8080

function insert {
    object=$1
    shift
    data="$@"
    curl -X POST -H "Content-Type: application/json" -d "$data" $url/$object
}

##########################################################################
insert calendars '{ 
    "name": "holidays", 
    "user": "admin" 
}'

insert events '{
    "calendar": "/calendars/1",
    "title": "New Years Day",
    "dateTime": "2017-01-01T00:00:00Z",
    "location": "Times Square",
    "attendees" : [ "everyone" ],
    "reminderTime": 60
}'

insert events '{
    "calendar": "/calendars/1",
    "title": "Groundhog Day",
    "dateTime": "2017-02-02T00:00:00Z",
    "location": "Burrow",
    "attendees" : [ "Phil", "Rita", "Larry" ],
    "reminderTime": 60
}'

insert events '{
    "calendar": "/calendars/1",
    "title": "Earth Day",
    "dateTime": "2017-04-22T00:00:00Z",
    "location": "Earth",
    "attendees" : [ "earth" ],
    "reminderTime": 60
}'

##########################################################################
insert calendars '{ 
    "name": "meetings", 
    "user": "admin" 
}'

insert events '{
    "calendar": "/calendars/2",
    "title": "Sprint Planning",
    "dateTime": "2017-04-24T10:00:00Z",
    "location": "Room 100",
    "attendees" : [ "Bob", "Wendy", "Bernard" ],
    "reminderTime": 15
}'

insert events '{
    "calendar": "/calendars/2",
    "title": "Test Planning",
    "dateTime": "2017-04-29T15:00:00Z",
    "location": "Room 101",
    "attendees" : [ "Scoop", "Muck" ],
    "reminderTime": 15
}'

insert events '{
    "calendar": "/calendars/2",
    "title": "Retrospective",
    "dateTime": "2017-05-01T11:00:00Z",
    "location": "Room 102",
    "attendees" : [ "Dizzy", "Roley" ],
    "reminderTime": 15
}'
