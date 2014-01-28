/*
 * Copyright 2013 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mortar;

import android.os.Bundle;

/** Implemented by objects that want to persist via the bundle. */
public interface Bundler extends Scoped {
  /**
   * The key that will identify the bundles passed to this instance via {@link #onLoad}
   * and {@link #onSave}.
   */
  String getMortarBundleKey();

  /**
   * Called when this object is first {@link MortarScope#register registered}, and each time a
   * host {@link android.app.Activity} is re-created (e.g. after a configuration change like
   * rotation, or after the app is paused and resumed). Redundant calls to this method are par for
   * the course, so implementations must be idempotent.
   *
   * @param savedInstanceState the state written by the most recent call to {@link #onSave}, or
   * null if that has never happened.
   */
  void onLoad(Bundle savedInstanceState);

  /**
   * Called from the {@link android.app.Activity#onSaveInstanceState onSaveInstanceState} method
   * of the host {@link android.app.Activity}. This is the receiver's sign that the
   * activity is being torn down, and possibly the entire app along with it.
   * <p/>
   * Note that receivers are likely to outlive multiple activity instances, and so receive multiple
   * calls of this method. Any state required to revive a new instance of the receiver in a new
   * process should be written out each time, as there is no way to know if the app is about to
   * hibernate.
   *
   * @param outState a bundle to write any state that needs to be restored if the plugin is
   * revived
   */
  void onSave(Bundle outState);
}
