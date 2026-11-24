import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException  {

        Teacher.setAlpha(0.01f);
        Teacher.setBeta(0.5f);
        Teacher.setBias(3);
        Teacher.setDatafile("perceptron.data");
        Teacher.setDatatestfile("perceptron.test.data");
        Teacher.setNumofiterations(5);

        Teacher.work();

    }
}