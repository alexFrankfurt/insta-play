import 'dart:html';

var req = new HttpRequest();
DivElement content = querySelector("#content");
DivElement photoDiv = querySelector("#photoDiv");
TextInputElement ti = querySelector("#search");
NodeValidatorBuilder _validator = new NodeValidatorBuilder.common()
  ..allowElement('img', attributes: ['src']);

void main() {
  print("Waiting for tag");
  ButtonInputElement bie = querySelector("#buttonSearch");
  ti.onKeyUp.listen(loadMore);
  bie.onClick.listen(loadMoreButton);
}

loadMore(KeyboardEvent e) {
  if (e.keyCode == KeyCode.ENTER)
    loadRequest();
}

loadMoreButton(MouseEvent e) => loadRequest();

loadRequest() {
  req..open("GET", "/searchPhotoByTag?tag=" + ti.value)
     ..onLoadEnd.listen(append)
     ..send();
}

append(Event e) {
  photoDiv.setInnerHtml(e.target.responseText, validator: _validator);
}
