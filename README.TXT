Steps to run application:

### Copy files from cd to hard disk ###

### Checking java version ###
-  Run check_java.bat file it will show your java version. This software requires java 1.7 or higher version to run.
- If you get "'java' is not recognized as an internal or external command, operable program or batch file." this means java is not installed.
- If you have installed java and still see above error then do following
	- Right click My computer >>  Properties >>  Advanced system settings >> Environment Variables
	- Search for 'Path' under variable >> edit >>  in variable value insert ";<path of java installation>"
	- If 'path' is not there then add new variable as 'Path' and value  "<path of java installation>"

### Run AutoTextSummarizer.bat ###
- It will ask to enter performance of your machine [high/mid/low]
- Type high if you have high end machine (processor having 4 cores or more).
- Type mid if you have mid end machine (processor having 2 cores).
- Type low if you have low end machine (processor having single core).
- If you are not sure then use in all three modes and find best one for your machine