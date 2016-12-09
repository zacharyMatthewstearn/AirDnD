package com.epicodus.airdd.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.airdd.R;
import com.epicodus.airdd.models.Game;
import com.epicodus.airdd.ui.GameDetailsActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {
    private static final String TAG = GameListAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<Game> mGames = new ArrayList<>();

    public GameListAdapter(Context context, ArrayList<Game> games) {
        mContext = context;
        mGames = games;
    }

    @Override
    public GameListAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_item, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                String thisGameName = ((TextView)((ViewGroup)((ViewGroup)v).getChildAt(0)).getChildAt(0)).getText().toString(); //TODO: Refactor
                Intent intent = new Intent(context, GameDetailsActivity.class);
                intent.putExtra("thisGame", Parcels.wrap(Game.findByTitle(mGames, thisGameName)));
                context.startActivity(intent);
            }
        });
        GameViewHolder viewHolder = new GameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GameListAdapter.GameViewHolder holder, int position) {
        holder.bindGame(mGames.get(position));
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.TextViewGameTitle) TextView mTextViewGameTitle;

        public GameViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindGame(Game game) {
            mTextViewGameTitle.setText(game.getTitle());
        }
    }
}
