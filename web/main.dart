

import 'dart:html';

void main() {
  ParagraphElement par = querySelector("#dart");
  par.text = "Hello from dart:html";
  print("You use dart");
}
