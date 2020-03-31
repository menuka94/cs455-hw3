#!/bin/bash

$HADOOP_HOME/bin/hdfs dfs -mkdir /data;
$HADOOP_HOME/bin/hdfs dfs -mkdir /data/gases;
$HADOOP_HOME/bin/hdfs dfs -mkdir /data/meteorological;

ssh lattice-0 << EOF

cd /s/lattice-0/b/nobackup/galileo/menukaw/cs455-hw3-data;

$HADOOP_HOME/bin/hdfs dfs -put gases/* /data/gases
$HADOOP_HOME/bin/hdfs dfs -put meteorological/* /data/meteorological

EOF
