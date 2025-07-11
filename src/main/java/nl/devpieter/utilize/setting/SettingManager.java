package nl.devpieter.utilize.setting;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import nl.devpieter.utilize.Utilize;
import nl.devpieter.utilize.setting.interfaces.ISetting;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SettingManager {

    private static SettingManager INSTANCE;

    private final Gson gson = new Gson();

    private final Duration saveInterval = Duration.ofSeconds(15);
    private final HashMap<String, List<KeyedSetting<?>>> saveQueue = new HashMap<>();

    private long lastSaveTime = 0;
    private CompletableFuture<Boolean> saveFuture = null;

    private SettingManager() {
    }

    public static SettingManager getInstance() {
        if (INSTANCE == null) INSTANCE = new SettingManager();
        return INSTANCE;
    }

    public void tick() {
        if (this.saveQueue.isEmpty()) return;

        long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastSaveTime < this.saveInterval.toMillis()) return;

        if (this.saveFuture != null && !this.saveFuture.isDone()) return;

        this.saveFuture = CompletableFuture.supplyAsync(() -> {
            int files = this.saveQueue.size();
            int size = this.saveQueue.values().stream().mapToInt(List::size).sum();
            Utilize.LOGGER.info("Starting periodic 'setting batch save' for {} files and {} settings", files, size);

            for (String path : this.saveQueue.keySet()) {
                File file = new File(path);

                if (this.saveBatchToFile(file, this.saveQueue.get(path))) continue;
                Utilize.LOGGER.error("Failed to save settings batch to file: {}", file.getAbsolutePath());
            }

            this.lastSaveTime = currentTime;
            this.saveQueue.clear();
            return true;
        });
    }

    public boolean queueSave(File file, ISetting<?> setting) {
        String path = file.getAbsolutePath();
        KeyedSetting<?> keyedSetting = setting.asKeyedSetting();

        List<KeyedSetting<?>> settingsList = this.saveQueue.getOrDefault(path, new ArrayList<>());
        settingsList.removeIf(s -> s.key().equals(keyedSetting.key()));
        settingsList.add(keyedSetting);

        this.saveQueue.put(path, settingsList);
        return true;
    }

    public boolean queueSave(File file, List<ISetting<?>> settings) {
        boolean success = true;

        for (ISetting<?> setting : settings) {
            if (!queueSave(file, setting)) success = false;
        }

        return success;
    }

    public void forceSaveQueue() {
        if (this.saveQueue.isEmpty()) return;

        int files = this.saveQueue.size();
        int size = this.saveQueue.values().stream().mapToInt(List::size).sum();
        Utilize.LOGGER.info("Forced starting 'setting batch save' for {} files and {} settings", files, size);

        for (String path : this.saveQueue.keySet()) {
            File file = new File(path);

            if (this.saveBatchToFile(file, this.saveQueue.get(path))) continue;
            Utilize.LOGGER.error("Failed to force save settings batch to file: {}", file.getAbsolutePath());
        }

        this.saveQueue.clear();
    }

    public <T> boolean loadSetting(File file, ISetting<T> setting) {
        List<KeyedSetting<?>> batch = this.readBatchFromFile(file);
        return loadSettingFromBatch(setting, batch);
    }

    public boolean loadSettings(File file, List<ISetting<?>> settings) {
        List<KeyedSetting<?>> batch = this.readBatchFromFile(file);
        for (ISetting<?> setting : settings) loadSettingFromBatch(setting, batch);

        return true;
    }

    private <T> boolean loadSettingFromBatch(ISetting<T> setting, List<KeyedSetting<?>> batch) {
        if (batch == null || batch.isEmpty()) {
            setting.setValue(setting.getDefault());
            return true;
        }

        for (KeyedSetting<?> keyedSetting : batch) {
            if (!keyedSetting.key().equals(setting.getIdentifier())) continue;

            JsonElement jsonElement = gson.toJsonTree(keyedSetting.value());
            T value = this.gson.fromJson(jsonElement, setting.getType());

            setting.setValue(setting.shouldAllowNull() ? value : value != null ? value : setting.getDefault());
            return true;
        }

        Utilize.LOGGER.warn("Failed to load setting: {} from batch", setting.getIdentifier());
        return false;
    }

    private boolean saveBatchToFile(File file, List<KeyedSetting<?>> settings) {
        List<KeyedSetting<?>> currentSettings = this.readBatchFromFile(file);
        if (currentSettings == null) currentSettings = new ArrayList<>();

        HashMap<String, KeyedSetting<?>> settingsMap = new HashMap<>();
        for (KeyedSetting<?> setting : currentSettings) {
            settingsMap.put(setting.key(), setting);
        }

        for (KeyedSetting<?> setting : settings) {
            settingsMap.put(setting.key(), setting);
        }

        currentSettings = new ArrayList<>(settingsMap.values());

        try (FileWriter writer = new FileWriter(file)) {
            this.gson.toJson(currentSettings, writer);
            return true;
        } catch (IOException e) {
            Utilize.LOGGER.error("Failed to save settings batch to file: {}", file.getAbsolutePath(), e);
            return false;
        }
    }

    private @Nullable List<KeyedSetting<?>> readBatchFromFile(File file) {
        try (Reader reader = new FileReader(file)) {
            return this.gson.fromJson(reader, new TypeToken<List<KeyedSetting<?>>>() {
            }.getType());
        } catch (IOException e) {
            Utilize.LOGGER.error("Failed to read settings batch from file: {}", file.getAbsolutePath(), e);
            return null;
        }
    }
}
