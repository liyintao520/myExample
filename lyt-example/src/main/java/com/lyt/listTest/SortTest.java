package com.lyt.listTest;

import com.lyt.module.user.entity.User;

import java.text.Collator;
import java.util.*;

/**
 * @ClassName SortTest
 * @Description TODO
 * @Author liyintao
 * @Date 2020/7/22 16:57
 */
public class SortTest {

    public static void main(String[] args) {
        User user = new User();
        List<User> list = new ArrayList<>();
        user.setUserName("波比");
        list.add(user);
        User user4 = new User();
        user4.setUserName("治安");
        list.add(user4);
        User User3 = new User();
        User3.setUserName("啊啊");
        list.add(User3);
        User user2 = new User();
        user2.setUserName("这个");
        list.add(user2);
        User User99 = new User();
        User99.setUserName(null);
        list.add(User99);
        User user1 = new User();
        user1.setUserName("啊波");
        list.add(user1);
        User user88 = new User();
        user88.setUserName("a波");
        list.add(user88);
        User user89 = new User();
        user89.setUserName("12");
        list.add(user89);
        User user90 = new User();
        user90.setUserName(null);
        list.add(user90);
        //   一二三
        Date date = new Date();
        list.add(new User("二", null, date, date));
        list.add(new User("四", null, date, date));
        list.add(new User("三", null, date, date));
        list.add(new User("一", null, date, date));
        list.add(new User("八", null, date, date));
        for (User User5 : list) {
            System.out.println(User5.getUserName());
        }
        Collections.sort(list, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                //这里俩个是对属性判null处理，为null的都放到列表最下面
                if (null==o1.getUserName()){
                    return 1;
                }
                if (null==o2.getUserName()){
                    return -1;
                }
                return Collator.getInstance(Locale.CHINESE).compare(o1.getUserName(),o2.getUserName());
            }
        });
        System.out.println("排序后结果：");
        for (User obj : list) {
            System.out.println(obj.getUserName());
        }
    }
}
