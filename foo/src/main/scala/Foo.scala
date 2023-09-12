object Foo {
  def main(args: Array[String]): Unit = {
    println("Hello World")
  }

  def banana[A, B](x: Either[A, B]): Either[A, B] = x.map(y => y)
}
