package twirlc

import play.twirl.api.Html

object SSeq {
  def apply(a: Html*) = Some(a)
}
