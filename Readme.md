# Introduction

Command-line tool to copy issue features (status, resolution) from one SonarQube project to another. 

Both projects (origin and destination) can be in the same of **different** SonarQube server.

Issue features currently covered:

- Statuses: Open
- Resolution: False-positive

**Scenario Example**:

SonarQube instance with Project_A containing thousands of issues. 

Some of them have been manually flagged to status "Confirmed" or "False-Positive".

At one point, Project_A is forked and a new analysis is run. Analysis results are stored in a new branch (let's call it "Production") in the same (or different) SonarQube server.
 
So, now we have:

- Project_A : Mix of "Open", "Confirmed" and "False-Positive" issues.
- Project_A Production : Same list of issues as in Project_A, but all are "Open".
  
As a developer, I want to flag the issues in 'Project_A Production' branch before starting to change code, so that new analysis contains same "flags" (Confirmed, False-positive) as the original one.

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

Steps to copy issues from Project_A to Project_B:

1. Run SonarQube analysis on Project_A:branch_A (note: using Sonar branches is optional) 
2. Run SonarQube analysis on Project_B:branch_B (note: using Sonar branches is optional). **IMPORTANT: source code of Project_A and Project_B should be as similar as possible to each other**.
3. Edit *conf/configuration.properties* (please read comments in *configuration.properties* file).  
4. Navigate to folder where you have unzipped "sonar-issue-migrator-bin.zip". It should contain *SonarIssueMigrator.jar*. Execute:
```sh
$ java -jar SonarIssueMigrator.jar
```
5. Program will start running. Once execution is done, it will show:

- Total number of flagged issues obtained from URL (*flagged_issues_url* parameter in config file)
- Number of matched issues: how many open issues have been found in destination project ('Project_B') matching the flagged ones. The tool compares issues based on <Component, Rule, Line> triad. 



