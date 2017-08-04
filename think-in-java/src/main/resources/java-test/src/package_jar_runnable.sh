cd ../classes/
echo "Manifest-Version: 1.0" >> ./manifest.mf
echo "Main-Class: com.lilzh.runner.RunApp" >> ./manifest.mf
jar cvfm ../target/word-printer-runnable.jar ./manifest.mf *

java -jar ../target/word-printer-runnable.jar
