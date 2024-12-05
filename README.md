# JDownloader-RCE
Documenting how to exploit JDownloader instances

Tested on Docker container running Linux Alpine 64 bit, bit it should run on windows/linux either way.

# TL;DR

You can upload a .jar file to JDownloader and change its name to `JDownloader.jar` then restart the service, triggering whatever was in the .jar 

# Step 1: Verify environment settings 

1.1 When you get to the JDownloader interface, go to the top left and select 'Help -> About JDownloader'

1.2 Check the Java version and Installation Directory in this screenshot its JRE 1.8 and `/config`

# Step 2: Create payload 

2.1 Create 
