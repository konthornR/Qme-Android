package com.example.testapp;

import java.io.InputStream;

import com.Qme.testapp.R;
import com.Qme.testapp.R.id;
import com.Qme.testapp.R.layout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class Promotion extends ActionBarActivity {

	static final String LOG_TAG = "SlidingTabsBasicFragment";
	private SlidingTabLayout mSlidingTabLayout;
	private ViewPager mViewPager;

	ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	String[] head = { "Daily deal", "Monthly deal", "special benefit" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_promotion);

		// อ้างอิงไปยัง DrawerLayout ที่เป็น root element ของ layout file
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout02);

		// อ้างอิงไปยัง ListView ซึ่งเป็นตัว drawer ที่จะเลื่อนออกมา
		mDrawerList = (ListView) findViewById(R.id.left_drawer02);

		// กำหนด Adapter ให้กับ ListView
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.list_item, maintain_data.listdrawer()));
		// กำหนด click listener ให้กับ ListView
		// เพื่อระบุการทำงานเมื่อไอเท็มในลิสต์ถูกคลิกเลือก

		mDrawerList.setOnItemClickListener(new DrawerItemClickListener02());

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.title_section1,
				R.string.title_section2) {

			/** Called when drawer is closed */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle("Q-Me: Promotion");
				invalidateOptionsMenu();
			}

			/** Called when a drawer is opened */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle("Q-Me's Choice");
				invalidateOptionsMenu();

			}

		};

		// Setting DrawerToggle on DrawerLayout
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setAdapter(new SamplePagerAdapter());
		mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
		mSlidingTabLayout.setViewPager(mViewPager);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if (id == R.id.action_settings) {

			return true;
		}
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class SamplePagerAdapter extends PagerAdapter {

		/**
		 * @return the number of pages to display
		 */
		@Override
		public int getCount() {
			return head.length;
		}

		/**
		 * @return true if the value returned from
		 *         {@link #instantiateItem(ViewGroup, int)} is the same object
		 *         as the {@link View} added to the {@link ViewPager}.
		 */
		@Override
		public boolean isViewFromObject(View view, Object o) {
			return o == view;
		}

		// BEGIN_INCLUDE (pageradapter_getpagetitle)
		/**
		 * Return the title of the item at {@code position}. This is important
		 * as what this method returns is what is displayed in the
		 * {@link SlidingTabLayout}.
		 * <p>
		 * Here we construct one using the position value, but for real
		 * application the title should refer to the item's contents.
		 */
		@Override
		public CharSequence getPageTitle(int position) {

			return head[position];
			// return "Item " + (position + 1);
		}

		// END_INCLUDE (pageradapter_getpagetitle)

		/**
		 * Instantiate the {@link View} which should be displayed at
		 * {@code position}. Here we inflate a layout from the apps resources
		 * and then change the text view to signify the position.
		 */
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// Inflate a new layout from our resources

			View view = null;
			switch (String.valueOf(position + 1)) {
			case "1":
				view = getLayoutInflater().inflate(R.layout.dailydeal,
						container, false);
				// Add the newly created View to the ViewPager
				container.addView(view);
				ImageView imgdaily = (ImageView) findViewById(R.id.imageViewDailyDeal);
				new DownloadImageTask(imgdaily)
						.execute("http://duiclub.5gbfree.com/promo/fuji_promo.jpg");

				// Retrieve a TextView from the inflated View, and update it's
				// text
				// TextView title = (TextView)
				// view.findViewById(R.id.item_title);
				// title.setText(String.valueOf(position + 1));
				break;
			case "2":
				view = getLayoutInflater().inflate(R.layout.monthydeal,
						container, false);
				// Add the newly created View to the ViewPager
				container.addView(view);
				imgdaily = (ImageView) findViewById(R.id.imageViewMonthlyDeal);
				new DownloadImageTask(imgdaily)
						.execute("http://duiclub.5gbfree.com/promo/fuji_promo2.jpg");

				break;

			case "3":
				view = getLayoutInflater().inflate(R.layout.specialbenefit,
						container, false);
				// Add the newly created View to the ViewPager
				container.addView(view);
				imgdaily = (ImageView) findViewById(R.id.imageViewSpecialBenefit);
				new DownloadImageTask(imgdaily)
						.execute("http://duiclub.5gbfree.com/promo/fuji_promo3.jpg");
				break;
			default:
				break;
			}
			// if(String.valueOf(position + 1)=="1"){
			//
			//
			// view = getLayoutInflater().inflate(R.layout.pager_item,
			// container, false);
			// // Add the newly created View to the ViewPager
			// container.addView(view);
			// // Retrieve a TextView from the inflated View, and update it's
			// text
			// // TextView title = (TextView)
			// view.findViewById(R.id.item_title);
			// // title.setText(String.valueOf(position + 1));
			//
			//
			// }
			//
			// if(String.valueOf(position + 1)=="2"){
			// view = getLayoutInflater().inflate(R.layout.activity_main,
			// container, false);
			// // Add the newly created View to the ViewPager
			// container.addView(view);
			// // Retrieve a TextView from the inflated View, and update it's
			// text
			// // TextView title = (TextView)
			// view.findViewById(R.id.item_title);
			// // title.setText(String.valueOf(position + 1));
			//
			// }
			// if(String.valueOf(position + 1)=="3"){
			// Intent browserIntent = new Intent(Intent.ACTION_VIEW,
			// Uri.parse("http://www.fuji.co.th/2009/TH/home/index.php"));
			// startActivity(browserIntent);
			// // Retrieve a TextView from the inflated View, and update it's
			// text
			// // TextView title = (TextView)
			// view.findViewById(R.id.item_title);
			// // title.setText(String.valueOf(position + 1));
			// }
			//
			// // Log.i(LOG_TAG, "instantiateItem() [position: " + position +
			// "]");
			//
			// // Return the View
			return view;
		}

		/**
		 * Destroy the item from the {@link ViewPager}. In our case this is
		 * simply removing the {@link View}.
		 */
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);

			// Log.i(LOG_TAG, "destroyItem() [position: " + position + "]");

		}

	}

	public class DrawerItemClickListener02 implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			// TODO Auto-generated method stub
			// ((TextView)
			// findViewById(R.id.textView)).setText(mPlanetTitles[position]);

			// ((TextView)
			// findViewById(R.id.textView1)).setText(mPlanetTitles[position]);

			if (position == 0) {
				finish();
				// Intent intent = new
				// Intent(getApplicationContext(),MainActivity.class);
				// startActivity(intent);
			}
			if (position == 1) {
				Intent browserIntent = new Intent(
						Intent.ACTION_VIEW,
						Uri.parse("http://www.fuji.co.th/2009/TH/home/index.php"));
				startActivity(browserIntent);
			}
			if (position == 2) {
				finish();
				// startActivity(new Intent("Menus_activity01"));
				Intent myIntent = new Intent("ChooseRestaurant");
				myIntent.putExtra("extra", "");
				startActivityForResult(myIntent, 0);
			}
			if (position == 3) {

				// startActivity(new Intent("Menus_activity01"));
			}
			// ปิด drawer
			// (ถ้าไม่สั่งปิดจะแสดงค้างอยู่อย่างนั้นจนกว่าผู้ใช้จะปิดเอง)
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}
	}

}