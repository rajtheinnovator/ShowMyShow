package me.abhishekraj.showmyshow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

/**
 * Created by ABHISHEK RAJ on 12/2/2016.
 */

public class MovieDetailsAdapter extends RecyclerView.Adapter<MovieDetailsAdapter.ViewHolder> {

    // Store a member variable for the movies
    private static ArrayList<Review> mReview;

    // Store the context for easy access
    private Context mContext;
    MovieDetailsBundle movieDetailsBundle;

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    /*
     Provide a direct reference to each of the views within a data item
     Used to cache the views within the item layout for fast access
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /*
        Your holder should contain a member variable
        for any view that will be set as you render a row
        */
        //public final TextView movieReviewContentTextView;
        public final TextView movieReviewAuthorTextView;
        /*
        * Make the review content expandable by using the code
        * from: @link https://github.com/Manabu-GT/ExpandableTextView
        */
        public final ExpandableTextView expTv1;
        private Context context;

        /*
        We also create a constructor that accepts the entire item row
        and does the view lookups to find each subview
        */
        public ViewHolder(Context context, View itemView) {
            /*
            Stores the itemView in a public final member variable that can be used
            to access the context from any ViewHolder instance.
            */
            super(itemView);
            //set the review content on the ExpandableTextView

            expTv1 = (ExpandableTextView) itemView.findViewById(R.id.expand_text_view);

            // movieReviewContentTextView = (TextView) itemView.findViewById(R.id.review_content);
            movieReviewAuthorTextView = (TextView) itemView.findViewById(R.id.review_author);
            this.context = context;
        }
    }

    // Pass in the movies array into the constructor
    public MovieDetailsAdapter(Context context, MovieDetailsBundle movies) {
        movieDetailsBundle = movies;
        mReview = new ArrayList<>();
        mContext = context;
    }

    @Override
    public MovieDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View moviesView = inflater.inflate(R.layout.item_movie_review, parent, false);

        // Return a new holder instance
        MovieDetailsAdapter.ViewHolder viewHolder = new MovieDetailsAdapter.ViewHolder(context, moviesView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieDetailsAdapter.ViewHolder viewHolder, int position) {
        Log.v("############", "onBindViewHolder called");
        // Get the data model based on position
        Review currentReview = mReview.get(position);
        Log.v("############", "currentReview called is " + currentReview.toString());
        Log.v("############", "currentReview's title is " + currentReview.getMovieReviewAuthor().toString());
        /*
        Set item views based on your views and data model
        TextView textView = viewHolder.movieReviewContentTextView;
        */
        viewHolder.expTv1.setText(currentReview.getMovieReviewContent());
        Log.v("############", "title is :>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + currentReview.getMovieReviewContent());
        String url = "https://image.tmdb.org/t/p/w500/" + currentReview.getMovieReviewAuthor().toString().trim();
        Log.v("############", "poster path is :>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + currentReview.getMovieReviewAuthor().toString());
        viewHolder.movieReviewAuthorTextView.setText(currentReview.getMovieReviewAuthor().trim());

    }

    @Override
    public int getItemCount() {
        Log.v("############", "getItemCount called with size " + mReview.size());
        return mReview.size();
    }

    public void setMovieDetailsBundleData(MovieDetailsBundle weatherData) {
        Log.v("############", "setMovieDetailsBundleData Called");
        movieDetailsBundle = weatherData;
        mReview = movieDetailsBundle.getReviewArrayList();
        Log.v("############", "mDefaultMovie is " + mReview);
        notifyDataSetChanged();
        Log.v("############", "notifyDataSetChanged Finished");
    }
}
