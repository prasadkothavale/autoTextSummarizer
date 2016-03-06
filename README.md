# autoTextSummarizer
GUI based Java application which creates summary of given input text or input files as .txt, .pdf, .doc, or .docx.

# 1. Before use extract
lib/tika-app-1.4.jar as file size is 28.3 MB hence splitted into 2 parts. Extract using win rar or similar application to merge the two parts.

# 2. Add environment variables
Add environment variable JAVA_TOOL_OPTIONS and set its value as -Dfile.encoding=UTF8 or check AutoTextSummarizer.bat if executing from command prompt 

# 3. Increase JVM memory
Set JVM parameters in IDE: -Xms1536m -Xmx3072m -mx1536m or check AutoTextSummarizer.bat if executing from command prompt

# 4. Main Class
summarizer.gui.Main

#Running application
I. Running from batch file
   Make sure point# 1 is completed, read README.TXT and then run AutoTextSummarizer.bat, the batch file will take care of points 2, 3 and 4.
   
II. Running from IDE
   Make sure points 1, 2, 3 are completed and then execute class mentioned in point# 4.
