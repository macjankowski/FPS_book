package pl.macjankowski.fps

import org.scalatest.FunSuite
import org.scalatest.prop.Checkers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import pl.macjankowski.fps.ch5.StreamSpecification

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 18.08.14
 *         Time: 13:36
 */
@RunWith(classOf[JUnitRunner])
class ScalaCheckRunner extends FunSuite with Checkers {

  test("Stream satisfies properties"){
    check(new StreamSpecification)
  }

}
