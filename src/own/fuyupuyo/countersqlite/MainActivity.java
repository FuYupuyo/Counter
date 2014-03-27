package own.fuyupuyo.countersqlite;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnMenuItemClickListener {
	private TextView mNum;
	private Num num;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		NumOpenHelper dbHelper = new NumOpenHelper(this);
		db = dbHelper.getWritableDatabase();
		Cursor c = db.rawQuery("select num from num", null);
		c.moveToFirst();
		mNum = (TextView) findViewById(R.id.text_num);
		num = new Num(c.getInt(0));
		Log.d("test", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + num.get());
		setNum();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem actionDelete = menu.findItem(R.id.action_reset);
		actionDelete.setOnMenuItemClickListener(this);
		return true;
	}

	public void setNum() {
		mNum.setText(num.toString());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			num.add();
			setNum();
		}
		return true;
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		if (item == null) {
			return false;
		}

		switch (item.getItemId()) {
		case R.id.action_reset:
			num.reset();
			setNum();
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	protected void onDestroy() {
		Log.d("test", "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + num.get());
		ContentValues values = new ContentValues();
		values.put("num", num.get());
		db.update("num", values, null, null);
		super.onDestroy();

	}
}
