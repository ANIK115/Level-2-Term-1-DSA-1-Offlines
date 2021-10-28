import matplotlib.pyplot as plt

input_size = [1,2,3,4,5,6]
merge_ascending = [1.2, 1.44, 2.50, 5.09, 27.04, 119.54]
merge_descending = [1.33, 1.31, 2.40, 5.46, 27.13, 116.8]
merge_random = [1.24, 1.42, 2.74, 5.72, 30.0, 223.25]

quick_ascending = [0.98, 1.43, 8.09, 108.03, 6017.04, 801006.03 ]
quick_descending = [1.05, 1.18, 6.72, 102.58, 5682.0, 809576.88 ]
quick_random = [0.96, 1.12, 1.71, 3.68, 27.89, 115.0]

# for dotted points
#plt.scatter(input_size, merge_ascending, color='red')
plt.scatter(input_size, merge_descending, color='blue')
#plt.scatter(input_size, merge_random, color='green')

#for curve
#plt.plot(input_size, merge_ascending, label = "merge sort for ascending input")
plt.plot(input_size, merge_descending, label = "merge sort for descending input")
#plt.plot(input_size, merge_random, label = "merge sort for random input")

#plt.plot(input_size, quick_ascending, label = "quick sort for ascending input")
plt.plot(input_size, quick_descending, label = "quick sort for descending input")
#plt.plot(input_size, quick_random, label = "quick sort for random input")

#plt.scatter(input_size, quick_ascending, color='black')
plt.scatter(input_size, quick_descending, color='yellow')
#plt.scatter(input_size, quick_random, color='violet')


plt.xlabel('Log(Input Size)')
plt.ylabel('Average Runtime in milliseconds')
plt.legend()
plt.grid(True)
plt.show()
