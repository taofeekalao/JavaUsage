import dataPoints.cartesian.CartesianPoint;
import org.jetbrains.kotlinx.kandy.ir.Plot;
import org.jetbrains.kotlinx.kandy.util.color.Color;
import testloads.TestContext;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates the use of the Metric-Space-Search and the metric-space-search visualisation frameworks.
 * It uses the metric-space-search framework to generate data and uses the visualisation framework to produce visualisations.
 * The visualisation generated is saved in static image format.
 */
public class JavaUsage {

    private static final int DATA_POINTS = 500;

    /**
     * This is the main method of the Java application. It serves as the entry point into the application.
     * @param args The array of arguments passed to the application at the start of the application execution.
     * @throws Exception This is the exception from the application.
     */
    public static void main(String[] args) throws Exception {

        List<Double> distanceList = new ArrayList<>();
        List<Double> nullDistanceList = new ArrayList<>();
        TestContext testContext;
        int dimension;

        dimension = 10;
        testContext = new TestContext(TestContext.Context.euc10, DATA_POINTS + dimension);
        distanceList = computeEuclideanDistance(dimension, testContext);
        plotVisualisation(distanceList, distanceList,"Euclidean Distance_" + dimension + "_");

        dimension = 20;
        testContext = new TestContext(TestContext.Context.euc20, DATA_POINTS + dimension);
        distanceList = computeEuclideanDistance(dimension, testContext);
        plotVisualisation(distanceList, distanceList,"Euclidean Distance_" + dimension + "_");

        dimension = 30;
        testContext = new TestContext(TestContext.Context.euc30, DATA_POINTS + dimension);
        distanceList = computeEuclideanDistance(dimension, testContext);
        plotVisualisation(distanceList, distanceList,"Euclidean Distance_" + dimension + "_");

        dimension = 100;
        testContext = new TestContext(TestContext.Context.euc100, DATA_POINTS + dimension);
        distanceList = computeEuclideanDistance(dimension, testContext);
        plotVisualisation(distanceList, distanceList,"Euclidean Distance_" + dimension + "_");
    }


    /**
     * This method computes the Euclidean distances between points.
     * It instantiates an instance of TestContext class from the metric-space-search framework.
     * The instance is used to call utilities from the framework to generate cartesian products of distance points.
     * @param dimension This is the dimension of the metric-space.
     * @param tc This is an instance of the TestContext from the metric-space-search framework.
     * @return The method returns array list of doubles representing the distance values between points.
     */
    private static List<Double> computeEuclideanDistance(int dimension, TestContext tc) {
        tc.setSizes(0, dimension);
        List<Double> distanceList = new ArrayList<>();

        List<CartesianPoint> eucData = tc.getDataCopy();

        for (int i = 0; i < DATA_POINTS; i++) {
            for (int j = 0; j < DATA_POINTS; j++) {
                if (i != j) {
                    distanceList.add(tc.metric().distance(eucData.get(i), eucData.get(j)));
                }
            }
        }
        return distanceList;
    }


    /**
     * This method uses the visualisation library to plot visualisation of the generated Euclidean distance data.
     * Once the visualisation is done, the output is saved in a static image format.
     * @param xDataPoint This is the value to be plotted on the x-axis of the two-dimensional plane.
     * @param yDataPoint This is the value to be plotted on the y-axis of the two-dimensional plane.
     * @param imageName This is the image name of the plot that will be saved on the local directory.
     */
    private static void plotVisualisation(List<Double> xDataPoint, List<Double> yDataPoint, String imageName) {
        Histogram histogram = new Histogram(
                "Custom Y-Axis",
                "Custom X-Axis",
                "Custom Histogram Description",
                Color.Companion.getBLUE(),
                xDataPoint,
                yDataPoint
        );
        Plot plotHistogram = histogram.plot();
        histogram.save(plotHistogram, imageName + "Hist" + ".jpg");

        Scatter scatter = new Scatter(
                "Custom Y-Axis",
                "Custom X-Axis",
                "Custom Histogram Description",
                Color.Companion.getBLUE(),
                xDataPoint,
                yDataPoint
        );

        Plot plotScatter = scatter.plot();
        histogram.save(plotScatter, imageName + "Scat" + ".jpg");
    }
}
