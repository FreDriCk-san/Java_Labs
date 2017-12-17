package Programm.Tools;

import Programm.IExpression;
import Programm.Unary.Value;

import java.util.function.Supplier;

public class CacheCalculation implements IExpression {

    private CacheValue cache;
    private Supplier< Double> expression;

    public CacheCalculation(Supplier<Double> expression){

        this.cache = null;
        this.expression = expression;
    }

    @Override
    public double calculate() {

        if (null == cache){
            Double result = expression.get();
            cache = new CacheValue(new Value(result));
        }

        return cache.calculate();
    }
}
