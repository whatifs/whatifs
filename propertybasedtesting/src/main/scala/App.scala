package io.whatifs.propertybasedtesting

case class HeartRate(bpm: Int);

object HeartRate {
  def build(bpm: Int): Either[String, HeartRate] = {
    Right(HeartRate(bpm))
  }
}