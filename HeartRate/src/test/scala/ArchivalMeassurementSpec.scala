package io.whatifs.heartrates

import org.scalatest.{Matchers, PropSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

import java.time.LocalDate

class ArchivedMeasurementSpec
    extends PropSpec
    with ScalaCheckDrivenPropertyChecks
    with Matchers {
        import HeartRateGenerators._

property ("Measurements can be archived according to the right rules") {
    forAll (dateGen, measurementGen) { 
        case (archiveDate, measurement) if !archiveDate.isBefore(measurement.date) && archiveDate.isBefore(LocalDate.now()) => 
      ArchivedMeasurement.archivedMeasurement(archiveDate, measurement).isRight
      case (archiveDate, measurement) => 
      ArchivedMeasurement.archivedMeasurement(archiveDate, measurement).isLeft
  }
}

    }