package io.whatifs.propertybasedtesting

import org.scalatest.{Matchers, PropSpec}
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class CheckSpec
  extends PropSpec
  with ScalaCheckDrivenPropertyChecks
  with Matchers {

  property ("Heart Rates are always within range") {
    forAll { (bpm: Int) =>
      bpm == 1
    }
  }

}
