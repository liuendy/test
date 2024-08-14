package com.example.intensity;

import java.util.Map;
import java.util.TreeMap;

public class IntensitySegments {
    //差分数组是一种用于处理区间更新的技巧，通过记录区间的起点增加值和区间的终点减少值，能够高效地对一个范围内的值进行批量更新。
    // 使用TreeMap来存储区间端点及其对应的强度变化
    // TreeMap会根据键值（区间端点）自动排序，方便进行区间管理
    private TreeMap<Integer, Integer> segments;

    // 构造函数，初始化TreeMap
    public IntensitySegments() {
        this.segments = new TreeMap<>();
    }

    // 增加指定区间[from, to)的强度值
    public void add(int from, int to, int amount) {
        if (from >= to) {
            return; // 如果起始点大于或等于终点，不进行任何操作
        }
        // 在from点增加amount的强度
        update(from, amount);
        // 在to点减少amount的强度（抵消掉区间结束后的强度）
        update(to, -amount);
    }

    // 更新指定点的强度变化
    // 如果更新后的强度为0，则删除该点
    private void update(int point, int amount) {
        // 获取point点当前的强度值并加上变化量
        int updatedValue = segments.getOrDefault(point, 0) + amount;
        if (updatedValue == 0) {
            // 如果更新后的强度为0，移除该点
            segments.remove(point);
        } else {
            // 否则更新该点的强度值
            segments.put(point, updatedValue);
        }
    }

    // 获取指定点的强度值
    // 通过遍历TreeMap并累加从最小到指定点的所有变化值来计算
    private int getIntensity(int point) {
        int intensity = 0;
        // 遍历segments中的所有条目
        for (Map.Entry<Integer, Integer> entry : segments.entrySet()) {
            // 一旦超过指定点，停止累加
            if (entry.getKey() > point) break;
            // 累加当前条目的强度变化值
            intensity += entry.getValue();
        }
        // 返回计算出的强度值
        return intensity;
    }

    // 生成区间和强度的字符串表示
    //扫描线算法，原理是通过“扫描”一条线，并在这条线上记录所有的事件（如区间开始、区间结束），然后根据事件的顺序进行处理
    //通过记录区间的起点和终点的变化，然后在查询或输出时，根据这些变化计算出整个区间的最终状态。
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("["); // 开始字符串的构建
        int currentIntensity = 0;

        // 遍历所有的区间端点
        for (Map.Entry<Integer, Integer> entry : segments.entrySet()) {
            int point = entry.getKey(); // 当前端点位置
            int value = entry.getValue(); // 当前端点的强度变化值
            currentIntensity += value; // 更新当前的强度
            // 构建字符串的一部分，表示某个端点及其对应的强度值
            result.append("[").append(point).append(",").append(currentIntensity).append("],");
        }

        if (result.length() > 1) {
            // 删除最后一个多余的逗号
            result.deleteCharAt(result.length() - 1);
        }
        result.append("]"); // 结束字符串的构建

        return result.toString(); // 返回字符串表示
    }

    // 设置指定区间[from, to)的强度值为amount
    public void set(int from, int to, int amount) {
        if (from >= to) {
            return; // 如果起始点大于或等于终点，不进行任何操作
        }

        // 获取 `from` 和 `to` 点的当前强度值
        int currentFromIntensity = getIntensity(from);
        int currentToIntensity = getIntensity(to);

        // 删除区间 [from, to) 内的所有段点
        segments.subMap(from, false, to, false).clear();

        // 将 `from` 点的强度设置为目标值
        update(from, amount - currentFromIntensity);

        // 调整 `to` 点以恢复该点之前的强度
        update(to, currentToIntensity - getIntensity(to));

        // 清理：如果 `from` 点或 `to` 点的强度回到0，则删除这些点
        if (segments.getOrDefault(from, 0) == 0) {
            segments.remove(from);
        }
        if (segments.getOrDefault(to, 0) == 0) {
            segments.remove(to);
        }

        // 额外清理：确保没有冗余的段点残留
        if (segments.containsKey(to) && segments.get(to) == 0) {
            segments.remove(to);
        }
    }

    public TreeMap<Integer, Integer> getSegments() {
        return segments;
    }

    //开发的时候调试用
    public static void main(String[] args) {
        IntensitySegments segments = new IntensitySegments();
        System.out.println(segments.toString()); // Should be "[]"

        segments.add(10, 30, 1);
        System.out.println(segments.toString()); // Should be "[[10,1],[30,0]]"
        System.out.println(segments.getSegments());

        segments.add(20, 40, 1);
        System.out.println(segments.toString()); // Should be "[[10,1],[20,2],[30,1],[40,0]]"
        System.out.println(segments.getSegments());

        segments.add(10, 40, -1);
        System.out.println(segments.toString()); // Should be "[[20,1],[30,0]]"

        segments.add(10, 40, -1);
        System.out.println(segments.toString()); // Should be "[[10,-1],[20,0],[30,-1],[40,0]]"

        // Second example sequence
        segments = new IntensitySegments();
        System.out.println(segments.toString()); // Should be "[]"

        segments.add(10, 30, 1);
        System.out.println(segments.toString()); // Should be "[[10,1],[30,0]]"

        segments.add(20, 40, 1);
        System.out.println(segments.toString()); // Should be "[[10,1],[20,2],[30,1],[40,0]]"

        segments.add(10, 40, -1);
        System.out.println(segments.toString()); // Should be "[[20,1],[30,0]]"

        segments.add(10, 40, -1);
        System.out.println(segments.toString()); // Should be "[[10,-1],[20,0],[30,-1],[40,0]]"

    }
}
