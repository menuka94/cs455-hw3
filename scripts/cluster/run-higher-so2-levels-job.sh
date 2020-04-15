#!/bin/bash

gradle build

$HADOOP_HOME/bin/hadoop jar build/libs/cs455-hw3-1.0-SNAPSHOT.jar \
  cs455.hadoop.q2_higherSO2Levels.HigherSO2LevelsJob \
  /data/gases \
  /home/output/higher-so2-levels
