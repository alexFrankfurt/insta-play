
import 'dart:async';
import 'dart:html';
import 'dart:isolate';

final LOAD_MORE = "Load more";
final LOADED = "loaded";

class DocumentAndSendPort {
  HtmlDocument doc;
  SendPort port;
  DocumentAndSendPort(this.doc, this.port);
}

appendPhoto(SendPort port, HtmlDocument doc) => (ProgressEvent e) {
  print("New photos loaded!");
  var photos = e.target.responseText;
  doc.querySelector("#photos").appendHtml(photos);
  port.send([LOADED, ""]);
};

void scrollStatus(DocumentAndSendPort dasp) {
  var state = false;
  var port = new ReceivePort();
  var doc = dasp.doc;
  var replyTo = dasp.port;
  HttpRequest photoRequest = new HttpRequest();
  replyTo.send(port.sendPort);
  port.listen((msg) {
    print("Isolate got message $msg");
    var data = msg[0];
    SendPort replyTo = msg[1];
    if (data == "START") {
      replyTo.send("Yes, I got your message :)");
      replyTo.send(data);
    } else if (data == LOAD_MORE) {
        print("Try to load more");
        if (state == false) {
          state = true;
          print("border!");
          photoRequest.open("GET", "/loadphotos");
          photoRequest.onLoadEnd.listen(appendPhoto(port.sendPort, doc));
          photoRequest.send();
        }
    } else if (data == LOADED) {
      state = false;
    }
  });
}

void main() {
  print("Isolate test");
  ReceivePort reply = new ReceivePort();
  Future<Isolate> remote = Isolate.spawn(scrollStatus, new DocumentAndSendPort(document, reply.sendPort));
  remote.then((_) => reply.first).then((SendPort port) {
    reply = new ReceivePort();
    port.send(["START", reply.sendPort]);
    reply.listen((msg) {
      print("Message received from isolate: $msg");
    });
    window.onScroll.listen(loadMore(reply, port));
  });
}

loadMore(ReceivePort reply, SendPort port) => (Event e) {
  num toEnd = document.body.scrollHeight - window.scrollY;
  print(toEnd);
  if (toEnd < 2000) {
    port.send([LOAD_MORE, reply.sendPort]);
  }
};
