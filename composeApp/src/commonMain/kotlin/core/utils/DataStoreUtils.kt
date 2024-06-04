/**
 * Created: Oct 14 2022
 * Reference: https://github.com/android/kotlin-multiplatform-samples/blob/main/DiceRoller/shared/src/commonMain/kotlin/com/google/samples/apps/diceroller/createDataStore.kt
 */

package core.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import okio.Path.Companion.toPath

private lateinit var dataStore: DataStore<Preferences>

private val lock = SynchronizedObject()

/**
 * Gets the singleton DataStore instance, creating it if necessary.
 */
fun createDataStore(producePath: () -> String): DataStore<Preferences> =
    synchronized(lock) {
        if (::dataStore.isInitialized) {
            dataStore
        } else {
            PreferenceDataStoreFactory.createWithPath(
                produceFile = { producePath().toPath() },
            ).also { dataStore = it }
        }
    }

internal const val dataStoreFileName = "user.preferences_pb"
