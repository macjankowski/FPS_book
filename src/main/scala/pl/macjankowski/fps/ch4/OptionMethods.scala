package pl.macjankowski.fps.ch4


/**
 * @author "Maciej Jankowski <mac.rarry@gmail.com>"
 *
 */
object OptionMethods {

  def sequence[A](a: List[Option[A]]): Option[List[A]] = a.foldRight(Option(List[A]()))((e, acc) => e match {
    case None => None
    case Some(a) => acc.fold(None: Option[List[A]])(l => Some(a :: l))
  }).fold(None: Option[List[A]])(_ match {
    case List() => None
    case x => Option(x)
  })





}