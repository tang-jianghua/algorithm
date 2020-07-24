package main.java.algorithm.zcy.class08;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司举办party，一个人被邀请后，他的直属下级不能被邀请，求最大快乐值
 * 公司结构是一个树形结构
 *
 * @auth tangjianghua
 * @date 2020/7/23
 */
public class Code09_MaxHappy {

    public static class Employee {
        /**
         * 自己的快乐值
         */
        public int happy;
        /**
         * 下级
         */
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    /**
     * 需要的信息：去的话快乐值是多少  不去的话快乐值是多少 自己去的快乐值加下级不去的快乐值，最后对比去和不去的快乐值即可
     */
    public static class Info {
        /**
         * 去的快乐值
         */
        int yes;

        /**
         * 不去的快乐值
         */
        int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    /**
     * 递归解法
     *
     * @param employee
     * @return
     */
    public static Info process2(Employee employee) {
        //先写basecase，没有下级的时候，返回自己的信息
        if (employee.nexts == null || employee.nexts.isEmpty()) {
            return new Info(employee.happy, 0);
        }

        int yes=employee.happy;
        int no=0;
        for (Employee employee1:employee.nexts) {
            //获取下级的快乐值
            Info info = process2(employee1);
            //自己的快乐值加下级不去的快乐值
            yes+=info.no;
            //自己不去 下级可去可不去。对比下级两种快乐值，添加到自己不去的快乐值上，得到自己不去的最大快乐值。
            no += Math.max(info.yes, info.no);
        }
        return new Info(yes,no);
    }

    /**
     * 求最大快乐值
     * @param boss
     * @return
     */
    public static int maxHappy2(Employee boss) {
        if (boss == null) {
            return 0;
        }
        Info all = process2(boss);
        return Math.max(all.yes, all.no);
    }

    public static int maxHappy1(Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    public static int process1(Employee cur, boolean up) {
        if (up) {
            int ans = 0;
            for (Employee next : cur.nexts) {
                ans += process1(next, false);
            }
            return ans;
        } else {
            int p1 = cur.happy;
            int p2 = 0;
            for (Employee next : cur.nexts) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy1(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
