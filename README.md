# Task Tracker
Task Tracker is an API that records and stores task time metrics. 

## Tech Stack
Java 17, Spring Boot, MySQL, Docker Compose

## Running locally

### Prerequisites
- Java 17
- Maven
- Docker and Docker Compose

The Docker MySQL image `amd64/mysql:5-debian` will probably not work on x86_64 architecture; it has only been tested in AMD64.

### Steps
1. Build the jar and run tests:
```shell
$ mvn package spring-boot:repackage
```
2. Bring up Docker Compose
```shell
$ docker-compose up -d
```
3. Execute requests again port 5388, for example:
```shell
curl --location --request POST 'http://localhost:5388/v1/tasks/f5ed6a7e-155a-4d57-8fc4-21d1aec05f31/performed?duration=10'
curl --location --request GET 'http://localhost:5388/v1/tasks/f5ed6a7e-155a-4d57-8fc4-21d1aec05f31/averageDuration'
```

## Swagger
Swagger UI can be accessed at `https://localhost:5388/swagger-ui/`

## Performance
Benchmark tests ran with `wrk` benchmark tool against Docker containers running an MacBook Pro M1 Pro, on a Colima VM (given 8GB memory and 2 CPUs).

447 requests per second for `POST /v1/tasks/{taskid}/performed`:
```shell
wrk -t12 -c100 -d30 -s ./post.lua --latency "http://localhost:5388/v1/tasks/15ed6a7e-155a-4d57-8fc4-21d1aec05f31/performed?duration=101"
Running 30s test @ http://localhost:5388/v1/tasks/15ed6a7e-155a-4d57-8fc4-21d1aec05f31/performed?duration=101
  12 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   201.83ms   90.47ms   1.77s    86.90%
    Req/Sec    40.85     16.75   100.00     62.04%
  Latency Distribution
     50%  191.36ms
     75%  224.21ms
     90%  272.62ms
     99%  551.53ms
  13471 requests in 30.10s, 0.94MB read
  Socket errors: connect 0, read 0, write 0, timeout 3
Requests/sec:    447.47
Transfer/sec:     31.96KB
```
1246 requests per second for `GET /v1/tasks/{taskid}/averageDuration`:
```shell
Running 30s test @ http://localhost:5388/v1/tasks/15ed6a7e-155a-4d57-8fc4-21d1aec05f31/averageDuration
  12 threads and 100 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency    76.62ms   26.99ms 369.82ms   85.34%
    Req/Sec   105.36     24.87   210.00     63.01%
  Latency Distribution
     50%   73.20ms
     75%   87.01ms
     90%  103.44ms
     99%  176.44ms
  37511 requests in 30.10s, 4.55MB read
Requests/sec:   1246.25
Transfer/sec:    154.75KB
```