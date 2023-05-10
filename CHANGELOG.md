# QuickPrint

## Unreleased

* [TF-342] Add eb environment name to github action

## 1.5.0

* [TT-5649] - Update to use non-experimental CoRoutines
* [TT-6290] - Build with openjdk8
* [TT-7089] - Update to Spring 2.2.6 and Gradle 4.10.2
* [TT-8218] - Update to Spring 2.3.4 and Grandle 6.7
* [PLAT-28] - Migrate from Travis to Github Action
* [TT-11531] - Migrated to using separate margin_x and margin_y params in PageFormat
* [TT-11686] - Updated cups-pdf ppd path in docker/entrypoint.sh
* [TF-302] - Update java version to 19 and related dependencies
* [TF-240] - Add rollbar integration
* [Tf0303] - Update gradle wrapper

## 1.3.1

* [TT-4911] - Fix images when no height or width is specified

## 1.3.0

* [TT-4629] - Disable images when printing receipts
* [TT-4906] - Add support for element width and height

## 1.2.0

* [TT-4614] - Add new endpoint which supports printing receipt tickets to Epson printers

## 1.1.1

* [TT-4595] - Fix docker build

## 1.1.0

* [TT-4522] - Add ability to generate docker image and publish to ECR
* [TT-4590] - Add elastic beanstalk deployment

## 1.0.2

* [TT-4561] - Fix issue printing duplicate tickets

## 1.0.1

* [TT-4557] - Enable CORS support
* [TT-4558] - Enable reading of double font sizes
* [TT-4559] - Ensure print-tickets returns a JSON response

## 1.0.0

* [TT-4514] - Initial port of Quickets
* [TT-4549] - Add profile to enable SSL
* [TT-4554] - Enable logentries in production mode
