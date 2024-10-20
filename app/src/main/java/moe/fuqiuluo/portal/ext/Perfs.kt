package moe.fuqiuluo.portal.ext

import android.content.Context
import androidx.core.content.edit
import com.baidu.mapapi.map.BaiduMap
import moe.fuqiuluo.portal.service.MockServiceHelper
import moe.fuqiuluo.portal.ui.mock.HistoricalLocation
import moe.fuqiuluo.xposed.utils.FakeLoc

val Context.sharedPrefs
    get() = getSharedPreferences(MockServiceHelper.PROVIDER_NAME, Context.MODE_PRIVATE)!!

var Context.selectLocation: HistoricalLocation?
    get() {
        return sharedPrefs.getString("selectedLocation", null)?.let {
            HistoricalLocation.fromString(it)
        }
    }

    set(value) = sharedPrefs.edit {
        putString("selectedLocation", value?.toString())
    }

val Context.historicalLocations: List<HistoricalLocation>
    get() {
        return sharedPrefs.getStringSet("locations", emptySet())?.map {
            HistoricalLocation.fromString(it)
        } ?: emptyList()
    }

var Context.rawHistoricalLocations: Set<String>
    get() {
        return sharedPrefs.getStringSet("locations", emptySet()) ?: emptySet()
    }

    set(value) {
        sharedPrefs.edit {
            putStringSet("locations", value)
        }
    }

var Context.mapType: Int
    get() = sharedPrefs.getInt("mapType", BaiduMap.MAP_TYPE_NORMAL)

    set(value) = sharedPrefs.edit {
        putInt("mapType", value)
    }

val Context.isFullScreen: Boolean
    get() = sharedPrefs.getBoolean("full_screen", false)

var Context.rockerCoords: Pair<Int, Int>
    get() {
        val x = sharedPrefs.getInt("rocker_x", 0)
        val y = sharedPrefs.getInt("rocker_y", 0)
        return Pair(x, y)
    }

    set(value) = sharedPrefs.edit {
        putInt("rocker_x", value.first)
        putInt("rocker_y", value.second)
    }

var Context.speed: Double
    get() = sharedPrefs.getFloat("speed", FakeLoc.speed.toFloat()).toDouble()

    set(value) = sharedPrefs.edit {
        putFloat("speed", value.toFloat())
    }

var Context.altitude: Double
    get() = sharedPrefs.getFloat("altitude", FakeLoc.altitude.toFloat()).toDouble()

    set(value) = sharedPrefs.edit {
        putFloat("altitude", value.toFloat())
    }

var Context.accuracy: Float
    get() = sharedPrefs.getFloat("accuracy", FakeLoc.accuracy)

    set(value) = sharedPrefs.edit {
        putFloat("accuracy", value)
    }

var Context.needOpenSELinux: Boolean
    get() = sharedPrefs.getBoolean("needOpenSELinux", false)

    set(value) = sharedPrefs.edit {
        putBoolean("needOpenSELinux", value)
    }
