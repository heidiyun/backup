//////public class Person {
//////    public static void main(String[] args) {
//////        System.out.println(max(1, 2));
//////    }
//////
//////    private static int max(int a, int b) {
//////        return (a > b) ? a : b;
//////    }
//////}
//////
//////public class Person {
//////    private final String name;
//////
//////    public Person(String name) {
//////        this.name = name;
//////    }
//////
//////    public String getname() {
//////        return name;
//////    }
//////}
////
////
////public class Person {
////    private String name;
////    private String address;
////
////    public Person(String name, String address) {
////        this.name = name;
////        this.address = address;
////    }
////
////    public String getName() {
////        return name;
////    }
////
////    public void setName(String name) {
////        this.name = name;
////    }
////
////    public String getAddress() {
////        return address;
////    }
////
////    public void setAddress(String address) {
////        this.address = address;
////    }
////
//////    public static void main(String[] args) {
//////        Rectangle rectangle = new Rectangle(100, 100);
//////        System.out.println(rectangle.isSquare());
//////    }
////}
////
////
////
////
//
//import java.util.Map;
//import java.util.TreeMap;
//
//public class Person{
//
//    public static void main(String[] args) {
//        TreeMap<Character, String> binaryReps = new TreeMap<>();
//
//        for (Character key : binaryReps.keySet()) {
//            System.out.println(key + binaryReps.get(key));
//        }
//    }
//
//    public int function(int percentage) throws Exception {
//
//        if (percentage <= 100 && percentage >= 0) {
//            return percentage;
//        }
//
//        else
//            throw new Exception();
//    }
//}
//
//
//class OrderUtil {
//
//    private Map<>
//    public static Discount createDiscountItem(String discountCode) throws Exception {
//        if(!isValidCode(discountCode)) {
//            throw new Exception("잘못된 할인 코드");
//        }
//        // 쿠폰 코드인가? 포인트 코드인가?
//        if(isUsableCoupon(discountCode)) {
//            return new Coupon(1000);
//        } else if(isUsablePoint(discountCode)) {
//            return new Point(500);
//        }
//        throw new Exception("이미 사용한 코드");
//    }
//}
//
//class Coupon extends Discount { }
//class Point extends Discount { }
//
//import java.util.stream.Stream;
//
//public class Person {
//    public static void main(String[] args) {
//        String str = "i'm your father";
//
//        Stream<String> stream = Stream.of(str.split(" "));
//        Stream<String> stream1 = Stream.of("a", "b", "c");
//
//        System.out.println(stream);
//    }
//}



//import ex1.SampleKt;
//import kotlin.jvm.functions.Function1;
//
//public class Person{
//    public static void main(String[] args) {
//        SampleKt.processTheAnswer(
//                new Function1<Integer, Integer>() {
//                    @Override
//                    public Integer invoke(Integer number) {
//                        System.out.println(number);
//                        return number + 1;
//                    }
//                }
//        );
//    }
//}

import java.util.ArrayList;
import java.util.List;

public class Person {
    private List list = new ArrayList<>();
}