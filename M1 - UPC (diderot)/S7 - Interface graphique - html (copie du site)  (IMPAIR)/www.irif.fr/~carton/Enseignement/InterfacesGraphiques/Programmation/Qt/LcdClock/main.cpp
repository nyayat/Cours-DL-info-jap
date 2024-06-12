/* Time-stamp: <main.cpp  22 Mar 2004 08:51:25> */

#include <qapplication.h>
#include "clock.h"

int main(int argc, char* argv[])
{
  QApplication app(argc, argv);
  Clock* clock = new Clock();
  app.setMainWidget(clock);
  clock->show();
  return app.exec();
}

