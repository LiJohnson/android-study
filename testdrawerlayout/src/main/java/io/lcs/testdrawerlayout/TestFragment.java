package io.lcs.testdrawerlayout;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lcs on 15-1-4.
 */
public class TestFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_test, null);
	}

	public static class LcsView extends TextView{

		public LcsView(Context context) {
			super(context);
		}

		public LcsView(Context context, AttributeSet attrs) {
			super(context, attrs);
		}

		public LcsView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
		}

		@TargetApi(Build.VERSION_CODES.LOLLIPOP)
		public LcsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
			super(context, attrs, defStyleAttr, defStyleRes);
		}

	}
}
