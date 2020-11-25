package main.java.algorithm.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author tangjianghua
 * @date 2020/11/22
 */
public class T3 {

    /**
     * 每个用户要传输的数据量
     */
    private static Double d;

    /**
     * 最大阶
     */
    private static Integer maxJie;

    /**
     * 用户
     */
    static class User {

        /**
         * 用户分配的信道
         */
        private Set<XinDao> xinDaos = new HashSet<>();

        public void addXinDao(XinDao xinDao) {
            xinDaos.add(xinDao);
        }

        public Double capacity(){
            return xinDaos.stream()
                    .reduce(0D, (aLong, xinDao) -> aLong + xinDao.capacity(), (aDouble, aDouble2) -> 0D);
        }

        public boolean enableTransport() {
            return xinDaos.stream()
                    .reduce(0D, (aLong, xinDao) -> aLong + xinDao.capacity(), (aDouble, aDouble2) -> 0D)
                    >= d;
        }

    }

    /**
     * 信道
     */
    static class XinDao {

        /**
         * 阶
         */
        private Integer jie;

        /**
         * 已分配用户
         */
        private Object owner;


        public void setOwner(Object owner) {
            this.owner = owner;
        }

        public XinDao(Integer jie) {
            this.jie = jie;
        }

        public Object getOwner() {
            return owner;
        }

        /**
         * 信道容量
         *
         * @return
         */
        public double capacity() {
            return Math.pow(2, jie);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        //最大阶数
        String s = bufferedReader.readLine();
        maxJie = Integer.parseInt(s);


        //每种信道的数量Ni，按照阶的值从小到大排列
        //代表从0~maxJie 每种阶的信道数量
        String n = bufferedReader.readLine();
        String[] nArr = n.split(" ");
        if (nArr.length != maxJie + 1) {
            return;
        }
        //记录每种阶的信道数量

        List<XinDao> list = new ArrayList<>();
        for (int i = 0; i < nArr.length; i++) {
            List<XinDao> generate = generate(i, Integer.parseInt(nArr[i]));
            list.addAll(generate);
        }

        //每个用户需要传输的数据量
        String dStr = bufferedReader.readLine();
        d = Double.valueOf(dStr);


        //输出最多供多少用户传输数据
        System.out.println();
    }

    /**
     * 生成信道集合
     *
     * @param jie
     * @param num
     * @return
     */
    private static List<XinDao> generate(Integer jie, Integer num) {
        List<XinDao> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(new XinDao(jie));
        }
        return list;
    }


    /**
     * 递归获取可用的用户数量
     *
     * @param xinDaos
     * @param users
     * @return
     */
    private static Integer process(List<XinDao> xinDaos,List<User> users,Integer enableUsers) {
        XinDao xinDao = xinDaos.get(0);
        double capacity = xinDao.capacity();
        if(xinDaos.size()==1){
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                Double capacity1 = user.capacity();
                if(capacity1<d){
                    if(capacity1+capacity>=d){
                        xinDao.setOwner(user);
                        user.addXinDao(xinDao);
                        return enableUsers+1;
                    }
                }
            }
            if(xinDao.getOwner()==null){
                if(capacity>=d){
                    User user = new User();
                    user.addXinDao(xinDao);
                    users.add(user);
                    enableUsers++;
                    return enableUsers+1;
                }else{
                    return enableUsers;
                }
            }
        }

        int i = xinDaos.get(0).capacity() < d ? 0 : 1;
        if (xinDaos.size() == 1) {
            return i;
        }
        xinDaos.remove(0);
        //return i + process(xinDaos);
        //todo
        return null;
    }
}
