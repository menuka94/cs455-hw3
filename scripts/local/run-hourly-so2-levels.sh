#!/bin/bash

gradle build

$HADOOP_HOME/bin/hadoop jar build/libs/cs455-hw3-1.0-SNAPSHOT.jar \
  cs455.hadoop.hourlySO2Levels.HourlySO2LevelsJob \
  sample-data/cs455-hw3-data/gases \
  sample-data/output/hourly-so2-levels
