package com.core.utils;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 
 * 创建者：qjt 修改时间：2015-4-15 下午3:25:33
 * 作用：针对同一个活动中的所有fragment中的操作跳转，不同的活动中的fragment无法通过该实例进行操作
 */
public class FragmentHelper {

	/**
	 * 
	 * 创建者：qjt 时间：2015-4-15 下午4:08:43
	 * 注释：当前fragment隐藏并显示另一个fragment中，当前fragment所有操作均保留
	 * 
	 * @param from
	 * @param to
	 * @param resourceId
	 */
	public static void showFrag(Fragment from, Fragment to, int resourceId) {
		try {
			FragmentTransaction fromTransaction = from.getFragmentManager()
					.beginTransaction();
			if (!to.isHidden()) {
				fromTransaction.add(resourceId, to);
			}
			 fromTransaction.hide(from);
			fromTransaction.show(to);
			fromTransaction.commit();
		} catch (Exception e) {

		}
	}

	public static void showFrag(FragmentActivity activity,
			List<Fragment> froms, int index, int resourceId)
			throws IllegalAccessException {
		FragmentTransaction transaction = activity.getSupportFragmentManager()
				.beginTransaction();
		if (index >= froms.size()) {
			throw new IllegalAccessException();
		}
		if (froms != null && froms.size() > 0) {
			for (int i = 0; i < froms.size(); i++) {
				Fragment fragment = froms.get(i);
				if (!fragment.isAdded()) {
					transaction.add(resourceId, fragment);
				}
				if (i == index) {
					transaction.show(fragment);
				} else {
					transaction.hide(fragment);
				}

			}
			transaction.commit();
		}
	}

	/**
	 * 
	 * 创建者：qjt 时间：2015-4-20 下午3:59:09 注释：原生fragment的跳转，不继承basefrag
	 * 
	 * @param from
	 * @param to
	 * @param resourceId
	 * @param bundle
	 */
	public static void replaceFrag(Fragment from, Fragment to, int resourceId,
			Bundle bundle) {
		initFragment(from, to, resourceId, bundle, false, false);
	}

	/**
	 * 
	 * 创建者：qjt 时间：2015-4-20 下午3:59:09 注释：原生fragment的跳转，不继承basefrag
	 * 
	 * @param from
	 * @param to
	 * @param resourceId
	 * @param bundle
	 */
	public static void toFragment(Fragment from, Fragment to, int resourceId,
			Bundle bundle) {
		initFragment(from, to, resourceId, bundle, true, false);
	}

	/**
	 * 
	 * 创建者：qjt 时间：2015-4-20 下午3:54:28 注释：清除后退栈中所有的fragment实例。
	 * 
	 * @param from
	 * @param to
	 * @param resourceId
	 * @param bundle
	 */
	public static void replaceTop(Fragment from, Fragment to, int resourceId,
			Bundle bundle) {
		initFragment(from, to, resourceId, bundle, false, true);
	}

	private static void initFragment(Fragment from, Fragment to,
			int resourceId, Bundle bundle, boolean save, boolean cleanAll) {
		FragmentManager manager = from.getActivity()
				.getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		int i = manager.getBackStackEntryCount();
		transaction.replace(resourceId, to);
		if (save) {
			transaction.addToBackStack(null);
		} else {
			if (i != 0) {
				manager.popBackStack();
			}
		}
		if (bundle != null) {
			if (!to.isRemoving()) {
				try {
					to.setArguments(bundle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if (cleanAll) {
			while (i > 0) {
				manager.popBackStack();
				i--;
			}
		}
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
		transaction.commitAllowingStateLoss();
	}



	/**
	 * 
	 * 创建者：ldh 时间：2015年5月7日 下午6:19:47 注释：回退到上一层
	 * 
	 * @param Instance
	 */
	public static void popBackFragment(FragmentActivity activity) {
		activity.getSupportFragmentManager().popBackStack();

	}
	/**
	 * 
	 * <br>创建者：ldh
	 * <br>修改时间：2015年5月20日 上午10:20:50 
	 * <br>注释：清除栈中的fragment
	 * @param activity
	 */
	public static void cleanAllFragement(FragmentActivity activity){
		FragmentManager manager = activity.getSupportFragmentManager();
		int i = manager.getBackStackEntryCount();
		while (i > 0) {
			manager.popBackStack();
			i--;
		}
	}
	/**
	 * 
	 * <br>创建者：ldh
	 * <br>修改时间：2015年5月30日 上午10:35:41 
	 * <br>注释：activity中 显示Fragment
	 * @param activity
	 * @param resourceId  Fragmentlayout id
	 * @param showFragment 要显示的Fragment
	 * @param bundle
	 */
	public static void showFrag(FragmentActivity activity,int resourceId,
			Fragment showFragment,Bundle bundle){

		
		FragmentManager manager = activity.getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
//		int i = manager.getBackStackEntryCount();

		transaction.add(resourceId, showFragment);

		transaction.addToBackStack(showFragment.getTag());

		if (bundle != null) {
			if (!showFragment.isRemoving()) {
				try {
					showFragment.setArguments(bundle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
		transaction.commitAllowingStateLoss();

	}

	/**
	 * 
	 *<br> 创建者：ldh
	 *<br>时间：2015年6月17日 下午4:51:46
	 *<br>注释：显示frag  当前所有操作保存   回退时显示以前的界面 如编辑中的editext内容不变
	 *	 *<br>@param from
	 *<br>@param to
	 *<br>@param resourceId
	 *<br>@param bundle
	 */
	public static void addFrag(Fragment from, Fragment to, int resourceId,Bundle bundle) {
		FragmentManager manager = from.getActivity().getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
//		int i = manager.getBackStackEntryCount();
		
		transaction.add(resourceId, to);
		transaction.addToBackStack(to.getTag());

		if (bundle != null) {
			if (!to.isRemoving()) {
				try {
					to.setArguments(bundle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
		transaction.commitAllowingStateLoss();
	}
	
	
}
