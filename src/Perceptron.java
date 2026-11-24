import java.util.ArrayList;

public class Perceptron

{

    ArrayList<Float> weights;

    double bias;

    double alpha;

    double beta;

    int dimension;

    public double getBias() {

        return bias;

    }

    public double getAlpha() {

        return alpha;

    }

    public double getBeta() {

        return beta;

    }

    public void setBias(double bias) {

        this.bias = bias;

    }

    public void setAlpha(double alpha) {

        this.alpha = alpha;

    }

    public void setBeta(double beta) {

        this.beta = beta;

    }

    public Perceptron(int dimension) {

        this.dimension = dimension;

        weights = new ArrayList<>();

        for(int i = 0; i < dimension; i++)

        {

            weights.add((float) Math.random());

        }

    }

    public int compute(ArrayList<Float> input)

    {

        double wynik=0;

        for(int i = 0; i < dimension; i++)

        {

            wynik+=weights.get(i)*input.get(i);

        }

        wynik = wynik-bias;

        if(wynik<0)

            return 0;

        else

            return 1;

    }

    public void learn(ArrayList<Float> input,int decision)

    {

        int a=compute(input);

        if(a==decision)

        {

            return;

        }

        ArrayList<Float> newweights = new ArrayList<>(dimension);

        for(int i = 0; i < dimension; i++)

        {

            newweights.add((float) (weights.get(i)+(alpha*(decision-a)*input.get(i))));

            bias = bias - beta*(decision-a);

        }

        weights = newweights;

    }

}

