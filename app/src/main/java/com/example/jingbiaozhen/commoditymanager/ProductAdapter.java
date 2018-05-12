package com.example.jingbiaozhen.commoditymanager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Administrator on 2018/5/12.
 * 商品列表适配器
 **/
class ProductAdapter extends BaseAdapter implements Filterable {


    private List<Product> mProducts;
    private List<Product> mRealProducts;
    private Context mContext;

    public ProductAdapter(Context context,List<Product> products) {
        mContext=context;
        mProducts=products;
        mRealProducts=products;
    }

    @Override
    public int getCount() {
        return mProducts.size();
    }

    @Override
    public Object getItem(int position) {
        return mProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_product, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.product_iv);
            holder.textView = convertView.findViewById(R.id.product_desc_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Product product = mProducts.get(position);
        holder.imageView.setImageResource(product.image);
        holder.textView.setText(product.desc);

        return convertView;
    }
    @Override
    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                constraint = constraint.toString().toLowerCase();
                FilterResults result = new FilterResults();

                if (constraint != null && constraint.toString().length() > 0) {
                    List<Product> founded = new ArrayList<Product>();
                    for (Product item : mProducts) {
                        if (item.desc.toLowerCase().contains(constraint)) {
                            founded.add(item);
                        }
                    }

                    result.values = founded;
                    result.count = founded.size();
                } else {
                    result.values = mRealProducts;
                    result.count = mRealProducts.size();
                }
                return result;


            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mProducts.clear();
                for (Product item : (List<Product>) results.values) {
                    mProducts.add(item);
                }
                notifyDataSetChanged();

            }

        };
    }
    class ViewHolder
    {
        ImageView imageView;

        TextView textView;

    }
}
