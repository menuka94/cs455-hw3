#!/bin/bash

$HADOOP_HOME/bin/hdfs dfs -mkdir /cs455
$HADOOP_HOME/bin/hdfs dfs -mkdir /cs455/books
$HADOOP_HOME/bin/hdfs dfs -put *.txt /cs455/books
$HADOOP_HOME/bin/hdfs dfs -ls /cs455/books
$HADOOP_HOME/bin/hdfs dfs -copyToLocal /hdfs/path /local/path


# Port Forwarding
==================
# Namenode information
ssh -NL 32453:localhost:32453 menukaw@richmond.cs.colostate.edu

# Yarn Resource Manager
ssh -NL 32460:localhost:32460 menukaw@earth.cs.colostate.edu


# Shared Cluster
# http://augusta.cs.colostate.edu:50070
ssh -NL 50070:localhost:50070 menukaw@augusta.cs.colostate.edu
