# pivotal-boot-camp
Practice project for the Perficient Detroit Pivotal boot camp

There are currently three applications:

* Candidate (`http://localhost:8080`)
  * H2 Database located at: `http://localhost:8080/CandidateService`
* Search (`http://localhost:8081`) 
* Employee (`http://localhost:8082`)
  * H2 Database located at: `http://localhost:8082/employeeConsole`

In order to run these projects you must have Maven and Java installed. Then:

1. Navigate to the root of whichever project you want to run in a command prompt.
1. Run: `mvn clean package`
1. Run: `java -jar target/APP_NAME-0.0.1-SNAPSHOT.jar`, where `APP_NAME` is `search/candidate/etc`

You will need to have all three running in order to fully use the project.

Once running: Start at the search application homepage, where you can choose to add new employees or candidates. After, navigate back to the search homepage and you can find a current employee or candidate and then proceed to edit them.

TODO:
* [ ] TrainingMaster
* [ ] Page Permissions
