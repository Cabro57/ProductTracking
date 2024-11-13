package tr.producttracking;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TasksDatabase extends ArrayList<Task> {
    private String file_path;

    private int ID;

    public TasksDatabase(String file_name) {
        this.ID = 0;
        createFile(file_name);
        load();
    }

    // Dosya oluşturma işlemi
    public void createFile(String file_name) {
        this.file_path = System.getProperty("user.home") + "\\AppData\\Roaming\\.product_tracking\\" + file_name;
        File file = new File(file_path);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("Dosya oluşturuldu!!!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getSafeID() {
        while (this.stream().anyMatch(c -> c.getID() == ID)) {
            ID++;
        }
        return ID;
    }

    public void update(Task newTask) {
        for (Task task : this) {
            if (task.getID() == newTask.getID()) {

                task.setFull_name(newTask.getFull_name());
                task.setPhone_no(newTask.getPhone_no());
                task.setEmail(newTask.getEmail());
                task.setPayment_date(newTask.getPayment_date());
                task.setProduct_status(newTask.getProduct_status());
                task.setComment(newTask.getComment());

                save();
                break;
            }
        }
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {
                    Task task = new Task(Integer.parseInt(data[0]), data[1]);
                    task.setPhone_no(data[2]);
                    task.setEmail(data[3]);
                    task.setPayment_date(data[4]);
                    task.setProduct_status(data[5]);
                    task.setComment(data[6]);
                    add(task);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_path))) {
            for (Task task : this) {
                writer.write(task.toCSV());
                writer.newLine(); // Satır sonu ekliyoruz
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ID'ye göre görev alma
    public Task getTask(int id) {
        for (Task task : this) {
            if (task.getID() == id) {
                return task;
            }
        }
        return null;
    }

    public List<Task> getTasks() {
        return TasksDatabase.this;
    }
}
