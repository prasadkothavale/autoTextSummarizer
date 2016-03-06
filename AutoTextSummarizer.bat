@echo off
set JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF8
SET /P userin=Enter performance of your machine [high/mid/low]
ECHO Starting Auto text summarizer in %userin% mode...
IF /I %userin%==high (
java -jar -Xms1536m -Xmx3072m -mx1536m AutoTextSummarizer_high.jar
) ELSE (
IF /I %userin%==mid (
java -jar -Xms1536m -Xmx3072m -mx1536m AutoTextSummarizer_mid.jar
) ELSE (
java -jar -Xms1536m -Xmx3072m -mx1536m AutoTextSummarizer_low.jar
))

