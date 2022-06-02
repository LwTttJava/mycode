package test.clonedemo;

import java.io.*;

/**
 * @author luotao
 * @date 2022-3-8  16:54
 */
public class People implements Serializable ,Cloneable{
    private String name;
    private int age;
    private Integer number;
    private Son son;
    public People(){

    }

    @Override
    protected People clone()  {
        People p =null;
        try {
            p = (People) super.clone();
            p.son =  son.clone();
            return p;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public People myclone(){
        try {
            /**
             * 对象--使用工具-->ObjectOutputStream --数据写到--> ByteArrayOutputStream
             * ByteArrayOutputStream.toByteArray 得到 byte[]数据
             * toByteArray --写入到--> ByteArrayInputStream <--读取数据-- ObjectInputStream --创建对象--> 对象
             */
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            byte[] bytes = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object o = ois.readObject();
            return (People) o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void testPrivateMethod(){
        System.out.println("私有方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("People{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", number=").append(number);
        sb.append(", son=").append(son);
        sb.append('}');
        return sb.toString();
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }


}
