package com.chenhan.stream;

import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain1 {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Tom", 8900, 25,"male", "New York"));
        list.add(new Person("Jack", 7000,26,"male", "Washington"));
        list.add(new Person("Lily", 7800,29, "female", "Washington"));
        list.add(new Person("Anni", 8200, 35,"female", "New York"));
        list.add(new Person("Owen", 9500, 40,"male", "New York"));
        list.add(new Person("Jerry", 7900, 49,"female", "New York"));
        list.add(new Person("Alisa", 7900, 45,"female", "New York"));


        //筛选、遍历，输出结果：Anni、Owen、Alisa
        list.stream().filter(person -> person.getAge() > 30).forEach(person -> System.out.println(person.getName()));

        //匹配第一个
        Optional<Person> first = list.stream().filter(person -> person.getAge() > 30).findFirst();

        //匹配任意（适用于并行流）
        Optional<Person> any = list.parallelStream().filter(person -> person.getAge() > 20).findAny();

        // 是否包含符合特定条件的元素
        boolean b = list.stream().anyMatch(person -> person.getAge() == 29);

        System.out.println("findFirst：" + (first.isPresent()?first.get().getName():"") );
        System.out.println("findAny：" + (any.isPresent()?any.get().getName():""));
        System.out.println("anyMatch = 29：" + b);


        //筛选员工中工资高于8000的人，并将姓名形成新的集合(map将每个Person类映射为name值或其他值)。 形成新集合依赖collect（收集）
        List<String> collect1 = list.stream().filter(person -> person.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.println("高于8000工资的员工" + collect1);

        //聚合，获取字符串中最长的
        List<String> strList = Arrays.asList("name", "age", "howdoyoudo", "I\'m fine,Thank you.");
        Optional<String> max = strList.stream().max(Comparator.comparing(String::length));
        System.out.println("最长字符串：" + max.get());





        //获取工资最高的人
        Optional<Person> max3 = list.stream().max(Comparator.comparing(Person::getSalary));
        System.out.println("工资最高是：" + max3.get().getSalary());

        //给所有人加1000工资，改变了原list的值
        /*List<Person> collect = list.stream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("增加后：" + collect.get(0).getName() + ":" + collect.get(0).getSalary());
        System.out.println("增加后，原list：" + collect.get(0).getName() + ":" + collect.get(0).getSalary());*/

        //给所有人加1000工资，不改变原list的值
        List<Person> collect = list.stream().map(person -> {
            Person personNew = new Person();
            BeanUtils.copyProperties(person, personNew);
            personNew.setSalary(person.getSalary() + 1000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("增加后：" + collect.get(0).getName() + ":" + collect.get(0).getSalary());
        System.out.println("增加后，原list：" + list.get(0).getName() + ":" + list.get(0).getSalary());


        //将两个字符数组合并成一个新的字符数组。
        List<String> listStr = Arrays.asList("m,k,l,a", "1,3,5,7");
        List<String> listStr2 = listStr.stream().flatMap(str -> {
            String[] split = str.split(",");
            Stream<String> stream = Arrays.stream(split);
            return stream;
        }).collect(Collectors.toList());
        System.out.println("转换前：" + listStr);
        System.out.println("转换后：" + listStr2);


        //归约(reduce)，把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
        //求Integer集合的元素之和、乘积和最大值。
        List<Integer> listInt1 = Arrays.asList(1, 3, 2, 8, 11, 4);
        //求和方式1
        Optional<Integer> sum1 = listInt1.stream().reduce((c, d) -> c + d);
        //求和方式2
        Optional<Integer> sum2 = listInt1.stream().reduce(Integer::sum);
        //求和方式3
        Integer sum3 = listInt1.stream().reduce(0, Integer::sum);
        System.out.println("三种求和：" + sum1.get() + "--" + sum2.get() + "--" + sum3);

        //乘积
        Optional<Integer> sum4 = listInt1.stream().reduce((c, d) -> c * d);
        System.out.println("乘积：" + sum4.get());

        //最大值  listInt1.stream().max(Integer::compareTo);
        Optional<Integer> sum5 = listInt1.stream().reduce((c, d) -> c>d?c:d);
        System.out.println("最大值：" + sum5.get());



        //求工资总和
        Optional<Integer> sumSalary = list.stream().map(person -> person.getSalary()).reduce(Integer::sum);
        Optional<Integer> sumSalary1 = list.stream().map(person -> person.getSalary()).reduce((c, d) ->  c + d);

        // identity提供一个跟Stream中数据同类型的初始值，得到一个同类型的最终结果，identity会参与后面的表达式运算，不能随便指定值
        Integer sumSalary2 = list.stream().map(person -> person.getSalary()).reduce(0, (c, d) -> c + d);
        System.out.println("工资总和1：" + sumSalary.get() + "工资总和2：" + sumSalary1.get() + "工资总和3：" + sumSalary2);

        //最高工资
        Integer reduceMax = list.stream().map(person -> person.getSalary()).reduce(0, (c, d) -> c > d ? c : d);
//        list.stream().map(person -> person.getSalary()).max(Integer::max);
//        list.stream().max(Comparator.comparing(Person::getSalary));
//        Optional<Integer> reduceMax1 = list.stream().map(person -> person.getSalary()).reduce((c, d) -> c > d ? c : d);
        System.out.println("最高工资：" + reduceMax);


        
        //Collectors.toMap
        Map<String, Person> collectToMap = list.stream().filter(person -> person.getSalary() > 8000).collect(Collectors.toMap(Person::getName, p -> p));
        System.out.println("toMap：" + collectToMap);


        /*Collectors统计
        * 计数：count
        *   平均值：averagingInt、averagingLong、averagingDouble
        *   最值：maxBy、minBy
        *   求和：summingInt、summingLong、summingDouble
        *   统计以上所有：summarizingInt、summarizingLong、summarizingDouble
        *
        */
        //统计员工人数
        Long collectCount = list.stream().collect(Collectors.counting());
        System.out.println("总人数：" + collectCount);

        //平均工资
        Double collectAverage = list.stream().collect(Collectors.averagingDouble(Person::getSalary));
        System.out.println("平均工资："  + collectAverage);

        //最高工资
        Optional<Integer> collectMax = list.stream().map(person -> person.getSalary()).collect(Collectors.maxBy(Integer::compareTo));
        System.out.println("最高工资：" + collectMax.get());

        //工资总额
        Integer collectSum = list.stream().collect(Collectors.summingInt(Person::getSalary));
        System.out.println("工资总额：" + collectSum);

        //统计全部
        DoubleSummaryStatistics collectAllStatistics = list.stream().collect(Collectors.summarizingDouble(Person::getSalary));
        System.out.println("全部统计值：" + collectAllStatistics);


        //Collectors 分组(partitioningBy：只能按照是否分两组/groupingBy：按指定条件分多组)
        //按薪资是否大于7000分组
        Map<Boolean, List<Person>> collectPartition = list.stream().collect(Collectors.partitioningBy(person -> person.getSalary() > 7000));
        System.out.println("按工资是否大于7000分组：" + collectPartition);

        //按照性别分组
        Map<String, List<Person>> collectGrouping = list.stream().collect(Collectors.groupingBy(person -> person.getSex()));
        System.out.println("按性别分组：" +collectGrouping);

        //先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> collectPartitionGrouping =
                list.stream().collect(Collectors.groupingBy(person -> person.getSex(), Collectors.groupingBy(person -> person.getArea())));
        System.out.println("按性别分组后再按地区分组：" + collectPartitionGrouping);

        //Collectors接合joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串。
        String collectJoining = list.stream().map(Person::getName).collect(Collectors.joining());
        System.out.println("joining ：" + collectJoining);

        //用|连接
        String collectJoining1 = list.stream().map(Person::getName).collect(Collectors.joining("|"));
        System.out.println("joining ：" + collectJoining1);

        //Collectors归约reduce，相比于stream本身的reduce方法，增加了对自定义归约的支持。
        //每个员工扣减起征点后的工资总和
        Integer collectorSum = list.stream().collect(Collectors.reducing(0, Person::getSalary, (x, y) -> x + y - 5000));
        System.out.println("每个员工扣减起征点后的工资总和：" + collectorSum);


        //stream排序sorted
        //按工资升序（自然排序）
        List<String> sortedSalary = list.stream().sorted(Comparator.comparing(Person::getSalary)).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资升序（自然排序）：" + sortedSalary);

        //按工资倒序
        List<String> sortedSalaryReversed = list.stream().sorted(Comparator.comparing(Person::getSalary).reversed()).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资倒序：" + sortedSalaryReversed);

        //按工资升序，工资一样再按年龄升序排序
        List<String> sortedSalaryAge = list.stream().sorted(Comparator.comparing(Person::getSalary).thenComparing(Person::getAge)).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资再按年龄升序排序：" + sortedSalaryAge);

        //按工资再按年龄自定义排序（下面实现的是降序）

        List<String> sorted = list.stream().sorted((m, n) -> {
            if (m.getSalary() == n.getSalary()) {
                return n.getAge() - m.getAge();//工资相同，按年龄降序
            } else {
                return n.getSalary() - m.getSalary();//按工资降序
            }
        }).map(Person::getName).collect(Collectors.toList());
        System.out.println("按工资再按年龄自定义（降序）排序：" + sorted);


        //提取、组合：流也可以进行合并、去重、限制、跳过等操作
        //iterate可以得到无限流，limit可以限制长度
        //斐波那契数列
        Stream.iterate(new int[]{0,1}, n -> new int[]{n[1],n[0]+n[1]}).limit(20).map(n -> n[0]).forEach(x -> System.out.print(x + ","));

        //奇数流
        System.out.println();
        Stream.iterate(0,n -> n + 1).filter(x -> x % 2 != 0).limit(10).forEach(x -> System.out.print(x + ","));

        // contact合并，distinct去重
        System.out.println();
        String[] arr1 = { "a", "b", "c", "d" };
        String[] arr2 = { "d", "e", "f", "g" };
        Stream<String> a1 = Stream.of(arr1);
        Stream<String> a2 = Stream.of(arr2);
        List<String> conDis = Stream.concat(a1, a2).distinct().collect(Collectors.toList());
        System.out.println("[a, b, c, d]、[d, e, f, g]合并去重后：" + conDis);

        //跳过流前n个数据
        List<Integer> skip = Stream.iterate(0, n -> n + 1).limit(20).skip(15).collect(Collectors.toList());
        System.out.println(skip);
    }

}
