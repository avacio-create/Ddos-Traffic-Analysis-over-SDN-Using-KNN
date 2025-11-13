/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ddosattackdetection;

import java.io.Serializable;
import java.util.Arrays;


public class KNN<T> implements SoftClassifier<T>, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * The data structure for nearest neighbor search.
     */
    private KNNSearch<T, T> knn;
    /**
     * The labels of training samples.
     */
    private int[] y;
    /**
     * The number of neighbors for decision.
     */
    private int k;
    /**
     * The number of classes.
     */
    private int c;

    /**
     * Trainer for KNN classifier.
     */
    public static class Trainer<T> extends ClassifierTrainer<T> {
        /**
         * The number of neighbors.
         */
        private int k;
        /**
         * The distance functor.
         */
        private Distance<T> distance;

        /**
         * Constructor.
         * 
         * @param distance the distance metric functor.
         * @param k the number of neighbors.
         */
        public Trainer(Distance<T> distance, int k) {
            if (k < 1) {
                throw new IllegalArgumentException("Invalid k of k-NN: " + k);
            }
            
            this.distance = distance;
            this.k = k;
        }
        
 
        public KNN<T> train(T[] x, int[] y) {
            return new KNN<>(x, y, distance, k);
        }
    }
    
    /**
     * Constructor.
     * @param knn k-nearest neighbor search data structure of training instances.
     * @param y training labels in [0, c), where c is the number of classes.
     * @param k the number of neighbors for classification.
     */
    public KNN(KNNSearch<T, T> knn, int[] y, int k) {
        this.knn = knn;
        this.k = k;
        this.y = y;
        
        // class label set.
        int[] labels = y;
        Arrays.sort(labels);
        
        for (int i = 0; i < labels.length; i++) {
            if (labels[i] < 0) {
                throw new IllegalArgumentException("Negative class label: " + labels[i]); 
            }
            
            if (i > 0 && labels[i] - labels[i-1] > 1) {
                throw new IllegalArgumentException("Missing class: " + labels[i]+1);                 
            }
        }

        c = labels.length;
        if (c < 2) {
            throw new IllegalArgumentException("Only one class.");            
        }
    }

    /**
     * Constructor. By default, this is a 1-NN classifier.
     * @param x training samples.
     * @param y training labels in [0, c), where c is the number of classes.
     * @param distance the distance measure for finding nearest neighbors.
     */
    public KNN(T[] x, int[] y, Distance<T> distance) {
        this(x, y, distance, 1);
    }

    /**
     * Learn the K-NN classifier from data of any generalized type with a given
     * distance definition.
     * @param k the number of neighbors for classification.
     * @param x training samples.
     * @param y training labels in [0, c), where c is the number of classes.
     * @param distance the distance measure for finding nearest neighbors.
     */
    public KNN(T[] x, int[] y, Distance<T> distance, int k) {
        if (x.length != y.length) {
            throw new IllegalArgumentException(String.format("The sizes of X and Y don't match: %d != %d", x.length, y.length));
        }

        if (k < 1) {
            throw new IllegalArgumentException("Illegal k = " + k);
        }
        
        // class label set.
        int[] labels = y;
        Arrays.sort(labels);
        
        for (int i = 0; i < labels.length; i++) {
            if (labels[i] < 0) {
                throw new IllegalArgumentException("Negative class label: " + labels[i]); 
            }
            
            if (i > 0 && labels[i] - labels[i-1] > 1) {
                throw new IllegalArgumentException("Missing class: " + labels[i]+1);                 
            }
        }

        c = labels.length;
        if (c < 2) {
            throw new IllegalArgumentException("Only one class.");            
        }
        
        this.y = y;
        this.k = k;

    }

    /**
     * Learn the 1-NN classifier from data of type double[].
     * @param x the training samples.
     * @param y training labels in [0, c), where c is the number of classes.
     */
    public static KNN<double[]> learn(double[][] x, int[] y) {
        return learn(x, y, 1);
    }

    /**
     * Learn the K-NN classifier from data of type double[].
     * @param k the number of neighbors for classification.
     * @param x training samples.
     * @param y training labels in [0, c), where c is the number of classes.
     */
    public static KNN<double[]> learn(double[][] x, int[] y, int k) {
        if (x.length != y.length) {
            throw new IllegalArgumentException(String.format("The sizes of X and Y don't match: %d != %d", x.length, y.length));
        }

        if (k < 1) {
            throw new IllegalArgumentException("Illegal k = " + k);
        }

        KNNSearch<double[], double[]> knn = null;
    
        return new KNN<>(knn, y, k);
    }

       public static void learn(double[] x1, int[] y1, int k1) {
 

        KNNSearch<double[], double[]> knn = null;
 
    }

    public int predict(T x) {
        return predict(x, null);
    }


    public int predict(T x, double[] posteriori) {
        if (posteriori != null && posteriori.length != c) {
            throw new IllegalArgumentException(String.format("Invalid posteriori vector size: %d, expected: %d", posteriori.length, c));
        }

     

        int[] count = new int[c];
 
        if (posteriori != null) {
            for (int i = 0; i < c; i++) {
                posteriori[i] = (double) count[i] / k;
            }
        }
        
        int max = 0;
        int idx = 0;
        for (int i = 0; i < c; i++) {
            if (count[i] > max) {
                max = count[i];
                idx = i;
            }
        }

        return idx;
    }
}
