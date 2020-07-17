# -*- coding: utf-8 -*-
"""
Created on Fri Dec 20 23:58:41 2019

@author: Abhi
"""

from tkinter import *

root = Tk()

def x(event):
    print("abhishek k")
    


button_1 = Button(root, text = "Start Attendance")
button_1.bind("<Button-1>", x)
button_1.pack()
root.mainloop()