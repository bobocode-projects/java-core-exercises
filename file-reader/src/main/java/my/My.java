package my;

import java.util.ArrayList;
import java.util.List;

public class My {
    public static void main(String[] args) {
        //set value need to be super & get to use extends
        //PEX
        List<? super Number> list = new ArrayList<>();
        list.add(new Integer(1));
        list.get(0);
//        list.add("saga");
        System.out.println(list.toString());
    }
}
