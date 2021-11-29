# -*- coding: utf-8 -*-
"""Agoda DE Q3.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1gxVsdEoDojfnbm4YTvfP_orp2dY-S_KD

# Question 3:
A company is performing an analysis on the computers at its main office. The computers are spaced along a single row. The analysis is performed in the following way:

1.   Choose a contiguous segment of a certain number of computers, starting from the beginning of the row.
2.   Analyze the available harddisk space on each of the computers.
3.   Determine the minimum available disk space within this segment.

After performing these steps for the first segment, it is then repeated for the next segment, continuing this procedure until the ned of the row (i.e if the segment size is 4, computers 1 to 4 would be analyzed, then 2 to 5, etc.,) Given this analysis procedure, find the maximum available disk space among all the minima that are found during the analysis

**Example**

n = 3, the number of computers
space = [8, 2, 4]
x = 2, the length of analysis segments

In this array of computers, the subarrays of size 2 are [8, 2] and [2, 4]. Thus, the initial analysis returns 2 and 2 because those are the minima for the segments. Finally, the maximum of these values is 2. Therefore, the answer is 2.

**Function Description**

Complete the function segment in the editor below.
segment has the following parameter(s):

int x: the segment length to analyze

int space[n]: the available hard disk space on each of the computers

Returns:

int: the maximum of the minimum values of available hard disk space
found while analyzing the computers in segments of numComps


Constraints


1.   1≤n≤106
2.   1≤x≤n
3.   1 ≤ space[i] ≤ 109
"""

# compute the disk space analysis based on the given logic
def dsa_compute(x_seg_len, n_space, space_list):
  if x_seg_len <= n_space:
    space_chunks = [space_list[x:x+x_seg_len] \
            for x in range(0, n_space) \
            if x+x_seg_len <= n_space]
    min_sub_seg = [min(seg) for seg in space_chunks]
    return max(min_sub_seg)
  else:
    return -1


if __name__ == "__main__":
    x_seg_len = int(input())
    n_space = int(input())
    space_list = []
    for i in range(0, n_space):
        space_i = int(input())
        space_list.append(space_i)
    max_ds = dsa_compute(x_seg_len, n_space, space_list)
    print(max_ds)

