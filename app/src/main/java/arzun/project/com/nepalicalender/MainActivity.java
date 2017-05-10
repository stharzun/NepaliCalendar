package arzun.project.com.nepalicalender;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager= (ViewPager) findViewById(R.id.calendarPager);
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(new CalendarPagerAdapter(getSupportFragmentManager(),0));

        Date today=new Date(Calendar.getInstance()).convertToNepali();
        viewPager.setCurrentItem(
                (today.year-DateUtils.startNepaliYear)*12 +(today.month-1)
        );

        LinearLayout todayLayout=(LinearLayout)findViewById(R.id.today);
        TextView todayNepaliDate=(TextView)todayLayout.findViewById(R.id.today_date_nepali);
        TextView todayNepaliMonthYear=(TextView)todayLayout.findViewById(R.id.today_month_year_nepali);
        TextView todayEnglishDate=(TextView)todayLayout.findViewById(R.id.today_date_english);

        if (todayNepaliDate!=null)
            todayNepaliDate.setText(NepaliTranslator.getNumber(today.day+""));

        if (todayNepaliMonthYear!=null){
            String nepali=NepaliTranslator.getMonth(today.month)+". "
                    +NepaliTranslator.getNumber(today.year+"");
            todayNepaliMonthYear.setText(nepali);
        }
        if (todayEnglishDate!=null){
            String english=new SimpleDateFormat("d MMMM, yyyy", Locale.US)
                    .format(Calendar.getInstance().getTime());
            todayEnglishDate.setText(english);
        }
    }

    private int currentType = 0;
    public void toggleCalendar() {
        currentType = 1 - currentType;

        if (viewPager != null) {
            int currentItem = viewPager.getCurrentItem();
            viewPager.setAdapter(new CalendarPagerAdapter(getSupportFragmentManager(),
                    currentType));
            viewPager.setCurrentItem(currentItem);

        }
    }
}
