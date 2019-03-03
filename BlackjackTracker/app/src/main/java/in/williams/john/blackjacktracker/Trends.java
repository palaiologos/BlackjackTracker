package in.williams.john.blackjacktracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Trends extends Fragment {

    // Make instance of database to draw points from.
    DatabaseHelper myDb;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Trends");

        // Make instance of new db helper class.
        myDb = new DatabaseHelper(getActivity());

        GraphView graphView = (GraphView) getView().findViewById(R.id.graph);

        // Set 'series' to be a line graph with data points from array.
        LineGraphSeries<DataPoint> series=new LineGraphSeries<>(getDataPoint());

        // Add our line graph to our graph view object.
        graphView.addSeries(series);

        // Set graph title.
        // source: http://www.android-graphview.org/style-options/
        graphView.setTitle("Wins/Losses");
        graphView.setTitleTextSize(70);

        // Make the legend for the graph.
        // source: https://www.youtube.com/watch?v=4NYljUle2u4&list=PLFh8wpMiEi88ojfNpavGpMB0dtP4mvEqa&index=17
        series.setTitle("Running Total $");
        graphView.getLegendRenderer().setVisible(true);

        // Set manual graph bounds for x axis.
        // source: https://www.youtube.com/watch?v=Lnm6YG8Ub50&list=PLFh8wpMiEi88ojfNpavGpMB0dtP4mvEqa&index=11
        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(1);

        // Make the graph scrollable.
        graphView.getViewport().setScrollable(true);
        // Make Y axis scrollable.
        graphView.getViewport().setScrollableY(true);

        // Make graph zoomable, x and y axis.
        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScalableY(true);

        // Align legend to bottom.
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


        // Make labels for the x and y axis on the graph.
        graphView.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter()
        {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                // Check if it is the x axis.

                if (isValueX){
                    // Return the value of the x axis.
                    return super.formatLabel(value, isValueX);
                    // Y axis is money.
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });

    }

    // Save data points in this array.
    private DataPoint[] getDataPoint() {
        // Read data from database.
        String[] columns={"date, time_spent"};

        // Make cursor traverse table.
        Cursor cursor= myDb.getAllData();

        // Make datapoint array length of num of rows in db.
        DataPoint[] dp=new DataPoint[cursor.getCount()];

        int session = 0;
        int net_gain = 0;

        // Loop through all data rows in db.
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();

            // Get time and net winnings data columns.
            // Add them to existing vars so we have a cumulative value over time.
            session ++;
            net_gain += cursor.getInt(7);

            // Add these to the data point.
            dp[i] = new DataPoint(session, net_gain);

        }

        // Return the data point array.
        return (dp);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trends, container, false);
    }
}
