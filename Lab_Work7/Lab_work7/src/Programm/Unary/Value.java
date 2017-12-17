package Programm.Unary;

import Programm.IExpression;
import Programm.Tools.CacheValue;

public class Value implements IExpression {

    private IExpression expression;
    private double value;

    public Value(Object value) {

        this.expression = null;
        this.value = Double.NaN;

        if (null == value) {
            throw new NullPointerException("Nullable value object");
        }

        if (value instanceof Byte) {
            this.value = new Double(value.toString());

        } else if (value instanceof Short) {
            this.value = new Double(value.toString());

        } else if (value instanceof Integer) {
            this.value = new Double(value.toString());

        } else if (value instanceof Long) {
            this.value = new Double(value.toString());

        } else if (value instanceof Float) {
            this.value = new Double(value.toString());

        } else if (value instanceof Double) {
            this.value = new Double(value.toString());

        } else if (value instanceof IExpression) {
            this.expression = new CacheValue((IExpression) value);

        } else if (value instanceof String) {

            try {
                this.value = Double.parseDouble((String) value);
            } catch (Exception exception) {
            }
        }

        if (Double.isNaN(this.value) && null == this.expression) {
            if (!(value instanceof Double)) {
                throw new NumberFormatException("Can't parse number.");
            }
        }
    }

    @Override
    public double calculate() {
        if (null != this.expression){
            return expression.calculate();
        }
        else {
            return this.value;
        }
    }
}
