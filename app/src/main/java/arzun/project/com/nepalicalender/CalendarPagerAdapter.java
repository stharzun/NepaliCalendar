package arzun.project.com.nepalicalender;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Pager adapter for displaying all supported calendar months.
 */
public class CalendarPagerAdapter extends FragmentStatePagerAdapter {
    int type;

    public CalendarPagerAdapter(FragmentManager manager, int type) {
        super(manager);
        this.type = type;
    }

    @Override
    public Fragment getItem(int position) {
        int year = position/12 + DateUtils.startNepaliYear;
        int month = position%12 + 1;

        CalendarFragment cf = new CalendarFragment();
        cf.set(year, month);
        return cf;

    }

    @Override
    public int getCount() {
        return 91*12;
    }
}
