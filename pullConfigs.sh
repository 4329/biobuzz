#!/bin/bash

adb connect 192.168.43.1:5555

dir="/storage/self/primary/FIRST"

mapfile -d '' files_on_robot < <(adb shell "find ${dir} -maxdepth 1 -mindepth 1 -print0 2>/dev/null")

cd config
for file in "${files_on_robot[@]}"
do
   if [ $(echo $file | grep -c -E '.xml$') -eq 0 ]
   then
     echo "${file} aint no xml file"
   else
     echo "oooh - I want me some ${file}"
     adb pull "${file}"
   fi

#    echo "wow - look at this log: ${log}"
#    if [ -e ./${log} ]
#    then
#      echo "no thanks, I've already got one"
#    else
#      adb pull "${dir}/${log}"
#      if [ $? -eq 0 ]
#      then
#         echo "yay - I snagged ${log}"
#      fi
#    fi
done