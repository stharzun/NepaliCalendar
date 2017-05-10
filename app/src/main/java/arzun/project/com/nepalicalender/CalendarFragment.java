package arzun.project.com.nepalicalender;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

/**
 * Fragment containing a calendar for a particular year and month.
 */
public class CalendarFragment extends Fragment {

    private CalendarAdapter mAdapter;
    private GridView mCalendar;
    private GridView mCalendarHeaders;
    private Date mCurrentDate = new Date(2000, 1, 1);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
//
//        mCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String data=((TextView)view).getText().toString();
//                Toast.makeText(getActivity(), ""+data, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        changeTitle(view);

        // Initialize grid views

        mAdapter = new CalendarAdapter(getContext(), mCurrentDate);
        mCalendar = (GridView)view.findViewById(R.id.calendar);
        mCalendar.setAdapter(mAdapter);

        mCalendarHeaders = (GridView)view.findViewById(R.id.calendar_headers);
        mCalendarHeaders.setAdapter(new CalendarHeaderAdapter(getContext()));

        // Set vertical spacing of calendar according to display height
//
//        DisplayMetrics metrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
//
//        int spacing = (int) (metrics.heightPixels / 800f * 17f);
//        mCalendar.setVerticalSpacing(spacing);
//        mCalendarHeaders.setVerticalSpacing(spacing);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCalendar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                TextView etext=(TextView)mCalendar.findViewById(R.id.englishDate);
//                String textn=ntext.getText().toString();
//                String texte=etext.getText().toString();
//                Toast.makeText(getActivity(), " "+textn +" "+texte, Toast.LENGTH_SHORT).show();

//                Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
                    //aja ko date aako
                    Date value=(Date) mAdapter.getmToday();
                    //month ko starting date
                    Date value1=(Date) mAdapter.getmDate();
                    System.out.println(value);
                    System.out.println(value1);
//                Toast.makeText(getActivity(), ""+value1, Toast.LENGTH_SHORT).show();
//                View viewItem=mCalendar.getChildAt(position);
//                if (viewItem!=null){
//                    TextView txt=(TextView)viewItem.findViewById(R.id.nepaliDate);
//                    Toast.makeText(getActivity(), txt.toString(), Toast.LENGTH_SHORT).show();
//                }

                String value3= (String) mAdapter.getItem(position);
                Toast.makeText(getActivity(), ""+value3, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void changeTitle(View view) {
        String nepali = NepaliTranslator.getNumber(mCurrentDate.year + "")
                + " "
                + NepaliTranslator.getMonth(mCurrentDate.month);

        Date eDate1 = new Date(mCurrentDate.year, mCurrentDate.month, 1).convertToEnglish();
        Date eDate2 = new Date(mCurrentDate.year, mCurrentDate.month, 26).convertToEnglish();

        String english = getEnglishMonth(eDate1.month) + "/"
                + getEnglishMonth(eDate2.month);
        english += " " + eDate1.year + (eDate1.year==eDate2.year?"":"/"+eDate2.year);

        String monthYear = nepali + "\n" + english;
        ((TextView)view.findViewById(R.id.monthYear)).setText(monthYear);

        view.findViewById(R.id.calendar_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).toggleCalendar();
            }
        });

    }

    private static String getEnglishMonth(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.getDefault());
    }


    /**
     * Set year and month for this calendar.
     * @param year Year to display.
     * @param month Month to display.
     */
    public void set(int year, int month) {
        mCurrentDate.year = year;
        mCurrentDate.month = month;

        if (mAdapter != null)
            mAdapter.changeDate(mCurrentDate);
    }
}
