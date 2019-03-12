package in.williams.john.blackjacktracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;

public class Home extends Fragment {

    // An instance to hold the info of whoever is logged in.
    UserAccountManager session;

    TextView welcome_message;
    TextView blackjack_news;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Home");

        // Account instance.
        session = new UserAccountManager(getContext());

        // Get username from database.
        HashMap<String, String> user = session.getUserDetails();
        String username = user.get(UserAccountManager.KEY_NAME);

        // Set TextView to contain the text.
        TextView welcome_message = (TextView) getView().findViewById(R.id.home_page_welcome);
        welcome_message.setText("Hello, " + username + "!");

        // Set blackjack news link.
        Spanned policy = Html.fromHtml(getString(R.string.bj_news));
        TextView blackjack_news = (TextView)getView().findViewById(R.id.bj_news);

        blackjack_news.setText(policy);
        blackjack_news.setMovementMethod(LinkMovementMethod.getInstance());


    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }
}
