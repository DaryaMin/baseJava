import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size() + 1, null);
        size = 0;
    }

    void save(Resume r) {
        storage[size()] = r;
        size++;
    }

    Resume get(String uuid) {
        for (Resume resume : storage) {
            if (resume != null) {
                if (resume.toString().equals(uuid)) {
                    return resume;
                }
            }
        }
            return null;
    }

    void delete(String uuid) {
        int numberForDelete = 0;

        for (Resume resume : storage) {
            if (resume.toString() == uuid) {
                resume = null;
                break;
            }
            numberForDelete++;

        }
        for (int i = numberForDelete; i <= size; i++) {
            storage[i] = storage[i + 1];
            storage[i + 1] = null;
        }
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
