package io.whatifs.heartrates

import java.time.LocalDate

import org.scalatest.{Matchers, PropSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class MeassurementSpec
    extends PropSpec
    with ScalaCheckDrivenPropertyChecks
    with Matchers {

  import HeartRateGenerators._

  property ("Measurements are done same day") {
    forAll(personGen, hrGen) { (person, hr) =>
      val measurement = Measurement.measure(person, hr)
      assertResult(Measurement(person, hr, LocalDate.now())) {
        measurement
      }
      assert(measurement.warn.isDefined == hr.isHigh, "today measurement warning <==> high heart rate")
    }
  }

  property ("Measurement warning properties") {
    forAll (measurementGen) { measurement =>
      assertResult(measurement.heartRate.isHigh && measurement.date.isAfter(Measurement.warnIfAfter))
      { measurement.warn.isDefined }
    }
  }

}