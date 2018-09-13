#!/bin/bash
cd "`dirname "$0"`"

humanUrl="http://www.uniprot.org/uniprot/?query=reviewed:yes+AND+organism:9606&format=fasta"
yeastUrl="http://www.uniprot.org/uniprot/?query=organism:643680&format=fasta"
ecoliUrl="http://www.uniprot.org/uniprot/?query=reviewed:yes+AND+organism:83333&format=fasta"

contFasta=resources/contaminants.fasta
targetDir=databases

tmpHuman="$targetDir/human.fasta"
tmpYeast="$targetDir/yeast.fasta"
tmpEcoli="$targetDir/ecoli.fasta"

timeStamp=`date "+%H%M"`
dateStamp=`date "+%Y%m%d"`
dateTimeStamp=`date "+%Y%m%d-%H%M"`

echo downloading human proteins ...
curl -v -o $tmpHuman "$humanUrl"
nHuman=`grep "^>.*" "$tmpHuman" | wc -l | sed -e 's/^[ \t]*//'`

echo downloading yeast proteins ...
curl -v -o $tmpYeast "$yeastUrl"
nYeast=`grep "^>.*" "$tmpYeast" | wc -l | sed -e 's/^[ \t]*//'`

echo downloading ecoli proteins ...
curl -v -o $tmpEcoli "$ecoliUrl"
nEcoli=`grep "^>.*" "$tmpEcoli" | wc -l | sed -e 's/^[ \t]*//'`

echo joing files ...
targetFile=${dateStamp}_HYE_H${nHuman}_Y${nYeast}_E${nEcoli}_entries.fasta
cat $tmpHuman > $targetDir/$targetFile
cat $tmpYeast >> $targetDir/$targetFile
cat $tmpEcoli >> $targetDir/$targetFile
echo file $targetFile created!

echo adding contaminants ...
# count contaminants
nConts=`grep "^>.*" $contFasta | wc -l | sed -e 's/^[ \t]*//'`
targetWithContaminantsFile=${dateStamp}_HYE_H${nHuman}_Y${nYeast}_E${nEcoli}entries_and_${nConts}contaminants.fasta
cat $targetDir/$targetFile > $targetDir/$targetWithContaminantsFile
cat "$contFasta" >> $targetDir/$targetWithContaminantsFile
echo file ${targetWithContaminantsFile} created!

echo removing temporary files ...
rm $tmpHuman
rm $tmpYeast
rm $tmpEcoli

echo all done!
