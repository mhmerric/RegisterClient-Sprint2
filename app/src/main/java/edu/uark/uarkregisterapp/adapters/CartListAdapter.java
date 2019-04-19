package edu.uark.uarkregisterapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import edu.uark.uarkregisterapp.R;
import edu.uark.uarkregisterapp.Item;

public class CartListAdapter extends ArrayAdapter<Item> {
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.getContext());
            view = inflater.inflate(R.layout.list_view_shopping_cart, parent, false);
        }

        Item item = this.getItem(position);
        if (item != null) {
            TextView lookupCodeTextView = (TextView) view.findViewById(R.id.tv_name);
            if (lookupCodeTextView != null) {
                lookupCodeTextView.setText(item.getItemName());
            }

            TextView countTextView = (TextView) view.findViewById(R.id.tv_total);
            if (countTextView != null) {
                countTextView.setText(item.getPrice());
            }

            TextView quantityTextView = (TextView) view.findViewById(R.id.tv_quantity);
            if (quantityTextView != null) {
                quantityTextView.setText(item.getQuantity());
            }
        }

        return view;
    }

    public CartListAdapter(Context context, List<Item> items) {
        super(context, R.layout.list_view_shopping_cart, items);
    }
}







/*package edu.uark.uarkregisterapp.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.uark.uarkregisterapp.R;
import edu.uark.uarkregisterapp.Item;

public class CartListAdapter extends RecyclerView.Adapter{
    private List callListResponses = new ArrayList<Item>();
    final List templist=new ArrayList<>();
    private Activity context;
    int lastPosition=0;

    public CartListAdapter(Activity context, List callListResponses)
    {
        super();
        this.context = context;
        this.callListResponses=callListResponses;
    }

    //@Override
    //public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
    //}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_view_shopping_cart, parent, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // Casted to "Item"?
        final Item call = (Item) callListResponses.get(position);

        holder.itemname.setText(call.getItemName());
        holder.itemprice.setText(call.getRate()+" Rs");
        holder.itemsize.setText(call.getSize());
        holder.tv_quantity.setText(call.getQuantity());

        //holder.cart_minus_img.setOnClickListener(new QuantityListener(context, holder.tv_quantity,call,false));
        //holder.cart_plus_img.setOnClickListener(new QuantityListener(context, holder.tv_quantity,call,true));
        //holder.img_deleteitem.setOnClickListener(new DeleteItemListener(context,call,this));
    }

    //Animating single element
    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition) {
            //Animation animation = AnimationUtils.loadAnimation(context, R.anim.push_right_in);
            //viewToAnimate.startAnimation(animation);
            lastPosition=position;
        }
        position++;
    }

    @Override
    public int getItemCount() {
        //Log.d("Size List:",String.valueOf(callListResponses.size()));
        if(callListResponses!=null){
            return callListResponses.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView itemprice,itemname, itemsize,tv_quantity;
        ImageView cart_minus_img, cart_plus_img,img_deleteitem;


        public ViewHolder(View itemView) {
            super(itemView);
            cart_minus_img=(ImageView) itemView.findViewById(R.id.cart_minus_img);
            cart_plus_img=(ImageView) itemView.findViewById(R.id.cart_plus_img);
            img_deleteitem=(ImageView) itemView.findViewById(R.id.img_deleteitem);
            itemname=(TextView) itemView.findViewById(R.id.itemname);
            itemprice=(TextView) itemView.findViewById(R.id.itemprice);
            itemsize=(TextView) itemView.findViewById(R.id.itemsize);
            tv_quantity=(TextView) itemView.findViewById(R.id.tv_quantity);

        }
    }
}
*/