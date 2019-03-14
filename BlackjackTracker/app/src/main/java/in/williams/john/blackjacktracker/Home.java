package in.williams.john.blackjacktracker;

import android.content.ClipData;
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
    Button how_to_play;
    Button chart;
    Button strategy;
    Button counting_cards;

    UserAccountManager session;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Home");

        // Account session.
        session = new UserAccountManager(getContext());

        // Set buttons to link with their views.
        blackjack_news = (Button)getView().findViewById(R.id.bj_news);
        how_to_play = (Button)getView().findViewById(R.id.basic_rules);
        chart = (Button)getView().findViewById(R.id.bj_chart);
        strategy = (Button)getView().findViewById(R.id.basic_strategy);
        counting_cards = (Button)getView().findViewById(R.id.counting);


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

        // On click listener for basic rules button.
        how_to_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Link to url.
                String url = "https://www.888casino.com/blog/blackjack-strategy-guide/how-to-play-blackjack";
                Intent how_to_play = new Intent(Intent.ACTION_VIEW, Uri.parse( url) );

                startActivity(how_to_play);
            }
        });

        // On click listener for charts button.
        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Link to url.
                String url = "https://www.888casino.com/blog/blackjack-strategy-guide/blackjack-charts";
                Intent charts = new Intent(Intent.ACTION_VIEW, Uri.parse( url) );

                startActivity(charts);
            }
        });

        // On click listener for strategy button.
        strategy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Link to url.
                String url = "https://www.888casino.com/blog/blackjack-strategy-guide/basic-blackjack-strategy";
                Intent strat = new Intent(Intent.ACTION_VIEW, Uri.parse( url) );

                startActivity(strat);
            }
        });

        // On click listener for counting button.
        counting_cards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Link to url.
                String url = "https://www.888casino.com/blog/blackjack-strategy-guide/blackjack-card-counting";
                Intent counting = new Intent(Intent.ACTION_VIEW, Uri.parse( url) );

                startActivity(counting);
            }
        });




    }






    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }
}
