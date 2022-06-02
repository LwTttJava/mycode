package test.enumdemo;

/**
 * @author luotao
 * @date 2022-3-9  23:01
 * 自定义枚举
 */
public enum Day {
    ONE_DAY(1,"星期一"){
        @Override
        void say() {
            System.out.println("今天又要开始上班了");
        }
    },
    TWO_DAY(2,"星期二"){
        @Override
        void say() {
            System.out.println("今天又是敲代码的一天");
        }
    },
    THREE_DAY(3,"星期三"){
        @Override
        void say() {
            System.out.println("今天代码发到验证环境了");
        }
    },
    FOUR_DAY(4,"星期四"){
        @Override
        void say() {
            System.out.println("今天晚上要发布项目");
        }
    },
    FIVE_DAY(5,"星期五"){
        @Override
        void say() {
            System.out.println("今天最后一天要放假了");
        }
    };
    private int dayCount;
    private String dayName;
    private Day(int dayCount,String dayName){
        this.dayCount = dayCount;
        this.dayName = dayName;
    }
    public int getDayCount(){
        return dayCount;
    }
    public String getDayName() {
        return dayName;
    }
    public void defualtSay(){
        System.out.println("今天："+dayName+"，已经过了"+dayCount+"天");
    }
    abstract void say();
}
