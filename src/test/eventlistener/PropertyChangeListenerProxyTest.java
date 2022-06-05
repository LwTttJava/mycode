package test.eventlistener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeListenerProxy;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author luotao
 * @date 2022-6-5  0:00
 */
public class PropertyChangeListenerProxyTest {
    public static void main(String[] args) {
        PropertyChangeListener propertyChangeEventConsumer = (PropertyChangeEvent evt) -> {
            System.out.println(String.format("propagationId=%s",evt.getPropagationId()));
            System.out.println(evt.getSource());
            System.out.println(evt.toString());
            return;
        };
        Student student = new Student("张三",18,new PropertyChangeListenerProxy("",propertyChangeEventConsumer));
        student.age=20;
        student.setAge(30);
    }
}

class Student{
    //PropertyChangeListenerProxy proxy = new PropertyChangeListenerProxy("name",(evt)->System.out.println(evt.toString()));
    PropertyChangeListenerProxy proxy;
    String name;
    int age;
    public Student(String name, int age) {
        this(name,age,null);
    }
    public Student(String name, int age,PropertyChangeListenerProxy proxy) {
        if(proxy != null){
            this.proxy = proxy;
        }
        this.name = name;
        this.age = age;
    }

    public void setAge(int age) {
        PropertyChangeEvent studentAgeChangeEvent = new PropertyChangeEvent(this,"age",this.age,age);
        proxy.propertyChange(studentAgeChangeEvent);
        this.age = age;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append('}');
        return sb.toString();
    }
}

