#!/bin/bash

adb connect 192.168.43.1:5555

dir="/storage/self/primary/FIRST/PsiKit"

logs_on_robot=$(adb shell ls "${dir}")
num_logs=${#logs_on_robot[@]}

if [ $num_logs -eq 0 -o "${logs_on_robot}" = "" ]
then
  echo "aint got no logs"
else
  echo "ooh I got ${num_logs} to pull"
  echo "loook: [${logs_on_robot}]"
fi

cd logs
for log in $logs_on_robot
do
    echo "wow - look at this log: ${log}"
    if [ -e ./${log} ]
    then
      echo "no thanks, I've already got one"
    else
      adb pull "${dir}/${log}"
      if [ $? -eq 0 ]
      then
         echo "yay - I snagged ${log}"
      fi
    fi
done