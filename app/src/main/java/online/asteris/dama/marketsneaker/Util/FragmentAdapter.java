package online.asteris.dama.marketsneaker.Util;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import online.asteris.dama.marketsneaker.CekresiFragment;
import online.asteris.dama.marketsneaker.ItemFragment;
import online.asteris.dama.marketsneaker.KontakFragment;
import online.asteris.dama.marketsneaker.R;

/**
 * Created by dama on 09/09/2017.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    private String[] titles = {"ITEM", "KONTAK", "CEK TRANSAKSI"};
    int[] icons = new int[]{ R.drawable.ic_menu_camera, R.drawable.ic_menu_manage, R.drawable.ic_menu_share};
    private int heightIcon;

    public FragmentAdapter(FragmentManager fm, Context c) {
        super(fm);

        context = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon=(int) (24*scale+0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment_page = null;

        if (position == 0) { fragment_page = new ItemFragment();}
        else if (position == 1) { fragment_page = new KontakFragment();}
        else if (position == 2) { fragment_page = new CekresiFragment();}

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment_page.setArguments(bundle);
        return fragment_page;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ( titles[position]);
    }
}
