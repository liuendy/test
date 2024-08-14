IntensitySegments - 时间和空间复杂度分析
本项目包含 IntensitySegments 类，它实现了一种使用差分数组和区间树来管理和操作强度区间的数据结构。下面是该类中每个方法的时间和空间复杂度分析。

方法复杂度分析
1. add(int from, int to, int amount) 方法
时间复杂度:

update(from, amount): 更新 from 点的强度。update 方法的时间复杂度取决于 TreeMap 的操作，TreeMap 的 put 和 get 操作的平均时间复杂度是 O(log n)，其中 n 是 segments 中的键值对数量。
update(to, -amount): 同样地，更新 to 点的强度的时间复杂度为 O(log n)。
总时间复杂度: O(log n)
空间复杂度:

update 方法可能会在 TreeMap 中插入两个新条目（from 和 to 点），所以在最坏情况下，空间复杂度是 O(1)（每次 add 操作只会影响 TreeMap 中的两个点）。
总空间复杂度: O(1)


2. update(int point, int amount) 方法
时间复杂度:

这个方法调用了 TreeMap 的 get 和 put 方法。每个操作的时间复杂度都是 O(log n)，其中 n 是 segments 中的键值对数量。
总时间复杂度: O(log n)
空间复杂度:

如果 amount 加上当前的值变成 0，该点会从 TreeMap 中移除；否则，它会更新或插入一个新的值。因此，空间复杂度是 O(1)。
总空间复杂度: O(1)


3. getIntensity(int point) 方法
时间复杂度:

这个方法通过遍历 TreeMap 中的所有条目来计算某个点的强度。遍历的时间复杂度是 O(n)，其中 n 是 TreeMap 中的键值对数量。
总时间复杂度: O(n)
空间复杂度:

该方法不需要额外的空间，除了用于存储强度的变量。空间复杂度为 O(1)。
总空间复杂度: O(1)


4. toString() 方法
时间复杂度:

toString 方法遍历 TreeMap 中的所有键值对并构建一个字符串表示。遍历的时间复杂度是 O(n)，其中 n 是 TreeMap 中的键值对数量。
总时间复杂度: O(n)
空间复杂度:

该方法使用了 StringBuilder 来构建字符串，生成的字符串的长度与 TreeMap 中的键值对数量有关。因此，空间复杂度也是 O(n)。
总空间复杂度: O(n)


5. set(int from, int to, int amount) 方法
时间复杂度:

getIntensity(from) 和 getIntensity(to) 的时间复杂度都是 O(n)。
清理 [from, to) 区间内的段点的操作时间复杂度是 O(m log n)，其中 m 是被删除的条目数量（最多 n 个），n 是 TreeMap 中的总键值对数量。
两次 update 操作的时间复杂度为 O(log n)。
总时间复杂度: O(n + m log n)，其中 m 最多为 n。
空间复杂度:

在最坏情况下，set 操作可能会更新或插入两个新条目。因此，空间复杂度是 O(1)。
总空间复杂度: O(1)


总结

add 方法: 时间复杂度 O(log n)，空间复杂度 O(1)

update 方法: 时间复杂度 O(log n)，空间复杂度 O(1)

getIntensity 方法: 时间复杂度 O(n)，空间复杂度 O(1)

toString 方法: 时间复杂度 O(n)，空间复杂度 O(n)

set 方法: 时间复杂度 O(n + m log n)，空间复杂度 O(1)



Methods Complexity Analysis
1. add(int from, int to, int amount) Method
Time Complexity:

update(from, amount): Updates the intensity at the from point. The update method's time complexity depends on the TreeMap operations, with put and get operations averaging O(log n), where n is the number of key-value pairs in the segments.
update(to, -amount): Similarly, updating the to point has a time complexity of O(log n).
Total Time Complexity: O(log n)
Space Complexity:

The update method might insert two new entries into the TreeMap (from and to points), so in the worst case, the space complexity is O(1) (each add operation only affects two points in the TreeMap).
Total Space Complexity: O(1)
2. update(int point, int amount) Method
Time Complexity:

This method involves TreeMap's get and put operations, each with a time complexity of O(log n), where n is the number of key-value pairs in the segments.
Total Time Complexity: O(log n)
Space Complexity:

If the amount plus the current value becomes zero, the point is removed from the TreeMap; otherwise, it is updated or inserted as a new value. Therefore, the space complexity is O(1).
Total Space Complexity: O(1)
3. getIntensity(int point) Method
Time Complexity:

This method calculates the intensity at a specific point by traversing all entries in the TreeMap. The traversal has a time complexity of O(n), where n is the number of key-value pairs in the segments.
Total Time Complexity: O(n)
Space Complexity:

The method does not require additional space, other than a variable to store the intensity. The space complexity is O(1).
Total Space Complexity: O(1)
4. toString() Method
Time Complexity:

The toString method traverses all key-value pairs in the TreeMap to construct a string representation. The traversal has a time complexity of O(n), where n is the number of key-value pairs in the segments.
Total Time Complexity: O(n)
Space Complexity:

This method uses a StringBuilder to construct the string, and the length of the string is proportional to the number of key-value pairs in the TreeMap. Thus, the space complexity is O(n).
Total Space Complexity: O(n)
5. set(int from, int to, int amount) Method
Time Complexity:

getIntensity(from) and getIntensity(to) both have a time complexity of O(n).
Clearing the range [from, to) in the TreeMap has a time complexity of O(m log n), where m is the number of entries being removed (up to n entries), and n is the total number of key-value pairs in the segments.
The two update operations each have a time complexity of O(log n).
Total Time Complexity: O(n + m log n), where m can be up to n.
Space Complexity:

In the worst case, the set operation might insert or update two new entries in the TreeMap. Therefore, the space complexity is O(1).
Total Space Complexity: O(1)
Summary
add Method: Time Complexity O(log n), Space Complexity O(1)
update Method: Time Complexity O(log n), Space Complexity O(1)
getIntensity Method: Time Complexity O(n), Space Complexity O(1)
toString Method: Time Complexity O(n), Space Complexity O(n)
set Method: Time Complexity O(n + m log n), Space Complexity O(1)




