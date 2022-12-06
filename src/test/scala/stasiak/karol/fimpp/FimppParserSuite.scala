package stasiak.karol.fimpp

class FimppParserSuite extends munit.FunSuite {

  test("more than one is relational gt") {
    val subject = FimppParser.parseAll(
      FimppParser.condition,
      "applejack has more than one".toLowerCase()
    )
    assert(subject.successful)
    assertEquals(
      subject.get,
      Relational(VariableValue("applejack"), ">", NumberValue(1))
    )
  }

  test("or are altivernative gts") {
    val subject = FimppParser.parseAll(
      FimppParser.condition,
      "applejack has more than one or pinkie pie has more than one"
    )

    assert(subject.successful)
    assertEquals(
      subject.get,
      Alternative(
        List(
          Relational(VariableValue("applejack"), ">", NumberValue(1)),
          Relational(VariableValue("pinkie pie"), ">", NumberValue(1))
        )
      )
    )
  }

  test("and are conjuction of gts") {
    val subject = FimppParser.parseAll(
      FimppParser.condition,
      "applejack has more than one and pinkie pie has more than one"
    )
    assert(subject.successful)
    assertEquals(
      subject.get,
      Conjunction(
        List(
          Relational(VariableValue("applejack"), ">", NumberValue(1)),
          Relational(VariableValue("pinkie pie"), ">", NumberValue(1))
        )
      )
    )
  }

  test("and are conjuction of gts") {
    val subject = FimppParser.parseAll(
      FimppParser.condition,
      "applejack is equal to one and pinkie pie has more than one"
    )

    assert(subject.successful)
    assertEquals(
      subject.get,
      Conjunction(
        List(
          Relational(VariableValue("applejack"), "=", NumberValue(1)),
          Relational(VariableValue("pinkie pie"), ">", NumberValue(1))
        )
      )
    )
  }

  test("and with single subject are relational of res") {
    val subject = FimppParser.parseAll(
      FimppParser.condition,
      "applejack is equal to one and two"
    )

    assert(subject.successful)
    assertEquals(
      subject.get,
      Relational(
        VariableValue("applejack"),
        "=",
        ListExpression(List(NumberValue(1), NumberValue(2)))
      )
    )
  }

  test("") {
    val subject = FimppParser.parseAll(
      FimppParser.identifier ~ FimppParser.kw("i") | FimppParser.kw(
        "x"
      ) ~ FimppParser.kw("i"),
      "x i".toLowerCase()
    )
    assert(subject.successful)
    // not sure why not compiling
    // import scala.util.parsing.combinator._
    // assertEquals(subject.get, ("x" ~()))
  }
  test("single quotes concat ") {
    val subject = FimppParser.parseAll(
      FimppParser.stringLiteral,
      "\"fafdsfs 'sdfsdfs' dsfsfsf\"".toLowerCase()
    )
    assert(subject.successful)
    assertEquals(
      subject.get,
      Concatenation(
        List(
          StringValue("fafdsfs "),
          VariableValue("sdfsdfs"),
          StringValue(" dsfsfsf")
        )
      )
    )
  }

  test("did you know assigns") {
    val subject = FimppParser.parseAll(
      FimppParser.statement,
      "Did you know that Applejack likes the number 99?".toLowerCase()
    )

    assert(subject.successful)
    assertEquals(
      subject.get,
      Assignment("applejack", NumberValue(99))
    )
  }

  test("did you know assigns") {
    val subject = FimppParser.parseAll(
      FimppParser.statement,
      """I sang " 'Applejack' jugs of cider on the wall, 'Applejack' jugs of cider,"."""
        .toLowerCase()
    )

    assert(subject.successful)
    assertEquals(
      subject.get,
      PrintStat(
        Concatenation(
          List(
            Concatenation(
              List(
                StringValue(" "),
                VariableValue("applejack"),
                StringValue(" jugs of cider on the wall, "),
                VariableValue("applejack"),
                StringValue(" jugs of cider,")
              )
            ),
            StringValue("\n")
          )
        )
      )
    )
  }
  test("when loop") {
    val subject = FimppParser.parseAll(
      FimppParser.statement,
      """
        |When Applejack had more than 1:
        |  I wrote the number twelve.
        |In the end, I did this instead.
        |  I sang "No more jugs of cider on the wall, no more jugs of cider.
        |Go to the store and buy some more, 99 jugs of cider on the wall.".
        |  That's what I did.""".stripMargin.toLowerCase()
    )
    assert(subject.successful)
    assertEquals(
      subject.get,
      IfStat(
        Relational(VariableValue("applejack"), ">", NumberValue(1)),
        List(
          PrintStat(Concatenation(List(NumberValue(12), StringValue("\n"))))
        ),
        List(
          PrintStat(
            Concatenation(
              List(
                Concatenation(
                  List(
                    StringValue(
                      "no more jugs of cider on the wall, no more jugs of cider.\n" +
                        "go to the store and buy some more, 99 jugs of cider on the wall."
                    )
                  )
                ),
                StringValue("\n")
              )
            )
          )
        )
      )
    )
  }

  test("decrement") {
    val subject = FimppParser.parseAll(
      FimppParser.statement,
      """Applejack got one less.""".toLowerCase()
    )

    assert(subject.successful)
    assertEquals(
      subject.get,
      Increment("applejack", NumberValue(-1))
    )
  }

  test("a big ol program") {
    val result = FimppParser.parseAll(
      FimppParser.module,
      """
        |Dear Princess Celestia: Letter One.
        |
        |Today I learned Applejack's Drinking Song.
        |
        |I learned about Applejack's Drinking Song:
        |
        |  Did you know that Applejack likes the number 99?
        |
        |  I did this while Applejack had more than 1.
        |    I sang " 'Applejack' jugs of cider on the wall, 'Applejack' jugs of cider,".
        |    Applejack got one less.
        |
        |    When Applejack had more than 1:
        |      I sang "Take one down and pass it around, 'Applejack' jugs of cider on the wall.".
        |    In the end, I did this instead:
        |      I sang "Take one down and pass it around, 1 jug of cider on the wall.
        |1 jug of cider on the wall, 1 jug of cider.
        |Take one down and pass it around, no more jugs of cider on the wall.".
        |    That's what I did.
        |
        |    In the end, I did this instead.
        |      I sang "No more jugs of cider on the wall, no more jugs of cider.
        |Go to the store and buy some more, 99 jugs of cider on the wall.".
        |    That's what I did.
        |
        |  That's about Applejack's Drinking Song with Applejack!
        |
        |Your faithful student, Twilight Sparkle.      """.stripMargin
    )

    assert(result.successful)
    assertEquals(
      result.get,
      Module(
        "Letter One",
        List(
          Function(
            "letter to celestia",
            List(),
            List(ExprStat(FunctionCall("applejack's drinking song", List()))),
            "twilight sparkle"
          ),
          Function(
            "applejack's drinking song",
            List(),
            List(
              Assignment("applejack", NumberValue(99)),
              WhileStat(
                Relational(VariableValue("applejack"), ">", NumberValue(1)),
                List(
                  PrintStat(
                    Concatenation(
                      List(
                        Concatenation(
                          List(
                            StringValue(" "),
                            VariableValue("applejack"),
                            StringValue(" jugs of cider on the wall, "),
                            VariableValue("applejack"),
                            StringValue(" jugs of cider,")
                          )
                        ),
                        StringValue("\n")
                      )
                    )
                  ),
                  Increment("applejack", NumberValue(-1)),
                  IfStat(
                    Relational(VariableValue("applejack"), ">", NumberValue(1)),
                    List(
                      PrintStat(
                        Concatenation(
                          List(
                            Concatenation(
                              List(
                                StringValue(
                                  "Take one down and pass it around, "
                                ),
                                VariableValue("applejack"),
                                StringValue(" jugs of cider on the wall.")
                              )
                            ),
                            StringValue("\n")
                          )
                        )
                      )
                    ),
                    List(
                      PrintStat(
                        Concatenation(
                          List(
                            Concatenation(
                              List(
                                StringValue(
                                  "Take one down and pass it around, 1 jug of cider on the wall.\n1 jug of cider on the wall, 1 jug of cider.\nTake one down and pass it around, no more jugs of cider on the wall."
                                )
                              )
                            ),
                            StringValue("\n")
                          )
                        )
                      )
                    )
                  )
                ),
                None,
                List(
                  PrintStat(
                    Concatenation(
                      List(
                        Concatenation(
                          List(
                            StringValue(
                              "No more jugs of cider on the wall, no more jugs of cider.\nGo to the store and buy some more, 99 jugs of cider on the wall."
                            )
                          )
                        ),
                        StringValue("\n")
                      )
                    )
                  )
                )
              )
            ),
            "applejack"
          )
        )
      )
    )
  }
}
