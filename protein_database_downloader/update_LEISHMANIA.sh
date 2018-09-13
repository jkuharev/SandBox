#!/bin/bash
cd "`dirname "$0"`"

targetUrl="http://www.uniprot.org/uniprot/?query=organism:5664&format=fasta"
contentId=up_leishmania_reference

contFasta=resources/contaminants.fasta
targetDir=databases
tempFasta=${targetDir}/${contentId}.fasta

timeStamp=`date "+%H%M"`
dateStamp=`date "+%Y%m%d"`
dateTimeStamp=`date "+%Y%m%d-%H%M"`

echo downloading file ...
curl -v -o "$tempFasta" $targetUrl

echo counting entries ...
nEntries=`grep "^>.*" $tempFasta | wc -l | sed -e 's/^[ \t]*//'`

echo renaming downloaded file ...
targetFileName=${dateStamp}_${contentId}_${nEntries}entries.fasta
mv $tempFasta ${targetDir}/$targetFileName
echo result stored to file "${targetFileName}"

echo adding contaminants to the downloaded data ...
nContaminantsEntries=`grep "^>.*" $contFasta | wc -l | sed -e 's/^[ \t]*//'`
targetWithContaminantsFileName=${dateStamp}_${contentId}_${nEntries}entries_and_${nContaminantsEntries}contaminants.fasta

cp $targetDir/$targetFileName $targetDir/$targetWithContaminantsFileName
cat $contFasta >> ${targetDir}/$targetWithContaminantsFileName

echo all done!
