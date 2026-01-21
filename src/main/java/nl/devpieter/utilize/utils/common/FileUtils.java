package nl.devpieter.utilize.utils.common;

import java.io.File;

public final class FileUtils {

    private FileUtils() {
    }

    public static boolean doesFileExist(File file) {
        try {
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean doesDirectoryExist(File directory) {
        try {
            return directory.exists() && directory.isDirectory();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean tryCreateParentDirectories(File file) {
        try {
            File parentDir = file.getParentFile();
            if (parentDir.exists()) return true;

            return parentDir.mkdirs();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean tryCreateFile(File file) {
        return tryCreateFile(file, true);
    }

    public static boolean tryCreateFile(File file, boolean createParentDirectories) {
        try {
            if (file.exists()) return true;
            if (createParentDirectories && !tryCreateParentDirectories(file)) return false;

            return file.createNewFile();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean tryDeleteFile(File file) {
        try {
            if (!file.exists()) return true;

            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }
}
