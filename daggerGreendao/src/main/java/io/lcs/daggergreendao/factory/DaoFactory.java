package io.lcs.daggergreendao.factory;

import android.content.Context;

import io.lcs.daggergreendao.dao.DaoMaster;
import io.lcs.daggergreendao.dao.DaoSession;

/**
 * Created by lcs on 14-11-22.
 */
public class DaoFactory {
	private static final String DB_NAME = "dd-db";
	private  DaoSession daoSession;

	public DaoFactory( Context context){
		DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DB_NAME, null);
		android.database.sqlite.SQLiteDatabase db = helper.getWritableDatabase();
		DaoMaster daoMaster = new DaoMaster(db);
		this.daoSession = daoMaster.newSession();
	}

	public DaoSession getDaoSession(){
		return this.daoSession;
	}
}
