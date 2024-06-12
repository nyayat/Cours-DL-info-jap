/* Time-stamp: <helloQt.cpp  22 Mar 2004 08:40:51> */

#include <qapplication.h>
#include <qlabel.h>

int main(int argc, char* argv[])
{
  QApplication app(argc, argv);
  // Les balises HTML <center>, <font> et <i> sont interprétées
  QLabel* hello = new QLabel("<center><font color=blue>Hello <i>Qt</i>"
			     "</font></center>", 0);
  app.setMainWidget(hello);
  hello->show();
  return app.exec();
}

// A compiler avec 
// g++ -L/usr/qt/3/lib/ -lqt -isystem /usr/qt/3/include/ -o helloQt helloQt.cpp
