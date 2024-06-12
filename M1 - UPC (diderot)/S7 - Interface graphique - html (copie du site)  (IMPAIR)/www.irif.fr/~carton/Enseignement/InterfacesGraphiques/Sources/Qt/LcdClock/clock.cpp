/* Time-stamp: <clock.cpp  22 Mar 2004 08:57:43> */

#include <qdatetime.h>
#include "clock.h"

// Constucteur
Clock::Clock(QWidget* parent, const char* name)
  : QLCDNumber(parent, name), showingColon(true)
{
  showTime();
  startTimer(1000);
}

void Clock::timerEvent(QTimerEvent* event)
{
  showTime();
}

void Clock::showTime()
{
  QString time = QTime::currentTime().toString().left(5);
  if (!showingColon) 
    time[2] = ' ';
  display(time);
  showingColon = !showingColon;
}
