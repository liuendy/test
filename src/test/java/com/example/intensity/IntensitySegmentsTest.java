package com.example.intensity;

/**
 * Created by liuhongming on 2024/8/13.
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntensitySegmentsTest {

    @Test
    public void testAddCase1() {
        IntensitySegments segments = new IntensitySegments();

        // Initial state
        assertEquals("[]", segments.toString());

        // Add [10, 30) with intensity 1
        segments.add(10, 30, 1);
        assertEquals("[[10,1],[30,0]]", segments.toString());

        // Add [20, 40) with intensity 1
        segments.add(20, 40, 1);
        assertEquals("[[10,1],[20,2],[30,1],[40,0]]", segments.toString());

        // Add [10, 40) with intensity -2
        segments.add(10, 40, -2);
        assertEquals("[[10,-1],[20,0],[30,-1],[40,0]]", segments.toString());

    }

    @Test
    public void testAddCase2() {
        IntensitySegments segments = new IntensitySegments();

        // Initial state
        assertEquals("[]", segments.toString());

        // Add [10, 30) with intensity 1
        segments.add(10, 30, 1);
        assertEquals("[[10,1],[30,0]]", segments.toString());

        // Add [20, 40) with intensity 1
        segments.add(20, 40, 1);
        assertEquals("[[10,1],[20,2],[30,1],[40,0]]", segments.toString());

        // Add [10, 40) with intensity -1
        segments.add(10, 40, -1);
        assertEquals("[[20,1],[30,0]]", segments.toString());

        // Add [10, 40) with intensity -1 again
        segments.add(10, 40, -1);
        assertEquals("[[10,-1],[20,0],[30,-1],[40,0]]", segments.toString());
    }


    @Test
    public void testSetCase1() {
        IntensitySegments segments = new IntensitySegments();

        // Initial state
        assertEquals("[]", segments.toString());

        // Set [10, 30) with intensity 1
        segments.set(10, 30, 1);
        assertEquals("[[10,1],[30,0]]", segments.toString());

        // Set [20, 40) with intensity 2
        segments.set(20, 40, 2);
        assertEquals("[[10,1],[20,2],[40,0]]", segments.toString());

        // Set [10, 40) with intensity 3
        segments.set(10, 40, 3);
        System.out.println(segments.getSegments());
        assertEquals("[[10,3],[40,0]]", segments.toString());

        // Set [15, 35) with intensity 0
        segments.set(15, 35, 0);
        assertEquals("[[10,3],[15,0],[35,3],[40,0]]", segments.toString());

        // Set [30, 50) with intensity 1
        segments.set(30, 50, 1);
        assertEquals("[[10,3],[15,0],[30,1],[50,0]]", segments.toString());
    }

    @Test
    public void testSetCase2() {
        IntensitySegments segments = new IntensitySegments();

        // Initial state
        assertEquals("[]", segments.toString());

        // Set [0, 10) with intensity 1
        segments.set(0, 10, 1);
        assertEquals("[[0,1],[10,0]]", segments.toString());
        System.out.println(segments.getSegments());

        // Set [10, 20) with intensity 1
        segments.set(10, 20, 1);
        assertEquals("[[0,1],[20,0]]", segments.toString());
        System.out.println(segments.getSegments());

        // Set [5, 15) with intensity 2
        segments.set(5, 15, 2);
        System.out.println(segments.getSegments());
        assertEquals("[[0,1],[5,2],[15,1],[20,0]]", segments.toString());


        // Set [15, 25) with intensity 0
        segments.set(15, 25, 0);
        assertEquals("[[0,1],[5,2],[15,0]]", segments.toString());
    }

    @Test
    public void testSetAndAddCase() {
        IntensitySegments segments = new IntensitySegments();

        // 初始状态
        assertEquals("[]", segments.toString());

        // 案例1: 添加区间 [10, 30)，强度为 1
        segments.add(10, 30, 1);
        assertEquals("[[10,1],[30,0]]", segments.toString());

        // 案例2: 设置区间 [20, 40)，强度为 2（部分与 [10, 30) 重叠）
        segments.set(20, 40, 2);
        assertEquals("[[10,1],[20,2],[40,0]]", segments.toString());

        // 案例3: 添加区间 [15, 35)，强度为 -1（部分与 [10, 30) 和 [20, 40) 重叠）
        segments.add(15, 35, -1);
        assertEquals("[[10,1],[15,0],[20,1],[35,2],[40,0]]", segments.toString());

        // 案例4: 设置区间 [0, 10)，强度为 3（与现有区间无重叠）
        segments.set(0, 10, 3);
        assertEquals("[[0,3],[10,1],[15,0],[20,1],[35,2],[40,0]]", segments.toString());

        // 案例5: 添加区间 [5, 25)，强度为 1（部分与多个区间重叠）
        segments.add(5, 25, 1);
        assertEquals("[[0,3],[5,4],[10,2],[15,1],[20,2],[25,1],[35,2],[40,0]]", segments.toString());

        // 案例6: 设置区间 [15, 45)，强度为 0（移除一个大范围内的强度）
        segments.set(15, 45, 0);
        assertEquals("[[0,3],[5,4],[10,2],[15,0]]", segments.toString());

        // 案例7: 添加区间 [30, 50)，强度为 2（与现有区间无重叠）
        segments.add(30, 50, 2);
        assertEquals("[[0,3],[5,4],[10,2],[15,0],[30,2],[50,0]]", segments.toString());

        // 案例8: 设置区间 [25, 35)，强度为 1（部分重叠 [30, 50) 区间）
        segments.set(25, 35, 1);
        assertEquals("[[0,3],[5,4],[10,2],[15,0],[25,1],[35,2],[50,0]]", segments.toString());

        // 案例9: 设置区间 [0, 55)，强度为 1（完全覆盖并修改所有现有区间）
        segments.set(0, 55, 1);
        assertEquals("[[0,1],[55,0]]", segments.toString());
    }

}
