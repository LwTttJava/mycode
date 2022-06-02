package builderPattern;

/**
 * @author luotao
 * @date 2020/10/30  22:12
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "Coke";
    }

    @Override
    public float price() {
        return 30.0f;
    }
}

class Pepsi extends ColdDrink{
    @Override
    public float price() {
        return 35.0f;
    }
    @Override
    public String name() {
        return "Pepsi";
    }
}