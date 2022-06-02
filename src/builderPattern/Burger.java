package builderPattern;

/**
 * @author luotao
 * @date 2020/10/30  22:08
 */
public abstract class Burger implements Item{
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();

}
