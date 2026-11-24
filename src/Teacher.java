import java.io.BufferedReader;

import java.io.FileReader;

import java.io.IOException;

import java.util.*;

public class Teacher {

    static float alpha = 0.0f;

    static float beta = 0.0f;

    static float bias = 0.0f;

    static String datafile;

    static String datatestfile;

    static int numofiterations = 0;

    public static void setNumofiterations(int numofiterations) {

        Teacher.numofiterations = numofiterations;

    }

    public static void setAlpha(float alpha) {

        Teacher.alpha = alpha;

    }

    public static void setBeta(float beta) {

        Teacher.beta = beta;

    }

    public static void setBias(float bias) {

        Teacher.bias = bias;

    }

    public static void setDatafile(String datafile) {

        Teacher.datafile = datafile;

    }

    public static void setDatatestfile(String datatestfile) {

        Teacher.datatestfile = datatestfile;

    }

    public static void work() throws IOException {

        ArrayList<TestRecord> records = new ArrayList<>();

        int dimension = 0;

        String PropertyThatActsAsOne = "";

        {

            FileReader fr = new FileReader(datafile);

            BufferedReader br = new BufferedReader(fr);

            while (br.ready()) {

                String[] line = br.readLine().split(",");

                String key = line[line.length - 1];

                if (PropertyThatActsAsOne.isEmpty()) {

                    PropertyThatActsAsOne = key;

                    dimension = line.length - 1;

                }

                ArrayList<Float> list = new ArrayList<Float>();

                for (int i = 0; i < line.length - 1; i++) {

                    list.add(Float.parseFloat(line[i]));

                }

                TestRecord record = new TestRecord(key, list);

                records.add(record);

            }

            for (var entry : records) {

                //System.out.println("Key: " + entry.name + ", Value: " + entry.inputs);

            }

            Collections.shuffle(records);

            for (var entry : records) {

                //System.out.println("Key: " + entry.name + ", Value: " + entry.inputs);

            }

        }

        HashMap<String, ArrayList<ArrayList<Float>>> map2 = new HashMap<>();

        {

            FileReader fr = new FileReader(datatestfile);

            BufferedReader br = new BufferedReader(fr);

            while (br.ready()) {

                String[] line = br.readLine().split(",");

                ArrayList<Float> list = new ArrayList<Float>();

                for (int i = 0; i < line.length - 1; i++) {

                    list.add(Float.parseFloat(line[i]));

                }

                map2.computeIfAbsent(line[line.length - 1], k -> new ArrayList<>()).add(list);

            }

           /* for (var entry : map2.entrySet()) {

                String key = entry.getKey();

                ArrayList<ArrayList<Float>> value = entry.getValue();

                System.out.println("Key: " + key + ", Value: " + value);

            }*/

        }

        Perceptron lala = new Perceptron(dimension);

        lala.setAlpha(alpha);

        lala.setBeta(beta);

        lala.setBias(bias);

        for(int i = 0; i < numofiterations; i++) {

            for (var entry : records) {

                String key = entry.name;

                ArrayList<Float> value = entry.inputs;

                int decision = key.equals(PropertyThatActsAsOne) ? 1 : 0;

                int wynik = -1;

                //System.out.println("now cheking: " + value);

                while (wynik != decision) {

                    //System.out.println(wynik);

                    wynik = lala.compute(value);

                    //System.out.println("wyszlo " + wynik + " powinno wyjsc: " + decision);

                    //System.out.println(lala.weights);

                    lala.learn(value, decision);

                    //System.out.println(lala.weights);

                }

            }

        }

        float accuracy = 0f;

        int all=0;

        int hit=0;

        for(var entry : map2.entrySet()) {

            String key = entry.getKey();

            ArrayList<ArrayList<Float>> value = entry.getValue();

            for(int j = 0; j < value.size(); j++) {

                int decision = key.equals(PropertyThatActsAsOne) ? 1 : 0;

                ArrayList<Float> list = value.get(j);

                System.out.println(list+" "+decision+" "+lala.compute(list));

                if(lala.compute(list)==decision) {

                    hit++;

                }

                all++;

            }

        }

        accuracy = (float)(hit/(float)all);

        System.out.println("Accuracy: " + accuracy*100 + "%");

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("1: add new point");

            System.out.println("2: exit");

            int a=sc.nextInt();

            if(a==2)

                break;

            ArrayList<Float> pom = new ArrayList<>();

            for(int i = 0; i < dimension; i++) {

                pom.add(sc.nextFloat());

            }

            String result = lala.compute(pom) == 1 ? PropertyThatActsAsOne : "something else";

            System.out.println("point: "+pom+" result: "+result);

        }

    }

}

