import play.twirl.api.Html

package object twirlc {
  type Styles = Seq[Html]
  type Contents = Seq[Html]
  type Scripts = Seq[Html]

  implicit val htmlToSomeSeq = (h: Html) => SSeq(h)
}
