import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        int i;
        int size = size();
        for (i = 0; i <= size; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int size = size();
        for (int i = 0; i <= size; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size];
                storage[size] = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume [] allResume = Arrays.copyOf(storage, size());
                return allResume;
    }

    int size() {
        int i;
        for ( i = 0 ; i < storage.length; i++) {
            if (storage[i] == null) {
            break;
            }
        }
        return i;
    }
}
