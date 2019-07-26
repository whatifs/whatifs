package io.whatifs.heartrates

import org.scalatest.{Matchers, PropSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import eu.timepit.refined.refineV
import eu.timepit.refined.auto._

class HeartRateSpec
    extends PropSpec
    with ScalaCheckDrivenPropertyChecks
    with Matchers {

  import HeartRateGenerators._

  property ("When building HeartRate, bounds are respected") {
    forAll(bpmGen) {
      case bpm if (bpm < HeartRate.MinHeartRate) =>
        assert(HeartRate.build(bpm).isLeft)

      case bpm if (bpm > HeartRate.MaxHeartRate) =>
        assert(HeartRate.build(bpm).isLeft)

      case bpm => 
        val hr = refineV[HeartRate.ValidHeartRate](bpm).map(HeartRate.apply(_))
        assert(hr.isRight)
        assertResult(hr)  {
          HeartRate.build(bpm)
        }
    }
  }

  property ("HeartRate compiles only for legal values") {
    HeartRate(120)
    assertCompiles("HeartRate(120)")
    assertTypeError("HeartRate(-100)")
  }

}
