package test.enumdemo;

/**
 * @author luotao
 * @date 2022-6-2  21:04
 */
public class test {

    public static void main(String[] args) {
        Day dayOf = Day.FIVE_DAY;

        switch (dayOf){
            case ONE_DAY:
                System.out.println("第1天被匹配");
                break;
            case TWO_DAY:
                System.out.println("第2天被匹配");
                break;
            case THREE_DAY:
                System.out.println("第3天被匹配");
                break;
            case FOUR_DAY:
                System.out.println("第4天被匹配");
                break;
            case FIVE_DAY:
                System.out.println("第5天被匹配");
                break;
        }

        for (Day value : Day.values()) {
            value.defualtSay();
            value.say();
            System.out.println(value.getDayCount());
            System.out.println(value.getDayName());
        }
    }
}
