//package geometry.example;
////
////import java.util.ArrayList;
////import java.util.List;
////
//////public class Main {
//////
//////    public static void mian(String[] args) {
//////
//////        String[] strings = new String[]{"hello", "hi", "how are you"};
//////        register(strings);
//////    }
//////
//////    private static void register(String... strings) {
//////        for (String s : strings) {
//////            System.out.println(s);
//////        }
//////    }
//////}
//////
//////class User {
//////    public String[] strings = new String[]{"hello", "hi", "how are you"};
//////
//////
//////}
////
////class View {
////    public void render() {
////
////    }
////
////    public void update() {
////
////    }
////
////    public void register(String name) {
////
////    }
////
////    public int click(int number) {
////        return 1;
////    }
////}
////
////class Rect extends View {
////    public void render(int name) {
////
////    }
////
////    public int update(int name) {
////        return 1;
////    }
////
////    public void click(int number) {
////
////    }
////
////    public int click(int number, int num);
////
////
//////}
////
////class Person{
////    private String name;
////    private int age;
////
////    Person(String name, int age) {
////        this.name = name;
////        this.age = age;
////    }
////
////    Person(String name) {
////        this.name = name;
////    }
////
////    Person(int age) {
////        this.age = age;
////    }
////}
//
// class User{
//    private String name;
//    private int age;
//    private String birthday;
//
//     User(String name) {
//        this.name = name;
//    }
//}
//
//class TwitterUser extends User {
//    private String name;
//    private int age;
//    private String birthday;
//
//    TwitterUser(String name, int age, String birthday) {
//        super(name);
//        this.age = age;
//        this.birthday = birthday;
//    }
//
//}
//
//interface State: Serializable
//
//interface View {
//    fun getCurrentState(): State
//    fun restoreState(state: State) {}
//}
//
//public class Button implements View {
//    @Override
//    pubic State getCurrentState() {
//        return new ButtonState();
//    }
//
//    @Override
//    public void restoreState(State state) {...}
//    public class ButtonState implements State {...} // 내부 클래스
//}
