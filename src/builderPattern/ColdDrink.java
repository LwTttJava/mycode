package builderPattern;

/**
 * @author luotao
 * @date 2020/10/30  22:09
 */
public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price() ;

}
