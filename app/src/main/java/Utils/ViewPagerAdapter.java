package Utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.krypto.R;
import com.squareup.picasso.Picasso;

/**
 * Created by z_x_9 on 25/7/2017.
 */

public class ViewPagerAdapter extends PagerAdapter {

    Activity activity;
    int[] images;
    String[] des;




    LayoutInflater inflater;

    public ViewPagerAdapter(Activity activity, int[] images, String[] des) {
        this.activity = activity;
        this.images = images;
        this.des = des;

    }


    @Override
    public int getCount() {
        return images.length;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.viewpager_item,container,false);
        ImageView image;
        image = (ImageView)itemView.findViewById(R.id.imageView);
        DisplayMetrics dis = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dis);
        int height = dis.heightPixels;
        int width = dis.widthPixels;
        image.setMinimumHeight(height);
        image.setMinimumWidth(width);
        TextView tv;
        tv = (TextView) itemView.findViewById(R.id.tvdes);

        ImageView larrow = (ImageView) itemView.findViewById(R.id.larrow);
        ImageView rarrow = (ImageView) itemView.findViewById(R.id.rarrow);

        int tmp = getCount()-1;

        try{
            Picasso.with(activity.getApplicationContext())

                    .load(images[position])
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(image);

                    tv.setText(des[position]);

            if (position == 0)
            {
                larrow.setVisibility(View.INVISIBLE);
            }

            if (position == tmp)
            {
                rarrow.setVisibility(View.INVISIBLE);
            }
        }

        catch (Exception ex){
        }

        container.addView(itemView);
        return itemView;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View)object);
    }
}
