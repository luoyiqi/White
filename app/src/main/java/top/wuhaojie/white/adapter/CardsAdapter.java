package top.wuhaojie.white.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import top.wuhaojie.white.R;
import top.wuhaojie.white.entities.IMusicItem;
import top.wuhaojie.white.entities.impl.MusicItemImpl;
import top.wuhaojie.white.view.SwitchView;

/**
 * Created by wuhaojie on 2016/7/4 14:27.
 */
public class CardsAdapter extends RecyclerView.Adapter {

    private List<? extends IMusicItem> mItems;
    private Context mContext;
    private final LayoutInflater mInflater;

    public interface OnCardClickListener {
        void onClick(String tag, SwitchView view);
    }

    private OnCardClickListener mOnCardClickListener;

    public CardsAdapter(Context context, List<? extends IMusicItem> items, OnCardClickListener listener) {
        mItems = items;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mOnCardClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_rain, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CardViewHolder cardViewHolder = (CardViewHolder) holder;
        MusicItemImpl item = (MusicItemImpl) mItems.get(position);
        cardViewHolder.mTitleView.setText(item.getTitle());
        cardViewHolder.mIconView.setImageResource(item.getIconResId());
        cardViewHolder.mSwitchView.setLevel(item.getLevel());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleView;
        ImageView mIconView;
        SwitchView mSwitchView;

        public CardViewHolder(View itemView) {
            super(itemView);
            mTitleView = (TextView) itemView.findViewById(R.id.tv_title);
            mIconView = (ImageView) itemView.findViewById(R.id.iv_icon_card);
            mSwitchView = (SwitchView) itemView.findViewById(R.id.switch_view_card);
            itemView.setOnClickListener((view) -> mOnCardClickListener.onClick(mItems.get(getAdapterPosition()).getTag(), mSwitchView));
        }
    }

}
