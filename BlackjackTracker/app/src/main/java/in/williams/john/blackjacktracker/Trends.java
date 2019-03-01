package in.williams.john.blackjacktracker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
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
    }

    // Save data points in this array.
    private DataPoint[] getDataPoint() {
        // Read data from database.
        String[] columns={"date, time_spent"};

        // Make cursor traverse table.
        Cursor cursor= myDb.getAllData();

        // Make datapoint array length of num of rows in db.
        DataPoint[] dp=new DataPoint[cursor.getCount()];

        int time = 0;
        int net_gain = 0;

        // Loop through all data rows in db.
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();

            // Get time and net winnings data columns.
            // Add them to existing vars so we have a cumulative value over time.
            time += cursor.getInt(1);
            net_gain += cursor.getInt(3);

            // Add these to the data point.
            dp[i] = new DataPoint(time, net_gain);

        }

        // Return the data point array.
        return (dp);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trends, container, false);
    }
}
