# task-tracker
mvn package spring-boot:repackage       
docker-compose up -d

curl --location --request POST 'http://localhost:5388/v1/tasks/f5ed6a7e-155a-4d57-8fc4-21d1aec05f31/performed?duration=10'
curl --location --request GET 'http://localhost:5388/v1/tasks/f5ed6a7e-155a-4d57-8fc4-21d1aec05f31/averageDuration'
