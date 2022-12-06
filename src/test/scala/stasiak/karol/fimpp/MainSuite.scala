package stasiak.karol.fimpp

import java.io.ByteArrayOutputStream

class MainSuite extends munit.FunSuite {

  test("hello world output") {
    val stream = new ByteArrayOutputStream()
    Console.withOut(stream) {
      Main.main(Array("examples/hello.fimpp"))
    }

    assertNoDiff(
      stream.toString(),
      "parsing: examples/hello.fimpp\n" +
        "interpreting: examples/hello.fimpp\n" +
        "Hello, Equestria"
    )
  }

  test("gcd main output") {
    val stream = new ByteArrayOutputStream()

    Console.withOut(stream) {
      Main.main(Array("examples/gcd.fimpp"))
    }

    assertNoDiff(
      stream.toString(),
      """parsing: examples/gcd.fimpp
interpreting: examples/gcd.fimpp
6"""
    )
  }

    //Main.main(Array("/home/karol/IdeaProjects/Fimpp/examples/99bottles.fimpp"))
    //Main.main(Array("/home/karol/IdeaProjects/Fimpp/examples/eratosthenes.fimpp"))
    //Main.main(Array("/home/karol/IdeaProjects/Fimpp/examples/bf.fimpp"))
    //Main.main(Array("/home/karol/IdeaProjects/Fimpp/examples/swing.fimpp"))

}
