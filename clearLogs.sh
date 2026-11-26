#!/bin/bash

adb connect 192.168.43.1:5555

dir="/storage/self/primary/FIRST/PsiKit"

logs_on_robot=$(adb shell ls "${dir}")
cd logs
for log in $logs_on_robot
do
    echo "wow - look at this log: ${log}"
    if [ -e ./${log} ]
    then
      echo "got ${log} right here, gonna blast it."
      adb shell "rm ${dir}/${log}"
    else
      echo -e "uhoh - I don't have ${log} remove anyway?(y/N): "
      read huh
      if [ "${huh}" = "Y" -o "${huh}" = "y" ]
      then
         adb shell "rm ${dir}/${log}"
      else
        echo "fine i wont touch it"
      fi
    fi
done