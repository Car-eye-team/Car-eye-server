sec=7 

while true
do  
  javaThread=`ps -ef | grep dsauth_1.0.jar | grep -v "grep"`  
  echo $javaThread  
  count=`ps -ef | grep dsauth_1.0.jar | grep -v "grep" | wc -l`  
  echo $count  
  if [ $count -gt 0 ]; then  
    echo sleep $sec second the $var time, the dsauth_1.0.jar thread is still alive  
    echo "break" 
    break  
  else 
    cd /home/java/comm/dsauth/ 
    nohup java -jar dsauth_1.0.jar  > /home/java/comm/filelog/dsauth.file 2>&1&
    sleep $sec  
  fi  
done  
  


while true
do
  javaThread1=`ps -ef | grep dsbusiness_1.0.jar | grep -v "grep"`
  echo $javaThread1
  count1=`ps -ef | grep dsbusiness_1.0.jar | grep -v "grep" | wc -l`
  echo $count1
  if [ $count1 -gt 0 ]; then
    echo sleep $sec second the $var time, the dsbusiness_1.0.jar thread is still alive
    echo "break"
    break
  else
    cd /home/java/comm/dsbusiness/
    nohup java -jar dsbusiness_1.0.jar  > /home/java/comm/filelog/dsbusiness.file 2>&1&
    sleep $sec
  fi
done

while true
do
  javaThread2=`ps -ef | grep dsparse_1.0.jar | grep -v "grep"`
  echo $javaThread2
  count2=`ps -ef | grep dsparse_1.0.jar | grep -v "grep" | wc -l`
  echo $count2
  if [ $count2 -gt 0 ]; then
    echo sleep $sec second the $var time, the dsparse_1.0.jar thread is still alive
    echo "break"
    break
  else
    cd /home/java/comm/dsparse/
    nohup java -jar dsparse_1.0.jar  > /home/java/comm/filelog/dsparse.file 2>&1&
    sleep $sec
  fi
done

while true
do
  javaThread3=`ps -ef | grep dscomm_1.0.jar | grep -v "grep"`
  echo $javaThread3
  count3=`ps -ef | grep dscomm_1.0.jar | grep -v "grep" | wc -l`
  echo $count3
  if [ $count3 -gt 0 ]; then
    echo sleep $sec second the $var time, the dscomm_1.0.jar thread is still alive
    echo "break"
    break
  else
    cd /home/java/comm/dscomm/
    nohup java -jar dscomm_1.0.jar  > /home/java/comm/filelog/dscomm.file 2>&1&
    sleep $sec
  fi
done


while true
do
  javaThread5=`ps -ef | grep dssms_1.0.jar | grep -v "grep"`
  echo $javaThread5
  count5=`ps -ef | grep dssms_1.0.jar | grep -v "grep" | wc -l`
  echo $count5
  if [ $count5 -gt 0 ]; then
    echo sleep $sec second the $var time, the dssms_1.0.jar thread is still alive
    echo "break"
    break
  else
    cd /home/java/comm/dssms/
    nohup java -jar dssms_1.0.jar  > /home/java/comm/filelog/dssms.file 2>&1&
    sleep $sec
  fi
done

while true
do
  javaThread6=`ps -ef | grep careyeauth_1.0.jar | grep -v "grep"`
  echo $javaThread6
  count6=`ps -ef | grep careyeauth_1.0.jar | grep -v "grep" | wc -l`
  echo $count6
  if [ $count6 -gt 0 ]; then
    echo sleep $sec second the $var time, the careyeauth_1.0.jar thread is still alive
    echo "break"
    break
  else
    cd /home/java/comm/careyeauth/
    nohup java -jar careyeauth_1.0.jar  > /home/java/comm/filelog/careyeauth.file 2>&1&
    sleep $sec
  fi
done


