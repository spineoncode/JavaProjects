import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

    public HashMap<String, String> readFromFile() {
        try {
            // This reads binary data from a file
            FileInputStream fileInputStream = new FileInputStream(
                    "C:\\Users\\Ayush Raj\\Documents\\CODING\\Projects\\Java Projects\\PasswordManager\\data.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            HashMap<String, String> hashMapRead = (HashMap<String, String>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return hashMapRead;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    public void writeToFile(HashMap<String, String> fileToWrite) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(
                    "C:\\Users\\Ayush Raj\\Documents\\CODING\\Projects\\Java Projects\\PasswordManager\\data.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(fileToWrite);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addPassword(String website, String password) {
        HashMap<String, String> hashMap = readFromFile();
        hashMap.put(website, password);
        writeToFile(hashMap);
    }

    public void removePassword(String website) {
        HashMap<String, String> hashMap = readFromFile();
        hashMap.remove(website);
        writeToFile(hashMap);
    }

    static void reAsker() {
        Main appObj = new Main();
        Scanner inputs = new Scanner(System.in);
        System.out.println("What Do You Want To Do? (show, add, remove, reset, exit)");
        String what_to_do = inputs.nextLine();
        if ("show".equals(what_to_do)) {
            HashMap<String, String> readFile = appObj.readFromFile();
            System.out.println(readFile);
            reAsker();
        } else if ("add".equals(what_to_do)) {
            System.out.println("Enter the website name: ");
            String website = inputs.nextLine();
            System.out.println("Enter the password: ");
            String password = inputs.nextLine();
            appObj.addPassword(website, password);
            reAsker();

        } else if ("reset".equals(what_to_do)) {
            HashMap<String, String> hashMap = new HashMap<String, String>();
            appObj.writeToFile(hashMap);
            System.out.println("Resetted the file.");
            reAsker();

        } else if ("remove".equals(what_to_do)) {
            System.out.println("Enter the website name: ");
            String website = inputs.nextLine();
            appObj.removePassword(website);
            reAsker();

        } else {
            System.out.println("Exiting...");
        }
        inputs.close();
    }

    public static void main(String[] args) throws IOException {
        reAsker();
    }
}
