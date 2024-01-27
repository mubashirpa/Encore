/**
 * Created: Oct 14 2022
 * Reference: https://github.com/android/kotlin-multiplatform-samples/blob/main/DiceRoller/shared/src/commonMain/kotlin/com/google/samples/apps/diceroller/createDataStore.kt
 */

package core.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    PreferenceDataStoreFactory.createWithPath(
        corruptionHandler = null,
        migrations = emptyList(),
        produceFile = { producePath().toPath() },
    )

internal const val dataStoreFileName = "user.preferences_pb"
