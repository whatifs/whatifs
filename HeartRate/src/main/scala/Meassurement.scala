package io.whatifs.heartrates

import java.time.LocalDate

case class Person(name: String, age: Int)

case class Measurement(person: Person, heartRate: HeartRate, date: LocalDate) {
  import Measurement._
  def warn: Option[String] =
    if (heartRate.isHigh && date.isAfter(warnIfAfter))
      Some("Heart Rate is High")
    else None

}

object Measurement {
  val WarningDays = 3

  def measure(person: Person, heartRate: HeartRate): Measurement = {
    new Measurement(person, heartRate, LocalDate.now())
  }

  def warnIfAfter: LocalDate =
    LocalDate.now().minusDays(WarningDays.toLong)
}
