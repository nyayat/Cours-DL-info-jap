#!/usr/bin/env python3

import pendulum
import math, time, argparse, sys

from tkinter import *
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg
from matplotlib.figure import Figure
import matplotlib.animation as animation

parser = argparse.ArgumentParser(
    usage = "%(prog)s [OPTION]",
    description = "Simulate an inverted pendulum"
)
parser.add_argument('-p', '--plot', dest='plot', action='store_true')
parser.set_defaults(plot=False)
args = parser.parse_args()

c = pendulum.Cart()
input_x0 = pendulum.max_w / 2
mouse_diff = False
mode_diff = False
pause = False
last_time = {0: time.time()}

theta_samples = [0]
error_samples = [0]

root = Tk()
if sys.platform == 'linux':
    root.attributes('-type', 'dialog')
root.title("Inverted pendulum")

w = Canvas(root, width=pendulum.max_w, height=pendulum.max_h)

f = Frame(root)
f.pack(side=TOP, fill=X)

vmode = StringVar()
lmode = Label(f, textvariable=vmode)
lmode.pack(side=LEFT)
vinfo = StringVar()
linfo = Label(f, textvariable=vinfo)
linfo.pack(side=RIGHT)

def create_line(x1, y1, x2, y2, **kwargs):
    w.create_line(x1, pendulum.max_h - y1, x2, pendulum.max_h - y2, **kwargs)

def create_oval(x1, y1, x2, y2, **kwargs):
    w.create_oval(x1, pendulum.max_h - y1, x2, pendulum.max_h - y2, **kwargs)

def push_sample(data, sample):
    data.append(sample)

def repaint():
    global input_x0, out, pause, error_samples
    joint_color = "#4760B2"
    mass_color = "#EF1010"
    radius = 4
    if not pause:
        out = c.step(input_x0, mouse_diff, mode_diff)
        push_sample(theta_samples, math.fmod(out.p.theta, 2 * math.pi))
        push_sample(error_samples, out.p.error)
    w.delete("all")
    create_line(0, out.p.y0, pendulum.max_w, out.p.y0, width=5);
    create_line(out.p.x0, out.p.y0, out.p.x, out.p.y, width=3)
    create_oval(out.p.x - radius, out.p.y - radius,
                out.p.x + radius, out.p.y + radius, fill=mass_color)
    create_oval(out.p.x0 - 2 * radius, out.p.y0 - 2 * radius,
                out.p.x0 + 2 * radius, out.p.y0 + 2 * radius, fill=joint_color)
    input_x0 = out.p.x0
    vmode.set(f"Current mode: {out.p.mode_name}"
              + (" (paused)" if pause else ""))
    new_time = time.time()
    vinfo.set("x: {:07.2f}, theta: {:06.2f}, error: {:06.2f}, fps: {:06.2f}" \
              .format(out.p.x,
                      out.p.theta,
                      out.p.error,
                      1. / (new_time - last_time[0])))
    last_time.update({0:new_time})

    w.after(int(1000 * pendulum.dt), repaint)

def mouse_change_callback(event):
    global mouse_diff, input_x0
    input_x0 = event.x
    mouse_diff = not mouse_diff

def key_pressed(event):
    global input_x0, mouse_diff, mode_diff, pause
    x_step = 3
    if event.keysym == 'q':
        quit()
    elif event.keysym == 'r':
        c.reset()
        input_x0 = pendulum.max_w / 2
        theta_samples.clear()
        error_samples.clear()
    elif event.keysym == 'm':
        mode_diff = not mode_diff
    elif event.keysym == 'p':
        pause = not pause
    elif event.keysym == 'Right' and not pause:
        input_x0 = input_x0 + x_step
        mouse_diff = not mouse_diff
    elif event.keysym == 'Left' and not pause:
        input_x0 = input_x0 - x_step
        mouse_diff = not mouse_diff

w.pack(expand = YES, fill = BOTH)
root.bind("<B1-Motion>", mouse_change_callback)
root.bind("<Button-1>", mouse_change_callback)
root.bind("<Key>", key_pressed)

fig = Figure(figsize=(6, 4.5), dpi=100)
ax = fig.add_subplot(111)
text = ax.text(0.97,0.97, "", transform=ax.transAxes, ha="left", va="top")
line1, = ax.plot([])
line1.set_label("theta")
line2, = ax.plot([])
line2.set_label("error")
ax.legend(loc = 'upper left')

def animate(i):
    line1.set_data(range(len(theta_samples)), theta_samples)
    line2.set_data(range(len(error_samples)), error_samples)
    ax.set_xlim([0, len(theta_samples)])
    ax.set_ylim([min(min(theta_samples), min(error_samples)),
                 max(max(theta_samples), max(error_samples)) + 0.0001])

    return line1, text

if args.plot:
    canfig = FigureCanvasTkAgg(fig, master=root)
    canfig.draw()
    canfig.get_tk_widget().pack(side=BOTTOM, fill=X)
    ani = animation.FuncAnimation(fig,
                                  animate,
                                  interval=int(1000 * pendulum.dt))

root.focus_set()
repaint()
mainloop()
