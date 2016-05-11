# nonzeroexit
Project that shows behaviour of an application when the discovery & config servers are not found, causing it to exit. Unfortunately it exits with an exit code of 0 when launched as a jar application.

# Versions

| Component | Version|
|-----------|--------|
| Docker Toolbox | 1.9.0 |
| Spring Boot | 1.3.x.RELEASE  (versions greate than 1.3.1.RELEASE) |

# Build and Run

In the root folder run the following command which will build your jars:

```sh
$ ./gradlew clean build
```

After that you can start the sample-application using the following:

```sh
$ java -jar sample-service/build/libs/sample-service-0.0.1-SNAPSHOT.jar
```

The application will attempt to start but fail and the java process will exit. To see the exit code from the java process run the following command (in linux/os-x):


```sh
$ echo $?
```

And you will see that the exit code is 0. 

Note that if you run the following:

```sh
$ ./gradle sample-service:bootRun
```

that the exit code there is correctly returned as 1. This indicates that the issue lies with the changes made here:

https://github.com/spring-projects/spring-boot/blob/v1.3.2.RELEASE/spring-boot-tools/spring-boot-loader/src/main/java/org/springframework/boot/loader/MainMethodRunner.java

If you regress to version 1.3.1.RELEASE (by modifying the springBootVersion property in the sample-service/build.gradle file) you will see that the exit code there is 1.

