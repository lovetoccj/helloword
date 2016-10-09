package com.example.providertest;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		Button button1 = (Button) findViewById(R.id.button_1);
		Button button2 = (Button) findViewById(R.id.button_2);
		Button button3 = (Button) findViewById(R.id.button_3);
		Button button4 = (Button) findViewById(R.id.button_4);
		Button button5 = (Button) findViewById(R.id.button_5);
		button5.setOnClickListener(this);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
	}

	private String newId;
	private final String providerUri = "content://com.example.providerdatabase.database.provider/book";
	private Uri uri =  Uri.parse("content://com.example.providerdatabase.database.provider/book");
	
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
//		Uri uri =  Uri.parse("content://com.example.providerdatabase.database.provider/book");
		switch (v.getId()) {
		case R.id.button_1://add
			ContentValues values = new ContentValues();
			values.put("name", "ÕÅÈý");
			values.put("price", 186.3);
			values.put("pages", 800);
			values.put("author", "ÈýÃ«");
			Uri newUri = getContentResolver().insert(uri, values);
			newId = newUri.getPathSegments().get(1);
			break;
		case R.id.button_5:
			Cursor cursor = getContentResolver().query(uri, null, null, null, null);
			if(cursor != null){
				while(cursor.moveToNext()){
					String name = cursor.getString(cursor.getColumnIndex("name"));
					String author = cursor.getString(cursor.getColumnIndex("author"));
					int id = cursor.getInt(cursor.getColumnIndex("id"));
					double price = cursor.getDouble(cursor.getColumnIndex("price"));
					System.out.println("id="+id+",name="+name+",price="+price + ",author"+author);
				}
				cursor.close();
			}
		case R.id.button_2://find
			Cursor cursor1 = getContentResolver().query(Uri.parse(providerUri +"/" + 1), null, null, null, null);
			if(cursor1 != null){
				while(cursor1.moveToNext()){
					String name = cursor1.getString(cursor1.getColumnIndex("name"));
					String author = cursor1.getString(cursor1.getColumnIndex("author"));
					int id = cursor1.getInt(cursor1.getColumnIndex("id"));
					double price = cursor1.getDouble(cursor1.getColumnIndex("price"));
					System.out.println("id="+id+",name="+name+",price="+price + ",author"+author);
				}
				cursor1.close();
			}
			break;
		case R.id.button_3://update
			Uri uri = Uri.parse(providerUri +"/" + newId);
			values = new ContentValues();
			values.put("name", "A Storm of Swords");
			values.put("pages", 123);
			values.put("price", 88.88);
			int updateId = getContentResolver().update(uri, values, null, null);
			System.out.println("updateId=" + updateId);
			break;
		case R.id.button_4://del
			int delId = getContentResolver().delete(Uri.parse(providerUri +"/" + newId), null, null);
			System.out.println("delId="+delId);
			break;

		default:
			break;
		}
	}
	
	

	
}
