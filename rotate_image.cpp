class Solution {
public:
    void rotate(vector<vector<int>>& matrix) {
        int n = matrix[0].size();
        for(int i = 0; i < n; i++){
            for(int j = i+1; j < n ; j++){
                swap(matrix[i][j], matrix[j][i]);
            }
        }
        
        for(int i = 0; i < n; i++){
            reverse(matrix[i].begin(), matrix[i].end());
        }
    }
};

//In this problem we simply take the transpose of a matrix and then rotate each row. 
