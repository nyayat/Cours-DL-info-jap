#!/usr/bin/env python

"""
setup.py file for Pendulum example
"""

from distutils.core import setup, Extension
from subprocess import check_output

heptagon_headers = \
    check_output("heptc -where", shell=True) \
    .decode("utf-8") \
    .replace("\n", "/c")

pendulum_module = Extension('_pendulum',
                            include_dirs = [heptagon_headers,
                                            '.',
                                            'pendulum_c'],
                            sources=['mathext.c',
                                     'debug.c',
                                     'cutils.c',
                                     'pendulum_wrap.c',
                                     'pendulum_c/pendulum_types.c',
                                     'pendulum_c/pendulum.c'],
                            )

setup (name = 'pendulum',
       version = '0.1',
       author      = "Adrien Guatto",
       description = "Wrapper for Heptagon module",
       ext_modules = [pendulum_module],
       py_modules = ["pendulum"],
       )
