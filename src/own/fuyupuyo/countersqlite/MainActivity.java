package own.fuyupuyo.countersqlite;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
		setView();
		getNumFromDB();
		setNumText();
	}

	private void setView() {
		setContentView(R.layout.activity_main);
		mNum = (TextView) findViewById(R.id.text_num);
	}

	private void getNumFromDB() {
		NumOpenHelper dbHelper = new NumOpenHelper(this);
		db = dbHelper.getWritableDatabase();
		Cursor c = db.rawQuery("select num from num", null);
		c.moveToFirst();
		num = new Num(c.getInt(0));
	}

	public void setNumText() {
		mNum.setText(num.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuItem actionDelete = menu.findItem(R.id.action_reset);
		actionDelete.setOnMenuItemClickListener(this);
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			num.add();
			setNumText();
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
			setNumText();
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	protected void onStop() {
		ContentValues values = new ContentValues();
		values.put("num", num.get());
		db.update("num", values, null, null);
		super.onDestroy();

	}
}
