package com.example.softwareproject.Quiz;

import com.example.softwareproject.ClassTemplate;
import com.example.softwareproject.Questions;

public class CSS215 extends ClassTemplate {
    @Override
    protected Questions[] getQuestions() {
        return new Questions[]{
                new Questions("В каком из вариантов представлен корректный формат вывода информации на экран?", new String[] {"Console.Write()", "console.log()", "print()", "System.out.println()"}),
                new Questions("Какой тип данных отвечает за целые числа?", new String[] {"String", "Float", "Boolean", "Integer"}),
                new Questions("Где правильно присвоено новое значение к многомерному массиву?", new String[] {"a(0)(0) = 1;", "a[0 0] = 1;", "a{0}{0} = 1;", "a[0][0] = 1;"}),
                new Questions("Какой метод позволяет запустить программу на Java?", new String[] {"Основного метода нет", "С класса, что был написан первым и с методов что есть внутри него", "Любой, его можно задавать в настройках проекта", "С метода main в любом из классов"}),
                new Questions("Каждый файл должен называется...", new String[] {"по имени первой библиотеки в нём", "по имени названия пакета", "как вам захочется", "по имени класса в нём"}),
                new Questions("Сколько параметров может принимать функция?", new String[] {"5", "10", "20", "неограниченное количество"}),



        };
    }

//    @FXML
//    private ToggleGroup answers;
//
//    @FXML
//    private Text question_text;
//
//    @FXML
//    private ResourceBundle resources;
//
//    @FXML
//    private URL location;
//
//    @FXML
//    private Button nextButton;
//
//    @FXML
//    private RadioButton radioButton1;
//
//    @FXML
//    private RadioButton radioButton2;
//
//    @FXML
//    private RadioButton radioButton3;
//
//    @FXML
//    private RadioButton radioButton4;
//
//
//    private Questions[] questions = new Questions[]{
//            new Questions("В каком из вариантов представлен корректный формат вывода информации на экран?", new String[] {"Console.Write()", "console.log()", "print()", "System.out.println()"}),
//            new Questions("Какой тип данных отвечает за целые числа?", new String[] {"String", "Float", "Boolean", "Integer"}),
//            new Questions("Где правильно присвоено новое значение к многомерному массиву?", new String[] {"a(0)(0) = 1;", "a[0 0] = 1;", "a{0}{0} = 1;", "a[0][0] = 1;"}),
//            new Questions("Какой метод позволяет запустить программу на Java?", new String[] {"Основного метода нет", "С класса, что был написан первым и с методов что есть внутри него", "Любой, его можно задавать в настройках проекта", "С метода main в любом из классов"}),
//            new Questions("Каждый файл должен называется...", new String[] {"по имени первой библиотеки в нём", "по имени названия пакета", "как вам захочется", "по имени класса в нём"}),
//            new Questions("Сколько параметров может принимать функция?", new String[] {"5", "10", "20", "неограниченное количество"})
//
//
//    };
//
//    private int nowQuestion = 0, correctAnswers;
//    private String nowCorrectAnswer;
//    ToggleGroup tg = new ToggleGroup();
//
//
//    @FXML
//    void initialize() {
//        radioButton1.setToggleGroup(tg);
//        radioButton2.setToggleGroup(tg);
//        radioButton3.setToggleGroup(tg);
//        radioButton4.setToggleGroup(tg);
//
//        nowCorrectAnswer = questions[nowQuestion].correctAnswer();
//
//         //Отслеживание нажатия на кнопку "Ответить"
//        nextButton.setOnAction(e -> {
//            // Получаем выбранную кнопку пользователем
//            RadioButton selectedRadioButton = (RadioButton) tg.getSelectedToggle();
//            // Код будет выполняться только если пользователь выбрал ответ
//            if(selectedRadioButton != null) {
//                // Получаем текст ответа
//                String toogleGroupValue = selectedRadioButton.getText();
//
//                // Сверяем ответ с корректным
//                if(toogleGroupValue.equals(nowCorrectAnswer)) {
//                    // Выводим информацию и увеличиваем количество верных ответов
//                    System.out.println("Верный ответ");
//                    correctAnswers++;
//                } else
//                    System.out.println("Не верный ответ");
//
//                // Если сейчас был последний вопрос, то скрываем все поля
//                if(nowQuestion + 1 == questions.length) {
//                    radioButton1.setVisible(false); // Скрываем все поля для выбора
//                    radioButton2.setVisible(false);
//                    radioButton3.setVisible(false);
//                    radioButton4.setVisible(false);
//                    nextButton.setVisible(false); // Скрываем кнопку ответа
//
//                    // Показываем текст в конце
//                    question_text.setText("Вы ответили корректно на " + correctAnswers + " из " + questions.length + " вопросов!");
//                } else { // Если еще есть вопросы...
//                    // Увеличиваем номер текущего вопроса
//                    nowQuestion++;
//                    // Указываем новый текст верного ответа
//                    nowCorrectAnswer = questions[nowQuestion].correctAnswer();
//
//                    // Меняем текст вопроса в программе
//                    question_text.setText(questions[nowQuestion].getQuestion());
//                    // Получаем массив ответов
//                    String[] answers = questions[nowQuestion].getAnswers();
//
//                    // Преобразовываем в список (так удобнее сортировать элементы)
//                    List<String> intList = Arrays.asList(answers);
//
//                    // Сортируем в случайном порядке
//                    Collections.shuffle(intList);
//
//                    // Подставляем ответы в радио кнопки
//                    radioButton1.setText(intList.get(0));
//                    radioButton2.setText(intList.get(1));
//                    radioButton3.setText(intList.get(2));
//                    radioButton4.setText(intList.get(3));
//
//                    // Снимаем выделение, что указал пользователь ранее
//                    selectedRadioButton.setSelected(false);
//                }
//
//            }
//        });
//
//
////        nextButton.setOnMouseClicked(event -> {
////
////            nowQuestion++;
////            nowCorrectAnswer = questions[nowQuestion].correctAnswer();
////
////            // Меняем текст вопроса в программе
////            question_text.setText(questions[nowQuestion].getQuestion());
////            // Получаем массив ответов
////            String[] answers = questions[nowQuestion].getAnswers();
////
////            // Преобразовываем в список (так удобнее сортировать элементы)
////            List<String> intList = Arrays.asList(answers);
////
////            // Сортируем в случайном порядке
////            Collections.shuffle(intList);
////
////            // Подставляем ответы в радио кнопки
////            radioButton1.setText(intList.get(0));
////            radioButton2.setText(intList.get(1));
////            radioButton3.setText(intList.get(2));
////            radioButton4.setText(intList.get(3));
////        });
//
//    }

}
