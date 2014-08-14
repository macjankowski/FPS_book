package pl.macjankowski.fps.ch4

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.prop.TableDrivenPropertyChecks
import pl.macjankowski.fps.ch4.option.MSome

/**
 * @author "Maciej Jankowski <mac.rarry@gmail.com>"
 *
 */
class TestOptionMethods extends FunSpec with ShouldMatchers with TableDrivenPropertyChecks {

  val tests = Table(
    ("list", "res"),
    (List(), None),
    (List(MSome(1), Some(3), Some(5)), Some(List(1, 3, 5))),
    (List(Some(1), None, Some(5)), None),
    (List(Some(1)), Some(List(1))),
    (List(None, Some(3), Some(5)), None),
    (List(Some(1), Some(3), Some(5), None), None))

  describe("Option.sequence") {
    forAll(tests) { (list, res) =>
      it(s"list $list should return $res") {
        OptionMethods.sequence(list) should equal(res)
      }
    }
  }
}