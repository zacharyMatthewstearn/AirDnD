package com.epicodus.airdd.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.ui.R;

import java.util.List;

import butterknife.Bind;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.titleTextView) TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.textView_title);
        }
    }

    private List<Game> mGames;
    private Context mContext;

    public GameListAdapter(Context context, List<Game> games) {
        mGames = games;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }

    @Override
    public GameListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.activity_find_game, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameListAdapter.ViewHolder viewHolder, int position) {
        Game game = mGames.get(position);

        TextView textView = viewHolder.mTitleTextView;
        textView.setText(game.getTitle());
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }
}
