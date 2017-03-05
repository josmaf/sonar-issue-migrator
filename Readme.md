# Introduction

Command-line tool to 'transfer' issues from one SonarQube project to another, located in the same of different SonarQube server.

**Example**:

SonarQube instance with Project_A containing thousands of issues. 

Some of then have been manually flagged to status "Confirmed" or "False-Positive".

At one point, Project_A is forked and a new analysis is run and stored in a new branch in the same SonarQube server. 
So, now we have:
- Project_A <-- Some issues are "Confirmed" or "False-Positive".
- Project_A Production <-- Same list of issues as in Project_A, but all are "Open".
  
As a developer, I'd like to flag the issues in 'Project_A Production' branch before starting to change code. 
That is what this tool is for.

# Requirements

JRE 8

# Compilation

```sh
$ mvn clean install
```

A deliverable zip will be generated in target folder.

# Installation

1. Donwload (TODO: link) o create deliverable zip following previous instructions.
2. Unzip "sonar-issue-migrator-bin.zip" to a directory.

# Usage

1. Edit *conf/configuration.properties* (please read comments in *configuration.properties* file)
2. Navigate to root folder containing *SonarIssueMigrator.jar*. Execute:
```sh
$ java -jar SonarIssueMigrator.jar
```



