package io.whatifs.heartrates

import eu.timepit.refined._, api.{Refined, Validate}

import java.time.LocalDate

case class ArchivalDatePredicate()
case class ArchivalPredicate[DT](dt: DT)

object ArchivalDatePredicate {
    implicit def archivalDatePredicateValidate: Validate.Plain[LocalDate, ArchivalDatePredicate] = 
        Validate.fromPartial(_.isBefore(LocalDate.now()), "ArchivalDate", ArchivalDatePredicate())
}

object ArchivalPredicate {    
    implicit def archivalDateValidate[DT <: LocalDate](implicit ws: W.Aux[DT]): Validate.Plain[Measurement, ArchivalPredicate[DT]] = 
        Validate.fromPredicate(m => !m.date.isAfter(ws.value), t => s"""$t.archivalDate(${ws.value})""", ArchivalPredicate(ws.value))
}

case class ArchivedMeasurement private(measurement: Measurement, archiveDate: ArchivedMeasurement.ArchivalDate) {

    def this(archiveDate: ArchivedMeasurement.ArchivalDate)(
        measurement: ArchivedMeasurement.ArchivalMeasurement[archiveDate.value.type]) =
        this(measurement.value, archiveDate)
}

object ArchivedMeasurement {

    type ArchivalDate = LocalDate Refined ArchivalDatePredicate
    type ArchivalMeasurement[DT] = Measurement Refined ArchivalPredicate[DT]
    
    def archivedMeasurement(archiveDate: LocalDate, measurement: Measurement): Either[String, ArchivedMeasurement] = for {
        dt <- refineV[ArchivalDatePredicate](archiveDate)
        m  <- refineV[ArchivalPredicate[dt.value.type]](measurement)
    } yield new ArchivedMeasurement(dt)(m)

}
