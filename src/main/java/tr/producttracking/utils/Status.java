package tr.producttracking.utils;

import java.io.*;
import java.util.ArrayList;

public class Status {
    private static final String file_path = System.getProperty("user.home") + "\\AppData\\Roaming\\.product_tracking\\Statuses.csv";
    private static ArrayList<String> statuses;
    private String status;

    public Status(String status) {
        addStatus(status);
        save();

    }

    public static void createFile(String file_name) {
        File file = new File(file_path + file_name);

        // Dosya mevcut değilse oluştur
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                //System.out.println("Dosya oluşturuldu!!!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    public static void createFile() {
        File file = new File(file_path);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                //System.out.println("Dosya oluşturuldu!!!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void addStatus(String status) {

        statuses.add(status);
    }


    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file_path));
            for (String status : statuses) {
                writer.write(status);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<String> load() {
        statuses = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_path));
            String line;
            while ((line = reader.readLine()) != null) {
                statuses.add(line);
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

        return statuses;
    }
}

