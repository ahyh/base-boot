#!/bin/bash

# activemq集群启动脚本
# 启动activemq集群之前需要启动zookeeper集群
# 要求将activemq在3台机器上的目录一致，配置文件的修改键notes
# 启动命令：./activemq-cluster-start.sh start
# 关闭命令：./activemq-cluster-start.sh stop

for host in mini1 mini2 mini3
do
	echo "$host:$1......"
	ssh $host "source /etc/profile;/apps/apache-activemq-5.15.9/bin/activemq $1"
done

sleep 2

for host in mini1 mini2 mini3
do
	echo "${host}:${1}ing"
	ssh $host "source /etc/profile;/apps/apache-activemq-5.15.9/bin/activemq status"
done