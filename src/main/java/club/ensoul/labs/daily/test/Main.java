package club.ensoul.labs.daily.test;


import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    
    public static void main(String[] args) {
        
        // 求单词长度之和
        Stream<String> stream1 = Stream.of("I", "love", "you", "too");
        Integer lengthSum1 = stream1.reduce(0, (sum, str) -> sum + str.length(), (a, b) -> a + b);
        System.out.println(lengthSum1);
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        
        Stream<String> stream2 = Stream.of("I", "love", "you", "too");
        Optional<String> lengthSum2 = stream2.reduce((a, b) -> a + " " + b);
        System.out.println(lengthSum2.get());
        
        // 将Stream转换成容器或Map
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        // List<String> list = stream.collect(Collectors.toList()); // (1)
        // Set<String> set = stream.collect(Collectors.toSet()); // (2)
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length)); // (3)
        for(Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        
        BeanA beanA = new BeanA();
        beanA.setUserName("xiaoming");
        
        BeanB beanB = beanA.beanConvert(() -> new BeanB(beanA));
        System.out.println(beanB);
        
    }
    
    
}
