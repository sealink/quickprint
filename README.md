[![Download](https://api.bintray.com/packages/sealink/maven/quickprint/images/download.svg) ](https://bintray.com/sealink/maven/quickprint/_latestVersion)
[![Coverage Status](https://coveralls.io/repos/github/sealink/quickprint/badge.svg?branch=master)](https://coveralls.io/github/sealink/quickprint?branch=master)
[![Build Status](https://travis-ci.org/sealink/quickprint.svg?branch=master)](https://travis-ci.org/sealink/quickprint)

Print Service is designed to be run on-premise and provides a bridge for web connected applcations
to print to physical printers.

#### Building

```
./gradlew build
```

#### Running

For general development you are best to start the server via gradle
```
QUICK_PRINT_API_KEY=123 ./gradlew bootrun
```

The build will also produce both a WAR and standalone executable JAR file.

The WAR can be deployed to an application server such as TomCat, the JAR file can be started with 
the following command.

```
java -DQUICK_PRINT_API_KEY=123  -jar build/libs/quickprint-{version}.jar
```

#### Enabling SSL 

You can use the following instructions to create a self-signed certificate and instruct
the server to use it.

* Create the certificate
```
 keytool -genkey -alias tomcat \
 -storetype PKCS12 -keyalg RSA -keysize 2048 \
 -keystore keystore.p12 -validity 3650
```
* The following environment variables will also need to be configured
```
KEY_STORE_PASSWORD = "password as configured in the previous step"
spring.profiles.active = "ssl"
```

If you have issued pem (may have .crt extension) files and private key you can create a Keystore using the following method.

* First combine the pem files together
```
cat *.crt > combined.pem
```

* Create the keystore using these combined public keys with your private key
```
openssl pkcs12 -export -inkey [private.key] -in combined.pem -name tomcat -out keystore.p12
```

#### Deployment

Deployment is handled via gradle and travis if you follow the correct git conventions.

If you are creating a new minor or major release then you would do the following.

* Create a new release branch, based upon the major and min
```
git checkout -b release/0.1
```
* Update the changelog with the correct version number (in this case 0.1.0)

```
git commit -m "Release 0.1.0"
git tag 0.1.0
git push origin master --tags
```

A point release would simply be a tag on this branch and you would follow the previous procedure.


#### Project Builds and Structure

* Gradle is the build tool of choice and all things related are contained within the project.

Some example build tasks include

```concept
./gradlew build
./gradlew test
./gradlew versionInfo
```

* Project is maven compatible however we only publish to Bintray so you will need to manually configure
 the repository, full instructions can be found at the following site
 https://bintray.com/sealink/maven/printing
* TravisCI is used to test / build and publish new versions.
* JUnit is used for integration and unit level testing.
