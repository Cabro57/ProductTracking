package tr.producttracking.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

public class SettingsManager {

    private static final String SETTINGS_FILE_PATH = System.getProperty("user.home") + "\\AppData\\Roaming\\.product_tracking\\settings.json";
    private static Map<String, Object> settings = new HashMap<>();

    public SettingsManager() {
        createFileIfNotExists();
        loadSettings();

        if ((getSetting("title") == null)) {
            setSetting("title", "Product Tracking Application");
        }

        saveSettings();
    }

    private void createFileIfNotExists() {
        File settingsFile = new File(SETTINGS_FILE_PATH);
        if (!settingsFile.exists()) {
            try (InputStream inputStream = getClass().getResourceAsStream("/settings.json")) {
                // Dosya ve dizinleri oluştur
                if (inputStream != null) {
                    Files.copy(inputStream, Paths.get(SETTINGS_FILE_PATH));
                    System.out.println("Settings dosyası oluşturuldu: " + SETTINGS_FILE_PATH);
                }
                else {
                    System.err.println(" Settings dosyası kaynaklarda bulunamadı.");
                }

            } catch (IOException e) {
                System.err.println("Dosya oluşturulurken bir hata oluştu: " + e.getMessage());
            }
        }
    }

    // Ayarları yükle
    private static void loadSettings() {
        try {
            File file = new File(SETTINGS_FILE_PATH);
            if (file.exists()) {
                String json = new String(Files.readAllBytes(Paths.get(SETTINGS_FILE_PATH)));
                Gson gson = new Gson();
                // JSON'u Map olarak dönüştür
                settings = gson.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
            } else {
                System.out.println("Settings dosyası bulunamadı, varsayılan ayarlar kullanılacak.");
            }
        } catch (IOException e) {
            System.out.println("Settings dosyası yüklenirken hata oluştu: " + e.getMessage());
        }
    }

    // Ayarları kaydet
    public static void saveSettings() {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(settings);
            Files.write(Paths.get(SETTINGS_FILE_PATH), json.getBytes());
        } catch (IOException e) {
            System.out.println("Settings dosyası kaydedilirken hata oluştu: " + e.getMessage());
        }
    }

    // Ayar ekle veya güncelle
    public static void setSetting(String key, Object value) {
        settings.put(key, value);
    }

    // Ayarı al
    public static Object getSetting(String key) {
        if (settings != null && settings.containsKey(key)) {
            return settings.get(key);
        } else {
            return null;
        }
    }

    public static String getSettingstoString(String key) {
        return settings.get(key).toString();
    }
    // Ayar sil
    public static void removeSetting(String key) {
        settings.remove(key);
    }

    // Ayarları yazdır
    public static void printSettings() {
        settings.forEach((key, value) -> {
            System.out.println(key + ": " + value.toString());
        });
    }
}
