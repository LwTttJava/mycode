package function;




import test.ListConcurrentAddAndRemoveTest;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 * @author luotao
 * @date 2022-3-24  20:36
 */
public class ConsumerTest {

    public static void main(String[] args) {
        Consumer<String> c1 = x-> System.out.print("开始："+x.substring(0,8));
        Consumer<String> c2 = x-> System.out.println(" 结尾："+x.substring(24,32));
        List<String> strList = ListConcurrentAddAndRemoveTest.getList();
        Optional.ofNullable(strList).ifPresent(temp->testCounsumer01(c1,c2,temp));
        //testCounsumer01(c1,c2,strList);
    }

    public static void testCounsumer01(Consumer<String> c1, Consumer<String> c2,List<String> list){

        list.stream().forEach(c1.andThen(c2));
    }

    private static void testLogger() {
        Logger logger =  Logger.getLogger("ConsumerTest");

        String info_message = new XMLFormatter().format(new LogRecord(Level.INFO, "info message"));

        System.out.println(info_message);
        //PredicateTest.test("张三");
        logger.info("info message");

        logger.warning("warn message");

        logger.log(Level.OFF,"off messgage");
    }


}
