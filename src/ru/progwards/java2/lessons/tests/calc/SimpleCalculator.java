//  1.1 Реализовать класс SimpleCalculator с методами
public class SimpleCalculator implements ICalculator{
    int checkOver(long source){
        if (source > Integer.MAX_VALUE || source < Integer.MIN_VALUE)
            throw new ArithmeticException("Переполнение размера Integer");
        return (int)source;
    }
    @Override
    public int sum(int a, int b) {
        long result = (long)a + b;
        return checkOver(result);
    }

    @Override
    public int diff(int a, int b) {
        long result = (long)a - b;
        return checkOver(result);
    }

    @Override
    public int mult(int a, int b) {
        long result = (long)a * b;
        return checkOver(result);
    }

    @Override
    public int div(int a, int b) {
        if (b == 0)
            throw new ArithmeticException("Деление на ноль");
        long result = (long)a / b;
        return checkOver(result);
    }
}
