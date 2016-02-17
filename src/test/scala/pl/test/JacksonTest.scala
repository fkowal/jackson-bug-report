package pl.test

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.junit.Test

class JacksonTest {

  @Test
  def unexpectedArrayIndexOutOfBoundsException(): Unit = {
    // given
    val sut = new MyIterable()
    val objectMapper = new ObjectMapper()
    objectMapper.registerModule(DefaultScalaModule) // removing scalaModule fixes the test

    // when
    val str = objectMapper.writeValueAsString(sut)

    assert(str != null, "result is null")
    assert(str == """["a","b"]""", s"${str} != [a,b]")
  }
}
