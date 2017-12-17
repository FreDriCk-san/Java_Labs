package Programm.Unary;

import Programm.IExpression;
import Programm.Tools.CacheCalculation;

public class Negative extends UnaryExpression implements IExpression {

    private CacheCalculation cacheValue;

    public Negative(Object value) {
        super(value);

        cacheValue = new CacheCalculation(() -> -this.value.calculate());
    }

    @Override
    public double calculate() {
        return cacheValue.calculate();
    }
}
