package pl.macjankowski.fps.ch4

import org.scalacheck._
import Prop.forAll
import pl.macjankowski.fps.ch4.matcher.Matcher

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 12:02
 */
class TestMatcher extends Properties("String"){

  property("startsWith") = forAll { (pat1: String, pat2: String, s: String) =>
    Matcher.bothMatch(pat1, pat2, s) ==  Matcher.bothMatchMap2(pat1, pat2, s)
  }
}
