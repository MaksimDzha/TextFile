import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {


    //Конструктор для чтения файлов с разбивкой по регулярному выражению
    public TextFile(String filename, String splitter){
        super(Arrays.asList(read(filename).split(splitter)));
        if (get(0).equals("")) remove(0);
    }

    //Конструктор для обычного чтения по строкам
    public TextFile(String filename){
        this(filename, "\n");
    }

    //Чтение файла как одной строки
    public static String read(String filename){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(filename).getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null){
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return sb.toString();
    }

    //Запись текста в файл одним вызовом метода
    public static void write(String filename, String text){
        try {
            PrintWriter out = new PrintWriter(new File(filename).getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    //Запись в файл текста, хранящегося в текущем объекте
    public void write(String filename){
        try {
            PrintWriter out = new PrintWriter(new File(filename).getAbsoluteFile());
            try {
                for(String item: this)
                    out.println(item);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    //Тест
    public static void main(String[] args) {
        String file = read("TextFile.java");
        write("test.txt", file);
        TextFile text = new TextFile("test.txt");
        text.write("test2.txt");

        TreeSet<String> words = new TreeSet<>(new TextFile("TextFile.java", "\\W+"));
//        TreeSet<String> words = new TreeSet<>(new TextFile("TextFile.java"));
        System.out.println(words.headSet("a"));
//        System.out.println(words);
    }
}
