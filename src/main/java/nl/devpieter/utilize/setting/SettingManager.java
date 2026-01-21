package nl.devpieter.utilize.setting;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import nl.devpieter.utilize.setting.interfaces.ISetting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class SettingManager {

    private static final SettingManager INSTANCE = new SettingManager();

    private final Gson gson = new Gson();
    private final Logger logger = LoggerFactory.getLogger("Utilize - SettingManager");

    private final Duration saveInterval = Duration.ofSeconds(15);
    private final HashMap<String, List<KeyedSetting<?>>> saveQueue = new HashMap<>();

    private long lastSaveTime = 0;
    private CompletableFuture<Boolean> saveFuture = null;

    private SettingManager() {
    }

    public static SettingManager getInstance() {
        return INSTANCE;
    }

    public static void shutdown() {
        INSTANCE.forceSaveQueue();
    }

    public void tick() {
        if (saveQueue.isEmpty()) return;

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSaveTime < saveInterval.toMillis()) return;

        if (saveFuture != null && !saveFuture.isDone()) return;

        saveFuture = CompletableFuture.supplyAsync(() -> {
            int files = saveQueue.size();
            int size = saveQueue.values().stream().mapToInt(List::size).sum();
            logger.info("Starting periodic 'setting batch save' for {} files and {} settings", files, size);

            for (String path : saveQueue.keySet()) {
                File file = new File(path);

                if (saveBatchToFile(file, saveQueue.get(path))) continue;
                logger.error("Failed to save settings batch to file: {}", file.getAbsolutePath());
            }

            lastSaveTime = currentTime;
            saveQueue.clear();
            return true;
        });
    }

    public boolean queueSave(@NotNull File file, @NotNull ISetting<?> setting) {
        String path = file.getAbsolutePath();
        KeyedSetting<?> keyedSetting = setting.asKeyedSetting();

        List<KeyedSetting<?>> settingsList = saveQueue.getOrDefault(path, new ArrayList<>());
        settingsList.removeIf(s -> s.key().equals(keyedSetting.key()));
        settingsList.add(keyedSetting);

        saveQueue.put(path, settingsList);
        return true;
    }

    public boolean queueSave(@NotNull File file, @NotNull List<ISetting<?>> settings) {
        boolean success = true;

        for (ISetting<?> setting : settings) {
            if (!queueSave(file, setting)) success = false;
        }

        return success;
    }

    public void forceSaveQueue() {
        if (saveQueue.isEmpty()) return;

        int files = saveQueue.size();
        int size = saveQueue.values().stream().mapToInt(List::size).sum();
        logger.info("Forced starting 'setting batch save' for {} files and {} settings", files, size);

        for (String path : saveQueue.keySet()) {
            File file = new File(path);

            if (saveBatchToFile(file, saveQueue.get(path))) continue;
            logger.error("Failed to force save settings batch to file: {}", file.getAbsolutePath());
        }

        saveQueue.clear();
    }

    public <T> boolean loadSetting(@NotNull File file, @NotNull ISetting<T> setting) {
        List<KeyedSetting<?>> batch = readBatchFromFile(file);
        return loadSettingFromBatch(setting, batch);
    }

    public boolean loadSettings(@NotNull File file, @NotNull List<ISetting<?>> settings) {
        List<KeyedSetting<?>> batch = readBatchFromFile(file);
        for (ISetting<?> setting : settings) loadSettingFromBatch(setting, batch);
        return true;
    }

    private <T> boolean loadSettingFromBatch(@NotNull ISetting<T> setting, @Nullable List<KeyedSetting<?>> batch) {
        if (batch == null || batch.isEmpty()) {
            setting.setValue(setting.getDefault());
            return true;
        }

        for (KeyedSetting<?> keyedSetting : batch) {
            if (!keyedSetting.key().equals(setting.getIdentifier())) continue;

            JsonElement jsonElement = gson.toJsonTree(keyedSetting.value());
            T value = gson.fromJson(jsonElement, setting.getType());

            setting.setValue(setting.shouldAllowNull() ? value : value != null ? value : setting.getDefault());
            return true;
        }

        logger.warn("Failed to load setting: {} from batch", setting.getIdentifier());
        return false;
    }

    private boolean saveBatchToFile(@NotNull File file, @NotNull List<KeyedSetting<?>> settings) {
        List<KeyedSetting<?>> currentSettings = readBatchFromFile(file);
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
            gson.toJson(currentSettings, writer);
            return true;
        } catch (IOException e) {
            logger.error("Failed to save settings batch to file: {}", file.getAbsolutePath(), e);
            return false;
        }
    }

    private @Nullable List<KeyedSetting<?>> readBatchFromFile(@NotNull File file) {
        try (Reader reader = new FileReader(file)) {
            return gson.fromJson(reader, new TypeToken<List<KeyedSetting<?>>>() {
            }.getType());
        } catch (IOException e) {
            logger.error("Failed to read settings batch from file: {}", file.getAbsolutePath(), e);
            return null;
        }
    }
}
