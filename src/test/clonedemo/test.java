package test.clonedemo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author luotao
 * @date 2022-6-2  21:14
 */
public class test {
    public static void main(String[] args) throws IOException {
        People people = new People("张三",30);
        people.setNumber(1000);
        people.setSon(new Son("张小三","5"));

        People clone = people.clone();
        clone.setNumber(2000);
        clone.getSon().setName("克隆修改");

        System.out.println("people==clone"+(people==clone));
        System.out.println(people);
        System.out.println(clone);

        people.setSon(new Son("王五","12"));
        System.out.println(people);
        System.out.println(clone);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\people.txt"));
        oos.writeObject(people);
    }
}
