package nowcoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 2671
 *
 * @author luotao
 * @date 2023-5-10  21:38
 */
public class FrequencyTracker {
    /**
     * 数字 - 数字次数
     */
    private Map<Integer, Integer> numberCountMap = new HashMap<>();

    /**
     * 频率 - 频率次数
     */
    private Map<Integer, Integer> frequencyCountMap = new HashMap<>();

    public FrequencyTracker() {
    }

    public void add(int number) {
        Integer count = numberCountMap.getOrDefault(number, 0);
        numberCountMap.put(number, count + 1);
        frequencyCountMap.put(count, frequencyCountMap.getOrDefault(count, 0) - 1);
        frequencyCountMap.put(count + 1, frequencyCountMap.getOrDefault(count + 1, 0) + 1);
    }

    public void deleteOne(int number) {
        if (numberCountMap.containsKey(number)) {
            Integer count = numberCountMap.get(number);
            if (count > 0) {
                numberCountMap.put(number, count - 1);
            } else {
                numberCountMap.remove(number);
            }
            frequencyCountMap.put(count, frequencyCountMap.getOrDefault(count, 0) - 1);
            frequencyCountMap.put(count - 1, frequencyCountMap.getOrDefault(count - 1, 0) + 1);
        }
    }

    public boolean hasFrequency(int frequency) {
        Integer count = frequencyCountMap.get(frequency);
        return count != null && count != 0;
    }

    public static void main(String[] args) {
        FrequencyTracker tracker = new FrequencyTracker();
        tracker.add(1);
        tracker.add(1);
        tracker.add(6);
        tracker.add(7);
        tracker.deleteOne(10);
        System.out.println(tracker.hasFrequency(1));
        System.out.println(tracker.hasFrequency(1));
        tracker.deleteOne(6);
        tracker.add(3);
        tracker.deleteOne(6);
        tracker.add(10);
        tracker.deleteOne(1);
        System.out.println(tracker.hasFrequency(2));
        tracker.deleteOne(2);
        tracker.deleteOne(6);
        tracker.deleteOne(9);
        tracker.deleteOne(4);
        tracker.deleteOne(5);
        tracker.add(3);
        System.out.println(tracker.hasFrequency(1));
        System.out.println(tracker.hasFrequency(1));
    }
}
