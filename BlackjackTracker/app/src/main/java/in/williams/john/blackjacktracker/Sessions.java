package in.williams.john.blackjacktracker;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.GridLayout.LayoutParams;

public class Sessions extends Fragment {

    // Create instance of DatabaseHelper for showing data.
    // source: https://www.youtube.com/watch?v=p8TaTgr4uKM&index=2&list=PLS1QulWo1RIaRdy16cOzBO5Jr6kEagA07
    DatabaseHelper myDb;

    // Table Layout object.
    private TableLayout t1;

    // Page for viewing all sessions in rows.
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the title of the page.
        getActivity().setTitle("View Sessions");

        // Make instance of new db helper class.
        myDb = new DatabaseHelper(getActivity());

        // Designate table row as the one in the layout with this ID.
        t1 = (TableLayout) getView().findViewById(R.id.sessions_table_main);
        // View all for showing all db records.
        viewAll();
    }

    // As soon as page loads, will send a cursor object through the db and
    // get each column, row by row. Then place it in something to display.
    public void viewAll() {
        Cursor res = myDb.getSessionsViewData();
        if (res.getCount() == 0) {
            // Show message.
            showMessage("Error", "No data found");
            return;
        }

        // Going to get the count of all db rows.
        // If there is a first, get counts.
        // source: https://stackoverflow.com/questions/32094123/populating-table-layout-from-sqlite-database
        if (res.moveToFirst()) {

            // Do-while loop while there is a next value for cursor to go to.
            do {
                int rows = res.getCount();
                int cols = res.getColumnCount();

                // outer for loop. Loop once for each row found above.
                for (int i = 0; i < rows; i++) {
                    // Create a new table row object.
                    TableRow row = new TableRow(getActivity());
                    // Set the params to fill parent and wrap content.
                    TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(tableRowParams);

                    // Inner for loop. Loop for each data column we want to put into the layout.
                    for (int j = 0; j < cols; j++) {
                        // Make a new text view object.
                        TextView tv = new TextView(getActivity());
                        // Set it to be a table row type.
                        tv.setLayoutParams(new TableRow.LayoutParams(
                                TableRow.LayoutParams.WRAP_CONTENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                        // Set the data to gravitate to the center of the view.
                        tv.setGravity(Gravity.CENTER);
                        // Set attributes of view.
                        tv.setTextSize(18);
                        tv.setPadding(0, 5, 0 ,5);

                        // Get the text of the column we are currently on.
                        tv.setText(res.getString(j));
                        // Add the actual table row to the layout.
                        row.addView(tv);

                    } // End inner for loop.

                    // Add row to the Table Layout at the end of the outer loop.
                    t1.addView(row);

                } // End outer for loop.
            } while (res.moveToNext()); // End do-while loop.

        } // End res.moveToFirst if.









        /*
        StringBuffer buffer = new StringBuffer();

        // While going thru the database..
        while (res.moveToNext()) {
            // Store it in the buffer.
            // Gets the id column and the value at column 0. Then append newline.
            // Do this for each column.
            buffer.append("id: " + res.getString(0) + "\n");
            buffer.append("date: " + res.getString(1) + "\n");
            buffer.append("location: " + res.getString(2) + "\n");
            buffer.append("time_spent: " + res.getString(3) + "\n");
            buffer.append("num_shoes: " + res.getString(4) + "\n");
            buffer.append("buy_in: " + res.getString(5) + "\n");
            buffer.append("cash_out: " + res.getString(6) + "\n");
            // Double line break for easier readability.
            buffer.append("net_change: " + res.getString(7) + "\n\n");

        } // End while. */

        // Show all the data.
        //showMessage("Data", buffer.toString());

    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        // Set the title and message.
        builder.setTitle(title);
        builder.setMessage(message);
        // Shows the alert dialog message.
        builder.show();
    }


    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sessions, container, false);
    }
}
