package in.williams.john.blackjacktracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Fragment {

    Button mButtonLogout;
    UserAccountManager session;

    // Make instance of database to draw points from.
    DatabaseHelper myDb;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Settings");

        // Account session.
        session = new UserAccountManager(getContext());

        // Logout button.
        mButtonLogout = (Button) getView().findViewById(R.id.button_logout);

        // When you click the logout button, you should logout and go to login screen.
        mButtonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Clear the shared preferences first.
                session.logoutUser();

                // Then go to login page.
                Intent goToLogin = new Intent(getContext(), Login.class);
                startActivity(goToLogin);

            }
        });

        // Calculate stats based on the rows in the database.
        // Make instance of new db helper class.
        myDb = new DatabaseHelper(getActivity());

        // Populate the text views.
        populateStats();
    }

    // Function for populating TextView stats on the page.
    public void populateStats() {
        // Function gets all relevant rows.
        Cursor res = myDb.getSessionsViewData();

        TextView runningTotalView = (TextView)getView().findViewById(R.id.settings_running_total);
        TextView totalHoursView = (TextView)getView().findViewById(R.id.settings_total_hours);
        TextView shoesPlayedView = (TextView)getView().findViewById(R.id.settings_shoes_played);
        TextView moneyPerShoeView = (TextView)getView().findViewById(R.id.settings_money_per_shoe);
        TextView moneyPerHourView = (TextView)getView().findViewById(R.id.settings_money_per_hour);
        TextView totalWinsView = (TextView)getView().findViewById(R.id.settings_total_wins);
        TextView totalLossesView = (TextView)getView().findViewById(R.id.settings_total_losses);
        TextView winRateView = (TextView)getView().findViewById(R.id.settings_win_rate);


        // Stats to be calculated.
        double running_total = 0;
        double total_hours = 0;
        double total_shoes= 0;
        double total_wins = 0;
        double total_losses = 0;
        double money_per_shoe = 0;
        double money_per_hour = 0;
        double win_rate = 0;
        double sessions = 0;

        // If there are no records in the db, then everything is 0.
        if (res.getCount() == 0) {

            // Set values to 0.
            runningTotalView.setText("0");
            totalHoursView.setText("0");
            shoesPlayedView.setText("0");
            moneyPerShoeView.setText("0");
            moneyPerHourView.setText("0");
            totalWinsView.setText("0");
            totalLossesView.setText("0");
            winRateView.setText("0");

            return;
        }

        //Otherwise, get all the rows and calculate the stats.
        if (res.moveToFirst() ) {

            // Loop while there is a next value for the cursor to go to.
            do {
                int rows = res.getCount();
                sessions = res.getColumnCount();
                int cols = res.getColumnCount();

                // For every row, loop.
                for (int i = 0; i < rows; i++) {

                    // For every column, loop.
                    for (int j = 3; j < cols; j++) {

                        // Switch statement for handling col values.
                        switch (j) {
                            // Time, in minutes.
                            case 3: total_hours += res.getInt(j);
                                break;
                                // Shoes.
                            case 4: total_shoes += res.getInt(j);
                                break;
                                // Net-change.
                            case 5: running_total += res.getInt(j);
                                // Calculate wins and losses.
                                if (res.getInt(j) > 0) {
                                    total_wins++;
                                }
                                else {
                                    total_losses++;
                                }
                                break;
                            default:
                                break;
                        } // End switch.
                    } // End column loop.
                } // End row loop.


            } while(res.moveToNext() ); // End do-while.

        }

        // Calculate and set values in the views.

        // Get money per hour, convert minutes to hours, etc.
        money_per_hour = running_total / (total_hours / 60);
        money_per_shoe = running_total / total_shoes;
        total_hours = total_hours / 60;

        // Calculate win rate to avoid dividing by zero.
        if (total_wins < 1) {
            win_rate = 0;
        }
        else if (total_losses < 1) {
            win_rate = 100;
        }
        else {
            win_rate = (total_wins / (sessions) ) * 100;
        }


        // Set values.
        runningTotalView.setText("$" + Double.toString(running_total));
        totalHoursView.setText(Double.toString(total_hours));
        shoesPlayedView.setText(Double.toString(total_shoes));
        moneyPerShoeView.setText("$" + Double.toString(money_per_shoe));
        moneyPerHourView.setText("$" + Double.toString(money_per_hour));
        totalWinsView.setText(Double.toString(total_wins));
        totalLossesView.setText(Double.toString(total_losses));
        winRateView.setText(Double.toString(win_rate) + "%");

    } // End populateStats()





    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings, container, false);
    }
}
