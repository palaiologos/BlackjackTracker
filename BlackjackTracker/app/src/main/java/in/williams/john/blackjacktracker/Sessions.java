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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.GridLayout.LayoutParams;

public class Sessions extends Fragment {

    // Page for viewing all sessions in rows.
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the title of the page.
        getActivity().setTitle("View Sessions");
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

        // Try to populate table rows dynamically based on db rows.

        return inflater.inflate(R.layout.sessions, container, false);
    }
}
