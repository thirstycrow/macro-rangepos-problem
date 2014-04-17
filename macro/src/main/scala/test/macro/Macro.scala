package test.macro

import scala.language.experimental.macros
import scala.reflect.macros.Context

object Macro {

  def echo(message: String): Unit = macro echo_impl

  def echo_impl(c: Context)(message: c.Expr[String]): c.Expr[Unit] = {
    import c.universe._
    reify { println(message.splice) }
  }
}
