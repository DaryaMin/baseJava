import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, 0, size()+1, null);
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        try {
            for (Resume resume : storage) {
                if (resume.uuid.equals(uuid)) {
                    return resume;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Резюме с uuid = " + uuid + " не существует");
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
        for (int i = numberForDelete; i <= size(); i++) {
            storage[i] = storage[i+1];
            storage[i + 1] = null;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int size = 0;
        for (Resume resume : storage) {
            if (resume == null) {
                break;
            }
            size++;
        }
        return size;
    }
}
