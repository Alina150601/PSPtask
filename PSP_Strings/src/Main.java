import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    /*необходимо выполнять операции над объектами типа String, содержащимися в коллекции ArrayList.
1. Добавление и удаление объектов.
2. Поиск одинаковых элементов с подсчетом совпадений
3. Выгрузка в xml-файл.
4. Реверс всех строк, входящих в коллекцию
5. Статистика по всем символам, содержащимся в строках коллекции
6. Поиск подстроки в строках коллекции
7. Инициализация листа по текстовому файлу и вывод содержимого коллекции на экран.
8. Расширить функциональность класса ArrayList методом compareInnerObjects ( int firstIndex, int secondIndex )
9. Посчитать длины срок входящих в коллекцию, и вывести результат в упорядоченном виде.
10. Реализовать возможность добавления в динамическую коллекцию, как если бы она была статической размерности, т.е. задаем статическую размерность коллекции, пока количество объектов меньше заданной размерности, происходит только добавление объектов, при достижении порогового значения, добавление нового элемента вызывает удаление первого элемента коллекции. Проверить, компилируется ли данный проект, прежде чем записывать его в репозиторий.
На выходе - отчёт со скринами и небольшим описанием + исходники разработанного кода*/

    public static void main(String[] args) throws IOException {

        ArrayList<String> list = new ArrayList<>();

        // 1. Добавление и удаление объектов
        list.add("string to delete");
        for(int i = 0; i < 2; i++) {
            list.add("one");
            list.add("two");
            list.add("three");
            list.add("four");
            list.add("five");
            list.add("six");
        }
        list.add("six");
        list.remove("string to delete");

        // 2. Поиск одинаковых элементов с подсчетом совпадений
        ArrayList<String> uniqElements = (ArrayList<String>) list.stream().distinct().collect(Collectors.toList());
        int twinsCount = list.size() - uniqElements.size();

        // 3. Выгрузка в xml-файл
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream("strings.xml")));
        e.writeObject(list);
        e.close();

        // 4. Реверс всех строк, входящих в коллекцию
        ArrayList<String> reverseStrings = (ArrayList<String>) list.stream()
                .map(s -> new StringBuilder(s)
                        .reverse()
                        .toString())
                .collect(Collectors.toList());

        // 5. Статистика по всем символам, содержащимся в строках коллекции
        List<List<Character>> charLists = list.stream()
                .map(s -> s
                        .chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        List<Character> allChars = charLists.stream().flatMap(List::stream).collect(Collectors.toList());
        List<Character> uniqChars = allChars.stream().distinct().collect(Collectors.toList());
        long[] occurrences = new long[uniqChars.size()];
        for (int i = 0; i < uniqChars.size(); i++) {
            Character currentChar = uniqChars.get(i);
            occurrences[i] =  allChars.stream().filter(c -> c == currentChar).count();
        }
        System.out.println("The character '" + uniqChars.get(5) + "' was used " + occurrences[5] + " times.");
        System.out.println("The character '" + uniqChars.get(6) + "' was used " + occurrences[6] + " times.");
        System.out.println("The character '" + uniqChars.get(7) + "' was used " + occurrences[7] + " times.");

        // 6. Поиск подстроки в строках коллекции
        String substring = "ree";
        ArrayList<String> containsSubstring = (ArrayList<String>) list.stream()
                .filter(s -> s.contains(substring))
                .collect(Collectors.toList());

        // 7. Инициализация листа по текстовому файлу и вывод содержимого коллекции на экран.
        ArrayList<String> listFromFile = new ArrayList<>();
        File file = new File("text.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        while ((str = br.readLine()) != null) {
            listFromFile.add(str);
        }
        for (String strFromCollection : listFromFile) {
            System.out.println(strFromCollection);
        }

        // 8. Расширить функциональность класса ArrayList методом compareInnerObjects ( int firstIndex, int secondIndex )
        EgorAlinaArrayList<String> extendedList = new EgorAlinaArrayList<>();
        extendedList.add("string that duplicates");
        extendedList.add("another string");
        extendedList.add("string that duplicates");
        boolean result = extendedList.compareInnerObjects(0, 2);
        System.out.println("Here should be true: " + result);

        // 9. Посчитать длины строк входящих в коллекцию, и вывести результат в упорядоченном виде.
        System.out.println("Strings lengths:");
        List<Integer> lengths = list.stream().map(String::length).collect(Collectors.toList());
        // мы не уверены, что правильно поняли, но видимо надо отсортировать длины по возрастанию
        lengths.stream().sorted().forEach(System.out::println);

        // 10. Реализовать возможность добавления в динамическую коллекцию, как если бы она была статической
        // размерности, т.е. задаем статическую размерность коллекции, пока количество объектов меньше заданной
        // размерности, происходит только добавление объектов, при достижении порогового значения, добавление нового
        // элемента вызывает удаление первого элемента коллекции. Проверить, компилируется ли данный проект, прежде
        // чем записывать его в репозиторий.
        EgorAlinaDynamicStaticCollection<String> staticList = new EgorAlinaDynamicStaticCollection<>(3);
        staticList.add("this line will be removed");
        staticList.add("first string");
        staticList.add("second string");
        staticList.add("third string");
        for (int i = 0; i < staticList.size(); i++) {
            System.out.println(staticList.get(i));
        }
    }
}
