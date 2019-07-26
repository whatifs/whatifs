package io.whatifs.heartrates

import eu.timepit.refined.{W, refineV}
import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Interval

case class HeartRate(bpm: Int Refined HeartRate.ValidHeartRate) {
  def isHigh = bpm.value < 100
}
object HeartRate {
  final val MinHeartRate = 60
  final val MaxHeartRate = 300

  type ValidHeartRate = Interval.Closed[W.`MinHeartRate`.T, W.`MaxHeartRate`.T]

  def build(bpm: Int): Either[String, HeartRate] = {
    refineV[ValidHeartRate](bpm)
      .map(HeartRate.apply)
      .left.map(_ => "Illegal heart rate")
  }
}
