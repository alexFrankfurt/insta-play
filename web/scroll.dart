
import 'dart:async';
import 'dart:html';
import 'dart:isolate';

final LOAD_MORE = "Load more";
final LOADED = "loaded";
final mediaCountH = querySelector("#mediaCount");
final photosUl = querySelector("#photos");

appendPhoto(SendPort port, SendPort mainPort) => (ProgressEvent e) {
  print("New photos loaded!");
  var photos = e.target.responseText;
//  querySelector("#photos").appendHtml(photos);
  port.send(["", mainPort, photos]);
};

void scrollStatus(SendPort replyTo) {
  var state = false;
  var port = new ReceivePort();
  HttpRequest photoRequest = new HttpRequest();
  replyTo.send(port.sendPort);
  port.listen((msg) {
    print("Isolate got message $msg");
    var data = msg[0];
    SendPort replyTo = msg[1];
    if (msg.length == 3) {
      var data = msg[2];
      replyTo.send([msg, data]);
    } else {
      if (data == "START") {
        replyTo.send(["Yes, I got your message :)"]);
        replyTo.send([data]);
      } else if (data == LOAD_MORE) {
        print("Try to load more");
        if (state == false) {
          state = true;
          print("border!");
          photoRequest.open("GET", "/loadphotos");
          photoRequest.onLoadEnd.listen(appendPhoto(port.sendPort, replyTo));
          photoRequest.send();
        } else
          print("Got back pressure");
      } else if (data == LOADED) {
        state = false;
      }
    }
  });
}

void main() {
  print("Isolate test");
  ReceivePort reply = new ReceivePort();
  Future<Isolate> remote =  Isolate.spawn(scrollStatus, reply.sendPort);
  remote.then((_) => reply.first).then((SendPort port) {
    reply = new ReceivePort();
    port.send(["START", reply.sendPort]);
    reply.listen((msg) {
      if (msg.length == 1)
        print("Message received from isolate: $msg");
      else {
        var data = msg[1];
        print("Got important data: ");
        photosUl.appendHtml(data);
        mediaCountH.text = "Media count: " + photosUl.childNodes.length.toString();
        print(data);
        port.send([LOADED, reply.sendPort]);
      }
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
