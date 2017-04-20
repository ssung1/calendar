Calendar Demo

The application is developed using Spring to provide RESTful web services to
manage event calendars.  The data access is done through Spring JPA, although
an actual database is not required to run.  Items #5 and #6 were not
implemented.



The build tool is Gradle.  To build the application, run

    ./gradlew build

And to run the application, do

    java -jar build/libs/calendar-0.1.0.jar



Once the application starts, run

    insert.sh

to insert some sample data.  The script uses curl to POST data to
localhost:8080 where the web service can be accessed.  Feel free to edit the
script to insert more data.



To see the list of CRUD services, run

    index.sh

The CRUD services are hypermedia-driven.  To get information on each service,
use

    index.sh {service}



To see all the calendars, run

    calendars.sh



To see all the events, run

    events.sh



To search for events of a day, use this script

    events_day.sh {yyyyMMdd} # try 20170101 for new years day



To search for events of a week, run

    events_week.sh {yyyyMMdd} # try 20170426 to see all the events in the same week


To search for events of a month, run

    events_month.sh {yyyyMMdd} # try 20170401 to see all the events in Apr 2017
