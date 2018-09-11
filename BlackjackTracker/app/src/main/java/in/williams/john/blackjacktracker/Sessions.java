package in.williams.john.blackjacktracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sessions extends Fragment {

    // Create instance of DatabaseHelper for showing data.
    // source: https://www.youtube.com/watch?v=p8TaTgr4uKM&index=2&list=PLS1QulWo1RIaRdy16cOzBO5Jr6kEagA07
    DatabaseHelper myDb;
    // Create variables for edit text buttons.
    EditText editDate, editLocation, editTime, editShoes, editBuyIn, editCashOut;
    Button btnAddData;




    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Sessions");

        // Had to use getActivity() instead of 'this' because it is a fragment.
        // source: https://stackoverflow.com/questions/12229434/databasehelper-not-taking-context-of-listfragment
        myDb = new DatabaseHelper(getActivity());

        // Have to call getView() function to get the view of the fragment first.
        // source: https://www.codeproject.com/Questions/1000137/How-do-I-get-rid-of-the-the-cannot-resolve-method
        editDate = (EditText) getView().findViewById(R.id.editText_date);
        editLocation = (EditText) getView().findViewById(R.id.editText_location);
        editTime = (EditText) getView().findViewById(R.id.editText_time);
        editShoes = (EditText) getView().findViewById(R.id.editText_shoes);
        editBuyIn = (EditText) getView().findViewById(R.id.editText_buy_in);
        editCashOut = (EditText) getView().findViewById(R.id.editText_cash_out);
        btnAddData = (Button) getView().findViewById(R.id.button_add);

        // Call method inside onCreate() so it is called when clicked.
        addData();



    }

    // Method for buttons.
    public void addData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Uses method from DatabaseHelper.java to add data.
                        // Use ParseInt to get numeric data. Make it a bool so we can tell if it worked or not.
                        // source: https://stackoverflow.com/questions/4903515/how-do-i-return-an-int-from-edittext-android
                        boolean isInserted = myDb.insertData(editDate.getText().toString(), editLocation.getText().toString(), Integer.parseInt(editTime.getText().toString()), Integer.parseInt(editShoes.getText().toString()),
                                Integer.parseInt(editBuyIn.getText().toString()), Integer.parseInt(editCashOut.getText().toString()) );

                        // Output the results for debugging based on whether it worked or not.
                        if (isInserted) {
                            // Use getActivity() instead of 'this' for the context objects, because it is a fragment.
                            // source: getActivity()
                            Toast.makeText(getActivity(), "Data Inserted Successfully", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getActivity(), "Data Insertion Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sessions, container, false);


    }
}
