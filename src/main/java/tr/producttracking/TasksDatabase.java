package tr.producttracking;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

public class TasksDatabase {
    private ArrayList<Task> tasks;
    private String file_path;

    public TasksDatabase(String file_name) {
        this.tasks = new ArrayList<>();
        createFile(file_name);
        load(); // Varolan veriyi yüklemek için
    }

    public void createFile(String file_name) {
        this.file_path = System.getProperty("user.home") + "\\AppData\\Roaming\\.countdown\\" + file_name;
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

    public String add(Task task) {
        tasks.add(task);
        save();
        return "Görev eklendi: " + task;
    }

    public void remove(int id) {
        tasks.removeIf(task -> task.getID() == id);
        save();
    }

    public void update(int id, boolean isCompleted) {
        for (Task task : tasks) {
            if (task.getID() == id) {
                //task.setCompleted(isCompleted);
                save();
                break;
            }
        }
    }

    public ArrayList<Task> load() {
        try (Reader reader = new FileReader(file_path)) {
            Gson gson = new Gson();
            tasks = gson.fromJson(reader, new TypeToken<ArrayList<Task>>(){}.getType());
            if (tasks == null) {
                tasks = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            tasks = new ArrayList<>();
        }
        return tasks;
    }

    public void save() {
        try (Writer writer = new FileWriter(file_path)) {
            Gson gson = new Gson();
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
