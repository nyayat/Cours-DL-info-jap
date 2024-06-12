/* Time-stamp: <clock.h  22 Mar 2004 08:53:45> */

#include <qlcdnumber.h>

class Clock : public QLCDNumber {
 public:
  Clock(QWidget* parent = 0, const char* name = 0);

 protected:
  void timerEvent(QTimerEvent* event);

 private:
  void showTime();
  bool showingColon;
};
