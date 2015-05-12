
import 'dart:html';

var done = false;

void main() {
  window.onScroll.listen(loadMore);
}

loadMore(Event e) {
  num toEnd = document.body.scrollHeight - window.scrollY;
  print(toEnd);
  if (toEnd < 2000 && done == false) {
    done = true;
    print("border!");
    print(toEnd);
    HttpRequest photoRequest = new HttpRequest();
    photoRequest.open("GET", "loadphotos");
    photoRequest.onLoadEnd.listen(appendPhoto);
    photoRequest.send();
  }
}

appendPhoto(ProgressEvent e) {
  print("New photos loaded!");
  var photos = e.target.responseText;
  querySelector("#photos").appendHtml(photos);
  done = false;
}
