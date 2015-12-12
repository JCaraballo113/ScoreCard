package jcaraballo.com.scorecard.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import Golf.Hole;
import jcaraballo.com.scorecard.R;

/**
 * Created by John Caraballo on 12/9/2015.
 * Contact: jcaraballo019@gmail.com
 */
public class HoleAdapter extends RecyclerView.Adapter<HoleAdapter.HoleViewHolder> {

    private Hole[] mHoles;
    private Context mContext;

    public HoleAdapter(Hole[] holes, Context context) {
        mHoles = holes;
        mContext = context;
    }
    @Override
    public HoleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_list_item,parent,false);
        HoleViewHolder viewHolder = new HoleViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HoleViewHolder holder, int position) {
        holder.bindHole(mHoles[position]);
    }

    @Override
    public int getItemCount() {
        return mHoles.length;
    }

    public class HoleViewHolder extends RecyclerView.ViewHolder{

        public TextView mHoleLabel;
        public TextView mScoreLabel;
        public Button mMinusScore;
        public Button mPlusScore;

        public HoleViewHolder(View itemView) {
            super(itemView);

            mHoleLabel = (TextView) itemView.findViewById(R.id.holeLabel);
            mScoreLabel = (TextView) itemView.findViewById(R.id.scoreLabel);
            mMinusScore = (Button) itemView.findViewById(R.id.minusScore);
            mPlusScore = (Button) itemView.findViewById(R.id.plusScore);
        }

        public void bindHole(Hole hole){
            mHoleLabel.setText(hole.getHoleLabel());
            mScoreLabel.setText(hole.getScore() + "");
            final Hole h = hole;
            mMinusScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int updatedStrokeCount = h.getScore() - 1;

                    if(updatedStrokeCount < 0) updatedStrokeCount = 0;
                    h.setScore(updatedStrokeCount);
                    mScoreLabel.setText(updatedStrokeCount + "");
                }
            });

            mPlusScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int updatedStrokeCount = h.getScore() + 1;
                    h.setScore(updatedStrokeCount);
                    mScoreLabel.setText(updatedStrokeCount + "");
                }
            });
        }
    }
}
