package com.fw.leetCode;

/**
 * @Author fengwei
 * Created on 2016/10/17/0017.
 * 378. Kth Smallest Element in a Sorted Matrix
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * matrix = [
 *  [ 1,  5,  9],
 *  [10, 11, 13],
 *  [12, 13, 15]
 *  ],
 *  k = 8,
 *  return 13
 */
public class KthSmallestNumInSortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int left = matrix[0][0];
        int right = matrix[m-1][m-1];
        while(left < right) {
            int mid = (left + right) / 2;
            int count = count(matrix, mid);
            if(count < k) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return right;
    }

    public int count(int[][] matrix, int target) {
        int count = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                if(matrix[i][j] <= target) {
                    count++;
                }
            }
        }
        return count;
/*        int m=matrix.length;
        int i=m-1;
        int j=0;
        int count = 0;

        while(i>=0&&j<m){
            if(matrix[i][j]<=target){
                count += i+1;
                j++;
            }else{
                i--;
            }
        }

        return count;*/
    }
}
