/**
 * Created: Oct 14 2022
 * Reference: https://github.com/android/kotlin-multiplatform-samples/blob/main/DiceRoller/shared/src/androidMain/kotlin/com/google/samples/apps/diceroller/createDataStore.android.kt
 */

package core.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun dataStore(context: Context): DataStore<Preferences> = createDataStore {
    context.filesDir.resolve(dataStoreFileName).absolutePath
}