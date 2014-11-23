package io.lcs.testmodel;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import org.androidannotations.annotations.Bean;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
	public ApplicationTest() {
		super(Application.class);
	}
	@Bean
	io.lcs.testmodel.greendao.TestBean d;
	public void testBean(){
		Log.i("shit",d +"");
	}
}