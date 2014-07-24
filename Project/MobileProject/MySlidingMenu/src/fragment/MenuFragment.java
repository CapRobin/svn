package fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.slidingmenu.MainActivity;
import com.slidingmenu.MenuItemAdapter;
import com.slidingmenu.R;

/**
 * 名称：MenuFragment.java
 * 
 * 描述：主要是用于显示menu菜单
 * 
 * @author Yu Farong - yfr5734@gmail.com
 * @date�?013-8-21 上午9:57:10
 * @version v1.0
 */
public class MenuFragment extends Fragment {
	int index_1 = -1;
	int index_2 = -1;
	private List<String> item = null;
	private List<String> stringList = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.menu_layout, null);
		ListView menuLayout = (ListView) view.findViewById(R.id.menuListView);
		// Button button2 = (Button) view.findViewById(R.id.button2);

		item = new ArrayList<String>();
		item.add("血		压");
		item.add("血		糖");
		item.add("咨		询");
		item.add("资		料");
		item.add("阈		值");

		MenuItemAdapter adapter = new MenuItemAdapter(getActivity(), item);
		menuLayout.setAdapter(adapter);

		menuLayout.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String itemName = item.get(position);
				stringList = new ArrayList<String>();
				for (int i = 0; i < 20; i++) {
					stringList.add(itemName+"......" + (i + 1) + "");
				}
				
				// index_1 = position;
				if (index_1 != -1) {
					((MainActivity) getActivity()).getSlidingMenu().toggle();
					if (position != index_1) {
						FragmentManager fragmentManager = ((MainActivity) getActivity()).getSupportFragmentManager();
						ContentFragment contentFragment = (ContentFragment) fragmentManager.findFragmentByTag(itemName);
						fragmentManager.beginTransaction().replace(R.id.content_frame, contentFragment == null ? new ContentFragment(stringList,itemName) : contentFragment, itemName).commit();
						index_1 = position;
					}
				} else {
					((MainActivity) getActivity()).getSlidingMenu().toggle();
					FragmentManager fragmentManager = ((MainActivity) getActivity()).getSupportFragmentManager();
					ContentFragment contentFragment = (ContentFragment) fragmentManager.findFragmentByTag(itemName);
					fragmentManager.beginTransaction().replace(R.id.content_frame, contentFragment == null ? new ContentFragment(stringList,itemName) : contentFragment, itemName).commit();
					index_1 = position;
				}
			}
		});
		return view;
	}
}
