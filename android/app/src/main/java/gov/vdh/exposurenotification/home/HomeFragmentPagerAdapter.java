/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package gov.vdh.exposurenotification.home;

import static gov.vdh.exposurenotification.home.HomeFragment.TAB_DEBUG;
import static gov.vdh.exposurenotification.home.HomeFragment.TAB_EXPOSURES;
//import static gov.vdh.exposurenotification.home.HomeFragment.TAB_HOME;
import static gov.vdh.exposurenotification.home.HomeFragment.TAB_NOTIFY;
import static gov.vdh.exposurenotification.home.HomeFragment.TAB_VIRTUAL_AGENT;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import gov.vdh.exposurenotification.BuildConfig;
import gov.vdh.exposurenotification.R;
import gov.vdh.exposurenotification.exposure.ExposureHomeFragment;
import gov.vdh.exposurenotification.notify.NotifyHomeFragment;
import gov.vdh.exposurenotification.debug.DebugHomeFragment;

/** Simple {@link FragmentPagerAdapter} for the different home tabs. */
public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

  private Fragment currentFragment;

  HomeFragmentPagerAdapter(FragmentManager fm) {
    super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
  }

  @NonNull
  @Override
  public Fragment getItem(int i) {
    switch (i) {
      case TAB_EXPOSURES:
        return new ExposureHomeFragment();
      case TAB_DEBUG:
        return new DebugHomeFragment();
      case TAB_VIRTUAL_AGENT:
        return new VirtualAgentHomeFragment();
      case TAB_NOTIFY:
        // fall through.
      default:
        return new NotifyHomeFragment();
    }
  }

  @Override
  public int getCount() {
    if ("debug".equalsIgnoreCase(BuildConfig.BUILD_TYPE)) {
      return 4;
    }return 3;
  }

  @Override
  public void setPrimaryItem(@NonNull ViewGroup group, int position, @NonNull Object object) {
    currentFragment = ((Fragment) object);
    super.setPrimaryItem(group, position, object);
  }

  Fragment getCurrentFragment() {
    return currentFragment;
  }
}
