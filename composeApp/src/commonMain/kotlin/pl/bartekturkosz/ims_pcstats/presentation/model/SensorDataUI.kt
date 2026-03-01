package pl.bartekturkosz.ims_pcstats.presentation.model

import imspcstats.composeapp.generated.resources.Res
import imspcstats.composeapp.generated.resources.ic_ac_unit
import imspcstats.composeapp.generated.resources.ic_battery
import imspcstats.composeapp.generated.resources.ic_bolt
import imspcstats.composeapp.generated.resources.ic_build
import imspcstats.composeapp.generated.resources.ic_electric_bolt
import imspcstats.composeapp.generated.resources.ic_energy_program_saving
import imspcstats.composeapp.generated.resources.ic_help
import imspcstats.composeapp.generated.resources.ic_humidity_high
import imspcstats.composeapp.generated.resources.ic_info
import imspcstats.composeapp.generated.resources.ic_settings
import imspcstats.composeapp.generated.resources.ic_speed
import imspcstats.composeapp.generated.resources.ic_sync
import imspcstats.composeapp.generated.resources.ic_temperature
import imspcstats.composeapp.generated.resources.ic_tune
import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import pl.bartekturkosz.ims_pcstats.data.model.SensorDataEntity
import kotlin.time.Clock
import kotlin.time.DurationUnit
import kotlin.time.Instant
import kotlin.time.toDuration

data class SensorDataUI(
    val name: String,
    val value: String,
    val lastUpdate: String,
    val icon: DrawableResource
)

// TODO to be implemented properly later
fun SensorDataEntity.toUI() = SensorDataUI(
    name = mapSensorToDisplayName(sensor),
    value = value,
    lastUpdate = formatTimestamp(timestamp),
    icon = mapSensorToIcon(sensor)
)

fun mapSensorToDisplayName(sensor: String) = when (sensor) {
    "sensor1" -> "Temperatura termostatu (°C)"
    "sensor2" -> "Akualna temperatura CO (°C)"
    "sensor3" -> "Aktualna temperatura CWU (°C)"
    "sensor4" -> "Temperatura zadana CO (°C)"
    "sensor5" -> "Temperatura zadana CWU (°C)"
    "sensor6" -> "Status PC"
    "sensor7" -> "Program"
    "sensor8" -> "Tryb"
    "sensor9" -> "Sterowanie"
    "sensor10" -> "Grzałka (cn4)"
    "sensor11" -> "Pompa obiegowa CO"
    "sensor12" -> "Aktualna temperatura termostatu (°C)"
    "sensor13" -> "Temperatura zewnętrzna (°C)"
    "sensor14" -> "Kompensacja CO (°C)"
    "sensor15" -> "Ograniczenie temperatury CWU (°C)"
    "sensor16" -> "Histereza IMS CWU (°C)"
    "sensor17" -> "Status Termostatu"
    "sensor18" -> "Status Timera"
    "sensor19" -> "Moc chwilowa (W)"
    "sensor20" -> "Defrost"
    "sensor23" -> "Częstotliwość sprężarki (Hz)"
    "sensor25" -> "Temperatura wew, IMS-PC (°C)"
    "sensor26" -> "Przegrzanie"
    "sensor27" -> "Dochłodzenie"
    "sensor28" -> "Temperatura powrotu CO (°C)"
    "sensor29" -> "Wilgotność w pomiesczeniu (IMS-TH)"
    "sensor30" -> "Poziom baterii Shelly Plus H&T"
    "sensor31" -> "AdaptIMS"
    "sensor32" -> "Obieg"
    "sensor33" -> "Dezaktywacja grzania CWU Status"
    "sensor34" -> "Dezaktywacja grzania CWU Temp"
    "sensor35" -> "Załączenie CN4 przez IMS-PC Status"
    "sensor36" -> "Temperatura zewnętrzna załączenia CN4 (°C)"
    else -> "Nieznany ($sensor)"
}

fun mapSensorToIcon(sensor: String): DrawableResource = when (sensor) {
    // Temperatury
    "sensor1",  // Temperatura termostatu
    "sensor2",  // Aktualna temperatura CO
    "sensor3",  // Aktualna temperatura CWU
    "sensor4",  // Temperatura zadana CO
    "sensor5",  // Temperatura zadana CWU
    "sensor12", // Aktualna temperatura termostatu
    "sensor13", // Temperatura zewnętrzna
    "sensor25", // Temperatura wew, IMS-PC
    "sensor28", // Temperatura powrotu CO
    "sensor36"  // Temperatura zewnętrzna załączenia CN4
        -> Res.drawable.ic_temperature

    // Status / informacja
    "sensor6",  // Status PC
    "sensor17", // Status Termostatu
    "sensor18", // Status Timera
    "sensor33", // Dezaktywacja grzania CWU Status
    "sensor35"  // Załączenie CN4 przez IMS-PC Status
        -> Res.drawable.ic_info

    // Program / tryb / sterowanie
    "sensor7" -> Res.drawable.ic_energy_program_saving
    "sensor8" -> Res.drawable.ic_settings
    "sensor9" -> Res.drawable.ic_tune

    // Grzałka
    "sensor10" -> Res.drawable.ic_bolt

    // Pompa obiegowa / obieg
    "sensor11",
    "sensor32"
        -> Res.drawable.ic_sync

    // Kompensacja / ograniczenia / histereza / adaptacja
    "sensor14",
    "sensor15",
    "sensor16",
    "sensor31"
        -> Res.drawable.ic_build

    // Moc chwilowa
    "sensor19" -> Res.drawable.ic_electric_bolt

    // Defrost
    "sensor20" -> Res.drawable.ic_ac_unit

    // Sprężarka / częstotliwość
    "sensor23" -> Res.drawable.ic_speed

    // Przegrzanie / dochłodzenie
    "sensor26",
    "sensor27"
        -> Res.drawable.ic_temperature

    // Wilgotność
    "sensor29" -> Res.drawable.ic_humidity_high

    // Bateria
    "sensor30" -> Res.drawable.ic_battery

    // Fallback
    else -> Res.drawable.ic_help
}

fun formatTimestamp(timestamp: Long): String {
    val now = Clock.System.now().toEpochMilliseconds()
    val diff = (now - timestamp).toDuration(DurationUnit.MILLISECONDS)
    return when {
        diff.inWholeMinutes < 1 -> "przed chwilą"
        diff.inWholeMinutes < 60 -> "${diff.inWholeMinutes} min temu"
        diff.inWholeHours < 24 -> "${diff.inWholeHours} h temu"
        diff.inWholeDays < 7 -> "${diff.inWholeDays} dni temu"
        else -> timestamp.toLocalDateTimeString()
    }
}

fun Long.toLocalDateTimeString(): String {
    val local = Instant.fromEpochMilliseconds(this).toLocalDateTime(TimeZone.currentSystemDefault())

    fun Int.pad() = toString().padStart(2, '0')

    return "${local.day.pad()}-${local.month.number.pad()}-${local.year}" + " " + "${local.hour.pad()}:${local.minute.pad()}:${local.second.pad()}"
}