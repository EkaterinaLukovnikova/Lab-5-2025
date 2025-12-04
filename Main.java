import functions.*;
import functions.basic.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, FunctionPointIndexOutOfBoundsException,  InappropriateFunctionPointException, ClassNotFoundException {
        
        FunctionPoint[] points1 = {
            new FunctionPoint(0, 0),
            new FunctionPoint(1, 1),
            new FunctionPoint(2, 4),
            new FunctionPoint(3, 9),
            new FunctionPoint(4, 16)
        };
        
        FunctionPoint[] points2 = {
            new FunctionPoint(0, 0),
            new FunctionPoint(1, 1),
            new FunctionPoint(2, 4),
            new FunctionPoint(3, 9),
            new FunctionPoint(4, 16)
        };
        
        FunctionPoint[] points3 = {
            new FunctionPoint(0, 0),
            new FunctionPoint(1, 2),
            new FunctionPoint(2, 5),
            new FunctionPoint(3, 10),
            new FunctionPoint(4, 17)
        };
        
        
        System.out.println("1. Тест toString():");
        
        ArrayTabulatedFunction arrayFunc1 = new ArrayTabulatedFunction(points1);
        LinkedListTabulatedFunction listFunc1 = new LinkedListTabulatedFunction(points2);
        
        System.out.println("ArrayTabulatedFunction arrayFunc1:");
        System.out.println("  toString() = " + arrayFunc1.toString());
        System.out.println();
        
        System.out.println("LinkedListTabulatedFunction listFunc1:");
        System.out.println("  toString() = " + listFunc1.toString());
        System.out.println();
        
        ArrayTabulatedFunction arrayFunc2 = new ArrayTabulatedFunction(points3);
        System.out.println("ArrayTabulatedFunction arrayFunc2 (другие точки):");
        System.out.println("  toString() = " + arrayFunc2.toString());
        System.out.println();
        
        
        System.out.println("\n2. Тест equals():");
        
        System.out.println("  arrayFunc1.equals(listFunc1) = " + arrayFunc1.equals(listFunc1));
        System.out.println("  listFunc1.equals(arrayFunc1) = " + listFunc1.equals(arrayFunc1));
        System.out.println();
        
        System.out.println("  arrayFunc1.equals(arrayFunc2) = " + arrayFunc1.equals(arrayFunc2));
        System.out.println("  arrayFunc2.equals(arrayFunc1) = " + arrayFunc2.equals(arrayFunc1));
        System.out.println();
        
        System.out.println("  arrayFunc1.equals(arrayFunc1) = " + arrayFunc1.equals(arrayFunc1));
        System.out.println("  listFunc1.equals(listFunc1) = " + listFunc1.equals(listFunc1));
        System.out.println();
        
        System.out.println("\n3. Тест hashCode():");
        
        
        System.out.println("  arrayFunc1.hashCode() = " + arrayFunc1.hashCode());
        System.out.println("  listFunc1.hashCode() = " + listFunc1.hashCode());
        System.out.println("  arrayFunc2.hashCode() = " + arrayFunc2.hashCode());
        System.out.println();
        
        System.out.println("  arrayFunc1.equals(listFunc1) = " + arrayFunc1.equals(listFunc1));
        System.out.println("  arrayFunc1.hashCode() == listFunc1.hashCode() = " + (arrayFunc1.hashCode() == listFunc1.hashCode()));
        System.out.println();
        
        System.out.println("Изменение функции и проверка изменения hashCode:");
        ArrayTabulatedFunction testFunc = new ArrayTabulatedFunction(points1);
        System.out.println("  Исходный hashCode = " + testFunc.hashCode());
        
        testFunc.setPointY(2, 4.001); 
        System.out.println("  После изменения точки (2, 4) на (2, 4.001):");
        System.out.println("  Новый hashCode = " + testFunc.hashCode());
        System.out.println("  Сравнение с исходным hashCode " + (testFunc.hashCode() == arrayFunc1.hashCode()));
        System.out.println("  Результат сравнения с исходным объектом " + testFunc.equals(arrayFunc1));
        System.out.println();
        
        System.out.println("\n4. Тест clone():");
        
        ArrayTabulatedFunction arrayOriginal = new ArrayTabulatedFunction(points1);
        ArrayTabulatedFunction arrayClone = null;
        try {
            arrayClone = (ArrayTabulatedFunction) arrayOriginal.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("  Ошибка клонирования: " + e.getMessage());
        }
        
        if (arrayClone != null) {
            System.out.println("  Исходная функция: " + arrayOriginal);
            System.out.println("  Клонированная функция: " + arrayClone);
            System.out.println("  Одинаковые после клонирования? = " + arrayOriginal.equals(arrayClone));
            System.out.println("  Это один и тот же объект? " + (arrayOriginal == arrayClone));
            System.out.println();
            
            System.out.println("Проверка глубокого клонирования ArrayTabulatedFunction:");
            arrayOriginal.setPointY(3, 100); 
            System.out.println("  Исходная после изменения: " + arrayOriginal);
            System.out.println("  Клон после изменения: " + arrayClone);
            System.out.println("  Одинаковые ли после изменения = " + arrayOriginal.equals(arrayClone));
            System.out.println();
        }
        
        System.out.println("Тестирование clone() для LinkedListTabulatedFunction:");
        LinkedListTabulatedFunction listOriginal = new LinkedListTabulatedFunction(points2);
        LinkedListTabulatedFunction listClone = null;
        try {
            listClone = (LinkedListTabulatedFunction) listOriginal.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("  Ошибка клонирования: " + e.getMessage());
        }
        
        if (listClone != null) {
            System.out.println("  Исходная функция: " + listOriginal);
            System.out.println("  Клонированная функция: " + listClone);
            System.out.println("  Одинаковые после клонирования? " + listOriginal.equals(listClone));
            System.out.println("  Это один и тот же объект? " + (listOriginal == listClone));
            System.out.println();
            
            System.out.println("Проверка глубокого клонирования LinkedListTabulatedFunction:");
            listOriginal.setPointY(1, 200); 
            System.out.println("  Исходная после изменения: " + listOriginal);
            System.out.println("  Клон после изменения: " + listClone);
            System.out.println("  Одинаковые ли после изменения = " + listOriginal.equals(listClone));
            System.out.println();
        }
        
        System.out.println("\n5. Тест с реальными функциями:");
        
        Function sinFunc = new Sin();
        Function cosFunc = new Cos();
        double pi = Math.PI;
        
        TabulatedFunction tabulatedSin = TabulatedFunctions.tabulate(sinFunc, 0, pi, 20);
        TabulatedFunction tabulatedCos = TabulatedFunctions.tabulate(cosFunc, 0, pi, 20);
        
        System.out.println("Табулированные sin и cos:");
        System.out.println("  Табулированный sin: " + tabulatedSin);
        System.out.println("  Табулированный cos: " + tabulatedCos);
        System.out.println();
        
        TabulatedFunction clonedSin = null;
        try {
            clonedSin = (TabulatedFunction) tabulatedSin.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("  Ошибка клонирования sin: " + e.getMessage());
        }
        
        if (clonedSin != null) {
            System.out.println("Клонированный sin: " + clonedSin);
            System.out.println("  Равенство c исходным? " + tabulatedSin.equals(clonedSin));
            
            
            if (tabulatedSin instanceof ArrayTabulatedFunction) {
                ((ArrayTabulatedFunction)tabulatedSin).setPointY(10, 999);
            } else if (tabulatedSin instanceof LinkedListTabulatedFunction) {
                ((LinkedListTabulatedFunction)tabulatedSin).setPointY(10, 999);
            }
            
            System.out.println("  После изменения исходного в точке 10:");
            System.out.println("    Исходный sin[10] = " + tabulatedSin.getPointY(10));
            System.out.println("    Клон sin[10] = " + clonedSin.getPointY(10));
            System.out.println("    Равны? " + tabulatedSin.equals(clonedSin));
        }
        
        System.out.println("\n6. Тест с разным количеством точек:");
        
        TabulatedFunction sin10 = TabulatedFunctions.tabulate(sinFunc, 0, pi, 10);
        TabulatedFunction sin20 = TabulatedFunctions.tabulate(sinFunc, 0, pi, 20);
        TabulatedFunction sin10Clone = null;
        
        try {
            sin10Clone = (TabulatedFunction) sin10.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("  Ошибка клонирования sin10: " + e.getMessage());
        }
        
        if (sin10Clone != null) {
            System.out.println("sin с 10 точками: " + sin10);
            System.out.println("sin с 20 точками: " + sin20);
            System.out.println("Клон sin с 10 точками: " + sin10Clone);
            System.out.println();
            
            System.out.println("  sin10.equals(sin20) = " + sin10.equals(sin20));
            System.out.println("  sin10.hashCode() == sin20.hashCode() = " + (sin10.hashCode() == sin20.hashCode()));
        }
    }
}