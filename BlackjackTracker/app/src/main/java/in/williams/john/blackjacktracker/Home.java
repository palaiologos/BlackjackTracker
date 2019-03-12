package in.williams.john.blackjacktracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class Home extends Fragment {

    // Buttons to links.
    Button blackjack_news;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Home");

        // Set blackjack news link.
        blackjack_news = (Button)getView().findViewById(R.id.bj_news);


        // On click listener for bj news button.
        blackjack_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Link to url of news.
                String url = "https://assets.bj21.com/newsletters/pdf_files/000/000/028/original/CBJN1805.pdf?1525361174";
                Intent news = new Intent(Intent.ACTION_VIEW, Uri.parse( url) );

                startActivity(news);
            }
        });

    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }
}
