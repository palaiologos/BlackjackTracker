package in.williams.john.blackjacktracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Settings extends Fragment {

    Button mButtonLogout;
    UserAccountManager session;

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
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings, container, false);
    }
}
