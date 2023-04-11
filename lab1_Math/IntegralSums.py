from matplotlib.collections import PolyCollection
import matplotlib.pyplot as plt
import numpy as np
from matplotlib.patches import Rectangle
# -*- coding: cp1251 -*-

a=-1
b=0
N=100
PointSelection="medium" #left/right

dx = (b-a)/N
x = np.arange(a, b+0.01, 0.01)
y=lambda x: np.sin(x*2)
sum=0.0



xi_l=np.arange(a, b,dx) #left points
xi_r=np.arange(a+dx,b+dx, dx)#right
xi_m=np.arange(a+0.5*dx,b,dx) #medium

fig, ax = plt.subplots()
ax.set_title('Selected '+ PointSelection+ ' points \n'+'Amount of points of partition - 100' + '\n y = sin(2x)', fontsize = 9)

if PointSelection=="left":
    for xi in xi_l:
        ax.add_patch (Rectangle((xi, 0), dx, y(xi), edgecolor = 'g',facecolor = 'g', alpha = 0.2 ,fill= True,lw= 1 ))
        sum+=y(xi)*dx

elif PointSelection=="right":
    for xi in xi_r:
        ax.add_patch (Rectangle((xi-dx, 0), dx, y(xi), edgecolor = 'g',facecolor = 'g', alpha = 0.2 ,fill= True,lw= 1 ))
        sum+=y(xi)*dx
else:
    for xi in xi_m:
        ax.add_patch (Rectangle((xi-0.5*dx, 0), dx, y(xi), edgecolor = 'g',facecolor = 'g', alpha = 0.2 ,fill= True,lw= 1 ))
        sum+=y(xi)*dx


plt.text(-0.4,-1.0,sum)
plt.plot(x, y(x), c = "r",lw=2)
plt.show() 

