package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final File PROPS = new File(getRootDir(), "config\\resumes.properties");
//    private static final String PROPS = "//resumes.properties";
    private static final Config INSTANCE = new Config();
    private final Properties props = new Properties();
    private final File storageDir;

    private final Storage base;
    public static Config get() {
        return INSTANCE;
    }

    private static File getRootDir() {
        String dir = System.getProperty("homeDir");
        File rootDir = new File(dir == null ? "." : dir);
        if (!rootDir.isDirectory()) {
            throw new IllegalStateException(rootDir + " is not directory");
        }
        return rootDir;
    }
    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
//        try (InputStream is = Config.class.getResourceAsStream(PROPS)) {
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            base = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
//            throw new IllegalStateException("Invalid config file " + PROPS);
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Properties getProps() {
        return props;
    }

    public Storage getBase() {
        return base;
    }

}
