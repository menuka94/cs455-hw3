#!/bin/bash

gradle build

$HADOOP_HOME/bin/hadoop jar build/libs/cs455-hw3-1.0-SNAPSHOT.jar \
  cs455.hadoop.q4_yearlySO2Levels.YearlySO2LevelsJob \
  sample-data/cs455-hw3-data/gases \
  sample-data/output/yearly-so2-levels
