cd ../classes/
echo "Manifest-Version: 1.0" >> ./manifest.mf
echo "Main-Class: com.lilzh.runner.RunApp" >> ./manifest.mf
echo "Class-Path: lib/word-printer.jar lib/word-printer-runnable.jar" >> ./manifest.mf
jar cvfm ../target/word-printer-runnable-uselib.jar ./manifest.mf ./com/lilzh/runner/RunApp.class

cd ../target/
mkdir lib/
cp ./word-printer.jar ./lib/
java -jar word-printer-runnable-uselib.jar
