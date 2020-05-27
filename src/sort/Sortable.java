package sort;

import java.util.Map;

public interface Sortable<K,V>{

    void sort(Map<K,V> map);
}
