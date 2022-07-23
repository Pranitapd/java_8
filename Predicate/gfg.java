package Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class gfg {

    static void pred(int num, Predicate<Integer> predicate){
        if(predicate.test(num))
            System.out.println("Number " + num);
    }

    public static Predicate<String> hasLengthOf10 = new Predicate<String>() {
        @Override
        public boolean test(String s) {
            return s.length()<10;
        }
    };

    public static void predicate_and(){
        Predicate<String> nonNullPredicate = Objects::nonNull;
        //https://www.educative.io/answers/what-is-objectsnonnull-in-java

        String nullString = null;
        boolean res4 = nonNullPredicate.and(hasLengthOf10).test(nullString);
        System.out.println("res4 " + res4);

        String lengthGreaterThan10 = "String length greater than 10";
        boolean res5 = nonNullPredicate.and(hasLengthOf10).test(lengthGreaterThan10);
        System.out.println("res5 " + res5);
    }

    public static void predicate_or(){
        Predicate<String> containsletterA = p-> p.contains("A");
        String containsA = "Pranita";
        boolean res3 = hasLengthOf10.or(containsletterA).test(containsA);
        System.out.println("res3 " + res3);
    }

    static class User {
        String name, role;

        User(String a, String b) {
            name = a;
            role = b;
        }

        String getRole() {
            return role;
        }

        String getName() {
            return name;
        }

        public String toString() {
            return "User Name : " + name + ", Role :" + role;
        }
    }
    //predicate in collection
    public static List<User> process(List<User> users, Predicate<User> predicate){
        List<User> res = new ArrayList<>();
        for(User user:users)
            if(predicate.test(user))
                res.add(user);
        return res;
    }

    public static void main(String[] args)
    {
        //Simple
        Predicate<Integer> lessThan = i ->(i<20);
        System.out.println(lessThan.test(12));

        //Predicate chaining
        Predicate<Integer> greaterThan = i -> (i > 10);
        boolean res1 = greaterThan.and(lessThan).test(15);
        boolean res2 = greaterThan.and(lessThan).negate().test(15);
        System.out.println(res1 + " " + res2);

        //predicate into function
        pred(5, i->i<7);

        //Predicate OR
        predicate_or();
        predicate_and();

        List<User> users = new ArrayList<>();
        users.add(new User("John","admin"));
        users.add(new User("Peter","adm"));
        List admins = process(users, p->p.getRole().equals("admin"));
        System.out.println(admins);
    }
}
