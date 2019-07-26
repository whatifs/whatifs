package io.whatifs.heartrates

import java.time.LocalDate

import org.scalacheck.Gen

import eu.timepit.refined.refineV

object HeartRateGenerators {
  val specialBPMValues: Seq[Int] =
    Seq(HeartRate.MinHeartRate, HeartRate.MaxHeartRate)
      .flatMap(bpm => Seq(bpm - 1, bpm, bpm + 1))

  val bpmGen: Gen[Int] = Gen.chooseNum(Int.MinValue, Int.MaxValue, specialBPMValues:_*).label("bpm")

  val hrGen: Gen[HeartRate] = Gen.chooseNum(HeartRate.MinHeartRate, HeartRate.MaxHeartRate)
  .map(refineV[HeartRate.ValidHeartRate](_))
  .flatMap(_.fold(_ => Gen.fail, bpm => Gen.const(HeartRate.apply(bpm))))

  val personGen: Gen[Person] = for {
    name <- Gen.alphaStr
    age  <- Gen.chooseNum(10, 120)
  } yield Person(name, age)

  val epochDayGen = Gen.chooseNum(LocalDate.MIN.toEpochDay, LocalDate.MAX.toEpochDay)

  val dateGen = epochDayGen.map(LocalDate.ofEpochDay)

  val measurementGen: Gen[Measurement] = for {
    person <- personGen
    hr <- hrGen
    dt <- dateGen
  } yield Measurement(person, hr, dt)

}
