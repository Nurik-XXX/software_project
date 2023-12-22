package com.example.softwareproject.Quiz;

import com.example.softwareproject.ClassTemplate;
import com.example.softwareproject.Questions;

public class MAT251 extends ClassTemplate {
    @Override
    protected Questions[] getQuestions() {
        return new Questions[]{
                new Questions("Сколько будет 3 * 5?", new String[]{"10", "25", "20", "15"}),
                new Questions("Решите уравнение: 2x + 7 = 15", new String[]{"3", "6", "5", "4"}),
                new Questions("Как называется теорема, утверждающая, что квадрат гипотенузы прямоугольного треугольника равен сумме квадратов катетов?", new String[]{"Архимедова теорема", "Эйлерова теорема", "Ферма теорема", "Пифагорова теорема"}),
                new Questions("Чему равен синус угла 30 градусов?", new String[]{"0.866", "0.707", "1", "0.5"}),
                new Questions("Сколько существует различных перестановок из букв слова 'МАТЕМАТИКА'?", new String[]{"120", "5040", "720", "3628800"}),
                new Questions("Решите систему уравнений: \n2x + y = 8 \n3x - y = 2", new String[]{"x=0, y=8", "x=1, y=6", "x=3, y=2", "x=2, y=4"})

                // Добавьте дополнительные вопросы, если нужно
        };
    }
}
