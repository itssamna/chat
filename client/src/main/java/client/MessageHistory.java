package client;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MessageHistory {
    private String login;
    private FileOutputStream messageFile;
    private OutputStream stream;
    private DataOutputStream out;
    private DataOutputStream in;

    public MessageHistory(String login) {
         this.login = login;

        open();
    }

    private void open(){
        try {
            this.messageFile = new FileOutputStream("history/history_" + login + ".txt", true);
            stream = new DataOutputStream(messageFile);
            this.out = new DataOutputStream(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void logMessage(String msg){
        try {
            out.writeBytes(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getMessageHistory(){
        List<String> messages = null;
        try {
            messages = Files.readAllLines(Paths.get("history/history_" + login + ".txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public void close(){
        try {
            messageFile.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
