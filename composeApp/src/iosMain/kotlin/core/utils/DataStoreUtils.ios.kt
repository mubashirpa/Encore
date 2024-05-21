/**
 * Created: Oct 14 2022
 * Reference: https://github.com/android/kotlin-multiplatform-samples/blob/main/DiceRoller/shared/src/iosMain/kotlin/com/google/samples/apps/diceroller/createDataStore.kt
 */

package core.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
fun dataStore(): DataStore<Preferences> =
    createDataStore {
        val documentDirectory: NSURL? =
            NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
        requireNotNull(documentDirectory).path + "/$dataStoreFileName"
    }
