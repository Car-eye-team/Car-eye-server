export JAVA_HOME=/usr/local/jdk1.7.0_79
export JAVA_BIN=/usr/local/jdk1.7.0_79/bin
export PATH=$PATH:$JAVA_HOME/bin
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

#/usr/local/nginx/sbin/nginx

/usr/local/mongodb/bin/mongod --config /usr/local/mongodb/bin/mongodb.conf

sh /home/java/comm/qidong.sh

sh /home/java/taxi/tomcat-taxiweb/bin/startup.sh
sh /home/java/taxi/tomcat-taxiapi/bin/startup.sh
sh /home/java/taxi/tomcat-taxiserver/bin/startup.sh

cd /home/java/taxi/taxiflexserver/ 
nohup java -jar taxiflexserver_1.0.jar  > /home/java/taxi/taxiflexserver/taxiflexserver.file 2>&1&


