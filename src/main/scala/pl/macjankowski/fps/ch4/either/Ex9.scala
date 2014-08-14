package pl.macjankowski.fps.ch4.either

/**
 * Created with IntelliJ IDEA.
 * @author Maciej Jankowski <maciej.jankowski@ser-solutions.pl>
 *         Date: 14.08.14
 *         Time: 15:42
 */
class Ex9 {

  case class Person(name: Name, age: Age)

  sealed class Name(val value: String)

  sealed class Age(val value: Int)

  def mkName(name: String): MEither[String, Name] =
    if (name == "" || name == null) MLeft("Name is empty.")
    else MRight(new Name(name))

  def mkAge(age: Int): MEither[String, Age] =
    if (age < 0) MLeft("Age is out of range.")
    else MRight(new Age(age))

  def mkPerson(name: String, age: Int): MEither[String, Person] =
    mkName(name).map2(mkAge(age))(Person(_, _))
}
